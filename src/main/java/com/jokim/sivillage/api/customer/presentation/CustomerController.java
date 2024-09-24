package com.jokim.sivillage.api.customer.presentation;


import com.jokim.sivillage.api.customer.application.CustomerService;
import com.jokim.sivillage.api.customer.dto.DuplicateEmailDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenRequestDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenResponseDto;
import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.dto.out.AddressResponseDto;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;
import com.jokim.sivillage.api.customer.dto.out.SizeResponseDto;
import com.jokim.sivillage.api.customer.vo.DuplicateEmailVo;
import com.jokim.sivillage.api.customer.vo.RefreshTokenResponseVo;
import com.jokim.sivillage.api.customer.vo.in.*;
import com.jokim.sivillage.api.customer.vo.out.AddressResponseVo;
import com.jokim.sivillage.api.customer.vo.out.SignInResponseVo;
import com.jokim.sivillage.api.customer.dto.in.CustomerSizeRequestDto;
import com.jokim.sivillage.api.customer.dto.in.UpdateInfoRequestDto;
import com.jokim.sivillage.api.customer.dto.in.UpdatePasswordRequestDto;
import com.jokim.sivillage.api.customer.vo.in.CustomerSizeRequestVo;
import com.jokim.sivillage.api.customer.vo.in.UpdateInfoRequestVo;
import com.jokim.sivillage.api.customer.vo.in.UpdatePasswordRequestVo;
import com.jokim.sivillage.api.customer.vo.out.SizeResponsVo;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.vo.out.ProductResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.server.PathParam;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
        customerService.duplicateEmail(DuplicateEmailDto.toDto(duplicateEmailVo));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
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
            return new BaseResponse<>(BaseResponseStatus.FAILED_CREATE_AUTHORITY);
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

    @Operation(summary = "Beauty/Size API", description = "사용자 뷰티/사이즈 생성 API 입니다.", tags = {"MyPage"})
    @PostMapping("/mypage/size")
    public BaseResponse<Void> createCustomerSize(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CustomerSizeRequestVo customerSizeRequestVo
    ) {
        // Authorization 헤더에서 accessToken 추출
        String accessToken = authorizationHeader.replace("Bearer ", "");
        customerService.saveOrUpdateCustomerSize(CustomerSizeRequestDto.toDto(accessToken, customerSizeRequestVo));

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);

    }

    @Operation(summary = "Beauty/Size API", description = "사용자 뷰티/사이즈 Get API 입니다.", tags = {"MyPage"})
    @GetMapping("/mypage/size")
    public BaseResponse<SizeResponsVo> GetCustomerSize(
        @RequestHeader("Authorization") String authorizationHeader
    ) {
        // Authorization 헤더에서 accessToken 추출
        String accessToken = authorizationHeader.replace("Bearer ", "");

        // 고객의 사이즈를 가져옴
        SizeResponseDto sizeResponsDto = customerService.getCustomerSize(accessToken);

        return new BaseResponse<>(sizeResponsDto.toVo());
    }

    @Operation(summary = "Delivery API", description = "사용자 배송지 조회 API 입니다.", tags = {"MyPage"})
    @GetMapping("/mypage/delivery-info")
    public BaseResponse<List<AddressResponseVo>> getAddress(
        @RequestHeader("Authorization") String authorizationHeader
    ) {
        // Authorization 헤더에서 accessToken 추출
        String accessToken = authorizationHeader.replace("Bearer ", "");

        // AddressResponseDto 리스트를 받아옴
        List<AddressResponseDto> addressResponseDtos = customerService.getAddress(accessToken);

        // AddressResponseDto 리스트를 AddressResponseVo 리스트로 변환
        List<AddressResponseVo> addressResponseVos = addressResponseDtos.stream()
            .map(AddressResponseDto::toVo)
            .toList(); // Dto -> Vo로 변환

        // 결과를 ResponseEntity로 반환
        return new BaseResponse<>(addressResponseVos);
    }

    @Operation(summary = "Delivery API", description = "사용자 배송지 상세조회 API 입니다.", tags = {"MyPage"})
    @GetMapping("/mypage/delivery-info/{addressCode}")
    public BaseResponse<AddressResponseVo> getAddressDetail(
        @RequestHeader("Authorization") String authorizationHeader,
        @PathVariable("addressCode") String addressCode
    ) {
        return new BaseResponse<>(customerService.getAddressDetail(addressCode).toVo());
    }

    @Operation(summary = "Delevery API", description = "사용자 배송지 생성 API 입니다.", tags = {"MyPage"})
    @PostMapping("/mypage/delivery-info")
    public BaseResponse<Void> CreateAddress(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CustomerAddressRequestVo customerAddressRequestVo
    ) {
        // Authorization 헤더에서 accessToken 추출
        String accessToken = authorizationHeader.replace("Bearer ", "");
        customerService.createAddress(
            CustomerAddressRequestDto.toDto(accessToken, customerAddressRequestVo));

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);

    }

    @Operation(summary = "Delevery API", description = "사용자 배송지 삭제 API 입니다.", tags = {"MyPage"})
    @DeleteMapping("/mypage/delivery-info/{addressCode}")
    public BaseResponse<Void> DeleteAddress(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("addressCode") String addressCode
    ) {
        // Authorization 헤더에서 accessToken 추출
        customerService.deleteAddress(addressCode);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);

    }

    @Operation(summary = "Delevery API", description = "사용자 배송지 수정 API 입니다.", tags = {"MyPage"})
    @PutMapping("/mypage/delivery-info")
    public BaseResponse<Void> UpdateAddress(
        @RequestHeader("Authorization") String authorizationHeader,
        @RequestBody CustomerAddressUpdateVo customerAddressUpdateVo
    ) {
        String accessToken = authorizationHeader.replace("Bearer ", "");
        customerService.updateAddress(CustomerAddressRequestDto.toUpdateDto(accessToken, customerAddressUpdateVo));

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);

    }

    @Operation(summary = "Delevery API", description = "사용자 기본배송지 설정 API 입니다.", tags = {"MyPage"})
    @PutMapping("/mypage/delivery/default-address/{addressCode}")
    public BaseResponse<Void> setDefaultAddress(
        @RequestHeader("Authorization") String authorizationHeader,
        @PathVariable("addressCode") String addressCode
    ) {
        String accessToken = authorizationHeader.replace("Bearer ", "");
        customerService.setDefaultAddress(CustomerAddressDefaultListDto.toSetDefaultAddress(addressCode, accessToken));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);

    }


}
