package org.htw.quizgame.server.api;

import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.data.QuestionRepository;
import org.htw.quizgame.server.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

public class QuestionController{
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<QuestionDTO> addQuestion(QuestionDTO newQuestionDTO) {
        Question newQuestion =questionRepository.insert(new Question(newQuestionDTO.getQuestion(),newQuestionDTO.getAnswerChoices(),newQuestionDTO.getCorrectAnswer()));
        return ok(newQuestion.toDTO());
    }

    public void deleteQuestion(QuestionDTO newQuestionDTO) {
        questionRepository.delete(new Question(newQuestionDTO.getQuestion(),newQuestionDTO.getAnswerChoices(),newQuestionDTO.getCorrectAnswer()));
    }

    public ResponseEntity<Question> getRndQuestionByID(){
        Optional<Question> requested = questionRepository.findRandomQuestion();
        if (requested == null){
            //TODO exception
        }
        return ResponseEntity.ok(requested.get());
    }

}
