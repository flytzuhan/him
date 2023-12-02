package com.shadow.him.client.bootstrap;

import com.shadow.him.client.handler.ClientInitHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ClientStart {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientStart.class);

    private static final EventLoopGroup GROUP = new NioEventLoopGroup();

    @Autowired
    private ClientInitHandler clientInitHandler;

    @PostConstruct
    public void start() {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(GROUP)
                    .channel(NioSocketChannel.class)
                    .handler(clientInitHandler);
        } finally {
            GROUP.shutdownGracefully();
        }
    }
}
