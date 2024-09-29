package com.emporio.auth.infrastructure.repo;

import java.util.Optional;

import com.emporio.auth.infrastructure.entity.ERole;
import com.emporio.auth.infrastructure.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}