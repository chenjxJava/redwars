package com.chenjx.redwars.result;

/**
 * 应用系统级别的错误码
 * <p>
 * Created by bysocket on 14/03/2017.
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface {
    SUCCESS("0", "success"),
    NOT_FOUND("-1", "service not found"),
    PARAMS_NO_COMPLETE("000001", "params no complete"),
    NO_TOKEN("000002", "no token, login first"),
    SYSTEM_ERROR("500", "system error");

    private String code;

    private String message;

    GlobalErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
