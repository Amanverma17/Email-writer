package com.email.writer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;
    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Value("${gemini_api_key}")
    private String geminiApiKey;

    @Value("${gemini_api_url}")
    private String geminiApiUrl;



    public String generateEmailReply(EmailRequest emailRequest) {
//        build the prompt
        String prompt= buildPrompt(emailRequest);


//        craft a request
        Map<String, String> requestBody= new HashMap<>();
        requestBody.put("model", "gemini-3.6-flash");
        requestBody.put("input", prompt);

//        do request and get response
        String response= webClient
                .post()
                .uri(geminiApiUrl)
                .header("x-goog-api-key", geminiApiKey)
                .header("content-type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

//        extract response
        return extractResponseContent(response);
    }


    private String extractResponseContent(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);

            return rootNode.path("steps")
                    .get(1)
                    .path("content")
                    .get(0)
                    .path("text")
                    .asString();

        } catch (Exception e){
            return "Error processing response"+ e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt= new StringBuilder();
        prompt.append("Write a single professional email reply to the email below. ");
        prompt.append("Return only the email reply. ");
        prompt.append("Do not provide multiple options, explanations, or alternatives. ");
        prompt.append("Please don't generate the subject line. ");

        if(emailRequest.getTone() != null && !emailRequest.getTone().isEmpty())
                prompt.append("Use ").append(emailRequest.getTone()).append("tone.");
        prompt.append("/n Original mail: /n").append(emailRequest.getEmailContent());

        return prompt.toString();
    }
}
