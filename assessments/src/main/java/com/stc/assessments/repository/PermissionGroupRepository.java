package com.stc.assessments.repository;

import com.stc.assessments.entites.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "permissionGroups", path = "permissionGroups")
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
    Optional<PermissionGroup> findPermissionGroupByGroupName(String groupName);
}
