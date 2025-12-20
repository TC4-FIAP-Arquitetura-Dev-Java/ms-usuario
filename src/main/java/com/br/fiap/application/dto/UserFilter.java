package com.br.fiap.application.dto;

public record UserFilter(
        String name,
        String email,
        String username,
        Boolean activeUser,
        Integer limit,
        Integer offset) {

    public static UserFilter empty() {
        return new UserFilter(null, null, null, null, null, null);
    }
}