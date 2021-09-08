package com.experis.experiscodingtestback.repositories;

import com.experis.experiscodingtestback.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    boolean existsUserByEmail(String email);

    @Modifying
    @Transactional
    void deleteByAnswerdateBefore(Date expiryDate);
}
