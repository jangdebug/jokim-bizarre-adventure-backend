package com.jokim.sivillage.common.utils;

import java.util.UUID;

public class CodeGenerator {

    public static String generateCode(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
