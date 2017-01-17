package com.app.mvc.controller.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/1/18.
 */
public class ReceiveLogsToSave {

    private final static String EXCHANGE_NAME="ex_log";

    public static void main(String[] args){
        ConnectionFactory factory=new ConnectionFactory();

        factory.setHost("localhost");

        try {
            Connection connection=factory.newConnection();

            Channel channel=connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

            //创建一个非持久的，唯一的且自动删除的队列
            String queueName=channel.queueDeclare().getQueue();
            //为转发器指定队列
            channel.queueBind(queueName,EXCHANGE_NAME,"");

            QueueingConsumer consumer=new QueueingConsumer(channel);
            //第二个参数表明是否自动应答
            channel.basicConsume(queueName,true,consumer);

            for (;;){
                QueueingConsumer.Delivery delivery=consumer.nextDelivery();

                String message=new String(delivery.getBody());

                System.out.println("接收到信息"+message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
