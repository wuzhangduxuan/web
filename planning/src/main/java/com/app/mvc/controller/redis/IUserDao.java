package com.app.mvc.controller.redis;

import java.util.List;

/**
 * Created by Administrator on 2017/1/25.
 */
public interface IUserDao {

    public boolean add(User user);

    public boolean add(List<User> users);

    public boolean del(String key);

    public boolean del(List<String> keys);

    public void update(User user);

    public User get(String key);
}
