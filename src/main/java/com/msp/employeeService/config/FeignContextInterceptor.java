package com.msp.employeeService.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignContextInterceptor implements RequestInterceptor {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void apply(RequestTemplate template) {
        List<ServiceInstance> instances = discoveryClient.getInstances("ADDRESS-SERVICE");
        if (!instances.isEmpty()) {
            String contextPath = instances.get(0).getMetadata().get("context-path");
            template.uri(contextPath + template.path());
        }
    }
}
