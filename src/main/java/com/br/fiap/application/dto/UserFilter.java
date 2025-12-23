package com.br.fiap.application.dto;

public record UserFilter(
        String name,
        String email,
        String username,
        Boolean activeUser) {
}