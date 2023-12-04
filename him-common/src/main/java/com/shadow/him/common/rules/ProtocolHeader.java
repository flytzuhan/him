package com.shadow.him.common.rules;

public class ProtocolHeader {

    // 消息数据的请求头长度
    public static final int HEADER_LENGTH = 10;

    // 魔数(2字节)
    public static final short MAGIC = (short) 0xf096;

    // 版本号(1字节)
    public static final byte VERSION = 0x01;

    // 序列化协议(1字节)
    public byte serializer;

    public static final byte JDK_SERIALIZE = 0x01;
    public static final byte JSON_SERIALIZE = 0x02;
    public static final byte PROTOCOL_SERIALIZE = 0x03;

    // 消息标志(1字节)
    private byte sign;

    public static final byte REQUEST               = 0x01;    // 请求  Client --> Server
    public static final byte RESPONSE              = 0x02;    // 响应  Server --> Client
    public static final byte NOTICE                = 0x03;    // 通知  Server --> Client  e.g.消息转发

    // 消息类型(1字节)
    private byte type;

    public static final byte LOGIN                 = 0x11;    // 登录
    public static final byte REGISTER              = 0x12;    // 注册
    public static final byte LOGOUT                = 0x13;    // 登出
    public static final byte RECONN                = 0x14;    // 重连
    public static final byte PERSON_MESSAGE        = 0x15;    // 发送个人消息
    public static final byte GROUP_MESSAGE         = 0x16;    // 发送讨论组消息
    public static final byte CREATE_GROUP          = 0x17;    // 创建讨论组
    public static final byte DISBAND_GROUP         = 0x18;    // 解散讨论组
    public static final byte ADD_MEMBER            = 0x19;    // 讨论组添加成员
    public static final byte REMOVE_MEMBER         = 0x1a;    // 讨论组移除成员
    public static final byte ADD_FRIEND            = 0x1b;    // 添加好友
    public static final byte REMOVE_FRIEND         = 0x1c;    // 删除好友
    public static final byte ALL_FRIEND            = 0x1d;    // 查看已添加好友
    public static final byte UPDATE_SELF_INFO      = 0x1e;    // 修改个人信息
    public static final byte LOOK_SELF_INFO        = 0x1f;    // 查看个人信息
    public static final byte LOOK_FRIEND_INFO      = 0x21;    // 查看好友信息
    public static final byte LOOK_GROUP_INFO       = 0x22;    // 查看自己所在讨论组信息
    public static final byte HEARTBEAT             = 0x23;    // 心跳

    // 消息体长度(4字节)
    private int bodyLength;
}
