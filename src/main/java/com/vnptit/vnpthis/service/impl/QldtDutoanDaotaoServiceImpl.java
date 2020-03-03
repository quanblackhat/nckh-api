package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.qldt.QldtDutoanDaotaoService;
import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotao;
import com.vnptit.vnpthis.repository.qldt.QldtDutoanDaotaoRepository;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoDTO;
import com.vnptit.vnpthis.service.mapper.QldtDutoanDaotaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QldtDutoanDaotao}.
 */
@Service
@Transactional
public class QldtDutoanDaotaoServiceImpl implements QldtDutoanDaotaoService {

    private final Logger log = LoggerFactory.getLogger(QldtDutoanDaotaoServiceImpl.class);

    private final QldtDutoanDaotaoRepository qldtDutoanDaotaoRepository;

    private final QldtDutoanDaotaoMapper qldtDutoanDaotaoMapper;

    public QldtDutoanDaotaoServiceImpl(QldtDutoanDaotaoRepository qldtDutoanDaotaoRepository, QldtDutoanDaotaoMapper qldtDutoanDaotaoMapper) {
        this.qldtDutoanDaotaoRepository = qldtDutoanDaotaoRepository;
        this.qldtDutoanDaotaoMapper = qldtDutoanDaotaoMapper;
    }

    /**
     * Save a qldtDutoanDaotao.
     *
     * @param qldtDutoanDaotaoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QldtDutoanDaotaoDTO save(QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO) {
        log.debug("Request to save QldtDutoanDaotao : {}", qldtDutoanDaotaoDTO);
        QldtDutoanDaotao qldtDutoanDaotao = qldtDutoanDaotaoMapper.toEntity(qldtDutoanDaotaoDTO);
        qldtDutoanDaotao = qldtDutoanDaotaoRepository.save(qldtDutoanDaotao);
        return qldtDutoanDaotaoMapper.toDto(qldtDutoanDaotao);
    }

    /**
     * Get all the qldtDutoanDaotaos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QldtDutoanDaotaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QldtDutoanDaotaos");
        return qldtDutoanDaotaoRepository.findAll(pageable)
            .map(qldtDutoanDaotaoMapper::toDto);
    }

    /**
     * Get one qldtDutoanDaotao by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QldtDutoanDaotaoDTO> findOne(Long id) {
        log.debug("Request to get QldtDutoanDaotao : {}", id);
        return qldtDutoanDaotaoRepository.findById(id)
            .map(qldtDutoanDaotaoMapper::toDto);
    }

    /**
     * Delete the qldtDutoanDaotao by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QldtDutoanDaotao : {}", id);
        qldtDutoanDaotaoRepository.deleteById(id);
    }
}
