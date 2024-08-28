package com.dev.boot_study.web;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // 웹서버 실행 시 포트 번호를 랜덤으로 바꾼다.
public class ApiTestConfig {

    @LocalServerPort // 실행 중인 서버 포트 번호를 주입해 준다.
    int port;

    @BeforeEach
    void setup(){
//        RestAssured.baseURI = "https://api.github.com";
        RestAssured.port = port;
    }


}
