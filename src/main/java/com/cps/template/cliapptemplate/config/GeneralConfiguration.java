package com.cps.template.cliapptemplate.config;

import com.cps.template.cliapptemplate.config.props.InfoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(InfoProperties.class)
public class GeneralConfiguration {
}
