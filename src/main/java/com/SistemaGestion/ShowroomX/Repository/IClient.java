package com.SistemaGestion.ShowroomX.Repository;

import com.SistemaGestion.ShowroomX.Model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClient extends CrudRepository<Client, Long> {

    Page<Client> findAll(Pageable pageable);

    Long deleteByIdClient(Long idClient);
}
