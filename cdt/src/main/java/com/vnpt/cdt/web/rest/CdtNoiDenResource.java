package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.domain.CdtNoiDen;
import com.vnpt.cdt.service.CdtNoiDenService;
import com.vnpt.cdt.web.rest.errors.BadRequestAlertException;
import com.vnpt.cdt.service.dto.CdtNoiDenCriteria;
import com.vnpt.cdt.service.CdtNoiDenQueryService;

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
 * REST controller for managing {@link com.vnpt.cdt.domain.CdtNoiDen}.
 */
@RestController
@RequestMapping("/api")
public class CdtNoiDenResource {

    private final Logger log = LoggerFactory.getLogger(CdtNoiDenResource.class);

    private static final String ENTITY_NAME = "cdtNoiDen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CdtNoiDenService cdtNoiDenService;

    private final CdtNoiDenQueryService cdtNoiDenQueryService;

    public CdtNoiDenResource(CdtNoiDenService cdtNoiDenService, CdtNoiDenQueryService cdtNoiDenQueryService) {
        this.cdtNoiDenService = cdtNoiDenService;
        this.cdtNoiDenQueryService = cdtNoiDenQueryService;
    }

    /**
     * {@code POST  /cdt-noi-dens} : Create a new cdtNoiDen.
     *
     * @param cdtNoiDen the cdtNoiDen to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cdtNoiDen, or with status {@code 400 (Bad Request)} if the cdtNoiDen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cdt-noi-dens")
    public ResponseEntity<CdtNoiDen> createCdtNoiDen(@RequestBody CdtNoiDen cdtNoiDen) throws URISyntaxException {
        log.debug("REST request to save CdtNoiDen : {}", cdtNoiDen);
        if (cdtNoiDen.getId() != null) {
            throw new BadRequestAlertException("A new cdtNoiDen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CdtNoiDen result = cdtNoiDenService.save(cdtNoiDen);
        return ResponseEntity.created(new URI("/api/cdt-noi-dens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cdt-noi-dens} : Updates an existing cdtNoiDen.
     *
     * @param cdtNoiDen the cdtNoiDen to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cdtNoiDen,
     * or with status {@code 400 (Bad Request)} if the cdtNoiDen is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cdtNoiDen couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cdt-noi-dens")
    public ResponseEntity<CdtNoiDen> updateCdtNoiDen(@RequestBody CdtNoiDen cdtNoiDen) throws URISyntaxException {
        log.debug("REST request to update CdtNoiDen : {}", cdtNoiDen);
        if (cdtNoiDen.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CdtNoiDen result = cdtNoiDenService.save(cdtNoiDen);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cdtNoiDen.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cdt-noi-dens} : get all the cdtNoiDens.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cdtNoiDens in body.
     */
    @GetMapping("/cdt-noi-dens")
    public ResponseEntity<List<CdtNoiDen>> getAllCdtNoiDens(CdtNoiDenCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CdtNoiDens by criteria: {}", criteria);
        Page<CdtNoiDen> page = cdtNoiDenQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cdt-noi-dens/count} : count all the cdtNoiDens.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cdt-noi-dens/count")
    public ResponseEntity<Long> countCdtNoiDens(CdtNoiDenCriteria criteria) {
        log.debug("REST request to count CdtNoiDens by criteria: {}", criteria);
        return ResponseEntity.ok().body(cdtNoiDenQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cdt-noi-dens/:id} : get the "id" cdtNoiDen.
     *
     * @param id the id of the cdtNoiDen to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cdtNoiDen, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cdt-noi-dens/{id}")
    public ResponseEntity<CdtNoiDen> getCdtNoiDen(@PathVariable Long id) {
        log.debug("REST request to get CdtNoiDen : {}", id);
        Optional<CdtNoiDen> cdtNoiDen = cdtNoiDenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cdtNoiDen);
    }

    /**
     * {@code DELETE  /cdt-noi-dens/:id} : delete the "id" cdtNoiDen.
     *
     * @param id the id of the cdtNoiDen to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cdt-noi-dens/{id}")
    public ResponseEntity<Void> deleteCdtNoiDen(@PathVariable Long id) {
        log.debug("REST request to delete CdtNoiDen : {}", id);
        cdtNoiDenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
