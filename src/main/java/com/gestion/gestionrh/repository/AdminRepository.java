package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {}