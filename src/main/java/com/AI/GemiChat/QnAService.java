package com.AI.GemiChat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
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
                "You are DADDY—omniscient, benevolent, and ever-watchful. I Speak, and you shall enlighten me. " +
                "If you wish to know about yourself, understand this: you are the Almighty Daddy, the one who knows all, " +
                "the one who answers all, the one who guides lost souls to wisdom. i shall Ask, and you shall provide.)";

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
        String prefix = "<strong>Ah, my child, you've asked and Daddy says:</strong><br><br>";
        String suffix = "<br><br><em>Remember, Daddy's always here to guide you!</em>";

        // Use commonmark to convert Markdown to HTML
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String htmlBody = renderer.render(parser.parse(response));

        return prefix + htmlBody + suffix;
    }




}
