package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.qldt.QldtTochucCap;
import com.vnptit.vnpthis.domain.qldt.QldtDmChungchi;
import com.vnptit.vnpthis.repository.qldt.QldtTochucCapRepository;
import com.vnptit.vnpthis.service.qldt.QldtTochucCapService;
import com.vnptit.vnpthis.service.dto.QldtTochucCapDTO;
import com.vnptit.vnpthis.service.mapper.QldtTochucCapMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.web.rest.qldt.QldtTochucCapResource;
import com.vnptit.vnpthis.service.dto.QldtTochucCapCriteria;
import com.vnptit.vnpthis.service.qldt.QldtTochucCapQueryService;

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
 * Integration tests for the {@link QldtTochucCapResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class QldtTochucCapResourceIT {

    private static final String DEFAULT_MATOCHUC = "AAAAAAAAAA";
    private static final String UPDATED_MATOCHUC = "BBBBBBBBBB";

    private static final String DEFAULT_TENTOCHUC = "AAAAAAAAAA";
    private static final String UPDATED_TENTOCHUC = "BBBBBBBBBB";

    private static final String DEFAULT_NOIDUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOIDUNG = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private QldtTochucCapRepository qldtTochucCapRepository;

    @Autowired
    private QldtTochucCapMapper qldtTochucCapMapper;

    @Autowired
    private QldtTochucCapService qldtTochucCapService;

    @Autowired
    private QldtTochucCapQueryService qldtTochucCapQueryService;

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

    private MockMvc restQldtTochucCapMockMvc;

    private QldtTochucCap qldtTochucCap;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QldtTochucCapResource qldtTochucCapResource = new QldtTochucCapResource(qldtTochucCapService, qldtTochucCapQueryService);
        this.restQldtTochucCapMockMvc = MockMvcBuilders.standaloneSetup(qldtTochucCapResource)
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
    public static QldtTochucCap createEntity(EntityManager em) {
        QldtTochucCap qldtTochucCap = new QldtTochucCap()
            .matochuc(DEFAULT_MATOCHUC)
            .tentochuc(DEFAULT_TENTOCHUC)
            .noidung(DEFAULT_NOIDUNG)
            .sudung(DEFAULT_SUDUNG);
        return qldtTochucCap;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtTochucCap createUpdatedEntity(EntityManager em) {
        QldtTochucCap qldtTochucCap = new QldtTochucCap()
            .matochuc(UPDATED_MATOCHUC)
            .tentochuc(UPDATED_TENTOCHUC)
            .noidung(UPDATED_NOIDUNG)
            .sudung(UPDATED_SUDUNG);
        return qldtTochucCap;
    }

    @BeforeEach
    public void initTest() {
        qldtTochucCap = createEntity(em);
    }

    @Test
    @Transactional
    public void createQldtTochucCap() throws Exception {
        int databaseSizeBeforeCreate = qldtTochucCapRepository.findAll().size();

        // Create the QldtTochucCap
        QldtTochucCapDTO qldtTochucCapDTO = qldtTochucCapMapper.toDto(qldtTochucCap);
        restQldtTochucCapMockMvc.perform(post("/api/qldt-tochuc-caps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtTochucCapDTO)))
            .andExpect(status().isCreated());

        // Validate the QldtTochucCap in the database
        List<QldtTochucCap> qldtTochucCapList = qldtTochucCapRepository.findAll();
        assertThat(qldtTochucCapList).hasSize(databaseSizeBeforeCreate + 1);
        QldtTochucCap testQldtTochucCap = qldtTochucCapList.get(qldtTochucCapList.size() - 1);
        assertThat(testQldtTochucCap.getMatochuc()).isEqualTo(DEFAULT_MATOCHUC);
        assertThat(testQldtTochucCap.getTentochuc()).isEqualTo(DEFAULT_TENTOCHUC);
        assertThat(testQldtTochucCap.getNoidung()).isEqualTo(DEFAULT_NOIDUNG);
        assertThat(testQldtTochucCap.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createQldtTochucCapWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qldtTochucCapRepository.findAll().size();

        // Create the QldtTochucCap with an existing ID
        qldtTochucCap.setId(1L);
        QldtTochucCapDTO qldtTochucCapDTO = qldtTochucCapMapper.toDto(qldtTochucCap);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQldtTochucCapMockMvc.perform(post("/api/qldt-tochuc-caps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtTochucCapDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtTochucCap in the database
        List<QldtTochucCap> qldtTochucCapList = qldtTochucCapRepository.findAll();
        assertThat(qldtTochucCapList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQldtTochucCaps() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList
        restQldtTochucCapMockMvc.perform(get("/api/qldt-tochuc-caps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtTochucCap.getId().intValue())))
            .andExpect(jsonPath("$.[*].matochuc").value(hasItem(DEFAULT_MATOCHUC)))
            .andExpect(jsonPath("$.[*].tentochuc").value(hasItem(DEFAULT_TENTOCHUC)))
            .andExpect(jsonPath("$.[*].noidung").value(hasItem(DEFAULT_NOIDUNG)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getQldtTochucCap() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get the qldtTochucCap
        restQldtTochucCapMockMvc.perform(get("/api/qldt-tochuc-caps/{id}", qldtTochucCap.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qldtTochucCap.getId().intValue()))
            .andExpect(jsonPath("$.matochuc").value(DEFAULT_MATOCHUC))
            .andExpect(jsonPath("$.tentochuc").value(DEFAULT_TENTOCHUC))
            .andExpect(jsonPath("$.noidung").value(DEFAULT_NOIDUNG))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getQldtTochucCapsByIdFiltering() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        Long id = qldtTochucCap.getId();

        defaultQldtTochucCapShouldBeFound("id.equals=" + id);
        defaultQldtTochucCapShouldNotBeFound("id.notEquals=" + id);

        defaultQldtTochucCapShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultQldtTochucCapShouldNotBeFound("id.greaterThan=" + id);

        defaultQldtTochucCapShouldBeFound("id.lessThanOrEqual=" + id);
        defaultQldtTochucCapShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllQldtTochucCapsByMatochucIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where matochuc equals to DEFAULT_MATOCHUC
        defaultQldtTochucCapShouldBeFound("matochuc.equals=" + DEFAULT_MATOCHUC);

        // Get all the qldtTochucCapList where matochuc equals to UPDATED_MATOCHUC
        defaultQldtTochucCapShouldNotBeFound("matochuc.equals=" + UPDATED_MATOCHUC);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByMatochucIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where matochuc not equals to DEFAULT_MATOCHUC
        defaultQldtTochucCapShouldNotBeFound("matochuc.notEquals=" + DEFAULT_MATOCHUC);

        // Get all the qldtTochucCapList where matochuc not equals to UPDATED_MATOCHUC
        defaultQldtTochucCapShouldBeFound("matochuc.notEquals=" + UPDATED_MATOCHUC);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByMatochucIsInShouldWork() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where matochuc in DEFAULT_MATOCHUC or UPDATED_MATOCHUC
        defaultQldtTochucCapShouldBeFound("matochuc.in=" + DEFAULT_MATOCHUC + "," + UPDATED_MATOCHUC);

        // Get all the qldtTochucCapList where matochuc equals to UPDATED_MATOCHUC
        defaultQldtTochucCapShouldNotBeFound("matochuc.in=" + UPDATED_MATOCHUC);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByMatochucIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where matochuc is not null
        defaultQldtTochucCapShouldBeFound("matochuc.specified=true");

        // Get all the qldtTochucCapList where matochuc is null
        defaultQldtTochucCapShouldNotBeFound("matochuc.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtTochucCapsByMatochucContainsSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where matochuc contains DEFAULT_MATOCHUC
        defaultQldtTochucCapShouldBeFound("matochuc.contains=" + DEFAULT_MATOCHUC);

        // Get all the qldtTochucCapList where matochuc contains UPDATED_MATOCHUC
        defaultQldtTochucCapShouldNotBeFound("matochuc.contains=" + UPDATED_MATOCHUC);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByMatochucNotContainsSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where matochuc does not contain DEFAULT_MATOCHUC
        defaultQldtTochucCapShouldNotBeFound("matochuc.doesNotContain=" + DEFAULT_MATOCHUC);

        // Get all the qldtTochucCapList where matochuc does not contain UPDATED_MATOCHUC
        defaultQldtTochucCapShouldBeFound("matochuc.doesNotContain=" + UPDATED_MATOCHUC);
    }


    @Test
    @Transactional
    public void getAllQldtTochucCapsByTentochucIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where tentochuc equals to DEFAULT_TENTOCHUC
        defaultQldtTochucCapShouldBeFound("tentochuc.equals=" + DEFAULT_TENTOCHUC);

        // Get all the qldtTochucCapList where tentochuc equals to UPDATED_TENTOCHUC
        defaultQldtTochucCapShouldNotBeFound("tentochuc.equals=" + UPDATED_TENTOCHUC);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByTentochucIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where tentochuc not equals to DEFAULT_TENTOCHUC
        defaultQldtTochucCapShouldNotBeFound("tentochuc.notEquals=" + DEFAULT_TENTOCHUC);

        // Get all the qldtTochucCapList where tentochuc not equals to UPDATED_TENTOCHUC
        defaultQldtTochucCapShouldBeFound("tentochuc.notEquals=" + UPDATED_TENTOCHUC);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByTentochucIsInShouldWork() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where tentochuc in DEFAULT_TENTOCHUC or UPDATED_TENTOCHUC
        defaultQldtTochucCapShouldBeFound("tentochuc.in=" + DEFAULT_TENTOCHUC + "," + UPDATED_TENTOCHUC);

        // Get all the qldtTochucCapList where tentochuc equals to UPDATED_TENTOCHUC
        defaultQldtTochucCapShouldNotBeFound("tentochuc.in=" + UPDATED_TENTOCHUC);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByTentochucIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where tentochuc is not null
        defaultQldtTochucCapShouldBeFound("tentochuc.specified=true");

        // Get all the qldtTochucCapList where tentochuc is null
        defaultQldtTochucCapShouldNotBeFound("tentochuc.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtTochucCapsByTentochucContainsSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where tentochuc contains DEFAULT_TENTOCHUC
        defaultQldtTochucCapShouldBeFound("tentochuc.contains=" + DEFAULT_TENTOCHUC);

        // Get all the qldtTochucCapList where tentochuc contains UPDATED_TENTOCHUC
        defaultQldtTochucCapShouldNotBeFound("tentochuc.contains=" + UPDATED_TENTOCHUC);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByTentochucNotContainsSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where tentochuc does not contain DEFAULT_TENTOCHUC
        defaultQldtTochucCapShouldNotBeFound("tentochuc.doesNotContain=" + DEFAULT_TENTOCHUC);

        // Get all the qldtTochucCapList where tentochuc does not contain UPDATED_TENTOCHUC
        defaultQldtTochucCapShouldBeFound("tentochuc.doesNotContain=" + UPDATED_TENTOCHUC);
    }


    @Test
    @Transactional
    public void getAllQldtTochucCapsByNoidungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where noidung equals to DEFAULT_NOIDUNG
        defaultQldtTochucCapShouldBeFound("noidung.equals=" + DEFAULT_NOIDUNG);

        // Get all the qldtTochucCapList where noidung equals to UPDATED_NOIDUNG
        defaultQldtTochucCapShouldNotBeFound("noidung.equals=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByNoidungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where noidung not equals to DEFAULT_NOIDUNG
        defaultQldtTochucCapShouldNotBeFound("noidung.notEquals=" + DEFAULT_NOIDUNG);

        // Get all the qldtTochucCapList where noidung not equals to UPDATED_NOIDUNG
        defaultQldtTochucCapShouldBeFound("noidung.notEquals=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByNoidungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where noidung in DEFAULT_NOIDUNG or UPDATED_NOIDUNG
        defaultQldtTochucCapShouldBeFound("noidung.in=" + DEFAULT_NOIDUNG + "," + UPDATED_NOIDUNG);

        // Get all the qldtTochucCapList where noidung equals to UPDATED_NOIDUNG
        defaultQldtTochucCapShouldNotBeFound("noidung.in=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByNoidungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where noidung is not null
        defaultQldtTochucCapShouldBeFound("noidung.specified=true");

        // Get all the qldtTochucCapList where noidung is null
        defaultQldtTochucCapShouldNotBeFound("noidung.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtTochucCapsByNoidungContainsSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where noidung contains DEFAULT_NOIDUNG
        defaultQldtTochucCapShouldBeFound("noidung.contains=" + DEFAULT_NOIDUNG);

        // Get all the qldtTochucCapList where noidung contains UPDATED_NOIDUNG
        defaultQldtTochucCapShouldNotBeFound("noidung.contains=" + UPDATED_NOIDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsByNoidungNotContainsSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where noidung does not contain DEFAULT_NOIDUNG
        defaultQldtTochucCapShouldNotBeFound("noidung.doesNotContain=" + DEFAULT_NOIDUNG);

        // Get all the qldtTochucCapList where noidung does not contain UPDATED_NOIDUNG
        defaultQldtTochucCapShouldBeFound("noidung.doesNotContain=" + UPDATED_NOIDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtTochucCapsBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where sudung equals to DEFAULT_SUDUNG
        defaultQldtTochucCapShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the qldtTochucCapList where sudung equals to UPDATED_SUDUNG
        defaultQldtTochucCapShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where sudung not equals to DEFAULT_SUDUNG
        defaultQldtTochucCapShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the qldtTochucCapList where sudung not equals to UPDATED_SUDUNG
        defaultQldtTochucCapShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultQldtTochucCapShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the qldtTochucCapList where sudung equals to UPDATED_SUDUNG
        defaultQldtTochucCapShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where sudung is not null
        defaultQldtTochucCapShouldBeFound("sudung.specified=true");

        // Get all the qldtTochucCapList where sudung is null
        defaultQldtTochucCapShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultQldtTochucCapShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtTochucCapList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultQldtTochucCapShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultQldtTochucCapShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtTochucCapList where sudung is less than or equal to SMALLER_SUDUNG
        defaultQldtTochucCapShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where sudung is less than DEFAULT_SUDUNG
        defaultQldtTochucCapShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the qldtTochucCapList where sudung is less than UPDATED_SUDUNG
        defaultQldtTochucCapShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtTochucCapsBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        // Get all the qldtTochucCapList where sudung is greater than DEFAULT_SUDUNG
        defaultQldtTochucCapShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the qldtTochucCapList where sudung is greater than SMALLER_SUDUNG
        defaultQldtTochucCapShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtTochucCapsByDmChungchiIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);
        QldtDmChungchi dmChungchi = QldtDmChungchiResourceIT.createEntity(em);
        em.persist(dmChungchi);
        em.flush();
        qldtTochucCap.addDmChungchi(dmChungchi);
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);
        Long dmChungchiId = dmChungchi.getId();

        // Get all the qldtTochucCapList where dmChungchi equals to dmChungchiId
        defaultQldtTochucCapShouldBeFound("dmChungchiId.equals=" + dmChungchiId);

        // Get all the qldtTochucCapList where dmChungchi equals to dmChungchiId + 1
        defaultQldtTochucCapShouldNotBeFound("dmChungchiId.equals=" + (dmChungchiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQldtTochucCapShouldBeFound(String filter) throws Exception {
        restQldtTochucCapMockMvc.perform(get("/api/qldt-tochuc-caps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtTochucCap.getId().intValue())))
            .andExpect(jsonPath("$.[*].matochuc").value(hasItem(DEFAULT_MATOCHUC)))
            .andExpect(jsonPath("$.[*].tentochuc").value(hasItem(DEFAULT_TENTOCHUC)))
            .andExpect(jsonPath("$.[*].noidung").value(hasItem(DEFAULT_NOIDUNG)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restQldtTochucCapMockMvc.perform(get("/api/qldt-tochuc-caps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQldtTochucCapShouldNotBeFound(String filter) throws Exception {
        restQldtTochucCapMockMvc.perform(get("/api/qldt-tochuc-caps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQldtTochucCapMockMvc.perform(get("/api/qldt-tochuc-caps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingQldtTochucCap() throws Exception {
        // Get the qldtTochucCap
        restQldtTochucCapMockMvc.perform(get("/api/qldt-tochuc-caps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQldtTochucCap() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        int databaseSizeBeforeUpdate = qldtTochucCapRepository.findAll().size();

        // Update the qldtTochucCap
        QldtTochucCap updatedQldtTochucCap = qldtTochucCapRepository.findById(qldtTochucCap.getId()).get();
        // Disconnect from session so that the updates on updatedQldtTochucCap are not directly saved in db
        em.detach(updatedQldtTochucCap);
        updatedQldtTochucCap
            .matochuc(UPDATED_MATOCHUC)
            .tentochuc(UPDATED_TENTOCHUC)
            .noidung(UPDATED_NOIDUNG)
            .sudung(UPDATED_SUDUNG);
        QldtTochucCapDTO qldtTochucCapDTO = qldtTochucCapMapper.toDto(updatedQldtTochucCap);

        restQldtTochucCapMockMvc.perform(put("/api/qldt-tochuc-caps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtTochucCapDTO)))
            .andExpect(status().isOk());

        // Validate the QldtTochucCap in the database
        List<QldtTochucCap> qldtTochucCapList = qldtTochucCapRepository.findAll();
        assertThat(qldtTochucCapList).hasSize(databaseSizeBeforeUpdate);
        QldtTochucCap testQldtTochucCap = qldtTochucCapList.get(qldtTochucCapList.size() - 1);
        assertThat(testQldtTochucCap.getMatochuc()).isEqualTo(UPDATED_MATOCHUC);
        assertThat(testQldtTochucCap.getTentochuc()).isEqualTo(UPDATED_TENTOCHUC);
        assertThat(testQldtTochucCap.getNoidung()).isEqualTo(UPDATED_NOIDUNG);
        assertThat(testQldtTochucCap.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingQldtTochucCap() throws Exception {
        int databaseSizeBeforeUpdate = qldtTochucCapRepository.findAll().size();

        // Create the QldtTochucCap
        QldtTochucCapDTO qldtTochucCapDTO = qldtTochucCapMapper.toDto(qldtTochucCap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQldtTochucCapMockMvc.perform(put("/api/qldt-tochuc-caps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtTochucCapDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtTochucCap in the database
        List<QldtTochucCap> qldtTochucCapList = qldtTochucCapRepository.findAll();
        assertThat(qldtTochucCapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQldtTochucCap() throws Exception {
        // Initialize the database
        qldtTochucCapRepository.saveAndFlush(qldtTochucCap);

        int databaseSizeBeforeDelete = qldtTochucCapRepository.findAll().size();

        // Delete the qldtTochucCap
        restQldtTochucCapMockMvc.perform(delete("/api/qldt-tochuc-caps/{id}", qldtTochucCap.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QldtTochucCap> qldtTochucCapList = qldtTochucCapRepository.findAll();
        assertThat(qldtTochucCapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
