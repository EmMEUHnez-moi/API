package com.willimath.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTripRepository extends JpaRepository<UserTripEntity, Integer> {
    @Query(value = "SELECT * FROM user_trip WHERE user_id = ?1", nativeQuery = true)
    Optional<List<UserTripEntity>> findByUserId(Integer userId);
}
