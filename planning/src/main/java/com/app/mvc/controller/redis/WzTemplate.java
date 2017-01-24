package com.app.mvc.controller.redis;

/**
 * Created by Administrator on 2017/1/25.
 */
public class WzTemplate<K,T> {

    void execute(WzCallback<T> wzCallback){
        wzCallback.doInback();
    };
}
