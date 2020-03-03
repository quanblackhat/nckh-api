package com.vnptit.vnpthis.web.rest.qldt;

import com.vnptit.vnpthis.service.qldt.QldtQlHocvienCtService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienCtDTO;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienCtCriteria;
import com.vnptit.vnpthis.service.qldt.QldtQlHocvienCtQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.qldt.QldtQlHocvienCt}.
 */
@RestController
@RequestMapping("/api")
public class QldtQlHocvienCtResource {

    private final Logger log = LoggerFactory.getLogger(QldtQlHocvienCtResource.class);

    private static final String ENTITY_NAME = "qldtQlHocvienCt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QldtQlHocvienCtService qldtQlHocvienCtService;

    private final QldtQlHocvienCtQueryService qldtQlHocvienCtQueryService;

    public QldtQlHocvienCtResource(QldtQlHocvienCtService qldtQlHocvienCtService, QldtQlHocvienCtQueryService qldtQlHocvienCtQueryService) {
        this.qldtQlHocvienCtService = qldtQlHocvienCtService;
        this.qldtQlHocvienCtQueryService = qldtQlHocvienCtQueryService;
    }

    /**
     * {@code POST  /qldt-ql-hocvien-cts} : Create a new qldtQlHocvienCt.
     *
     * @param qldtQlHocvienCtDTO the qldtQlHocvienCtDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qldtQlHocvienCtDTO, or with status {@code 400 (Bad Request)} if the qldtQlHocvienCt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qldt-ql-hocvien-cts")
    public ResponseEntity<QldtQlHocvienCtDTO> createQldtQlHocvienCt(@RequestBody QldtQlHocvienCtDTO qldtQlHocvienCtDTO) throws URISyntaxException {
        log.debug("REST request to save QldtQlHocvienCt : {}", qldtQlHocvienCtDTO);
        if (qldtQlHocvienCtDTO.getId() != null) {
            throw new BadRequestAlertException("A new qldtQlHocvienCt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QldtQlHocvienCtDTO result = qldtQlHocvienCtService.save(qldtQlHocvienCtDTO);
        return ResponseEntity.created(new URI("/api/qldt-ql-hocvien-cts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qldt-ql-hocvien-cts} : Updates an existing qldtQlHocvienCt.
     *
     * @param qldtQlHocvienCtDTO the qldtQlHocvienCtDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qldtQlHocvienCtDTO,
     * or with status {@code 400 (Bad Request)} if the qldtQlHocvienCtDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qldtQlHocvienCtDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qldt-ql-hocvien-cts")
    public ResponseEntity<QldtQlHocvienCtDTO> updateQldtQlHocvienCt(@RequestBody QldtQlHocvienCtDTO qldtQlHocvienCtDTO) throws URISyntaxException {
        log.debug("REST request to update QldtQlHocvienCt : {}", qldtQlHocvienCtDTO);
        if (qldtQlHocvienCtDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QldtQlHocvienCtDTO result = qldtQlHocvienCtService.save(qldtQlHocvienCtDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qldtQlHocvienCtDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qldt-ql-hocvien-cts} : get all the qldtQlHocvienCts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qldtQlHocvienCts in body.
     */
    @GetMapping("/qldt-ql-hocvien-cts")
    public ResponseEntity<List<QldtQlHocvienCtDTO>> getAllQldtQlHocvienCts(QldtQlHocvienCtCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QldtQlHocvienCts by criteria: {}", criteria);
        Page<QldtQlHocvienCtDTO> page = qldtQlHocvienCtQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qldt-ql-hocvien-cts/count} : count all the qldtQlHocvienCts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/qldt-ql-hocvien-cts/count")
    public ResponseEntity<Long> countQldtQlHocvienCts(QldtQlHocvienCtCriteria criteria) {
        log.debug("REST request to count QldtQlHocvienCts by criteria: {}", criteria);
        return ResponseEntity.ok().body(qldtQlHocvienCtQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /qldt-ql-hocvien-cts/:id} : get the "id" qldtQlHocvienCt.
     *
     * @param id the id of the qldtQlHocvienCtDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qldtQlHocvienCtDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qldt-ql-hocvien-cts/{id}")
    public ResponseEntity<QldtQlHocvienCtDTO> getQldtQlHocvienCt(@PathVariable Long id) {
        log.debug("REST request to get QldtQlHocvienCt : {}", id);
        Optional<QldtQlHocvienCtDTO> qldtQlHocvienCtDTO = qldtQlHocvienCtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qldtQlHocvienCtDTO);
    }

    /**
     * {@code DELETE  /qldt-ql-hocvien-cts/:id} : delete the "id" qldtQlHocvienCt.
     *
     * @param id the id of the qldtQlHocvienCtDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qldt-ql-hocvien-cts/{id}")
    public ResponseEntity<Void> deleteQldtQlHocvienCt(@PathVariable Long id) {
        log.debug("REST request to delete QldtQlHocvienCt : {}", id);
        qldtQlHocvienCtService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
