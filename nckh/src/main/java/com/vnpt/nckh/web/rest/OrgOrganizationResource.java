package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.domain.OrgOrganization;
import com.vnpt.nckh.service.OrgOrganizationService;
import com.vnpt.nckh.web.rest.errors.BadRequestAlertException;
import com.vnpt.nckh.service.dto.OrgOrganizationCriteria;
import com.vnpt.nckh.service.OrgOrganizationQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.vnpt.nckh.domain.OrgOrganization}.
 */
@RestController
@RequestMapping("/api")
public class OrgOrganizationResource {

    private final Logger log = LoggerFactory.getLogger(OrgOrganizationResource.class);

    private static final String ENTITY_NAME = "orgOrganization";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrgOrganizationService orgOrganizationService;

    private final OrgOrganizationQueryService orgOrganizationQueryService;

    public OrgOrganizationResource(OrgOrganizationService orgOrganizationService, OrgOrganizationQueryService orgOrganizationQueryService) {
        this.orgOrganizationService = orgOrganizationService;
        this.orgOrganizationQueryService = orgOrganizationQueryService;
    }

    /**
     * {@code POST  /org-organizations} : Create a new orgOrganization.
     *
     * @param orgOrganization the orgOrganization to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orgOrganization, or with status {@code 400 (Bad Request)} if the orgOrganization has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/org-organizations")
    public ResponseEntity<OrgOrganization> createOrgOrganization(@Valid @RequestBody OrgOrganization orgOrganization) throws URISyntaxException {
        log.debug("REST request to save OrgOrganization : {}", orgOrganization);
        if (orgOrganization.getId() != null) {
            throw new BadRequestAlertException("A new orgOrganization cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrgOrganization result = orgOrganizationService.save(orgOrganization);
        return ResponseEntity.created(new URI("/api/org-organizations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /org-organizations} : Updates an existing orgOrganization.
     *
     * @param orgOrganization the orgOrganization to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orgOrganization,
     * or with status {@code 400 (Bad Request)} if the orgOrganization is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orgOrganization couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/org-organizations")
    public ResponseEntity<OrgOrganization> updateOrgOrganization(@Valid @RequestBody OrgOrganization orgOrganization) throws URISyntaxException {
        log.debug("REST request to update OrgOrganization : {}", orgOrganization);
        if (orgOrganization.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrgOrganization result = orgOrganizationService.save(orgOrganization);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, orgOrganization.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /org-organizations} : get all the orgOrganizations.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orgOrganizations in body.
     */
    @GetMapping("/org-organizations")
    public ResponseEntity<List<OrgOrganization>> getAllOrgOrganizations(OrgOrganizationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrgOrganizations by criteria: {}", criteria);
        Page<OrgOrganization> page = orgOrganizationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /org-organizations/count} : count all the orgOrganizations.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/org-organizations/count")
    public ResponseEntity<Long> countOrgOrganizations(OrgOrganizationCriteria criteria) {
        log.debug("REST request to count OrgOrganizations by criteria: {}", criteria);
        return ResponseEntity.ok().body(orgOrganizationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /org-organizations/:id} : get the "id" orgOrganization.
     *
     * @param id the id of the orgOrganization to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orgOrganization, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/org-organizations/{id}")
    public ResponseEntity<OrgOrganization> getOrgOrganization(@PathVariable Long id) {
        log.debug("REST request to get OrgOrganization : {}", id);
        Optional<OrgOrganization> orgOrganization = orgOrganizationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orgOrganization);
    }

    /**
     * {@code DELETE  /org-organizations/:id} : delete the "id" orgOrganization.
     *
     * @param id the id of the orgOrganization to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/org-organizations/{id}")
    public ResponseEntity<Void> deleteOrgOrganization(@PathVariable Long id) {
        log.debug("REST request to delete OrgOrganization : {}", id);
        orgOrganizationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
