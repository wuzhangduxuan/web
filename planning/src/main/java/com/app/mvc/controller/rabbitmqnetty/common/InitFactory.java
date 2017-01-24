package com.app.mvc.controller.rabbitmqnetty.common;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/1/22.
 */
public class InitFactory {

    private String host;

    public InitFactory(String host){
        this.host=host;
    }

    public Connection getConnection(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        Connection connection=null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
