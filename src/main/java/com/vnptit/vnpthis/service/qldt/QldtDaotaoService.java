package com.vnptit.vnpthis.service.qldt;

import com.vnptit.vnpthis.service.dto.QldtDaotaoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.vnptit.vnpthis.domain.QldtDaotao}.
 */
public interface QldtDaotaoService {

    /**
     * Save a qldtDaotao.
     *
     * @param qldtDaotaoDTO the entity to save.
     * @return the persisted entity.
     */
    QldtDaotaoDTO save(QldtDaotaoDTO qldtDaotaoDTO);

    /**
     * Get all the qldtDaotaos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QldtDaotaoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qldtDaotao.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QldtDaotaoDTO> findOne(Long id);

    /**
     * Delete the "id" qldtDaotao.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
