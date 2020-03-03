package com.vnptit.vnpthis.web.rest.qldt;

import com.vnptit.vnpthis.service.qldt.QldtQlHocvienService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienDTO;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienCriteria;
import com.vnptit.vnpthis.service.qldt.QldtQlHocvienQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.qldt.QldtQlHocvien}.
 */
@RestController
@RequestMapping("/api")
public class QldtQlHocvienResource {

    private final Logger log = LoggerFactory.getLogger(QldtQlHocvienResource.class);

    private static final String ENTITY_NAME = "qldtQlHocvien";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QldtQlHocvienService qldtQlHocvienService;

    private final QldtQlHocvienQueryService qldtQlHocvienQueryService;

    public QldtQlHocvienResource(QldtQlHocvienService qldtQlHocvienService, QldtQlHocvienQueryService qldtQlHocvienQueryService) {
        this.qldtQlHocvienService = qldtQlHocvienService;
        this.qldtQlHocvienQueryService = qldtQlHocvienQueryService;
    }

    /**
     * {@code POST  /qldt-ql-hocviens} : Create a new qldtQlHocvien.
     *
     * @param qldtQlHocvienDTO the qldtQlHocvienDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qldtQlHocvienDTO, or with status {@code 400 (Bad Request)} if the qldtQlHocvien has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qldt-ql-hocviens")
    public ResponseEntity<QldtQlHocvienDTO> createQldtQlHocvien(@RequestBody QldtQlHocvienDTO qldtQlHocvienDTO) throws URISyntaxException {
        log.debug("REST request to save QldtQlHocvien : {}", qldtQlHocvienDTO);
        if (qldtQlHocvienDTO.getId() != null) {
            throw new BadRequestAlertException("A new qldtQlHocvien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QldtQlHocvienDTO result = qldtQlHocvienService.save(qldtQlHocvienDTO);
        return ResponseEntity.created(new URI("/api/qldt-ql-hocviens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qldt-ql-hocviens} : Updates an existing qldtQlHocvien.
     *
     * @param qldtQlHocvienDTO the qldtQlHocvienDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qldtQlHocvienDTO,
     * or with status {@code 400 (Bad Request)} if the qldtQlHocvienDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qldtQlHocvienDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qldt-ql-hocviens")
    public ResponseEntity<QldtQlHocvienDTO> updateQldtQlHocvien(@RequestBody QldtQlHocvienDTO qldtQlHocvienDTO) throws URISyntaxException {
        log.debug("REST request to update QldtQlHocvien : {}", qldtQlHocvienDTO);
        if (qldtQlHocvienDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QldtQlHocvienDTO result = qldtQlHocvienService.save(qldtQlHocvienDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qldtQlHocvienDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qldt-ql-hocviens} : get all the qldtQlHocviens.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qldtQlHocviens in body.
     */
    @GetMapping("/qldt-ql-hocviens")
    public ResponseEntity<List<QldtQlHocvienDTO>> getAllQldtQlHocviens(QldtQlHocvienCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QldtQlHocviens by criteria: {}", criteria);
        Page<QldtQlHocvienDTO> page = qldtQlHocvienQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qldt-ql-hocviens/count} : count all the qldtQlHocviens.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/qldt-ql-hocviens/count")
    public ResponseEntity<Long> countQldtQlHocviens(QldtQlHocvienCriteria criteria) {
        log.debug("REST request to count QldtQlHocviens by criteria: {}", criteria);
        return ResponseEntity.ok().body(qldtQlHocvienQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /qldt-ql-hocviens/:id} : get the "id" qldtQlHocvien.
     *
     * @param id the id of the qldtQlHocvienDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qldtQlHocvienDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qldt-ql-hocviens/{id}")
    public ResponseEntity<QldtQlHocvienDTO> getQldtQlHocvien(@PathVariable Long id) {
        log.debug("REST request to get QldtQlHocvien : {}", id);
        Optional<QldtQlHocvienDTO> qldtQlHocvienDTO = qldtQlHocvienService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qldtQlHocvienDTO);
    }

    /**
     * {@code DELETE  /qldt-ql-hocviens/:id} : delete the "id" qldtQlHocvien.
     *
     * @param id the id of the qldtQlHocvienDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qldt-ql-hocviens/{id}")
    public ResponseEntity<Void> deleteQldtQlHocvien(@PathVariable Long id) {
        log.debug("REST request to delete QldtQlHocvien : {}", id);
        qldtQlHocvienService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
