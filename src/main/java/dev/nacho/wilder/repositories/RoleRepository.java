package dev.nacho.wilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.nacho.wilder.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}