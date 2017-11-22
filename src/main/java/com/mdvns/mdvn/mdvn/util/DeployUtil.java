package com.mdvns.mdvn.mdvn.util;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.WriterFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DeployUtil {
    public static void main(String[] args) {

//        makeWarPackage();
        localhostIntegration();
//        makeWarPackageForProd();
    }

    private static void updateProperties(String config, List<String> packageList){
        try {
            Properties pro = new Properties();
            InputStream in;
            String projPath = new File("").getAbsolutePath()+File.separator;
            String configPath = File.separator + "src" + File.separator+ "main" + File.separator + "resources" +File.separator +"application.properties";
            String attr = "spring.profiles.active";
            for (int i = 0; i < packageList.size(); i++) {
                String packageName = packageList.get(i);
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



    private static void updatePomDependencyScope(String dependency, String scope, List<String> packageList){
        if(StringUtils.isEmpty(scope)){
            scope=null;
        }
        String projPath = new File("").getAbsolutePath();
        for (int i = 0; i < packageList.size(); i++) {
            File pomFile = new File(projPath +File.separator+ packageList.get(i)+File.separator+"pom.xml");
            try {
                MavenXpp3Reader reader = new MavenXpp3Reader();
                Model model = reader.read(new FileReader(projPath +File.separator+ packageList.get(i)+File.separator+"pom.xml"));
                List<Dependency> dependencies = model.getDependencies();
                for (int j = 0; j < dependencies.size(); j++) {
                    if(dependencies.get(j).getArtifactId().equals(dependency)){
                        if(!StringUtils.isEmpty(dependencies.get(j).getScope()) && dependencies.get(j).getScope().equals(scope)){
                            System.err.println(" current dependency scope is correct, no need to change");
                        }else if(StringUtils.isEmpty(scope)  && StringUtils.isEmpty(dependencies.get(j).getScope())){
                            System.err.println(" current dependency scope is also null, no need to change");
                        }else{
                            System.out.println(packageList.get(i)+" current dependency scope is "+dependencies.get(j).getScope());
                            dependencies.get(j).setScope(scope);
                            System.out.println(packageList.get(i)+" update dependency to "+dependencies.get(j).getScope());
                        }
                    }
                }
                model.setDependencies(dependencies);
                MavenXpp3Writer pomWriter = new MavenXpp3Writer();
                Writer fileWriter = WriterFactory.newXmlWriter(pomFile);
                pomWriter.write(fileWriter,model);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        packageList.add("mdvn-dashboard-papi");
        packageList.add("mdvn-dashboard-sapi");
        packageList.add("mdvn-department-papi");
        packageList.add("mdvn-department-sapi");
        packageList.add("mdvn-file-papi");
        packageList.add("mdvn-file-sapi");
        packageList.add("mdvn-comment-papi");
        packageList.add("mdvn-comment-sapi");
        return packageList;
    }


    private static void makeWarPackage(){
        updateProperties("test",setPackageList());
        updatePomDependencyScope("spring-boot-starter-tomcat","provided",setPackageList());
    }

    private static void localhostIntegration(){
        updateProperties("dev",setPackageList());
        updatePomDependencyScope("spring-boot-starter-tomcat","",setPackageList());
    }

    private static void makeWarPackageForProd(){
        updateProperties("prod",setPackageList());
        updatePomDependencyScope("spring-boot-starter-tomcat","provided",setPackageList());
    }



}
