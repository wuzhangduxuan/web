package com.app.mvc.controller.rabbitmqnetty.coder;

import com.app.mvc.controller.rabbitmqnetty.protocol.LuckHeader;
import com.app.mvc.controller.rabbitmqnetty.protocol.LuckMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Administrator on 2017/1/19.
 */
public class LuckEncoder extends MessageToByteEncoder<LuckMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, LuckMessage luckMessage, ByteBuf byteBuf) throws Exception {

        //将message转为二进制数据
        LuckHeader header=luckMessage.getLuckHeader();

        //注意顺序

        //写入Header信息
        byteBuf.writeInt(header.getVersion());
        byteBuf.writeInt(luckMessage.getContent().length());
        byteBuf.writeBytes(header.getSessionId().getBytes());

        //写入主体信息
        byteBuf.writeBytes(luckMessage.getContent().getBytes());



    }
}
