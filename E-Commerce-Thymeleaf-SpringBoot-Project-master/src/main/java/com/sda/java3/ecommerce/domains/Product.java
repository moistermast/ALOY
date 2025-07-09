package com.sda.java3.ecommerce.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ec_product")
public class Product {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    protected UUID id;

    @Column(name = "name")
    protected String name;

    @Column(name = "description")
    protected String description;

    @Column(name = "price")
    protected BigDecimal price;

    @Column(name = "image")
    protected String image;

    @Column(name = "sale")
    protected boolean sale = false;

    @Column(name = "sale_price")
    protected BigDecimal salePrice;

    @Column(name = "views")
    protected int views = 0;

    @Column(name = "featured")
    protected boolean featured = false;

    @Column(name = "featured_image")
    protected String featureImage;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    protected Category category;

    @OneToMany(mappedBy = "product")
    protected List<Cart> items;
}
