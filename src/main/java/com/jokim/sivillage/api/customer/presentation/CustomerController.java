package com.jokim.sivillage.api.customer.presentation;

import com.jokim.sivillage.api.customer.application.CustomerService;
import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.dto.CustomerSignUpDto;
import com.jokim.sivillage.api.customer.vo.CustomerResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomerResponseVo> getCustomerById(@PathVariable Long id) {
        CustomerSignUpDto customerSignUpDto = customerService.getCustomerById(id);
        CustomerResponseVo customerResponseVo = customerSignUpDto.toResponseVo();

        return ResponseEntity.ok(customerResponseVo);

    }

}
