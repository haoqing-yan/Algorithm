package com.donny.securitylab.jwt;

import org.junit.jupiter.api.*;
import java.time.*;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class JwtClaimsValidatorGraderTest {
    private static final Instant NOW = Instant.parse("2026-09-08T00:00:00Z");
    private JwtClaimsValidator validator;

    @BeforeEach void setUp() {
        validator = new JwtClaimsValidator("https://identity.example.test", "order-api",
                Set.of("rsa-2026-01"), Clock.fixed(NOW, ZoneOffset.UTC));
    }

    @Test void acceptsOnlyCompleteTrustedContext() { assertTrue(validator.isAccepted(valid(), 7)); }

    @Test void rejectsCrossServiceAndStalePrivilegeTokens() {
        assertFalse(validator.isAccepted(copy(Set.of("billing-api"), "RS256", "rsa-2026-01", 7), 7));
        assertFalse(validator.isAccepted(copy(Set.of("order-api"), "RS256", "rsa-2026-01", 6), 7));
    }

    @Test void rejectsAlgorithmAndKeySelectionConfusion() {
        assertFalse(validator.isAccepted(copy(Set.of("order-api"), "HS256", "rsa-2026-01", 7), 7));
        assertFalse(validator.isAccepted(copy(Set.of("order-api"), "RS256", "../../tmp/key", 7), 7));
    }

    @Test void rejectsIncompleteOrWrongClaims() {
        JwtContext v = valid();
        assertFalse(validator.isAccepted(new JwtContext(true, v.algorithm(), v.keyId(), "other", v.audience(), v.subject(), v.expiresAt(), 7), 7));
        assertFalse(validator.isAccepted(new JwtContext(true, v.algorithm(), v.keyId(), v.issuer(), v.audience(), "  ", v.expiresAt(), 7), 7));
        assertFalse(validator.isAccepted(new JwtContext(false, v.algorithm(), v.keyId(), v.issuer(), v.audience(), v.subject(), v.expiresAt(), 7), 7));
    }

    private JwtContext valid() {
        return new JwtContext(true, "RS256", "rsa-2026-01", "https://identity.example.test",
                Set.of("order-api", "profile-api"), "user-1001", NOW.plusSeconds(300), 7);
    }
    private JwtContext copy(Set<String> aud, String alg, String kid, long version) {
        JwtContext v = valid();
        return new JwtContext(true, alg, kid, v.issuer(), aud, v.subject(), v.expiresAt(), version);
    }
}
