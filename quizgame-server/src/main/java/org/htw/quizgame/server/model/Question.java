package org.htw.quizgame.server.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.model.util.ConvertsTo;
import org.htw.quizgame.server.model.util.SaveAs;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Question implements ConvertsTo<QuestionDTO>, SaveAs<Question> {

  @Id
  private String id;

  private String question;

  private List<String> answers;

  private String correctAnswer;

  public Question(QuestionDTO dto) {
    super();
    question = dto.getQuestion();
    answers = dto.getAnswerChoices();
    correctAnswer = dto.getCorrectAnswer();
  }


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

  @Override
  public String toString() {
    return "Question{" +
        "Id='" + id + '\'' +
        ", question='" + question + '\'' +
        ", answers=" + answers +
        ", correctAnswer='" + correctAnswer + '\'' +
        '}';
  }
}
