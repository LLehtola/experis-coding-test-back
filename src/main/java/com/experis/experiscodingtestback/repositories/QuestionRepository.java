package com.experis.experiscodingtestback.repositories;
import com.experis.experiscodingtestback.models.Answer;
import com.experis.experiscodingtestback.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Answer> {

    List<Question> findAllByAnswersId(long id);

    boolean existsById(long id);
}

