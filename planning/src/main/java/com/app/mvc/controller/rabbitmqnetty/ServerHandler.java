package com.app.mvc.controller.rabbitmqnetty;

import com.app.mvc.controller.rabbitmqnetty.bean.User;
import com.app.mvc.controller.rabbitmqnetty.protocol.LuckHeader;
import com.app.mvc.controller.rabbitmqnetty.protocol.LuckMessage;
import com.app.mvc.controller.rabbitmqnetty.util.FileAndByte;
import com.app.mvc.controller.rabbitmqnetty.util.ObjectAndByte;
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
        System.out.println("接收到信息"+message.getContent());
        LuckHeader header=message.getLuckHeader();

        //判断读取的类型
        String entity=header.getSessionId();
        int type=header.getVersion();

        //String   == 0
        if(type==0){
            System.out.println("字符串类型");
            System.out.println("内容"+message.getContent());
            System.out.println("条码"+header.getSessionId());
        }//json类型并且是支持的格式
        else if (type==1){
            System.out.println("json类型");
        }//实体类
        else if(type==2){
            System.out.println("实体类");
            byte[] bt=message.getContent();
            User user= (User) ObjectAndByte.toObject(bt);
            System.out.println("user"+user.getUser_Name());
        }//文件
        else if(type==3){
            System.out.println("文件");
            savejpg(message.getContent());
            //此处可拓展
           /* switch (entity){
                case "jpg":savejpg(message.getContent());
                case "pdf":savepdf(message.getContent());
            }*/
        }

        //读完便会传递到rabbitmq进行传递
        //应该自定义好路由规则
        //并且需要提供未消费支持，即一个个地发送，如果处理未成功，那么会重新发送


        //如果发送的是一对一，那么使用什么模式，如果发生的是聊天室，那么使用什么模式
        //如果发送的是推送咋么做
        //查找数据库,聊天室多少个人就直接建多少个队列，多少个用户就建立多少个队列


        System.out.println();
    }

    private void savepdf(byte[] bt) {

    }

    private void savejpg(byte[] bt) {
        System.out.println("存储图片");
        FileAndByte.getFile(bt,"c:","222.jpg");
    }
}
