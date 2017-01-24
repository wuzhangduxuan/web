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
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 获取协议的版本
        int version = in.readInt();

        // 获取消息长度
        int contentLength = in.readInt();
        // 获取SessionId
        String sessionId="12";


        // 组装协议头
        LuckHeader header = new LuckHeader(version, contentLength, sessionId);

        // 读取消息内容
        byte[] content = new byte[in.readableBytes()];

        LuckMessage message = new LuckMessage(header,content);

        out.add(message);
    }
}
