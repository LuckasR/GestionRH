package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {}