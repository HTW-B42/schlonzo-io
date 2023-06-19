package org.htw.quizgame.server.model;

import lombok.Getter;
import org.htw.quizgame.api.model.AuthSuccessDTO;
import org.htw.quizgame.server.model.util.ConvertsTo;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Getter
public class UserSession implements ConvertsTo<AuthSuccessDTO> {

  private final User user;
  private final String sessionToken;
  @Id
  private String userSessionID;
  private LocalDateTime expires;
  private GameSession gameSession;
  private UserScore userScore;

  public UserSession(User user, String sessionToken) {
    super();
    this.user = user;
    this.sessionToken = sessionToken;
    touch();
  }

  public UserSession touch() {
    expires = LocalDateTime.now().plusMinutes(10);
    return this;
  }

  public UserSession joinGame(GameSession gameSession) {
    if (isNull(this.gameSession) || this.gameSession.getGameOver()) {
      this.gameSession = gameSession;
      this.userScore = UserScore.forSession(this);
      this.gameSession.addMember(this);
      return this;
    }
    throw new RuntimeException("game already running");
  }

  public UserSession answerQuestion(boolean correct) {
//    if (gameSession == null || userScore == null || gameSession.getGameOver()) {
//      throw new RuntimeException("no question to answer...");
//    } else {
//      gameSession.answerQuestion(user, correct);
      userScore.addScore(BigDecimal.valueOf(correct ? 10 : -5));
//    }
    return this;
  }

  public UserSession leaveGame() {
    // TODO
    return this;
  }

  public boolean isValid() {
    return expires.isAfter(LocalDateTime.now());
  }

  @Override
  public AuthSuccessDTO toDTO() {
    return new AuthSuccessDTO()
        .user(user.toDTO())
        .sessionToken(sessionToken);
  }
}
