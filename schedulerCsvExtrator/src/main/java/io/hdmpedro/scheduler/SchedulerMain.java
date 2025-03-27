package io.hdmpedro.scheduler;

import io.hdmpedro.scheduler.config.SchedulerConfig;
import io.hdmpedro.scheduler.config.XmlConfigLoader;
import io.hdmpedro.scheduler.dao.DatabaseManager;
import io.hdmpedro.scheduler.tasks.ExportTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.ArrayList;
import java.util.List;

public class SchedulerMain {

    public static void main(String[] args) throws Exception {
        SchedulerConfig config = XmlConfigLoader.loadConfig("config.xml");
        DatabaseManager.initialize(config.getDatabases());

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("configPath", "config.xml");

        JobDetail job = JobBuilder.newJob(ExportTask.class)
                .withIdentity("exportJob")
                .usingJobData(jobDataMap)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(config.getSchedule().getCronExpression()))
                .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}

