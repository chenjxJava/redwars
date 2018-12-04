package com.chenjx.redwars.result;


/**
 * 业务错误码 案例
 * <p>
 * Created by bysocket on 14/03/2017.
 */
public enum UploadErrorInfoEnum implements ErrorInfoInterface {
    NO_CONTENT("000001", "params no complete"),
    CITY_EXIT("000002", "city exit");

    private String code;

    private String message;

    UploadErrorInfoEnum(String code, String message) {
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