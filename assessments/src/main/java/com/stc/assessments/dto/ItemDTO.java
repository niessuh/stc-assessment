package com.stc.assessments.dto;

import com.stc.assessments.constant.ItemTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ItemDTO {
    @NotBlank
     private ItemTypeEnum type;
    @NotBlank
    private String itemName;
    @NotNull
    private UserPermission userPermission;
    private String parentSpaceName;
    private String parentFolderName;

}