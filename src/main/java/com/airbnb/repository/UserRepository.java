package com.airbnb.repository;

import com.airbnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.username = :username AND u.isDelete = FALSE ")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.email = :email AND u.isDelete = FALSE ")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.isDelete = FALSE")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u LEFT JOIN u.bookings WHERE u.id = :userId AND u.isDelete = FALSE")
    Optional<User> findByIdWithBookings(@Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE u.isDelete = FALSE")
    List<User> findAllUser();
}