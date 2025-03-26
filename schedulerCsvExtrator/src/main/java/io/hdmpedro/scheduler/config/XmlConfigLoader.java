/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hdmpedro.scheduler.config;

import java.io.File;
import java.io.Serializable;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
/**
 *
 * @author DSK-11
 */
public class XmlConfigLoader {
    public static SchedulerConfig loadConfig(String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SchedulerConfig.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchedulerConfig config = (SchedulerConfig) unmarshaller.unmarshal(new File(filePath));

        System.out.println("Databases configurados:");
        config.getDatabases().forEach(db ->
                System.out.printf("DB: %s | Host: %s | Database: %s%n",
                        db.getName(), db.getHost(), db.getDatabaseName()));

        return config;
    }
}