package com.vnptit.vnpthis.service.qldt;

import com.vnptit.vnpthis.service.dto.QldtTochucCapDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.QldtTochucCap}.
 */
public interface QldtTochucCapService {

    /**
     * Save a qldtTochucCap.
     *
     * @param qldtTochucCapDTO the entity to save.
     * @return the persisted entity.
     */
    QldtTochucCapDTO save(QldtTochucCapDTO qldtTochucCapDTO);

    /**
     * Get all the qldtTochucCaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QldtTochucCapDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qldtTochucCap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QldtTochucCapDTO> findOne(Long id);

    /**
     * Delete the "id" qldtTochucCap.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
