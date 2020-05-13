package com.sunday.wkday.handler;

import com.sunday.wkday.enums.BaseErrorCode;
import com.sunday.wkday.exception.BaseException;
import com.sunday.wkday.util.ResponseBuilder;
import com.sunday.wkday.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author copysunday
 */
@Slf4j
@ControllerAdvice
@Component
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    BaseResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResponseBuilder.error(BaseErrorCode.METHOD_NOT_SUPPORTED);
        } else if(e instanceof BaseException) {
            return ResponseBuilder.error(((BaseException) e).getCode(), e.getMessage());
        }
        return ResponseBuilder.error(BaseErrorCode.UNKNOWN_ERROR);
    }


    /**
     * 处理实体字段校验不通过异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for (FieldError error : fieldErrors) {
            builder.append(error.getDefaultMessage()).append(" ");
        }
        log.error("{}#{}() error:{}", ex.getParameter().getDeclaringClass().getName(), ex.getParameter().getMethod().getName(), builder.toString());
        return ResponseBuilder.error(BaseErrorCode.PARAM_ERROR, builder.toString());
    }
}
