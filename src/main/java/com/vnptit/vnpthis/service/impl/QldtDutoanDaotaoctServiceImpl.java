package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.qldt.QldtDutoanDaotaoctService;
import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotaoct;
import com.vnptit.vnpthis.repository.qldt.QldtDutoanDaotaoctRepository;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoctDTO;
import com.vnptit.vnpthis.service.mapper.QldtDutoanDaotaoctMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QldtDutoanDaotaoct}.
 */
@Service
@Transactional
public class QldtDutoanDaotaoctServiceImpl implements QldtDutoanDaotaoctService {

    private final Logger log = LoggerFactory.getLogger(QldtDutoanDaotaoctServiceImpl.class);

    private final QldtDutoanDaotaoctRepository qldtDutoanDaotaoctRepository;

    private final QldtDutoanDaotaoctMapper qldtDutoanDaotaoctMapper;

    public QldtDutoanDaotaoctServiceImpl(QldtDutoanDaotaoctRepository qldtDutoanDaotaoctRepository, QldtDutoanDaotaoctMapper qldtDutoanDaotaoctMapper) {
        this.qldtDutoanDaotaoctRepository = qldtDutoanDaotaoctRepository;
        this.qldtDutoanDaotaoctMapper = qldtDutoanDaotaoctMapper;
    }

    /**
     * Save a qldtDutoanDaotaoct.
     *
     * @param qldtDutoanDaotaoctDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QldtDutoanDaotaoctDTO save(QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO) {
        log.debug("Request to save QldtDutoanDaotaoct : {}", qldtDutoanDaotaoctDTO);
        QldtDutoanDaotaoct qldtDutoanDaotaoct = qldtDutoanDaotaoctMapper.toEntity(qldtDutoanDaotaoctDTO);
        qldtDutoanDaotaoct = qldtDutoanDaotaoctRepository.save(qldtDutoanDaotaoct);
        return qldtDutoanDaotaoctMapper.toDto(qldtDutoanDaotaoct);
    }

    /**
     * Get all the qldtDutoanDaotaocts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QldtDutoanDaotaoctDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QldtDutoanDaotaocts");
        return qldtDutoanDaotaoctRepository.findAll(pageable)
            .map(qldtDutoanDaotaoctMapper::toDto);
    }

    /**
     * Get one qldtDutoanDaotaoct by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QldtDutoanDaotaoctDTO> findOne(Long id) {
        log.debug("Request to get QldtDutoanDaotaoct : {}", id);
        return qldtDutoanDaotaoctRepository.findById(id)
            .map(qldtDutoanDaotaoctMapper::toDto);
    }

    /**
     * Delete the qldtDutoanDaotaoct by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QldtDutoanDaotaoct : {}", id);
        qldtDutoanDaotaoctRepository.deleteById(id);
    }
}
