package com.shadow.him.common.coder;

import com.shadow.him.common.message.MessageHolder;
import com.shadow.him.common.rules.ProtocolHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtocolEncoder extends MessageToByteEncoder<MessageHolder> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolEncoder.class);

    protected void encode(ChannelHandlerContext ctx, MessageHolder msg, ByteBuf out) throws Exception {
        LOGGER.info("执行消息编码操作");
        String body = msg.getBody();

        if (body == null) {
            throw new RuntimeException("消息内容不能为空");
        }

        // 编码
        byte[] bytes = body.getBytes(CharsetUtil.UTF_8);

        out.writeShort(ProtocolHeader.MAGIC)
                .writeByte(msg.getVersion())
                .writeByte(msg.getSerializer())
                .writeByte(msg.getSign())
                .writeByte(msg.getType())
                .writeInt(bytes.length)
                .writeBytes(bytes);
    }
}
