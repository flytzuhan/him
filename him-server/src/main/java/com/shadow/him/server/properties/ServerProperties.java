package com.shadow.him.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "im.server")
@Data
public class ServerProperties {

    private Integer port;
}
