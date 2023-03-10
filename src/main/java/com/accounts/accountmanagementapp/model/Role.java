package com.accounts.accountmanagementapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id", columnDefinition = "UUID")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Pattern(message = "Name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-_ ]+")
    @NotBlank(message = "Name is an important field")
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Override
    public String getAuthority() {
        return getName();
    }
}
