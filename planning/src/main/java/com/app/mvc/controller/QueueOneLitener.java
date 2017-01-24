package com.app.mvc.controller;



import com.alibaba.fastjson.JSON;
import com.app.mvc.controller.rabbitmqnetty.Global;
import com.app.mvc.controller.rabbitmqnetty.bean.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by Administrator on 2017/1/23.
 */
@Component
public class QueueOneLitener{

    @Autowired
    private FastJsonMessageConverter  fastJsonMessageConverter;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ConnectionFactory connectionFactory;

    private SimpleMessageListenerContainer container;


    public void onMessage(String queue) {
        if(container==null){
            container=new SimpleMessageListenerContainer(connectionFactory);
        }
        //设置处理池
        container.setTaskExecutor(taskExecutor);
        //设置为手动提交
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //节省网络流量
        container.setPrefetchCount(1);
        container.addQueues(new Queue(queue));
        container.setMessageConverter(fastJsonMessageConverter);
        container.setMessageListener(new ChannelAwareMessageListener(){
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                ChatMessage chatMessage=JSON.parseObject(message.getBody(),ChatMessage.class);
                System.out.println("接收到信息"+message.getMessageProperties().getConsumerQueue()+
                        chatMessage.getFrom());
                Iterator<io.netty.channel.Channel> poll= Global.channels.iterator();

                System.out.println("处理信息中............");
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }
        });
        container.start();

    }
}
