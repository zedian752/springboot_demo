package org.spring_demo.config;


import lombok.Data;
import org.spring_demo.config.prefixConfig.whiteListConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Data
@EnableConfigurationProperties(whiteListConfig.class)
public class PropertyConfig {
    @Bean
    List<String> whiteListConfig(whiteListConfig whiteListConfig) {
        return whiteListConfig.getList();
    }
}
