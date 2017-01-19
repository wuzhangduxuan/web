package com.app.mvc.controller.serializable.server;

import com.app.mvc.controller.serializable.EmpBean;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Administrator on 2017/1/18.
 */
public class SubReqServerHandler extends SimpleChannelInboundHandler<Object> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress().toString());

        System.out.println("发送信息");


        EmpBean empBean=new EmpBean();

        empBean.setEmpAge(1);

        empBean.setEmpName("hello");

        ctx.writeAndFlush(empBean);

        System.out.println("发送信息成功");


    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("对象");
    }
}
