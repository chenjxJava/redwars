package com.chenjx.redwars.utils;

import java.util.UUID;

/**
 * create by chenjx 2018/11/26
 */
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
