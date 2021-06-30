# creditCalculatorWithSqlite

## 개발 step

1. 새 프로젝트 생성 (File > New > Project.. > Gradle > Java) 후 creditCalculatorWithFile 복사
    - https://mvnrepository.com 에서 sqlite-jdbc 검색 후 버전 고르고 gradle 복사 --> build.gradle 파일에서 dependencies에 추가

2. SQLiteManager를 통해 Connection 생성 후, DDLService(테이블 생성), DMLService(데이터 생성, 수정, 삭제), DQLService(데이터 조회)를 하나의 Connection으로 생성 (Singleton 방식)
3. ArrayList에 조회, 추가, 수정, 삭제 하는 부분을 DDLService, DMLSerive, DQLService에서 PreparedStatement 객체 생성 후 쿼리를 통해 조회, 추가, 수정, 삭제 대체
     * 참고 링크 ( PrepareStatement 사용 방법)

       https://sas-study.tistory.com/160
       
 
  ## 실행 결과
  
  메뉴
  
  <img width="209" alt="스크린샷 2021-06-30 오후 2 59 23" src="https://user-images.githubusercontent.com/47955992/123910335-42ebf500-d9b5-11eb-911f-ba1fda02088b.png">

  1. CREATE 기능
  
  <img width="169" alt="스크린샷 2021-06-24 오후 10 01 49" src="https://user-images.githubusercontent.com/47955992/123267267-c7fb8800-d537-11eb-96d2-08782471160c.png">
  
  2. READ 기능
  
  <img width="416" alt="스크린샷 2021-06-26 오후 10 56 31" src="https://user-images.githubusercontent.com/47955992/123515289-c06fe680-d6d1-11eb-849a-1303b7202369.png">
  
  3. Update 기능
  
  <img width="412" alt="스크린샷 2021-06-26 오후 10 57 08" src="https://user-images.githubusercontent.com/47955992/123515309-d67da700-d6d1-11eb-8500-9470e31ae608.png">
  
  - 수정 후
  
  <img width="400" alt="스크린샷 2021-06-26 오후 10 57 28" src="https://user-images.githubusercontent.com/47955992/123515317-e2696900-d6d1-11eb-8bbb-2989b1a52ecc.png">

  4. Delete 기능
  
  <img width="414" alt="스크린샷 2021-06-26 오후 10 57 50" src="https://user-images.githubusercontent.com/47955992/123515325-f01eee80-d6d1-11eb-8e54-06d58924b0ac.png">
  
  - 삭제 후
 
  <img width="405" alt="스크린샷 2021-06-26 오후 10 58 08" src="https://user-images.githubusercontent.com/47955992/123515336-fb721a00-d6d1-11eb-8ebd-5d5a78b3837a.png">
  
  5. 이름 검색 기능 생성 
  
  <img width="399" alt="스크린샷 2021-06-24 오후 10 05 52" src="https://user-images.githubusercontent.com/47955992/123267777-5839cd00-d538-11eb-9b13-7a60d9fb4caa.png">
  
  6. 파일 불러오기 기능
  - 프로그램 시작하면 바로 data.txt 파일 불러온 후 리스트에 추가 (StringTokenizer 사용)
  - buffereReader, FileReader 등 사용
  - commons-io 라이브러리 FileUtils 사용 (maven repository에서 commons-io 검색 후 build.gradle에 dependency 추가)

  <img width="342" alt="스크린샷 2021-06-26 오후 11 02 40" src="https://user-images.githubusercontent.com/47955992/123515499-9c60d500-d6d2-11eb-9d95-403252628734.png">
  
  <img width="415" alt="스크린샷 2021-06-26 오후 11 03 04" src="https://user-images.githubusercontent.com/47955992/123515515-ab478780-d6d2-11eb-8dc9-abfc6e41a047.png">
  
  7. 파일 저장하기 기능 추가
  - 리스트에 있는 데이터들 data.txt 파일 저장하기
  - BufferedWriter, FileWriter 등 사용
  - commons-io 라이브러리 FileUtils 사용 (maven repository에서 commons-io 검색 후 build.gradle에 dependency 추가)
  - FileWriter 사용
 
  <img width="423" alt="스크린샷 2021-06-26 오후 11 04 22" src="https://user-images.githubusercontent.com/47955992/123515547-d92ccc00-d6d2-11eb-99d3-a5cf27762a2f.png">

  <img width="325" alt="스크린샷 2021-06-26 오후 11 04 49" src="https://user-images.githubusercontent.com/47955992/123515573-e9dd4200-d6d2-11eb-9626-66786a0fb31c.png">


  
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
