package com.stc.assessments.repository;

import com.stc.assessments.entites.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    Optional<Folder> findByName(String parentFolderName);
}
