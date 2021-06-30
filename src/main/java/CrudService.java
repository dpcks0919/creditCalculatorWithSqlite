import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudService {

    BufferedReader br;

    public void readData(DQLService dql) {
        List<Map<String, Object>>  resultList = dql.selectAll();
        dql.printMapList(resultList);
    }

    public HashMap<String, Object> createData() {

        int num;
        String name;
        int korScore;
        int engScore;
        int mathScore;
        String grade;
        String regDate;

        final HashMap<String, Object> dataMap = new HashMap<String, Object>();

        try {
            System.out.println("이름 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            name = br.readLine();
            dataMap.put("NAME"   , name);
            System.out.println("국어 성적 입력");
            korScore = Integer.parseInt(br.readLine());
            dataMap.put("KOR_SCORE"   , korScore);
            System.out.println("영어 성적 입력");
            engScore = Integer.parseInt(br.readLine());
            dataMap.put("ENG_SCORE" , engScore);
            System.out.println("수학 성적 입력");
            mathScore = Integer.parseInt(br.readLine());
            dataMap.put("MATH_SCORE" , mathScore);
            grade = Person.calculateGrade(korScore, engScore, mathScore);
            dataMap.put("GRADE" , grade);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        regDate = date.format(formatter);

        dataMap.put("REG_DATE" , regDate);

        return dataMap;
    }

    public HashMap<String, Object> updateData() {

        int num;
        String name;
        int korScore;
        int engScore;
        int mathScore;
        String grade;
        String regDate;

        final HashMap<String, Object> updateMap = new HashMap<String, Object>();

        try {
            System.out.println("수정할 번호 입력");
            br = new BufferedReader(new InputStreamReader(System.in));

            num = Integer.parseInt(br.readLine());
            updateMap.put("updateID" , num);

            System.out.println("이름 입력");
            name = br.readLine();
            updateMap.put("NAME"   , name);
            System.out.println("국어 성적 입력");
            korScore = Integer.parseInt(br.readLine());
            updateMap.put("KOR_SCORE"   , korScore);

            System.out.println("영어 성적 입력");
            engScore = Integer.parseInt(br.readLine());
            updateMap.put("ENG_SCORE" , engScore);

            System.out.println("수학 성적 입력");
            mathScore = Integer.parseInt(br.readLine());
            updateMap.put("MATH_SCORE" , mathScore);

            grade = Person.calculateGrade(korScore, engScore, mathScore);
            updateMap.put("GRADE" , grade);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return updateMap;
    }

    public int deleteData() {

        int num = 0;

        try {
            System.out.println("삭제할 번호 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            num = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return num;
    }

}
