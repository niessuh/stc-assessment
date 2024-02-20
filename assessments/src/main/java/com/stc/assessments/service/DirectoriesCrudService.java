package com.stc.assessments.service;

import com.stc.assessments.dto.ItemDTO;
import com.stc.assessments.entites.File;
import com.stc.assessments.entites.Item;
import com.stc.assessments.model.FileMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DirectoriesCrudService {
    void saveDirectory(ItemDTO dto, Item item, MultipartFile file) throws IOException;
    FileMetadata getFileMetadata(long fileId, boolean isHasAccessInThisFile);
    File findFileById(long fileId);
    void downloadFileById(long fileId, boolean isHasAccessInThisFile);
}
