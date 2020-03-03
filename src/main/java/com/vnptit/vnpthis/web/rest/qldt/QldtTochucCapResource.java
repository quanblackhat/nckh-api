package com.vnptit.vnpthis.web.rest.qldt;

import com.vnptit.vnpthis.service.qldt.QldtTochucCapService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.QldtTochucCapDTO;
import com.vnptit.vnpthis.service.dto.QldtTochucCapCriteria;
import com.vnptit.vnpthis.service.qldt.QldtTochucCapQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.qldt.QldtTochucCap}.
 */
@RestController
@RequestMapping("/api")
public class QldtTochucCapResource {

    private final Logger log = LoggerFactory.getLogger(QldtTochucCapResource.class);

    private static final String ENTITY_NAME = "qldtTochucCap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QldtTochucCapService qldtTochucCapService;

    private final QldtTochucCapQueryService qldtTochucCapQueryService;

    public QldtTochucCapResource(QldtTochucCapService qldtTochucCapService, QldtTochucCapQueryService qldtTochucCapQueryService) {
        this.qldtTochucCapService = qldtTochucCapService;
        this.qldtTochucCapQueryService = qldtTochucCapQueryService;
    }

    /**
     * {@code POST  /qldt-tochuc-caps} : Create a new qldtTochucCap.
     *
     * @param qldtTochucCapDTO the qldtTochucCapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qldtTochucCapDTO, or with status {@code 400 (Bad Request)} if the qldtTochucCap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qldt-tochuc-caps")
    public ResponseEntity<QldtTochucCapDTO> createQldtTochucCap(@RequestBody QldtTochucCapDTO qldtTochucCapDTO) throws URISyntaxException {
        log.debug("REST request to save QldtTochucCap : {}", qldtTochucCapDTO);
        if (qldtTochucCapDTO.getId() != null) {
            throw new BadRequestAlertException("A new qldtTochucCap cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QldtTochucCapDTO result = qldtTochucCapService.save(qldtTochucCapDTO);
        return ResponseEntity.created(new URI("/api/qldt-tochuc-caps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qldt-tochuc-caps} : Updates an existing qldtTochucCap.
     *
     * @param qldtTochucCapDTO the qldtTochucCapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qldtTochucCapDTO,
     * or with status {@code 400 (Bad Request)} if the qldtTochucCapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qldtTochucCapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qldt-tochuc-caps")
    public ResponseEntity<QldtTochucCapDTO> updateQldtTochucCap(@RequestBody QldtTochucCapDTO qldtTochucCapDTO) throws URISyntaxException {
        log.debug("REST request to update QldtTochucCap : {}", qldtTochucCapDTO);
        if (qldtTochucCapDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QldtTochucCapDTO result = qldtTochucCapService.save(qldtTochucCapDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qldtTochucCapDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qldt-tochuc-caps} : get all the qldtTochucCaps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qldtTochucCaps in body.
     */
    @GetMapping("/qldt-tochuc-caps")
    public ResponseEntity<List<QldtTochucCapDTO>> getAllQldtTochucCaps(QldtTochucCapCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QldtTochucCaps by criteria: {}", criteria);
        Page<QldtTochucCapDTO> page = qldtTochucCapQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qldt-tochuc-caps/count} : count all the qldtTochucCaps.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/qldt-tochuc-caps/count")
    public ResponseEntity<Long> countQldtTochucCaps(QldtTochucCapCriteria criteria) {
        log.debug("REST request to count QldtTochucCaps by criteria: {}", criteria);
        return ResponseEntity.ok().body(qldtTochucCapQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /qldt-tochuc-caps/:id} : get the "id" qldtTochucCap.
     *
     * @param id the id of the qldtTochucCapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qldtTochucCapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qldt-tochuc-caps/{id}")
    public ResponseEntity<QldtTochucCapDTO> getQldtTochucCap(@PathVariable Long id) {
        log.debug("REST request to get QldtTochucCap : {}", id);
        Optional<QldtTochucCapDTO> qldtTochucCapDTO = qldtTochucCapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qldtTochucCapDTO);
    }

    /**
     * {@code DELETE  /qldt-tochuc-caps/:id} : delete the "id" qldtTochucCap.
     *
     * @param id the id of the qldtTochucCapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qldt-tochuc-caps/{id}")
    public ResponseEntity<Void> deleteQldtTochucCap(@PathVariable Long id) {
        log.debug("REST request to delete QldtTochucCap : {}", id);
        qldtTochucCapService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
