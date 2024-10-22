package database;

public class LogData {
	String cIp; // 클라이언트 IP
	String fileName; // 파일명 (IP 기반 파일명)
	int totalGet; // GET 요청 수
	int totalPost; // POST 요청 수
	int totalPut; // PUT 요청 수
	int totalDelete; // DELETE 요청 수
	int totalRequests; // 총 요청 수
	double errorRate; // 에러율
	double cacheHitRate;// 캐시 히트율

	// 생성자
	public LogData(String cIp) {
		this.cIp = cIp;
		this.fileName = cIp + ".txt"; // IP 기반 파일명 생성
		this.totalGet = 0;
		this.totalPost = 0;
		this.totalPut = 0;
		this.totalDelete = 0;
		this.totalRequests = 0;
		this.errorRate = 0;
		this.cacheHitRate = 0;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %d, %d, %d, %d, %d, %.1f, %.1f", cIp, fileName, totalRequests, totalGet,
				totalPost, totalPut, totalDelete, errorRate, cacheHitRate);
	}
}