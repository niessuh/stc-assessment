package com.stc.assessments.service;

import com.stc.assessments.entites.PermissionGroup;

public interface PermissionGroupService {
    PermissionGroup findGroupByName(String name);
    PermissionGroup findByGroupId(long id);
}
