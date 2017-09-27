package com.mdvns.mdvn.pchklst.papi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger LOG = LoggerFactory.getLogger(LogUtil.class);

    public static void serviceStartLog(String methodName){
        LOG.debug("---开始执行{}方法...", methodName);
    }

    public static void serviceEndLog(String methodName){
        LOG.debug("---执行结束{}方法...", methodName);
    }

    public static void errorLog(Exception e){
        LOG.error(e.getMessage());
    }
}
