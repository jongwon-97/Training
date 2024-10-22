package database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFileReader {
	private List<String> fields; // 필드명 저장

	public LogFileReader() {
		fields = new ArrayList<>();
	}

	// 로그 파일 읽기 및 필드명과 데이터 추출
	public List<LogEntry> readLogFile(String filePath) {
		List<LogEntry> logs = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				// #로 시작하는 주석 무시
				if (line.startsWith("#")) {
					// 필드명 추출
					if (line.startsWith("#Fields: ")) {
						String fieldsLine = line.substring(9); // "#Fields: " 이후 부분
						String[] fieldsArray = fieldsLine.split(" ");
						for (String field : fieldsArray) {
							fields.add(field);
						}
					}
					continue; // 주석은 무시하고 다음 라인으로
				}

				// 로그 데이터를 파싱하여 LogEntry 객체 생성
				String[] parts = line.split(" ");
				if (parts.length >= 10) { // 데이터가 충분한지 확인
					LogEntry logEntry = new LogEntry(parts[0], // date
							parts[1], // time
							Integer.parseInt(parts[2]), // time_taken
							parts[3], // c_ip
							Integer.parseInt(parts[4]), // sc_status
							parts[5], // s_action
							Integer.parseInt(parts[6]), // sc_bytes
							Integer.parseInt(parts[7]), // cs_bytes
							parts[8], // cs_method
							parts[9], // cs_uri_scheme
							parts.length > 10 ? parts[10] : "" // cs_host (존재하는 경우만)
					);
					logs.add(logEntry);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logs;
	}

	// 필드명을 반환하는 메서드
	public List<String> getFields() {
		return fields;
	}
}