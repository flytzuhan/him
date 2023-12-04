package com.shadow.him.common.coder;

import com.shadow.him.common.message.MessageHolder;
import com.shadow.him.common.rules.ProtocolHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProtocolDecoder extends ByteToMessageDecoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        LOGGER.info("开始执行解码操作");

        if (in.readableBytes() < ProtocolHeader.HEADER_LENGTH) {
            LOGGER.error("数据包长度小于协议头长度");
            return;
        }

        in.markReaderIndex();
        if (in.readShort() != ProtocolHeader.MAGIC) {
            LOGGER.error("Magic不一致");
            in.resetReaderIndex();
            return;
        }

        // 开始解码
        byte version = in.readByte();
        byte serializer = in.readByte();
        byte sign = in.readByte();
        byte type = in.readByte();
        int bodyLength = in.readInt();

        if (bodyLength != in.readableBytes()) {
            LOGGER.error("消息体长度不一致");
            in.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[bodyLength];
        in.readBytes(bytes);

        MessageHolder holder = new MessageHolder();
        holder.setVersion(version);
        holder.setSerializer(serializer);
        holder.setSign(sign);
        holder.setType(type);
        holder.setBody(new String(bytes, CharsetUtil.UTF_8));

        list.add(holder);
    }
}
