package com.app.mvc.controller.rabbitmqnetty.protocol;

/**
 *消息的主体
 * Created by Administrator on 2017/1/19.
 */
public class LuckMessage {

    private LuckHeader luckHeader;
    private String content;

    public LuckMessage(LuckHeader luckHeader, String content) {
        this.luckHeader = luckHeader;
        this.content = content;
    }

    public LuckHeader getLuckHeader() {
        return luckHeader;
    }

    public void setLuckHeader(LuckHeader luckHeader) {
        this.luckHeader = luckHeader;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
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
