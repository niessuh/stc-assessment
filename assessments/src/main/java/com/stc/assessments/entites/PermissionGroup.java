package com.stc.assessments.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Setter
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "group_name")})
public class PermissionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @OneToMany(mappedBy = "permissionGroup")
    private List<Item> items;

}
