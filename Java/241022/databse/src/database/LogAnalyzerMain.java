package database;

import java.util.ArrayList;

public class LogAnalyzerMain {
	public static void main(String[] args) {
		// DB 연결 클래스
		DatabaseConnector dbConnector = new DatabaseConnector();
		dbConnector.connectToServer(); // 서버에 연결
		dbConnector.connectToDatabase(); // 데이터베이스에 연결

		// 로그 분석 클래스
		LogAnalyzer logAnalyzer = new LogAnalyzer(dbConnector.getConnection());

		// 로그 데이터를 분석하고 결과를 가져옴
		ArrayList<LogData> analyzedLogData = logAnalyzer.analyzeLogs();

		// 분석된 데이터를 파일에 저장
		String filePath = "C:\\Users\\user\\Desktop\\logs\\analyzed_logs.txt"; // 저장할 파일 경로
		logAnalyzer.saveAnalyzedLogsToFile(analyzedLogData, filePath);

		// DB 연결 해제
		dbConnector.disconnect();
	}
}
