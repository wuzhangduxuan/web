package com.app.mvc.controller.rabbitmqnetty.oneToOne;

import com.app.mvc.controller.rabbitmqnetty.common.InitFactory;
import com.app.mvc.controller.rabbitmqnetty.protocol.LuckMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/22.
 */
public class ReceEnd {

    private String from;

    private String to;

    private String QUEUENAME;

    private io.netty.channel.Channel ch;

    public ReceEnd(String from, String to, io.netty.channel.Channel channel){
        this.from=from;
        this.to=to;
        this.ch=channel;
        QUEUENAME=String.valueOf(from)+"_"+String.valueOf(to);
    }



    public void ReceEndOne(){
        Connection connection=new InitFactory("localhost").getConnection();
        try {
            Channel channel=connection.createChannel();

            channel.basicQos(1);

            QueueingConsumer consumer=new QueueingConsumer(channel);

            Boolean ack=false;

            channel.basicConsume(QUEUENAME,ack,consumer);

            while (true){
                QueueingConsumer.Delivery delivery=consumer.nextDelivery();

                byte[] mess=delivery.getBody();

                //组装过程
                LuckMessage message=new LuckMessage();
                ch.writeAndFlush(message);

                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
