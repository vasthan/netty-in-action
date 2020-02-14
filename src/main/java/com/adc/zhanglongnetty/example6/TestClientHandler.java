package com.adc.zhanglongnetty.example6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<DataInfo.Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Message msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int i = new Random().nextInt(3) + 1;

        DataInfo.Message.MessageType messageType = DataInfo.Message.MessageType.forNumber(i);
        DataInfo.Message.Builder builder = DataInfo.Message.newBuilder().setMessageType(messageType);

        if (messageType == DataInfo.Message.MessageType.Apple) {
            DataInfo.Apple apple = DataInfo.Apple.newBuilder().setColor("green").setWeight(100).build();
            builder.setApple(apple);
        } else if (messageType == DataInfo.Message.MessageType.Banana) {
            DataInfo.Banana banana = DataInfo.Banana.newBuilder().setColor("yellow").setPrice(200).build();
            builder.setBanana(banana);
        } else if (messageType == DataInfo.Message.MessageType.Orange) {
            DataInfo.Orange orange = DataInfo.Orange.newBuilder().setColor("orange").setAddress("shanghai").build();
            builder.setOrange(orange);
        }
        ctx.writeAndFlush(builder.build());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
        ctx.close();
    }
}
