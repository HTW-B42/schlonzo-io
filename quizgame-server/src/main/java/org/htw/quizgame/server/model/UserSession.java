package org.htw.quizgame.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {

  @Id
  private String userSessionID;
  private User user;
  private String sessionToken;
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
    if (correct) {
      userScore.addScore(BigDecimal.valueOf(10));
    } else {
      userScore.addScore(BigDecimal.valueOf(-5));
    }
    return this;
  }

  public UserSession leaveGame() {
    // TODO
    return this;
  }

  public boolean isValid() {
    return expires.isAfter(LocalDateTime.now());
  }
}
