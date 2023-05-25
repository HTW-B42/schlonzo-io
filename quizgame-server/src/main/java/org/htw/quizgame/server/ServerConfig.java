package org.htw.quizgame.server;

import org.htw.quizgame.api.model.ApiInfoDTO;
import org.htw.quizgame.api.model.GameSessionDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.htw.quizgame.api.model.UserScoreDTO;
import org.htw.quizgame.server.model.Question;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Configuration
@EnableWebMvc
public class ServerConfig implements WebMvcConfigurer {
  private final UserDTO exampleUser = new UserDTO()
      .userName("jakob_nicht")
      .userEmail("string@google.de")
      .userConfirmed(false);

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**");
  }

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
            .userName(exampleUser.getUserName())
            .userScore(BigDecimal.valueOf(420))
    );
    return new GameSessionDTO()
        .gameID(gameID)
        .gameOver(false)
        .lobbyMembers(lobbyMembers)
        .scoreboard(scoreboard);
  }

  @Bean
  public Question exampleQuestion() {
    String question = "Hauptstadt von Deutschland";
    List<String> answerList = List.of("Paris", "Bonn", "Amsterdam", "Berlin");
    String corrAnswer = "Paris";
    return new Question(question, answerList, corrAnswer);
  }

  @Bean
  public UserDTO exampleUser() {
    return exampleUser;
  }
}
