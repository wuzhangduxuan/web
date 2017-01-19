package com.app.mvc.controller.rabbitmqnetty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 保存在线用户的通道
 * Created by Administrator on 2017/1/19.
 */
public  class Global {

    public static ChannelGroup channels =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
