package com.jokim.sivillage.api.customer.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String oauthProviderId;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false,length = 50)
    private String name;

}
