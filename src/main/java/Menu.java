import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Menu {
    BufferedReader br;

    private DDLService DDL = new DDLService("jdbc:sqlite:person.db");
    private DMLService DML = new DMLService("jdbc:sqlite:person.db");
    private DQLService DQL = new DQLService("jdbc:sqlite:person.db");

    public Menu() {
    }

    public void printMenu() {
        System.out.println("---menu---");
        System.out.println("1. 조회");
        System.out.println("2. 추가");
        System.out.println("3. 수정");
        System.out.println("4. 삭제");
        System.out.println("5. 이름 검색");
        System.out.println("6. 학점 검색");
        System.out.println("7. 파일 저장하기");
        System.out.println("0. 종료");
        System.out.println("----------");
    }

    public boolean menuChoose(String input) throws SQLException {
        switch (input) {

            case "1":
                readData();
                break;

            case "2":
                createData();
                break;

            case "3":
                updateData();
                break;

            case "4":
                deleteData();
                break;

            case "5":
                searchByName();
                break;

            case "6":
                searchByGrade();
                break;

            case "7":
                saveFile();
                break;

            case "0":
                System.out.println("종료");
                return false;
            default:
                System.out.println("잘못된 메뉴 선택");
        }
        return true;
    }

    // 테이블 생성 함수
    public void createTable() throws SQLException {
        final String SQL = "CREATE TABLE IF NOT EXISTS PERSON (     "+"\n"
                + "  ID     INTEGER     PRIMARY KEY     AUTOINCREMENT ,     "+"\n"
                + "  NAME     TEXT     NOT NULL,                "+"\n"
                + "  KOR_SCORE     INTEGER     NOT NULL,        "+"\n"
                + "  ENG_SCORE     INTEGER     NOT NULL,        "+"\n"
                + "  MATH_SCORE     INTEGER     NOT NULL,        "+"\n"
                + "  REG_DATE    TEXT     NOT NULL     "+"\n"
                + "  )";

        // 테이블 생성
        DDLService.ResultType result = DDL.createTable("PERSON", SQL);

        // 테이블 생성 결과 출력
        switch( result ) {
            case SUCCESS:
                //System.out.println("테이블 생성 완료.");
                break;
            case WARNING:
                //System.out.println("테이블이 이미 존재합니다.");
                break;
            case FAILURE:
                System.out.println("테이블 생성 실패.");
                break;
        }

        // DB 연결 종료
        DDL.closeConnection();
    }

    // 테이블 삭제 함수
    public void dropTable() throws SQLException {

        // 테이블 삭제
        DDLService.ResultType result = DDL.dropTable("PERSON");

        // 테이블 삭제 결과 출력
        switch( result ) {
            case SUCCESS:
                //System.out.println("테이블 삭제 완료.");
                break;
            case WARNING:
                //System.out.println("테이블이 존재하지 않습니다.");
                break;
            case FAILURE:
                System.out.println("테이블 삭제 실패.");
                break;
        }

        // DB 연결 종료
        DDL.closeConnection();
    }

    private void saveFile() {

        try {
            File file = new File("data.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            List<Map<String, Object>> result = DQL.selectAll();
            for(int i = 0; i < result.size(); i++) {
                Map<String, Object> map = result.get(i);
                int id = Integer.parseInt(map.get("ID").toString());
                String name = (String) map.get("NAME");
                int kor_score = Integer.parseInt(map.get("KOR_SCORE").toString());
                int eng_score = Integer.parseInt(map.get("ENG_SCORE").toString());
                int math_score = Integer.parseInt(map.get("MATH_SCORE").toString());
                String reg_date = (String) map.get("REG_DATE");

                bw.write(name + " / " + kor_score + " / " + eng_score + " / " + math_score + " / " + reg_date);
                bw.newLine();
            }

            bw.close();
            System.out.println("파일에 저장되었습니다.");

            readData();

        }catch (FileNotFoundException e) {
            System.out.println("data.txt 파일이 존재하지 않습니다.");
        }catch(IOException e){
            System.out.println(e);
        }
    }

    private void createData() throws SQLException {

        final Map<String, Object> dataMap = new HashMap<String, Object>();

        try {
            System.out.println("이름 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            dataMap.put("NAME"   , br.readLine());
            System.out.println("국어 성적 입력");
            dataMap.put("KOR_SCORE"   , Integer.parseInt(br.readLine()));
            System.out.println("영어 성적 입력");
            dataMap.put("ENG_SCORE" , Integer.parseInt(br.readLine()));
            System.out.println("수학 성적 입력");
            dataMap.put("MATH_SCORE" , Integer.parseInt(br.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String regDate = date.format(formatter);
        dataMap.put("REG_DATE"   , regDate);

        // 데이터 입력
        int inserted = DML.insertPerson(dataMap);

        if( inserted >= 0 ) {
            System.out.println(String.format("데이터 입력 성공: %d건", inserted));
        } else {
            System.out.println("데이터 입력 실패");
        }
    }

    private void searchByGrade(){
        System.out.println("검색할 학점 입력");
        br = new BufferedReader(new InputStreamReader(System.in));

        try{
            String grade = br.readLine();
            List<Map<String, Object>> result = DQL.selectAll();
            DQL.printMapListByGrade(result, grade);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchByName(){

        System.out.println("검색할 이름 입력");
        br = new BufferedReader(new InputStreamReader(System.in));

        try{
            String name = br.readLine();

            //   - 조회할 데이터
            final Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("NAME"   , name);

            // 데이터 조회
            List<Map<String, Object>> result = DQL.selectByName(dataMap);

            // 조회 결과 출력
            DQL.printMapList(result);


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData() {
        List<Map<String, Object>> result = DQL.selectAll();

        // 조회 결과 출력
        DQL.printMapList(result);
    }

    private void updateData() throws SQLException {

        try {
            readData();
            System.out.println("수정할 번호 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            final Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("ID"   , Integer.parseInt(br.readLine()));

            final Map<String, Object> updateMap = new HashMap<String, Object>();

            System.out.println("이름 입력");
            updateMap.put("NAME" , br.readLine());
            System.out.println("국어 성적 입력");
            updateMap.put("KOR_SCORE" , Integer.parseInt(br.readLine()));
            System.out.println("영어 성적 입력");
            updateMap.put("ENG_SCORE" , Integer.parseInt(br.readLine()));
            System.out.println("수학 성적 입력");
            updateMap.put("MATH_SCORE" , Integer.parseInt(br.readLine()));

            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String regDate = date.format(formatter);
            updateMap.put("REG_DATE"   , regDate);

            int updated = DML.updatePerson(dataMap, updateMap);
            if( updated >= 0 ) {
                System.out.println(String.format("데이터 수정 성공: %d건", updated));
            } else {
                System.out.println("데이터 수정 실패");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteData() throws SQLException {

        try {
            readData();
            System.out.println("삭제할 번호 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            final Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("ID"   , Integer.parseInt(br.readLine()));

            // 데이터 삭제
            int deleted = DML.deletePerson(dataMap);
            if( deleted >= 0 ) {
                System.out.println(String.format("데이터 삭제 성공: %d건", deleted));
            } else {
                System.out.println("데이터 삭제 실패");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}