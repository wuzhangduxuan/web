package com.app.mvc.controller.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;

import io.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.SimpleChannelHandler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/18.
 */
public class FileServerHandler extends ChannelHandlerAdapter {

    private boolean first = true;
    private FileOutputStream fos;
    private BufferedOutputStream bufferedOutputStream;

    /*
     * channelAction
	 *
	 * channel 通道
	 * action  活跃的
	 *
	 * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
	 *
	 */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        first = true;

        System.out.println(ctx.channel().localAddress().toString() + " channelActive");
    }

    /*
     * channelInactive
     *
     * channel 	通道
     * Inactive 不活跃的
     *
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     *
     */
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress().toString() + " channelInactive");
        // 关闭流
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        first = false;
    }

    /*
     * channelRead
     *
     * channel 通道
     * Read    读
     *
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        // 第一次接收信息只创建文件
        if (first) {
            System.out.println("创建文件");
            first = false;
            File file = new File("F://test" + new SimpleDateFormat("yyyymmddhhmmss").format(new Date()) + ".zip");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fos = new FileOutputStream(file);
                bufferedOutputStream = new BufferedOutputStream(fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }

        // 开始处理文件信息
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        System.out.println("本次接收内容长度：" + msg.toString().length());
        try {
            bufferedOutputStream.write(bytes, 0, bytes.length);
            buf.release();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * channelReadComplete
     *
     * channel  通道
     * Read     读取
     * Complete 完成
     *
     * 在通道读取完成后会在这个方法里通知，对应可以做刷新操作
     * ctx.flush()
     *
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /*
     * exceptionCaught
     *
     * exception	异常
     * Caught		抓住
     *
     * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     *
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }




}
