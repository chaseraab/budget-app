package com.budget_app.csvHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVReader {

    public CSVReader() {}

    public List<String> ReadCSV(String fileLocation) {
        try {
            BufferedReader bfro = new BufferedReader(new FileReader(fileLocation));
            String curLine;
            List<String> content = new ArrayList<String>();
            while ((curLine = bfro.readLine()) != null) {content.add(curLine);}
            return content.subList(1, content.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
