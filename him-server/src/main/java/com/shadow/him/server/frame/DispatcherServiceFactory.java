package com.shadow.him.server.frame;

import com.shadow.him.server.frame.type.ActionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DispatcherServiceFactory implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherServiceFactory.class);

    @Autowired
    private List<DispatcherService> dispatcherServiceList;

    private Map<ActionType, DispatcherService> dispatcherServiceMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Start dispatcher service transfer");
        if (CollectionUtils.isEmpty(dispatcherServiceList)) {
            dispatcherServiceMap = Collections.emptyMap();
        } else {
            dispatcherServiceMap = dispatcherServiceList.stream()
                    .collect(Collectors.toMap(DispatcherService::getActionType, Function.identity()));
        }
    }

    public DispatcherService getDispatcherService(ActionType type) {
        DispatcherService service = dispatcherServiceMap.getOrDefault(type, null);
        if (service == null) {
            LOGGER.error("Unsupported dispatcher service.");
            throw new RuntimeException("Unsupported dispatcher service.");
        }
        return service;
    }
}
