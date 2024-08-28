package com.dev.boot_study.web;


import io.restassured.RestAssured;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CookieTest {

    /**
     * jakarta.servlet.http.Cookie
     * 쿠키의 값을 설정, 읽기 메소드에 대해 알아보는 테스트이다..
     */

    private final Logger LOGGER = LoggerFactory.getLogger(CookieTest.class.getName());
    private final String cookieName = "CK";
    private final String cookieValue = "쿠키값";
    private final String encodedValue =  URLEncoder.encode(cookieValue,"utf-8"); // %EC%BF%A0%ED%82%A4%EA%B0%92
    Cookie cookie = null;

    CookieTest() throws UnsupportedEncodingException {
    }

    @BeforeEach
    void setup(){
        cookie = new Cookie(cookieName, cookieValue);
        LOGGER.info("기본 쿠키 생성됨");
    }

    @Test
    @DisplayName("기본 쿠키 name, value 읽기 테스트")
    void test1() throws UnsupportedEncodingException {
        String name = cookie.getName();
        String value = cookie.getValue();
        LOGGER.info("name : {}",name); // CK
        LOGGER.info("value : {}",value); // 쿠키값
    }

    @Test
    @DisplayName("쿠키 value 인코딩 한 값 설정/읽기 테스트")
    void test2() throws UnsupportedEncodingException {
        cookie.setValue(encodedValue);
        LOGGER.info("쿠키에 인코딩된 값 설정됨");
        String name = cookie.getName(); // CK
        String value = cookie.getValue(); // %EC%BF%A0%ED%82%A4%EA%B0%92
        LOGGER.info("name : {}",name);
        LOGGER.info("encodedValue : {}",value);

        String decodedValue = URLDecoder.decode(value,"utf-8");
        LOGGER.info("decodedValue : {}",decodedValue);

        assertThat(decodedValue).isEqualTo(cookieValue);
    }

    @Test
    @DisplayName("쿠키 전송도메인, 전송경로, 유효시간(초) 설정/읽기 테스트")
    void test3(){
        cookie.setDomain("www.naver.com"); // 특정 도메인 연결 시에만 쿠키를 전송한다.
//        cookie.setDomain(".naver.com"); // naver.com 모든 도메인 연결 시 쿠키를 전송한다.
        /*
             경로는 도메인 이후의 부분을 말한다. 해당 path 연결 시에만 쿠키를 전송한다.
             일반적으로 쿠키는 웹 어플리케이션 전체에서 사용할 수 있기에 경로를 "/"로 지정한다.
         */
        cookie.setPath("/");
        cookie.setMaxAge(5); // 유효시간. 미 지정 시 웹브라우저 종료 시 쿠기를 삭제한다. 초 단위로 지정한다.

        String domain = cookie.getDomain();
        String path = cookie.getPath();
        int maxAge = cookie.getMaxAge();

        LOGGER.info("domain : {}", domain);
        LOGGER.info("path : {}", path);
        LOGGER.info("maxAge : {}", maxAge);
    }

    @Test
    @DisplayName("응답에 쿠키를 추가한다")
    void test4(){
        HttpServletResponse httpServletResponse = new MockHttpServletResponse(); // 아무 구현체

        httpServletResponse.addCookie(cookie); // 쿠키를 응답에 추가한다.
    }
}