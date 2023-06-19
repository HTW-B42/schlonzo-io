package org.htw.quizgame.server.data;

import org.htw.quizgame.server.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
