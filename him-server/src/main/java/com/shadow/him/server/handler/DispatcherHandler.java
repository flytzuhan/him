package com.shadow.him.server.handler;

import com.shadow.him.common.message.MessageHolder;
import com.shadow.him.server.frame.DispatcherService;
import com.shadow.him.server.frame.DispatcherServiceFactory;
import com.shadow.him.server.frame.type.ActionType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class DispatcherHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherHandler.class);

    @Autowired
    private DispatcherServiceFactory dispatcherServiceFactory;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("开始处理客户端发送过来的数据");
        if (msg instanceof MessageHolder) {
            MessageHolder holder = (MessageHolder) msg;
            String content = holder.getBody();
            LOGGER.info("客户端发送过来的消息是：{}", content);
            LOGGER.info("客户端地址：{}", ctx.channel().remoteAddress());
            ActionType actionType = ActionType.from(holder.getType());
            DispatcherService dispatcherService = dispatcherServiceFactory.getDispatcherService(actionType);
            dispatcherService.handleAction(content);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("数据已经读取完毕", Charset.defaultCharset()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("服务端发生异常: ", cause);
        ctx.close();
    }
}
