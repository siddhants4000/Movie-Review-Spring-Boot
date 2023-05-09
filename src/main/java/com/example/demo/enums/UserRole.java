package com.example.demo.enums;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.demo.enums.UserPermission.*;

public enum UserRole {
    USER(Sets.newHashSet(MOVIE_READ, REVIEW_READ, REVIEW_WRITE)),
    ADMIN(Sets.newHashSet(MOVIE_WRITE, MOVIE_READ, REVIEW_READ, REVIEW_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
}
