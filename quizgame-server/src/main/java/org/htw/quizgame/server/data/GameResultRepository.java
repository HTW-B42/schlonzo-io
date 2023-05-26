package org.htw.quizgame.server.data;

import org.htw.quizgame.server.model.GameResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameResultRepository extends MongoRepository<GameResult, String> {

}
