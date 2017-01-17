package com.app.mvc.controller.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/1/18.
 */
public class FileClient {

    public static void main(String[] args){

        ClientBootstrap bootstrap=new ClientBootstrap(
                new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));



    }
}
