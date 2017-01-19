package com.app.mvc.controller.rabbitmqnetty;

import com.app.mvc.controller.rabbitmqnetty.coder.LuckEncoder;
import com.app.mvc.controller.rabbitmqnetty.coder.LunckDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * Created by Administrator on 2017/1/19.
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    private static final LuckEncoder encoder=new LuckEncoder();

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline=socketChannel.pipeline();

        //添加编码器
        pipeline.addLast(encoder);
        pipeline.addLast(new LunckDecoder());

        //添加控制
        pipeline.addLast(new ServerHandler());

    }
}
