package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.domain.CdtTaiChinh;
import com.vnpt.cdt.service.CdtTaiChinhService;
import com.vnpt.cdt.web.rest.errors.BadRequestAlertException;
import com.vnpt.cdt.service.dto.CdtTaiChinhCriteria;
import com.vnpt.cdt.service.CdtTaiChinhQueryService;

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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.vnpt.cdt.domain.CdtTaiChinh}.
 */
@RestController
@RequestMapping("/api")
public class CdtTaiChinhResource {

    private final Logger log = LoggerFactory.getLogger(CdtTaiChinhResource.class);

    private static final String ENTITY_NAME = "cdtTaiChinh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CdtTaiChinhService cdtTaiChinhService;

    private final CdtTaiChinhQueryService cdtTaiChinhQueryService;

    public CdtTaiChinhResource(CdtTaiChinhService cdtTaiChinhService, CdtTaiChinhQueryService cdtTaiChinhQueryService) {
        this.cdtTaiChinhService = cdtTaiChinhService;
        this.cdtTaiChinhQueryService = cdtTaiChinhQueryService;
    }

    /**
     * {@code POST  /cdt-tai-chinhs} : Create a new cdtTaiChinh.
     *
     * @param cdtTaiChinh the cdtTaiChinh to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cdtTaiChinh, or with status {@code 400 (Bad Request)} if the cdtTaiChinh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cdt-tai-chinhs")
    public ResponseEntity<CdtTaiChinh> createCdtTaiChinh(@RequestBody CdtTaiChinh cdtTaiChinh) throws URISyntaxException {
        log.debug("REST request to save CdtTaiChinh : {}", cdtTaiChinh);
        if (cdtTaiChinh.getId() != null) {
            throw new BadRequestAlertException("A new cdtTaiChinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CdtTaiChinh result = cdtTaiChinhService.save(cdtTaiChinh);
        return ResponseEntity.created(new URI("/api/cdt-tai-chinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cdt-tai-chinhs} : Updates an existing cdtTaiChinh.
     *
     * @param cdtTaiChinh the cdtTaiChinh to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cdtTaiChinh,
     * or with status {@code 400 (Bad Request)} if the cdtTaiChinh is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cdtTaiChinh couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cdt-tai-chinhs")
    public ResponseEntity<CdtTaiChinh> updateCdtTaiChinh(@RequestBody CdtTaiChinh cdtTaiChinh) throws URISyntaxException {
        log.debug("REST request to update CdtTaiChinh : {}", cdtTaiChinh);
        if (cdtTaiChinh.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CdtTaiChinh result = cdtTaiChinhService.save(cdtTaiChinh);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cdtTaiChinh.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cdt-tai-chinhs} : get all the cdtTaiChinhs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cdtTaiChinhs in body.
     */
    @GetMapping("/cdt-tai-chinhs")
    public ResponseEntity<List<CdtTaiChinh>> getAllCdtTaiChinhs(CdtTaiChinhCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CdtTaiChinhs by criteria: {}", criteria);
        Page<CdtTaiChinh> page = cdtTaiChinhQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cdt-tai-chinhs/count} : count all the cdtTaiChinhs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cdt-tai-chinhs/count")
    public ResponseEntity<Long> countCdtTaiChinhs(CdtTaiChinhCriteria criteria) {
        log.debug("REST request to count CdtTaiChinhs by criteria: {}", criteria);
        return ResponseEntity.ok().body(cdtTaiChinhQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cdt-tai-chinhs/:id} : get the "id" cdtTaiChinh.
     *
     * @param id the id of the cdtTaiChinh to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cdtTaiChinh, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cdt-tai-chinhs/{id}")
    public ResponseEntity<CdtTaiChinh> getCdtTaiChinh(@PathVariable Long id) {
        log.debug("REST request to get CdtTaiChinh : {}", id);
        Optional<CdtTaiChinh> cdtTaiChinh = cdtTaiChinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cdtTaiChinh);
    }

    /**
     * {@code DELETE  /cdt-tai-chinhs/:id} : delete the "id" cdtTaiChinh.
     *
     * @param id the id of the cdtTaiChinh to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cdt-tai-chinhs/{id}")
    public ResponseEntity<Void> deleteCdtTaiChinh(@PathVariable Long id) {
        log.debug("REST request to delete CdtTaiChinh : {}", id);
        cdtTaiChinhService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
