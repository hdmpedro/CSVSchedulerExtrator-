/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hdmpedro.scheduler.tasks;

import io.hdmpedro.scheduler.config.CsvConfig;
import io.hdmpedro.scheduler.config.QueryConfig;
import io.hdmpedro.scheduler.config.SchedulerConfig;
import io.hdmpedro.scheduler.config.XmlConfigLoader;
import io.hdmpedro.scheduler.dao.DatabaseManager;
import io.hdmpedro.scheduler.util.CsvWriter;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import javax.management.Query;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DSK-11
 */
public class ExportTask implements Job {
    private static final Logger logger = Logger.getLogger(ExportTask.class.getName());

    //String path = "c:\\Users\\DSK11\\Desktop\\schedulerCsvExtrator\\target";
    //CsvConfig csvN = new CsvConfig(path);

    @Override
    public void execute(JobExecutionContext context) {
        try {
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            String configPath = dataMap.getString("configPath");
            SchedulerConfig config = XmlConfigLoader.loadConfig(configPath);

            for (QueryConfig query : config.getQueries()) {
                processQuery(query);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro fatal na execução da tarefa", e);
        }
    }

    private void processQuery(QueryConfig query) {
        try (Connection conn = DatabaseManager.getConnection(query.getDatabaseRef());
             PreparedStatement stmt = conn.prepareStatement(query.getSql());
             ResultSet rs = stmt.executeQuery()) {

            CsvConfig csv = query.getCsv();
            Path outputPath = Paths.get(csv.getOutputPath(), csv.getFileName());

            Files.createDirectories(outputPath.getParent());
            CsvWriter.export(rs, outputPath.toString(), csv.getDelimiter());

            logger.info(() -> {
                try {
                    return String.format("Exportado %d registros para: %s",
                            getRowCount(rs), outputPath);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha na query: " + query.getSql(), e);
        }
    }

    private int getRowCount(ResultSet rs) throws SQLException {
        int count = 0;
        if (rs.last()) {
            count = rs.getRow();
            rs.beforeFirst();
        }
        return count;
    }
}
