package com.app.mvc.controller.rabbitmqnetty;

import com.app.mvc.controller.rabbitmqnetty.protocol.LuckHeader;
import com.app.mvc.controller.rabbitmqnetty.protocol.LuckMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理逻辑类
 * Created by Administrator on 2017/1/19.
 */
public class ServerHandler extends SimpleChannelInboundHandler<LuckMessage> {



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //存放到通道中
        Global.channels.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Global.channels.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LuckMessage message) throws Exception {

        //读取信息头部
        LuckHeader header=message.getLuckHeader();

        //判断读取的类型
        String entity=header.getSessionId();
        int type=header.getVersion();

        //String   == 0
        if(type==0){
            System.out.println("字符串类型");
        }//json类型并且是支持的格式
        else if (type==1&&entity.equals("info")){
            System.out.println("json类型");
        }//实体类
        else if(type==2&&entity.equals("info")){
            System.out.println("实体类");
        }//文件
        else if(type==3){
            System.out.println("文件");
            //此处可拓展
            switch (entity){
                case "jpg":savejpg();
                case "pdf":savepdf();
            }
        }

        //读完便会传递到rabbitmq进行传递
        //应该自定义好路由规则
        //并且需要提供未消费支持，即一个个地发送，如果处理未成功，那么会重新发送

        System.out.println();
    }

    private void savepdf() {
    }

    private void savejpg() {
    }
}
