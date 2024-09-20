package com.jokim.sivillage.api.customer.domain;

import com.jokim.sivillage.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;


@Getter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("회원 UUID")
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

    @Comment("회원상태")
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private State state;

    @Comment("회원주소")
    @Column(length = 200)
    private String address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.uuid;
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
