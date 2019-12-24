package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.domain.NckhNhanSu;
import com.vnpt.nckh.service.NckhNhanSuService;
import com.vnpt.nckh.web.rest.errors.BadRequestAlertException;
import com.vnpt.nckh.service.dto.NckhNhanSuCriteria;
import com.vnpt.nckh.service.NckhNhanSuQueryService;

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
 * REST controller for managing {@link com.vnpt.nckh.domain.NckhNhanSu}.
 */
@RestController
@RequestMapping("/api")
public class NckhNhanSuResource {

    private final Logger log = LoggerFactory.getLogger(NckhNhanSuResource.class);

    private static final String ENTITY_NAME = "nckhNhanSu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NckhNhanSuService nckhNhanSuService;

    private final NckhNhanSuQueryService nckhNhanSuQueryService;

    public NckhNhanSuResource(NckhNhanSuService nckhNhanSuService, NckhNhanSuQueryService nckhNhanSuQueryService) {
        this.nckhNhanSuService = nckhNhanSuService;
        this.nckhNhanSuQueryService = nckhNhanSuQueryService;
    }

    /**
     * {@code POST  /nckh-nhan-sus} : Create a new nckhNhanSu.
     *
     * @param nckhNhanSu the nckhNhanSu to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nckhNhanSu, or with status {@code 400 (Bad Request)} if the nckhNhanSu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nckh-nhan-sus")
    public ResponseEntity<NckhNhanSu> createNckhNhanSu(@RequestBody NckhNhanSu nckhNhanSu) throws URISyntaxException {
        log.debug("REST request to save NckhNhanSu : {}", nckhNhanSu);
        if (nckhNhanSu.getId() != null) {
            throw new BadRequestAlertException("A new nckhNhanSu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NckhNhanSu result = nckhNhanSuService.save(nckhNhanSu);
        return ResponseEntity.created(new URI("/api/nckh-nhan-sus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nckh-nhan-sus} : Updates an existing nckhNhanSu.
     *
     * @param nckhNhanSu the nckhNhanSu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nckhNhanSu,
     * or with status {@code 400 (Bad Request)} if the nckhNhanSu is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nckhNhanSu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nckh-nhan-sus")
    public ResponseEntity<NckhNhanSu> updateNckhNhanSu(@RequestBody NckhNhanSu nckhNhanSu) throws URISyntaxException {
        log.debug("REST request to update NckhNhanSu : {}", nckhNhanSu);
        if (nckhNhanSu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NckhNhanSu result = nckhNhanSuService.save(nckhNhanSu);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, nckhNhanSu.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nckh-nhan-sus} : get all the nckhNhanSus.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nckhNhanSus in body.
     */
    @GetMapping("/nckh-nhan-sus")
    public ResponseEntity<List<NckhNhanSu>> getAllNckhNhanSus(NckhNhanSuCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NckhNhanSus by criteria: {}", criteria);
        Page<NckhNhanSu> page = nckhNhanSuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /nckh-nhan-sus/count} : count all the nckhNhanSus.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/nckh-nhan-sus/count")
    public ResponseEntity<Long> countNckhNhanSus(NckhNhanSuCriteria criteria) {
        log.debug("REST request to count NckhNhanSus by criteria: {}", criteria);
        return ResponseEntity.ok().body(nckhNhanSuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /nckh-nhan-sus/:id} : get the "id" nckhNhanSu.
     *
     * @param id the id of the nckhNhanSu to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nckhNhanSu, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nckh-nhan-sus/{id}")
    public ResponseEntity<NckhNhanSu> getNckhNhanSu(@PathVariable Long id) {
        log.debug("REST request to get NckhNhanSu : {}", id);
        Optional<NckhNhanSu> nckhNhanSu = nckhNhanSuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nckhNhanSu);
    }

    /**
     * {@code DELETE  /nckh-nhan-sus/:id} : delete the "id" nckhNhanSu.
     *
     * @param id the id of the nckhNhanSu to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nckh-nhan-sus/{id}")
    public ResponseEntity<Void> deleteNckhNhanSu(@PathVariable Long id) {
        log.debug("REST request to delete NckhNhanSu : {}", id);
        nckhNhanSuService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
