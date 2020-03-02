package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.qldt.QldtDmNguoidungService;
import com.vnptit.vnpthis.domain.qldt.QldtDmNguoidung;
import com.vnptit.vnpthis.repository.qldt.QldtDmNguoidungRepository;
import com.vnptit.vnpthis.service.dto.QldtDmNguoidungDTO;
import com.vnptit.vnpthis.service.mapper.QldtDmNguoidungMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QldtDmNguoidung}.
 */
@Service
@Transactional
public class QldtDmNguoidungServiceImpl implements QldtDmNguoidungService {

    private final Logger log = LoggerFactory.getLogger(QldtDmNguoidungServiceImpl.class);

    private final QldtDmNguoidungRepository qldtDmNguoidungRepository;

    private final QldtDmNguoidungMapper qldtDmNguoidungMapper;

    public QldtDmNguoidungServiceImpl(QldtDmNguoidungRepository qldtDmNguoidungRepository, QldtDmNguoidungMapper qldtDmNguoidungMapper) {
        this.qldtDmNguoidungRepository = qldtDmNguoidungRepository;
        this.qldtDmNguoidungMapper = qldtDmNguoidungMapper;
    }

    /**
     * Save a qldtDmNguoidung.
     *
     * @param qldtDmNguoidungDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QldtDmNguoidungDTO save(QldtDmNguoidungDTO qldtDmNguoidungDTO) {
        log.debug("Request to save QldtDmNguoidung : {}", qldtDmNguoidungDTO);
        QldtDmNguoidung qldtDmNguoidung = qldtDmNguoidungMapper.toEntity(qldtDmNguoidungDTO);
        qldtDmNguoidung = qldtDmNguoidungRepository.save(qldtDmNguoidung);
        return qldtDmNguoidungMapper.toDto(qldtDmNguoidung);
    }

    /**
     * Get all the qldtDmNguoidungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QldtDmNguoidungDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QldtDmNguoidungs");
        return qldtDmNguoidungRepository.findAll(pageable)
            .map(qldtDmNguoidungMapper::toDto);
    }

    /**
     * Get one qldtDmNguoidung by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QldtDmNguoidungDTO> findOne(Long id) {
        log.debug("Request to get QldtDmNguoidung : {}", id);
        return qldtDmNguoidungRepository.findById(id)
            .map(qldtDmNguoidungMapper::toDto);
    }

    /**
     * Delete the qldtDmNguoidung by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QldtDmNguoidung : {}", id);
        qldtDmNguoidungRepository.deleteById(id);
    }
}
