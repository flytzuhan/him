package com.shadow.him.server.bootstrap;

import com.shadow.him.server.config.IMServerConfig;
import com.shadow.him.server.handler.ServerInitHandler;
import com.shadow.him.server.listener.ServerReadyListener;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServerStart {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerStart.class);

    private static final NioEventLoopGroup boss = new NioEventLoopGroup(1);

    private static final NioEventLoopGroup worker = new NioEventLoopGroup();

    @Autowired
    private ServerInitHandler serverInitHandler;

    @Autowired
    private ServerReadyListener serverReadyListener;

    @Autowired
    private IMServerConfig imServerConfig;

    @PostConstruct
    public void start() {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(imServerConfig.getServerProperties().getPort())
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(serverInitHandler);

            LOGGER.info("...服务器 开始准备...");

            ChannelFuture future = bootstrap.bind().sync();

            future.addListener(serverReadyListener);

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
