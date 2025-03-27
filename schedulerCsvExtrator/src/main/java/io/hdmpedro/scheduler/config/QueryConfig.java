package io.hdmpedro.scheduler.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class QueryConfig implements Serializable {
    @XmlElement(name = "database-ref")
    private String databaseRef;

    @XmlElement(name = "sql")
    private String sql;

    @XmlElement(name = "csv")
    private CsvConfig csv;

    public String getDatabaseRef() { return databaseRef; }
    public String getSql() { return sql; }
    public CsvConfig getCsv() { return csv; }
}
