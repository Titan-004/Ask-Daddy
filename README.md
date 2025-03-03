Tried SpringAI integration with Google's Gemini model . 

feel free to add any thing useful or creative🙂 !

# ASKDADDY - The Almighty AI Chatbot

ASKDADDY is an AI-powered chatbot built with **Spring Boot** and **Spring AI**, integrating with the **Gemini AI model** to provide intelligent, context-aware responses. Personified as the **Almighty Daddy**, it delivers wisdom, guidance, and humor in its interactions.

## 🚀 Features
- **Conversational AI:** Uses **Gemini AI** to generate insightful responses.
- **Personified as Almighty Daddy:** Adds a unique, humorous, and powerful persona to responses.
- **Spring Boot Backend:** Efficient and scalable architecture.
- **Customizable Behavior:** Allows modifying responses for different use cases.

## 🛠️ Tech Stack
- **Backend:** Spring Boot, Spring AI
- **AI Model:** Gemini AI
- **Data Format:** JSON (Map-based input and output)
- **Deployment:** Docker (optional)

## 🔧 Setup & Installation
### Prerequisites
- **Java 17+** (Ensure you have JDK 17 or later installed)
- **Maven** (For building the project)
- **Gemini AI API Key** (For AI responses)

### Steps to Run
1. **Clone the Repository**
   ```sh
   git clone https://github.com/Titan-004/AIchatBot.git
   cd AIchatBot
   ```
2. **Configure API Key**
    - Add your **Gemini AI API Key** in `application.properties`:
   ```properties
   gemini.api.key=YOUR_API_KEY
   ```
3. **Build and Run**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. **Test the API**
    - Use **Postman** or `curl` to send questions to Daddy:
   ```sh
   curl -X POST "http://localhost:8080/ask" -H "Content-Type: application/json" -d '{"question":"What is life?"}'
   ```

## 📝 API Endpoints
### 1. Ask a Question
- **Endpoint:** `POST /ask`
- **Request:**
  ```json
  {
    "question": "Who are you?"
  }
  ```
- **Response:**
  ```json
  {
    "answer": "I am ASKDADDY, the Almighty Daddy—wise, powerful, and always here to answer your questions. Ask, and Daddy shall provide."
  }
  ```

## 🤖 How It Works
- The **getAnswer()** method in the backend sends user queries to Gemini AI.
- Each question is appended with Daddy’s persona statement to maintain character consistency.
- The AI processes the question and returns a response infused with **Almighty Daddy’s** wisdom.

## 🏗️ Future Enhancements
- **Frontend UI** with a sleek chat interface.
- **Voice-based interaction** using Speech-to-Text.
- **Multi-user support** with personalized AI responses.

## 💡 Contributing
Feel free to submit pull requests, suggest improvements, or report issues.

## 📜 License
This project is **open-source** under the MIT License.

---

💬 *Ask, and Daddy shall provide!*


