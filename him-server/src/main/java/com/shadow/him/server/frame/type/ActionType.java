package com.shadow.him.server.frame.type;

import com.shadow.him.common.rules.ProtocolHeader;

/**
 * 消息类型
 */
public enum ActionType {

    LOGIN(ProtocolHeader.LOGIN);

    private byte value;

    ActionType(byte value) {
        this.value = value;
    }

    public static ActionType from(byte value) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.value == value) {
                return actionType;
            }
        }

        throw new RuntimeException("Unsupported action type.");
    }
}
