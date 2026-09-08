package com.donny.securitylab.sql;

/** Challenge 1: returned text is inserted by MyBatis ${orderBy}. */
public final class OrderByPolicy {
    public String toOrderBy(String field, String direction) {
        // TODO SECURITY: never return client-controlled SQL structure directly.
        return field + " " + direction;
    }
}
