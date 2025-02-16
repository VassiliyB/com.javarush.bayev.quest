package com.quest.util;

public class IpConverter {

    public static String convertIpv6ToIpv4(String ipv6Address) {
        if ("0:0:0:0:0:0:0:1".equals(ipv6Address) || "[0:0:0:0:0:0:0:1]".equals(ipv6Address)) {
            return "127.0.0.1";
        }
        return ipv6Address;
    }
}
