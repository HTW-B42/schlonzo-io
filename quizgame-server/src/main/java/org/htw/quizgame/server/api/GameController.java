package org.htw.quizgame.server.api;

import org.htw.quizgame.api.GameApi;
import org.htw.quizgame.api.model.GameSessionDTO;
import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.data.GameSessionRepository;
import org.htw.quizgame.server.data.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController implements GameApi {

  private final UserSessionRepository userSessionRepository;
  private final GameSessionRepository gameSessionRepository;
  private final QuestionDTO exampleQuestion;
  private final GameSessionDTO exampleGameSession;

  @Autowired
  public GameController(
      UserSessionRepository userSessionRepository,
      GameSessionRepository gameSessionRepository,
      QuestionDTO exampleQuestion,
      GameSessionDTO exampleGameSession) {
    this.userSessionRepository = userSessionRepository;
    this.gameSessionRepository = gameSessionRepository;
    this.exampleQuestion = exampleQuestion;
    this.exampleGameSession = exampleGameSession;
  }

  @Override
  public ResponseEntity<QuestionDTO> gameQuestionGet(String sessionToken) {
    return ResponseEntity.ok(exampleQuestion);
  }

  @Override
  public ResponseEntity<GameSessionDTO> gameQuestionPost(String sessionToken,
      Boolean userAnsweredCorrectly) {
    return ResponseEntity.ok(exampleGameSession);
  }

  @Override
  public ResponseEntity<GameSessionDTO> gameStartGet(String sessionToken) {
    userSessionRepository.toString();
    return ResponseEntity.ok(exampleGameSession);
  }
}
