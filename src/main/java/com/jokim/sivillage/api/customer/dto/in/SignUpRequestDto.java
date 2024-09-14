package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.domain.Marketing;
import com.jokim.sivillage.api.customer.domain.Policy;
import com.jokim.sivillage.api.customer.domain.State;
import com.jokim.sivillage.api.customer.vo.in.SignUpRequestVo;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

    private Long id;
    private String uuid;
    private String email;
    private String password;
    private String name;
    private Date birth;
    private String phone;
    private String address;
    private State state;

    private Long marketingId;
    private Boolean smsAgreement;
    private Boolean emailAgreement;
    private Boolean dmAgreement;
    private Boolean callAgreement;

    private Long policyId;
    private Boolean webUsageRight;
    private Boolean integratedMemberRight;
    private Boolean infoUsageRight;
    private Boolean tomboyInfoUsageRight;

    public static SignUpRequestDto toDto(SignUpRequestVo signUpRequestVo) {
        return SignUpRequestDto.builder()
            .email(signUpRequestVo.getEmail())
            .password(signUpRequestVo.getPassword())
            .name(signUpRequestVo.getName())
            .birth(signUpRequestVo.getBirth())
            .phone(signUpRequestVo.getPhone())
            .address(signUpRequestVo.getAddress())
            .smsAgreement(signUpRequestVo.getSmsAgreement())
            .emailAgreement(signUpRequestVo.getEmailAgreement())
            .dmAgreement(signUpRequestVo.getDmAgreement())
            .callAgreement(signUpRequestVo.getCallAgreement())
            .webUsageRight(signUpRequestVo.getWebUsageRight())
            .integratedMemberRight(signUpRequestVo.getIntegratedMemberRight())
            .infoUsageRight(signUpRequestVo.getInfoUsageRight())
            .tomboyInfoUsageRight(signUpRequestVo.getTomboyInfoUsageRight())
            .build();

    }

    public Customer toCustomerEntity(PasswordEncoder passwordEncoder, String uuid, State state){
        return Customer.builder()
            .email(email)
            .password(passwordEncoder.encode(password))
            .uuid(uuid)
            .name(name)
            .birth(birth)
            .phone(phone)
            .address(address)
            .state(state)
            .build();
    }

    public Marketing toMarketingEntity(String uuid){
        return Marketing.builder()
            .smsAgreement(smsAgreement)
            .uuid(uuid)
            .emailAgreement(emailAgreement)
            .dmAgreement(dmAgreement)
            .callAgreement(callAgreement)
            .build();
    }

    public Policy toPolicyEntity(String uuid){
        return Policy.builder()
            .webUsageRight(webUsageRight)
            .uuid(uuid)
            .integratedMemberRight(integratedMemberRight)
            .infoUsageRight(infoUsageRight)
            .tomboyInfoUsageRight(tomboyInfoUsageRight)
            .build();
    }

}
