package com.dev.boot_study.web;

import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

@Slf4j
class CookiesTest extends ApiTestConfig {

    /**
     * Cookies 유틸리티 클래스를 활용해서 쿠키를 생성하거나 쿠키정보를 얻는 테스트
     */

    Cookie cookie, cookie1 = null; // restAssured 패키지 내의 쿠키 클래스를 사용해야 한다.

    @BeforeEach
    void setupCookies() throws UnsupportedEncodingException {
        cookie = new Cookie
            .Builder("some_cookie", "some_value")
            .setMaxAge(100)
            .setPath("/test")
            .setSecured(true)
            .build();
        cookie1 = new Cookie
            .Builder("some_cookie1", "some_value1")
            .setMaxAge(100)
            .setPath("/test")
            .setSecured(true)
            .build();
    }

    @Test
    @DisplayName("API 호출 후 쿠키가 있는지 확인하는 테스트")
    void test1(){

        ExtractableResponse<Response> res =
            RestAssured
                .given().cookie(cookie).cookie(cookie1)
                .and().log().all()
                .when().get("/test")
                .then().statusCode(404)
                .and().log().all()
                .extract();
        // 미완성
    }

}