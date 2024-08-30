package com.jokim.sivillage.customer.application;


import com.jokim.sivillage.customer.domain.*;
import com.jokim.sivillage.customer.dto.CustomerSignUpDto;
import com.jokim.sivillage.customer.infrastructure.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMarketingRepository customerMarketingRepository;
    private final CustomerPolicyRepository customerPolicyRepository;
    private final CustomerAdressRepository customerAdressRepository;
    private final CustomerDefaultAddresRepository customerDefaultAddresRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void signUp(CustomerSignUpDto customerSignUpDto){

        //UUID생성, 상태 저장
        String customerUuid = UUID.randomUUID().toString();
        State state = State.ACTIVATION;

        Customer customer = customerRepository.findByEmail(customerSignUpDto.getEmail()).orElse(null);

        //이메일 중복체크
        if(customer != null){
            throw new IllegalArgumentException("이미 가입된 회원입니다");
        }

        //회원가입 시 address 입력 받았는지 확인

        Customer newCustomer = customerSignUpDto.toEnity(customerUuid, state, passwordEncoder);
        Customer savedCustomer = customerRepository.save(newCustomer);

        Marketing marketing = customerSignUpDto.toMarketingEntity(savedCustomer);
        customerMarketingRepository.save(marketing);

        Policy policy = customerSignUpDto.toPolicyEntity(savedCustomer);
        customerPolicyRepository.save(policy);


        //회원가입시에 zip_code가 null이면 defaultAddress와 address 저장 X
        Address savedAddress = null;
        if (customerSignUpDto.getZipCode() != null && !customerSignUpDto.getZipCode().isEmpty()) {
            Address address = customerSignUpDto.toAdressEntity();
            savedAddress = customerAdressRepository.save(address);
            DefaultAddress defaultAddress = customerSignUpDto.toDefaultAddressEntity(savedCustomer, savedAddress);
            customerDefaultAddresRepository.save(defaultAddress);
        }



    }
}
