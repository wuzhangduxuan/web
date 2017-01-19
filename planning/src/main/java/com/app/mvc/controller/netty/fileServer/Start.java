package com.app.mvc.controller.netty.fileServer;


/**
 * Created with IntelliJ IDEA.
 * User: fuzhengwei
 * Date: 15-11-8
 * Time: 下午2:30
 * To change this template use File | Settings | File Templates.
 */
public class Start {

    public static void main(String[] args) {
        System.out.println("启动NettyServer start... ...");
        Thread thread = new Thread(new NettyServer());
        thread.start();
        System.out.println("启动NettyServer end... ...");
    }

}
