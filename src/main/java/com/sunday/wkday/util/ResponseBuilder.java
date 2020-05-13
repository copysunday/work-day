package com.sunday.wkday.util;

import com.sunday.wkday.enums.BaseErrorCode;
import com.sunday.wkday.vo.BaseResult;

public class ResponseBuilder {
    /**
     * error
     * @param code
     * @param message
     * @return
     */
    public static BaseResult error(String code, String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(code);
        baseResult.setMessage(message);
        return baseResult;
    }

    public static BaseResult error(BaseErrorCode baseErrorCode) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(baseErrorCode.getCode());
        baseResult.setMessage(baseErrorCode.getMessage());
        return baseResult;
    }

    public static BaseResult error(BaseErrorCode baseErrorCode, String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(baseErrorCode.getCode());
        baseResult.setMessage(message);
        return baseResult;
    }

    public static<T> BaseResult<T> success(T object) {
        BaseResult<T> result = new BaseResult<>(object);
        result.setCode(BaseErrorCode.SUCCESS.getCode());
        result.setMessage(BaseErrorCode.SUCCESS.getMessage());
        return result;
    }
}
