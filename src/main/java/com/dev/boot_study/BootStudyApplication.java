package com.dev.boot_study;

import com.dev.boot_study.global.config.PrintBeanNamesUtil;
import com.dev.boot_study.global.config.profiles.ProfileManager;
import com.dev.boot_study.global.config.profiles.EnvConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude =
		{DataSourceAutoConfiguration.class} // 스프링부트 실행 시 DB config를 제외한다.
)
public class BootStudyApplication {

	private final Logger LOGGER = LoggerFactory.getLogger(BootStudyApplication.class);

	@Autowired
	public BootStudyApplication(EnvConfiguration envConfiguration, ProfileManager profileManager, PrintBeanNamesUtil printBeanNamesUtil){
		LOGGER.info(envConfiguration.getMessage());
		// Print current profile
		profileManager.printActiveProfiles();

		// Print Spring bean
		printBeanNamesUtil.printContextBeanNames();

		LOGGER.info(envConfiguration.getMessage());
	}

	public static void main(String[] args) {
		SpringApplication.run(BootStudyApplication.class, args);
	}

}
