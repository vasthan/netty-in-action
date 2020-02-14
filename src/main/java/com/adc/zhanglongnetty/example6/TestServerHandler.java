package com.adc.zhanglongnetty.example6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<DataInfo.Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Message msg) throws Exception {

        if (msg.getMessageType() == DataInfo.Message.MessageType.Apple) {
            System.out.println(msg.getApple().getColor());
            System.out.println(msg.getApple().getWeight());
        } else if (msg.getMessageType() == DataInfo.Message.MessageType.Banana) {
            System.out.println(msg.getBanana().getColor());
            System.out.println(msg.getBanana().getPrice());
        } else if (msg.getMessageType() == DataInfo.Message.MessageType.Orange) {
            System.out.println(msg.getOrange().getColor());
            System.out.println(msg.getOrange().getAddress());
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 断开");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
