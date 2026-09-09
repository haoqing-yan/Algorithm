package com.donny.securitylab.ssrf;

import org.junit.jupiter.api.Test;
import java.net.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SsrfUrlPolicyGraderTest {
    @Test void acceptsHttpPublicAddress() throws Exception {
        SsrfUrlPolicy p = policyFor(List.of(InetAddress.getByName("93.184.216.34")));
        assertEquals(URI.create("https://images.example.test/a.png"), p.validateHop("https://images.example.test/a.png"));
    }

    @Test void rejectsDangerousSchemesAndCredentials() throws Exception {
        SsrfUrlPolicy p = policyFor(List.of(InetAddress.getByName("93.184.216.34")));
        assertThrows(IllegalArgumentException.class, () -> p.validateHop("file:///etc/passwd"));
        assertThrows(IllegalArgumentException.class, () -> p.validateHop("https://user:pass@images.example.test/a.png"));
    }

    @Test void rejectsIfAnyResolvedAddressIsPrivate() throws Exception {
        SsrfUrlPolicy p = policyFor(List.of(InetAddress.getByName("93.184.216.34"), InetAddress.getByName("10.0.0.8")));
        assertThrows(IllegalArgumentException.class, () -> p.validateHop("https://mixed.example.test/a.png"));
    }

    @Test void rejectsIpv6LoopbackAndLinkLocalAddresses() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> policyFor(List.of(InetAddress.getByName("::1"))).validateHop("https://internal.example.test/a.png"));
        assertThrows(IllegalArgumentException.class, () -> policyFor(List.of(InetAddress.getByName("169.254.169.254"))).validateHop("https://metadata.example.test/latest"));
    }

    @Test void rejectsResolutionFailureAndEmptyResults() {
        SsrfUrlPolicy failed = new SsrfUrlPolicy(host -> { throw new UnknownHostException(host); });
        SsrfUrlPolicy empty = new SsrfUrlPolicy(host -> List.of());
        assertThrows(IllegalArgumentException.class, () -> failed.validateHop("https://missing.example.test/a.png"));
        assertThrows(IllegalArgumentException.class, () -> empty.validateHop("https://empty.example.test/a.png"));
    }

    private SsrfUrlPolicy policyFor(List<InetAddress> addresses) { return new SsrfUrlPolicy(host -> addresses); }
}
