package com.bookshop.bookshop.result;

public class ResultFactory {

    public static Result buildResult(ResultEnum resultCode, String message, Object data) {
        return buildResult(resultCode.code, message, data);
    }

    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }

    public static Result buildSuccessResult(Object data,String message) {
        return buildResult(ResultEnum.SUCCESS, message, data);
    }

    public static Result buildFailResult(String message) {
        return buildResult(ResultEnum.FAIL, message, null);
    }


}
