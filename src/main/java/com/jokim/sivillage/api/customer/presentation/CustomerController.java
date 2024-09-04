package com.jokim.sivillage.api.customer.presentation;


import com.jokim.sivillage.api.common.entity.CommonResponseEntity;
import com.jokim.sivillage.api.common.jwt.JwtTokenProvider;
import com.jokim.sivillage.api.customer.application.CustomerService;
import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.vo.in.*;
import com.jokim.sivillage.api.customer.vo.out.OauthCustomerSignInResponseVo;
import com.jokim.sivillage.api.customer.vo.out.OauthCustomerSignUpResponseVo;
import com.jokim.sivillage.api.customer.vo.out.SignInResponseVo;
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
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "SignUp API", description = "SignUp API 입니다.", tags = {"Auth"})
    @PostMapping("auth/sign-up/simple")
    public CommonResponseEntity<Void> signUp(
        @RequestBody CustomerSignUpRequestVo customerSignUpRequestVo) {
        log.info("signUpRequestVo : {}", customerSignUpRequestVo);
        customerService.signUp(
            new ModelMapper().map(customerSignUpRequestVo, CustomerSignUpDto.class));
        return new CommonResponseEntity<>(HttpStatus.OK, "간편 회원가입에 성공하였습니다", null);
    }

    @Operation(summary = "OAuth SignUp API", description = "OAuth SignUp API 입니다.", tags = {"Auth"})
    @PostMapping("auth/sign-up/oauth")
    public CommonResponseEntity<OauthCustomerSignUpResponseVo> oauthSignUp(
        @RequestBody OauthCustomerSignUpRequestVo oauthCustomerSignUpRequestVo) {

        ModelMapper modelMapper = new ModelMapper();
        OauthCustomerSignUpDto oauthCustomerSignUpDto = OauthCustomerSignUpDto.builder()
            .email(oauthCustomerSignUpRequestVo.getEmail())
            .accessToken(oauthCustomerSignUpRequestVo.getAccessToken())
            .name(oauthCustomerSignUpRequestVo.getName())
            .provider(oauthCustomerSignUpRequestVo.getProvider())
            .birth((oauthCustomerSignUpRequestVo.getBirth()))
            .phone(oauthCustomerSignUpRequestVo.getPhone())
            .build();

        OauthCustomerSignUpResponseVo oauthCustomerSignUpResponseVo =
            modelMapper.map(customerService.oauthSignUp(oauthCustomerSignUpDto),
                OauthCustomerSignUpResponseVo.class);

        return new CommonResponseEntity<>(
            HttpStatus.OK,
            "소셜 회원가입에 성공하였습니다.",
            oauthCustomerSignUpResponseVo);

    }

    @Operation(summary = "OAuth SignUp Additional Info API", description = "OAuth SignUp 후 추가 정보를 입력하는 API입니다.", tags = {
        "Auth"})
    @PostMapping("auth/sign-up/oauth/policy")
    public CommonResponseEntity<OauthCustomerSignInResponseVo> oauthSignUpAdditionalInfo(
        @RequestBody OauthCustomerSignUpRequestPolicyVo oauthCustomerSignUpRequestPolicyVo) {

        ModelMapper modelMapper = new ModelMapper();
        OauthCustomerSignUpPolicyDto oauthCustomerSignUpPolicyDto = OauthCustomerSignUpPolicyDto.builder()
            .accessToken(oauthCustomerSignUpRequestPolicyVo.getAccessToken())
            .uuid(oauthCustomerSignUpRequestPolicyVo.getUuid())
            .marketingSms(oauthCustomerSignUpRequestPolicyVo.getMarketingSms())
            .marketingEmail(oauthCustomerSignUpRequestPolicyVo.getMarketingEmail())
            .marketingDm(oauthCustomerSignUpRequestPolicyVo.getMarketingDm())
            .marketingCall(oauthCustomerSignUpRequestPolicyVo.getMarketingCall())
            .essential1(oauthCustomerSignUpRequestPolicyVo.getPolicyEssential1())
            .essential2(oauthCustomerSignUpRequestPolicyVo.getPolicyEssential2())
            .essential3(oauthCustomerSignUpRequestPolicyVo.getPolicyEssential3())
            .optional(oauthCustomerSignUpRequestPolicyVo.getPolicyOptional())
            .zipCode(oauthCustomerSignUpRequestPolicyVo.getZipCode())
            .address(oauthCustomerSignUpRequestPolicyVo.getAddress())
            .addressDetail(oauthCustomerSignUpRequestPolicyVo.getAddressDetail())
            .build();

        OauthCustomerSignInResponseVo oauthCustomerSignInResponseVo =
            modelMapper.map(customerService.oauthpolicySignUp(oauthCustomerSignUpPolicyDto),
                OauthCustomerSignInResponseVo.class);

        return new CommonResponseEntity<>
            (HttpStatus.OK, "추가 정보 저장에 성공하였습니다", oauthCustomerSignInResponseVo);
    }


    @Operation(summary = "SignIn API", description = "SignIn API 입니다.", tags = {"Auth"})
    @PostMapping("auth/sign-in/simple")
    public CommonResponseEntity<SignInResponseVo> signIn(
        @RequestBody SignInRequestVo signInRequestVo) {
        ModelMapper modelMapper = new ModelMapper();
        SignInRequestDto signInRequestDto = SignInRequestDto.builder()
            .email(signInRequestVo.getEmail())
            .password(signInRequestVo.getPassword())
            .build();
        SignInResponseVo signInResponseVo = modelMapper.map(
            customerService.signIn(signInRequestDto), SignInResponseVo.class);
        log.info("signInResponseVo : {}", signInResponseVo);
        return new CommonResponseEntity<>(
            HttpStatus.OK,
            "간편 로그인에 성공하였습니다.",
            signInResponseVo);

    }

    @Operation(summary = "OAuth SignIn API", description = "OAuth SignIn API 입니다.", tags = {"Auth"})
    @PostMapping("auth/sign-in/oauth")
    public CommonResponseEntity<SignInResponseVo> oauthSignIn(
        @RequestBody OauthCustomerSignInRequestVo oauthCustomerSignUpRequestVo) {
        ModelMapper modelMapper = new ModelMapper();
        OauthCustomerSignInRequestDto oauthCustomerSignInRequestDto = OauthCustomerSignInRequestDto.builder()
            .email(oauthCustomerSignUpRequestVo.getEmail())
            .provider(oauthCustomerSignUpRequestVo.getProvider())
            .build();
        SignInResponseVo signInResponseVo = modelMapper.map(
            customerService.oauthSignIn(oauthCustomerSignInRequestDto), SignInResponseVo.class);
        return new CommonResponseEntity<>(
            HttpStatus.OK,
            "소셜 로그인에 성공하였습니다.",
            signInResponseVo);
    }

    @Operation(summary = "Logout API", description = "로그아웃 API 입니다.", tags = {"Auth"})
    @PostMapping("/auth/logout")
    public CommonResponseEntity<Void> logout(
        @RequestHeader("Authorization") String authorizationHeader) {
        // Authorization 헤더에서 accessToken 추출
        String accessToken = authorizationHeader.replace("Bearer ", "");
        log.info("logoutRequest accessToken : {}", accessToken);

        customerService.logout(accessToken);

        return new CommonResponseEntity<>(HttpStatus.OK, "로그아웃에 성공하였습니다", null);

    }


}
