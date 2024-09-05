package com.jokim.sivillage.api.customer.application;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.dto.CustomerSignUpDto;
import com.jokim.sivillage.api.customer.infrastructure.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void signUp(CustomerSignUpDto customerSignUpDto) {
        Customer customer = customerSignUpDto.toEntity();

        customerRepository.save(customer);
    }

    @Override
    public CustomerSignUpDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        return CustomerSignUpDto.builder()
                .id(customer.getId())
                .uuid(customer.getUuid())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .name(customer.getName())
                .birth(customer.getBirth())
                .phone(customer.getPhone())
                .build();
    }

    @Override
    public CustomerSignUpDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("해당 이메일을 가진 회원이 없습니다.")
        );

        if (customer != null) {
            return CustomerSignUpDto.builder()
                    .id(customer.getId())
                    .uuid(customer.getUuid())
                    .email(customer.getEmail())
                    .password(customer.getPassword())
                    .name(customer.getName())
                    .birth(customer.getBirth())
                    .phone(customer.getPhone())
                    .build();
        }

        return null;
    }
}