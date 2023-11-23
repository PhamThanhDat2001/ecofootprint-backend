package com.doan.ecofootprint_be.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class CustomUserDetail implements UserDetails {
    private Users users;

    // Triển khai logic để trả về danh sách quyền của người dùng (GrantedAuthorities)
    // Ví dụ: có thể chuyển đổi Roles của người dùng thành GrantedAuthorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        // Assuming Role is an enum in your Account entity
        authorities.add(new SimpleGrantedAuthority("ROLE_" + users.getRole().name()));

        return authorities;
    }

    public CustomUserDetail(Users users) {
        this.users = users;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
