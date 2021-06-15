package com.haiya.controller;


import com.haiya.service.SysGenService;
import com.haiya.utils.PageUtils;
import com.haiya.utils.Query;
import com.haiya.utils.R;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/sys/generator")
public class SysGenController {
    @Autowired
    SysGenService sysGenService;


    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils pageUtil = sysGenService.queryList(new Query(params));
        return R.ok().put("page", pageUtil);
    }

    /**
     * 生成代码
     */
    @RequestMapping("/code")
    public void code(String tables, HttpServletResponse response) throws IOException {
        byte[] data = sysGenService.generatorCode(tables.split(","));

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"haiya.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
