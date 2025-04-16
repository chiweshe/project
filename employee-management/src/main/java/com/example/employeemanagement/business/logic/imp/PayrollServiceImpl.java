package com.example.employeemanagement.business.logic.imp;

import com.example.employeemanagement.business.logic.api.EmployeeService;
import com.example.employeemanagement.business.logic.api.PayrollService;

public class PayrollServiceImpl  implements PayrollService {

//    @Autowired
//    private SalaryStructureRepository salaryStructureRepo;
//
//    @Autowired
//    private EmployeeAllowanceRepository allowanceRepo;
//
//    @Autowired
//    private EmployeeDeductionRepository deductionRepo;
//
//    public SalaryBreakdownDTO calculateSalary(Long employeeId) {
//        SalaryStructure salary = salaryStructureRepo.findByEmployeeIdAndIsActiveTrue(employeeId)
//                .orElseThrow(() -> new RuntimeException("Salary structure not found"));
//
//        // Fetch dynamic allowances
//        List<EmployeeAllowance> allowances = allowanceRepo.findByEmployeeId(employeeId);
//        BigDecimal dynamicAllowances = allowances.stream()
//                .map(EmployeeAllowance::getAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        // Fetch dynamic deductions
//        List<EmployeeDeduction> deductions = deductionRepo.findByEmployeeId(employeeId);
//        BigDecimal dynamicDeductions = deductions.stream()
//                .map(EmployeeDeduction::getAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        // Calculate totals
//        BigDecimal totalAllowances = salary.getHra()
//                .add(salary.getBonus())
//                .add(salary.getOtherAllowances())
//                .add(dynamicAllowances);
//
//        BigDecimal totalDeductions = salary.getDeductions()
//                .add(dynamicDeductions);
//
//        BigDecimal grossPay = salary.getBasicSalary().add(totalAllowances);
//        BigDecimal netPay = grossPay.subtract(totalDeductions);
//
//        // Prepare result DTO
//        SalaryBreakdownDTO result = new SalaryBreakdownDTO();
//        result.setBasicSalary(salary.getBasicSalary());
//        result.setTotalAllowances(totalAllowances);
//        result.setTotalDeductions(totalDeductions);
//        result.setGrossPay(grossPay);
//        result.setNetPay(netPay);
//
//        return result;
//    }
//}
}
