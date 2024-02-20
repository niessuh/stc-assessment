package com.stc.assessments.repository;

import com.stc.assessments.entites.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long>{
}
