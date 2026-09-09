package com.donny.securitylab.ssrf;

import java.net.InetAddress;
import java.net.URI;

/** Challenge 3: call validateHop before the initial request and every redirect. */
public final class SsrfUrlPolicy {
    private final HostResolver resolver;

    public SsrfUrlPolicy(HostResolver resolver) {
        this.resolver = resolver;
    }

    public URI validateHop(String rawUrl) {
        URI uri = URI.create(rawUrl);
        // Intentionally incomplete: textual localhost checks are bypassable.
        if (uri.getHost() == null || "localhost".equalsIgnoreCase(uri.getHost())) {
            throw new IllegalArgumentException("host is not allowed");
        }
        try {
            InetAddress first = resolver.resolveAll(uri.getHost()).get(0);
            if (first.isLoopbackAddress()) {
                throw new IllegalArgumentException("loopback is not allowed");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("cannot validate target", e);
        }
        return uri;
    }
}
