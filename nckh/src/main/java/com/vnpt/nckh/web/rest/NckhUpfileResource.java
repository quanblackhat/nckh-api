package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.domain.NckhUpfile;
import com.vnpt.nckh.service.NckhUpfileService;
import com.vnpt.nckh.web.rest.errors.BadRequestAlertException;
import com.vnpt.nckh.service.dto.NckhUpfileCriteria;
import com.vnpt.nckh.service.NckhUpfileQueryService;

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
 * REST controller for managing {@link com.vnpt.nckh.domain.NckhUpfile}.
 */
@RestController
@RequestMapping("/api")
public class NckhUpfileResource {

    private final Logger log = LoggerFactory.getLogger(NckhUpfileResource.class);

    private static final String ENTITY_NAME = "nckhUpfile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NckhUpfileService nckhUpfileService;

    private final NckhUpfileQueryService nckhUpfileQueryService;

    public NckhUpfileResource(NckhUpfileService nckhUpfileService, NckhUpfileQueryService nckhUpfileQueryService) {
        this.nckhUpfileService = nckhUpfileService;
        this.nckhUpfileQueryService = nckhUpfileQueryService;
    }

    /**
     * {@code POST  /nckh-upfiles} : Create a new nckhUpfile.
     *
     * @param nckhUpfile the nckhUpfile to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nckhUpfile, or with status {@code 400 (Bad Request)} if the nckhUpfile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nckh-upfiles")
    public ResponseEntity<NckhUpfile> createNckhUpfile(@Valid @RequestBody NckhUpfile nckhUpfile) throws URISyntaxException {
        log.debug("REST request to save NckhUpfile : {}", nckhUpfile);
        if (nckhUpfile.getId() != null) {
            throw new BadRequestAlertException("A new nckhUpfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NckhUpfile result = nckhUpfileService.save(nckhUpfile);
        return ResponseEntity.created(new URI("/api/nckh-upfiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nckh-upfiles} : Updates an existing nckhUpfile.
     *
     * @param nckhUpfile the nckhUpfile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nckhUpfile,
     * or with status {@code 400 (Bad Request)} if the nckhUpfile is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nckhUpfile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nckh-upfiles")
    public ResponseEntity<NckhUpfile> updateNckhUpfile(@Valid @RequestBody NckhUpfile nckhUpfile) throws URISyntaxException {
        log.debug("REST request to update NckhUpfile : {}", nckhUpfile);
        if (nckhUpfile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NckhUpfile result = nckhUpfileService.save(nckhUpfile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, nckhUpfile.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nckh-upfiles} : get all the nckhUpfiles.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nckhUpfiles in body.
     */
    @GetMapping("/nckh-upfiles")
    public ResponseEntity<List<NckhUpfile>> getAllNckhUpfiles(NckhUpfileCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NckhUpfiles by criteria: {}", criteria);
        Page<NckhUpfile> page = nckhUpfileQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /nckh-upfiles/count} : count all the nckhUpfiles.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/nckh-upfiles/count")
    public ResponseEntity<Long> countNckhUpfiles(NckhUpfileCriteria criteria) {
        log.debug("REST request to count NckhUpfiles by criteria: {}", criteria);
        return ResponseEntity.ok().body(nckhUpfileQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /nckh-upfiles/:id} : get the "id" nckhUpfile.
     *
     * @param id the id of the nckhUpfile to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nckhUpfile, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nckh-upfiles/{id}")
    public ResponseEntity<NckhUpfile> getNckhUpfile(@PathVariable Long id) {
        log.debug("REST request to get NckhUpfile : {}", id);
        Optional<NckhUpfile> nckhUpfile = nckhUpfileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nckhUpfile);
    }

    /**
     * {@code DELETE  /nckh-upfiles/:id} : delete the "id" nckhUpfile.
     *
     * @param id the id of the nckhUpfile to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nckh-upfiles/{id}")
    public ResponseEntity<Void> deleteNckhUpfile(@PathVariable Long id) {
        log.debug("REST request to delete NckhUpfile : {}", id);
        nckhUpfileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
