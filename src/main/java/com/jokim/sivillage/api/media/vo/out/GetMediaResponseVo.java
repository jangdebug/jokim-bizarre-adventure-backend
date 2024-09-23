package com.jokim.sivillage.api.media.vo.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMediaResponseVo {

    private String mediaCode;

    @JsonProperty("mediaUrl")
    private String url;

    @JsonProperty("mediaName")
    private String name;

    private String mediaType;

}
