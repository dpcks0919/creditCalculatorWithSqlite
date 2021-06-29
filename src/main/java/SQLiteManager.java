import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteManager {

    //   - Database 변수
    private static final String SQLITE_JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String SQLITE_FILE_DB_URL = "jdbc:sqlite:person.db";
    //  - Database 옵션 변수
    private static final boolean OPT_AUTO_COMMIT = false;
    private static final int OPT_VALID_TIMEOUT = 500;
    //   - Database 접속 정보 변수
    private static Connection conn = null;

    // DB 연결 함수
    static Connection createConnection() {
        try {
            // JDBC Driver Class 로드
            Class.forName(SQLITE_JDBC_DRIVER);
            // DB 연결 객체 생성
            conn = DriverManager.getConnection(SQLITE_FILE_DB_URL);
            conn.setAutoCommit(OPT_AUTO_COMMIT);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // DB 연결 종료 함수
    static void closeConnection() {
        try {
            if( conn != null ) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    // DB 연결 객체 가져오기
    public static Connection getConnection() throws SQLException{

        if( conn == null || conn.isValid(OPT_VALID_TIMEOUT) ) {
            closeConnection();      // 연결 종료
            createConnection();     // 연결
        }

        return conn;
    }
}