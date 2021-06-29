import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileService {

    public void saveFile(List<Map<String, Object>> list) {

        try {
            // fileUtils 사용
            File file = new File("data.txt");
            FileUtils.write(file, "", false);

            for(int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);

                String name = (String) map.get("NAME");
                int kor_score = Integer.parseInt(map.get("KOR_SCORE").toString());
                int eng_score = Integer.parseInt(map.get("ENG_SCORE").toString());
                int math_score = Integer.parseInt(map.get("MATH_SCORE").toString());
                String reg_date = (String) map.get("REG_DATE");

                int sum = kor_score + eng_score + math_score;

                FileUtils.write (file,name + " / " + kor_score + " / " +eng_score + " / " + math_score + " / " + reg_date + "\n", true);

            }

            // fileUtils 사용
        }catch (FileNotFoundException e) {
            System.out.println("data.txt 파일이 존재하지 않습니다.");
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
