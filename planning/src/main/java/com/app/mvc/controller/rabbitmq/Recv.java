package com.app.mvc.controller.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * RabbitMQ 是信息传输的中间者。
 * 本质上，他从生产者（producers）接收消息，转发这些消息给消费者（consumers）.
 * 换句话说，他能够按根据你指定的规则进行消息转发、缓冲、和持久化。
 */

/**
 * 接收端
 * Created by Administrator on 2017/1/17.
 */
public class Recv {

    private final static String Queue="helll";

    public static void main(String[] args){
        ConnectionFactory factory=new ConnectionFactory();

        factory.setHost("localhost");

        try {
            Connection connection=factory.newConnection();

            Channel channel=connection.createChannel();

            channel.queueDeclare("",false,false,false,null);
            //确保接收公平
            channel.basicQos(1);

            System.out.println("接收message");

            QueueingConsumer consumer=new QueueingConsumer(channel);

            //应答机制启动
            boolean ack=false;

            channel.basicConsume(Queue,ack,consumer);

            while (true){
                try {
                    QueueingConsumer.Delivery delivery=consumer.nextDelivery();
                    String message=new String(delivery.getBody());
                    System.out.println("接收到信息:"+message);
                    dowork(message);
                    System.out.println("task  done");
                    //发送应答
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }

    private static void dowork(String message) {

        for (char ch:message.toCharArray()){
            if (ch == '.'){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
