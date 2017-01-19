package com.app.mvc.controller.serializable;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Administrator on 2017/1/18.
 */
public class SubReqClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        EmpBean empBean=new EmpBean();

        empBean.setEmpAge(1);

        empBean.setEmpName("hello");

        ctx.writeAndFlush(empBean);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到信息");

        EmpBean emp= (EmpBean) msg;


        System.out.println("年龄:"+emp.getEmpAge());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("接收到信息");

        EmpBean emp= (EmpBean) o;

        System.out.println("年龄:"+emp.getEmpAge());


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
