package com.vnptit.vnpthis.service;

import com.vnptit.vnpthis.domain.nckh.TienDo;
import com.vnptit.vnpthis.service.dto.TienDoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link TienDo}.
 */
public interface TienDoService {

    /**
     * Save a tienDo.
     *
     * @param tienDoDTO the entity to save.
     * @return the persisted entity.
     */
    TienDoDTO save(TienDoDTO tienDoDTO);

    /**
     * Get all the tienDos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TienDoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tienDo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TienDoDTO> findOne(Long id);

    /**
     * Delete the "id" tienDo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
