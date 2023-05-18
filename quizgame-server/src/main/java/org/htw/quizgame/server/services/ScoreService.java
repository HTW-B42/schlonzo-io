package org.htw.quizgame.server.services;

import org.htw.quizgame.server.data.ScoreRepository;
import org.htw.quizgame.server.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public Optional<Score> getScoreById(String id) {
        return scoreRepository.findById(id);
    }

    public Score createScore(Score score) {
        return scoreRepository.save(score);
    }

    public Score updateScore(Score score) {
        return scoreRepository.save(score);
    }

    public void deleteScore(String id) {
        scoreRepository.deleteById(id);
    }
}
