package com.vnptit.vnpthis.service.qldt;

import com.vnptit.vnpthis.service.dto.QldtDmNoidungDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.QldtDmNoidung}.
 */
public interface QldtDmNoidungService {

    /**
     * Save a qldtDmNoidung.
     *
     * @param qldtDmNoidungDTO the entity to save.
     * @return the persisted entity.
     */
    QldtDmNoidungDTO save(QldtDmNoidungDTO qldtDmNoidungDTO);

    /**
     * Get all the qldtDmNoidungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QldtDmNoidungDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qldtDmNoidung.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QldtDmNoidungDTO> findOne(Long id);

    /**
     * Delete the "id" qldtDmNoidung.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
