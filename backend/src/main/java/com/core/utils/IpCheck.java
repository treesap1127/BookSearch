package com.core.utils;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class IpCheck {
	public String getClientIp(HttpServletRequest request) throws UnknownHostException {
        String ipAddress = request.getHeader("X-Forwarded-For");

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        // 클라이언트의 IP 주소 반환
        return ipAddress;
    }

    public boolean isLocalIp(String ipAddress) {
        // 로컬 PC 여부 확인 (여러 다른 조건을 추가할 수 있음)
        return ipAddress.startsWith("192.168.") || ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1") || ipAddress.equals("115.95.23.27");
    }
}
