import java.sql.*;
import java.util.*;

public class DQLService extends SQLiteManager {

    // 생성자
    public DQLService() {

    }
    public DQLService(String url) {
        super(url);
    }

    // 데이터 조회 함수
    public List<Map<String, Object>> selectAll(){
        // 상수설정
        //   - SQL
        final String SQL = "SELECT *           "+"\n"
                + "  FROM PERSON "+"\n";

        //   - 조회 결과 변수
        final Set<String> columnNames = new HashSet<String>();
        final List<Map<String, Object>> selected = new ArrayList<Map<String, Object>>();

        // 변수설정
        //   - Database 변수
        Connection conn = createConnection();
        PreparedStatement pstmt = null;
        ResultSetMetaData meta = null;

        try {
            // PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(SQL);

            // 데이터 조회
            ResultSet rs = pstmt.executeQuery();

            // 조회된 데이터의 컬럼명 저장
            meta = pstmt.getMetaData();
            for(int i=1; i<=meta.getColumnCount(); i++) {
                columnNames.add(meta.getColumnName(i));
            }

            // ResultSet -> List<Map> 객체
            Map<String, Object> resultMap = null;

            while(rs.next()) {
                resultMap = new HashMap<String, Object>();

                for(String column : columnNames) {
                    resultMap.put(column, rs.getObject(column));
                }

                if( resultMap != null ) {
                    selected.add(resultMap);
                }
            }

        } catch (SQLException e) {
            // 오류처리
            System.out.println(e.getMessage());

        } finally  {
            try {
                // PreparedStatement 종료
                if( pstmt != null ) {
                    pstmt.close();
                }

                // Database 연결 종료
                closeConnection();

            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }

        // 결과 반환
        //   - 조회된 데이터 리스트
        return selected;
    }

    // 데이터 조회 함수
    public List<Map<String, Object>> selectByName(Map<String, Object> dataMap){
        // 상수설정
        //   - SQL
        final String SQL = "SELECT *           "+"\n"
                + "  FROM PERSON "+"\n"
                + " WHERE NAME = ?               "+"\n"
                ;

        //   - 조회 결과 변수
        final Set<String> columnNames = new HashSet<String>();
        final List<Map<String, Object>> selected = new ArrayList<Map<String, Object>>();

        // 변수설정
        //   - Database 변수
        Connection conn = createConnection();
        PreparedStatement pstmt = null;
        ResultSetMetaData meta = null;

        try {
            // PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(SQL);

            // 조회 데이터 조건 매핑
            pstmt.setObject(1, dataMap.get("NAME"));

            // 데이터 조회
            ResultSet rs = pstmt.executeQuery();

            // 조회된 데이터의 컬럼명 저장
            meta = pstmt.getMetaData();
            for(int i=1; i<=meta.getColumnCount(); i++) {
                columnNames.add(meta.getColumnName(i));
            }

            // ResultSet -> List<Map> 객체
            Map<String, Object> resultMap = null;

            while(rs.next()) {
                resultMap = new HashMap<String, Object>();

                for(String column : columnNames) {
                    resultMap.put(column, rs.getObject(column));
                }

                if( resultMap != null ) {
                    selected.add(resultMap);
                }
            }

        } catch (SQLException e) {
            // 오류처리
            System.out.println(e.getMessage());

        } finally  {
            try {
                // PreparedStatement 종료
                if( pstmt != null ) {
                    pstmt.close();
                }

                // Database 연결 종료
                closeConnection();

            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }

        // 결과 반환
        //   - 조회된 데이터 리스트
        return selected;
    }

    // 조회 결과 출력 함수
    public void printMapList(List<Map<String, Object>> mapList) {
        if( mapList.size() == 0 ) {
            System.out.println("조회된 데이터가 없습니다.");
            return;
        }

        // 상세 데이터 출력
        System.out.println("No Name Kor Eng Math Sum Avg Grade RegDate");
        System.out.println("==========================================");

        for(int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);


            int id = Integer.parseInt(map.get("ID").toString());
            String name = (String) map.get("NAME");
            int kor_score = Integer.parseInt(map.get("KOR_SCORE").toString());
            int eng_score = Integer.parseInt(map.get("ENG_SCORE").toString());
            int math_score = Integer.parseInt(map.get("MATH_SCORE").toString());
            String reg_date = (String) map.get("REG_DATE");

            int sum = kor_score + eng_score + math_score;
            double avg = sum / 3;
            String credit;

            if(avg >= 95){
                credit = "A+";
            }else if( avg >= 90 ){
                credit = "A";
            }else if( avg >= 85 ){
                credit = "B+";
            }else if( avg >= 80 ){
                credit = "B";
            }else if( avg >= 75 ){
                credit = "C+";
            }else if( avg >= 70 ){
                credit = "C";
            }else if( avg >= 65 ){
                credit = "D+";
            }else if( avg >= 60 ){
                credit = "D";
            }else{
                credit = "F";
            }

            System.out.println(id + ". " + name + "  " + kor_score + "  " + eng_score + "  " + math_score + "  " + sum + " " + avg + "  " + credit + "   " + reg_date);
        }
    }

    // 조회 결과 출력 함수
    public void printMapListByGrade(List<Map<String, Object>> mapList, String grade) {
        if( mapList.size() == 0 ) {
            System.out.println("조회된 데이터가 없습니다.");
            return;
        }

        // 상세 데이터 출력
        System.out.println("No Name Kor Eng Math Sum Avg Grade RegDate");
        System.out.println("==========================================");

        for(int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);


            int id = Integer.parseInt(map.get("ID").toString());
            String name = (String) map.get("NAME");
            int kor_score = Integer.parseInt(map.get("KOR_SCORE").toString());
            int eng_score = Integer.parseInt(map.get("ENG_SCORE").toString());
            int math_score = Integer.parseInt(map.get("MATH_SCORE").toString());
            String reg_date = (String) map.get("REG_DATE");

            int sum = kor_score + eng_score + math_score;
            double avg = sum / 3;
            String credit;

            if(avg >= 95){
                credit = "A+";
            }else if( avg >= 90 ){
                credit = "A";
            }else if( avg >= 85 ){
                credit = "B+";
            }else if( avg >= 80 ){
                credit = "B";
            }else if( avg >= 75 ){
                credit = "C+";
            }else if( avg >= 70 ){
                credit = "C";
            }else if( avg >= 65 ){
                credit = "D+";
            }else if( avg >= 60 ){
                credit = "D";
            }else{
                credit = "F";
            }

            if( credit.equals(grade) ){
                System.out.println(id + ". " + name + "  " + kor_score + "  " + eng_score + "  " + math_score + "  " + sum + " " + avg + "  " + credit + "   " + reg_date);
            }
        }
    }
}