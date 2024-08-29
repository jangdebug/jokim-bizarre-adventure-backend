package com.jokim.sivillage.api.customer.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("UUID")
    @Column(nullable = false, length = 36)
    private String uuid;

    @Comment("회원 이메일")
    @Column(nullable = false, length = 50)
    private String email;

    @Comment("회원 비밀번호")
    @Column(nullable = false, length = 64)
    private String password;

    @Comment("회원 이름")
    @Column(nullable = false, length = 50)
    private String name;

    @Comment("회원 생년월일")
    private Date birth;

    @Comment("회원 전화번호")
    @Column(nullable = false, length = 20)
    private String phone;

    @Builder
    public Customer(
            Long id,
            String uuid,
            String email,
            String password,
            String name,
            Date birth,
            String phone
    ) {
        this.id = id;
        this.uuid = uuid;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
    }
}
