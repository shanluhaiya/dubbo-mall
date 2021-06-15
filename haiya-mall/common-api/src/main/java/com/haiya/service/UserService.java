package com.haiya.service;

import com.haiya.entity.UserAddress;

import java.util.List;

public interface UserService {
    List<UserAddress> getUserAddress(String userId);
}
