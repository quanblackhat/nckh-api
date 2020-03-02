package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.domain.nckh.UpFile;
import com.vnptit.vnpthis.service.UpFileService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.UpFileDTO;
import com.vnptit.vnpthis.service.dto.UpFileCriteria;
import com.vnptit.vnpthis.service.UpFileQueryService;

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
 * REST controller for managing {@link UpFile}.
 */
@RestController
@RequestMapping("/api")
public class UpFileResource {

    private final Logger log = LoggerFactory.getLogger(UpFileResource.class);

    private static final String ENTITY_NAME = "upFile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UpFileService upFileService;

    private final UpFileQueryService upFileQueryService;

    public UpFileResource(UpFileService upFileService, UpFileQueryService upFileQueryService) {
        this.upFileService = upFileService;
        this.upFileQueryService = upFileQueryService;
    }

    /**
     * {@code POST  /up-files} : Create a new upFile.
     *
     * @param upFileDTO the upFileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new upFileDTO, or with status {@code 400 (Bad Request)} if the upFile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/up-files")
    public ResponseEntity<UpFileDTO> createUpFile(@Valid @RequestBody UpFileDTO upFileDTO) throws URISyntaxException {
        log.debug("REST request to save UpFile : {}", upFileDTO);
        if (upFileDTO.getId() != null) {
            throw new BadRequestAlertException("A new upFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UpFileDTO result = upFileService.save(upFileDTO);
        return ResponseEntity.created(new URI("/api/up-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /up-files} : Updates an existing upFile.
     *
     * @param upFileDTO the upFileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated upFileDTO,
     * or with status {@code 400 (Bad Request)} if the upFileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the upFileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/up-files")
    public ResponseEntity<UpFileDTO> updateUpFile(@Valid @RequestBody UpFileDTO upFileDTO) throws URISyntaxException {
        log.debug("REST request to update UpFile : {}", upFileDTO);
        if (upFileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UpFileDTO result = upFileService.save(upFileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, upFileDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /up-files} : get all the upFiles.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of upFiles in body.
     */
    @GetMapping("/up-files")
    public ResponseEntity<List<UpFileDTO>> getAllUpFiles(UpFileCriteria criteria, Pageable pageable) {
        log.debug("REST request to get UpFiles by criteria: {}", criteria);
        Page<UpFileDTO> page = upFileQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /up-files/count} : count all the upFiles.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/up-files/count")
    public ResponseEntity<Long> countUpFiles(UpFileCriteria criteria) {
        log.debug("REST request to count UpFiles by criteria: {}", criteria);
        return ResponseEntity.ok().body(upFileQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /up-files/:id} : get the "id" upFile.
     *
     * @param id the id of the upFileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the upFileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/up-files/{id}")
    public ResponseEntity<UpFileDTO> getUpFile(@PathVariable Long id) {
        log.debug("REST request to get UpFile : {}", id);
        Optional<UpFileDTO> upFileDTO = upFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(upFileDTO);
    }

    /**
     * {@code DELETE  /up-files/:id} : delete the "id" upFile.
     *
     * @param id the id of the upFileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/up-files/{id}")
    public ResponseEntity<Void> deleteUpFile(@PathVariable Long id) {
        log.debug("REST request to delete UpFile : {}", id);
        upFileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
