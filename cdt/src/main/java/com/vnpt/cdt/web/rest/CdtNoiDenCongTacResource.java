package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.domain.CdtNoiDenCongTac;
import com.vnpt.cdt.service.CdtNoiDenCongTacService;
import com.vnpt.cdt.web.rest.errors.BadRequestAlertException;
import com.vnpt.cdt.service.dto.CdtNoiDenCongTacCriteria;
import com.vnpt.cdt.service.CdtNoiDenCongTacQueryService;

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
 * REST controller for managing {@link com.vnpt.cdt.domain.CdtNoiDenCongTac}.
 */
@RestController
@RequestMapping("/api")
public class CdtNoiDenCongTacResource {

    private final Logger log = LoggerFactory.getLogger(CdtNoiDenCongTacResource.class);

    private static final String ENTITY_NAME = "cdtNoiDenCongTac";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CdtNoiDenCongTacService cdtNoiDenCongTacService;

    private final CdtNoiDenCongTacQueryService cdtNoiDenCongTacQueryService;

    public CdtNoiDenCongTacResource(CdtNoiDenCongTacService cdtNoiDenCongTacService, CdtNoiDenCongTacQueryService cdtNoiDenCongTacQueryService) {
        this.cdtNoiDenCongTacService = cdtNoiDenCongTacService;
        this.cdtNoiDenCongTacQueryService = cdtNoiDenCongTacQueryService;
    }

    /**
     * {@code POST  /cdt-noi-den-cong-tacs} : Create a new cdtNoiDenCongTac.
     *
     * @param cdtNoiDenCongTac the cdtNoiDenCongTac to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cdtNoiDenCongTac, or with status {@code 400 (Bad Request)} if the cdtNoiDenCongTac has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cdt-noi-den-cong-tacs")
    public ResponseEntity<CdtNoiDenCongTac> createCdtNoiDenCongTac(@Valid @RequestBody CdtNoiDenCongTac cdtNoiDenCongTac) throws URISyntaxException {
        log.debug("REST request to save CdtNoiDenCongTac : {}", cdtNoiDenCongTac);
        if (cdtNoiDenCongTac.getId() != null) {
            throw new BadRequestAlertException("A new cdtNoiDenCongTac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CdtNoiDenCongTac result = cdtNoiDenCongTacService.save(cdtNoiDenCongTac);
        return ResponseEntity.created(new URI("/api/cdt-noi-den-cong-tacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cdt-noi-den-cong-tacs} : Updates an existing cdtNoiDenCongTac.
     *
     * @param cdtNoiDenCongTac the cdtNoiDenCongTac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cdtNoiDenCongTac,
     * or with status {@code 400 (Bad Request)} if the cdtNoiDenCongTac is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cdtNoiDenCongTac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cdt-noi-den-cong-tacs")
    public ResponseEntity<CdtNoiDenCongTac> updateCdtNoiDenCongTac(@Valid @RequestBody CdtNoiDenCongTac cdtNoiDenCongTac) throws URISyntaxException {
        log.debug("REST request to update CdtNoiDenCongTac : {}", cdtNoiDenCongTac);
        if (cdtNoiDenCongTac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CdtNoiDenCongTac result = cdtNoiDenCongTacService.save(cdtNoiDenCongTac);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cdtNoiDenCongTac.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cdt-noi-den-cong-tacs} : get all the cdtNoiDenCongTacs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cdtNoiDenCongTacs in body.
     */
    @GetMapping("/cdt-noi-den-cong-tacs")
    public ResponseEntity<List<CdtNoiDenCongTac>> getAllCdtNoiDenCongTacs(CdtNoiDenCongTacCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CdtNoiDenCongTacs by criteria: {}", criteria);
        Page<CdtNoiDenCongTac> page = cdtNoiDenCongTacQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cdt-noi-den-cong-tacs/count} : count all the cdtNoiDenCongTacs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cdt-noi-den-cong-tacs/count")
    public ResponseEntity<Long> countCdtNoiDenCongTacs(CdtNoiDenCongTacCriteria criteria) {
        log.debug("REST request to count CdtNoiDenCongTacs by criteria: {}", criteria);
        return ResponseEntity.ok().body(cdtNoiDenCongTacQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cdt-noi-den-cong-tacs/:id} : get the "id" cdtNoiDenCongTac.
     *
     * @param id the id of the cdtNoiDenCongTac to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cdtNoiDenCongTac, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cdt-noi-den-cong-tacs/{id}")
    public ResponseEntity<CdtNoiDenCongTac> getCdtNoiDenCongTac(@PathVariable Long id) {
        log.debug("REST request to get CdtNoiDenCongTac : {}", id);
        Optional<CdtNoiDenCongTac> cdtNoiDenCongTac = cdtNoiDenCongTacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cdtNoiDenCongTac);
    }

    /**
     * {@code DELETE  /cdt-noi-den-cong-tacs/:id} : delete the "id" cdtNoiDenCongTac.
     *
     * @param id the id of the cdtNoiDenCongTac to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cdt-noi-den-cong-tacs/{id}")
    public ResponseEntity<Void> deleteCdtNoiDenCongTac(@PathVariable Long id) {
        log.debug("REST request to delete CdtNoiDenCongTac : {}", id);
        cdtNoiDenCongTacService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
