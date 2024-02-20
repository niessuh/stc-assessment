package com.stc.assessments.dto;

import com.stc.assessments.entites.PermissionGroup;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Setter
@Getter
public class PermissionDTO {
    @NotBlank
    private String userEmail;
    @NotBlank
    private String permissionLevel;
    @NotNull
    private PermissionGroup group;
}
