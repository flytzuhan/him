package com.shadow.him.server.frame;

import com.shadow.him.server.frame.type.ActionType;

/**
 * 消息分发服务
 */
public interface DispatcherService {

    /**
     * 获取消息类型
     *
     * @return
     */
    ActionType getActionType();

    /**
     * 执行处理消息的动作
     *
     * @param content
     */
    void handleAction(String content);
}
