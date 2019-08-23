package com.itcodai.springcloud.config;

import com.netflix.zuul.ExecutionStatus;
import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.ZuulFilterResult;
import com.netflix.zuul.context.Debug;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class DidiFilterProcessor extends FilterProcessor {
    //扩展ErrorFilter过滤器,当过滤器执行抛出异常的时候,我们捕获它,并在请求上下文中记录一些信息
    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
        try {
            return super.processZuulFilter(filter);
        } catch (ZuulException e) {
            RequestContext ctx=RequestContext.getCurrentContext();
            ctx.set("failed.filter",filter);
            throw e;
        }
    }
}
