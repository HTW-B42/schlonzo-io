package org.htw.quizgame.server.data;

import org.htw.quizgame.server.model.GameSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameSessionRepository extends MongoRepository<GameSession, String> {

}
