package com.airbnb.repository;

import com.airbnb.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findByUsername(String username);

    @Modifying
    @Query("UPDATE UserToken ut SET ut.isDelete = true WHERE ut.username = :username AND ut.isDelete = false")
    void softDeleteOldTokensByUsername(String username);
}