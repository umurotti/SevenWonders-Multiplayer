/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cs319deneme3;

import io.swagger.jaxrs.config.BeanConfig;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author ferhat
 */
@ApplicationPath("/7wonders")
public class JaxRsActivator extends Application {

    public JaxRsActivator() {

        Properties props = new Properties();
        try {
            props.load(new FileInputStream("/etc/7wonders.properties"));
        } catch (Exception ex) {
            Logger.getLogger(JaxRsActivator.class.getName()).log(Level.SEVERE, "Error loading /etc/7wonders.properties", ex);
        }

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost(props.getProperty("host", "ebedek:8080"));
//        beanConfig.setHost("ec2-54-93-112-68.eu-central-1.compute.amazonaws.com:8080");
        beanConfig.setBasePath("/cs319deneme3/7wonders");
//        beanConfig.setBasePath("/cs319deneme3-1.0-SNAPSHOT/7wonders");
//        beanConfig.setBasePath("7wonders");
        beanConfig.setResourcePackage(SWhouseServices.class.getPackage().getName());
        beanConfig.setTitle("Domain services RESTful API");
        beanConfig.setDescription("Powered by: RESTEasy, Swagger and Swagger UI");
        beanConfig.setScan(true);
//        packages("com.mycompany.cs319deneme3");
    }
}
