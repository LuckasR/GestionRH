package com.gestion.gestionrh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gestion.gestionrh.service.DashboardService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;


import java.util.Map;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public String index(Model model) {

        // ==========================================
        // 1. KPI CARDS (Real Data)
        // ==========================================
        model.addAttribute("turnoverRate", dashboardService.getTurnoverRate());
        model.addAttribute("absenceRate", 4.2); // Still static or add method in service
        model.addAttribute("avgSeniority", dashboardService.getAvgSeniority());
        model.addAttribute("totalEmployees", dashboardService.getTotalEmployees());
        model.addAttribute("avgAge", 29); // You can add a query for this in service too

        // ==========================================
        // 2. SERVICE CHART (Donut)
        // ==========================================
        List<Object[]> deptStats = dashboardService.getDepartmentStats();
        List<String> serviceLabels = new ArrayList<>();
        List<Number> serviceCounts = new ArrayList<>();

        for (Object[] row : deptStats) {
            serviceLabels.add((String) row[0]); // Department Name
            serviceCounts.add((Number) row[1]); // Count
        }

        model.addAttribute("serviceLabels", serviceLabels);
        model.addAttribute("serviceCounts", serviceCounts);

        // ==========================================
        // 3. CONTRACT CHART (Pie)
        // ==========================================
        List<Object[]> contractStats = dashboardService.getContractStats();
        List<String> contractLabels = new ArrayList<>();
        List<Number> contractCounts = new ArrayList<>();

        for (Object[] row : contractStats) {
            contractLabels.add((String) row[0]); // 'CDI' or 'CDD'
            contractCounts.add((Number) row[1]); // Count
        }

        model.addAttribute("contractLabels", contractLabels);
        model.addAttribute("contractCounts", contractCounts);

        // Fetch raw list: [First, Last, Restants, Acquis]
        List<Object[]> rawLeaves = dashboardService.getLeaveAlerts();

        // We can pass the raw list directly to Thymeleaf and access via index array
        model.addAttribute("leaveAlerts", rawLeaves);

        // ==========================================
        // 4. AGE PYRAMID (Horizontal Bar)
        // ==========================================
        // Note: The SQL handles the categories, but we need to ensure the order matches
        List<Object[]> ageStats = dashboardService.getAgeStats();
        // Define the EXACT order of labels for the chart
        List<String> expectedLabels = Arrays.asList("18-24", "25-30", "31-40", "41-50", "50+");
        List<Number> ageDistribution = new ArrayList<>();

        Map<String, Number> statsMap = new HashMap<>();
        for (Object[] row : ageStats) {
            statsMap.put((String) row[0], (Number) row[1]);
        }

        // Loop through expected labels to ensure order and fill '0' for missing data
        for (String label : expectedLabels) {
            ageDistribution.add(statsMap.getOrDefault(label, 0));
        }

        // Result sent to HTML: [0, 2, 7, 1, 0]
        model.addAttribute("ageDistribution", ageDistribution);

          List<Object[]> expiringContracts = dashboardService.getExpiringContracts();
        model.addAttribute("expiringContracts", expiringContracts);

        List<Object[]> budgetAlerts = dashboardService.getBudgetAlerts();
        model.addAttribute("budgetAlerts", budgetAlerts);


    


        return "dashboard/index";
    }
}