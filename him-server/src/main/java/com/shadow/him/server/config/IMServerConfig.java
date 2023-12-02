package com.shadow.him.server.config;

import com.shadow.him.server.properties.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ServerProperties.class)
public class IMServerConfig {

    @Autowired
    private ServerProperties serverProperties;

    public ServerProperties getServerProperties() {
        return serverProperties;
    }
}
