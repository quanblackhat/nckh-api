package com.vnptit.vnpthis.service.qldt;

import com.vnptit.vnpthis.service.dto.QldtQlHocvienDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.QldtQlHocvien}.
 */
public interface QldtQlHocvienService {

    /**
     * Save a qldtQlHocvien.
     *
     * @param qldtQlHocvienDTO the entity to save.
     * @return the persisted entity.
     */
    QldtQlHocvienDTO save(QldtQlHocvienDTO qldtQlHocvienDTO);

    /**
     * Get all the qldtQlHocviens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QldtQlHocvienDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qldtQlHocvien.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QldtQlHocvienDTO> findOne(Long id);

    /**
     * Delete the "id" qldtQlHocvien.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
