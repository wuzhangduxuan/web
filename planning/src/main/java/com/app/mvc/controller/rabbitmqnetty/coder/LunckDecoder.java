package com.app.mvc.controller.rabbitmqnetty.coder;

import com.app.mvc.controller.rabbitmqnetty.protocol.LuckHeader;
import com.app.mvc.controller.rabbitmqnetty.protocol.LuckMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by Administrator on 2017/1/19.
 */
public class LunckDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        //获取协议的版本
        int version=byteBuf.readInt();

        //获取消息的长度
        int contentLength=byteBuf.readInt();

        //获取SessionId
        byte[] sessionByte=new byte[36];
        byteBuf.readBytes(sessionByte);
        String sessionId=new String(sessionByte);

        //组装协议头
        LuckHeader header=new LuckHeader(version,contentLength,sessionId);

        //读取消息内容
        byte[] content =byteBuf.readBytes(byteBuf.readableBytes()).array();

        LuckMessage luckMessage=new LuckMessage(header,new String(content));

        list.add(luckMessage);

    }
}
