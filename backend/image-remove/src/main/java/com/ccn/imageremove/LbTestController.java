package com.ccn.imageremove;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LbTestController {

    @Value("${server.port:8080}")
    private String serverPort;

    @GetMapping("/lb-test")
    public Map<String, Object> lbTest() throws Exception {
        InetAddress host = InetAddress.getLocalHost();
        Map<String, Object> result = new HashMap<>();
        result.put("hostname", host.getHostName());
        result.put("ip", host.getHostAddress());
        result.put("port", serverPort);
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
}
