package database;

public class LogEntry {
	private String date;
	private String time;
	private int timeTaken;
	private String clientIp;
	private int statusCode;
	private String cacheResult;
	private int scBytes;
	private int csBytes;
	private String httpMethod;
	private String uriScheme;
	private String host;

	public LogEntry(String date, String time, int timeTaken, String clientIp, int statusCode, String cacheResult,
			int scBytes, int csBytes, String httpMethod, String uriScheme, String host) {
		this.date = date;
		this.time = time;
		this.timeTaken = timeTaken;
		this.clientIp = clientIp;
		this.statusCode = statusCode;
		this.cacheResult = cacheResult;
		this.scBytes = scBytes;
		this.csBytes = csBytes;
		this.httpMethod = httpMethod;
		this.uriScheme = uriScheme;
		this.host = host;
	}
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public String getClientIp() {
        return clientIp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getCacheResult() {
        return cacheResult;
    }

    public int getScBytes() {
        return scBytes;
    }

    public int getCsBytes() {
        return csBytes;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getUriScheme() {
        return uriScheme;
    }

    public String getHost() {
        return host;
    }
}