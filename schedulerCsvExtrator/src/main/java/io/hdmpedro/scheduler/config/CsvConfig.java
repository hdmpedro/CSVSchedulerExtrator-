package io.hdmpedro.scheduler.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class CsvConfig implements Serializable {
    @XmlElement(name = "output-path")
    private String outputPath;

    @XmlElement(name = "file-name")
    private String fileName;

    @XmlElement(name = "delimiter")
    private char delimiter;

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOutputPath() { return outputPath; }
    public String getFileName() { return fileName; }
    public char getDelimiter() { return delimiter; }
}
