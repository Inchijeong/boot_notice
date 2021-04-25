# Notice



## 기능

1. 공지 사항
   * 목록 조회
   * 단일 조회
   * 수정
   * 삭제
2. 목록 페이징

※ 테스트 작성

## URL

* 목록 페이지
  * /notices 
* 상세 페이지
  * /notices/{id}
* 수정 페이지
  * /notices/edit/{id}



## API

* 목록 조회

  ```
  HTTP GET /api/v1/notices
  ```

* 단일 조회

  ````
  HTTP GET /api/v1/notices/{id}
  ````

* 등록

  ```
  HTTP POST /api/v1/notices/{id}
  ```

* 수정

  ```
  HTTP PUT /api/v1/notices/{id}
  ```

* 삭제

  ```
  HTTP DELETE /api/v1/notices/{id}
  ```



## 환경

* Open JDK 11
* SpringBoot 2.4.5
* H2
* JPA
* Thymeleaf
* Gradle



## 빌드 및 실행

1. Git 설치 (Windows의 경우 [Git 설치 및 초기 설정](https://poetic-code.tistory.com/79?category=752161) 참고)

   ```
   yum install git
   ```

2. 소스 받기

   ```
   git clone https://github.com/Inchijeong/boot_notice.git
   ```

3. build

   ```
   cd ./boot_notice
   ```

   ```
   ./gradlew bootWar
   ```

4. 폴더 이동

   ```
   cd ./build/libs
   ```

5. 내장 Tomcat 실행

   ```
   java -jar notice-0.0.1-SNAPSHOT.war
   ```

   