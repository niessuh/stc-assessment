package com.stc.assessments.service;

import com.stc.assessments.entites.Files;
import com.stc.assessments.entites.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileProcessesService {
    void uploadFileAsBytes(Item item, MultipartFile file) throws IOException;
    Files findFileBinaryById(long itemId);
}
