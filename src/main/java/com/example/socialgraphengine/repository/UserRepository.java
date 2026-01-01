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

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u " +
            "LEFT JOIN u.account a " +
            "LEFT JOIN a.accountDetails ad " +
            "WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(ad.personalInfo.firstName) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(ad.personalInfo.lastName) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<User> searchUsers(@Param("query") String query, Pageable pageable);


    boolean existByEmail(String email);
    Optional<User> findByEmail(String email);

}
