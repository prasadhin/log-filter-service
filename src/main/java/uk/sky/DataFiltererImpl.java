package uk.sky;

import uk.sky.model.ProcessedLogs;
import uk.sky.utility.LogFileReader;

import java.io.Reader;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *Implementation for DataFilter Interface
 */

public class DataFiltererImpl implements DataFilterer {

    /**
     *Implementation for Filtering logs for a specific country
     */
    @Override
    public  Collection<? extends ProcessedLogs> filterByCountry(Reader source, String country) {
        List<ProcessedLogs> inputLogsList = LogFileReader.fetchLogs(source);
        List<ProcessedLogs> processedLogsList = inputLogsList.stream()
                .filter(log -> log.getCountryCode().equals(country))
                .collect(Collectors.toList());
        return processedLogsList;
    }

    /**
     *Implementation for Filtering logs for a specific country, with response time above a specific limit
     */
    @Override
    public Collection<? extends ProcessedLogs> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
        List<ProcessedLogs> inputLogsList = LogFileReader.fetchLogs(source);
        List<ProcessedLogs> processedLogsList = inputLogsList.stream()
                .filter(log -> log.getCountryCode().equals(country))
                .filter(log -> LocalTime.ofSecondOfDay(Long.parseLong(log.getResponseTime()))
                        .isAfter(LocalTime.ofSecondOfDay(limit)))
                .collect(Collectors.toList());
        return processedLogsList;
    }

    /**
     *Implementation for Filtering logs for a specific country, with response time above the avergae time
     * Uses filter methods and aggregate functions
     */
    @Override
    public Collection<? extends ProcessedLogs> filterByResponseTimeAboveAverage(Reader source) {
        List<ProcessedLogs> inputLogsList = LogFileReader.fetchLogs(source);
        long averageResponseTime = (long) inputLogsList.stream().mapToLong(log -> Long.parseLong(log.getResponseTime()))
                .average().getAsDouble();
        List<ProcessedLogs> processedLogsList = inputLogsList.stream()
                .filter(log -> LocalTime.ofSecondOfDay(Long.parseLong(log.getResponseTime()))
                        .isAfter(LocalTime.ofSecondOfDay(averageResponseTime)))
                .collect(Collectors.toList());
        return processedLogsList;
    }

}
