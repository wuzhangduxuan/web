package com.app.mvc.controller.netty.netty;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created with IntelliJ IDEA.
 * User: fuzhengwei
 * Date: 15-11-8
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */
public class ChildChannelHandler  extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ByteArrayEncoder());
        ch.pipeline().addLast(new ChunkedWriteHandler());
        ch.pipeline().addLast(new MyClientHandler());
    }
}
