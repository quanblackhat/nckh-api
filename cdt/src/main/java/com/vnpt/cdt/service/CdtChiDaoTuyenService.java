package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import com.vnpt.cdt.repository.CdtChiDaoTuyenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CdtChiDaoTuyen}.
 */
@Service
@Transactional
public class CdtChiDaoTuyenService {

    private final Logger log = LoggerFactory.getLogger(CdtChiDaoTuyenService.class);

    private final CdtChiDaoTuyenRepository cdtChiDaoTuyenRepository;

    public CdtChiDaoTuyenService(CdtChiDaoTuyenRepository cdtChiDaoTuyenRepository) {
        this.cdtChiDaoTuyenRepository = cdtChiDaoTuyenRepository;
    }

    /**
     * Save a cdtChiDaoTuyen.
     *
     * @param cdtChiDaoTuyen the entity to save.
     * @return the persisted entity.
     */
    public CdtChiDaoTuyen save(CdtChiDaoTuyen cdtChiDaoTuyen) {
        log.debug("Request to save CdtChiDaoTuyen : {}", cdtChiDaoTuyen);
        return cdtChiDaoTuyenRepository.save(cdtChiDaoTuyen);
    }

    /**
     * Get all the cdtChiDaoTuyens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtChiDaoTuyen> findAll(Pageable pageable) {
        log.debug("Request to get all CdtChiDaoTuyens");
        return cdtChiDaoTuyenRepository.findAll(pageable);
    }


    /**
     * Get one cdtChiDaoTuyen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CdtChiDaoTuyen> findOne(Long id) {
        log.debug("Request to get CdtChiDaoTuyen : {}", id);
        return cdtChiDaoTuyenRepository.findById(id);
    }

    /**
     * Delete the cdtChiDaoTuyen by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CdtChiDaoTuyen : {}", id);
        cdtChiDaoTuyenRepository.deleteById(id);
    }
}
