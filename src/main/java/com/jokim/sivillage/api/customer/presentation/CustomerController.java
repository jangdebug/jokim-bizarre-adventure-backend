package com.jokim.sivillage.api.customer.presentation;


import com.jokim.sivillage.api.customer.application.CustomerService;
import com.jokim.sivillage.api.customer.dto.DuplicateEmailDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenRequestDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenResponseDto;
import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;
import com.jokim.sivillage.api.customer.vo.DuplicateEmailVo;
import com.jokim.sivillage.api.customer.vo.RefreshTokenResponseVo;
import com.jokim.sivillage.api.customer.vo.in.*;
import com.jokim.sivillage.api.customer.vo.out.SignInResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class CustomerController {

    private final CustomerService customerService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "SignUp API", description = "SignUp API 입니다.", tags = {"Auth"})
    @PostMapping("auth/sign-up/simple")
    public BaseResponse<Void> signUp(
        @RequestBody SignUpRequestVo signUpRequestVo) {
        customerService.signUp(SignUpRequestDto.toDto(signUpRequestVo));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "OAuth SignIn API", description = "OAuth SignIn API 입니다.", tags = {"Auth"})
    @PostMapping("auth/sign-in/oauth")
    public BaseResponse<SignInResponseVo> oauthSignIn(
        @RequestBody OauthSignInRequestVo oauthSignInRequestVo) {

        // 소셜 로그인 처리
        SignInResponseDto responseDto = customerService.oauthSignIn(OauthSignInRequestDto.toDto(oauthSignInRequestVo));

        // 회원이 아닌 경우 회원가입 안내
        if (responseDto == null) {
            log.info("회원이 아니므로 회원가입 페이지로 이동합니다: {}", oauthSignInRequestVo.getEmail());
            return new BaseResponse<>(
                BaseResponseStatus.NO_EXIST_USER);
        }
        // 로그인 성공 시
        return new BaseResponse<> (
            responseDto.toVo()
        );
    }


    @Operation(summary = "SignIn API", description = "SignIn API 입니다.", tags = {"Auth"})
    @PostMapping("auth/sign-in")
    public BaseResponse<SignInResponseVo> signIn(
        @RequestBody SignInRequestVo signInRequestVo) {

        if(customerService.signIn(SignInRequestDto.toDto(signInRequestVo)).toVo() != null){
            return new BaseResponse<>(
                customerService.signIn(SignInRequestDto.toDto(signInRequestVo)).toVo());
        }
        return new BaseResponse<>(
            BaseResponseStatus.NO_EXIST_USER);
    }

    @Operation(summary = "DuplicateEmail API", description = "이메일 중복체크 API입니다", tags = {"Auth"})
    @PostMapping("auth/duplicate-email")
    public BaseResponse<Void> duplicateEmail(@RequestBody DuplicateEmailVo duplicateEmailVo) {
        try {
            customerService.duplicateEmail(DuplicateEmailDto.toDto(duplicateEmailVo));
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @Operation(summary = "Logout API", description = "로그아웃 API 입니다.", tags = {"Auth"})
    @PostMapping("auth/logout")
    public void logout(@RequestHeader("Authorization") String authorizationHeader) {

    }

    @Operation(summary = "Refresh AccessToken API", description = "리프레시 토큰을 사용하여 새로운 액세스 토큰을 재발급합니다.", tags = {"Auth"})
    @PostMapping("auth/refresh")
    public BaseResponse<RefreshTokenResponseVo> refreshAccessToken(
        @RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Authorization 헤더에서 리프레시 토큰 추출
            String refreshToken = authorizationHeader.replace("Bearer ", "");
            // 리프레시 토큰으로 액세스 토큰 재발급
            RefreshTokenResponseDto responseDto = customerService.refreshAccessToken(RefreshTokenRequestDto.toDto(refreshToken));

            // 재발급된 액세스 토큰을 반환
            return new BaseResponse<>(responseDto.toVo());
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        } catch (Exception e) {
            log.error("리프레시 토큰 재발급 오류: ", e);
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "UpdatePassword API", description = "사용자 비밀번호 업데이트 API 입니다.", tags = {"MyPage"})
    @PutMapping("/mypage/init-info/change-pwd")
    public BaseResponse<Void> updatePassword(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody UpdatePasswordRequestVo updatePasswordRequestVo
    ) {
        // Authorization 헤더에서 accessToken 추출
        String accessToken = authorizationHeader.replace("Bearer ", "");
        customerService.updatePassword(UpdatePasswordRequestDto.toDto(accessToken, updatePasswordRequestVo));

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);

    }

    @Operation(summary = "UpdateInfo API", description = "사용자 정보/정책 업데이트 API 입니다.", tags = {"MyPage"})
    @PutMapping("/mypage/info")
    public BaseResponse<Void> updateInfo(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody UpdateInfoRequestVo updateInfoRequestVo
            ) {
        // Authorization 헤더에서 accessToken 추출
        String accessToken = authorizationHeader.replace("Bearer ", "");
        customerService.updateInfo(UpdateInfoRequestDto.toDto(accessToken, updateInfoRequestVo));

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);

    }

    @Operation(summary = "Beauty/Size API", description = "사용자 뷰티/사이즈 API 입니다.", tags = {"MyPage"})
    @PostMapping("/mypage/size")
    public BaseResponse<Void> createCustomerSize(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CustomerSizeRequestVo customerSizeRequestVo
    ) {
        // Authorization 헤더에서 accessToken 추출
        String accessToken = authorizationHeader.replace("Bearer ", "");
        customerService.createCustomerSize(CustomerSizeRequestDto.toDto(accessToken, customerSizeRequestVo));

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);

    }

    @Operation(summary = "Beauty/Size API", description = "사용자 뷰티/사이즈 API 입니다.", tags = {"MyPage"})
    @PutMapping("/mypage/size")
    public BaseResponse<Void> updateCustomerSize(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CustomerSizeRequestVo customerSizeRequestVo
    ) {
        // Authorization 헤더에서 accessToken 추출
        String accessToken = authorizationHeader.replace("Bearer ", "");
        customerService.updateCustomerSize(CustomerSizeRequestDto.toDto(accessToken, customerSizeRequestVo));

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);

    }


}
