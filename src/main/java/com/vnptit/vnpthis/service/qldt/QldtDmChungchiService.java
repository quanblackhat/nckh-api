package com.vnptit.vnpthis.service.qldt;

import com.vnptit.vnpthis.service.dto.QldtDmChungchiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.QldtDmChungchi}.
 */
public interface QldtDmChungchiService {

    /**
     * Save a qldtDmChungchi.
     *
     * @param qldtDmChungchiDTO the entity to save.
     * @return the persisted entity.
     */
    QldtDmChungchiDTO save(QldtDmChungchiDTO qldtDmChungchiDTO);

    /**
     * Get all the qldtDmChungchis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QldtDmChungchiDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qldtDmChungchi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QldtDmChungchiDTO> findOne(Long id);

    /**
     * Delete the "id" qldtDmChungchi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
