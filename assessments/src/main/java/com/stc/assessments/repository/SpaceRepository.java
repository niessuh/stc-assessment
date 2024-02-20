package com.stc.assessments.repository;

import com.stc.assessments.entites.Space;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpaceRepository  extends JpaRepository<Space, Long> {
    Optional<Space> findByName(String spaceName);
}
