package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.qldt.QldtQlHocvienCtService;
import com.vnptit.vnpthis.domain.qldt.QldtQlHocvienCt;
import com.vnptit.vnpthis.repository.qldt.QldtQlHocvienCtRepository;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienCtDTO;
import com.vnptit.vnpthis.service.mapper.QldtQlHocvienCtMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QldtQlHocvienCt}.
 */
@Service
@Transactional
public class QldtQlHocvienCtServiceImpl implements QldtQlHocvienCtService {

    private final Logger log = LoggerFactory.getLogger(QldtQlHocvienCtServiceImpl.class);

    private final QldtQlHocvienCtRepository qldtQlHocvienCtRepository;

    private final QldtQlHocvienCtMapper qldtQlHocvienCtMapper;

    public QldtQlHocvienCtServiceImpl(QldtQlHocvienCtRepository qldtQlHocvienCtRepository, QldtQlHocvienCtMapper qldtQlHocvienCtMapper) {
        this.qldtQlHocvienCtRepository = qldtQlHocvienCtRepository;
        this.qldtQlHocvienCtMapper = qldtQlHocvienCtMapper;
    }

    /**
     * Save a qldtQlHocvienCt.
     *
     * @param qldtQlHocvienCtDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QldtQlHocvienCtDTO save(QldtQlHocvienCtDTO qldtQlHocvienCtDTO) {
        log.debug("Request to save QldtQlHocvienCt : {}", qldtQlHocvienCtDTO);
        QldtQlHocvienCt qldtQlHocvienCt = qldtQlHocvienCtMapper.toEntity(qldtQlHocvienCtDTO);
        qldtQlHocvienCt = qldtQlHocvienCtRepository.save(qldtQlHocvienCt);
        return qldtQlHocvienCtMapper.toDto(qldtQlHocvienCt);
    }

    /**
     * Get all the qldtQlHocvienCts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QldtQlHocvienCtDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QldtQlHocvienCts");
        return qldtQlHocvienCtRepository.findAll(pageable)
            .map(qldtQlHocvienCtMapper::toDto);
    }

    /**
     * Get one qldtQlHocvienCt by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QldtQlHocvienCtDTO> findOne(Long id) {
        log.debug("Request to get QldtQlHocvienCt : {}", id);
        return qldtQlHocvienCtRepository.findById(id)
            .map(qldtQlHocvienCtMapper::toDto);
    }

    /**
     * Delete the qldtQlHocvienCt by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QldtQlHocvienCt : {}", id);
        qldtQlHocvienCtRepository.deleteById(id);
    }
}
