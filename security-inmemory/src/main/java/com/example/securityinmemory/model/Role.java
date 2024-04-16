package com.example.securityinmemory.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Column(name = "role_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "role_name")
    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_mapping", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
    private List<User> users;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
