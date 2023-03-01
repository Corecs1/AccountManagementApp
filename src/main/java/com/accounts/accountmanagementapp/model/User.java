package com.accounts.accountmanagementapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
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
    private String firsName;

    @Pattern(message = "Last name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-]")
    @NotBlank(message = "Last name is an important field")
    @Column(name = "last_name")
    private String lastName;

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
    private LocalDateTime creationDate;

    @PrePersist
    private void initCreateTime() {
        creationDate = LocalDateTime.now();
    }
}
