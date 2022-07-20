package com.cp.template.mavenprojecttemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppRunner {
    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

    public static void main(String[] args) {
        log.info("{}", "Hello!");
    }
}
