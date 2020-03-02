package com.vnptit.vnpthis.service.qldt;

import com.vnptit.vnpthis.service.dto.QldtQlHocvienCtDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.QldtQlHocvienCt}.
 */
public interface QldtQlHocvienCtService {

    /**
     * Save a qldtQlHocvienCt.
     *
     * @param qldtQlHocvienCtDTO the entity to save.
     * @return the persisted entity.
     */
    QldtQlHocvienCtDTO save(QldtQlHocvienCtDTO qldtQlHocvienCtDTO);

    /**
     * Get all the qldtQlHocvienCts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QldtQlHocvienCtDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qldtQlHocvienCt.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QldtQlHocvienCtDTO> findOne(Long id);

    /**
     * Delete the "id" qldtQlHocvienCt.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
