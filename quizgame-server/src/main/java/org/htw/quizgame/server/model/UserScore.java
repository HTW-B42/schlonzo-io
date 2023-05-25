package org.htw.quizgame.server.model;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.htw.quizgame.api.model.UserScoreDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class UserScore implements ConvertsTo<UserScoreDTO> {
  @Id
  @Setter
  private Long userScoreId;
  @ManyToOne
  private UserSession userSession;
  private BigDecimal score;

  public static UserScore forSession(UserSession session) {
    return UserScore.builder()
        .userSession(session)
        .score(BigDecimal.ZERO)
        .build();
  }

  public User getUser() {
    return userSession.getUser();
  }

  public void addScore(BigDecimal score) {
    this.score = this.score.add(score);
  }

  @Override
  public UserScoreDTO toDTO() {
    return new UserScoreDTO()
        .userName(userSession.getUser().getUserName())
        .userScore(score);
  }
}
