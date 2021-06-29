import java.sql.*;

public class DDLService {

    final String TABLE_NAME = "PERSON";

    final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +" (  "
            + "  ID  INTEGER  PRIMARY KEY  AUTOINCREMENT, "
            + "  NAME     TEXT     NOT NULL,  "
            + "  KOR_SCORE     INTEGER     NOT NULL, "
            + "  ENG_SCORE     INTEGER     NOT NULL, "
            + "  MATH_SCORE     INTEGER     NOT NULL, "
            + "  GRADE     TEXT     NOT NULL, "
            + "  REG_DATE    TEXT     NOT NULL  )";

//    final String DROP_SQL = "DROP TABLE IF EXISTS "+ TABLE_NAME ;

    Connection conn;

    public DDLService(Connection conn) {
        this.conn = conn;
    }

    // SQL 실행 함수
    public boolean executeSQL(final String SQL) throws SQLException {

        Statement stmt = null;
        //   - 결과 변수
        boolean result = false;

        try {
            // Statement 객체  생성
            stmt = conn.createStatement();

            // SQL 실행
            stmt.execute(SQL);

            // 트랜잭션 COMMIT
            conn.commit();

            // 성공
            result = true;

        } catch (SQLException e) {
            // 오류출력
            System.out.println(e.getMessage());
            // 트랜잭션 ROLLBACK
            if( conn != null ) {
                conn.rollback();
            }
            // 오류
            result = false;

        } finally {
            // Statement 종료
            if( stmt != null ) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 결과 반환
        return result;
    }

    // 테이블 생성 함수
    public boolean createTable() throws SQLException {
        // SQL 실행 및 반환
        return executeSQL(CREATE_SQL);
    }

//    // 테이블 삭제 함수
//    public boolean dropTable(String tableName) throws SQLException {
//
//        // SQL 실행 및 반환
//        return executeSQL(DROP_SQL);
//    }

}
 