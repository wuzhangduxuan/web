package com.app.mvc.controller.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.InetAddress;

/**
 * Created by Administrator on 2017/1/16.
 */
@Controller("helloServerHandler")
@Scope("prototype")
//特别注意这个注解@Sharable，默认的4版本不能自动导入匹配的包，需要手动加入
//地址是import io.netty.channel.ChannelHandler.Sharable;
@ChannelHandler.Sharable
/*处理接触时的逻辑*/
public class HelloServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)

        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        }
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
        }
        channels.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("Received your message !\n");
    }

    /*
     *
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     *
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");

        ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }
}
