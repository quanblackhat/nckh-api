package com.vnptit.vnpthis.service.qldt;

import com.vnptit.vnpthis.service.dto.QldtDmNguoidungDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.QldtDmNguoidung}.
 */
public interface QldtDmNguoidungService {

    /**
     * Save a qldtDmNguoidung.
     *
     * @param qldtDmNguoidungDTO the entity to save.
     * @return the persisted entity.
     */
    QldtDmNguoidungDTO save(QldtDmNguoidungDTO qldtDmNguoidungDTO);

    /**
     * Get all the qldtDmNguoidungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QldtDmNguoidungDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qldtDmNguoidung.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QldtDmNguoidungDTO> findOne(Long id);

    /**
     * Delete the "id" qldtDmNguoidung.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
