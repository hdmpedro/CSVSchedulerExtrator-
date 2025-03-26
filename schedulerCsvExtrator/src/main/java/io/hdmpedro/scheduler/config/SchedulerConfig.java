/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hdmpedro.scheduler.config;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 *
 * @author DSK-11
 */
@XmlRootElement(name = "scheduler-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class SchedulerConfig {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "host")
    private String host;

    @XmlElement(name = "cron-expression")
    private String cronExpression;

    @XmlElementWrapper(name = "databases")
    @XmlElement(name = "database")
    private List<DatabaseConfig> databases;

    @XmlElement(name = "schedule")
    private SchedulerConfig schedule;

    @XmlElementWrapper(name = "queries")
    @XmlElement(name = "query")
    private List<QueryConfig> queries;

    public void setDatabases(List<DatabaseConfig> databases) {
        this.databases = databases;
    }

    public void setQueries(List<QueryConfig> queries) {
        this.queries = queries;
    }

    public void setSchedule(SchedulerConfig schedule) {
        this.schedule = schedule;
    }

    public List<DatabaseConfig> getDatabases() { return databases; }
    public SchedulerConfig getSchedule() { return schedule; }
    public List<QueryConfig> getQueries() { return queries; }

    public String getCronExpression() {
        return cronExpression;
    }

    
}