package com.beeoverflow.apibeeoverflow.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doc_detail")
public class DocDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name_doc", nullable = false)
    private String nameDoc;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "doc_src")
    private String docSrc;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @ColumnDefault("0")
    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "docDetail")
    private Set<Document> documents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "doc")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

}