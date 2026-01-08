package com.br.fiap.infraestrutura.config.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MyUserDetailsTest {

    @Test
    void shouldCreateUserDetailsWithBuilder() {
        MyUserDetails userDetails = MyUserDetails.builder()
                .userId(1L)
                .username("gustavo")
                .password("123456")
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .build();

        assertThat(userDetails.getUserId()).isEqualTo(1L);
        assertThat(userDetails.getUsername()).isEqualTo("gustavo");
        assertThat(userDetails.getPassword()).isEqualTo("123456");
        assertThat(userDetails.getAuthorities())
                .extracting(GrantedAuthority::getAuthority)
                .containsExactly("ROLE_USER");
    }

    @Test
    void shouldReturnAccountNonExpiredAsTrue() {
        MyUserDetails userDetails = MyUserDetails.builder().build();

        assertThat(userDetails.isAccountNonExpired()).isTrue();
    }

    @Test
    void shouldReturnAccountNonLockedAsTrue() {
        MyUserDetails userDetails = MyUserDetails.builder().build();

        assertThat(userDetails.isAccountNonLocked()).isTrue();
    }

    @Test
    void shouldReturnCredentialsNonExpiredAsTrue() {
        MyUserDetails userDetails = MyUserDetails.builder().build();

        assertThat(userDetails.isCredentialsNonExpired()).isTrue();
    }

    @Test
    void shouldReturnEnabledAsTrue() {
        MyUserDetails userDetails = MyUserDetails.builder().build();

        assertThat(userDetails.isEnabled()).isTrue();
    }

    @Test
    void shouldAllowEmptyAuthorities() {
        MyUserDetails userDetails = MyUserDetails.builder()
                .authorities(List.of())
                .build();

        assertThat(userDetails.getAuthorities()).isEmpty();
    }
}
