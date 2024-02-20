package com.stc.assessments.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileMetadata {
    private Long id;
    private String name;
    private FolderMetadata folder;
}
