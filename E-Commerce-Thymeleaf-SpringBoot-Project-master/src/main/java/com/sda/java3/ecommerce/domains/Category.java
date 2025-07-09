package com.sda.java3.ecommerce.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ec_category")
public class Category {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    protected UUID id;

    @Column(name = "name")
    protected String name;

    @Column(name = "parent_id")
    @JdbcTypeCode(SqlTypes.CHAR)
    protected  UUID parentId;

    protected String description;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "category")
    protected List<Product> products;
}
