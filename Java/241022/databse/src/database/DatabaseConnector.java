package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConnector {

	private String user = "root"; // 사용자명 고정
	private String password = "mysql904#"; // 비밀번호 입력받기
	private String databaseName; // 데이터베이스 이름 입력받기
	private String url; // 데이터베이스 URL
	private Connection connection; // MySQL 연결 객체
	private Scanner sc; // 스캐너 추가

	// MySQL 서버에 연결 (데이터베이스 없이)
	public void connectToServer() {
		sc = new Scanner(System.in); // 스캐너 초기화

//		// 비밀번호 입력받기
//		System.out.print("MySQL 비밀번호를 입력해주세요: ");
//		password = sc.nextLine();

		// 데이터베이스 이름 입력받기
		System.out.print("연결할 데이터베이스 이름을 입력해주세요: ");
		databaseName = sc.nextLine();

		// URL 설정 (데이터베이스 없이 서버에 연결)
		url = "jdbc:mysql://localhost:3306?serverTimezone=UTC";

		try {
			// MySQL 서버에 연결 (데이터베이스 없이)
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("MySQL 서버에 연결 성공");

		} catch (SQLException e) {
			System.out.println("MySQL 서버 연결 실패");
			e.printStackTrace();
		}
	}

	// 데이터베이스에 연결하는 메서드 (생성 후)
	public void connectToDatabase() {
		url = "jdbc:mysql://localhost:3306/" + databaseName + "?serverTimezone=UTC";
		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("데이터베이스 '" + databaseName + "'에 연결 성공");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		}
	}

	// DB 연결 해제 메서드
	public void disconnect() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("DB 연결이 해제되었습니다.");
			}
		} catch (SQLException e) {
			System.out.println("DB 연결 해제 중 오류 발생");
			e.printStackTrace();
		} finally {
			// 스캐너 닫기
			if (sc != null) {
				sc.close();
			}
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public String getDatabaseName() {
		return databaseName;
	}
}