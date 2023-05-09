package com.example.demo.enums;

public enum UserPermission {
    MOVIE_READ("movie:read"),
    MOVIE_WRITE("movie:write"),
    REVIEW_READ("review:read"),
    REVIEW_WRITE("review:write");

    private final String permission;

    UserPermission(String permission){
        this.permission=permission;
    }

    public String getPermission() {
        return permission;
    }
}
