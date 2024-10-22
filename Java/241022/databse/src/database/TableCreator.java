package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TableCreator {

	private Connection connection;
	private Scanner sc;

	public TableCreator(Connection connection) {
		this.connection = connection;
		sc = new Scanner(System.in); // 스캐너 초기화
	}

	public void createTables() {
		System.out.print("생성할 테이블 개수를 입력해주세요: ");
		int tableCount = sc.nextInt();
		sc.nextLine(); // 개행문자 처리

		for (int i = 1; i <= tableCount; i++) {
			System.out.print("테이블 " + i + "의 이름을 입력해주세요: ");
			String tableName = sc.nextLine();

			String createTableSQL;
			if (i == 1) {
				createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
						+ "date VARCHAR(50), " + "time VARCHAR(50), "+"time_taken INT, "+"c_ip VARCHAR(50), " +"sc_status INT, "
						+ "s_action VARCHAR(50), " + "sc_bytes INT, " + "cs_bytes INT, " +"cs_method VARCHAR(50), " 
						+ "cs_uri_scheme VARCHAR(50), "+ "cs_host VARCHAR(50)" + ")";
			} else if (i == 2) {
				createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
						+ "date VARCHAR(50), " + "time VARCHAR(50), "+"time_taken INT, "+"c_ip VARCHAR(50), " +"sc_status INT, "
						+ "s_action VARCHAR(50), " + "sc_bytes INT, " + "cs_bytes INT, " +"cs_method VARCHAR(50), " 
						+ "cs_uri_scheme VARCHAR(50), "+ "cs_host VARCHAR(50)" + ")";
			} else {
				createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
						+ "date VARCHAR(50), " + "time VARCHAR(50), "+"time_taken INT, "+"c_ip VARCHAR(50), " +"sc_status INT, "
						+ "s_action VARCHAR(50), " + "sc_bytes INT, " + "cs_bytes INT, " +"cs_method VARCHAR(50), " 
						+ "cs_uri_scheme VARCHAR(50), "+ "cs_host VARCHAR(50)" + ")";
			}

			try {
				Statement statement = connection.createStatement();
				statement.executeUpdate(createTableSQL);
				System.out.println("테이블 '" + tableName + "' 생성 완료");
				statement.close();
			} catch (SQLException e) {
				System.out.println("테이블 생성 실패");
				e.printStackTrace();
			}
		}

		sc.close();
	}
}