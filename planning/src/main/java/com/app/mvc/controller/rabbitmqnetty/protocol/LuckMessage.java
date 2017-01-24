package com.app.mvc.controller.rabbitmqnetty.protocol;

/**
 *消息的主体
 * Created by Administrator on 2017/1/19.
 */
public class LuckMessage {

    public LuckMessage (){

    }

    private LuckHeader luckHeader;
    private byte[] content;

    public LuckMessage(LuckHeader luckHeader, byte[] content) {
        this.luckHeader = luckHeader;
        this.content = content;
    }

    public LuckHeader getLuckHeader() {
        return luckHeader;
    }

    public void setLuckHeader(LuckHeader luckHeader) {
        this.luckHeader = luckHeader;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("[version=%d,contentLength=%d,sessionId=%s,content=%s]",
                luckHeader.getVersion(),
                luckHeader.getContentLength(),
                luckHeader.getSessionId(),
                content);
    }
}
