# creditCalculatorWithSqlite

## 개발 step

1. 새 프로젝트 생성 (File > New > Project.. > Gradle > Java) 후 creditCalculatorWithFile 복사
  - https://mvnrepository.com에서 sqlite-jdbc 검색 후 버전 고르고 gradle 복사 --> build.gradle 파일에서 dependencies에 추가
  
  
  
  
  
 ## Sqlite 쿼리
  - 테이블명: PERSON
  - 칼럼 이름(타입) : ID(INTEGER), NAME(TEXT), KOR_SCORE(INTEGER), ENG_SCORE(INTEGER), MATH_SCORE(INTEGER), GRADE(TEXT), REG_DATE(TEXT)
  
  제약 조건(constrain)
  - PRIMARY KEY: 데이터를 유일(unique)하게 식별할수 있는 컬럼
  - AUTOINCREMENT: 데이터가 삽입될 때마다 1씩 증가시켜주는 칼럼, 데이터 삽입 시 해당 칼럼에 따로 값을 지정해주지 않아도 된다.
  - NOT NULL: NULL 값을 가질 수 없는 칼럼, 값을 꼭 지정해서 삽입해주어야 한다.
  
  1. DDL(Data Definition Language) - 데이터 정의 언어
  
  테이블을 생성, 수정, 삭제하는 언어
    
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
  
  데이터 검색 언어
  
  대부분 DML에 포함
    
    - 검색 쿼리     
    SELECT * FROM PERSON
    
    - 이름 검색 쿼리
    SELECT * FROM PERSON WHERE NAME = ?
    
  3. DML(Data Manipulation Language) - 데이터 조작 언어
  
  데이터 추가, 수정, 삭제 언어
  
    - 데이터 추가 쿼리    
      INSERT INTO PERSON ( NAME, KOR_SCORE, ENG_SCORE, MATH_SCORE, GRADE, REG_DATE) VALUES (?,?,?,?,?,?)
      
    - 데이터 수정 쿼리
      UPDATE PERSON SET NAME = ?, KOR_SCORE = ?, ENG_SCORE = ?, MATH_SCORE = ?, GRADE = ? WHERE ID = ?
    
    - 데이터 삭제 쿼리
      DELETE FROM PERSON WHERE ID = ?
