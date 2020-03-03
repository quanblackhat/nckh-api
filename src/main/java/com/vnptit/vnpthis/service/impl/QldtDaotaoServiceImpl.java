package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.qldt.QldtDaotaoService;
import com.vnptit.vnpthis.domain.qldt.QldtDaotao;
import com.vnptit.vnpthis.repository.qldt.QldtDaotaoRepository;
import com.vnptit.vnpthis.service.dto.QldtDaotaoDTO;
import com.vnptit.vnpthis.service.mapper.QldtDaotaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QldtDaotao}.
 */
@Service
@Transactional
public class QldtDaotaoServiceImpl implements QldtDaotaoService {

    private final Logger log = LoggerFactory.getLogger(QldtDaotaoServiceImpl.class);

    private final QldtDaotaoRepository qldtDaotaoRepository;

    private final QldtDaotaoMapper qldtDaotaoMapper;

    public QldtDaotaoServiceImpl(QldtDaotaoRepository qldtDaotaoRepository, QldtDaotaoMapper qldtDaotaoMapper) {
        this.qldtDaotaoRepository = qldtDaotaoRepository;
        this.qldtDaotaoMapper = qldtDaotaoMapper;
    }

    /**
     * Save a qldtDaotao.
     *
     * @param qldtDaotaoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QldtDaotaoDTO save(QldtDaotaoDTO qldtDaotaoDTO) {
        log.debug("Request to save QldtDaotao : {}", qldtDaotaoDTO);
        QldtDaotao qldtDaotao = qldtDaotaoMapper.toEntity(qldtDaotaoDTO);
        qldtDaotao = qldtDaotaoRepository.save(qldtDaotao);
        return qldtDaotaoMapper.toDto(qldtDaotao);
    }

    /**
     * Get all the qldtDaotaos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QldtDaotaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QldtDaotaos");
        return qldtDaotaoRepository.findAll(pageable)
            .map(qldtDaotaoMapper::toDto);
    }

    /**
     * Get one qldtDaotao by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QldtDaotaoDTO> findOne(Long id) {
        log.debug("Request to get QldtDaotao : {}", id);
        return qldtDaotaoRepository.findById(id)
            .map(qldtDaotaoMapper::toDto);
    }

    /**
     * Delete the qldtDaotao by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QldtDaotao : {}", id);
        qldtDaotaoRepository.deleteById(id);
    }
}
