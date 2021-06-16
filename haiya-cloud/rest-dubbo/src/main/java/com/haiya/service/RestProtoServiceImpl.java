package com.haiya.service;

import org.apache.dubbo.config.annotation.DubboService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@DubboService(protocol = "rest")
@Path("/")
public class RestProtoServiceImpl implements RestProtoService {
    @Override
    @Path("test/{p}")
    @GET
    public String test(@PathParam("p") String param) {
        return "rest service: " + param;
    }
}
