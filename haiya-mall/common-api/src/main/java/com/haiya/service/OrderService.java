package com.haiya.service;

import com.haiya.entity.UserAddress;

import java.util.List;

public interface OrderService {
    List<UserAddress> initOrder(String userId);
}
