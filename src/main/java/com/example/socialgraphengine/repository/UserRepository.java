package com.example.socialgraphengine.repository;

import com.example.socialgraphengine.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User u " +
            "LEFT JOIN FETCH u.account a " +
            "LEFT JOIN FETCH a.accountDetails ad " +
            "WHERE UPPER(u.email) LIKE UPPER(CONCAT(:query, '%')) " +
            "OR UPPER(ad.personalInfo.firstName) LIKE UPPER(CONCAT(:query, '%')) " +
            "OR UPPER(ad.personalInfo.lastName) LIKE UPPER(CONCAT(:query, '%'))")
    Page<User> searchUsers(@Param("query") String query, Pageable pageable);


    boolean existByEmail(String email);
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE UPPER(u.email) = UPPER(:email)")
    Optional<User> findEmailByIgnoreCase(@Param("email") String email);


}
