package com.jokim.sivillage.common.utils;

public class TokenExtractor {

    public static String extractToken(String authorizationHeader) {
        return authorizationHeader.replace("Bearer ", "");
    }

}
