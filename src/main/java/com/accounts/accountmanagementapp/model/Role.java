package com.accounts.accountmanagementapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Entity
@Table(name = "Roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    //TODO Подумать еще раз над regexp (возможно необходимо начало и конец строки с букв)
    @Email
    @Pattern(message = "Name should be correct", regexp = "[a-zA-Z\u0430-\u044F\u0410-\u042F-_ ]")
    @NotBlank(message = "Name is an important field")
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;
}
