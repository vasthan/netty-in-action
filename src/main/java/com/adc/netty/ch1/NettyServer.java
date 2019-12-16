package com.adc.netty.ch1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                .childAttr(AttributeKey.newInstance("key"), "value")
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        System.out.printf("%s启动中...\n", ch.attr(AttributeKey.valueOf("serverName")));
                    }
                })
                .childHandler(new ChannelInitializer<Channel>() {
                    protected void initChannel(Channel channel) throws Exception {
                        System.out.println(channel.attr(AttributeKey.valueOf("key")));
                    }
                });

        bind(server, 1080);
    }

    private static void bind(ServerBootstrap server, int port) {
        server.bind(port)
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("绑定端口成功：" + port);
                        } else {
                            System.out.println("绑定端口失败：" + port);
                            bind(server, port + 1);
                        }
                    }
                });
    }
}
