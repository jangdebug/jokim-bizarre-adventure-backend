package com.jokim.sivillage.api.customer.presentation;


import com.jokim.sivillage.api.common.entity.CommonResponseEntity;
import com.jokim.sivillage.api.common.jwt.JwtTokenProvider;
import com.jokim.sivillage.api.customer.application.CustomerService;
import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.vo.in.*;
import com.jokim.sivillage.api.customer.vo.out.OauthSignInResponseVo;
import com.jokim.sivillage.api.customer.vo.out.OauthSignUpResponseVo;
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
        @RequestBody SignUpRequestVo signUpRequestVo) {
        log.info("signUpRequestVo : {}", signUpRequestVo);
        customerService.signUp(
            new ModelMapper().map(signUpRequestVo, SignUpDto.class));
        return new CommonResponseEntity<>(HttpStatus.OK, "간편 회원가입에 성공하였습니다", null);
    }

    @Operation(summary = "OAuth SignUp API", description = "OAuth SignUp API 입니다.", tags = {"Auth"})
    @PostMapping("auth/sign-up/oauth")
    public CommonResponseEntity<OauthSignUpResponseVo> oauthSignUp(
        @RequestBody OauthSignUpRequestVo oauthSignUpRequestVo) {

        ModelMapper modelMapper = new ModelMapper();
        OauthSignUpDto oauthSignUpDto = OauthSignUpDto.builder()
            .email(oauthSignUpRequestVo.getEmail())
            .accessToken(oauthSignUpRequestVo.getAccessToken())
            .name(oauthSignUpRequestVo.getName())
            .provider(oauthSignUpRequestVo.getProvider())
            .birth((oauthSignUpRequestVo.getBirth()))
            .phone(oauthSignUpRequestVo.getPhone())
            .build();

        OauthSignUpResponseVo oauthSignUpResponseVo =
            modelMapper.map(customerService.oauthSignUp(oauthSignUpDto),
                OauthSignUpResponseVo.class);

        return new CommonResponseEntity<>(
            HttpStatus.OK,
            "소셜 회원가입에 성공하였습니다.",
            oauthSignUpResponseVo);

    }

    @Operation(summary = "OAuth SignUp Additional Info API", description = "OAuth SignUp 후 추가 정보를 입력하는 API입니다.", tags = {
        "Auth"})
    @PostMapping("auth/sign-up/oauth/policy")
    public CommonResponseEntity<OauthSignInResponseVo> oauthSignUpAdditionalInfo(
        @RequestBody OauthSignUpRequestPolicyVo oauthSignUpRequestPolicyVo) {

        ModelMapper modelMapper = new ModelMapper();
        OauthSignUpPolicyDto oauthSignUpPolicyDto = OauthSignUpPolicyDto.builder()
            .accessToken(oauthSignUpRequestPolicyVo.getAccessToken())
            .uuid(oauthSignUpRequestPolicyVo.getUuid())
            .marketingSms(oauthSignUpRequestPolicyVo.getMarketingSms())
            .marketingEmail(oauthSignUpRequestPolicyVo.getMarketingEmail())
            .marketingDm(oauthSignUpRequestPolicyVo.getMarketingDm())
            .marketingCall(oauthSignUpRequestPolicyVo.getMarketingCall())
            .essential1(oauthSignUpRequestPolicyVo.getPolicyEssential1())
            .essential2(oauthSignUpRequestPolicyVo.getPolicyEssential2())
            .essential3(oauthSignUpRequestPolicyVo.getPolicyEssential3())
            .optional(oauthSignUpRequestPolicyVo.getPolicyOptional())
            .zipCode(oauthSignUpRequestPolicyVo.getZipCode())
            .address(oauthSignUpRequestPolicyVo.getAddress())
            .addressDetail(oauthSignUpRequestPolicyVo.getAddressDetail())
            .build();

        OauthSignInResponseVo oauthSignInResponseVo =
            modelMapper.map(customerService.oauthpolicySignUp(oauthSignUpPolicyDto),
                OauthSignInResponseVo.class);

        return new CommonResponseEntity<>
            (HttpStatus.OK, "추가 정보 저장에 성공하였습니다", oauthSignInResponseVo);
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
        @RequestBody OauthSignInRequestVo oauthCustomerSignUpRequestVo) {
        ModelMapper modelMapper = new ModelMapper();
        OauthSignInRequestDto oauthSignInRequestDto = OauthSignInRequestDto.builder()
            .email(oauthCustomerSignUpRequestVo.getEmail())
            .provider(oauthCustomerSignUpRequestVo.getProvider())
            .build();
        SignInResponseVo signInResponseVo = modelMapper.map(
            customerService.oauthSignIn(oauthSignInRequestDto), SignInResponseVo.class);
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
