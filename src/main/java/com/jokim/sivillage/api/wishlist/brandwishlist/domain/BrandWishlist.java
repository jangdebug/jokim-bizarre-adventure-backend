package com.jokim.sivillage.api.wishlist.brandwishlist.domain;

import com.jokim.sivillage.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"uuid", "brandCode"})})
@Entity
public class BrandWishlist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false, length = 36)
    private String brandCode;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isChecked;

}
