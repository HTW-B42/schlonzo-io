package org.htw.quizgame.server.model;


import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Document
public record GameResult(Map<User, BigDecimal> scoreBoard, LinkedHashMap<Question, Map<User, Boolean>> questions)
    implements ConvertsTo<String>, SaveAs<GameResult> {

  @Override
  public String toDTO() {
    // TODO
    return "TODO";
  }

  @Override
  public GameResult toEntity() {
    return this;
  }
}
