package com.stc.assessments.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FolderMetadata {
    private Long id;
    private String name;
    private SpaceMetadata space;
}
