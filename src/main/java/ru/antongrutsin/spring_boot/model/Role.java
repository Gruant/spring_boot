package ru.antongrutsin.spring_boot.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
public class Role implements GrantedAuthority {
    @Id
    private int id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(int id, String name) {
    }

    public Role() {

    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
