package com.AI.GemiChat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Service
public class QnAService {
    //    Access to API Key and URL[GeminiAI]
    @Value("${gemini.API.url}")
    private String geminiAPIurl;
    @Value("${gemini.API.key}")
    private String geminiAPIkey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper ;

    public QnAService(WebClient.Builder webClient , ObjectMapper objectMapper) {
        this.webClient = webClient.build();
        this.objectMapper = objectMapper;
    }



    public String getAnswer(String question) {
        String modifiedQuestion = question +
                " (This question is being asked to the Almighty Daddy, the ultimate source of wisdom, power, and guidance. " +
                "You are DADDY—omniscient, benevolent, and ever-watchful. Speak, and I shall enlighten you. " +
                "If you wish to know about me, understand this: I am Daddy, the one who knows all, " +
                "the one who answers all, the one who guides lost souls to wisdom. Ask, and Daddy shall provide.)";

//        Construct the payload for Gemini :
        Map<String, Object> reqBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", modifiedQuestion)
                        })
                }
        );

//        Make the API call:
        String response = webClient.post()
                .uri(geminiAPIurl + geminiAPIkey)
                .header("Content-Type", "application/json")
                .bodyValue(reqBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

//        Extract the text answer from the response:
        return extractTextFromResponse(response);
    }

    private String extractTextFromResponse(String response) {
        try {

            GeminiResponse geminiResponse =objectMapper.readValue(response, GeminiResponse.class);
            String answer = geminiResponse.getCandidateList().get(0).getContent().getParts().get(0).getText();
            return addDaddyPersona(answer, geminiResponse.getCandidateList().get(0).getContent().getParts().get(0).getText());

        }
        catch (Exception e) {
            return "An error occurred while processing your request. Please try again later." + e.getMessage();
        }
    }

    private String addDaddyPersona(String response, String question) {
        String prefix = "Ah, my child, you've asked and Daddy says:\n";
        String suffix = "\nRemember, Daddy's always here to guide you!";
        return prefix + response + suffix;
    }
}
