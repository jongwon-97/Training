package database;

import java.io.File;

public class FileWritterMain {
	public static void main(String[] args) {
        // DB 연결 클래스
        DatabaseConnector dbConnector = new DatabaseConnector();
        dbConnector.connectToServer();  // 서버에 연결
        dbConnector.connectToDatabase();  // 데이터베이스에 연결

        // logs 폴더 경로 (파일 저장할 경로 설정)
        String outputFolderPath = "C:\\Users\\user\\Desktop\\logs\\ip별 로그 목록";
        File outputFolder = new File(outputFolderPath);
        if (!outputFolder.exists()) {
            outputFolder.mkdir();  // 출력 폴더가 없으면 생성
        }

        // c_ip별로 로그를 파일로 저장
        LogFileWriter logFileWriter = new LogFileWriter(dbConnector.getConnection());
        logFileWriter.writeLogsByCIP(outputFolderPath);

        // DB 연결 해제
        dbConnector.disconnect();
    }
}
