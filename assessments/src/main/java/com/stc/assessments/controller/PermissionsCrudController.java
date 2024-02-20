package com.stc.assessments.controller;

import com.stc.assessments.dto.PermissionDTO;
import com.stc.assessments.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/stc-test/v1")
public class PermissionsCrudController {
    private final PermissionService permissionService;

    public PermissionsCrudController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping(value = "/permission-crud/permission")
    public ResponseEntity<Object> addPermission(@RequestBody @Valid PermissionDTO permissionDTO) {
        permissionService.createPermission(permissionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
