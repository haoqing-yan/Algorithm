package com.donny.securitylab.ssrf;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@FunctionalInterface
public interface HostResolver {
    List<InetAddress> resolveAll(String host) throws UnknownHostException;
}
