package com.SistemaGestion.ShowroomX.Repository;

import com.SistemaGestion.ShowroomX.Model.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvider extends CrudRepository<Provider, Long> {

    Page<Provider> findAll(Pageable pageable);

    long deleteByIdProvider(Long idProvider);

    long deleteByName(String name);

}
