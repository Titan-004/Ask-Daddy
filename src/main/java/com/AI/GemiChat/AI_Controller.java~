package com.AI.GemiChat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai/qna")
@CrossOrigin(origins = "http://localhost:5173")  // or "*" for testing

public class AI_Controller {
    private final QnAService qnaService;

    public AI_Controller(QnAService qnaService) {
        this.qnaService = qnaService;
    }

    @PostMapping("/ask")
    public ResponseEntity<String> askMe(@RequestBody Map<String,String> payload){
        String question = payload.get("question");
        String answer = qnaService.getAnswer(question);
        return ResponseEntity.ok(answer);
    }

}
