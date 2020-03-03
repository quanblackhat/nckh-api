package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.qldt.QldtDmNoidung;
import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotaoct;
import com.vnptit.vnpthis.repository.qldt.QldtDmNoidungRepository;
import com.vnptit.vnpthis.service.qldt.QldtDmNoidungService;
import com.vnptit.vnpthis.service.dto.QldtDmNoidungDTO;
import com.vnptit.vnpthis.service.mapper.QldtDmNoidungMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.web.rest.qldt.QldtDmNoidungResource;
import com.vnptit.vnpthis.service.dto.QldtDmNoidungCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDmNoidungQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.vnptit.vnpthis.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link QldtDmNoidungResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class QldtDmNoidungResourceIT {

    private static final String DEFAULT_NOIDUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOIDUNG = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private QldtDmNoidungRepository qldtDmNoidungRepository;

    @Autowired
    private QldtDmNoidungMapper qldtDmNoidungMapper;

    @Autowired
    private QldtDmNoidungService qldtDmNoidungService;

    @Autowired
    private QldtDmNoidungQueryService qldtDmNoidungQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restQldtDmNoidungMockMvc;

    private QldtDmNoidung qldtDmNoidung;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QldtDmNoidungResource qldtDmNoidungResource = new QldtDmNoidungResource(qldtDmNoidungService, qldtDmNoidungQueryService);
        this.restQldtDmNoidungMockMvc = MockMvcBuilders.standaloneSetup(qldtDmNoidungResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtDmNoidung createEntity(EntityManager em) {
        QldtDmNoidung qldtDmNoidung = new QldtDmNoidung()
            .noidung(DEFAULT_NOIDUNG)
            .sudung(DEFAULT_SUDUNG);
        return qldtDmNoidung;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtDmNoidung createUpdatedEntity(EntityManager em) {
        QldtDmNoidung qldtDmNoidung = new QldtDmNoidung()
            .noidung(UPDATED_NOIDUNG)
            .sudung(UPDATED_SUDUNG);
        return qldtDmNoidung;
    }

    @BeforeEach
    public void initTest() {
        qldtDmNoidung = createEntity(em);
    }

    @Test
    @Transactional
    public void createQldtDmNoidung() throws Exception {
        int databaseSizeBeforeCreate = qldtDmNoidungRepository.findAll().size();

        // Create the QldtDmNoidung
        QldtDmNoidungDTO qldtDmNoidungDTO = qldtDmNoidungMapper.toDto(qldtDmNoidung);
        restQldtDmNoidungMockMvc.perform(post("/api/qldt-dm-noidungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmNoidungDTO)))
            .andExpect(status().isCreated());

        // Validate the QldtDmNoidung in the database
        List<QldtDmNoidung> qldtDmNoidungList = qldtDmNoidungRepository.findAll();
        assertThat(qldtDmNoidungList).hasSize(databaseSizeBeforeCreate + 1);
        QldtDmNoidung testQldtDmNoidung = qldtDmNoidungList.get(qldtDmNoidungList.size() - 1);
        assertThat(testQldtDmNoidung.getNoidung()).isEqualTo(DEFAULT_NOIDUNG);
        assertThat(testQldtDmNoidung.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createQldtDmNoidungWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qldtDmNoidungRepository.findAll().size();

        // Create the QldtDmNoidung with an existing ID
        qldtDmNoidung.setId(1L);
        QldtDmNoidungDTO qldtDmNoidungDTO = qldtDmNoidungMapper.toDto(qldtDmNoidung);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQldtDmNoidungMockMvc.perform(post("/api/qldt-dm-noidungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmNoidungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDmNoidung in the database
        List<QldtDmNoidung> qldtDmNoidungList = qldtDmNoidungRepository.findAll();
        assertThat(qldtDmNoidungList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQldtDmNoidungs() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList
        restQldtDmNoidungMockMvc.perform(get("/api/qldt-dm-noidungs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDmNoidung.getId().intValue())))
            .andExpect(jsonPath("$.[*].noidung").value(hasItem(DEFAULT_NOIDUNG)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getQldtDmNoidung() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get the qldtDmNoidung
        restQldtDmNoidungMockMvc.perform(get("/api/qldt-dm-noidungs/{id}", qldtDmNoidung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qldtDmNoidung.getId().intValue()))
            .andExpect(jsonPath("$.noidung").value(DEFAULT_NOIDUNG))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getQldtDmNoidungsByIdFiltering() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        Long id = qldtDmNoidung.getId();

        defaultQldtDmNoidungShouldBeFound("id.equals=" + id);
        defaultQldtDmNoidungShouldNotBeFound("id.notEquals=" + id);

        defaultQldtDmNoidungShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultQldtDmNoidungShouldNotBeFound("id.greaterThan=" + id);

        defaultQldtDmNoidungShouldBeFound("id.lessThanOrEqual=" + id);
        defaultQldtDmNoidungShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllQldtDmNoidungsByNoidungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where noidung equals to DEFAULT_NOIDUNG
        defaultQldtDmNoidungShouldBeFound("noidung.equals=" + DEFAULT_NOIDUNG);

        // Get all the qldtDmNoidungList where noidung equals to UPDATED_NOIDUNG
        defaultQldtDmNoidungShouldNotBeFound("noidung.equals=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNoidungsByNoidungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where noidung not equals to DEFAULT_NOIDUNG
        defaultQldtDmNoidungShouldNotBeFound("noidung.notEquals=" + DEFAULT_NOIDUNG);

        // Get all the qldtDmNoidungList where noidung not equals to UPDATED_NOIDUNG
        defaultQldtDmNoidungShouldBeFound("noidung.notEquals=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNoidungsByNoidungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where noidung in DEFAULT_NOIDUNG or UPDATED_NOIDUNG
        defaultQldtDmNoidungShouldBeFound("noidung.in=" + DEFAULT_NOIDUNG + "," + UPDATED_NOIDUNG);

        // Get all the qldtDmNoidungList where noidung equals to UPDATED_NOIDUNG
        defaultQldtDmNoidungShouldNotBeFound("noidung.in=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNoidungsByNoidungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where noidung is not null
        defaultQldtDmNoidungShouldBeFound("noidung.specified=true");

        // Get all the qldtDmNoidungList where noidung is null
        defaultQldtDmNoidungShouldNotBeFound("noidung.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDmNoidungsByNoidungContainsSomething() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where noidung contains DEFAULT_NOIDUNG
        defaultQldtDmNoidungShouldBeFound("noidung.contains=" + DEFAULT_NOIDUNG);

        // Get all the qldtDmNoidungList where noidung contains UPDATED_NOIDUNG
        defaultQldtDmNoidungShouldNotBeFound("noidung.contains=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNoidungsByNoidungNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where noidung does not contain DEFAULT_NOIDUNG
        defaultQldtDmNoidungShouldNotBeFound("noidung.doesNotContain=" + DEFAULT_NOIDUNG);

        // Get all the qldtDmNoidungList where noidung does not contain UPDATED_NOIDUNG
        defaultQldtDmNoidungShouldBeFound("noidung.doesNotContain=" + UPDATED_NOIDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtDmNoidungsBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where sudung equals to DEFAULT_SUDUNG
        defaultQldtDmNoidungShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNoidungList where sudung equals to UPDATED_SUDUNG
        defaultQldtDmNoidungShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNoidungsBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where sudung not equals to DEFAULT_SUDUNG
        defaultQldtDmNoidungShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNoidungList where sudung not equals to UPDATED_SUDUNG
        defaultQldtDmNoidungShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNoidungsBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultQldtDmNoidungShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the qldtDmNoidungList where sudung equals to UPDATED_SUDUNG
        defaultQldtDmNoidungShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNoidungsBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where sudung is not null
        defaultQldtDmNoidungShouldBeFound("sudung.specified=true");

        // Get all the qldtDmNoidungList where sudung is null
        defaultQldtDmNoidungShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDmNoidungsBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultQldtDmNoidungShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNoidungList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultQldtDmNoidungShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNoidungsBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultQldtDmNoidungShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNoidungList where sudung is less than or equal to SMALLER_SUDUNG
        defaultQldtDmNoidungShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNoidungsBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where sudung is less than DEFAULT_SUDUNG
        defaultQldtDmNoidungShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNoidungList where sudung is less than UPDATED_SUDUNG
        defaultQldtDmNoidungShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNoidungsBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        // Get all the qldtDmNoidungList where sudung is greater than DEFAULT_SUDUNG
        defaultQldtDmNoidungShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNoidungList where sudung is greater than SMALLER_SUDUNG
        defaultQldtDmNoidungShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtDmNoidungsByDuToandaotaoCtIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);
        QldtDutoanDaotaoct duToandaotaoCt = QldtDutoanDaotaoctResourceIT.createEntity(em);
        em.persist(duToandaotaoCt);
        em.flush();
        qldtDmNoidung.addDuToandaotaoCt(duToandaotaoCt);
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);
        Long duToandaotaoCtId = duToandaotaoCt.getId();

        // Get all the qldtDmNoidungList where duToandaotaoCt equals to duToandaotaoCtId
        defaultQldtDmNoidungShouldBeFound("duToandaotaoCtId.equals=" + duToandaotaoCtId);

        // Get all the qldtDmNoidungList where duToandaotaoCt equals to duToandaotaoCtId + 1
        defaultQldtDmNoidungShouldNotBeFound("duToandaotaoCtId.equals=" + (duToandaotaoCtId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQldtDmNoidungShouldBeFound(String filter) throws Exception {
        restQldtDmNoidungMockMvc.perform(get("/api/qldt-dm-noidungs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDmNoidung.getId().intValue())))
            .andExpect(jsonPath("$.[*].noidung").value(hasItem(DEFAULT_NOIDUNG)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restQldtDmNoidungMockMvc.perform(get("/api/qldt-dm-noidungs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQldtDmNoidungShouldNotBeFound(String filter) throws Exception {
        restQldtDmNoidungMockMvc.perform(get("/api/qldt-dm-noidungs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQldtDmNoidungMockMvc.perform(get("/api/qldt-dm-noidungs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingQldtDmNoidung() throws Exception {
        // Get the qldtDmNoidung
        restQldtDmNoidungMockMvc.perform(get("/api/qldt-dm-noidungs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQldtDmNoidung() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        int databaseSizeBeforeUpdate = qldtDmNoidungRepository.findAll().size();

        // Update the qldtDmNoidung
        QldtDmNoidung updatedQldtDmNoidung = qldtDmNoidungRepository.findById(qldtDmNoidung.getId()).get();
        // Disconnect from session so that the updates on updatedQldtDmNoidung are not directly saved in db
        em.detach(updatedQldtDmNoidung);
        updatedQldtDmNoidung
            .noidung(UPDATED_NOIDUNG)
            .sudung(UPDATED_SUDUNG);
        QldtDmNoidungDTO qldtDmNoidungDTO = qldtDmNoidungMapper.toDto(updatedQldtDmNoidung);

        restQldtDmNoidungMockMvc.perform(put("/api/qldt-dm-noidungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmNoidungDTO)))
            .andExpect(status().isOk());

        // Validate the QldtDmNoidung in the database
        List<QldtDmNoidung> qldtDmNoidungList = qldtDmNoidungRepository.findAll();
        assertThat(qldtDmNoidungList).hasSize(databaseSizeBeforeUpdate);
        QldtDmNoidung testQldtDmNoidung = qldtDmNoidungList.get(qldtDmNoidungList.size() - 1);
        assertThat(testQldtDmNoidung.getNoidung()).isEqualTo(UPDATED_NOIDUNG);
        assertThat(testQldtDmNoidung.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingQldtDmNoidung() throws Exception {
        int databaseSizeBeforeUpdate = qldtDmNoidungRepository.findAll().size();

        // Create the QldtDmNoidung
        QldtDmNoidungDTO qldtDmNoidungDTO = qldtDmNoidungMapper.toDto(qldtDmNoidung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQldtDmNoidungMockMvc.perform(put("/api/qldt-dm-noidungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmNoidungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDmNoidung in the database
        List<QldtDmNoidung> qldtDmNoidungList = qldtDmNoidungRepository.findAll();
        assertThat(qldtDmNoidungList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQldtDmNoidung() throws Exception {
        // Initialize the database
        qldtDmNoidungRepository.saveAndFlush(qldtDmNoidung);

        int databaseSizeBeforeDelete = qldtDmNoidungRepository.findAll().size();

        // Delete the qldtDmNoidung
        restQldtDmNoidungMockMvc.perform(delete("/api/qldt-dm-noidungs/{id}", qldtDmNoidung.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QldtDmNoidung> qldtDmNoidungList = qldtDmNoidungRepository.findAll();
        assertThat(qldtDmNoidungList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
