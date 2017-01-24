package com.app.mvc.controller;

import com.app.mvc.controller.rabbitmqnetty.bean.User;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/1/23.
 */
@Controller

public class springrabbitmq {

    @Autowired
    private AmqpTemplate amqpTemplate;;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private QueueOneLitener litener;

    /*用户上线后监听自己的队列*/
    @RequestMapping("receive")
    public void receive(String queue){
        litener.onMessage(queue);
    }



    /**
     * 发送推送服务给所有的用户
     * @param chatMessage
     */
    public void publish(ChatMessage chatMessage){
        /*存放到所有用户的队列中
        *查找本地数据库在该队列的用户*/
        ArrayList<String> allUser =new ArrayList<>();
        /*假定目前只有23,12两个用户*/
        allUser.add("23");allUser.add("12");
        /*遍历全部的用户*/
        Iterator poll=allUser.iterator();
        while (poll.hasNext()){
            String to= (String) poll.next();
            System.out.println("推送服务到:"+to);
            Queue queue=new Queue(to,true,false,
                    false,null);
            rabbitAdmin.declareQueue(queue);
            FanoutExchange publishExchange=new FanoutExchange("Publish",true,
                    false,null);
            rabbitAdmin.declareExchange(publishExchange);
            rabbitAdmin.declareBinding(BindingBuilder.bind(queue)
                    .to(publishExchange));
            amqpTemplate.convertAndSend("Publish",chatMessage);
        }
    }

    /*发送信息给在聊天室房间的用户*/
    public void chatRoom(ChatMessage chatMessage){
        /*存放到在同一个聊天室的用户队列中*/
        String roomId=chatMessage.getTo();
        /*查找本地数据库在该队列的用户*/
        ArrayList<String> userInRoom =new ArrayList<String>();
        /*假定用户聊天id号在此房间中*/
        userInRoom.add("12"); userInRoom.add("23");
        /*遍历房间，将信息存放到用户队列中*/
        Iterator poll=userInRoom.iterator();
        while (poll.hasNext()){
            /*找出需要发送到的队列*/
            String to= (String) poll.next();
            System.out.println("聊天房间号:"+roomId+"接收的用户:"+to);
            Queue queue=new Queue(to,true,false,
                    false,null);
            rabbitAdmin.declareQueue(queue);
            DirectExchange roomExchange =new DirectExchange("RoomExchange",true,
                    false,null);
            rabbitAdmin.declareExchange(roomExchange);
            /*并将房间号作为路由选择的依据*/
            rabbitAdmin.declareBinding(BindingBuilder.bind(queue)
                    .to(roomExchange).with(roomId));
            amqpTemplate.convertAndSend("RoomExchange",roomId,chatMessage);
        }
    }

    /**
     * 发送到单点的用户
     * @param chatMessage
     */
    public void oneToOne(ChatMessage chatMessage){
        /*存放到接收方的队列
        * 1.并且不设置优先接收处理的级别*/
        Queue queue=new Queue(chatMessage.getTo(),true,false,
                false,null);
        rabbitAdmin.declareQueue(queue);
        /*作为分发到队列的通道*/
        DirectExchange oneExchange=new DirectExchange("OneToOne",true,false,
                null);
        rabbitAdmin.declareExchange(oneExchange);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).
                to(oneExchange).with(chatMessage.getTo()));
        amqpTemplate.convertAndSend("OneToOne",chatMessage.getTo(),chatMessage);
    }


    @RequestMapping("send")
    public void sendDataToQueue(){
        Queue queue=new Queue("hello",true,false,
                false,null);
        rabbitAdmin.declareQueue(queue);
        Queue queue1=new Queue("hr",true,false,false,null);
        rabbitAdmin.declareQueue(queue1);
        Queue queue2=new Queue("hw",true,false,false,null);
        rabbitAdmin.declareQueue(queue2);
        TopicExchange topicExchange=new TopicExchange("to",true,false);
        FanoutExchange fanoutExchange=new FanoutExchange("fanout",true,false);
        rabbitAdmin.declareExchange(fanoutExchange);
        rabbitAdmin.declareExchange(topicExchange);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue2).to(fanoutExchange));
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(fanoutExchange));
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue1).to(topicExchange).with("qq.ww"));
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue2).to(topicExchange).with("qq.*"));
        User user=new User();
        user.setId(1);user.setUser_Name("hello");
        amqpTemplate.convertAndSend("fanout","qq.ww", user);
    }
}
