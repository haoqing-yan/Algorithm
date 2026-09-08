package com.donny.securitylab;

import com.donny.securitylab.jwt.JwtClaimsValidator;
import com.donny.securitylab.sql.OrderByPolicy;
import com.donny.securitylab.ssrf.SsrfUrlPolicy;
import org.junit.jupiter.api.Test;
import java.net.InetAddress;
import java.time.Clock;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SmokeTest {
    @Test void exerciseClassesCanBeConstructed() throws Exception {
        assertNotNull(new OrderByPolicy());
        assertNotNull(new JwtClaimsValidator("issuer", "api", Set.of("key-1"), Clock.systemUTC()));
        assertNotNull(new SsrfUrlPolicy(host -> List.of(InetAddress.getByName("8.8.8.8"))));
    }
}
