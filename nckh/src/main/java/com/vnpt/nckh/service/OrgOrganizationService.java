package com.vnpt.nckh.service;

import com.vnpt.nckh.domain.OrgOrganization;
import com.vnpt.nckh.repository.OrgOrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrgOrganization}.
 */
@Service
@Transactional
public class OrgOrganizationService {

    private final Logger log = LoggerFactory.getLogger(OrgOrganizationService.class);

    private final OrgOrganizationRepository orgOrganizationRepository;

    public OrgOrganizationService(OrgOrganizationRepository orgOrganizationRepository) {
        this.orgOrganizationRepository = orgOrganizationRepository;
    }

    /**
     * Save a orgOrganization.
     *
     * @param orgOrganization the entity to save.
     * @return the persisted entity.
     */
    public OrgOrganization save(OrgOrganization orgOrganization) {
        log.debug("Request to save OrgOrganization : {}", orgOrganization);
        return orgOrganizationRepository.save(orgOrganization);
    }

    /**
     * Get all the orgOrganizations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrgOrganization> findAll(Pageable pageable) {
        log.debug("Request to get all OrgOrganizations");
        return orgOrganizationRepository.findAll(pageable);
    }


    /**
     * Get one orgOrganization by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrgOrganization> findOne(Long id) {
        log.debug("Request to get OrgOrganization : {}", id);
        return orgOrganizationRepository.findById(id);
    }

    /**
     * Delete the orgOrganization by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrgOrganization : {}", id);
        orgOrganizationRepository.deleteById(id);
    }
}
