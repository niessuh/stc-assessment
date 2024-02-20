package com.stc.assessments.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private byte[] binaryFile;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
