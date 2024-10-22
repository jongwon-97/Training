package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class LogAnalyzer {
	private Connection connection;

	public LogAnalyzer(Connection connection) {
		this.connection = connection;
	}

	// IP별 로그 데이터를 분석하고 ArrayList에 저장
	public ArrayList<LogData> analyzeLogs() {
		ArrayList<LogData> ipLogData = new ArrayList<>();
		String query = "SELECT c_ip, cs_method, sc_status, cs_uri_scheme FROM logs";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				String cIp = rs.getString("c_ip");
				String method = rs.getString("cs_method");
				int status = rs.getInt("sc_status");
				String uriScheme = rs.getString("cs_uri_scheme");

				// IP별 데이터 분석
				analyzeLogData(cIp, method, status, uriScheme, ipLogData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 요청 건수 많은 IP별로 정렬
		Collections.sort(ipLogData, (o1, o2) -> Integer.compare(o2.totalRequests, o1.totalRequests));
		return ipLogData;
	}

	// 로그 데이터를 분석하는 메소드
	private void analyzeLogData(String cIp, String method, int status, String uriScheme, ArrayList<LogData> ipLogData) {
		LogData logData = ipLogData.stream().filter(data -> data.cIp.equals(cIp)).findFirst().orElse(new LogData(cIp));

		logData.totalRequests++;

		// HTTP 메소드에 따른 요청 건수 증가
		if (method.equals("GET"))
			logData.totalGet++;
		else if (method.equals("POST"))
			logData.totalPost++;
		else if (method.equals("PUT"))
			logData.totalPut++;
		else if (method.equals("DELETE"))
			logData.totalDelete++;

		// 상태 코드가 200, 201이 아니면 에러 처리
		if (status != 200 && status != 201)
			logData.errorRate++;

		// 캐시 히트 처리
		if (uriScheme.equals("TCP_HIT") || uriScheme.equals("TCP_IMS_HIT") || uriScheme.equals("TCP_REFRESH_HIT")) {
			logData.cacheHitRate++;
		}

		// 에러율과 캐시 히트율 계산
		double error = logData.errorRate;
		double cash = logData.cacheHitRate;

		if (logData.totalRequests > 0) {
			double test = Math.round(error / logData.totalRequests * 1000);
			logData.errorRate = test / 10; // 에러율 계산

			test = Math.round(cash / logData.totalRequests * 1000);
			logData.cacheHitRate = test / 10; // 캐시 히트율 계산
		}

		if (!ipLogData.contains(logData)) {
			ipLogData.add(logData);
		}
	}

	// 분석된 로그 데이터를 파일로 저장하는 메소드
	public void saveAnalyzedLogsToFile(ArrayList<LogData> ipLogData, String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (LogData log : ipLogData) {
				writer.write(log.toString());
				writer.newLine();
			}
			System.out.println("분석된 로그 데이터가 파일에 저장되었습니다: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}