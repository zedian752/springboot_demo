package org.spring_demo.config.prefixConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


@Data
@ConfigurationProperties(prefix = "service.whitelist")
public class whiteListConfig {
    private List<String> list;


}
