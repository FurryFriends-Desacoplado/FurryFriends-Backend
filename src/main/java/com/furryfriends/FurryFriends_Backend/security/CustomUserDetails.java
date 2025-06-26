package com.furryfriends.FurryFriends_Backend.security;

import com.furryfriends.FurryFriends_Backend.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Agregamos el prefijo ROLE_ para que Spring Security reconozca el rol
        String roleWithPrefix = "ROLE_" + user.getRole();
        return Collections.singletonList(new SimpleGrantedAuthority(roleWithPrefix));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();  // Usamos email como username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Implementa según tu lógica si quieres
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Implementa según tu lógica si quieres
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Implementa según tu lógica si quieres
    }

    @Override
    public boolean isEnabled() {
        return true;  // Implementa según tu lógica si quieres
    }

    // Método para devolver el rol original sin prefijo para usarlo en la respuesta del login
    public String getRole() {
        return user.getRole();
    }

    public String getNombre() {
        return user.getNombre();
    }

    public String getApellido() {
        return user.getApellido();
    }
}
