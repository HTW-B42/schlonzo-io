package org.htw.quizgame.server.api;

import org.htw.quizgame.api.GameApi;
import org.htw.quizgame.api.model.GameSessionDTO;
import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.service.IdentityProvider;
import org.htw.quizgame.server.model.GameSession;
import org.htw.quizgame.server.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class GameController implements GameApi {
  private final IdentityProvider identityProvider;
  private final Question exampleQuestion;

  @Autowired
  public GameController(IdentityProvider identityProvider, Question exampleQuestion) {
    this.identityProvider = identityProvider;
    this.exampleQuestion = exampleQuestion;
  }

  @Override
  public ResponseEntity<GameSessionDTO> answerQuestion(String sessionToken, Boolean userAnsweredCorrectly) {
    GameSessionDTO gameSessionDTO = identityProvider
        .findValidSessionBySessionToken(sessionToken)
        .orElseThrow(NoSuchElementException::new)
        .answerQuestion(userAnsweredCorrectly)
        .getGameSession().toDTO();
    return ResponseEntity.ok(gameSessionDTO);
  }

  @Override
  public ResponseEntity<QuestionDTO> getQuestion(String sessionToken) {
    QuestionDTO questionDTO = identityProvider
        .findValidSessionBySessionToken(sessionToken)
        .orElseThrow(RuntimeException::new)
        .getGameSession()
        // TODO question vom questionservice holen
        .nextQuestion(exampleQuestion)
        .getQuestion().toDTO();
    return ResponseEntity.ok(questionDTO);
  }

  @Override
  public ResponseEntity<GameSessionDTO> startGame(String sessionToken) {
    GameSessionDTO gameSessionDTO = identityProvider
        .findValidSessionBySessionToken(sessionToken)
        .orElseThrow(RuntimeException::new)
        // TODO get gamesession from service
        .joinGame(new GameSession())
        .getGameSession().toDTO();
    return ResponseEntity.ok(gameSessionDTO);
  }

}
