package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import com.vnpt.cdt.service.CdtChiDaoTuyenService;
import com.vnpt.cdt.web.rest.errors.BadRequestAlertException;
import com.vnpt.cdt.service.dto.CdtChiDaoTuyenCriteria;
import com.vnpt.cdt.service.CdtChiDaoTuyenQueryService;

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
 * REST controller for managing {@link com.vnpt.cdt.domain.CdtChiDaoTuyen}.
 */
@RestController
@RequestMapping("/api")
public class CdtChiDaoTuyenResource {

    private final Logger log = LoggerFactory.getLogger(CdtChiDaoTuyenResource.class);

    private static final String ENTITY_NAME = "cdtChiDaoTuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CdtChiDaoTuyenService cdtChiDaoTuyenService;

    private final CdtChiDaoTuyenQueryService cdtChiDaoTuyenQueryService;

    public CdtChiDaoTuyenResource(CdtChiDaoTuyenService cdtChiDaoTuyenService, CdtChiDaoTuyenQueryService cdtChiDaoTuyenQueryService) {
        this.cdtChiDaoTuyenService = cdtChiDaoTuyenService;
        this.cdtChiDaoTuyenQueryService = cdtChiDaoTuyenQueryService;
    }

    /**
     * {@code POST  /cdt-chi-dao-tuyens} : Create a new cdtChiDaoTuyen.
     *
     * @param cdtChiDaoTuyen the cdtChiDaoTuyen to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cdtChiDaoTuyen, or with status {@code 400 (Bad Request)} if the cdtChiDaoTuyen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cdt-chi-dao-tuyens")
    public ResponseEntity<CdtChiDaoTuyen> createCdtChiDaoTuyen(@Valid @RequestBody CdtChiDaoTuyen cdtChiDaoTuyen) throws URISyntaxException {
        log.debug("REST request to save CdtChiDaoTuyen : {}", cdtChiDaoTuyen);
        if (cdtChiDaoTuyen.getId() != null) {
            throw new BadRequestAlertException("A new cdtChiDaoTuyen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CdtChiDaoTuyen result = cdtChiDaoTuyenService.save(cdtChiDaoTuyen);
        return ResponseEntity.created(new URI("/api/cdt-chi-dao-tuyens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cdt-chi-dao-tuyens} : Updates an existing cdtChiDaoTuyen.
     *
     * @param cdtChiDaoTuyen the cdtChiDaoTuyen to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cdtChiDaoTuyen,
     * or with status {@code 400 (Bad Request)} if the cdtChiDaoTuyen is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cdtChiDaoTuyen couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cdt-chi-dao-tuyens")
    public ResponseEntity<CdtChiDaoTuyen> updateCdtChiDaoTuyen(@Valid @RequestBody CdtChiDaoTuyen cdtChiDaoTuyen) throws URISyntaxException {
        log.debug("REST request to update CdtChiDaoTuyen : {}", cdtChiDaoTuyen);
        if (cdtChiDaoTuyen.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CdtChiDaoTuyen result = cdtChiDaoTuyenService.save(cdtChiDaoTuyen);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cdtChiDaoTuyen.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cdt-chi-dao-tuyens} : get all the cdtChiDaoTuyens.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cdtChiDaoTuyens in body.
     */
    @GetMapping("/cdt-chi-dao-tuyens")
    public ResponseEntity<List<CdtChiDaoTuyen>> getAllCdtChiDaoTuyens(CdtChiDaoTuyenCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CdtChiDaoTuyens by criteria: {}", criteria);
        Page<CdtChiDaoTuyen> page = cdtChiDaoTuyenQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cdt-chi-dao-tuyens/count} : count all the cdtChiDaoTuyens.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cdt-chi-dao-tuyens/count")
    public ResponseEntity<Long> countCdtChiDaoTuyens(CdtChiDaoTuyenCriteria criteria) {
        log.debug("REST request to count CdtChiDaoTuyens by criteria: {}", criteria);
        return ResponseEntity.ok().body(cdtChiDaoTuyenQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cdt-chi-dao-tuyens/:id} : get the "id" cdtChiDaoTuyen.
     *
     * @param id the id of the cdtChiDaoTuyen to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cdtChiDaoTuyen, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cdt-chi-dao-tuyens/{id}")
    public ResponseEntity<CdtChiDaoTuyen> getCdtChiDaoTuyen(@PathVariable Long id) {
        log.debug("REST request to get CdtChiDaoTuyen : {}", id);
        Optional<CdtChiDaoTuyen> cdtChiDaoTuyen = cdtChiDaoTuyenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cdtChiDaoTuyen);
    }

    /**
     * {@code DELETE  /cdt-chi-dao-tuyens/:id} : delete the "id" cdtChiDaoTuyen.
     *
     * @param id the id of the cdtChiDaoTuyen to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cdt-chi-dao-tuyens/{id}")
    public ResponseEntity<Void> deleteCdtChiDaoTuyen(@PathVariable Long id) {
        log.debug("REST request to delete CdtChiDaoTuyen : {}", id);
        cdtChiDaoTuyenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
