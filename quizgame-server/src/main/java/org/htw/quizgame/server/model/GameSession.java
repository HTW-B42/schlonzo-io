package org.htw.quizgame.server.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Data;
import org.htw.quizgame.api.model.GameSessionDTO;
import org.htw.quizgame.api.model.UserScoreDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class GameSession {

  @Id
  private String gameID;

  private List<User> lobbyMembers = new ArrayList<>();

  private Map<User, BigDecimal> scoreboard = new HashMap<>();

  private Boolean gameOver = false;

  public GameSession(List<User> initialMembers) {
    super();
    initialMembers.forEach(user -> {
      lobbyMembers.add(user);
      scoreboard.put(user, BigDecimal.valueOf(0));
    });
  }

  public void addScore(User user, BigDecimal score) {
    BigDecimal newScore = scoreboard.get(user).add(score);
    scoreboard.put(user, newScore);
  }

  public GameSessionDTO toDTO() {
    return new GameSessionDTO()
        .gameID(gameID)
        .lobbyMembers(lobbyMembers.stream().map(User::toDTO).toList())
        .scoreboard(scoreboard.entrySet().stream().map(GameSession::toUserScoreDTO).toList())
        .gameOver(gameOver);
  }

  private static UserScoreDTO toUserScoreDTO(Entry<User, BigDecimal> userScore) {
    return new UserScoreDTO()
        .userId(userScore.getKey().getUserId())
        .userScore(userScore.getValue());
  }

}

