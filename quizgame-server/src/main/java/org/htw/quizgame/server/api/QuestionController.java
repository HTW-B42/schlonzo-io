package org.htw.quizgame.server.api;

import org.htw.quizgame.api.QuestionApi;
import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.data.QuestionRepository;
import org.htw.quizgame.server.model.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class QuestionController implements QuestionApi {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public ResponseEntity<Void> deleteQuestion(String s) {
        Optional<Question> question = questionRepository.findQuestionByQuestion(s);
        System.out.println("Question found?: " + question);
        if (question.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("Question deleted:" + question);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> uploadQuestion(QuestionDTO questionDTO) {
        Question question = questionRepository.insert(new Question(questionDTO.getQuestion(),questionDTO.getAnswerChoices(),questionDTO.getCorrectAnswer()));
        if(question.answers().size() != 4){
            return ResponseEntity.notFound().build();
        }
        System.out.println("New Question added: " + question);
        return ResponseEntity.ok().build();
    }
}
