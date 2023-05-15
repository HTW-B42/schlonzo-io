package org.htw.quizgame.server;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.htw.quizgame.api.model.ApiInfoDTO;
import org.htw.quizgame.api.model.GameSessionDTO;
import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.htw.quizgame.api.model.UserScoreDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ServerConfig {

  private final UserDTO exampleUser = new UserDTO()
      .userId(UUID.randomUUID().toString())
      .userName("jakob_nicht")
      .userEmail("string@google.de")
      .userConfirmed(false);

  @Bean
  public ApiInfoDTO apiInfo() {
    return new ApiInfoDTO().infoMsg("hallo welt");
  }

  @Bean
  public GameSessionDTO exampleGameSession() {
    String gameID = UUID.randomUUID().toString();
    List<UserDTO> lobbyMembers = List.of(exampleUser);
    List<UserScoreDTO> scoreboard = List.of(
        new UserScoreDTO()
            .userId(exampleUser.getUserId())
            .userScore(BigDecimal.valueOf(420))
    );
    return new GameSessionDTO()
        .gameID(gameID)
        .gameOver(false)
        .lobbyMembers(lobbyMembers)
        .scoreboard(scoreboard);
  }

  @Bean
  public QuestionDTO exampleQuestion() {
    String question = "Hauptstadt von Deutschland";
    List<String> answerList = List.of("Paris", "Bonn", "Amsterdam", "Berlin");
    String corrAnswer = "Paris";
    return new QuestionDTO().question(question).answerChoices(answerList).correctAnswer(corrAnswer);
  }

  @Bean
  public UserDTO exampleUser() {
    return exampleUser;
  }
}
