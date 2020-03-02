package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.qldt.QldtDmChungchiService;
import com.vnptit.vnpthis.domain.qldt.QldtDmChungchi;
import com.vnptit.vnpthis.repository.qldt.QldtDmChungchiRepository;
import com.vnptit.vnpthis.service.dto.QldtDmChungchiDTO;
import com.vnptit.vnpthis.service.mapper.QldtDmChungchiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QldtDmChungchi}.
 */
@Service
@Transactional
public class QldtDmChungchiServiceImpl implements QldtDmChungchiService {

    private final Logger log = LoggerFactory.getLogger(QldtDmChungchiServiceImpl.class);

    private final QldtDmChungchiRepository qldtDmChungchiRepository;

    private final QldtDmChungchiMapper qldtDmChungchiMapper;

    public QldtDmChungchiServiceImpl(QldtDmChungchiRepository qldtDmChungchiRepository, QldtDmChungchiMapper qldtDmChungchiMapper) {
        this.qldtDmChungchiRepository = qldtDmChungchiRepository;
        this.qldtDmChungchiMapper = qldtDmChungchiMapper;
    }

    /**
     * Save a qldtDmChungchi.
     *
     * @param qldtDmChungchiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QldtDmChungchiDTO save(QldtDmChungchiDTO qldtDmChungchiDTO) {
        log.debug("Request to save QldtDmChungchi : {}", qldtDmChungchiDTO);
        QldtDmChungchi qldtDmChungchi = qldtDmChungchiMapper.toEntity(qldtDmChungchiDTO);
        qldtDmChungchi = qldtDmChungchiRepository.save(qldtDmChungchi);
        return qldtDmChungchiMapper.toDto(qldtDmChungchi);
    }

    /**
     * Get all the qldtDmChungchis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QldtDmChungchiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QldtDmChungchis");
        return qldtDmChungchiRepository.findAll(pageable)
            .map(qldtDmChungchiMapper::toDto);
    }

    /**
     * Get one qldtDmChungchi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QldtDmChungchiDTO> findOne(Long id) {
        log.debug("Request to get QldtDmChungchi : {}", id);
        return qldtDmChungchiRepository.findById(id)
            .map(qldtDmChungchiMapper::toDto);
    }

    /**
     * Delete the qldtDmChungchi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QldtDmChungchi : {}", id);
        qldtDmChungchiRepository.deleteById(id);
    }
}
