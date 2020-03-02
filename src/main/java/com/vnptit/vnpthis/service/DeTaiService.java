package com.vnptit.vnpthis.service;

import com.vnptit.vnpthis.service.dto.DeTaiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.DeTai}.
 */
public interface DeTaiService {

    /**
     * Save a deTai.
     *
     * @param deTaiDTO the entity to save.
     * @return the persisted entity.
     */
    DeTaiDTO save(DeTaiDTO deTaiDTO);

    /**
     * Get all the deTais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeTaiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" deTai.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeTaiDTO> findOne(Long id);

    /**
     * Delete the "id" deTai.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
