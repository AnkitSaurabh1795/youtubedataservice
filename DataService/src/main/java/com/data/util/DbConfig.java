package com.data.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "db-config")
public class DbConfig {
    private String uri;
    private String dbname;


}
