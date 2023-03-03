package com.accounts.accountmanagementapp;

import com.accounts.accountmanagementapp.model.Role;
import com.accounts.accountmanagementapp.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AccountManagementAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountManagementAppApplication.class, args);
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository) {
        return args -> {
            Set<Role> roles = new HashSet<>();

            Role admin = Role.builder()
                    .name("ROLE_ADMIN")
                    .build();

            Role employee = Role.builder()
                    .name("ROLE_EMPLOYEE")
                    .build();

            Role observer = Role.builder()
                    .name("ROLE_USER")
                    .build();

            roles.add(admin);
            roles.add(employee);
            roles.add(observer);

            roleRepository.saveAll(roles);
        };
    }
}
