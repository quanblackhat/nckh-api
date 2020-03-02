package com.vnptit.vnpthis.service;

import com.vnptit.vnpthis.domain.nckh.NhanSu;
import com.vnptit.vnpthis.service.dto.NhanSuDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link NhanSu}.
 */
public interface NhanSuService {

    /**
     * Save a nhanSu.
     *
     * @param nhanSuDTO the entity to save.
     * @return the persisted entity.
     */
    NhanSuDTO save(NhanSuDTO nhanSuDTO);

    /**
     * Get all the nhanSus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NhanSuDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nhanSu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NhanSuDTO> findOne(Long id);

    /**
     * Delete the "id" nhanSu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
