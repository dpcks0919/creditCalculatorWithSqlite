import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class creditCalculator {

    public static void main(String[] args) throws SQLException{

        Menu m = new Menu();
        boolean check = true;

        m.createTable();
        System.out.println("-----시작-----");

        while (check) {
            try {
                m.printMenu();
                BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in)); // Using BufferedReader
                String input = sbr.readLine();
                check = m.menuChoose(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}


