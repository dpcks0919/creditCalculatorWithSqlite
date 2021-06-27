import java.sql.*;

public class DDLService extends SQLiteManager {
    // 생성자
    public DDLService() {
    }
    public DDLService(String url) {
        super(url);
    }

    // SQL 실행 함수
    public ResultType executeSQL(final String SQL) throws SQLException {
        // 변수설정
        //   - Database 변수
        Connection conn = null;
        Statement stmt = null;

        //   - 결과 변수
        ResultType result = ResultType.FAILURE;

        try {
            // Database 연결
            conn = ensureConnection();

            // Statement 객체  생성
            stmt = conn.createStatement();

            // SQL 실행
            stmt.execute(SQL);

            // 트랜잭션 COMMIT
            conn.commit();

            // 성공
            result = ResultType.SUCCESS;

        } catch (SQLException e) {
            // 오류출력
            System.out.println(e.getMessage());

            // 트랜잭션 ROLLBACK
            if( conn != null ) {
                conn.rollback();
            }

            // 오류
            result = ResultType.FAILURE;

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

    // 테이블 조회 함수
    public boolean checkTable(String tableName) throws SQLException {
        // 변수설정
        //   - Database 변수
        Connection conn = ensureConnection();
        DatabaseMetaData meta = conn.getMetaData();

        // 테이블 목록 조회
        ResultSet tables = meta.getTables(null, null, tableName, null);

        // 테이블 생성 확인
        return ( tables.next() ? tables.getRow() != 0 : false );
    }

    // 테이블 생성 함수
    public ResultType createTable(String tableName, final String SQL) throws SQLException {
        // 테이블 확인
        if( checkTable(tableName) ) {
            return ResultType.WARNING;
        }

        // SQL 실행 및 반환
        return executeSQL(SQL);
    }

    // 테이블 삭제 함수
    public ResultType dropTable(String tableName) throws SQLException {
        // 테이블 확인
        if( !checkTable(tableName) ) {
            return ResultType.WARNING;
        }

        // SQL 실행 및 반환
        return executeSQL("DROP TABLE IF EXISTS "+tableName);
    }

    // 결과 코드 정의
    public enum ResultType {
        SUCCESS(1),     // 성공
        WARNING(0),     // 경고
        FAILURE(-1);    // 실패

        // 코드 변수
        private int code = 0;

        // 코드값 설정
        private ResultType(int code){
            this.code = code;
        }

        // 코드값 가져오기
        public int getCode() {
            return this.code;
        }
    }
}
 