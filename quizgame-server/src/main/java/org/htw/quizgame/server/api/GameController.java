package org.htw.quizgame.server.api;

import org.htw.quizgame.api.GameApi;
import org.htw.quizgame.api.model.GameSessionDTO;
import org.htw.quizgame.api.model.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController implements GameApi {


    private final QuestionDTO exampleQuestion;
    private final GameSessionDTO exampleGameSession;

    @Autowired
    public GameController(QuestionDTO exampleQuestion, GameSessionDTO exampleGameSession){
        this.exampleQuestion = exampleQuestion;
        this.exampleGameSession = exampleGameSession;
    }

    @Override
    public ResponseEntity<QuestionDTO> gameQuestionGet() {
        return ResponseEntity.ok(exampleQuestion);
    }

    @Override
    public ResponseEntity<GameSessionDTO> gameQuestionPost(Boolean userAnsweredCorrectly) {
        return ResponseEntity.ok(exampleGameSession);
    }

    @Override
    public ResponseEntity<GameSessionDTO> gameStartGet() {
        return ResponseEntity.ok(exampleGameSession);
    }
}
