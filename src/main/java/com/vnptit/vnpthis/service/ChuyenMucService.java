package com.vnptit.vnpthis.service;

import com.vnptit.vnpthis.domain.nckh.ChuyenMuc;
import com.vnptit.vnpthis.service.dto.ChuyenMucDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ChuyenMuc}.
 */
public interface ChuyenMucService {

    /**
     * Save a chuyenMuc.
     *
     * @param chuyenMucDTO the entity to save.
     * @return the persisted entity.
     */
    ChuyenMucDTO save(ChuyenMucDTO chuyenMucDTO);

    /**
     * Get all the chuyenMucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChuyenMucDTO> findAll(Pageable pageable);


    /**
     * Get the "id" chuyenMuc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChuyenMucDTO> findOne(Long id);

    /**
     * Delete the "id" chuyenMuc.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
