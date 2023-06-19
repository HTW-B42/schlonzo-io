package org.htw.quizgame.server.api;

import java.util.List;
import org.htw.quizgame.api.QuestionApi;
import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.data.QuestionRepository;
import org.htw.quizgame.server.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController implements QuestionApi {

  private final QuestionRepository questionRepository;

  @Autowired
  public QuestionController(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  @Override
  public ResponseEntity<Void> deleteQuestion(String id) {
    boolean b = questionRepository.existsById(id);
    System.out.println("exists?: " + b);
    if (b) {
      questionRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @Override
  public ResponseEntity<Void> uploadQuestion(QuestionDTO questionDTO) {
    if (questionDTO.getAnswerChoices().size() != 4) {
      System.out.println("Wrong number of answers choices");
      return ResponseEntity.notFound().build();
    }
    Question question = questionRepository.insert(new Question(questionDTO));
    System.out.println("New Question added: " + question.toString());
    return ResponseEntity.ok().build();
  }
}