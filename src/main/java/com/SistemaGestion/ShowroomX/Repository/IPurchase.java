package com.SistemaGestion.ShowroomX.Repository;

import com.SistemaGestion.ShowroomX.Model.Purchases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchase extends CrudRepository<Purchases, Long> {

    Page<Purchases> findAll(Pageable pageable);

    Long deleteByIdPurchases(Long idPurchases);
}
