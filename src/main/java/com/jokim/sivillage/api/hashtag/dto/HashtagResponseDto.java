package com.jokim.sivillage.api.hashtag.dto;


import com.jokim.sivillage.api.hashtag.vo.HashtagResponseVo;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
public class HashtagResponseDto {

    private Long hashtagId;
    private String value;


    public HashtagResponseVo toVo() {
        return HashtagResponseVo.builder()
            .hashtagId(hashtagId)
            .value(value)
            .build();
    }


}
