package com.bookshop.bookshop.result;

public enum ResultEnum {

    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultEnum(int code) {
        this.code = code;
    }
}
