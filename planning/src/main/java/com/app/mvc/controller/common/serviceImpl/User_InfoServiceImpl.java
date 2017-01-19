package com.app.mvc.controller.common.serviceImpl;

import com.app.mvc.controller.common.mapper.UserInfoMapper;
import com.app.mvc.controller.common.model.UserInfo;
import com.app.mvc.controller.common.service.User_InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/19.
 */
@Service
public class User_InfoServiceImpl implements User_InfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void insert(UserInfo record) {
        try {
            userInfoMapper.insert(record);
        }catch (Exception e){
            System.out.println("插入出错");
        }
        System.out.println("插入成功");
    }
}
