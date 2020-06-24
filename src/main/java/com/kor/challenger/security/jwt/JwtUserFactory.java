package com.kor.challenger.security.jwt;

import com.kor.challenger.domain.Role;
import com.kor.challenger.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {

    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.isActive(),
                mapToGrantedAuthority(new HashSet<>(user.getRoles()))
        );
    }

    private static Set<GrantedAuthority> mapToGrantedAuthority(Set<Role> userRoles) {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toSet());
    }
}
