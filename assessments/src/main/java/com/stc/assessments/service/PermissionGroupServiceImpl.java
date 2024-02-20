package com.stc.assessments.service;

import com.stc.assessments.entites.PermissionGroup;
import com.stc.assessments.repository.PermissionGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionGroupServiceImpl implements PermissionGroupService{
    private final PermissionGroupRepository permissionGroupRepository;

    public PermissionGroupServiceImpl(PermissionGroupRepository permissionGroupRepository) {
        this.permissionGroupRepository = permissionGroupRepository;
    }

    @Override
    public PermissionGroup findGroupByName(String name) {
       return permissionGroupRepository.findPermissionGroupByGroupName(name)
                .orElseThrow(() -> new IllegalArgumentException("Permission group with name " + name + " not found"));
    }

    @Override
    public PermissionGroup findByGroupId(long id) {
        return permissionGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permission group with id " + id + " not found"));
    }
}
