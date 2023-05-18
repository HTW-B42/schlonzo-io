package org.htw.quizgame.server.services;

import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.data.QuestionRepository;
import org.htw.quizgame.server.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public QuestionDTO getQuestionById(String id) {
        Question question = questionRepository.findById(id).orElse(null);
        return question != null ? question.toDTO() : null;
    }


    public QuestionDTO createQuestion(QuestionDTO questionDto) {
        Question question = new Question(questionDto);
        Question savedQuestion = questionRepository.save(question);
        return savedQuestion.toDTO();
    }


    public void deleteQuestion(String id) {
        questionRepository.deleteById(id);
    }
}
