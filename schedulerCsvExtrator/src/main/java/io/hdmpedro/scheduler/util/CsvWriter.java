/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hdmpedro.scheduler.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 *
 * @author DSK-11
 */
public class CsvWriter {
    public static void export(ResultSet rs, String filePath, char delimiter) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                writer.write(meta.getColumnName(i));
                if (i < columnCount) writer.write(delimiter);
            }
            writer.newLine();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String value = rs.getString(i);
                    writer.write(value != null ? value : "");
                    if (i < columnCount) writer.write(delimiter);
                }
                writer.newLine();
            }
        }
    }
}
