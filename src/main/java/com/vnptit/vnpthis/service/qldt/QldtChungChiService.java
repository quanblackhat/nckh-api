package com.vnptit.vnpthis.service.qldt;

import com.vnptit.vnpthis.service.dto.QldtChungChiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.QldtChungChi}.
 */
public interface QldtChungChiService {

    /**
     * Save a qldtChungChi.
     *
     * @param qldtChungChiDTO the entity to save.
     * @return the persisted entity.
     */
    QldtChungChiDTO save(QldtChungChiDTO qldtChungChiDTO);

    /**
     * Get all the qldtChungChis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QldtChungChiDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qldtChungChi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QldtChungChiDTO> findOne(Long id);

    /**
     * Delete the "id" qldtChungChi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
