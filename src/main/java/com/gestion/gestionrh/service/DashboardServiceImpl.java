package com.gestion.gestionrh.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService{

    @PersistenceContext
    private EntityManager em;

    // 1. KPI: Total Employees
    public long getTotalEmployees() {
        String sql = "SELECT COUNT(*) FROM contrat_employee WHERE has_active = true";
        Number result = (Number) em.createNativeQuery(sql).getSingleResult();
        return result != null ? result.longValue() : 0;
    }

    // 2. KPI: Average Seniority
    public double getAvgSeniority() {
        String sql = "SELECT AVG(EXTRACT(YEAR FROM AGE(CURRENT_DATE, c.date_debut))) " +
                     "FROM contrat_employee c WHERE c.has_active = true";
        Number result = (Number) em.createNativeQuery(sql).getSingleResult();
        return result != null ? Math.round(result.doubleValue() * 10.0) / 10.0 : 0.0;
    }

    // 3. KPI: Turnover Rate (Using the complex logic)
    public double getTurnoverRate() {
        String sql = "WITH Departures AS (" +
                     "    SELECT COUNT(*) as count FROM contrat_employee " +
                     "    WHERE has_active = false AND date_fin > (CURRENT_DATE - INTERVAL '1 year') " +
                     "), Total AS (" +
                     "    SELECT COUNT(*) as count FROM contrat_employee WHERE has_active = true " +
                     ") " +
                     "SELECT CASE WHEN (SELECT count FROM Total) = 0 THEN 0 " +
                     "ELSE ((SELECT count FROM Departures)::numeric / (SELECT count FROM Total)::numeric) * 100 END";
        
        Number result = (Number) em.createNativeQuery(sql).getSingleResult();
        return result != null ? Math.round(result.doubleValue() * 10.0) / 10.0 : 0.0;
    }

    // 4. CHART: Department Distribution
    // Returns List of Object[]: [DepartmentName, Count]
    public List<Object[]> getDepartmentStats() {
        // Adjust the JOINs if your department table structure is different
        String sql = "SELECT d.name, COUNT(e.id) " +
                     "FROM employee e " +
                     "JOIN contrat_employee c ON c.employee_id = e.id " +
                     "JOIN departement d ON e.departement_id = d.id " + 
                     "WHERE c.has_active = true " +
                     "GROUP BY d.name";
        return em.createNativeQuery(sql).getResultList();
    }

    // 5. CHART: Contract Type Distribution
    // Returns List of Object[]: [Type, Count]
    public List<Object[]> getContractStats() {
        String sql = "SELECT " +
                     "CASE WHEN date_fin IS NULL THEN 'CDI' ELSE 'CDD' END as type, " +
                     "COUNT(*) " +
                     "FROM contrat_employee " +
                     "WHERE has_active = true " +
                     "GROUP BY CASE WHEN date_fin IS NULL THEN 'CDI' ELSE 'CDD' END";
        return em.createNativeQuery(sql).getResultList();
    }
    
    // 6. CHART: Age Pyramid
    // Returns List of Object[]: [Range, Count]
    public List<Object[]> getAgeStats() {
          String sql = 
        "SELECT " +
        "  CASE " +
        "    WHEN age BETWEEN 18 AND 24 THEN '18-24' " +
        "    WHEN age BETWEEN 25 AND 30 THEN '25-30' " +
        "    WHEN age BETWEEN 31 AND 40 THEN '31-40' " +
        "    WHEN age BETWEEN 41 AND 50 THEN '41-50' " +
        "    ELSE '50+' " +
        "  END as age_range, " +
        "  COUNT(*) " +
        "FROM ( " +
        "  SELECT EXTRACT(YEAR FROM AGE(info.date_naissance)) as age " +
        "  FROM information_employee info " +
        "  JOIN contrat_employee c ON c.employee_id = info.employee_id " +
        "  WHERE c.has_active = true " +
        ") as sub_query " +
        "GROUP BY age_range";
        return em.createNativeQuery(sql).getResultList();
    }

     public List<Object[]> getLeaveAlerts() {

        // NEW: Get employees with the most unused leaves
    // FIXED: Dynamically finds the latest year (MAX(annee)) instead of hardcoding current year
        
        String sql = "SELECT info.first_name, info.last_name, s.jours_restants, s.jours_acquis " +
                     "FROM solde_conge s " +
                     "JOIN information_employee info ON s.employee_id = info.employee_id " +
                     "WHERE s.annee = (SELECT MAX(annee) FROM solde_conge) " + // <--- THE FIX
                     "AND s.jours_restants > 10 " + 
                     "ORDER BY s.jours_restants DESC " +
                     "LIMIT 5";
        

        return em.createNativeQuery(sql)
                 .getResultList();
    }

    // NEW: Get Expiring Contracts (Upcoming or Recently Expired)
    // Returns: [FirstName, LastName, DateFin, DaysRemaining]
   public List<Object[]> getExpiringContracts() {
    String sql = "SELECT info.first_name, info.last_name, c.date_fin, " +
                 // FIX 1: Direct subtraction (returns integer in Postgres)
                 "(c.date_fin - CURRENT_DATE) as days_left " + 
                 "FROM contrat_employee c " +
                 "JOIN information_employee info ON c.employee_id = info.employee_id " +
                 "WHERE c.has_active = true " +
                 "AND c.date_fin IS NOT NULL " + 
                 // FIX 2: Removed the "BETWEEN" restriction so old data shows up
                 "ORDER BY c.date_fin DESC " + // Show the most recent dates first
                 "LIMIT 5";

    return em.createNativeQuery(sql).getResultList();
}

// NEW: Get Budget Alerts (Simulated based on Salary Mass)
    // Returns: [DeptName, BudgetAllocated, BudgetUsed, Percentage]
    public List<Object[]> getBudgetAlerts() {
        // Logic:
        // 1. Group by Department
        // 2. Budget Allocated = 12,000 (Arbitrary fixed limit for demo)
        // 3. Budget Used = Sum of (salaire_base) * 0.5 (Simulation: Training costs are high!)
        // 4. We only show departments where 'Used' > 80% of 'Allocated'
        
        String sql = "SELECT " +
                     "  d.name, " +
                     "  12000 AS budget_allocated, " +
                     "  CAST(SUM(p.salaire_base) * 0.8 AS INTEGER) AS budget_used, " +
                     "  CAST((SUM(p.salaire_base) * 0.8 / 12000) * 100 AS INTEGER) AS percentage " +
                     "FROM departement d " +
                     "JOIN poste p ON p.departement_id = d.id " +
                     "GROUP BY d.name " +
                     "HAVING (SUM(p.salaire_base) * 0.8) > 8000 " + // Filter: Show only high spenders
                     "ORDER BY percentage DESC " +
                     "LIMIT 5";

        return em.createNativeQuery(sql).getResultList();
    }
}