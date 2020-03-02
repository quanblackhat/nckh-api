package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.qldt.QldtDmNoidungService;
import com.vnptit.vnpthis.domain.qldt.QldtDmNoidung;
import com.vnptit.vnpthis.repository.qldt.QldtDmNoidungRepository;
import com.vnptit.vnpthis.service.dto.QldtDmNoidungDTO;
import com.vnptit.vnpthis.service.mapper.QldtDmNoidungMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QldtDmNoidung}.
 */
@Service
@Transactional
public class QldtDmNoidungServiceImpl implements QldtDmNoidungService {

    private final Logger log = LoggerFactory.getLogger(QldtDmNoidungServiceImpl.class);

    private final QldtDmNoidungRepository qldtDmNoidungRepository;

    private final QldtDmNoidungMapper qldtDmNoidungMapper;

    public QldtDmNoidungServiceImpl(QldtDmNoidungRepository qldtDmNoidungRepository, QldtDmNoidungMapper qldtDmNoidungMapper) {
        this.qldtDmNoidungRepository = qldtDmNoidungRepository;
        this.qldtDmNoidungMapper = qldtDmNoidungMapper;
    }

    /**
     * Save a qldtDmNoidung.
     *
     * @param qldtDmNoidungDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QldtDmNoidungDTO save(QldtDmNoidungDTO qldtDmNoidungDTO) {
        log.debug("Request to save QldtDmNoidung : {}", qldtDmNoidungDTO);
        QldtDmNoidung qldtDmNoidung = qldtDmNoidungMapper.toEntity(qldtDmNoidungDTO);
        qldtDmNoidung = qldtDmNoidungRepository.save(qldtDmNoidung);
        return qldtDmNoidungMapper.toDto(qldtDmNoidung);
    }

    /**
     * Get all the qldtDmNoidungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QldtDmNoidungDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QldtDmNoidungs");
        return qldtDmNoidungRepository.findAll(pageable)
            .map(qldtDmNoidungMapper::toDto);
    }

    /**
     * Get one qldtDmNoidung by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QldtDmNoidungDTO> findOne(Long id) {
        log.debug("Request to get QldtDmNoidung : {}", id);
        return qldtDmNoidungRepository.findById(id)
            .map(qldtDmNoidungMapper::toDto);
    }

    /**
     * Delete the qldtDmNoidung by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QldtDmNoidung : {}", id);
        qldtDmNoidungRepository.deleteById(id);
    }
}
