package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.qldt.QldtDaotaoCtService;
import com.vnptit.vnpthis.domain.qldt.QldtDaotaoCt;
import com.vnptit.vnpthis.repository.qldt.QldtDaotaoCtRepository;
import com.vnptit.vnpthis.service.dto.QldtDaotaoCtDTO;
import com.vnptit.vnpthis.service.mapper.QldtDaotaoCtMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QldtDaotaoCt}.
 */
@Service
@Transactional
public class QldtDaotaoCtServiceImpl implements QldtDaotaoCtService {

    private final Logger log = LoggerFactory.getLogger(QldtDaotaoCtServiceImpl.class);

    private final QldtDaotaoCtRepository qldtDaotaoCtRepository;

    private final QldtDaotaoCtMapper qldtDaotaoCtMapper;

    public QldtDaotaoCtServiceImpl(QldtDaotaoCtRepository qldtDaotaoCtRepository, QldtDaotaoCtMapper qldtDaotaoCtMapper) {
        this.qldtDaotaoCtRepository = qldtDaotaoCtRepository;
        this.qldtDaotaoCtMapper = qldtDaotaoCtMapper;
    }

    /**
     * Save a qldtDaotaoCt.
     *
     * @param qldtDaotaoCtDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QldtDaotaoCtDTO save(QldtDaotaoCtDTO qldtDaotaoCtDTO) {
        log.debug("Request to save QldtDaotaoCt : {}", qldtDaotaoCtDTO);
        QldtDaotaoCt qldtDaotaoCt = qldtDaotaoCtMapper.toEntity(qldtDaotaoCtDTO);
        qldtDaotaoCt = qldtDaotaoCtRepository.save(qldtDaotaoCt);
        return qldtDaotaoCtMapper.toDto(qldtDaotaoCt);
    }

    /**
     * Get all the qldtDaotaoCts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QldtDaotaoCtDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QldtDaotaoCts");
        return qldtDaotaoCtRepository.findAll(pageable)
            .map(qldtDaotaoCtMapper::toDto);
    }

    /**
     * Get one qldtDaotaoCt by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QldtDaotaoCtDTO> findOne(Long id) {
        log.debug("Request to get QldtDaotaoCt : {}", id);
        return qldtDaotaoCtRepository.findById(id)
            .map(qldtDaotaoCtMapper::toDto);
    }

    /**
     * Delete the qldtDaotaoCt by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QldtDaotaoCt : {}", id);
        qldtDaotaoCtRepository.deleteById(id);
    }
}
