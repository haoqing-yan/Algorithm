package com.donny.securitylab.jwt;

import java.time.Instant;
import java.util.Set;

public record JwtContext(boolean signatureValid, String algorithm, String keyId,
                         String issuer, Set<String> audience, String subject,
                         Instant expiresAt, long roleVersion) {}
