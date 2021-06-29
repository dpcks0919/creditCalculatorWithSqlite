import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CreditCalculator {

    public static void main(String[] args) throws SQLException{

        Connection conn = SQLiteManager.getConnection();
        int result = -1;
        List<Map<String, Object>> resultList;

        Menu m = new Menu();
        CrudService crudService = new CrudService();
        DDLService DDL = new DDLService(conn);
        DMLService DML = new DMLService(conn);
        DQLService DQL = new DQLService(conn);
        FileService fileService = new FileService();

        DDL.createTable();

        System.out.println("-----시작-----");

        while(true) {
            try {
                String choose = m.printMenu();
                switch(choose){
                    case "1":
                        crudService.readData(DQL);
                        break;

                    case "2":
                        result = DML.insertPerson(crudService.createData());
                        if( result >= 0 ) {
                            System.out.println("추가되었습니다.");
                        } else {
                            System.out.println("데이터 입력 실패");
                        }

                        break;

                    case "3":
                        crudService.readData(DQL);

                        result = DML.updatePerson(crudService.updateData());
                        if( result >= 0 ) {
                            System.out.println("수정되었습니다.");
                        } else {
                            System.out.println("데이터 수정 실패");
                        }

                        break;

                    case "4":
                        crudService.readData(DQL);

                        result = DML.deletePerson(crudService.deleteData());
                        if( result >= 0 ) {
                            System.out.println("삭제되었습니다.");
                        } else {
                            System.out.println("데이터 삭제 실패");
                        }

                        break;

                    case "5":
                        resultList = DQL.selectByName(crudService.searchByName());
                        DQL.printMapList(resultList);
                        break;

                    case "6":
                        resultList = DQL.selectAll();
                        fileService.saveFile(resultList);
                        System.out.println("파일에 저장되었습니다.");
                        break;

                    case "0":
                        System.out.println("종료");
                        return;

                    default:
                        System.out.println("잘못된 메뉴 선택");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


