package com.adc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap client = new Bootstrap();

        NioEventLoopGroup group = new NioEventLoopGroup();

        client.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) {
                        channel.pipeline().addLast(new StringEncoder());
                    }
                });
        Channel channel = client.connect("127.0.0.1", 8080).channel();
        while (true) {
            channel.writeAndFlush(new Date() + " netty");
            Thread.sleep(2000);
        }
    }
}
