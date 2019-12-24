package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.domain.NckhDetai;
import com.vnpt.nckh.service.NckhDetaiService;
import com.vnpt.nckh.web.rest.errors.BadRequestAlertException;
import com.vnpt.nckh.service.dto.NckhDetaiCriteria;
import com.vnpt.nckh.service.NckhDetaiQueryService;

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
 * REST controller for managing {@link com.vnpt.nckh.domain.NckhDetai}.
 */
@RestController
@RequestMapping("/api")
public class NckhDetaiResource {

    private final Logger log = LoggerFactory.getLogger(NckhDetaiResource.class);

    private static final String ENTITY_NAME = "nckhDetai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NckhDetaiService nckhDetaiService;

    private final NckhDetaiQueryService nckhDetaiQueryService;

    public NckhDetaiResource(NckhDetaiService nckhDetaiService, NckhDetaiQueryService nckhDetaiQueryService) {
        this.nckhDetaiService = nckhDetaiService;
        this.nckhDetaiQueryService = nckhDetaiQueryService;
    }

    /**
     * {@code POST  /nckh-detais} : Create a new nckhDetai.
     *
     * @param nckhDetai the nckhDetai to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nckhDetai, or with status {@code 400 (Bad Request)} if the nckhDetai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nckh-detais")
    public ResponseEntity<NckhDetai> createNckhDetai(@Valid @RequestBody NckhDetai nckhDetai) throws URISyntaxException {
        log.debug("REST request to save NckhDetai : {}", nckhDetai);
        if (nckhDetai.getId() != null) {
            throw new BadRequestAlertException("A new nckhDetai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NckhDetai result = nckhDetaiService.save(nckhDetai);
        return ResponseEntity.created(new URI("/api/nckh-detais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nckh-detais} : Updates an existing nckhDetai.
     *
     * @param nckhDetai the nckhDetai to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nckhDetai,
     * or with status {@code 400 (Bad Request)} if the nckhDetai is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nckhDetai couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nckh-detais")
    public ResponseEntity<NckhDetai> updateNckhDetai(@Valid @RequestBody NckhDetai nckhDetai) throws URISyntaxException {
        log.debug("REST request to update NckhDetai : {}", nckhDetai);
        if (nckhDetai.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NckhDetai result = nckhDetaiService.save(nckhDetai);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, nckhDetai.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nckh-detais} : get all the nckhDetais.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nckhDetais in body.
     */
    @GetMapping("/nckh-detais")
    public ResponseEntity<List<NckhDetai>> getAllNckhDetais(NckhDetaiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NckhDetais by criteria: {}", criteria);
        Page<NckhDetai> page = nckhDetaiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /nckh-detais/count} : count all the nckhDetais.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/nckh-detais/count")
    public ResponseEntity<Long> countNckhDetais(NckhDetaiCriteria criteria) {
        log.debug("REST request to count NckhDetais by criteria: {}", criteria);
        return ResponseEntity.ok().body(nckhDetaiQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /nckh-detais/:id} : get the "id" nckhDetai.
     *
     * @param id the id of the nckhDetai to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nckhDetai, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nckh-detais/{id}")
    public ResponseEntity<NckhDetai> getNckhDetai(@PathVariable Long id) {
        log.debug("REST request to get NckhDetai : {}", id);
        Optional<NckhDetai> nckhDetai = nckhDetaiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nckhDetai);
    }

    /**
     * {@code DELETE  /nckh-detais/:id} : delete the "id" nckhDetai.
     *
     * @param id the id of the nckhDetai to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nckh-detais/{id}")
    public ResponseEntity<Void> deleteNckhDetai(@PathVariable Long id) {
        log.debug("REST request to delete NckhDetai : {}", id);
        nckhDetaiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
