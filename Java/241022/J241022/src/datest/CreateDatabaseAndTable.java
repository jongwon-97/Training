package datest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateDatabaseAndTable {

	public static void main(String[] args) {
		// MySQL 데이터베이스 URL, 사용자명, 비밀번호 설정
		String url = "jdbc:mysql://localhost:3306?serverTimezone=UTC"; // 데이터베이스 없이 연결
		String user = "root";
		String password = "mysql904#";

		Scanner sc = new Scanner(System.in);

		try {
			// MySQL JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");

			// MySQL에 연결
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("MySQL에 연결 성공");

			// 데이터베이스 이름 입력받기
			System.out.print("생성할 DB명을 입력해주세요: ");
			String dbName = sc.nextLine();

			// 데이터베이스 생성 SQL
			String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + dbName;
			Statement statement = conn.createStatement();
			statement.executeUpdate(createDatabaseSQL);
			System.out.println("데이터베이스 '" + dbName + "' 생성 완료");

			// 데이터베이스에 연결
			String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=UTC";
			Connection dbConn = DriverManager.getConnection(dbUrl, user, password);

			// 생성할 테이블 개수 입력받기
			System.out.print("생성할 테이블 갯수를 입력해주세요: ");
			int tableCount = sc.nextInt();
			sc.nextLine(); // 개행문자 처리

			for (int i = 1; i <= tableCount; i++) {
				System.out.print("테이블 " + i + "의 이름을 입력해주세요: ");
				String tableName = sc.nextLine();

				// 미리 정의된 필드명과 타입을 적용
				String createTableSQL;

				if (i == 1) {
					// 첫 번째 테이블의 필드명 설정
					createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
							+ "employee_id INT AUTO_INCREMENT PRIMARY KEY, " + "name VARCHAR(50) NOT NULL, "
							+ "position VARCHAR(50), " + "salary DECIMAL(10, 2)" + ")";
				} else if (i == 2) {
					// 두 번째 테이블의 필드명 설정
					createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
							+ "department_id INT AUTO_INCREMENT PRIMARY KEY, "
							+ "department_name VARCHAR(100) NOT NULL, " + "location VARCHAR(50)" + ")";
				} else {
					// 그 외의 테이블 필드 설정 (필요시 더 추가)
					createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
							+ "id INT AUTO_INCREMENT PRIMARY KEY, " + "name VARCHAR(50), " + "description VARCHAR(100)"
							+ ")";
				}

				// SQL 실행
				Statement dbStatement = dbConn.createStatement();
				dbStatement.executeUpdate(createTableSQL);
				System.out.println("테이블 '" + tableName + "' 생성 완료");
				dbStatement.close();
			}

			// 연결 종료
			statement.close();
			dbConn.close();
			conn.close();
			System.out.println("DB연동 종료");

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("MySQL 연결 또는 SQL 실행 오류");
			e.printStackTrace();
		}

		sc.close();
	}
}
