package com.app.mvc.controller.rabbitmq.Topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 实现如果用户上线就启动
 * Created by Administrator on 2017/1/20.
 */
public class ReceiveLogsTopicForKernel {

    private static final String EXCHANGE_NAME = "topic_logs1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 声明转发器
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        channel.basicQos(1);

        String QUEUE_NAME="log1";

       // channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"auth.info");

        System.out.println("wati for message");

        QueueingConsumer consumer=new QueueingConsumer(channel);

        channel.basicConsume(QUEUE_NAME,false,consumer);


        while (true){

            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String msg=new String(delivery.getBody());
            //并处理之,处理完发送心跳给控制台
            System.out.println("接收到信息"+msg);
           channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

    }
}
