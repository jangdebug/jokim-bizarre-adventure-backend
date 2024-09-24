package com.jokim.sivillage.api.media.vo.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AddMediaRequestVo {

    @JsonProperty("mediaUrl")
    private String url;

    @JsonProperty("mediaName")
    private String name;

    private String mediaType;

}
