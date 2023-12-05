package com.shadow.him.server.frame.impl;

import com.shadow.him.server.frame.DispatcherService;
import com.shadow.him.server.frame.type.ActionType;
import com.shadow.him.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDispatcherService implements DispatcherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDispatcherService.class);

    @Autowired
    private UserService userService;

    @Override
    public ActionType getActionType() {
        return ActionType.LOGIN;
    }

    @Override
    public void handleAction(String content) {
        // 这里就可以开始执行登录的逻辑了，先对数据进行反序列化
    }
}
