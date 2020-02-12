package com.adc.zhanglongnetty.example4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = ((IdleStateEvent) evt);

            switch (event.state()) {
                case READER_IDLE:
                    System.out.println(ctx.channel().remoteAddress() + " 读空闲");
                    break;
                case WRITER_IDLE:
                    System.out.println(ctx.channel().remoteAddress() + " 写空闲");
                    break;
                case ALL_IDLE:
                    System.out.println(ctx.channel().remoteAddress() + " 读写空闲");
                    break;
            }
            ctx.channel().close();
        }
    }
}
