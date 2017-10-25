package com.mdvns.mdvn.mdvn.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DeployUtil {
    public static void main(String[] args) {

        updateProperties("test",setPackageList());


    }



    private static void updateProperties(String config, List<String> packList){
        try {
            Properties pro = new Properties();
            InputStream in;
            String projPath = new File("").getAbsolutePath()+File.separator;
            String configPath = File.separator + "src" + File.separator+ "main" + File.separator + "resources" +File.separator +"application.properties";
            String attr = "spring.profiles.active";
            for (int i = 0; i < packList.size(); i++) {
                String packageName = packList.get(i);
                String filePath = projPath + packageName + configPath;
                in = new BufferedInputStream(new FileInputStream(filePath));
                pro.load(in);
                String currentConfig = pro.getProperty(attr);
                System.out.println("current config: "+packageName+"-->"+attr+":"+currentConfig);
                if(currentConfig.equals(config)){
                    System.err.println("current config: "+packageName+" is correct, no need to change");
                }else{
                    FileOutputStream file = new FileOutputStream(filePath);
                    pro.put("spring.profiles.active", config);
                    pro.store(file, "update the config to "+config ); //这句话表示重新写入配置文件
                    System.out.println("updated config: "+packageName+"-->"+attr+":"+pro.getProperty("spring.profiles.active"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List<String> setPackageList(){
        List<String> packageList = new ArrayList<>();
        packageList.add("mdvn-model-papi");
        packageList.add("mdvn-model-sapi");
        packageList.add("mdvn-project-papi");
        packageList.add("mdvn-project-sapi");
        packageList.add("mdvn-reqmnt-papi");
        packageList.add("mdvn-reqmnt-sapi");
        packageList.add("mdvn-staff-papi");
        packageList.add("mdvn-staff-sapi");
        packageList.add("mdvn-story-papi");
        packageList.add("mdvn-story-sapi");
        packageList.add("mdvn-tag-papi");
        packageList.add("mdvn-tag-sapi");
        packageList.add("mdvn-task-papi");
        packageList.add("mdvn-task-sapi");
        return packageList;

    }







}
