package com.donny.securitylab.jwt;

import java.time.Clock;
import java.util.Set;

/** Challenge 2: signature validity alone is not an authorization decision. */
public final class JwtClaimsValidator {
    private final String expectedIssuer;
    private final String expectedAudience;
    private final Set<String> trustedKeyIds;
    private final Clock clock;

    public JwtClaimsValidator(String expectedIssuer, String expectedAudience,
                              Set<String> trustedKeyIds, Clock clock) {
        this.expectedIssuer = expectedIssuer;
        this.expectedAudience = expectedAudience;
        this.trustedKeyIds = Set.copyOf(trustedKeyIds);
        this.clock = clock;
    }

    public boolean isAccepted(JwtContext token, long currentRoleVersion) {
        // TODO SECURITY: complete the checks described in README.
        return token != null && token.signatureValid() && token.expiresAt() != null
                && clock.instant().isBefore(token.expiresAt());
    }
}
