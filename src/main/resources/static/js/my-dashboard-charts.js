'use strict';

document.addEventListener('DOMContentLoaded', function () {
  
  // --- Helper Function: Parse Lists [1,2,3] or "A,B,C" ---
  function parseData(dataString) {
    if (!dataString) return [];
    // Removes brackets if they exist (common in Java toString) and splits
    return dataString.replace(/[\[\]]/g, '').split(',').map(Number);
  }

  function parseLabels(labelString) {
    if (!labelString) return [];
    return labelString.split(',');
  }

  // =======================================================
  // 1. Service Distribution Chart
  // =======================================================
  const serviceEl = document.querySelector('#serviceDistributionChart');
  if (serviceEl) {
    const options = {
      chart: { type: 'donut', height: 280 },
      // Read from HTML data-series
      series: parseData(serviceEl.dataset.series),
      // Read from HTML data-labels
      labels: parseLabels(serviceEl.dataset.labels),
      legend: { show: false },
      dataLabels: { enabled: false },
      colors: ['#696cff', '#8592a3', '#71dd37', '#03c3ec']
    };
    new ApexCharts(serviceEl, options).render();
  }

  // =======================================================
  // 2. Contract Chart
  // =======================================================
  const contractEl = document.querySelector('#contractChart');
  if (contractEl) {
    const options = {
      chart: { type: 'pie', height: 280 },
      series: parseData(contractEl.dataset.series),
      labels: parseLabels(contractEl.dataset.labels),
       colors: ['#696cff', '#03c3ec', '#71dd37'],
      legend: { position: 'bottom' },
      noData: { text: 'No Data Available' }
    };
    new ApexCharts(contractEl, options).render();
  }

  // =======================================================
  // 3. Age Chart (Bar)
  // =======================================================
 // =======================================================
  // 3. Age Chart (Histogram / Vertical Bar)
  // =======================================================
  const ageEl = document.querySelector('#ageChart');
  if (ageEl) {
    const options = {
      // 1. Change type to 'bar' (same as before)
      chart: { 
          type: 'bar', 
          height: 250, 
          toolbar: { show: false } 
      },
      series: [{ 
        name: 'Employés', 
        data: parseData(ageEl.dataset.series) 
      }],
      xaxis: { 
        categories: ['18-24', '25-30', '30-40', '40-50', '50+'],
        axisBorder: { show: false },
        axisTicks: { show: false }
      },
      plotOptions: {
        bar: { 
          borderRadius: 4, 
          horizontal: false,       // <--- CHANGE TO FALSE (Vertical)
          columnWidth: '55%',      // <--- Adjust width for Histogram look
          endingShape: 'rounded'
        }
      },
      dataLabels: { 
          enabled: false           // Cleaner look for histograms
      }, 
      grid: {
        strokeDashArray: 4,        // Dotted grid lines
      },
      colors: ['#696cff']
    };
    new ApexCharts(ageEl, options).render();
  }
});