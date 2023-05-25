package org.htw.quizgame.server.model;

import org.htw.quizgame.api.model.QuestionDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public record Question(String question, List<String> answers, String correctAnswer)
    implements ConvertsTo<QuestionDTO>, SaveAs<Question> {
  @Override
  public QuestionDTO toDTO() {
    return new QuestionDTO()
        .question(question)
        .answerChoices(answers)
        .correctAnswer(correctAnswer);
  }

  @Override
  public Question toEntity() {
    return this;
  }
}
