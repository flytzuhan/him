package com.shadow.him.client.handler;

import com.shadow.him.common.coder.ProtocolDecoder;
import com.shadow.him.common.coder.ProtocolEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientInitHandler extends ChannelInitializer<SocketChannel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientInitHandler.class);

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        LOGGER.info("准备开始建立连接");
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new ProtocolDecoder());
        pipeline.addLast(new ProtocolEncoder());
    }
}
