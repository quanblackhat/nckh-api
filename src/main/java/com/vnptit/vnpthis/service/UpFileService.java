package com.vnptit.vnpthis.service;

import com.vnptit.vnpthis.domain.nckh.UpFile;
import com.vnptit.vnpthis.service.dto.UpFileDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link UpFile}.
 */
public interface UpFileService {

    /**
     * Save a upFile.
     *
     * @param upFileDTO the entity to save.
     * @return the persisted entity.
     */
    UpFileDTO save(UpFileDTO upFileDTO);

    /**
     * Get all the upFiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UpFileDTO> findAll(Pageable pageable);


    /**
     * Get the "id" upFile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UpFileDTO> findOne(Long id);

    /**
     * Delete the "id" upFile.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
