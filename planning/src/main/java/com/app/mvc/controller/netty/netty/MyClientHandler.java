package com.app.mvc.controller.netty.netty;


import com.app.mvc.controller.netty.common.Globle;
import io.netty.channel.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fuzhengwei
 * Date: 15-11-8
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Globle.channel=ctx;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Globle.channel=null;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("接收到数据");
    }
}
