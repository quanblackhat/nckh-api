package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.qldt.QldtQlHocvienService;
import com.vnptit.vnpthis.domain.qldt.QldtQlHocvien;
import com.vnptit.vnpthis.repository.qldt.QldtQlHocvienRepository;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienDTO;
import com.vnptit.vnpthis.service.mapper.QldtQlHocvienMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QldtQlHocvien}.
 */
@Service
@Transactional
public class QldtQlHocvienServiceImpl implements QldtQlHocvienService {

    private final Logger log = LoggerFactory.getLogger(QldtQlHocvienServiceImpl.class);

    private final QldtQlHocvienRepository qldtQlHocvienRepository;

    private final QldtQlHocvienMapper qldtQlHocvienMapper;

    public QldtQlHocvienServiceImpl(QldtQlHocvienRepository qldtQlHocvienRepository, QldtQlHocvienMapper qldtQlHocvienMapper) {
        this.qldtQlHocvienRepository = qldtQlHocvienRepository;
        this.qldtQlHocvienMapper = qldtQlHocvienMapper;
    }

    /**
     * Save a qldtQlHocvien.
     *
     * @param qldtQlHocvienDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QldtQlHocvienDTO save(QldtQlHocvienDTO qldtQlHocvienDTO) {
        log.debug("Request to save QldtQlHocvien : {}", qldtQlHocvienDTO);
        QldtQlHocvien qldtQlHocvien = qldtQlHocvienMapper.toEntity(qldtQlHocvienDTO);
        qldtQlHocvien = qldtQlHocvienRepository.save(qldtQlHocvien);
        return qldtQlHocvienMapper.toDto(qldtQlHocvien);
    }

    /**
     * Get all the qldtQlHocviens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QldtQlHocvienDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QldtQlHocviens");
        return qldtQlHocvienRepository.findAll(pageable)
            .map(qldtQlHocvienMapper::toDto);
    }

    /**
     * Get one qldtQlHocvien by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QldtQlHocvienDTO> findOne(Long id) {
        log.debug("Request to get QldtQlHocvien : {}", id);
        return qldtQlHocvienRepository.findById(id)
            .map(qldtQlHocvienMapper::toDto);
    }

    /**
     * Delete the qldtQlHocvien by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QldtQlHocvien : {}", id);
        qldtQlHocvienRepository.deleteById(id);
    }
}
