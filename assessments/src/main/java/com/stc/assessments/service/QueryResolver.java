package com.stc.assessments.service;

import com.stc.assessments.model.FileMetadata;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Service;

@Service
public class QueryResolver implements GraphQLQueryResolver {
    private final DirectoriesCrudService directoriesCrudService;

    public QueryResolver(DirectoriesCrudService directoriesCrudService) {
        this.directoriesCrudService = directoriesCrudService;
    }

    public FileMetadata getFileMetadata(Long fileId,boolean hasAccess) {
        return directoriesCrudService.getFileMetadata(fileId,hasAccess);
    }
}
