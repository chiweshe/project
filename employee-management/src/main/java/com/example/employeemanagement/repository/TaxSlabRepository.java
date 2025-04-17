package com.example.employeemanagement.repository;

import com.example.employeemanagement.domain.Status;
import com.example.employeemanagement.domain.TaxSlab;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TaxSlabRepository extends JpaRepository<TaxSlab, Long>, JpaSpecificationExecutor<TaxSlab> {

    Optional<TaxSlab> findByIdAndStatusNot(Long id, Status status);
    List<TaxSlab> findAllByStatusNot(Status status);
    Page<TaxSlab> findAllByStatusNot(Status status, Pageable pageable);
    Optional<TaxSlab>findByLowerBoundAndUpperBoundAndRateAndStatusNot(BigDecimal lowerBound, BigDecimal upperBound,
                                                                      BigDecimal rate, Status status);
}
