package com.app.mvc.controller.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Administrator on 2017/1/25.
 */
public class UserDao extends AbstractBaseRedisDao<String,User> implements IUserDao {

    private WzTemplate<String,String> wzTemplate=new WzTemplate();

    @Override
    public boolean add(final User user) {

        Boolean result=redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer=getRedisSerializer();
                byte[] id=serializer.serialize(user.getId());
                byte[] name=serializer.serialize(user.getName());
                return connection.setNX(id,name);
            }
        });
        return result;
    }

    @Override
    public boolean add(final List<User> users) {
        Assert.notEmpty(users);
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> redisSerializer=getRedisSerializer();
                for(User user:users){
                    byte[] id=redisSerializer.serialize(user.getId());
                    byte[] name=redisSerializer.serialize(user.getName());
                    connection.setNX(id,name);
                }
                return true;
            }
        },false,true);
        return false;
    }

    @Override
    public boolean del(String key) {
        return false;
    }

    @Override
    public boolean del(List<String> keys) {
        return false;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User get(String key) {
        return null;
    }
}
