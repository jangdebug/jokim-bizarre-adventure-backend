package com.jokim.sivillage.api.customer.domain;

import com.jokim.sivillage.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Getter
@Entity
@ToString
@NoArgsConstructor
public class Customer extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("소셜 제공자(kakao/google)")
    @Column(length = 50)
    private String provider;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 36)
    private String customerUuid;

    @Comment("회원 이메일")
    @Column(length = 50)
    private String email;

    @Comment("회원 비밀번호")
    @Column(length = 64)
    private String password;

    @Comment("회원 이름")
    @Column(length = 50)
    private String name;

    @Comment("회원 생년월일")
    @Column(length = 20)
    private Date birth;

    @Comment("회원 전화번호")
    @Column(length = 20)
    private String phone;

    @Comment("회원상태")
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private State state;

    @Builder
    public Customer(
        Long id,
        String customerUuid,
        String email,
        String password,
        String name,
        Date birth,
        String phone,
        State state,
        String provider

    ) {
        this.id = id;
        this.customerUuid = customerUuid;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.state = state;
        this.provider = provider;
    }


    public void hashPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
