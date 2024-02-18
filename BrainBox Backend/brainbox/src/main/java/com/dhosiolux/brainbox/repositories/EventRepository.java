package com.dhosiolux.brainbox.repositories;

import com.dhosiolux.brainbox.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM events_table e WHERE e.createdAt > :date")
    List<Event> findEventByCreatedAtDateAfter(@Param("date") LocalDate date);

}
