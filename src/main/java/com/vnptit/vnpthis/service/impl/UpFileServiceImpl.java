package com.vnptit.vnpthis.service.impl;

import com.vnptit.vnpthis.service.UpFileService;
import com.vnptit.vnpthis.domain.UpFile;
import com.vnptit.vnpthis.repository.UpFileRepository;
import com.vnptit.vnpthis.service.dto.UpFileDTO;
import com.vnptit.vnpthis.service.mapper.UpFileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UpFile}.
 */
@Service
@Transactional
public class UpFileServiceImpl implements UpFileService {

    private final Logger log = LoggerFactory.getLogger(UpFileServiceImpl.class);

    private final UpFileRepository upFileRepository;

    private final UpFileMapper upFileMapper;

    public UpFileServiceImpl(UpFileRepository upFileRepository, UpFileMapper upFileMapper) {
        this.upFileRepository = upFileRepository;
        this.upFileMapper = upFileMapper;
    }

    /**
     * Save a upFile.
     *
     * @param upFileDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UpFileDTO save(UpFileDTO upFileDTO) {
        log.debug("Request to save UpFile : {}", upFileDTO);
        UpFile upFile = upFileMapper.toEntity(upFileDTO);
        upFile = upFileRepository.save(upFile);
        return upFileMapper.toDto(upFile);
    }

    /**
     * Get all the upFiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UpFileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UpFiles");
        return upFileRepository.findAll(pageable)
            .map(upFileMapper::toDto);
    }


    /**
     * Get one upFile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UpFileDTO> findOne(Long id) {
        log.debug("Request to get UpFile : {}", id);
        return upFileRepository.findById(id)
            .map(upFileMapper::toDto);
    }

    /**
     * Delete the upFile by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UpFile : {}", id);
        upFileRepository.deleteById(id);
    }
}
