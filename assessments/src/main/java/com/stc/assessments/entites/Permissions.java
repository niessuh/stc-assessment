package com.stc.assessments.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userEmail;

    private String permissionLevel;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private PermissionGroup group;
}
