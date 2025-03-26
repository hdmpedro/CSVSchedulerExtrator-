/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hdmpedro.scheduler.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DSK-11
 */
public class CsvWriter {

    public static void export(ResultSet rs, String filePath, char delimiter) throws IOException, SQLException {
        export(rs, filePath, delimiter, true, StandardCharsets.UTF_8, "", "\r\n");
    }

    public static void export(ResultSet rs, String filePath, char delimiter, boolean includeHeader, Charset charset)
            throws IOException, SQLException {
        export(rs, filePath, delimiter, includeHeader, charset, "", "\r\n");
    }

    public static void export(ResultSet rs, String filePath, char delimiter, boolean includeHeader, Charset charset,
                              String nullValue, String lineSeparator) throws IOException, SQLException {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), charset))) {

            if (StandardCharsets.UTF_8.equals(charset)) {
                writer.write("\uFEFF");
            }

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            if (includeHeader) {
                StringBuilder headerBuilder = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    if (i > 1) {
                        headerBuilder.append(delimiter);
                    }
                    headerBuilder.append(escapeCsv(meta.getColumnLabel(i), delimiter));
                }
                headerBuilder.append(lineSeparator);
                writer.write(headerBuilder.toString());
            }

            while (rs.next()) {
                StringBuilder rowBuilder = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    if (i > 1) {
                        rowBuilder.append(delimiter);
                    }
                    String value = rs.getString(i);
                    if (value == null) {
                        value = nullValue;
                    }
                    rowBuilder.append(escapeCsv(value, delimiter));
                }
                rowBuilder.append(lineSeparator);
                writer.write(rowBuilder.toString());
            }
            writer.flush();
        }
    }

    private static String escapeCsv(String value, char delimiter) {
        boolean needsQuotes = value.chars()
                .anyMatch(c -> c == delimiter || c == '"' || c == '\n' || c == '\r');
        if (needsQuotes) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
