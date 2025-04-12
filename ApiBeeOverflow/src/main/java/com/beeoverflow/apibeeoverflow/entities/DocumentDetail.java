package com.beeoverflow.apibeeoverflow.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "document_detail")
public class DocumentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "name_doc")
    private String nameDoc;

    @Lob
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "doc_src")
    private String docSrc;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @ColumnDefault("0")
    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "docDetail")
    private Set<Document> documents = new LinkedHashSet<>();

}