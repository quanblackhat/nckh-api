package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.domain.CdtNhanVien;
import com.vnpt.cdt.service.CdtNhanVienService;
import com.vnpt.cdt.web.rest.errors.BadRequestAlertException;
import com.vnpt.cdt.service.dto.CdtNhanVienCriteria;
import com.vnpt.cdt.service.CdtNhanVienQueryService;

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
 * REST controller for managing {@link com.vnpt.cdt.domain.CdtNhanVien}.
 */
@RestController
@RequestMapping("/api")
public class CdtNhanVienResource {

    private final Logger log = LoggerFactory.getLogger(CdtNhanVienResource.class);

    private static final String ENTITY_NAME = "cdtNhanVien";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CdtNhanVienService cdtNhanVienService;

    private final CdtNhanVienQueryService cdtNhanVienQueryService;

    public CdtNhanVienResource(CdtNhanVienService cdtNhanVienService, CdtNhanVienQueryService cdtNhanVienQueryService) {
        this.cdtNhanVienService = cdtNhanVienService;
        this.cdtNhanVienQueryService = cdtNhanVienQueryService;
    }

    /**
     * {@code POST  /cdt-nhan-viens} : Create a new cdtNhanVien.
     *
     * @param cdtNhanVien the cdtNhanVien to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cdtNhanVien, or with status {@code 400 (Bad Request)} if the cdtNhanVien has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cdt-nhan-viens")
    public ResponseEntity<CdtNhanVien> createCdtNhanVien(@RequestBody CdtNhanVien cdtNhanVien) throws URISyntaxException {
        log.debug("REST request to save CdtNhanVien : {}", cdtNhanVien);
        if (cdtNhanVien.getId() != null) {
            throw new BadRequestAlertException("A new cdtNhanVien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CdtNhanVien result = cdtNhanVienService.save(cdtNhanVien);
        return ResponseEntity.created(new URI("/api/cdt-nhan-viens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cdt-nhan-viens} : Updates an existing cdtNhanVien.
     *
     * @param cdtNhanVien the cdtNhanVien to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cdtNhanVien,
     * or with status {@code 400 (Bad Request)} if the cdtNhanVien is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cdtNhanVien couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cdt-nhan-viens")
    public ResponseEntity<CdtNhanVien> updateCdtNhanVien(@RequestBody CdtNhanVien cdtNhanVien) throws URISyntaxException {
        log.debug("REST request to update CdtNhanVien : {}", cdtNhanVien);
        if (cdtNhanVien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CdtNhanVien result = cdtNhanVienService.save(cdtNhanVien);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cdtNhanVien.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cdt-nhan-viens} : get all the cdtNhanViens.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cdtNhanViens in body.
     */
    @GetMapping("/cdt-nhan-viens")
    public ResponseEntity<List<CdtNhanVien>> getAllCdtNhanViens(CdtNhanVienCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CdtNhanViens by criteria: {}", criteria);
        Page<CdtNhanVien> page = cdtNhanVienQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cdt-nhan-viens/count} : count all the cdtNhanViens.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cdt-nhan-viens/count")
    public ResponseEntity<Long> countCdtNhanViens(CdtNhanVienCriteria criteria) {
        log.debug("REST request to count CdtNhanViens by criteria: {}", criteria);
        return ResponseEntity.ok().body(cdtNhanVienQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cdt-nhan-viens/:id} : get the "id" cdtNhanVien.
     *
     * @param id the id of the cdtNhanVien to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cdtNhanVien, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cdt-nhan-viens/{id}")
    public ResponseEntity<CdtNhanVien> getCdtNhanVien(@PathVariable Long id) {
        log.debug("REST request to get CdtNhanVien : {}", id);
        Optional<CdtNhanVien> cdtNhanVien = cdtNhanVienService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cdtNhanVien);
    }

    /**
     * {@code DELETE  /cdt-nhan-viens/:id} : delete the "id" cdtNhanVien.
     *
     * @param id the id of the cdtNhanVien to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cdt-nhan-viens/{id}")
    public ResponseEntity<Void> deleteCdtNhanVien(@PathVariable Long id) {
        log.debug("REST request to delete CdtNhanVien : {}", id);
        cdtNhanVienService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
