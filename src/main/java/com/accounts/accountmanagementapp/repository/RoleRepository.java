package com.accounts.accountmanagementapp.repository;

import com.accounts.accountmanagementapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
