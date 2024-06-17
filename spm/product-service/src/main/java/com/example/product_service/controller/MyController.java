package com.example.product_service.controller;

import com.example.product_service.model.ServiceInstance;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyController {

    public final EurekaClient eurekaClient;

    @GetMapping("/services")
    public List<ServiceInstance> getServices(
//            @RequestParam List<String> serviceNames
    ) {
        return eurekaClient
                .getApplications()
                .getRegisteredApplications()
                .stream()
                .flatMap(application -> application.getInstances().stream())
//                .filter(instanceInfo -> serviceNames.contains(instanceInfo.getAppName()))
                .map(this::toServiceInstance)
                .collect(Collectors.toList());
    }

    private ServiceInstance toServiceInstance(InstanceInfo instanceInfo) {
        return new ServiceInstance(
                instanceInfo.getAppName(),
                instanceInfo.getHostName(),
                instanceInfo.getPort()
        );
    }
}
