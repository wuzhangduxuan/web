package com.app.mvc.controller.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.SocketAddress;

/**
 * Created by Administrator on 2017/1/16.
 */
@Controller
@RequestMapping("netty")
public class ServerNetty {

    private static Logger log = Logger.getLogger(ServerNetty.class);
    /**
     * 服务端监听的端口地址
     */
    private static final int portNumber=7882;

    @Autowired
    private HelloServerHandler helloServerHandler;

    @Autowired
    private ServerInitializer helloServerInitializer;
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    ChannelFuture f;

    @RequestMapping("send")
    public void sendMessage(String to,String baseName){
        ChannelGroup channels=helloServerHandler.channels;
        System.out.println("接收:"+to);
        for(Channel channel:channels){
            SocketAddress socketAddress=channel.remoteAddress();
            String toSelf=socketAddress.toString();
            System.out.println(toSelf+"######");
            channel.writeAndFlush("[SERVER] - " +baseName+ " leave\n");
            System.out.println(toSelf+"######");
        }
    }

    //程序初始方法入口注解，提示spring这个程序先执行这里
    @RequestMapping("start")
    public void serverStart() throws InterruptedException{
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(helloServerInitializer);
            // 服务器绑定端口监听
            f = b.bind(portNumber).sync();
            // 监听服务器关闭监听
            try {
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("###########################################");
            // 可以简写为
            //b.bind(portNumber).sync().channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
