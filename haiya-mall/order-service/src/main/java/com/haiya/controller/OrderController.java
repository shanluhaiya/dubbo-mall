package com.haiya.controller;

import com.haiya.entity.UserAddress;
import com.haiya.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/userAddress")
    public List<UserAddress> userAddress(@RequestParam("id") String userId) {
        return orderService.initOrder(userId);
    }
}
