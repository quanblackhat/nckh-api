package com.vnptit.vnpthis.web.rest.qldt;

import com.vnptit.vnpthis.service.qldt.QldtDmNguoidungService;
import com.vnptit.vnpthis.web.rest.errors.BadRequestAlertException;
import com.vnptit.vnpthis.service.dto.QldtDmNguoidungDTO;
import com.vnptit.vnpthis.service.dto.QldtDmNguoidungCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDmNguoidungQueryService;

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
 * REST controller for managing {@link com.vnptit.vnpthis.domain.qldt.QldtDmNguoidung}.
 */
@RestController
@RequestMapping("/api")
public class QldtDmNguoidungResource {

    private final Logger log = LoggerFactory.getLogger(QldtDmNguoidungResource.class);

    private static final String ENTITY_NAME = "qldtDmNguoidung";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QldtDmNguoidungService qldtDmNguoidungService;

    private final QldtDmNguoidungQueryService qldtDmNguoidungQueryService;

    public QldtDmNguoidungResource(QldtDmNguoidungService qldtDmNguoidungService, QldtDmNguoidungQueryService qldtDmNguoidungQueryService) {
        this.qldtDmNguoidungService = qldtDmNguoidungService;
        this.qldtDmNguoidungQueryService = qldtDmNguoidungQueryService;
    }

    /**
     * {@code POST  /qldt-dm-nguoidungs} : Create a new qldtDmNguoidung.
     *
     * @param qldtDmNguoidungDTO the qldtDmNguoidungDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qldtDmNguoidungDTO, or with status {@code 400 (Bad Request)} if the qldtDmNguoidung has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qldt-dm-nguoidungs")
    public ResponseEntity<QldtDmNguoidungDTO> createQldtDmNguoidung(@RequestBody QldtDmNguoidungDTO qldtDmNguoidungDTO) throws URISyntaxException {
        log.debug("REST request to save QldtDmNguoidung : {}", qldtDmNguoidungDTO);
        if (qldtDmNguoidungDTO.getId() != null) {
            throw new BadRequestAlertException("A new qldtDmNguoidung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QldtDmNguoidungDTO result = qldtDmNguoidungService.save(qldtDmNguoidungDTO);
        return ResponseEntity.created(new URI("/api/qldt-dm-nguoidungs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qldt-dm-nguoidungs} : Updates an existing qldtDmNguoidung.
     *
     * @param qldtDmNguoidungDTO the qldtDmNguoidungDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qldtDmNguoidungDTO,
     * or with status {@code 400 (Bad Request)} if the qldtDmNguoidungDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qldtDmNguoidungDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qldt-dm-nguoidungs")
    public ResponseEntity<QldtDmNguoidungDTO> updateQldtDmNguoidung(@RequestBody QldtDmNguoidungDTO qldtDmNguoidungDTO) throws URISyntaxException {
        log.debug("REST request to update QldtDmNguoidung : {}", qldtDmNguoidungDTO);
        if (qldtDmNguoidungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QldtDmNguoidungDTO result = qldtDmNguoidungService.save(qldtDmNguoidungDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qldtDmNguoidungDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qldt-dm-nguoidungs} : get all the qldtDmNguoidungs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qldtDmNguoidungs in body.
     */
    @GetMapping("/qldt-dm-nguoidungs")
    public ResponseEntity<List<QldtDmNguoidungDTO>> getAllQldtDmNguoidungs(QldtDmNguoidungCriteria criteria, Pageable pageable) {
        log.debug("REST request to get QldtDmNguoidungs by criteria: {}", criteria);
        Page<QldtDmNguoidungDTO> page = qldtDmNguoidungQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qldt-dm-nguoidungs/count} : count all the qldtDmNguoidungs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/qldt-dm-nguoidungs/count")
    public ResponseEntity<Long> countQldtDmNguoidungs(QldtDmNguoidungCriteria criteria) {
        log.debug("REST request to count QldtDmNguoidungs by criteria: {}", criteria);
        return ResponseEntity.ok().body(qldtDmNguoidungQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /qldt-dm-nguoidungs/:id} : get the "id" qldtDmNguoidung.
     *
     * @param id the id of the qldtDmNguoidungDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qldtDmNguoidungDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qldt-dm-nguoidungs/{id}")
    public ResponseEntity<QldtDmNguoidungDTO> getQldtDmNguoidung(@PathVariable Long id) {
        log.debug("REST request to get QldtDmNguoidung : {}", id);
        Optional<QldtDmNguoidungDTO> qldtDmNguoidungDTO = qldtDmNguoidungService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qldtDmNguoidungDTO);
    }

    /**
     * {@code DELETE  /qldt-dm-nguoidungs/:id} : delete the "id" qldtDmNguoidung.
     *
     * @param id the id of the qldtDmNguoidungDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qldt-dm-nguoidungs/{id}")
    public ResponseEntity<Void> deleteQldtDmNguoidung(@PathVariable Long id) {
        log.debug("REST request to delete QldtDmNguoidung : {}", id);
        qldtDmNguoidungService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
