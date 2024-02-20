package com.stc.assessments.controller;

import com.stc.assessments.dto.ItemDTO;
import com.stc.assessments.service.ItemManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/stc-test/v1")
public class ItemManagementController {
 private final ItemManagementService itemManagementService;

    public ItemManagementController(ItemManagementService itemManagementService) {
        this.itemManagementService = itemManagementService;
    }


    @PostMapping(value = "/items/space")
    public ResponseEntity<Object> createItemAsSpace(@RequestBody @Valid ItemDTO itemDTO) throws IOException {
         itemManagementService.createItem(itemDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/items/folder")
    public ResponseEntity<Object> createItemAsFolder(@RequestBody @Valid ItemDTO itemDTO) throws IOException {
        itemManagementService.createItem(itemDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping(value = "/items/file")
    public ResponseEntity<Object> createItemAsFile(@RequestBody @Valid ItemDTO itemDTO, @RequestParam("file") MultipartFile file) throws IOException {
        itemManagementService.createItem(itemDTO,file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/items/file/file-metadata/{fileId}/{userEmail}")
    public ResponseEntity<Object> createItemAsFile(@PathVariable long fileId,@PathVariable String userEmail) {
        return new ResponseEntity<>(itemManagementService.getFileMetadata(fileId,userEmail),HttpStatus.OK);
    }

    @GetMapping(value = "/items/file/download/{fileId}/{userEmail}")
    public ResponseEntity<Object> downloadFile(@PathVariable long fileId,@PathVariable String userEmail) {
        itemManagementService.downloadFileById(fileId,userEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
