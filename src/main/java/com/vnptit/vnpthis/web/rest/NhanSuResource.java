package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.domain.nckh.NhanSu;
import com.vnptit.vnpthis.service.NhanSuService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.NhanSuDTO;
import com.vnptit.vnpthis.service.dto.NhanSuCriteria;
import com.vnptit.vnpthis.service.NhanSuQueryService;

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
 * REST controller for managing {@link NhanSu}.
 */
@RestController
@RequestMapping("/api")
public class NhanSuResource {

    private final Logger log = LoggerFactory.getLogger(NhanSuResource.class);

    private static final String ENTITY_NAME = "nhanSu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NhanSuService nhanSuService;

    private final NhanSuQueryService nhanSuQueryService;

    public NhanSuResource(NhanSuService nhanSuService, NhanSuQueryService nhanSuQueryService) {
        this.nhanSuService = nhanSuService;
        this.nhanSuQueryService = nhanSuQueryService;
    }

    /**
     * {@code POST  /nhan-sus} : Create a new nhanSu.
     *
     * @param nhanSuDTO the nhanSuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nhanSuDTO, or with status {@code 400 (Bad Request)} if the nhanSu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nhan-sus")
    public ResponseEntity<NhanSuDTO> createNhanSu(@Valid @RequestBody NhanSuDTO nhanSuDTO) throws URISyntaxException {
        log.debug("REST request to save NhanSu : {}", nhanSuDTO);
        if (nhanSuDTO.getId() != null) {
            throw new BadRequestAlertException("A new nhanSu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NhanSuDTO result = nhanSuService.save(nhanSuDTO);
        return ResponseEntity.created(new URI("/api/nhan-sus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nhan-sus} : Updates an existing nhanSu.
     *
     * @param nhanSuDTO the nhanSuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nhanSuDTO,
     * or with status {@code 400 (Bad Request)} if the nhanSuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nhanSuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nhan-sus")
    public ResponseEntity<NhanSuDTO> updateNhanSu(@Valid @RequestBody NhanSuDTO nhanSuDTO) throws URISyntaxException {
        log.debug("REST request to update NhanSu : {}", nhanSuDTO);
        if (nhanSuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NhanSuDTO result = nhanSuService.save(nhanSuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, nhanSuDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nhan-sus} : get all the nhanSus.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nhanSus in body.
     */
    @GetMapping("/nhan-sus")
    public ResponseEntity<List<NhanSuDTO>> getAllNhanSus(NhanSuCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NhanSus by criteria: {}", criteria);
        Page<NhanSuDTO> page = nhanSuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /nhan-sus/count} : count all the nhanSus.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/nhan-sus/count")
    public ResponseEntity<Long> countNhanSus(NhanSuCriteria criteria) {
        log.debug("REST request to count NhanSus by criteria: {}", criteria);
        return ResponseEntity.ok().body(nhanSuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /nhan-sus/:id} : get the "id" nhanSu.
     *
     * @param id the id of the nhanSuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nhanSuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nhan-sus/{id}")
    public ResponseEntity<NhanSuDTO> getNhanSu(@PathVariable Long id) {
        log.debug("REST request to get NhanSu : {}", id);
        Optional<NhanSuDTO> nhanSuDTO = nhanSuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nhanSuDTO);
    }

    /**
     * {@code DELETE  /nhan-sus/:id} : delete the "id" nhanSu.
     *
     * @param id the id of the nhanSuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nhan-sus/{id}")
    public ResponseEntity<Void> deleteNhanSu(@PathVariable Long id) {
        log.debug("REST request to delete NhanSu : {}", id);
        nhanSuService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
