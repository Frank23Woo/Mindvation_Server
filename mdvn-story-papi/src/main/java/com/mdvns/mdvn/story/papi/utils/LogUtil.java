package com.mdvns.mdvn.story.papi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

   private static final Logger LOG = LoggerFactory.getLogger(LogUtil.class);

    public static void sreviceStartLog(String methodName){
           LOG.debug("---开始执行{}方法...", methodName);
    }

    public static void sreviceEndLog(String methodName){
        LOG.debug("---执行结束{}方法...", methodName);
    }

    public static void errorLog(String errorDesc, Exception e){
        LOG.error(errorDesc, e.getMessage());
    }
    public static void errorLog(String errorDesc){
        LOG.error("---{}",errorDesc);
    }

    public static void logInfo(String logDesc, Object object) {
        LOG.info(logDesc+object.toString());
    }

    public static void logDebug(String logDesc, Object object) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(logDesc, object.toString());
        }
    }
}
