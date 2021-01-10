package uk.sky.model;

/**
 * Class that represents an logs extracted and processed
 */
public class ProcessedLogs {

    //Fields
    private String requestTimeStamp;
    private String countryCode;
    private String responseTime;

    public ProcessedLogs(String requestTimeStamp, String countryCode, String responseTime) {
        this.requestTimeStamp = requestTimeStamp;
        this.countryCode = countryCode;
        this.responseTime = responseTime;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getResponseTime() {
        return responseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProcessedLogs)) return false;
        ProcessedLogs other = (ProcessedLogs)o;
        return other.requestTimeStamp.equals(requestTimeStamp) &&
                other.countryCode.equals(countryCode) &&
                other.responseTime.equals(responseTime);
    }
}
