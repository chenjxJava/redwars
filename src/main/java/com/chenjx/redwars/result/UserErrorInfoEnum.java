package com.chenjx.redwars.result;


/**
 * 业务错误码 案例
 * <p>
 * Created by bysocket on 14/03/2017.
 */
public enum UserErrorInfoEnum implements ErrorInfoInterface {
    LOGIN_ERROR("000001", "username or password error"),
    USERNAME_REPEAT("000002", "username has been registered"),
    TOKEN_WITHOUT_RELATIVEUSER("000003", "this token has no relative user");

    private String code;

    private String message;

    UserErrorInfoEnum(String code, String message) {
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
