package com.willimath.api.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, Integer> {

    @Query(value = "SELECT * FROM trip WHERE from_location= ?1 AND to_location = ?2 AND start_date = ?3", nativeQuery = true)
    Optional<List<TripEntity>> findByLocationAndStart_date(String villedepart, String villearrivee, LocalDate date);
}
