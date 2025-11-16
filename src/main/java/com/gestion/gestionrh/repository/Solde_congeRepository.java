package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gestion.gestionrh.model.Solde_conge;
import com.gestion.gestionrh.model.Employee;
import com.gestion.gestionrh.model.Type_conge;
import java.util.List;

public interface Solde_congeRepository extends JpaRepository<Solde_conge, Integer> {
    List<Solde_conge> findByEmployee(Employee employee);
    
    @Query("SELECT s FROM Solde_conge s WHERE s.employee = :employee AND s.type_conge = :typeConge AND s.annee = :annee")
    Solde_conge findByEmployeeAndType_congeAndAnnee(@Param("employee") Employee employee, @Param("typeConge") Type_conge typeConge, @Param("annee") Integer annee);
}