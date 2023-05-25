package org.htw.quizgame.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.htw.quizgame.api.model.GameSessionDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class GameSession implements ConvertsTo<GameSessionDTO> {

  @Id
  private String gameID;

  private List<UserSession> lobbyMembers = new ArrayList<>();

  private List<UserScore> scoreboard = new ArrayList<>();

  private Boolean gameOver = false;

  public void addScore(User user, BigDecimal score) {
    if (gameOver) {
      return;
    }
    getScoreFor(user).ifPresentOrElse(
        s -> s.addScore(score),
        () -> System.out.println("score konnte keinem user zugeordnet werden")
    );
  }

  private Optional<UserScore> getScoreFor(User user) {
    return scoreboard.stream()
        .filter(s -> s.getUserSession().getUser().getUserName().equals(user.getUserName()))
        .findFirst();
  }

  public void addMember(UserSession userSession) {
    if (gameOver) {
      return;
    }
    initializeMember(userSession);
  }

  public void removeMember(UserSession userSession) {
    if (gameOver) {
      return;
    }
    lobbyMembers.remove(userSession);
  }

  public void end() {
    gameOver = true;
    // TODO export scores
  }

  @Override
  public GameSessionDTO toDTO() {
    return new GameSessionDTO()
        .gameID(gameID)
        .lobbyMembers(lobbyMembers.stream()
            .map(UserSession::getUser)
            .map(User::toDTO)
            .toList())
        .scoreboard(scoreboard.stream()
            .map(UserScore::toDTO)
            .toList())
        .gameOver(gameOver);
  }

  private void initializeMember(UserSession userSession) {
    lobbyMembers.add(userSession);
    scoreboard.add(userSession.getUserScore());
  }

}

