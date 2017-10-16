package com.mdvns.mdvn.story.sapi.exception;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

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

//@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    //SAPI调用异常
    @ExceptionHandler(BusinessException.class)
    public RestResponse businessExceptionHandler(BusinessException ex) {
        LOG.error("Internal Server Error:{}", ex.getMessage());
        return RestResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getErrorCode(), ex.getErrorMsg());
    }

    //调用SAPI异常
    @ExceptionHandler(HttpClientErrorException.class)
    public RestResponse httpClientErrorException(HttpClientErrorException ex) {
        LOG.error("Internal Server Error:{}", ex.getMessage());
        return RestResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionEnum.SAPI_EXCEPTION.getErroCode(), ex.getMessage());
    }
//    @ExceptionHandler(ConstraintViolationException.class)
//    public RestResponse defautExceptinHandler(ConstraintViolationException ex) {
//        LOG.error("违反唯一性约束: "+ex.getMessage());
//        return ReturnFormat.retParam("500", "500",ex.getMessage());
//    }
//
//    //运行时异常
//   /* @ExceptionHandler(RuntimeException.class)
//    public RestResponse runtimeExceptionHandler(RuntimeException ex) {
//        LOG.error("运行时异常:{}", ex.getMessage());
//        return ReturnFormat.retParam("500", "500", ex.getMessage());
//    }*/
//
//    //空指针异常
//    @ExceptionHandler(NullPointerException.class)
//    public RestResponse nullPointerExceptionHandler(NullPointerException ex) {
//        LOG.error("空指针异常:{}", ex.getMessage());
//        return ReturnFormat.retParam("500", "1001", ex.getMessage());
//    }
//
//    //类型转换异常
//    @ExceptionHandler(ClassCastException.class)
//    public RestResponse classCastExceptionHandler(ClassCastException ex) {
//        LOG.error("类型转换异常:{}", ex.getMessage());
//        return ReturnFormat.retParam("500", "1002", ex.getMessage());
//    }
//
//    //IO异常
//    @ExceptionHandler(IOException.class)
//    public RestResponse iOExceptionHandler(IOException ex) {
//        LOG.error("IO异常:{}", ex.getMessage());
//        return ReturnFormat.retParam("500", "1003", ex.getLocalizedMessage());
//    }
//
//    //未知方法异常
//    @ExceptionHandler(NoSuchMethodException.class)
//    public RestResponse noSuchMethodExceptionHandler(NoSuchMethodException ex) {
//        LOG.error("处理请求的方法不存在:{}", ex.getMessage());
//        return ReturnFormat.retParam("500", "1004", ex.getMessage());
//    }
//
//    //数组越界异常
//    @ExceptionHandler(IndexOutOfBoundsException.class)
//    public RestResponse indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
//        LOG.error("数组越界异常:{}", ex.getMessage());
//        return ReturnFormat.retParam("500", "1005", ex.getMessage());
//    }
//
//    //500错误
//    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
//    public RestResponse server500(RuntimeException runtimeException) {
//        LOG.error("500错误:{}", runtimeException.getMessage());
//        return ReturnFormat.retParam("406", "406", runtimeException.getMessage());
//    }
//
//    //400错误
//    @ExceptionHandler({HttpMessageNotReadableException.class})
//    public RestResponse requestNotReadable(HttpMessageNotReadableException ex) {
//        LOG.error("400错误:{}", ex.getMessage());
//        return ReturnFormat.retParam(HttpStatus.BAD_REQUEST.toString(), "400", ex.getMessage());
//    }
//
//    //400错误
//    @ExceptionHandler({TypeMismatchException.class})
//    public RestResponse requestTypeMismatch(TypeMismatchException ex) {
//        LOG.error("400错误:{}", ex.getMessage());
//        return ReturnFormat.retParam(HttpStatus.BAD_REQUEST.toString(), "400", ex.getMessage());
//    }
//
//    //400错误
//    @ExceptionHandler({MissingServletRequestParameterException.class})
//    public RestResponse requestMissingServletRequest(MissingServletRequestParameterException ex) {
//        LOG.error("400错误:{}", ex.getMessage());
//        return ReturnFormat.retParam(HttpStatus.BAD_REQUEST.toString(), "400", ex.getMessage());
//    }
//
//    @ExceptionHandler(value = {NoHandlerFoundException.class})
//    public RestResponse noHandlerFoundException(NoHandlerFoundException ex) {
//        LOG.error("404错误:{}", ex.getMessage());
//        RestResponse restDefautResponse = ReturnFormat.retParam(HttpStatus.NOT_FOUND.toString(),"404", ex.getMessage());
//
//        return restDefautResponse;
//    }
//
//    //405错误
//    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
//    public RestResponse request405(HttpRequestMethodNotSupportedException ex) {
//        LOG.error("405错误:{}", ex.getMessage());
//        return ReturnFormat.retParam(HttpStatus.METHOD_NOT_ALLOWED.toString(), "405", ex.getMessage());
//    }
//
//    //406错误
//    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
//    public RestResponse request406(HttpMediaTypeNotAcceptableException ex) {
//        LOG.error("406错误:{}", ex.getMessage());
//        return ReturnFormat.retParam(HttpStatus.NOT_ACCEPTABLE.toString(), "406", ex.getMessage());
//    }


}