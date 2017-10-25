package com.mdvns.mdvn.mdvn.util;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.WriterFactory;

import java.io.File;
import java.io.FileReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class TempTest {

    public static void main(String[] args) {
        File file = new File("");
        String projPath = file.getAbsolutePath();
        System.out.println(projPath);
        File xmlFile = new File(projPath +File.separator+ "mdvn-task-sapi"+File.separator+"pom.xml");
        try {
//            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder= builderFactory.newDocumentBuilder();
//            Document document = builder.parse(xmlFile);
//            Element element = document.getDocumentElement();
//            System.out.println(element);
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader(projPath +File.separator+ "mdvn-task-papi"+File.separator+"pom.xml"));
            List<Dependency> dependencies = model.getDependencies();
            List<Dependency> newDependencies = model.getDependencies();
            for (int i = 0; i < dependencies.size(); i++) {
                if(dependencies.get(i).getArtifactId().equals("spring-boot-starter-tomcat")){
                    System.out.println(dependencies.get(i).getScope());
                    dependencies.get(i).setScope("provided");
                    System.out.println(dependencies.get(i).getScope());

                }
                newDependencies.add(dependencies.get(i));

            }
            model.setDependencies(newDependencies);


            MavenXpp3Writer pomWriter = new MavenXpp3Writer();
            Writer fileWriter = WriterFactory.newXmlWriter(xmlFile);
            pomWriter.write(fileWriter,model);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
