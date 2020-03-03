package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.qldt.QldtTochucCapService;
import com.vnptit.vnpthis.domain.qldt.QldtTochucCap;
import com.vnptit.vnpthis.repository.qldt.QldtTochucCapRepository;
import com.vnptit.vnpthis.service.dto.QldtTochucCapDTO;
import com.vnptit.vnpthis.service.mapper.QldtTochucCapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QldtTochucCap}.
 */
@Service
@Transactional
public class QldtTochucCapServiceImpl implements QldtTochucCapService {

    private final Logger log = LoggerFactory.getLogger(QldtTochucCapServiceImpl.class);

    private final QldtTochucCapRepository qldtTochucCapRepository;

    private final QldtTochucCapMapper qldtTochucCapMapper;

    public QldtTochucCapServiceImpl(QldtTochucCapRepository qldtTochucCapRepository, QldtTochucCapMapper qldtTochucCapMapper) {
        this.qldtTochucCapRepository = qldtTochucCapRepository;
        this.qldtTochucCapMapper = qldtTochucCapMapper;
    }

    /**
     * Save a qldtTochucCap.
     *
     * @param qldtTochucCapDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QldtTochucCapDTO save(QldtTochucCapDTO qldtTochucCapDTO) {
        log.debug("Request to save QldtTochucCap : {}", qldtTochucCapDTO);
        QldtTochucCap qldtTochucCap = qldtTochucCapMapper.toEntity(qldtTochucCapDTO);
        qldtTochucCap = qldtTochucCapRepository.save(qldtTochucCap);
        return qldtTochucCapMapper.toDto(qldtTochucCap);
    }

    /**
     * Get all the qldtTochucCaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QldtTochucCapDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QldtTochucCaps");
        return qldtTochucCapRepository.findAll(pageable)
            .map(qldtTochucCapMapper::toDto);
    }

    /**
     * Get one qldtTochucCap by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QldtTochucCapDTO> findOne(Long id) {
        log.debug("Request to get QldtTochucCap : {}", id);
        return qldtTochucCapRepository.findById(id)
            .map(qldtTochucCapMapper::toDto);
    }

    /**
     * Delete the qldtTochucCap by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QldtTochucCap : {}", id);
        qldtTochucCapRepository.deleteById(id);
    }
}
