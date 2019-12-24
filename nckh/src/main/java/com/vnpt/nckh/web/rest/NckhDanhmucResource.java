package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.domain.NckhDanhmuc;
import com.vnpt.nckh.service.NckhDanhmucService;
import com.vnpt.nckh.web.rest.errors.BadRequestAlertException;
import com.vnpt.nckh.service.dto.NckhDanhmucCriteria;
import com.vnpt.nckh.service.NckhDanhmucQueryService;

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
 * REST controller for managing {@link com.vnpt.nckh.domain.NckhDanhmuc}.
 */
@RestController
@RequestMapping("/api")
public class NckhDanhmucResource {

    private final Logger log = LoggerFactory.getLogger(NckhDanhmucResource.class);

    private static final String ENTITY_NAME = "nckhDanhmuc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NckhDanhmucService nckhDanhmucService;

    private final NckhDanhmucQueryService nckhDanhmucQueryService;

    public NckhDanhmucResource(NckhDanhmucService nckhDanhmucService, NckhDanhmucQueryService nckhDanhmucQueryService) {
        this.nckhDanhmucService = nckhDanhmucService;
        this.nckhDanhmucQueryService = nckhDanhmucQueryService;
    }

    /**
     * {@code POST  /nckh-danhmucs} : Create a new nckhDanhmuc.
     *
     * @param nckhDanhmuc the nckhDanhmuc to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nckhDanhmuc, or with status {@code 400 (Bad Request)} if the nckhDanhmuc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nckh-danhmucs")
    public ResponseEntity<NckhDanhmuc> createNckhDanhmuc(@Valid @RequestBody NckhDanhmuc nckhDanhmuc) throws URISyntaxException {
        log.debug("REST request to save NckhDanhmuc : {}", nckhDanhmuc);
        if (nckhDanhmuc.getId() != null) {
            throw new BadRequestAlertException("A new nckhDanhmuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NckhDanhmuc result = nckhDanhmucService.save(nckhDanhmuc);
        return ResponseEntity.created(new URI("/api/nckh-danhmucs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nckh-danhmucs} : Updates an existing nckhDanhmuc.
     *
     * @param nckhDanhmuc the nckhDanhmuc to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nckhDanhmuc,
     * or with status {@code 400 (Bad Request)} if the nckhDanhmuc is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nckhDanhmuc couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nckh-danhmucs")
    public ResponseEntity<NckhDanhmuc> updateNckhDanhmuc(@Valid @RequestBody NckhDanhmuc nckhDanhmuc) throws URISyntaxException {
        log.debug("REST request to update NckhDanhmuc : {}", nckhDanhmuc);
        if (nckhDanhmuc.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NckhDanhmuc result = nckhDanhmucService.save(nckhDanhmuc);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, nckhDanhmuc.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nckh-danhmucs} : get all the nckhDanhmucs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nckhDanhmucs in body.
     */
    @GetMapping("/nckh-danhmucs")
    public ResponseEntity<List<NckhDanhmuc>> getAllNckhDanhmucs(NckhDanhmucCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NckhDanhmucs by criteria: {}", criteria);
        Page<NckhDanhmuc> page = nckhDanhmucQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /nckh-danhmucs/count} : count all the nckhDanhmucs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/nckh-danhmucs/count")
    public ResponseEntity<Long> countNckhDanhmucs(NckhDanhmucCriteria criteria) {
        log.debug("REST request to count NckhDanhmucs by criteria: {}", criteria);
        return ResponseEntity.ok().body(nckhDanhmucQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /nckh-danhmucs/:id} : get the "id" nckhDanhmuc.
     *
     * @param id the id of the nckhDanhmuc to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nckhDanhmuc, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nckh-danhmucs/{id}")
    public ResponseEntity<NckhDanhmuc> getNckhDanhmuc(@PathVariable Long id) {
        log.debug("REST request to get NckhDanhmuc : {}", id);
        Optional<NckhDanhmuc> nckhDanhmuc = nckhDanhmucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nckhDanhmuc);
    }

    /**
     * {@code DELETE  /nckh-danhmucs/:id} : delete the "id" nckhDanhmuc.
     *
     * @param id the id of the nckhDanhmuc to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nckh-danhmucs/{id}")
    public ResponseEntity<Void> deleteNckhDanhmuc(@PathVariable Long id) {
        log.debug("REST request to delete NckhDanhmuc : {}", id);
        nckhDanhmucService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
