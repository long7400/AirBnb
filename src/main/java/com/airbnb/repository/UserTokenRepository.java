package com.airbnb.repository;

import com.airbnb.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    @Query("SELECT ut FROM UserToken ut WHERE LOWER(ut.username) = LOWER(:username) AND ut.isDelete = FALSE")
    Optional<UserToken> findByUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE UserToken ut SET ut.isDelete = TRUE WHERE LOWER(ut.username) = LOWER(:username) AND ut.isDelete = FALSE")
    void softDeleteOldTokensByUsername(@Param("username") String username);
}