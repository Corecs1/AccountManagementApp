package com.accounts.accountmanagementapp.repository;

import com.accounts.accountmanagementapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
