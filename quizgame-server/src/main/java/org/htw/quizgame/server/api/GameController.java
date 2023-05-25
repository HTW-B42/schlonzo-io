package org.htw.quizgame.server.api;

import org.htw.quizgame.api.GameApi;
import org.htw.quizgame.api.model.GameSessionDTO;
import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.IdentityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class GameController implements GameApi {
  private final IdentityProvider identityProvider;

  @Autowired
  public GameController(IdentityProvider identityProvider) {
    this.identityProvider = identityProvider;
  }

  @Override
  public ResponseEntity<GameSessionDTO> answerQuestion(String sessionToken, Boolean userAnsweredCorrectly) {
    GameSessionDTO gameSessionDTO = identityProvider
        .findValidSessionBySessionToken(sessionToken)
        .orElseThrow(NoSuchElementException::new)
        .answerQuestion(userAnsweredCorrectly)
        .getGameSession()
        .toDTO();
    return ResponseEntity.ok(gameSessionDTO);
  }

  @Override
  public ResponseEntity<QuestionDTO> getQuestion(String sessionToken) {
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<GameSessionDTO> startGame(String sessionToken) {
    return ResponseEntity.ok().build();
  }

}
