package com.haiya.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.haiya.entity.UserAddress;
import com.haiya.service.OrderService;
import com.haiya.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Reference
    UserService userService;
    @Override
    public List<UserAddress> initOrder(String userId) {
        return userService.getUserAddress(userId);
    }
}
