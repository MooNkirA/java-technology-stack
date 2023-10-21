package com.moon.design.chain.vo;

import com.moon.design.chain.enums.ErrorCode;
import lombok.Data;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2023-10-07 20:32
 * @description
 */
@Data
public class Result {

    private boolean success;
    private String code;
    private String msg;
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode("0");
        return result;
    }

    public static Result failure(ErrorCode errorCode) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(errorCode.getCode());
        result.setMsg(errorCode.getMsg());
        return result;
    }

}
