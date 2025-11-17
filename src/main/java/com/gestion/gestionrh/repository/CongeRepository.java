package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gestion.gestionrh.model.Conge;
import com.gestion.gestionrh.model.Employee;
import com.gestion.gestionrh.model.Status_general;
import java.util.List;

public interface CongeRepository extends JpaRepository<Conge, Integer> {
    List<Conge> findByEmployee(Employee employee);
    
    @Query("SELECT c FROM Conge c WHERE c.status_general = :status")
    List<Conge> findByStatus_general(@Param("status") Status_general status);
    
    List<Conge> findBySuperviseur(Employee superviseur);
}