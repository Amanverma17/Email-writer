# ✉️ Email Writer Assistant

An AI-powered email reply generator that helps users generate professional email responses directly from Gmail.

The project combines a **Spring Boot backend**, **React frontend**, **Google Gemini AI**, and a **Chrome Extension** to provide AI-powered email assistance inside Gmail.

## 🚀 Features

- Generate AI-powered email replies
- Choose different email tones
  - Professional
  - Casual
  - Friendly
- React-based frontend interface
- Chrome Extension integration with Gmail
- Generate replies directly inside Gmail
- Copy generated replies to clipboard
- Spring Boot REST API
- Google Gemini API integration
- Environment variable support for API credentials

## 🏗️ Architecture

```text
                    ┌─────────────────────┐
                    │       Gmail         │
                    │                     │
                    │    AI Reply Button  │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │  Chrome Extension   │
                    │                     │
                    │   Content Script    │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Spring Boot API   │
                    │                     │
                    │  Email Generation   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    Gemini API       │
                    │                     │
                    │   AI Generation     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │ Generated Email     │
                    │ Reply               │
                    └─────────────────────┘
```

## 🛠️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring WebFlux
- WebClient
- Lombok
- Maven

### Frontend
- React
- Vite
- JavaScript
- Material UI
- Axios

### Browser Extension
- JavaScript
- Chrome Extension Manifest V3
- MutationObserver
- Gmail DOM integration

### AI
- Google Gemini API

## 📂 Project Structure

```text
Email-writer/
│
├── email-writer/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/email/writer/
│   │       │       ├── EmailWriterApplication.java
│   │       │       ├── EmailGeneratorController.java
│   │       │       ├── EmailGeneratorService.java
│   │       │       └── EmailRequest.java
│   │       │
│   │       └── resources/
│   │           └── application.properties
│   │
│   └── pom.xml
│
├── email-writer-react/
│   ├── src/
│   │   ├── App.jsx
│   │   └── App.css
│   ├── package.json
│   └── vite.config.js
│
└── email-writer-extension/
    ├── manifest.json
    ├── content.js
    └── content.css
```

## ⚙️ Setup

### 1. Clone the repository

```bash
git clone https://github.com/Amanverma17/Email-writer.git
cd Email-writer
```

### 2. Configure Gemini API

Create environment variables:

```text
GEMINI_URL=your_gemini_api_url
GEMINI_KEY=your_gemini_api_key
```

The application reads them through:

```properties
gemini_api_url=${GEMINI_URL}
gemini_api_key=${GEMINI_KEY}
```

> Never commit your Gemini API key to GitHub.

### 3. Run the Spring Boot backend

Navigate to:

```bash
cd email-writer
```

Run:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The backend runs on:

```text
http://localhost:8080
```

### 4. Run the React frontend

Open another terminal:

```bash
cd email-writer-react
npm install
npm run dev
```

The frontend will be available at:

```text
http://localhost:5173
```

### 5. Install the Chrome Extension

1. Open Chrome.
2. Go to:

```text
chrome://extensions
```

3. Enable **Developer mode**.
4. Click **Load unpacked**.
5. Select:

```text
email-writer-extension
```

6. Open Gmail.
7. Open an email or compose window.
8. Click **AI Reply**.

## 🔌 API

### Generate Email Reply

**POST**

```text
/api/email/generate
```

Request:

```json
{
  "emailContent": "Hi, how are you?",
  "tone": "professional"
}
```

Response:

```text
Dear [Name],

Thank you for reaching out. I hope you are doing well...
```

## 🧠 How It Works

1. User enters an email in the React application or opens a Gmail compose/reply window.
2. The Chrome Extension detects Gmail's dynamic UI using `MutationObserver`.
3. The extension injects an **AI Reply** button into the Gmail toolbar.
4. The email content is extracted from Gmail.
5. The extension sends the email content to the Spring Boot REST API.
6. Spring Boot sends the request to the Gemini API using `WebClient`.
7. Gemini generates the email reply.
8. The generated response is returned to the extension.
9. The extension inserts the generated reply into the Gmail compose box.

## 📚 What I Learned

- Building REST APIs with Spring Boot
- Dependency Injection in Spring
- Using `WebClient` for external API calls
- Integrating Google Gemini API
- Working with environment variables
- Parsing JSON responses using `ObjectMapper` and `JsonNode`
- Building React applications with Vite
- Managing React state using `useState`
- Making API requests using Axios
- Creating Chrome Extensions using Manifest V3
- Using Content Scripts
- Using `MutationObserver` to detect dynamic Gmail elements
- Injecting custom UI into Gmail
- Connecting frontend, backend, AI API, and browser extension

## 🔮 Future Improvements

- Add more email tones
- Add a tone selector directly inside Gmail
- Improve Gmail element detection
- Add loading animations
- Add response regeneration
- Add customizable prompts
- Add support for multiple languages
- Improve extension UI and accessibility
- Deploy the backend and frontend for production use

## 👨‍💻 Author

**Aman Verma**

BTech Student | Java Backend Developer | Spring Boot | DSA

GitHub: https://github.com/Amanverma17
