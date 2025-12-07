package com.gestion.gestionrh.service;
import java.util.List;
public interface DashboardService {

    // 1. KPI: Total Employees
    long getTotalEmployees();
    // 2. KPI: Average Seniority
    double getAvgSeniority();
    // 3. KPI: Turnover Rate (Using the complex logic)
    double getTurnoverRate();

    // 4. CHART: Department Distribution
    // Returns List of Object[]: [DepartmentName, Count]
    List<Object[]> getDepartmentStats();

    // 5. CHART: Contract Type Distribution
    // Returns List of Object[]: [Type, Count]
    List<Object[]> getContractStats();
    
    // 6. CHART: Age Pyramid
    // Returns List of Object[]: [Range, Count]
    List<Object[]> getAgeStats();

    List<Object[]> getLeaveAlerts();

    List<Object[]> getExpiringContracts();

    List<Object[]> getBudgetAlerts();

    int getAvgAge();

}