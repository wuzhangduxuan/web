package com.app.mvc.controller.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送信息到rabbitmq中
 * Created by Administrator on 2017/1/17.
 */
public class Send {

    private final static String Queue="helll";

    public static void main(String[] args){
        ConnectionFactory factory=new ConnectionFactory();

        factory.setHost("localhost");

        Connection connection=null;
        try {
            connection=factory.newConnection();

            Channel channel=connection.createChannel();

            //Direct  Topic  Headers  Fanout
            //将信息广播到它已知的全部队列
            //声明转发器的类型
            channel.exchangeDeclare("logs","fanout");

            //如果未指定队列持久化，重新启动时，队列会被删除
            //持久化队列
            //注：队列
            boolean durable=true;

            channel.queueDeclare(Queue,durable,false,false,null);

            for(int i=0;i<10;i++) {
                String message = "hello world";
                for(int j=0;j<i;j++) {
                    message+=".";
                }
                //并且使用MessageProperties.PERSISTENT_TEXT_PLAIN标识我的消息是持久化的
                channel.basicPublish("", Queue, MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes());
                System.out.println("发送信息");
            }
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }






    }
}
