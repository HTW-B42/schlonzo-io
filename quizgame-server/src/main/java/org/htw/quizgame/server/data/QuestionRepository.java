package org.htw.quizgame.server.data;

import org.htw.quizgame.server.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, String> {
    @Query("{ 'question' : { $regex: ?0, $options: 'i' } }")
    Optional<Question> findByQuestion(String question);


    @Query("{ 'question' : { $regex: ?0, $options: 'i' } }")
    void deleteByQuestion(String question);
}
