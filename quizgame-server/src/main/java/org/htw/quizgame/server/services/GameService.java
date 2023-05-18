package org.htw.quizgame.server.services;

import org.htw.quizgame.api.model.GameSessionDTO;
import org.htw.quizgame.api.model.QuestionDTO;
import org.htw.quizgame.server.data.GameSessionRepository;
import org.htw.quizgame.server.data.QuestionRepository;
import org.htw.quizgame.server.model.GameSession;
import org.htw.quizgame.server.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameService {
    private final GameSessionRepository gameSessionRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public GameService(GameSessionRepository gameSessionRepository, QuestionRepository questionRepository) {
        this.gameSessionRepository = gameSessionRepository;
        this.questionRepository = questionRepository;
    }

    public QuestionDTO getNextQuestion(String gameId) {
        List<Question> allQuestions = questionRepository.findAll();
        Question question = allQuestions.get(new Random().nextInt(allQuestions.size()));
        return question.toDTO();
    }

    public void answerQuestion(String gameId, String questionId, String answer) {
        GameSession gameSession = gameSessionRepository.findById(gameId).orElse(null);
        Question question = questionRepository.findById(questionId).orElse(null);
        if (gameSession != null && question != null) {
            if (question.getCorrectAnswer().equals(answer)) {
                gameSession.updateScore(questionId, true);
            } else {

                gameSession.updateScore(questionId, false);
            }
            gameSessionRepository.save(gameSession);
        }
    }
}
