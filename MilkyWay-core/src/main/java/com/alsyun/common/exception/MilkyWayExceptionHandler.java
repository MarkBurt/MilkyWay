package com.alsyun.common.exception;

import com.alsyun.common.api.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.connection.PoolException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 *
 * @author Markburt
 */
@RestControllerAdvice
@Slf4j
public class MilkyWayExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(MilkyWayException.class)
    public Result<?> handleMilkyWayException(MilkyWayException e){
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(MilkyWay401Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<?> handleMilkyWay401Exception(MilkyWay401Exception e){
        log.error(e.getMessage(), e);
        return new Result(401,e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result<?> handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public Result<?> handleAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(), e);
        return Result.noauth("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e){
        log.error(e.getMessage(), e);
        return Result.error("操作失败，"+e.getMessage());
    }

    /**
     * @Author 政辉
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("不支持");
        stringBuffer.append(e.getMethod());
        stringBuffer.append("请求方法，");
        stringBuffer.append("支持以下");
        String [] methods = e.getSupportedMethods();
        if(methods!=null){
            for(String str:methods){
                stringBuffer.append(str);
                stringBuffer.append("、");
            }
        }
        log.error(stringBuffer.toString(), e);
        //return Result.error("没有权限，请联系管理员授权");
        return Result.error(405,stringBuffer.toString());
    }

    /**
     * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error(e.getMessage(), e);
        return Result.error("文件大小超出10MB限制, 请压缩或降低文件质量! ");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        return Result.error("字段太长,超出数据库字段的长度");
    }

    @ExceptionHandler(PoolException.class)
    public Result<?> handlePoolException(PoolException e) {
        log.error(e.getMessage(), e);
        return Result.error("Redis 连接异常!");
    }

}
