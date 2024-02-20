package com.stc.assessments.service;

import com.stc.assessments.dto.ItemDTO;
import com.stc.assessments.model.FileMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ItemManagementService {
    void createItem(ItemDTO dto) throws IOException;
    void createItem(ItemDTO dto, MultipartFile file) throws IOException;
    FileMetadata getFileMetadata(long fileId, String userEmail);
    void downloadFileById(long fileId, String userEmail);
}
