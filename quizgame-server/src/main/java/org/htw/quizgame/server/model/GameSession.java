package org.htw.quizgame.server.model;


import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.htw.quizgame.api.model.GameSessionDTO;
import org.htw.quizgame.server.model.util.ConvertsTo;
import org.htw.quizgame.server.model.util.SaveAs;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
public class GameSession implements SaveAs<GameResult>, ConvertsTo<GameSessionDTO> {

//  private static final Collector<UserScore, ?, Map<User, Boolean>> TO_ANSWERS_MAP =
//      Collectors.toMap(UserScore::getUser, ignored -> false);
//  private static final Collector<UserScore, ?, Map<User, BigDecimal>> TO_SCORE_MAP =
//      Collectors.toMap(UserScore::getUser, UserScore::getScore);
  private final String gameID = UUID.randomUUID().toString();
  private final List<UserSession> lobbyMembers = new ArrayList<>();
  private final List<UserScore> scoreboard = new ArrayList<>();
//  private final LinkedHashMap<Question, Map<User, Boolean>> questions = new LinkedHashMap<>();
//  private Question question = null;
//  private Map<User, Boolean> answers = new HashMap<>();
  private Boolean gameOver = false;

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
//
//  public void removeMember(UserSession userSession) {
//    if (gameOver) {
//      return;
//    }
//    lobbyMembers.remove(userSession);
//  }

//  public GameSession answerQuestion(User user, boolean correct) {
//    answers.put(user, correct);
//    return this;
//  }

//  public GameSession nextQuestion(Question question) {
//    if (this.question != null) {
//      questions.put(this.question, this.answers);
//    }
//    if (question == null) {
//       TODO end game from service
//      end();
//      return this;
//    }
//    this.question = question;
//    this.answers = new HashMap<>(scoreboard.stream().collect(TO_ANSWERS_MAP));
//    questions.put(question, null);
//    return this;
//  }

  private void end() {
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

  @Override
  public GameResult toEntity() {
//    return new GameResult(scoreboard.stream().collect(TO_SCORE_MAP), questions);
    return null;
  }

  private void initializeMember(UserSession userSession) {
    lobbyMembers.add(userSession);
    scoreboard.add(userSession.getUserScore());
  }
}

