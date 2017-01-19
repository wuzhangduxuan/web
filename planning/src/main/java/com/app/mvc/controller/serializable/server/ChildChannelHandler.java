package com.app.mvc.controller.serializable.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;


/**
 * Created by Administrator on 2017/1/18.
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {

        System.out.println("报告");

        //序列化、解码
        sc.pipeline().addLast(
                new ObjectDecoder(1024 * 1024, ClassResolvers
                        .weakCachingConcurrentResolver(this.getClass()
                                .getClassLoader())));

        //序列化、编码
        sc.pipeline().addLast(new ObjectEncoder());

        //信息处理
        sc.pipeline().addLast(new SubReqServerHandler());


    }
}
