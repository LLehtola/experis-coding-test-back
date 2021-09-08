package com.experis.experiscodingtestback.repositories;
import com.experis.experiscodingtestback.models.Answer;
import com.experis.experiscodingtestback.models.Question;
import com.experis.experiscodingtestback.models.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByAnswersId(long id);
    boolean existsById(long id);

    @Query(
            value = "SELECT * FROM question WHERE question.hidden = false AND question.category = ?1",
            nativeQuery = true
    )
    List<Question> findAllByCategory(int category);
}

