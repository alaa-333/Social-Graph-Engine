package com.example.socialgraphengine.repository;

import com.example.socialgraphengine.model.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {

    @Query("SELECT ad FROM AccountDetails ad " +
            "JOIN ad.account a " +
            "JOIN a.user u " +
            "WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(ad.personalInfo.firstName) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(ad.personalInfo.lastName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<AccountDetails> searchProfiles(@Param("query") String query);
}
