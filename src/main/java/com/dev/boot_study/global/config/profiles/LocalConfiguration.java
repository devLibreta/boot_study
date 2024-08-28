package com.dev.boot_study.global.config.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local") //실행 프로파일이 local 일 경우에만 실행한 Bean 을 설정한다.
@Configuration
public class LocalConfiguration implements EnvConfiguration{
    private final Logger LOGGER = LoggerFactory.getLogger(LocalConfiguration.class.getSimpleName());

    @Value("#{environment['env.loading.message']}") // 환경 프로퍼티에서 메시지를 가져온다.
    private String message;

    @Override
    public String getMessage() {
//        LOGGER.info("[getMessage] LocalConfiguration ON");
        return message;
    }
}
