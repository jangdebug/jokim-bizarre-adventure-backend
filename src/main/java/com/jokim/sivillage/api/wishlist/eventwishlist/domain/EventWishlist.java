package com.jokim.sivillage.api.wishlist.eventwishlist.domain;

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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"uuid", "eventCode"})})
@Entity
public class EventWishlist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false, length = 36)
    private String eventCode;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isChecked;

}
