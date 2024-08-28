package com.dev.boot_study.web;

import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class RestAssuredControllerTest extends ApiTestConfig {


    /* path variables */

    @Test
    @DisplayName("single pathParam 테스트")
    void test1(){
        RestAssured
            .given().pathParam("userName", "홍길동")
            .and().log().all()
            .when().get("/restAssured/pathParam/{userName}")
            .then().statusCode(200)
            .and().log().all();
    }

    @Test
    @DisplayName(" multiple pathParams pathParam() 테스트")
    void test2(){
        RestAssured
            .given().pathParams("userName", "홍길동","userSex", "men")
            .and().log().all()
            .when().get("/restAssured/pathParams/{userName}/{userSex}")
            .then().statusCode(200)
            .and().log().all();
    }

    @Test
    @DisplayName(" multiple pathParams pathParams() 테스트")
    void test3(){
        RestAssured
            .given().pathParams("userName", "홍길동")
            .and().log().all()
            .when().get("/restAssured/pathParams/{userName}/{userSex}", "userSex")
            .then().statusCode(200)
            .and().log().all();
    }

    /* query parameters */

    @Test
    @DisplayName("single query parameter 테스트")
    void test4(){
        RestAssured
            .given().queryParam("userName", "홍길동")
            .and().log().all()
            .when().get("/restAssured/queryParameter")
            .then().statusCode(200)
            .and().log().all();
    }

    @Test
    @DisplayName("multiple query parameters queryParam() 테스트")
    void test5(){
        RestAssured
            .given().queryParam("userName", "홍길동").queryParam("userSex","men")
            .and().log().all()
            .when().get("/restAssured/queryParameters")
            .then().statusCode(200)
            .and().log().all();
    }

    @Test
    @DisplayName("multiple query parameters queryParams() 테스트")
    void test6(){
        RestAssured
            .given().queryParams("userName", "홍길동","userSex","men")
            .and().log().all()
            .when().get("/restAssured/queryParameters")
            .then().statusCode(200)
            .and().log().all();
    }

    /* form parameters */

    @Test
    @DisplayName("multiple form parameters formParams() 테스트")
    void test7(){
        RestAssured
            .given().formParams("userName", "홍길동","userSex","men")
            // 기본 인코딩이 ISO 설정이기에 UTF-8로 설정해줘야 톰캣이 한글이 깨지지 않고 인식한다.
            .and().contentType("application/x-www-form-urlencoded; charset=UTF-8") // 기본 인코딩은 ISO
            .and().log().all()
            .when().post("/restAssured/formParameters")
            .then().log().all();
    }

    @Test
    @DisplayName("multiple form parameters params() 테스트")
    void test8(){
        RestAssured
            .given().params("userName", "홍길동","userSex","men")
            // 기본 인코딩이 ISO 설정이기에 UTF-8로 설정해줘야 톰캣이 한글이 깨지지 않고 인식한다.
            .and().contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .and().log().all()
            .when().post("/restAssured/formParameters")
            .then().log().all();
    }

    /* setting headers */

    @Test
    @DisplayName("single header value setting header() 테스트")
    void test9(){
        RestAssured
            .given().header("header1", "val1")
            .and().log().all()
            .when().get("/restAssured/header")
            .then().statusCode(200)
            .and().log().all();
    }

    @Test
    @DisplayName("multiple header values setting header() 테스트")
    void test10(){
        RestAssured
            .given().header("header1", "val1", "val2","val3")
            .and().log().all()
            .when().get("/restAssured/headerValues")
            .then().statusCode(200)
            .and().log().all();
    }

    @Test
    @DisplayName("multiple headers setting headers() 테스트")
    void test11(){
        RestAssured
            .given().headers("header1", "val1", "header2","val2")
            .and().log().all()
            .when().get("/restAssured/headers")
            .then().statusCode(200)
            .and().log().all();
    }

    @Test
    @DisplayName("cookies cookie() 테스트")
    void test12(){
        Cookie cookie = new Cookie.Builder("ck1","ckValue")
            .setSecured(true)
            .setMaxAge(1000)
            .setComment("this is comment")
            .build();
        Cookie cookie2 = new Cookie.Builder("ck2","ckValue")
            .setSecured(true)
            .setMaxAge(1000)
            .setComment("this is comment")
            .build();

        RestAssured
            .given().cookie(cookie).cookie(cookie2)
            .and().log().all()
            .when().get("/restAssured/cookies")
            .then().statusCode(200)
            .and().log().all();
    }








}