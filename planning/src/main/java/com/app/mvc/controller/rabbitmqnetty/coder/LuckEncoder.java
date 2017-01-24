package com.app.mvc.controller.rabbitmqnetty.coder;

import com.app.mvc.controller.rabbitmqnetty.protocol.LuckHeader;
import com.app.mvc.controller.rabbitmqnetty.protocol.LuckMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Administrator on 2017/1/19.
 */
@ChannelHandler.Sharable
public class LuckEncoder extends MessageToByteEncoder<LuckMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, LuckMessage message, ByteBuf out) throws Exception {

        // 将Message转换成二进制数据
        LuckHeader header = message.getLuckHeader();

        // 这里写入的顺序就是协议的顺序.

        // 写入Header信息
        out.writeInt(header.getVersion());
        out.writeBytes(header.getSessionId().getBytes());
        out.writeInt(message.getContent().length);


        // 写入消息主体信息
        out.writeBytes(message.getContent());
    }
}
