package com.jokim.sivillage.customer.presentation;


import com.jokim.sivillage.common.entity.CommonResponseEntity;
import com.jokim.sivillage.common.entity.CommonResponseMessage;
import com.jokim.sivillage.customer.application.CustomerService;
import com.jokim.sivillage.customer.dto.CustomerSignUpDto;
import com.jokim.sivillage.customer.vo.CustomerSignUpRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "SignUp API", description = "SignUp API 입니다.", tags = {"Auth"})
    @PostMapping("auth/sign-up/simple")
    public CommonResponseEntity<Void> signUp(
            @RequestBody CustomerSignUpRequestVo customerSignUpRequestVo) {
        log.info("signUpRequestVo : {}", customerSignUpRequestVo);
        customerService.signUp(new ModelMapper().map(customerSignUpRequestVo, CustomerSignUpDto.class));
        return new CommonResponseEntity<>(HttpStatus.OK, "간편 회원가입에 성공하였습니다", null);
    }

}
