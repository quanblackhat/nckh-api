package com.vnptit.vnpthis.service.qldt;

import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoctDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.QldtDutoanDaotaoct}.
 */
public interface QldtDutoanDaotaoctService {

    /**
     * Save a qldtDutoanDaotaoct.
     *
     * @param qldtDutoanDaotaoctDTO the entity to save.
     * @return the persisted entity.
     */
    QldtDutoanDaotaoctDTO save(QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO);

    /**
     * Get all the qldtDutoanDaotaocts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QldtDutoanDaotaoctDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qldtDutoanDaotaoct.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QldtDutoanDaotaoctDTO> findOne(Long id);

    /**
     * Delete the "id" qldtDutoanDaotaoct.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
