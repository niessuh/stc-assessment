package com.stc.assessments.service;

import com.stc.assessments.dto.PermissionDTO;
import com.stc.assessments.entites.PermissionGroup;
import com.stc.assessments.entites.Permissions;
import com.stc.assessments.repository.PermissionGroupRepository;
import com.stc.assessments.repository.PermissionsRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService{
    private final PermissionsRepository permissionsRepository;
    private final PermissionGroupService permissionGroupService;

    public PermissionServiceImpl(PermissionsRepository permissionsRepository, PermissionGroupService permissionGroupService) {
        this.permissionsRepository = permissionsRepository;
        this.permissionGroupService = permissionGroupService;
    }

    @Override
    public void createPermission(PermissionDTO dto) {
        PermissionGroup group = permissionGroupService.findByGroupId(dto.getGroup().getId());
        Permissions permission = new Permissions();
        permission.setUserEmail(dto.getUserEmail());
        permission.setPermissionLevel(dto.getPermissionLevel());
        permission.setGroup(group);

        permissionsRepository.save(permission);
    }
}
