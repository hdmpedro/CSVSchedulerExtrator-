/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hdmpedro.scheduler.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author DSK-11
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DatabaseConfig implements Serializable {
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "host")
    private String host;

    @XmlElement(name = "port")
    private int port;

    @XmlElement(name = "databaseName")
    private String databaseName;

    @XmlElement(name = "user")
    private String user;

    @XmlElement(name = "password")
    private String password;

    public DatabaseConfig() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String DatabaseName) {
        this.databaseName = DatabaseName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
