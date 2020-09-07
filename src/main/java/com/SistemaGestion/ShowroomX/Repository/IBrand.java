package com.SistemaGestion.ShowroomX.Repository;

import com.SistemaGestion.ShowroomX.Model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IBrand extends CrudRepository<Brand, Long> {

    Page<Brand> findAll(Pageable pageable);

    Long deleteByName(String name);

    Long deleteByIdBrand(Long idBrand);

    Brand findByName(String name);

    List<Brand> findByStock(Integer stock);

}
