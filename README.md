# creditCalculatorWithSqlite

## 개발 step

1. 새 프로젝트 생성 (File > New > Project.. > Gradle > Java) 후 creditCalculatorWithFile 복사
    - https://mvnrepository.com 에서 sqlite-jdbc 검색 후 버전 고르고 gradle 복사 --> build.gradle 파일에서 dependencies에 추가

2. SQLiteManager를 통해 Connection 생성 후, DDLService(테이블 생성), DMLService(데이터 생성, 수정, 삭제), DQLService(데이터 조회)를 하나의 Connection으로 생성 (Singleton 방식)
3. ArrayList에 조회, 추가, 수정, 삭제 하는 부분을 DDLService, DMLSerive, DQLService에서 PreparedStatement 객체 생성 후 쿼리를 통해 조회, 추가, 수정, 삭제 대체
     * 참고 링크 ( PrepareStatement 사용 방법)
 
     https://sas-study.tistory.com/160
  
  ## Sqlite 쿼리
  - 테이블명: PERSON
  - 칼럼 이름(타입) : ID(INTEGER), NAME(TEXT), KOR_SCORE(INTEGER), ENG_SCORE(INTEGER), MATH_SCORE(INTEGER), GRADE(TEXT), REG_DATE(TEXT)
  
  제약 조건(constrain)
  - PRIMARY KEY: 데이터를 유일(unique)하게 식별할수 있는 컬럼
  - AUTOINCREMENT: 데이터가 삽입될 때마다 1씩 증가시켜주는 칼럼, 데이터 삽입 시 해당 칼럼에 따로 값을 지정해주지 않아도 된다.
  - NOT NULL: NULL 값을 가질 수 없는 칼럼, 값을 꼭 지정해서 삽입해주어야 한다.
  
  1. DDL(Data Definition Language) - 데이터 정의 언어
     * 테이블을 생성, 수정, 삭제하는 언어
    
    - 테이블 생성 쿼리
    CREATE TABLE IF NOT EXISTS PERSON (  
           ID  INTEGER  PRIMARY KEY  AUTOINCREMENT,
           NAME     TEXT     NOT NULL, 
           KOR_SCORE     INTEGER     NOT NULL, 
           ENG_SCORE     INTEGER     NOT NULL, 
           MATH_SCORE     INTEGER     NOT NULL, 
           GRADE     TEXT     NOT NULL, 
           REG_DATE    TEXT     NOT NULL  )
                   
    - 테이블 삭제 쿼리 
    DROP TABLE IF EXISTS TABLE
    
  2. DQL(Data Query Language) - 데이터 질의 언어
     * 데이터 검색 언어
     * 대부분 DML에 포함
    
    - 검색 쿼리     
    SELECT * FROM PERSON
    
    - 이름 검색 쿼리
    SELECT * FROM PERSON WHERE NAME = ?
    
  3. DML(Data Manipulation Language) - 데이터 조작 언어
     * 데이터 추가, 수정, 삭제 언어
  
    - 데이터 추가 쿼리    
      INSERT INTO PERSON ( NAME, KOR_SCORE, ENG_SCORE, MATH_SCORE, GRADE, REG_DATE) VALUES (?,?,?,?,?,?)
      
    - 데이터 수정 쿼리
      UPDATE PERSON SET NAME = ?, KOR_SCORE = ?, ENG_SCORE = ?, MATH_SCORE = ?, GRADE = ? WHERE ID = ?
    
    - 데이터 삭제 쿼리
      DELETE FROM PERSON WHERE ID = ?

  
  
 ## .db 파일 IntelliJ 연결 후 쿼리 방법
 1. Preference -> Plugins 에서 'Database Naviagator' 검색 후 설치
 <img width="986" alt="스크린샷 2021-06-29 오후 10 51 20" src="https://user-images.githubusercontent.com/47955992/123809391-864e5100-d92c-11eb-9aeb-5f14a56438aa.png">

2. View -> Tool Windows -> DB Browser 선택
<img width="511" alt="스크린샷 2021-06-29 오후 10 51 55" src="https://user-images.githubusercontent.com/47955992/123809488-9d8d3e80-d92c-11eb-8cd4-7f844dfadaae.png">

3. + 버튼을 누러 SQlite 선택
<img width="294" alt="스크린샷 2021-06-29 오후 10 53 48" src="https://user-images.githubusercontent.com/47955992/123809795-de855300-d92c-11eb-89bd-d2712e8e6196.png">

4. .db 파일을 경로 지정 후 우측 하단 OK 버튼 클릭
<img width="631" alt="스크린샷 2021-06-29 오후 10 55 08" src="https://user-images.githubusercontent.com/47955992/123810602-80a53b00-d92d-11eb-9928-3485e424ada0.png">

5. DB Browser 탭에서 연결된 Connection -> Consoles -> Connection 클릭
<img width="295" alt="스크린샷 2021-06-29 오후 11 00 19" src="https://user-images.githubusercontent.com/47955992/123810947-c82bc700-d92d-11eb-96ac-3ab9204aa429.png">

6. 우측 콘솔창에 쿼리문을 적고 초록색 실행 버튼(RUN) 클릭하면 아래 콘솔 창에 결과 출력
<img width="939" alt="스크린샷 2021-06-29 오후 11 03 04" src="https://user-images.githubusercontent.com/47955992/123811436-31abd580-d92e-11eb-8e1b-839aee8854f2.png">
