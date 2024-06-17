package com.example.userservice.controller;

import com.example.userservice.model.ServiceInstance;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final EurekaClient eurekaClient;

    @RequestMapping(value = "/services", method = RequestMethod.GET, produces = "application/json")
    public Object getServices(
//            @RequestParam List<String> serviceNames
    ) {
       return eurekaClient
               .getApplications()
               .getRegisteredApplications()
               .stream()
               .flatMap(application -> application.getInstances().stream())
//               .filter(instanceInfo -> serviceNames.contains(instanceInfo.getAppName()))
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
