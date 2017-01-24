package com.app.mvc.controller.rabbitmqnetty.coder;

import com.app.mvc.controller.rabbitmqnetty.protocol.LuckHeader;
import com.app.mvc.controller.rabbitmqnetty.protocol.LuckMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * Created by Administrator on 2017/1/20.
 */
@ChannelHandler.Sharable
public class ProtoCodec extends MessageToMessageCodec<ByteBuf,LuckMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, LuckMessage message, List<Object> list) throws Exception {

        ByteBuf byteBuf= ByteBufAllocator.DEFAULT.buffer();

        if(message.getContent()!=null){

            byteBuf.writeInt(message.getLuckHeader().getVersion());

            byteBuf.writeInt(message.getLuckHeader().getContentLength());

            byteBuf.writeBytes(message.getLuckHeader().getSessionId().getBytes());

            byteBuf.writeBytes(message.getContent());


        }
        channelHandlerContext.writeAndFlush(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        LuckMessage message=new LuckMessage();
        LuckHeader header=new LuckHeader();
        header.setVersion(byteBuf.readInt());
        header.setContentLength(byteBuf.readInt());
        int length=header.getContentLength();
        byte[] bb = new byte[length];
        byteBuf.readBytes(bb);
        header.setSessionId(new String(bb));
        byte[] b=new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(b);
        message.setContent(b);
        message.setLuckHeader(header);
        list.add(message);
    }
}
