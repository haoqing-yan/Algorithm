package com.donny.securitylab.sql;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderByPolicyGraderTest {
    private final OrderByPolicy policy = new OrderByPolicy();

    @Test void mapsOnlyServerControlledColumns() {
        assertEquals("created_at ASC", policy.toOrderBy("createdAt", "asc"));
        assertEquals("amount_cents DESC", policy.toOrderBy("amount", "DESC"));
        assertEquals("status ASC", policy.toOrderBy("status", "ASC"));
    }

    @Test void rejectsSqlStructureInEitherParameter() {
        assertThrows(IllegalArgumentException.class, () -> policy.toOrderBy("created_at, (select 1)", "asc"));
        assertThrows(IllegalArgumentException.class, () -> policy.toOrderBy("amount", "desc nulls last"));
        assertThrows(IllegalArgumentException.class, () -> policy.toOrderBy(null, "asc"));
    }
}
