package database;

import java.util.List;

public class InsertMain {
    public static void main(String[] args) {
        // DB 연결 클래스
        DatabaseConnector dbConnector = new DatabaseConnector();
        dbConnector.connectToServer();  // 서버에 연결
        dbConnector.connectToDatabase();  // 데이터베이스에 연결

        // 로그 파일 경로
        String logFilePath = "C:\\Users\\user\\Desktop\\logs\\log.txt";  
        LogFileReader logFileReader = new LogFileReader();
        List<LogEntry> logEntries = logFileReader.readLogFile(logFilePath);

        // 데이터베이스에 로그 삽입
        LogInserter logInserter = new LogInserter(dbConnector.getConnection());
        logInserter.insertLogs(logEntries);

        // DB 연결 해제
        dbConnector.disconnect();
    }
}
