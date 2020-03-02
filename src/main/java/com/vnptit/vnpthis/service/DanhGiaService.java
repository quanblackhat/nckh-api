package com.vnptit.vnpthis.service;

import com.vnptit.vnpthis.domain.nckh.DanhGia;
import com.vnptit.vnpthis.service.dto.DanhGiaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link DanhGia}.
 */
public interface DanhGiaService {

    /**
     * Save a danhGia.
     *
     * @param danhGiaDTO the entity to save.
     * @return the persisted entity.
     */
    DanhGiaDTO save(DanhGiaDTO danhGiaDTO);

    /**
     * Get all the danhGias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DanhGiaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" danhGia.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhGiaDTO> findOne(Long id);

    /**
     * Delete the "id" danhGia.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
