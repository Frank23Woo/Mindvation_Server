package com.mdvns.mdvn.common.exception;

import java.util.HashMap;
import java.util.Map;

public class ReturnFormat {
    private static Map<String,String> messageMap = new HashMap<String, String>();
    //��ʼ��״̬��������˵��
    static {
        messageMap.put("000", "����ɹ�");

        messageMap.put("400", "Bad Request!");
        messageMap.put("401", "NotAuthorization");
        messageMap.put("405", "Method Not Allowed");
        messageMap.put("406", "Not Acceptable");
        messageMap.put("500", "Internal Server Error");

        messageMap.put("1000", "[������]����ʱ�쳣");
        messageMap.put("1001", "[������]��ֵ�쳣");
        messageMap.put("1002", "[������]��������ת���쳣");
        messageMap.put("1003", "[������]IO�쳣");
        messageMap.put("1004", "[������]δ֪�����쳣");
        messageMap.put("1005", "[������]����Խ���쳣");
        messageMap.put("1006", "[������]�����쳣");
/*
        messageMap.put("1010", "�û�δע��");
        messageMap.put("1011", "�û���ע��");
        messageMap.put("1012", "�û������������");
        messageMap.put("1013", "�û��ʺŶ���");
        messageMap.put("1014", "�û���Ϣ�༭ʧ��");
        messageMap.put("1015", "�û���ϢʧЧ�������»�ȡ");

        messageMap.put("1020", "��֤�뷢��ʧ��");
        messageMap.put("1021", "��֤��ʧЧ");
        messageMap.put("1022", "��֤�����");
        messageMap.put("1023", "��֤�벻����");
        messageMap.put("1029", "����ƽ̨�쳣");

        messageMap.put("1030", "�ܱ��޵���");
        messageMap.put("1031", "�������ʧ��");
        messageMap.put("1032", "�༭������Ϣʧ��");
        messageMap.put("1033", "ÿ���û�ֻ�����һ������");
        messageMap.put("1034", "���̲�����");

        messageMap.put("1040", "�������Ʒ");
        messageMap.put("1041", "���ʧ��,��Ʒ���೬������");
        messageMap.put("1042", "��Ʒ������");
        messageMap.put("1043", "��Ʒɾ��ʧ��");

        messageMap.put("2010", "ȱ�ٲ�����ֵΪ��");

        messageMap.put("2029", "�������Ϸ�");
        messageMap.put("2020", "��Ч��Token");
        messageMap.put("2021", "�޲���Ȩ��");
        messageMap.put("2022", "RSA����ʧ��,������������");
        messageMap.put("2023", "�����µ�¼");*/
    }
    public static RestDefaultRespons retParam(String status,String responseCodde, Object data) {
        RestDefaultRespons restDefautResponse = new RestDefaultRespons(status, responseCodde, messageMap.get(responseCodde), data);
        return restDefautResponse;
    }
  
}
