package com.AI.GemiChat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

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

    public QnAService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public String getAnswer(String question) {
//        Construct the payload for Gemini :
        Map<String, Object> reqBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", question)
                        })
                }
        );

//        Make the API call:
         String response=webClient.post()
                .uri(geminiAPIurl+geminiAPIkey)
                .header("Content-Type","application/json")
                .bodyValue(reqBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

//        Return the response:

        return response;
    }
}
