package com.shadow.him.server.handler;

import com.shadow.him.common.coder.ProtocolDecoder;
import com.shadow.him.common.coder.ProtocolEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerInitHandler extends ChannelInitializer<SocketChannel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerInitHandler.class);

    @Autowired
    private DispatcherHandler dispatcherHandler;

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        LOGGER.info("添加初始化成功之后的自定义业务逻辑处理器");
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("ProtocolDecoder", new ProtocolDecoder());
        pipeline.addLast("ProtocolEncoder", new ProtocolEncoder());
        pipeline.addLast("DispatcherHandler", dispatcherHandler);
    }
}
