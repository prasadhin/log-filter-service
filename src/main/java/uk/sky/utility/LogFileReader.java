package uk.sky.utility;

import uk.sky.model.ProcessedLogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class LogFileReader {
    public static List<ProcessedLogs> fetchLogs(Reader source) {

        List<ProcessedLogs> processedLogsList = new ArrayList<>();;
        String strLine;
        try(BufferedReader br = new BufferedReader(source)) {
            if (source != null) {
                while ((strLine = br.readLine()) != null) {
                    String[] entry = strLine.split(",");
                    processedLogsList.add(new ProcessedLogs(entry[0], entry[1], entry[2]));
                }
            }
            source.close();

        } catch (IOException e) { e.printStackTrace(); }
        if(processedLogsList.size()!=0) processedLogsList.remove(0);
        return processedLogsList;
    }
}
