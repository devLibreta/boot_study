package com.dev.boot_study.global.config;

import com.dev.boot_study.global.config.profiles.ProfileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Stream;

@Component
public class PrintBeanNamesUtil {

    private final Logger LOGGER = LoggerFactory.getLogger(PrintBeanNamesUtil.class.getSimpleName());

    private ApplicationContext applicationContext;

    @Autowired
    public PrintBeanNamesUtil(ApplicationContext applicationContext){
        this.applicationContext=applicationContext;
    }

    public void printContextBeanNames(){
        Stream<String> beanNames = Arrays.stream(applicationContext.getBeanDefinitionNames());
        beanNames.forEach( beanName -> LOGGER.debug("[printContextBeanNames] " + beanName));
    }
}
