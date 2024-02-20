package com.stc.assessments.service;

import com.stc.assessments.constant.AppConstants;
import com.stc.assessments.dto.ItemDTO;
import com.stc.assessments.entites.*;
import com.stc.assessments.model.FileMetadata;
import com.stc.assessments.model.FolderMetadata;
import com.stc.assessments.model.SpaceMetadata;
import com.stc.assessments.repository.FileRepository;
import com.stc.assessments.repository.FolderRepository;
import com.stc.assessments.repository.SpaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@Service
public class DirectoriesCrudServiceImpl implements DirectoriesCrudService {
    private final SpaceRepository spaceRepository;
    private final FolderRepository folderRepository;
    private final FileRepository fileRepository;
    private final FileProcessesService fileProcessesService;

    public DirectoriesCrudServiceImpl(SpaceRepository spaceRepository, FolderRepository folderRepository, FileRepository fileRepository, FileProcessesService fileProcessesService) {
        this.spaceRepository = spaceRepository;
        this.folderRepository = folderRepository;
        this.fileRepository = fileRepository;
        this.fileProcessesService = fileProcessesService;
    }


    @Override
    public void saveDirectory(ItemDTO dto, Item item, MultipartFile file) throws IOException {
        switch (dto.getType()){
            case SPACE :
                saveItemAsSpace(dto,item);
                break;
            case FOLDER:
                saveItemAsFolder(dto,item);
                break;
            default:
                saveItemAsFile(dto,item,file);
                break;

        }
    }

    private void saveItemAsSpace(ItemDTO dto, Item item) {
        Space space=new Space();
        space.setName(dto.getItemName());
        space.setItem(item);
        spaceRepository.save(space);
    }

    private void saveItemAsFolder(ItemDTO dto,Item item) {
        Folder folder=new Folder();
        Space space=spaceRepository.findByName(dto.getParentSpaceName())
                .orElseThrow((() -> new IllegalArgumentException("This Parent Space with name " + dto.getParentSpaceName() + " not found")));
        folder.setName(dto.getItemName());
        folder.setSpace(space);
        folder.setItem(item);
        folderRepository.save(folder);
    }

    private void saveItemAsFile(ItemDTO dto, Item item, MultipartFile file) throws IOException {
        File fileDirectory=new File();
        Folder folder=folderRepository.findByName(dto.getParentFolderName())
                .orElseThrow((() -> new IllegalArgumentException("This Parent Folder with name " + dto.getParentFolderName() + " not found")));
        fileDirectory.setName(dto.getItemName());
        fileDirectory.setFolder(folder);
        fileDirectory.setItem(item);
        fileRepository.save(fileDirectory);
        fileProcessesService.uploadFileAsBytes(item,file);
    }

    @Override
    public File findFileById(long fileId) {
        Optional<File> optionalFile = fileRepository.findById(fileId);
        return optionalFile.orElse(null);

    }
    @Override
    public FileMetadata getFileMetadata(long fileId, boolean isHasAccessInThisFile) {
        if(!isHasAccessInThisFile)
            throw new IllegalArgumentException(AppConstants.NO_AUTHORITY);

        File file = findFileById(fileId);
        if (file != null) {
            return convertToFileMetadata(file);
        }
        return null;
    }

    private FileMetadata convertToFileMetadata(File file) {
        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setId(file.getId());
        fileMetadata.setName(file.getName());
        fileMetadata.setFolder(convertToFolderMetadata(file.getFolder()));
        return fileMetadata;
    }

    private FolderMetadata convertToFolderMetadata(Folder folder) {
        FolderMetadata folderMetadata = new FolderMetadata();
        folderMetadata.setId(folder.getId());
        folderMetadata.setName(folder.getName());
        folderMetadata.setSpace(convertToSpaceMetadata(folder.getSpace()));
        return folderMetadata;
    }

    private SpaceMetadata convertToSpaceMetadata(Space space) {
        SpaceMetadata spaceMetadata = new SpaceMetadata();
        spaceMetadata.setId(space.getId());
        spaceMetadata.setName(space.getName());
        return spaceMetadata;
    }
    @Override
    public void downloadFileById(long fileId, boolean isHasAccessInThisFile){
        if(!isHasAccessInThisFile)
            throw new IllegalArgumentException(AppConstants.NO_AUTHORITY);

        File file=findFileById(fileId);
        Files files=fileProcessesService.findFileBinaryById(file.getItem().getId());
        if (files != null){
            try (OutputStream outputStream = new FileOutputStream("downloadPath/downloaded_file.txt")) {
                outputStream.write(files.getBinaryFile());
                System.out.println("File downloaded successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found!");
        }
    }
}
