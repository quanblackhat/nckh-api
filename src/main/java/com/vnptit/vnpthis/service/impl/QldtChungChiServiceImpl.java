package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.qldt.QldtChungChiService;
import com.vnptit.vnpthis.domain.qldt.QldtChungChi;
import com.vnptit.vnpthis.repository.qldt.QldtChungChiRepository;
import com.vnptit.vnpthis.service.dto.QldtChungChiDTO;
import com.vnptit.vnpthis.service.mapper.QldtChungChiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QldtChungChi}.
 */
@Service
@Transactional
public class QldtChungChiServiceImpl implements QldtChungChiService {

    private final Logger log = LoggerFactory.getLogger(QldtChungChiServiceImpl.class);

    private final QldtChungChiRepository qldtChungChiRepository;

    private final QldtChungChiMapper qldtChungChiMapper;

    public QldtChungChiServiceImpl(QldtChungChiRepository qldtChungChiRepository, QldtChungChiMapper qldtChungChiMapper) {
        this.qldtChungChiRepository = qldtChungChiRepository;
        this.qldtChungChiMapper = qldtChungChiMapper;
    }

    /**
     * Save a qldtChungChi.
     *
     * @param qldtChungChiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QldtChungChiDTO save(QldtChungChiDTO qldtChungChiDTO) {
        log.debug("Request to save QldtChungChi : {}", qldtChungChiDTO);
        QldtChungChi qldtChungChi = qldtChungChiMapper.toEntity(qldtChungChiDTO);
        qldtChungChi = qldtChungChiRepository.save(qldtChungChi);
        return qldtChungChiMapper.toDto(qldtChungChi);
    }

    /**
     * Get all the qldtChungChis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QldtChungChiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QldtChungChis");
        return qldtChungChiRepository.findAll(pageable)
            .map(qldtChungChiMapper::toDto);
    }

    /**
     * Get one qldtChungChi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QldtChungChiDTO> findOne(Long id) {
        log.debug("Request to get QldtChungChi : {}", id);
        return qldtChungChiRepository.findById(id)
            .map(qldtChungChiMapper::toDto);
    }

    /**
     * Delete the qldtChungChi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QldtChungChi : {}", id);
        qldtChungChiRepository.deleteById(id);
    }
}
