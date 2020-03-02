package com.vnptit.vnpthis.service;

import com.vnptit.vnpthis.service.dto.DuToanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.DuToan}.
 */
public interface DuToanService {

    /**
     * Save a duToan.
     *
     * @param duToanDTO the entity to save.
     * @return the persisted entity.
     */
    DuToanDTO save(DuToanDTO duToanDTO);

    /**
     * Get all the duToans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DuToanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" duToan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DuToanDTO> findOne(Long id);

    /**
     * Delete the "id" duToan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
