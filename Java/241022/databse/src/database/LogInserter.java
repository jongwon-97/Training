package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LogInserter {
    private Connection connection;

    public LogInserter(Connection connection) {
        this.connection = connection;
    }

    public void insertLogs(List<LogEntry> logEntries) {
        String sql = "INSERT INTO logs (date, time, time_taken, c_ip, sc_status, s_action, sc_bytes, cs_bytes, cs_method, cs_uri_scheme, cs_host) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (LogEntry log : logEntries) {
                pstmt.setString(1, log.getDate());
                pstmt.setString(2, log.getTime());
                pstmt.setInt(3, log.getTimeTaken());
                pstmt.setString(4, log.getClientIp());
                pstmt.setInt(5, log.getStatusCode());
                pstmt.setString(6, log.getCacheResult());
                pstmt.setInt(7, log.getScBytes());
                pstmt.setInt(8, log.getCsBytes());
                pstmt.setString(9, log.getHttpMethod());
                pstmt.setString(10, log.getUriScheme());
                pstmt.setString(11, log.getHost());

                pstmt.addBatch();  // 배치에 추가
            }

            pstmt.executeBatch();  // 배치 실행
            System.out.println("로그 데이터 삽입 완료");
        } catch (SQLException e) {
            e.printStackTrace();  // 오류 상세 정보 출력
        }
    }
}