package com.mdvns.mdvn.tag.sapi.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;

/**
 * 异常增强，以JSON的形式返回给客服端
 * 异常增强类型：NullPointerException,RunTimeException,ClassCastException,
 * 　　　　　　　　 NoSuchMethodException,IOException,IndexOutOfBoundsException
 * 　　　　　　　　 以及springmvc自定义异常等，如下：
 * SpringMVC自定义异常对应的status code
 * Exception                       HTTP Status Code
 * ConversionNotSupportedException         500 (Internal Server Error)
 * HttpMessageNotWritableException         500 (Internal Server Error)
 * HttpMediaTypeNotSupportedException      415 (Unsupported Media Type)
 * HttpMediaTypeNotAcceptableException     406 (Not Acceptable)
 * HttpRequestMethodNotSupportedException  405 (Method Not Allowed)
 * NoSuchRequestHandlingMethodException    404 (Not Found)
 * TypeMismatchException                   400 (Bad Request)
 * HttpMessageNotReadableException         400 (Bad Request)
 * MissingServletRequestParameterException 400 (Bad Request)
 */

/*
@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public RestDefaultResponse defautExceptinHandler(ConstraintViolationException ex) {
        LOG.error("违反唯一性约束: "+ex.getMessage());
        return ReturnFormat.retParam("500", "500",ex.getMessage());
    }

    //运行时异常
   */
/* @ExceptionHandler(RuntimeException.class)
    public RestDefaultResponse runtimeExceptionHandler(RuntimeException ex) {
        LOG.error("运行时异常:{}", ex.getMessage());
        return ReturnFormat.retParam("500", "500", ex.getMessage());
    }*//*


    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public RestDefaultResponse nullPointerExceptionHandler(NullPointerException ex) {
        LOG.error("空指针异常:{}", ex.getMessage());
        return ReturnFormat.retParam("500", "1001", ex.getMessage());
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public RestDefaultResponse classCastExceptionHandler(ClassCastException ex) {
        LOG.error("类型转换异常:{}", ex.getMessage());
        return ReturnFormat.retParam("500", "1002", ex.getMessage());
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public RestDefaultResponse iOExceptionHandler(IOException ex) {
        LOG.error("IO异常:{}", ex.getMessage());
        return ReturnFormat.retParam("500", "1003", ex.getLocalizedMessage());
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public RestDefaultResponse noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        LOG.error("处理请求的方法不存在:{}", ex.getMessage());
        return ReturnFormat.retParam("500", "1004", ex.getMessage());
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public RestDefaultResponse indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        LOG.error("数组越界异常:{}", ex.getMessage());
        return ReturnFormat.retParam("500", "1005", ex.getMessage());
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public RestDefaultResponse server500(RuntimeException runtimeException) {
        LOG.error("500错误:{}", runtimeException.getMessage());
        return ReturnFormat.retParam("406", "406", runtimeException.getMessage());
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public RestDefaultResponse requestNotReadable(HttpMessageNotReadableException ex) {
        LOG.error("400错误:{}", ex.getMessage());
        return ReturnFormat.retParam(HttpStatus.BAD_REQUEST.toString(), "400", ex.getMessage());
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public RestDefaultResponse requestTypeMismatch(TypeMismatchException ex) {
        LOG.error("400错误:{}", ex.getMessage());
        return ReturnFormat.retParam(HttpStatus.BAD_REQUEST.toString(), "400", ex.getMessage());
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public RestDefaultResponse requestMissingServletRequest(MissingServletRequestParameterException ex) {
        LOG.error("400错误:{}", ex.getMessage());
        return ReturnFormat.retParam(HttpStatus.BAD_REQUEST.toString(), "400", ex.getMessage());
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public RestDefaultResponse noHandlerFoundException(NoHandlerFoundException ex) {
        LOG.error("404错误:{}", ex.getMessage());
        RestDefaultResponse restDefautResponse = ReturnFormat.retParam(HttpStatus.NOT_FOUND.toString(),"404", ex.getMessage());

        return restDefautResponse;
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public RestDefaultResponse request405(HttpRequestMethodNotSupportedException ex) {
        LOG.error("405错误:{}", ex.getMessage());
        return ReturnFormat.retParam(HttpStatus.METHOD_NOT_ALLOWED.toString(), "405", ex.getMessage());
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public RestDefaultResponse request406(HttpMediaTypeNotAcceptableException ex) {
        LOG.error("406错误:{}", ex.getMessage());
        return ReturnFormat.retParam(HttpStatus.NOT_ACCEPTABLE.toString(), "406", ex.getMessage());
    }


}*/
