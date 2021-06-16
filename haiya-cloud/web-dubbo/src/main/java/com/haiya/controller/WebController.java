package com.haiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.haiya.service.DubboProtoService;

/**
 * @author zlt
 * @date 2020/6/26
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@RestController
public class WebController {
    @Autowired
    private DubboProtoService dubboService;

    @GetMapping("/test/{p}")
    public String test(@PathVariable("p") String param) {
        return dubboService.test(param);
    }
}
