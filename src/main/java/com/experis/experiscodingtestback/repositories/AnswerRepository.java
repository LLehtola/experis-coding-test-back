package com.experis.experiscodingtestback.repositories;

import com.experis.experiscodingtestback.models.Answer;
import com.experis.experiscodingtestback.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, User> {


    /*
    //@Query(value="select * from Answer", nativeQuery=true)
    @Query(value="select * from answer a where a.user = ?1", nativeQuery=true)
    List<Answer> getAnswersById(String id);
    */
    List<Answer> findAllByUserId(long id);
}
