package org.htw.quizgame.server.api;

import org.htw.quizgame.api.QuestionApi;
import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.data.QuestionRepository;
import org.htw.quizgame.server.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class QuestionController implements QuestionApi {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public ResponseEntity<Void> deleteQuestion(String question) {
        Optional<Question> q = questionRepository.findByQuestion(question.trim());

        System.out.println("Question found?: " + q);
        if (q.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        questionRepository.deleteByQuestion(q.get().question().trim());

        System.out.println("Question deleted:" + question);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> uploadQuestion(QuestionDTO questionDTO) {
        if(questionDTO.getAnswerChoices().size() != 4){
            System.out.println("Wrong number of answers choices");
            return ResponseEntity.notFound().build();
        }
        Question question = questionRepository.insert(new Question(questionDTO.getQuestion().trim(),questionDTO.getAnswerChoices(),questionDTO.getCorrectAnswer()));
        System.out.println("New Question added: " + question);
        return ResponseEntity.ok().build();
    }
}