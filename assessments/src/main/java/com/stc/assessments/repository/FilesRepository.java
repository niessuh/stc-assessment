package com.stc.assessments.repository;

import com.stc.assessments.entites.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FilesRepository extends JpaRepository<Files, Long> {
    @Query("SELECT f FROM Files f " +
            "JOIN f.item i " +
            "WHERE f.item.id = :itemId")
    Optional<Files> findByItemId(long itemId);
}
