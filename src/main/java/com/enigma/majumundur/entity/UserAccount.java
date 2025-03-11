package com.enigma.majumundur.entity;

import com.enigma.majumundur.constant.DbBash;
import com.enigma.majumundur.constant.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = DbBash.USER_ACCOUNT)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserRole> myRole = List.of(role);
        return myRole.stream().map(userRole -> new SimpleGrantedAuthority(userRole.name())).toList();
    }
}
