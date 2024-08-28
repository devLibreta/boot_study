package com.dev.boot_study.global.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent> {

    // 참고 Class
    // ServletWebServerFactoryAutoConfiguration : servlet web servers 의 Auto-configuration 을 담당하는 클래스.

    private final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class.getSimpleName());
    
    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        // 웹앱이 웹서버 서블릿과 연결되는 이벤트 발생 시 처리하는 메서드
        ServletWebServerApplicationContext ac = event.getApplicationContext();
        LOGGER.info("[onApplicationEvent] =================> 실행됨");


    }
}
