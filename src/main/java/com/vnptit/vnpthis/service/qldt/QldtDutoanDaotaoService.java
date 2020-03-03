package com.vnptit.vnpthis.service.qldt;

import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.QldtDutoanDaotao}.
 */
public interface QldtDutoanDaotaoService {

    /**
     * Save a qldtDutoanDaotao.
     *
     * @param qldtDutoanDaotaoDTO the entity to save.
     * @return the persisted entity.
     */
    QldtDutoanDaotaoDTO save(QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO);

    /**
     * Get all the qldtDutoanDaotaos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QldtDutoanDaotaoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qldtDutoanDaotao.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QldtDutoanDaotaoDTO> findOne(Long id);

    /**
     * Delete the "id" qldtDutoanDaotao.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
