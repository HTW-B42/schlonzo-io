package org.htw.quizgame.server.model;

import lombok.Getter;
import lombok.Setter;
import org.htw.quizgame.api.model.QuestionDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("questions")
public class Question {

    @Id
    private String questionId;

    private String question;

    private List<String> answerChoices;

    private String correctAnswer;

    public Question(QuestionDTO questionDto) {
        //this.questionId = questionDto.getQuestionId(); TODO: auskommentieren wenn implementiert
        this.question = questionDto.getQuestion();
        this.answerChoices = questionDto.getAnswerChoices();
        this.correctAnswer = questionDto.getCorrectAnswer();
    }

    public QuestionDTO toDTO() {
        QuestionDTO questionDTO = new QuestionDTO();
        //questionDTO.setQuestionId(this.questionId); TODO: auskommentieren wenn implementiert
        questionDTO.setQuestion(this.question);
        questionDTO.setAnswerChoices(this.answerChoices);
        questionDTO.setCorrectAnswer(this.correctAnswer);
        return questionDTO;
    }
}
