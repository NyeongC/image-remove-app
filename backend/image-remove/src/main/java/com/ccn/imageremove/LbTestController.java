package com.ccn.imageremove;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@Slf4j
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
        log.info("LB 테스트 API 응답: {}", result);
        return result;
    }

    @GetMapping("/request-context-test")
    public Map<String, Object> requestContextTest() {
        ServletRequestAttributes attrs =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        Map<String, Object> result = new HashMap<>();

        
        if (attrs == null) {
            result.put("requestContext", "NULL");
            return result;
        }

        HttpServletRequest request = attrs.getRequest();

        result.put("requestContext", "EXISTS");
        result.put("uri", request.getRequestURI());
        result.put("method", request.getMethod());
        result.put("remoteAddr", request.getRemoteAddr());
        result.put("userAgent", request.getHeader("User-Agent"));

        return result;
    }
}
