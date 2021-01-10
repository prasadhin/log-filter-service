package uk.sky;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.sky.model.ProcessedLogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *Implementation for DataFilter Interface
 * Contains given when then approach for hooking into test suits later.
 */
class DataFiltererImplTest {

    private DataFilterer dataFilterer;
    private List<ProcessedLogs> processedLogsList;

    @BeforeEach
    void setUp() {
        dataFilterer = new DataFiltererImpl();
        processedLogsList = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        processedLogsList = null;
    }

    /**
     *Test to ensure methods for log filters by country wise
     */
    @Test
    void filterByCountryProducesLogs() throws FileNotFoundException {
        // Given input files with log records
        //when logs are to be filtered Country wise
        processedLogsList = (List<ProcessedLogs>) dataFilterer
                .filterByCountry(openFile("src/test/resources/sample-extract"), "GB");
        //Then ensure logs of matching country is returned
        assertEquals(expectedLogsForCountry(),processedLogsList);
    }

    /**
     *Test to ensure methods for log filters by country wise works for empty logs input file.
     */
    @Test
    void filterByCountryForNoLogs() throws FileNotFoundException {
        // Given input files with log records
        //when logs are to be filtered Country wise, but no matching record exist
        processedLogsList = (List<ProcessedLogs>) dataFilterer
                .filterByCountry(openFile("src/test/resources/sample-extract"), "IR");
        //Then ensure no logs are returned
        assertTrue(processedLogsList.isEmpty());
    }

    /**
     *Test to ensure methods for log filters by country wise works for non matching records.
     */
    @Test
    void filterByCountryForNoRecordLogs() throws FileNotFoundException {
        // Given input files with no log records
        //when logs are to be filtered Country wise, since input file has no logs
        processedLogsList = (List<ProcessedLogs>) dataFilterer
                .filterByCountry(openFile("src/test/resources/zero-logs"), "GB");
        //Then ensure no logs are returned
        assertTrue(processedLogsList.isEmpty());
    }

    /**
     *Test to ensure methods for log filters by country wise and records with more response time.
     */
    @Test
    void filterByCountryWithResponseTimeAboveLimit() throws FileNotFoundException {
        // Given input files with log records
        //when logs are to be filtered Country wise and above response time
        processedLogsList = (List<ProcessedLogs>) dataFilterer
                .filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/sample-extract"), "US",30);
        //Then ensure logs are returned for countries with more response time
        assertEquals(expectedLogsResponseTimeAboveLimit(),processedLogsList);
    }

    /**
     * Test to ensure methods for log filters by country wise and records with response time more than avergae time.
     */
    @Test
    void filterByResponseTimeAboveAverage() throws FileNotFoundException {
        // Given input files with log records
        //when logs are to be filtered Country wise and above averge time
        processedLogsList = (List<ProcessedLogs>) dataFilterer
                .filterByResponseTimeAboveAverage(openFile("src/test/resources/sample-extract"));
        //Then ensure logs are returned for countries with response more than average time
        assertEquals(expectedLogsResponseTimeAboveAvg(),processedLogsList);

    }

    /**
     *Test for invalid input file and exception handling
     */
    @Test
    void filterByCountryForInvalidFile() throws FileNotFoundException {
        //when logs are to be filtered Country wise
        //Then ensure exception is thrwon
        Assertions.assertThrows(FileNotFoundException.class, () -> {dataFilterer
                .filterByCountry(openFile("src/test/resources/sample-extracts"), "GB");
        });
    }

    /**
     * Utility to provide Reader
     */
    private FileReader openFile(String file) throws FileNotFoundException {
        return new FileReader(new File(file));
    }

    /**
     * Utility to provide mock data as expected logs.
     */
    private  List<ProcessedLogs> expectedLogsForCountry(){
        List<ProcessedLogs> mockDta = new ArrayList<>();
        mockDta.add(new ProcessedLogs("1432917066","GB","37"));
        return mockDta;
    }

    private  List<ProcessedLogs> expectedLogsResponseTimeAboveLimit(){
        return getMockData();
    }

    private  List<ProcessedLogs> expectedLogsResponseTimeAboveAvg(){
        return getMockData();
    }

    /**
     * Utility to provide mock data as expected logs.
     */
    private List<ProcessedLogs> getMockData(){
        List<ProcessedLogs> mockDta = new ArrayList<>();
        mockDta.add(new ProcessedLogs("1433190845","US","539"));
        mockDta.add(new ProcessedLogs("1433666287","US","789"));
        mockDta.add(new ProcessedLogs("1432484176","US","850"));
        return mockDta;
    }
}