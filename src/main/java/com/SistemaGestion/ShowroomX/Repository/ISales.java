package com.SistemaGestion.ShowroomX.Repository;

import com.SistemaGestion.ShowroomX.Model.Sales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISales extends CrudRepository<Sales, Long> {

    Page<Sales> findAll(Pageable pageable);

    List<Sales> findByDate(java.sql.Date date);

    List<Sales> findByPaymentTpye(String paymentType);

    @Query(value = "SELECT * FROM Sales WHERE FK_CLIENT = ?1", nativeQuery = true)
    List<Sales> findByIdClient(@Param("idClient") Long idClient);

    Long deleteByIdSales(Long idSales);

    Long deleteByDate(java.sql.Date date);

}
