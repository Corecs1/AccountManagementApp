package com.accounts.accountmanagementapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @Column(name = "id", columnDefinition = "UUID")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotBlank(message = "Email is an important field")
    @Email(message = "Incorrect email")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Password is an important field")
    @Column(name = "password")
    private String password;

    @Pattern(message = "First name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]")
    @NotBlank(message = "First name is an important field")
    @Column(name = "first_name")
    private String name;

    @Pattern(message = "Last name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]")
    @NotBlank(message = "Last name is an important field")
    @Column(name = "last_name")
    private String familyName;

    @Pattern(message = "Middle name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]")
    @Column(name = "middle_name")
    private String middleName;

    @NotBlank(message = "Role is an important field")
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @NotBlank(message = "Status is an important field")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "creation_date")
    private LocalDateTime createdAt;

    @PrePersist
    private void initCreateTime() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
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
