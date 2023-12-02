package com.shadow.him.server.listener;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ServerReadyListener implements ChannelFutureListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerReadyListener.class);

    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (channelFuture.isSuccess()) {
            LOGGER.info("服务启动成功");
            LOGGER.info("服务端地址：{}", channelFuture.channel().localAddress());
        } else {
            LOGGER.error("服务端启动失败");
        }
    }
}
