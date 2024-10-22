package database;

public class Main {

    public static void main(String[] args) {
        // 1. MySQL 서버에 연결 (데이터베이스 없이)
        DatabaseConnector dbConnector = new DatabaseConnector();
        dbConnector.connectToServer();

        // 2. 데이터베이스 생성 (없으면 생성)
        DatabaseCreator dbCreator = new DatabaseCreator(dbConnector.getConnection(), dbConnector.getDatabaseName());
        dbCreator.createDatabaseIfNotExists();

        // 3. 데이터베이스에 다시 연결
        dbConnector.connectToDatabase();

        // 4. 테이블 생성
        TableCreator tableCreator = new TableCreator(dbConnector.getConnection());
        tableCreator.createTables();
        
       

        // 5. DB 연결 해제
        dbConnector.disconnect();
    }
}