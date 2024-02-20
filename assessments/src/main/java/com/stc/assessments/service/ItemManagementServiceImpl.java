package com.stc.assessments.service;

import com.stc.assessments.constant.AppConstants;
import com.stc.assessments.constant.ItemTypeEnum;
import com.stc.assessments.constant.PermissionLevelEnum;
import com.stc.assessments.dto.ItemDTO;
import com.stc.assessments.entites.*;
import com.stc.assessments.model.FileMetadata;
import com.stc.assessments.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ItemManagementServiceImpl implements ItemManagementService{
    private final PermissionGroupService permissionGroupService;
    private final ItemRepository itemRepository;
    private final DirectoriesCrudService directoriesCrudService;
    private final PermissionsRepository permissionsRepository;
    private final QueryResolver queryResolver;

    public ItemManagementServiceImpl(PermissionGroupService permissionGroupService, ItemRepository itemRepository, DirectoriesCrudService directoriesCrudService, PermissionsRepository permissionsRepository, QueryResolver queryResolver) {
        this.permissionGroupService = permissionGroupService;
        this.itemRepository = itemRepository;
        this.directoriesCrudService = directoriesCrudService;
        this.permissionsRepository = permissionsRepository;
        this.queryResolver = queryResolver;
    }

    @Override
    public void createItem(ItemDTO dto) throws IOException {
     PermissionGroup group=permissionGroupService.findGroupByName(dto.getUserPermission().getUserGroup().name());
     if(hasGrantEditAccess(dto,group)) {
         Item item=createNewItem(dto, group);
         directoriesCrudService.saveDirectory(dto, item, null);
     }else {
         throw new IllegalArgumentException(AppConstants.NO_AUTHORITY);
     }

    }

    @Override
    public void createItem(ItemDTO dto, MultipartFile file) throws IOException {
        PermissionGroup group=permissionGroupService.findGroupByName(dto.getUserPermission().getUserGroup().name());
        if(hasGrantEditAccess(dto,group)) {
            Item item=createNewItem(dto, group);
            directoriesCrudService.saveDirectory(dto,item,file);
        }else {
            throw new IllegalArgumentException(AppConstants.NO_AUTHORITY);
        }
    }

    private boolean hasGrantEditAccess(ItemDTO dto, PermissionGroup group) {
        if(dto.getType().equals(ItemTypeEnum.SPACE))
            return true;

        Optional<Permissions> permissions=permissionsRepository.findPermissionsByUserEmailAndGroupNameAndPermissionLevel(dto.getUserPermission().getUserEmail(),group.getGroupName()
                                                                                                                               , PermissionLevelEnum.EDIT.name());
        return permissions.isPresent();
    }

    private Item createNewItem(ItemDTO dto, PermissionGroup group) {
        Item item=new Item();
        item.setType(dto.getType().name());
        item.setName(dto.getItemName());
        item.setPermissionGroup(group);
        return itemRepository.save(item);
    }

    @Override
    public FileMetadata getFileMetadata(long fileId, String userEmail) {
        return queryResolver.getFileMetadata(fileId,isHasAccessInThisFile(fileId,userEmail));
    }

    @Override
    public void downloadFileById(long fileId, String userEmail) {
        directoriesCrudService.downloadFileById(fileId,isHasAccessInThisFile(fileId,userEmail));
    }

    private boolean isHasAccessInThisFile(long fileId, String userEmail) {
        File file= directoriesCrudService.findFileById(fileId);
        return hasAccessInThisFile(file.getItem().getPermissionGroup().getGroupName(),userEmail);
    }

    private boolean hasAccessInThisFile(String groupName, String userEmail) {
        Optional<Permissions> permissions=permissionsRepository.findPermissionsByUserEmailAndGroupNameAndPermissionLevel(userEmail,groupName
                , PermissionLevelEnum.VIEW.name());
        return permissions.isPresent();
    }
}
