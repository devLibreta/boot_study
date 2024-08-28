package com.dev.boot_study.java;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CompareToTest {

    // Comparable 인터페이스를 구현한 클래스 테스트

    @Test
    @DisplayName("BigDecimal.compareTo : A < B")
    public void test01() {
        // GIVEN
        BigDecimal arg1 = new BigDecimal("20240801");
        BigDecimal arg2 = new BigDecimal("20240831");

        // WHEN
        int rslt = arg1.compareTo(arg2);

        // THEN
        assertThat(rslt).isEqualTo(-1); // 20240801 < 20240831
    }

    @Test
    @DisplayName("BigDecimal.compareTo : A = B")
    public void test02() {
        // GIVEN
        BigDecimal arg1 = new BigDecimal("20240801");
        BigDecimal arg2 = new BigDecimal("20240801");

        // WHEN
        int rslt = arg1.compareTo(arg2);

        // THEN
        assertThat(rslt).isEqualTo(0);
    }

    @Test
    @DisplayName("BigDecimal.compareTo : A > B")
    public void test03() {
        // GIVEN
        BigDecimal arg1 = new BigDecimal("20240831");
        BigDecimal arg2 = new BigDecimal("20240801");

        // WHEN
        int rslt = arg1.compareTo(arg2);

        // THEN
        assertThat(rslt).isEqualTo(1);
    }

    @Test
    @DisplayName("String.compareTo : A < B")
    public void test04() {
        // GIVEN
        String arg1 = new String("a");
        String arg2 = new String("z");

        // WHEN
        int rslt = arg1.compareTo(arg2);

        // THEN
        assertThat(rslt).isLessThan(0);
    }
    @Test
    @DisplayName("Integer.compareTo : A < B")
    public void test05() {
        // GIVEN
        Integer arg1 = 1;
        Integer arg2 = 10;

        // WHEN
        int rslt = arg1.compareTo(arg2);

        // THEN
        assertThat(rslt).isEqualTo(-1);
    }
    @Test
    @DisplayName("Integer.compareTo : A = B")
    public void test06() {
        // GIVEN
        Integer arg1 = 10;
        Integer arg2 = 10;

        // WHEN
        int rslt = arg1.compareTo(arg2);

        // THEN
        assertThat(rslt).isEqualTo(0);
    }
    @Test
    @DisplayName("Integer.compareTo : A > B")
    public void test07() {
        // GIVEN
        Integer arg1 = 10;
        Integer arg2 = 1;

        // WHEN
        int rslt = arg1.compareTo(arg2);

        // THEN
        assertThat(rslt).isEqualTo(1);
    }

}
