package com.itcodai.springcloud.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

public class ErrorFilter extends ZuulFilter {

    Logger log= LoggerFactory.getLogger(ErrorFilter.class);
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("进入zuul的error过滤器阶段");
        RequestContext ctx=RequestContext.getCurrentContext();
        Throwable throwable=ctx.getThrowable();
        log.error("this is a ErrorFilter:{}",throwable.getCause().getMessage());
        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ctx.set("error.exception",throwable.getCause());
        return null;
    }
}
