package org.spring_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.redis")
public class myJedisProperties {
    private String url;
    private String host;
    private String port;
    private String password;
}
