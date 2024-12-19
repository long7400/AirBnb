package com.airbnb.repository;

import com.airbnb.entity.HomestayAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HomestayAvailabilityRepository extends JpaRepository<HomestayAvailability, Long> {

    @Query("SELECT a FROM HomestayAvailability a " +
            "WHERE a.homestayId = :homestayId " +
            "AND a.status = :status " +
            "AND a.date >= :startDate " +
            "AND a.date <= :endDate " +
            "ORDER BY a.date ASC")
    List<HomestayAvailability> findAndLockHomestayAvailability(@Param("homestayId") Long homestayId, @Param("status") String status,
                                                               @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate
    );
}