package com.mdvns.mdnv.file.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Configuration
public class LocalHostUtil implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(LocalHostUtil.class);

    private static EmbeddedServletContainerInitializedEvent event;


    /*声明访问类型*/
    private static String accessType;

    /*附件存储目录*/
    private static String uploadPath;


    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        LocalHostUtil.event = event;
    }

    public static int getPort() {
        int port = event.getEmbeddedServletContainer().getPort();
        Assert.state(port != -1, "端口号获取失败!");
        LOG.info("端口号为：{}", port);
        return port;
    }

    public static String getIp() throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        LOG.info("本机IP为：{}", ip);
        return ip;
    }

    /**
     * 生成Url
     * @param uploadDir
     * @param fileName
     * @return
     * @throws UnknownHostException
     */
    public static String genUrl(HttpServletRequest request, String uploadDir, String fileName) throws UnknownHostException {
        StringBuilder url = new StringBuilder(accessType);

        url.append(LocalHostUtil.getIp()).append(":").append(LocalHostUtil.getPort())
                .append(request.getContextPath()).append("/").append(uploadPath).append("/").append(uploadDir).append("/").append(fileName);
        LOG.info("该附件的URL为：{}", url);
        return url.toString();
    }

    /*使用@Value注解获取配置文件中的值*/
    @Value("${access.type}")
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    /*使用@Value注解获取配置文件中的值*/
    @Value("${web.upload-path}")
    public void setUploadPath(String uploadPath) {
        LocalHostUtil.uploadPath = uploadPath;
    }
}
