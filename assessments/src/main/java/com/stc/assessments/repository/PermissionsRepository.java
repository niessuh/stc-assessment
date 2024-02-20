package com.stc.assessments.repository;

import com.stc.assessments.entites.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

    @Query("SELECT p FROM Permissions p " +
            "JOIN p.group g " +
            "WHERE p.userEmail = :userEmail " +
            "AND g.groupName = :groupName " +
            "AND p.permissionLevel = :permissionLevel")
    Optional<Permissions> findPermissionsByUserEmailAndGroupNameAndPermissionLevel(
            @Param("userEmail") String userEmail,
            @Param("groupName") String groupName,
            @Param("permissionLevel") String permissionLevel);
}
