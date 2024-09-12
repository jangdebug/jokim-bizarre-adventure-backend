package com.jokim.sivillage.common.utils;

import java.util.UUID;

public class CodeGenerator {

    public static String generateCode() {
        return UUID.randomUUID().toString();
    }
}
