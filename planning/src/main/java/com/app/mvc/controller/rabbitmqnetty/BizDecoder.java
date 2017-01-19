package com.app.mvc.controller.rabbitmqnetty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by Administrator on 2017/1/19.
 */
public class BizDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final byte[] JSON_PROTOCOL={01,00,00,00};

    private final byte[] STRING_PROTOCOL={00,01,00,00};

    private final byte[] SERIALZABLE_PROTOCOL={00,00,01,00};

    private final byte[] FILE_PROTOCOL={00,00,00,01};

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

    }
}
