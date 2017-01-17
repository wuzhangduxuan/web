package com.app.mvc.controller.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * 订阅信息不太舒服的地方就是只能实时的发送，不能将之前已发送的同步到接收端
 * Created by Administrator on 2017/1/17.
 */
public class EmitLog {

    private final static String EXCHANGE_NAME="ex_log";

    public static void main(String[] args){
        ConnectionFactory factory=new ConnectionFactory();

        factory.setHost("localhost");

        try {
            Connection connection=factory.newConnection();

            Channel channel=connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

            String message=new Date().toString()+":log.something";

            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());

            System.out.println("send");

            channel.close();

            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }
}
