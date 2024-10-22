package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreator {

    private Connection connection;
    private String databaseName;

    public DatabaseCreator(Connection connection, String databaseName) {
        this.connection = connection;
        this.databaseName = databaseName;
    }

    public void createDatabaseIfNotExists() {
        try {
            // 데이터베이스가 없을 경우 생성
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(createDatabaseSQL);

            if (result == 1) {
                System.out.println("데이터베이스 '" + databaseName + "'가 생성되었습니다.");
            } else {
                System.out.println("데이터베이스 '" + databaseName + "'가 이미 존재합니다.");
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("데이터베이스 생성 실패");
            e.printStackTrace();
        }
    }
}