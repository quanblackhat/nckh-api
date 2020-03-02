package com.vnptit.vnpthis.service.qldt;

import com.vnptit.vnpthis.service.dto.QldtDaotaoCtDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.QldtDaotaoCt}.
 */
public interface QldtDaotaoCtService {

    /**
     * Save a qldtDaotaoCt.
     *
     * @param qldtDaotaoCtDTO the entity to save.
     * @return the persisted entity.
     */
    QldtDaotaoCtDTO save(QldtDaotaoCtDTO qldtDaotaoCtDTO);

    /**
     * Get all the qldtDaotaoCts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QldtDaotaoCtDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qldtDaotaoCt.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QldtDaotaoCtDTO> findOne(Long id);

    /**
     * Delete the "id" qldtDaotaoCt.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
