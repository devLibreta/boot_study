package com.dev.boot_study.junit;

import org.junit.jupiter.api.*;

// Application Context Annotation.
//@ExtendWith(SpringExtension.class) // junit4 @RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration( // junit4 No change, @Bean config file.
//        { "/app-config.xml", "/test-data-access-config.xml" }
//)
//@Import({app-config.class})
public class JUnitTest {
    @Test // junit5
    @DisplayName("샘플 테스트1")
    public void expectedException(){
        // param1: Exception.class
        // param2: Executable code
        // param3: Fails with the supplied failure message.
        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("One");
        }, "NumberFormatException was expected");
        Assertions.assertEquals("For input string: \"One\"", thrown.getMessage());
    }
    @Test
    @Disabled // 테스트가 실행되지 않게 설정한다.
    public void disabledTest(){

    }

    @BeforeAll // junit4 @BeforeClass
    public static void setupClassBefore(){
        System.out.println("=============================================");
        System.out.println("@BeforeAll 은 무조건 static 으로 만들어야 합니다.");
        System.out.println("@BeforeAll 은 현 클래스 실행 전 실행 됩니다.");
        System.out.println("@BeforeAll 은 클래스 내 @Test 메소드가 여러개라도 한번만 실행 됩니다.");
        System.out.println("=============================================");
    }
    @BeforeEach // junit4 @Before
    public void setupBefore(){
        System.out.println("=============================================");
        System.out.println("@BeforeEach 은 static 이 아니어도 됩니다.");
        System.out.println("@BeforeEach 은 클래스 내부 테스트 메소드 실행 전 실행 됩니다.");
        System.out.println("@BeforeEach 은 @BeforeAll 다음에 실행됩니다.");
        System.out.println("@BeforeEach 은 클래스 내 @Test 메소드가 여러개라면 여러번 실행 됩니다.");
        System.out.println("=============================================");
    }
    @AfterAll // junit4 @AfterClass
    public static void setupClassAfter(){
        System.out.println("=============================================");
        System.out.println("@AfterAll 은 무조건 static 으로 만들어야 합니다.");
        System.out.println("@AfterAll 은 현 클래스 실행 후 실행 됩니다.");
        System.out.println("@AfterAll 은 클래스 내 @Test 메소드가 여러개라도 한번만 실행 됩니다.");
        System.out.println("=============================================");
    }
    @AfterAll // junit4 @After
    public static void setupAfter(){
        System.out.println("=============================================");
        System.out.println("@AfterEach 은 static 이 아니어도 됩니다.");
        System.out.println("@AfterEach 은 클래스 내부 테스트 메소드 실행 후 실행 됩니다.");
        System.out.println("@AfterEach 은 @AfterAll 다음에 실행됩니다.");
        System.out.println("@AfterAll 은 클래스 내 @Test 메소드가 여러개라면 여러번 실행 됩니다.");
        System.out.println("=============================================");
    }
}
