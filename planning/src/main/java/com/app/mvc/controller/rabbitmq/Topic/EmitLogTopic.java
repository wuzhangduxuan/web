package com.app.mvc.controller.rabbitmq.Topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.collections.map.HashedMap;

import java.io.IOException;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/1/20.
 */
public class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs1";
    private static final String QUEUE_NAME="log1";

    public static void main(String[] argv) throws Exception
    {
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Map<String,Object> dic=new HashedMap();
        dic.put("x-max-priority",20);

        channel.queueDeclare(QUEUE_NAME,true,false,false,dic);

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"#.info");

        String[] routing_keys = new String[] { "kernal.info", "ww.info",
                "auth.info", "kernel.critical" };
        for (String routing_key : routing_keys)
        {
            String msg = UUID.randomUUID().toString();
            channel.basicPublish(EXCHANGE_NAME, routing_key, null, msg
                    .getBytes());

            System.out.println(" [x] Sent routingKey = "+routing_key+" ,msg = " + msg + ".");
        }

        channel.close();
        connection.close();
    }
}
