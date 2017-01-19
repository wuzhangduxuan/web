package com.app.mvc.controller.netty.main;


import com.app.mvc.controller.netty.common.Globle;
import com.app.mvc.controller.netty.file.FileControl;
import com.app.mvc.controller.netty.netty.NettyClient;
import io.netty.util.internal.StringUtil;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fuzhengwei
 * Date: 15-11-8
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class Start {

    public static void main(String[] args) {
        System.out.println("启动NettyClient start ... ...");
        Thread thread = new Thread(new NettyClient());
        thread.start();
        System.out.println("启动NettyClient end ... ...");

        // 当socket链接上才可以进行传送文件
        while (null == Globle.channel) {
            try {
                Thread.sleep(1000);
                System.out.println("等待socket握手... ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("发送文件 开始" + new Date());
        String fileUrl = "C:\\java副本\\QQ截图20160501091331.png";
        FileControl fileControl = new FileControl(Globle.channel);
        // 通知服务端我要发文件了
        fileControl.sendNotice();
        // 开始发文件
        fileControl.sendFile(fileUrl);
        System.out.println("发送文件 结束" + new Date());
    }

}
