package com.app.mvc.controller.rabbitmqnetty.client;

import com.app.mvc.controller.rabbitmqnetty.coder.LuckEncoder;
import com.app.mvc.controller.rabbitmqnetty.coder.LunckDecoder;
import com.app.mvc.controller.rabbitmqnetty.coder.ProtoCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by Administrator on 2017/1/20.
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    private static final ProtoCodec ENCODER = new ProtoCodec();

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        // 添加编解码器, 由于ByteToMessageDecoder的子类无法使用@Sharable注解,
        // 这里必须给每个Handler都添加一个独立的Decoder.
       // pipeline.addLast(ENCODER);
       // pipeline.addLast(new LunckDecoder());
        //1-读半包的解码器
        channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024*1024, 0, 4, 0, 4));
//2-进行消息解码

//encoder
        channel.pipeline().addLast(new LengthFieldPrepender(4));


        // and then business logic.
        pipeline.addLast(new HelloServerHandler());

    }
}
