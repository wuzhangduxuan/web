package com.app.mvc.controller.rabbitmqnetty.client;

import com.app.mvc.controller.rabbitmqnetty.bean.User;
import com.app.mvc.controller.rabbitmqnetty.protocol.LuckHeader;
import com.app.mvc.controller.rabbitmqnetty.protocol.LuckMessage;
import com.app.mvc.controller.rabbitmqnetty.util.FileAndByte;
import com.app.mvc.controller.rabbitmqnetty.util.ObjectAndByte;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

/**
 * Created by Administrator on 2017/1/20.
 */
public class Client {

    public static void main(String args[]) throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());

            // Start the connection attempt.
            Channel ch = b.connect("127.0.0.1", 2008).sync().channel();


            //连接上之后就传递给rabbitmq

            int version = 3;
            /*实体类*/
            User user=new User();
            user.setId(2);
            user.setUser_Name("吴樟");
            byte[] bb=ObjectAndByte.toByteArray(user);

            /*文件*/
            String content = "User";
            byte[] bt=FileAndByte.getBytes("C:\\2.jpg");

            LuckHeader header = new LuckHeader(version,content.length(),content );
            LuckMessage message = new LuckMessage(header, bt);
            ByteBuf byteBuf = Unpooled.buffer();
            byteBuf.writeInt("jpg".length());
            byteBuf.writeBytes("jpg".getBytes());
            byteBuf.writeInt(bt.length);
            byteBuf.writeBytes(bt);
            ch.writeAndFlush(byteBuf);
            ch.close();

        } finally {
            group.shutdownGracefully();
        }
    }
}
