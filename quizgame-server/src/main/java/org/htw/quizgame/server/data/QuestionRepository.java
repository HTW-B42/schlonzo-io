package org.htw.quizgame.server.data;

import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
/*
    public default QuestionDTO toDTO() {
        QuestionDTO dto = new QuestionDTO();
        dto.setQuestionId(this.questionId);
        dto.setQuestionText(this.questionText);
        dto.setAnswerChoices(this.answerChoices);
        dto.setCorrectAnswer(this.correctAnswer);
        return dto;
    }*/

}
