package com.app.mvc.controller.rabbitmqnetty.oneToOne;

import com.app.mvc.controller.rabbitmqnetty.common.InitFactory;
import com.app.mvc.controller.rabbitmqnetty.common.Message;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/22.
 */

public class SendTo {

    private int from;

    private int to;

    private Message message;

    private String QUEUENAME;

    public SendTo(int from,int to,Message message){
        this.from=from;
        this.to=to;
        this.message=message;
        this.QUEUENAME=String.valueOf(from)+"_"+String.valueOf(to);
    }

    public void sendToOne(){
        Connection connection=new InitFactory("localhost").getConnection();
        try {
            //未来优化考虑用池来存放
            Channel channel=connection.createChannel();

            boolean durable=true;

            channel.queueDeclare(QUEUENAME,durable,false,false,null);

            channel.basicPublish("",QUEUENAME, MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getMessage().getBytes());

            System.out.println("一对一发送人:"+from+",送达方:"+to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
