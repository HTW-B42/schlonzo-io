package org.htw.quizgame.server.data;

import org.htw.quizgame.server.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findRandomQuestions(int count);

    Optional<Question> findRandomQuestion();
}
