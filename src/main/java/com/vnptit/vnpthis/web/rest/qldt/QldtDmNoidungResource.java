package com.vnptit.vnpthis.web.rest.qldt;

import com.vnptit.vnpthis.service.qldt.QldtDmNoidungService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.QldtDmNoidungDTO;
import com.vnptit.vnpthis.service.dto.QldtDmNoidungCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDmNoidungQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.qldt.QldtDmNoidung}.
 */
@RestController
@RequestMapping("/api")
public class QldtDmNoidungResource {

    private final Logger log = LoggerFactory.getLogger(QldtDmNoidungResource.class);

    private static final String ENTITY_NAME = "qldtDmNoidung";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QldtDmNoidungService qldtDmNoidungService;

    private final QldtDmNoidungQueryService qldtDmNoidungQueryService;

    public QldtDmNoidungResource(QldtDmNoidungService qldtDmNoidungService, QldtDmNoidungQueryService qldtDmNoidungQueryService) {
        this.qldtDmNoidungService = qldtDmNoidungService;
        this.qldtDmNoidungQueryService = qldtDmNoidungQueryService;
    }

    /**
     * {@code POST  /qldt-dm-noidungs} : Create a new qldtDmNoidung.
     *
     * @param qldtDmNoidungDTO the qldtDmNoidungDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qldtDmNoidungDTO, or with status {@code 400 (Bad Request)} if the qldtDmNoidung has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qldt-dm-noidungs")
    public ResponseEntity<QldtDmNoidungDTO> createQldtDmNoidung(@RequestBody QldtDmNoidungDTO qldtDmNoidungDTO) throws URISyntaxException {
        log.debug("REST request to save QldtDmNoidung : {}", qldtDmNoidungDTO);
        if (qldtDmNoidungDTO.getId() != null) {
            throw new BadRequestAlertException("A new qldtDmNoidung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QldtDmNoidungDTO result = qldtDmNoidungService.save(qldtDmNoidungDTO);
        return ResponseEntity.created(new URI("/api/qldt-dm-noidungs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qldt-dm-noidungs} : Updates an existing qldtDmNoidung.
     *
     * @param qldtDmNoidungDTO the qldtDmNoidungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qldtDmNoidungDTO,
     * or with status {@code 400 (Bad Request)} if the qldtDmNoidungDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qldtDmNoidungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qldt-dm-noidungs")
    public ResponseEntity<QldtDmNoidungDTO> updateQldtDmNoidung(@RequestBody QldtDmNoidungDTO qldtDmNoidungDTO) throws URISyntaxException {
        log.debug("REST request to update QldtDmNoidung : {}", qldtDmNoidungDTO);
        if (qldtDmNoidungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QldtDmNoidungDTO result = qldtDmNoidungService.save(qldtDmNoidungDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qldtDmNoidungDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qldt-dm-noidungs} : get all the qldtDmNoidungs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qldtDmNoidungs in body.
     */
    @GetMapping("/qldt-dm-noidungs")
    public ResponseEntity<List<QldtDmNoidungDTO>> getAllQldtDmNoidungs(QldtDmNoidungCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QldtDmNoidungs by criteria: {}", criteria);
        Page<QldtDmNoidungDTO> page = qldtDmNoidungQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qldt-dm-noidungs/count} : count all the qldtDmNoidungs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/qldt-dm-noidungs/count")
    public ResponseEntity<Long> countQldtDmNoidungs(QldtDmNoidungCriteria criteria) {
        log.debug("REST request to count QldtDmNoidungs by criteria: {}", criteria);
        return ResponseEntity.ok().body(qldtDmNoidungQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /qldt-dm-noidungs/:id} : get the "id" qldtDmNoidung.
     *
     * @param id the id of the qldtDmNoidungDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qldtDmNoidungDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qldt-dm-noidungs/{id}")
    public ResponseEntity<QldtDmNoidungDTO> getQldtDmNoidung(@PathVariable Long id) {
        log.debug("REST request to get QldtDmNoidung : {}", id);
        Optional<QldtDmNoidungDTO> qldtDmNoidungDTO = qldtDmNoidungService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qldtDmNoidungDTO);
    }

    /**
     * {@code DELETE  /qldt-dm-noidungs/:id} : delete the "id" qldtDmNoidung.
     *
     * @param id the id of the qldtDmNoidungDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qldt-dm-noidungs/{id}")
    public ResponseEntity<Void> deleteQldtDmNoidung(@PathVariable Long id) {
        log.debug("REST request to delete QldtDmNoidung : {}", id);
        qldtDmNoidungService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
