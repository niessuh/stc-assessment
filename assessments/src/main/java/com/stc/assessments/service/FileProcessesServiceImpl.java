package com.stc.assessments.service;

import com.stc.assessments.entites.Files;
import com.stc.assessments.entites.Item;
import com.stc.assessments.repository.FilesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileProcessesServiceImpl implements FileProcessesService {
    private final FilesRepository filesRepository;

    public FileProcessesServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public void uploadFileAsBytes(Item item, MultipartFile file) throws IOException {
        Files newFile=Files.builder()
                .binaryFile(getFileBytes(file))
                .item(item)
                .build();
        filesRepository.save(newFile);
    }

    @Override
    public Files findFileBinaryById(long itemId) {
        return filesRepository.findByItemId(itemId).orElse(null);
    }

    private byte[] getFileBytes(MultipartFile file) throws IOException {
        byte[] bytes = null;
        if (!file.isEmpty()) {
             bytes = file.getBytes();
        }
        return bytes;
    }


}
