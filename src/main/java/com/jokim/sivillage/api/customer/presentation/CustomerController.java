package com.jokim.sivillage.api.customer.presentation;


import com.jokim.sivillage.api.common.entity.CommonResponseEntity;
import com.jokim.sivillage.api.customer.application.CustomerService;
import com.jokim.sivillage.api.customer.dto.CustomerSignUpDto;
import com.jokim.sivillage.api.customer.dto.SignInRequestDto;
import com.jokim.sivillage.api.customer.vo.CustomerSignUpRequestVo;
import com.jokim.sivillage.api.customer.vo.SignInRequestVo;
import com.jokim.sivillage.api.customer.vo.SignInResponseVo;
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

    @Operation(summary = "SignIn API", description = "SignIn API 입니다.", tags = {"Auth"})
    @PostMapping("/sign-in/simple")
    public CommonResponseEntity<SignInResponseVo> signIn(
            @RequestBody SignInRequestVo signInRequestVo) {
        ModelMapper modelMapper = new ModelMapper();
        SignInRequestDto signInRequestDto = SignInRequestDto.builder().
                email(signInRequestVo.getEmail()).
                password(signInRequestVo.getPassword()).
                build();
        SignInResponseVo signInResponseVo = modelMapper.map(customerService.signIn(signInRequestDto), SignInResponseVo.class);
        log.info("signInResponseVo : {}", signInResponseVo);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "간편 로그인에 성공하였습니다.",
                signInResponseVo);

    }

}
