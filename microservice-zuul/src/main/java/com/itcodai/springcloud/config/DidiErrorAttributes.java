package com.itcodai.springcloud.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class DidiErrorAttributes extends DefaultErrorAttributes {
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> result = new LinkedHashMap();
        result.remove("exception");
        return result;
    }
}
