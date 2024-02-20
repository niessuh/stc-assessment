package com.stc.assessments.dto;

import com.stc.assessments.constant.PermissionGroupsEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserPermission {
    @NotBlank
    private String userEmail;
    private PermissionGroupsEnum userGroup;
}