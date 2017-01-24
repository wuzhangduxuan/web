package com.app.mvc.controller.rabbitmqnetty.bean;

import com.app.mvc.controller.rabbitmqnetty.util.FileAndByte;
import com.app.mvc.controller.rabbitmqnetty.util.ObjectAndByte;

/**
 * Created by Administrator on 2017/1/20.
 */
public class test {

    public static void main(String[] args){
        String filePath = "C:\\wu.jpg";
        String outFilePath = "C:";
        String outFileName = "2.jpg";

        FileAndByte.getFile(FileAndByte.getBytes(filePath),outFilePath,outFileName);
    }
}
