package com.app.mvc.controller.netty.fileServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: fuzhengwei
 * Date: 15-11-8
 * Time: 下午1:57
 * To change this template use File | Settings | File Templates.
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        System.out.println("报告");
        System.out.println("信息：有一客户端链接到本服务端");
        System.out.println("IP:" + ch.localAddress().getHostName());
        System.out.println("Port:" + ch.localAddress().getPort());
        System.out.println("报告完毕");

        ch.pipeline().addLast(new ByteArrayEncoder());
        // 在管道中添加我们自己的接收数据实现方法
        ch.pipeline().addLast(new ChildChannelHandler());

    }
}
