package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.domain.nckh.DanhGia;
import com.vnptit.vnpthis.service.DanhGiaService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.DanhGiaDTO;
import com.vnptit.vnpthis.service.dto.DanhGiaCriteria;
import com.vnptit.vnpthis.service.DanhGiaQueryService;

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
 * REST controller for managing {@link DanhGia}.
 */
@RestController
@RequestMapping("/api")
public class DanhGiaResource {

    private final Logger log = LoggerFactory.getLogger(DanhGiaResource.class);

    private static final String ENTITY_NAME = "danhGia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanhGiaService danhGiaService;

    private final DanhGiaQueryService danhGiaQueryService;

    public DanhGiaResource(DanhGiaService danhGiaService, DanhGiaQueryService danhGiaQueryService) {
        this.danhGiaService = danhGiaService;
        this.danhGiaQueryService = danhGiaQueryService;
    }

    /**
     * {@code POST  /danh-gias} : Create a new danhGia.
     *
     * @param danhGiaDTO the danhGiaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danhGiaDTO, or with status {@code 400 (Bad Request)} if the danhGia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/danh-gias")
    public ResponseEntity<DanhGiaDTO> createDanhGia(@Valid @RequestBody DanhGiaDTO danhGiaDTO) throws URISyntaxException {
        log.debug("REST request to save DanhGia : {}", danhGiaDTO);
        if (danhGiaDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhGia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DanhGiaDTO result = danhGiaService.save(danhGiaDTO);
        return ResponseEntity.created(new URI("/api/danh-gias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /danh-gias} : Updates an existing danhGia.
     *
     * @param danhGiaDTO the danhGiaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danhGiaDTO,
     * or with status {@code 400 (Bad Request)} if the danhGiaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danhGiaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/danh-gias")
    public ResponseEntity<DanhGiaDTO> updateDanhGia(@Valid @RequestBody DanhGiaDTO danhGiaDTO) throws URISyntaxException {
        log.debug("REST request to update DanhGia : {}", danhGiaDTO);
        if (danhGiaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DanhGiaDTO result = danhGiaService.save(danhGiaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, danhGiaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /danh-gias} : get all the danhGias.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danhGias in body.
     */
    @GetMapping("/danh-gias")
    public ResponseEntity<List<DanhGiaDTO>> getAllDanhGias(DanhGiaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DanhGias by criteria: {}", criteria);
        Page<DanhGiaDTO> page = danhGiaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /danh-gias/count} : count all the danhGias.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/danh-gias/count")
    public ResponseEntity<Long> countDanhGias(DanhGiaCriteria criteria) {
        log.debug("REST request to count DanhGias by criteria: {}", criteria);
        return ResponseEntity.ok().body(danhGiaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /danh-gias/:id} : get the "id" danhGia.
     *
     * @param id the id of the danhGiaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danhGiaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/danh-gias/{id}")
    public ResponseEntity<DanhGiaDTO> getDanhGia(@PathVariable Long id) {
        log.debug("REST request to get DanhGia : {}", id);
        Optional<DanhGiaDTO> danhGiaDTO = danhGiaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhGiaDTO);
    }

    /**
     * {@code DELETE  /danh-gias/:id} : delete the "id" danhGia.
     *
     * @param id the id of the danhGiaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/danh-gias/{id}")
    public ResponseEntity<Void> deleteDanhGia(@PathVariable Long id) {
        log.debug("REST request to delete DanhGia : {}", id);
        danhGiaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
