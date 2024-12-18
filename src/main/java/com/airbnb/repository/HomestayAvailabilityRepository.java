package com.airbnb.repository;

import com.airbnb.entity.HomestayAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayAvailabilityRepository extends JpaRepository<HomestayAvailability, Long> {
}