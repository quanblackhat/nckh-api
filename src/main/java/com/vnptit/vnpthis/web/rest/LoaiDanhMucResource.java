package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.domain.nckh.LoaiDanhMuc;
import com.vnptit.vnpthis.service.LoaiDanhMucService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.LoaiDanhMucDTO;
import com.vnptit.vnpthis.service.dto.LoaiDanhMucCriteria;
import com.vnptit.vnpthis.service.LoaiDanhMucQueryService;

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
 * REST controller for managing {@link LoaiDanhMuc}.
 */
@RestController
@RequestMapping("/api")
public class LoaiDanhMucResource {

    private final Logger log = LoggerFactory.getLogger(LoaiDanhMucResource.class);

    private static final String ENTITY_NAME = "loaiDanhMuc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoaiDanhMucService loaiDanhMucService;

    private final LoaiDanhMucQueryService loaiDanhMucQueryService;

    public LoaiDanhMucResource(LoaiDanhMucService loaiDanhMucService, LoaiDanhMucQueryService loaiDanhMucQueryService) {
        this.loaiDanhMucService = loaiDanhMucService;
        this.loaiDanhMucQueryService = loaiDanhMucQueryService;
    }

    /**
     * {@code POST  /loai-danh-mucs} : Create a new loaiDanhMuc.
     *
     * @param loaiDanhMucDTO the loaiDanhMucDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loaiDanhMucDTO, or with status {@code 400 (Bad Request)} if the loaiDanhMuc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/loai-danh-mucs")
    public ResponseEntity<LoaiDanhMucDTO> createLoaiDanhMuc(@Valid @RequestBody LoaiDanhMucDTO loaiDanhMucDTO) throws URISyntaxException {
        log.debug("REST request to save LoaiDanhMuc : {}", loaiDanhMucDTO);
        if (loaiDanhMucDTO.getId() != null) {
            throw new BadRequestAlertException("A new loaiDanhMuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoaiDanhMucDTO result = loaiDanhMucService.save(loaiDanhMucDTO);
        return ResponseEntity.created(new URI("/api/loai-danh-mucs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /loai-danh-mucs} : Updates an existing loaiDanhMuc.
     *
     * @param loaiDanhMucDTO the loaiDanhMucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loaiDanhMucDTO,
     * or with status {@code 400 (Bad Request)} if the loaiDanhMucDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loaiDanhMucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/loai-danh-mucs")
    public ResponseEntity<LoaiDanhMucDTO> updateLoaiDanhMuc(@Valid @RequestBody LoaiDanhMucDTO loaiDanhMucDTO) throws URISyntaxException {
        log.debug("REST request to update LoaiDanhMuc : {}", loaiDanhMucDTO);
        if (loaiDanhMucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoaiDanhMucDTO result = loaiDanhMucService.save(loaiDanhMucDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, loaiDanhMucDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /loai-danh-mucs} : get all the loaiDanhMucs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loaiDanhMucs in body.
     */
    @GetMapping("/loai-danh-mucs")
    public ResponseEntity<List<LoaiDanhMucDTO>> getAllLoaiDanhMucs(LoaiDanhMucCriteria criteria, Pageable pageable) {
        log.debug("REST request to get LoaiDanhMucs by criteria: {}", criteria);
        Page<LoaiDanhMucDTO> page = loaiDanhMucQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /loai-danh-mucs/count} : count all the loaiDanhMucs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/loai-danh-mucs/count")
    public ResponseEntity<Long> countLoaiDanhMucs(LoaiDanhMucCriteria criteria) {
        log.debug("REST request to count LoaiDanhMucs by criteria: {}", criteria);
        return ResponseEntity.ok().body(loaiDanhMucQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /loai-danh-mucs/:id} : get the "id" loaiDanhMuc.
     *
     * @param id the id of the loaiDanhMucDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loaiDanhMucDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/loai-danh-mucs/{id}")
    public ResponseEntity<LoaiDanhMucDTO> getLoaiDanhMuc(@PathVariable Long id) {
        log.debug("REST request to get LoaiDanhMuc : {}", id);
        Optional<LoaiDanhMucDTO> loaiDanhMucDTO = loaiDanhMucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loaiDanhMucDTO);
    }

    /**
     * {@code DELETE  /loai-danh-mucs/:id} : delete the "id" loaiDanhMuc.
     *
     * @param id the id of the loaiDanhMucDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/loai-danh-mucs/{id}")
    public ResponseEntity<Void> deleteLoaiDanhMuc(@PathVariable Long id) {
        log.debug("REST request to delete LoaiDanhMuc : {}", id);
        loaiDanhMucService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
