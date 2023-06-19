package org.htw.quizgame.server.api;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import org.htw.quizgame.api.GameApi;
import org.htw.quizgame.api.model.GameSessionDTO;
import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.data.QuestionRepository;
import org.htw.quizgame.server.model.GameSession;
import org.htw.quizgame.server.model.Question;
import org.htw.quizgame.server.service.IdentityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController implements GameApi {

  private final List<GameSession> gameSessionRepository = new ArrayList<>();
  private final IdentityProvider identityProvider;
  private final QuestionRepository questionRepository;

  @Autowired
  public GameController(IdentityProvider identityProvider, QuestionRepository questionRepository) {
    this.identityProvider = identityProvider;
    this.questionRepository = questionRepository;
  }

  @Override
  public ResponseEntity<QuestionDTO> getQuestion(String sessionToken) {
//    QuestionDTO questionDTO = identityProvider
//        .findValidSessionBySessionToken(sessionToken)
//        .orElseThrow(RuntimeException::new)
//        .getGameSession()
//        // TODO question vom questionservice holen
//        .nextQuestion(null)
//        .getQuestion().toDTO();
    Question question = questionRepository.findAll()
        .stream()
        .min((_ignored, _ignored2) -> ThreadLocalRandom.current().nextInt(-1, 2))
        .orElseThrow(NoSuchElementException::new);
    ResponseEntity<QuestionDTO> ok = ResponseEntity.ok(question.toDTO());
    System.out.println(ok);
    return ok;
  }

  @Override
  public ResponseEntity<GameSessionDTO> startGame(String sessionToken) {
    GameSession session = identityProvider
        .findValidSessionBySessionToken(sessionToken)
        .orElseThrow(RuntimeException::new)
        // TODO get gamesession from service
        .joinGame(new GameSession())
        .getGameSession();
    gameSessionRepository.add(session);
    GameSessionDTO gameSessionDTO = session.toDTO();
    ResponseEntity<GameSessionDTO> ok = ResponseEntity.ok(gameSessionDTO);
    System.out.println(ok);
    return ok;
  }

  @Override
  public ResponseEntity<GameSessionDTO> answerQuestion(String sessionToken, Boolean userAnsweredCorrectly) {
    GameSessionDTO gameSessionDTO = identityProvider
        .findValidSessionBySessionToken(sessionToken)
        .orElseThrow(NoSuchElementException::new)
        .answerQuestion(userAnsweredCorrectly)
        .getGameSession().toDTO();
    ResponseEntity<GameSessionDTO> ok = ResponseEntity.ok(gameSessionDTO);
    System.out.println(ok);
    return ok;
  }

}
