package com.app.mvc.controller.netty.server;

import com.app.mvc.controller.netty.server.HelloServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/1/16.
 */
@Component
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private HelloServerHandler helloServerHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 以("\n")为结尾分割的 解码器
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

        // 字符串解码 和 编码
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // 自己的逻辑Handler
        pipeline.addLast("handler", helloServerHandler);
    }
}
