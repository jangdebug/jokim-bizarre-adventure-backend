package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.domain.Marketing;
import com.jokim.sivillage.api.customer.domain.Policy;
import com.jokim.sivillage.api.customer.vo.in.UpdateInfoRequestVo;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateInfoRequestDto {
    private String accessToken;
    private String phone;
    private String address;
    private Boolean smsAgreement;
    private Boolean emailAgreement;
    private Boolean dmAgreement;
    private Boolean callAgreement;
    private Boolean tomboyInfoUsageRight;

    public static UpdateInfoRequestDto toDto(String accessToekn, UpdateInfoRequestVo updateInfoRequestVo) {
        return UpdateInfoRequestDto.builder()
                .accessToken(accessToekn)
                .phone(updateInfoRequestVo.getPhone())
                .address(updateInfoRequestVo.getAddress())
                .smsAgreement(updateInfoRequestVo.getSmsAgreement())
                .emailAgreement(updateInfoRequestVo.getEmailAgreement())
                .dmAgreement(updateInfoRequestVo.getDmAgreement())
                .callAgreement(updateInfoRequestVo.getCallAgreement())
                .tomboyInfoUsageRight(updateInfoRequestVo.getTomboyInfoUsageRight())
                .build();
    }

    public Customer updateEntity(Customer customer) {
        return Customer.builder()
                .id(customer.getId())
                .uuid(customer.getUuid())                          // 기존 UUID 유지
                .email(customer.getEmail())                        // 기존 이메일 유지
                .password(customer.getPassword())                  // 기존 비밀번호 유지
                .name(customer.getName())                          // 기존 이름 유지
                .birth(customer.getBirth())                        // 기존 생일 유지
                .phone(phone)                        // 기존 전화번호 유지
                .address(address)                    // 기존 주소 유지
                .state(customer.getState())                        // 기존 상태 유지
                .build();                                          // 새 Customer 객체 반환
    }

    public Marketing updateEntity(Marketing marketing){
        return Marketing.builder()
                .id(marketing.getId())
                .uuid(marketing.getUuid())
                .callAgreement(callAgreement)
                .dmAgreement(dmAgreement)
                .emailAgreement(emailAgreement)
                .smsAgreement(smsAgreement)
                .build();
    }

    public Policy updateEntity(Policy policy){
        return Policy.builder()
                .id(policy.getId())
                .uuid(policy.getUuid())
                .webUsageRight(policy.getWebUsageRight())
                .tomboyInfoUsageRight(tomboyInfoUsageRight)
                .infoUsageRight(policy.getInfoUsageRight())
                .integratedMemberRight(policy.getIntegratedMemberRight())
                .build();
    }
}
