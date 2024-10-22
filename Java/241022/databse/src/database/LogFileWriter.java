package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogFileWriter {
	private Connection connection;

	public LogFileWriter(Connection connection) {
		this.connection = connection;
	}

	// c_ip별로 데이터를 파일에 저장
	public void writeLogsByCIP(String outputFolderPath) {
		// DISTINCT를 사용해 c_ip 목록을 가져옴
		String distinctCIPQuery = "SELECT DISTINCT c_ip FROM logs";
		try (PreparedStatement pstmt = connection.prepareStatement(distinctCIPQuery);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				String c_ip = rs.getString("c_ip");
				writeLogsForCIP(c_ip, outputFolderPath);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 특정 c_ip에 대한 데이터를 파일로 저장
	private void writeLogsForCIP(String c_ip, String outputFolderPath) {
		String query = "SELECT * FROM logs WHERE c_ip = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, c_ip);
			try (ResultSet rs = pstmt.executeQuery()) {
				String outputFilePath = outputFolderPath + "/" + c_ip + ".txt";
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
					while (rs.next()) {
						String logLine = String.format("%s %s %d %s %d %s %d %d %s %s %s\n", rs.getString("date"),
								rs.getString("time"), rs.getInt("time_taken"), rs.getString("c_ip"),
								rs.getInt("sc_status"), rs.getString("s_action"), rs.getInt("sc_bytes"),
								rs.getInt("cs_bytes"), rs.getString("cs_method"), rs.getString("cs_uri_scheme"),
								rs.getString("cs_host"));
						writer.write(logLine);
					}
					System.out.println(c_ip + "로그 파일 생성 완료: " + outputFilePath);
				} catch (IOException e) {
					System.out.println("파일 쓰기 중 오류 발생: " + e.getMessage());
				}
			}

		} catch (SQLException e) {
			System.out.println("DB 조회 중 오류 발생: " + e.getMessage());
		}
	}
}