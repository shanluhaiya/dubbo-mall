package com.haiya.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(protocol = "dubbo")
public class DubboProtoServiceImpl implements DubboProtoService {
    @Override
    public String test(String param) {
        return "dubbo service: " + param;
    }
}
