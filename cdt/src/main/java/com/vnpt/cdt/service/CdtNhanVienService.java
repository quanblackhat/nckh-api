package com.vnpt.cdt.service;

import com.vnpt.cdt.domain.CdtNhanVien;
import com.vnpt.cdt.repository.CdtNhanVienRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CdtNhanVien}.
 */
@Service
@Transactional
public class CdtNhanVienService {

    private final Logger log = LoggerFactory.getLogger(CdtNhanVienService.class);

    private final CdtNhanVienRepository cdtNhanVienRepository;

    public CdtNhanVienService(CdtNhanVienRepository cdtNhanVienRepository) {
        this.cdtNhanVienRepository = cdtNhanVienRepository;
    }

    /**
     * Save a cdtNhanVien.
     *
     * @param cdtNhanVien the entity to save.
     * @return the persisted entity.
     */
    public CdtNhanVien save(CdtNhanVien cdtNhanVien) {
        log.debug("Request to save CdtNhanVien : {}", cdtNhanVien);
        return cdtNhanVienRepository.save(cdtNhanVien);
    }

    /**
     * Get all the cdtNhanViens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CdtNhanVien> findAll(Pageable pageable) {
        log.debug("Request to get all CdtNhanViens");
        return cdtNhanVienRepository.findAll(pageable);
    }


    /**
     * Get one cdtNhanVien by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CdtNhanVien> findOne(Long id) {
        log.debug("Request to get CdtNhanVien : {}", id);
        return cdtNhanVienRepository.findById(id);
    }

    /**
     * Delete the cdtNhanVien by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CdtNhanVien : {}", id);
        cdtNhanVienRepository.deleteById(id);
    }
}
