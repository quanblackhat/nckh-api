package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.qldt.QldtQlHocvienCt;
import com.vnptit.vnpthis.domain.qldt.QldtDaotaoCt;
import com.vnptit.vnpthis.repository.qldt.QldtQlHocvienCtRepository;
import com.vnptit.vnpthis.service.qldt.QldtQlHocvienCtService;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienCtDTO;
import com.vnptit.vnpthis.service.mapper.QldtQlHocvienCtMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.web.rest.qldt.QldtQlHocvienCtResource;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienCtCriteria;
import com.vnptit.vnpthis.service.qldt.QldtQlHocvienCtQueryService;

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
 * Integration tests for the {@link QldtQlHocvienCtResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class QldtQlHocvienCtResourceIT {

    private static final Integer DEFAULT_DIEMDANH = 1;
    private static final Integer UPDATED_DIEMDANH = 2;
    private static final Integer SMALLER_DIEMDANH = 1 - 1;

    private static final Integer DEFAULT_DIEMTHI = 1;
    private static final Integer UPDATED_DIEMTHI = 2;
    private static final Integer SMALLER_DIEMTHI = 1 - 1;

    private static final String DEFAULT_DANHGIA = "AAAAAAAAAA";
    private static final String UPDATED_DANHGIA = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private QldtQlHocvienCtRepository qldtQlHocvienCtRepository;

    @Autowired
    private QldtQlHocvienCtMapper qldtQlHocvienCtMapper;

    @Autowired
    private QldtQlHocvienCtService qldtQlHocvienCtService;

    @Autowired
    private QldtQlHocvienCtQueryService qldtQlHocvienCtQueryService;

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

    private MockMvc restQldtQlHocvienCtMockMvc;

    private QldtQlHocvienCt qldtQlHocvienCt;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QldtQlHocvienCtResource qldtQlHocvienCtResource = new QldtQlHocvienCtResource(qldtQlHocvienCtService, qldtQlHocvienCtQueryService);
        this.restQldtQlHocvienCtMockMvc = MockMvcBuilders.standaloneSetup(qldtQlHocvienCtResource)
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
    public static QldtQlHocvienCt createEntity(EntityManager em) {
        QldtQlHocvienCt qldtQlHocvienCt = new QldtQlHocvienCt()
            .diemdanh(DEFAULT_DIEMDANH)
            .diemthi(DEFAULT_DIEMTHI)
            .danhgia(DEFAULT_DANHGIA)
            .sudung(DEFAULT_SUDUNG);
        return qldtQlHocvienCt;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtQlHocvienCt createUpdatedEntity(EntityManager em) {
        QldtQlHocvienCt qldtQlHocvienCt = new QldtQlHocvienCt()
            .diemdanh(UPDATED_DIEMDANH)
            .diemthi(UPDATED_DIEMTHI)
            .danhgia(UPDATED_DANHGIA)
            .sudung(UPDATED_SUDUNG);
        return qldtQlHocvienCt;
    }

    @BeforeEach
    public void initTest() {
        qldtQlHocvienCt = createEntity(em);
    }

    @Test
    @Transactional
    public void createQldtQlHocvienCt() throws Exception {
        int databaseSizeBeforeCreate = qldtQlHocvienCtRepository.findAll().size();

        // Create the QldtQlHocvienCt
        QldtQlHocvienCtDTO qldtQlHocvienCtDTO = qldtQlHocvienCtMapper.toDto(qldtQlHocvienCt);
        restQldtQlHocvienCtMockMvc.perform(post("/api/qldt-ql-hocvien-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtQlHocvienCtDTO)))
            .andExpect(status().isCreated());

        // Validate the QldtQlHocvienCt in the database
        List<QldtQlHocvienCt> qldtQlHocvienCtList = qldtQlHocvienCtRepository.findAll();
        assertThat(qldtQlHocvienCtList).hasSize(databaseSizeBeforeCreate + 1);
        QldtQlHocvienCt testQldtQlHocvienCt = qldtQlHocvienCtList.get(qldtQlHocvienCtList.size() - 1);
        assertThat(testQldtQlHocvienCt.getDiemdanh()).isEqualTo(DEFAULT_DIEMDANH);
        assertThat(testQldtQlHocvienCt.getDiemthi()).isEqualTo(DEFAULT_DIEMTHI);
        assertThat(testQldtQlHocvienCt.getDanhgia()).isEqualTo(DEFAULT_DANHGIA);
        assertThat(testQldtQlHocvienCt.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createQldtQlHocvienCtWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qldtQlHocvienCtRepository.findAll().size();

        // Create the QldtQlHocvienCt with an existing ID
        qldtQlHocvienCt.setId(1L);
        QldtQlHocvienCtDTO qldtQlHocvienCtDTO = qldtQlHocvienCtMapper.toDto(qldtQlHocvienCt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQldtQlHocvienCtMockMvc.perform(post("/api/qldt-ql-hocvien-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtQlHocvienCtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtQlHocvienCt in the database
        List<QldtQlHocvienCt> qldtQlHocvienCtList = qldtQlHocvienCtRepository.findAll();
        assertThat(qldtQlHocvienCtList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocvienCts() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList
        restQldtQlHocvienCtMockMvc.perform(get("/api/qldt-ql-hocvien-cts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtQlHocvienCt.getId().intValue())))
            .andExpect(jsonPath("$.[*].diemdanh").value(hasItem(DEFAULT_DIEMDANH)))
            .andExpect(jsonPath("$.[*].diemthi").value(hasItem(DEFAULT_DIEMTHI)))
            .andExpect(jsonPath("$.[*].danhgia").value(hasItem(DEFAULT_DANHGIA)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getQldtQlHocvienCt() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get the qldtQlHocvienCt
        restQldtQlHocvienCtMockMvc.perform(get("/api/qldt-ql-hocvien-cts/{id}", qldtQlHocvienCt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qldtQlHocvienCt.getId().intValue()))
            .andExpect(jsonPath("$.diemdanh").value(DEFAULT_DIEMDANH))
            .andExpect(jsonPath("$.diemthi").value(DEFAULT_DIEMTHI))
            .andExpect(jsonPath("$.danhgia").value(DEFAULT_DANHGIA))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getQldtQlHocvienCtsByIdFiltering() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        Long id = qldtQlHocvienCt.getId();

        defaultQldtQlHocvienCtShouldBeFound("id.equals=" + id);
        defaultQldtQlHocvienCtShouldNotBeFound("id.notEquals=" + id);

        defaultQldtQlHocvienCtShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultQldtQlHocvienCtShouldNotBeFound("id.greaterThan=" + id);

        defaultQldtQlHocvienCtShouldBeFound("id.lessThanOrEqual=" + id);
        defaultQldtQlHocvienCtShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemdanhIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemdanh equals to DEFAULT_DIEMDANH
        defaultQldtQlHocvienCtShouldBeFound("diemdanh.equals=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienCtList where diemdanh equals to UPDATED_DIEMDANH
        defaultQldtQlHocvienCtShouldNotBeFound("diemdanh.equals=" + UPDATED_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemdanhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemdanh not equals to DEFAULT_DIEMDANH
        defaultQldtQlHocvienCtShouldNotBeFound("diemdanh.notEquals=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienCtList where diemdanh not equals to UPDATED_DIEMDANH
        defaultQldtQlHocvienCtShouldBeFound("diemdanh.notEquals=" + UPDATED_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemdanhIsInShouldWork() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemdanh in DEFAULT_DIEMDANH or UPDATED_DIEMDANH
        defaultQldtQlHocvienCtShouldBeFound("diemdanh.in=" + DEFAULT_DIEMDANH + "," + UPDATED_DIEMDANH);

        // Get all the qldtQlHocvienCtList where diemdanh equals to UPDATED_DIEMDANH
        defaultQldtQlHocvienCtShouldNotBeFound("diemdanh.in=" + UPDATED_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemdanhIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemdanh is not null
        defaultQldtQlHocvienCtShouldBeFound("diemdanh.specified=true");

        // Get all the qldtQlHocvienCtList where diemdanh is null
        defaultQldtQlHocvienCtShouldNotBeFound("diemdanh.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemdanhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemdanh is greater than or equal to DEFAULT_DIEMDANH
        defaultQldtQlHocvienCtShouldBeFound("diemdanh.greaterThanOrEqual=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienCtList where diemdanh is greater than or equal to UPDATED_DIEMDANH
        defaultQldtQlHocvienCtShouldNotBeFound("diemdanh.greaterThanOrEqual=" + UPDATED_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemdanhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemdanh is less than or equal to DEFAULT_DIEMDANH
        defaultQldtQlHocvienCtShouldBeFound("diemdanh.lessThanOrEqual=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienCtList where diemdanh is less than or equal to SMALLER_DIEMDANH
        defaultQldtQlHocvienCtShouldNotBeFound("diemdanh.lessThanOrEqual=" + SMALLER_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemdanhIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemdanh is less than DEFAULT_DIEMDANH
        defaultQldtQlHocvienCtShouldNotBeFound("diemdanh.lessThan=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienCtList where diemdanh is less than UPDATED_DIEMDANH
        defaultQldtQlHocvienCtShouldBeFound("diemdanh.lessThan=" + UPDATED_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemdanhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemdanh is greater than DEFAULT_DIEMDANH
        defaultQldtQlHocvienCtShouldNotBeFound("diemdanh.greaterThan=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienCtList where diemdanh is greater than SMALLER_DIEMDANH
        defaultQldtQlHocvienCtShouldBeFound("diemdanh.greaterThan=" + SMALLER_DIEMDANH);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemthiIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemthi equals to DEFAULT_DIEMTHI
        defaultQldtQlHocvienCtShouldBeFound("diemthi.equals=" + DEFAULT_DIEMTHI);

        // Get all the qldtQlHocvienCtList where diemthi equals to UPDATED_DIEMTHI
        defaultQldtQlHocvienCtShouldNotBeFound("diemthi.equals=" + UPDATED_DIEMTHI);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemthiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemthi not equals to DEFAULT_DIEMTHI
        defaultQldtQlHocvienCtShouldNotBeFound("diemthi.notEquals=" + DEFAULT_DIEMTHI);

        // Get all the qldtQlHocvienCtList where diemthi not equals to UPDATED_DIEMTHI
        defaultQldtQlHocvienCtShouldBeFound("diemthi.notEquals=" + UPDATED_DIEMTHI);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemthiIsInShouldWork() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemthi in DEFAULT_DIEMTHI or UPDATED_DIEMTHI
        defaultQldtQlHocvienCtShouldBeFound("diemthi.in=" + DEFAULT_DIEMTHI + "," + UPDATED_DIEMTHI);

        // Get all the qldtQlHocvienCtList where diemthi equals to UPDATED_DIEMTHI
        defaultQldtQlHocvienCtShouldNotBeFound("diemthi.in=" + UPDATED_DIEMTHI);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemthiIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemthi is not null
        defaultQldtQlHocvienCtShouldBeFound("diemthi.specified=true");

        // Get all the qldtQlHocvienCtList where diemthi is null
        defaultQldtQlHocvienCtShouldNotBeFound("diemthi.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemthiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemthi is greater than or equal to DEFAULT_DIEMTHI
        defaultQldtQlHocvienCtShouldBeFound("diemthi.greaterThanOrEqual=" + DEFAULT_DIEMTHI);

        // Get all the qldtQlHocvienCtList where diemthi is greater than or equal to UPDATED_DIEMTHI
        defaultQldtQlHocvienCtShouldNotBeFound("diemthi.greaterThanOrEqual=" + UPDATED_DIEMTHI);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemthiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemthi is less than or equal to DEFAULT_DIEMTHI
        defaultQldtQlHocvienCtShouldBeFound("diemthi.lessThanOrEqual=" + DEFAULT_DIEMTHI);

        // Get all the qldtQlHocvienCtList where diemthi is less than or equal to SMALLER_DIEMTHI
        defaultQldtQlHocvienCtShouldNotBeFound("diemthi.lessThanOrEqual=" + SMALLER_DIEMTHI);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemthiIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemthi is less than DEFAULT_DIEMTHI
        defaultQldtQlHocvienCtShouldNotBeFound("diemthi.lessThan=" + DEFAULT_DIEMTHI);

        // Get all the qldtQlHocvienCtList where diemthi is less than UPDATED_DIEMTHI
        defaultQldtQlHocvienCtShouldBeFound("diemthi.lessThan=" + UPDATED_DIEMTHI);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDiemthiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where diemthi is greater than DEFAULT_DIEMTHI
        defaultQldtQlHocvienCtShouldNotBeFound("diemthi.greaterThan=" + DEFAULT_DIEMTHI);

        // Get all the qldtQlHocvienCtList where diemthi is greater than SMALLER_DIEMTHI
        defaultQldtQlHocvienCtShouldBeFound("diemthi.greaterThan=" + SMALLER_DIEMTHI);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDanhgiaIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where danhgia equals to DEFAULT_DANHGIA
        defaultQldtQlHocvienCtShouldBeFound("danhgia.equals=" + DEFAULT_DANHGIA);

        // Get all the qldtQlHocvienCtList where danhgia equals to UPDATED_DANHGIA
        defaultQldtQlHocvienCtShouldNotBeFound("danhgia.equals=" + UPDATED_DANHGIA);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDanhgiaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where danhgia not equals to DEFAULT_DANHGIA
        defaultQldtQlHocvienCtShouldNotBeFound("danhgia.notEquals=" + DEFAULT_DANHGIA);

        // Get all the qldtQlHocvienCtList where danhgia not equals to UPDATED_DANHGIA
        defaultQldtQlHocvienCtShouldBeFound("danhgia.notEquals=" + UPDATED_DANHGIA);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDanhgiaIsInShouldWork() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where danhgia in DEFAULT_DANHGIA or UPDATED_DANHGIA
        defaultQldtQlHocvienCtShouldBeFound("danhgia.in=" + DEFAULT_DANHGIA + "," + UPDATED_DANHGIA);

        // Get all the qldtQlHocvienCtList where danhgia equals to UPDATED_DANHGIA
        defaultQldtQlHocvienCtShouldNotBeFound("danhgia.in=" + UPDATED_DANHGIA);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDanhgiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where danhgia is not null
        defaultQldtQlHocvienCtShouldBeFound("danhgia.specified=true");

        // Get all the qldtQlHocvienCtList where danhgia is null
        defaultQldtQlHocvienCtShouldNotBeFound("danhgia.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDanhgiaContainsSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where danhgia contains DEFAULT_DANHGIA
        defaultQldtQlHocvienCtShouldBeFound("danhgia.contains=" + DEFAULT_DANHGIA);

        // Get all the qldtQlHocvienCtList where danhgia contains UPDATED_DANHGIA
        defaultQldtQlHocvienCtShouldNotBeFound("danhgia.contains=" + UPDATED_DANHGIA);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByDanhgiaNotContainsSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where danhgia does not contain DEFAULT_DANHGIA
        defaultQldtQlHocvienCtShouldNotBeFound("danhgia.doesNotContain=" + DEFAULT_DANHGIA);

        // Get all the qldtQlHocvienCtList where danhgia does not contain UPDATED_DANHGIA
        defaultQldtQlHocvienCtShouldBeFound("danhgia.doesNotContain=" + UPDATED_DANHGIA);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where sudung equals to DEFAULT_SUDUNG
        defaultQldtQlHocvienCtShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienCtList where sudung equals to UPDATED_SUDUNG
        defaultQldtQlHocvienCtShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where sudung not equals to DEFAULT_SUDUNG
        defaultQldtQlHocvienCtShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienCtList where sudung not equals to UPDATED_SUDUNG
        defaultQldtQlHocvienCtShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultQldtQlHocvienCtShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the qldtQlHocvienCtList where sudung equals to UPDATED_SUDUNG
        defaultQldtQlHocvienCtShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where sudung is not null
        defaultQldtQlHocvienCtShouldBeFound("sudung.specified=true");

        // Get all the qldtQlHocvienCtList where sudung is null
        defaultQldtQlHocvienCtShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultQldtQlHocvienCtShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienCtList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultQldtQlHocvienCtShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultQldtQlHocvienCtShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienCtList where sudung is less than or equal to SMALLER_SUDUNG
        defaultQldtQlHocvienCtShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where sudung is less than DEFAULT_SUDUNG
        defaultQldtQlHocvienCtShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienCtList where sudung is less than UPDATED_SUDUNG
        defaultQldtQlHocvienCtShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        // Get all the qldtQlHocvienCtList where sudung is greater than DEFAULT_SUDUNG
        defaultQldtQlHocvienCtShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienCtList where sudung is greater than SMALLER_SUDUNG
        defaultQldtQlHocvienCtShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocvienCtsByQldtDaotaoCtIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);
        QldtDaotaoCt qldtDaotaoCt = QldtDaotaoCtResourceIT.createEntity(em);
        em.persist(qldtDaotaoCt);
        em.flush();
        qldtQlHocvienCt.setQldtDaotaoCt(qldtDaotaoCt);
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);
        Long qldtDaotaoCtId = qldtDaotaoCt.getId();

        // Get all the qldtQlHocvienCtList where qldtDaotaoCt equals to qldtDaotaoCtId
        defaultQldtQlHocvienCtShouldBeFound("qldtDaotaoCtId.equals=" + qldtDaotaoCtId);

        // Get all the qldtQlHocvienCtList where qldtDaotaoCt equals to qldtDaotaoCtId + 1
        defaultQldtQlHocvienCtShouldNotBeFound("qldtDaotaoCtId.equals=" + (qldtDaotaoCtId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQldtQlHocvienCtShouldBeFound(String filter) throws Exception {
        restQldtQlHocvienCtMockMvc.perform(get("/api/qldt-ql-hocvien-cts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtQlHocvienCt.getId().intValue())))
            .andExpect(jsonPath("$.[*].diemdanh").value(hasItem(DEFAULT_DIEMDANH)))
            .andExpect(jsonPath("$.[*].diemthi").value(hasItem(DEFAULT_DIEMTHI)))
            .andExpect(jsonPath("$.[*].danhgia").value(hasItem(DEFAULT_DANHGIA)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restQldtQlHocvienCtMockMvc.perform(get("/api/qldt-ql-hocvien-cts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQldtQlHocvienCtShouldNotBeFound(String filter) throws Exception {
        restQldtQlHocvienCtMockMvc.perform(get("/api/qldt-ql-hocvien-cts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQldtQlHocvienCtMockMvc.perform(get("/api/qldt-ql-hocvien-cts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingQldtQlHocvienCt() throws Exception {
        // Get the qldtQlHocvienCt
        restQldtQlHocvienCtMockMvc.perform(get("/api/qldt-ql-hocvien-cts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQldtQlHocvienCt() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        int databaseSizeBeforeUpdate = qldtQlHocvienCtRepository.findAll().size();

        // Update the qldtQlHocvienCt
        QldtQlHocvienCt updatedQldtQlHocvienCt = qldtQlHocvienCtRepository.findById(qldtQlHocvienCt.getId()).get();
        // Disconnect from session so that the updates on updatedQldtQlHocvienCt are not directly saved in db
        em.detach(updatedQldtQlHocvienCt);
        updatedQldtQlHocvienCt
            .diemdanh(UPDATED_DIEMDANH)
            .diemthi(UPDATED_DIEMTHI)
            .danhgia(UPDATED_DANHGIA)
            .sudung(UPDATED_SUDUNG);
        QldtQlHocvienCtDTO qldtQlHocvienCtDTO = qldtQlHocvienCtMapper.toDto(updatedQldtQlHocvienCt);

        restQldtQlHocvienCtMockMvc.perform(put("/api/qldt-ql-hocvien-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtQlHocvienCtDTO)))
            .andExpect(status().isOk());

        // Validate the QldtQlHocvienCt in the database
        List<QldtQlHocvienCt> qldtQlHocvienCtList = qldtQlHocvienCtRepository.findAll();
        assertThat(qldtQlHocvienCtList).hasSize(databaseSizeBeforeUpdate);
        QldtQlHocvienCt testQldtQlHocvienCt = qldtQlHocvienCtList.get(qldtQlHocvienCtList.size() - 1);
        assertThat(testQldtQlHocvienCt.getDiemdanh()).isEqualTo(UPDATED_DIEMDANH);
        assertThat(testQldtQlHocvienCt.getDiemthi()).isEqualTo(UPDATED_DIEMTHI);
        assertThat(testQldtQlHocvienCt.getDanhgia()).isEqualTo(UPDATED_DANHGIA);
        assertThat(testQldtQlHocvienCt.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingQldtQlHocvienCt() throws Exception {
        int databaseSizeBeforeUpdate = qldtQlHocvienCtRepository.findAll().size();

        // Create the QldtQlHocvienCt
        QldtQlHocvienCtDTO qldtQlHocvienCtDTO = qldtQlHocvienCtMapper.toDto(qldtQlHocvienCt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQldtQlHocvienCtMockMvc.perform(put("/api/qldt-ql-hocvien-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtQlHocvienCtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtQlHocvienCt in the database
        List<QldtQlHocvienCt> qldtQlHocvienCtList = qldtQlHocvienCtRepository.findAll();
        assertThat(qldtQlHocvienCtList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQldtQlHocvienCt() throws Exception {
        // Initialize the database
        qldtQlHocvienCtRepository.saveAndFlush(qldtQlHocvienCt);

        int databaseSizeBeforeDelete = qldtQlHocvienCtRepository.findAll().size();

        // Delete the qldtQlHocvienCt
        restQldtQlHocvienCtMockMvc.perform(delete("/api/qldt-ql-hocvien-cts/{id}", qldtQlHocvienCt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QldtQlHocvienCt> qldtQlHocvienCtList = qldtQlHocvienCtRepository.findAll();
        assertThat(qldtQlHocvienCtList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
