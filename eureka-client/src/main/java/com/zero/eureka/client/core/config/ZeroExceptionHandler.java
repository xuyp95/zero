package com.zero.eureka.client.core.config;

import com.zero.eureka.client.core.exception.ZeroException;
import com.zero.eureka.client.core.vo.ExceptionResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 * @Author:xuyp
 * @Date:2018/8/26 9:57
 */
@RestControllerAdvice
public class ZeroExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ZeroExceptionHandler.class);

    /**
     * 针对自定义异常的全局处理
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(ZeroException.class)
    public ExceptionResponseVO zeroException(HttpServletRequest request, HttpServletResponse response,
                                             final Exception e) {
        // 设置响应状态
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        // 处理响应值
        ZeroException zeroException = (ZeroException) e;
        ExceptionResponseVO responseVO = new ExceptionResponseVO(zeroException.getCode(), zeroException.getMessage());
        return responseVO;
    }

    /**
     * 处理RuntimeException
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ExceptionResponseVO runtimeException(HttpServletRequest request, HttpServletResponse response,
                                                final Exception e) {

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException runtimeException = (RuntimeException) e;
        log.error("", runtimeException);
        ExceptionResponseVO responseVO = new ExceptionResponseVO("400", runtimeException.getMessage());
        return responseVO;
    }

    /**
     * 统一处理其他异常
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ExceptionResponseVO exception(HttpServletRequest request, HttpServletResponse response,
                                         final Exception e) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        log.error("", e);
        return new ExceptionResponseVO("400", e.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            log.error("bindException", ex);
            return new ResponseEntity<>(new ExceptionResponseVO(status.value()+"", exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()), status);
        }

        if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            log.error("bindException", ex);
            return new ResponseEntity<>(new ExceptionResponseVO(status.value()+"", bindException.getBindingResult().getAllErrors().get(0).getDefaultMessage()), status);
        }

        log.error("全局异常处理", ex);
        return new ResponseEntity<>(new ExceptionResponseVO(status.value()+"", ex.getMessage()), status);
    }
}
