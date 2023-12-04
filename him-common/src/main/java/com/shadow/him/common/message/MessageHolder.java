package com.shadow.him.common.message;

import io.netty.channel.Channel;

public class MessageHolder {
    // 版本号
    private byte version;

    // 序列化协议
    private byte serializer;

    // 消息标志
    private byte sign;

    // 消息类型
    private byte type;

    // Json消息体
    private String body;

    // 接收到消息的通道
    private Channel channel;

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getSerializer() {
        return serializer;
    }

    public void setSerializer(byte serializer) {
        this.serializer = serializer;
    }

    public byte getSign() {
        return sign;
    }

    public void setSign(byte sign) {
        this.sign = sign;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "MessageHolder{" +
                "version=" + version +
                ", serializer=" + serializer +
                ", sign=" + sign +
                ", type=" + type +
                ", body='" + body + '\'' +
                ", channel=" + channel +
                '}';
    }
}
