package com.vnptit.vnpthis.service;

import com.vnptit.vnpthis.domain.nckh.LoaiDanhMuc;
import com.vnptit.vnpthis.service.dto.LoaiDanhMucDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link LoaiDanhMuc}.
 */
public interface LoaiDanhMucService {

    /**
     * Save a loaiDanhMuc.
     *
     * @param loaiDanhMucDTO the entity to save.
     * @return the persisted entity.
     */
    LoaiDanhMucDTO save(LoaiDanhMucDTO loaiDanhMucDTO);

    /**
     * Get all the loaiDanhMucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LoaiDanhMucDTO> findAll(Pageable pageable);


    /**
     * Get the "id" loaiDanhMuc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LoaiDanhMucDTO> findOne(Long id);

    /**
     * Delete the "id" loaiDanhMuc.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
