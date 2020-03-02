package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.qldt.QldtQlHocvien;
import com.vnptit.vnpthis.domain.qldt.QldtDaotao;
import com.vnptit.vnpthis.repository.qldt.QldtQlHocvienRepository;
import com.vnptit.vnpthis.service.qldt.QldtQlHocvienService;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienDTO;
import com.vnptit.vnpthis.service.mapper.QldtQlHocvienMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.web.rest.qldt.QldtQlHocvienResource;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienCriteria;
import com.vnptit.vnpthis.service.qldt.QldtQlHocvienQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.vnptit.vnpthis.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link QldtQlHocvienResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class QldtQlHocvienResourceIT {

    private static final Integer DEFAULT_DIEMDANH = 1;
    private static final Integer UPDATED_DIEMDANH = 2;
    private static final Integer SMALLER_DIEMDANH = 1 - 1;

    private static final Integer DEFAULT_DIEM = 1;
    private static final Integer UPDATED_DIEM = 2;
    private static final Integer SMALLER_DIEM = 1 - 1;

    private static final String DEFAULT_DANHGIA = "AAAAAAAAAA";
    private static final String UPDATED_DANHGIA = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    private static final Integer DEFAULT_TRANGTHAITHANHTOAN = 1;
    private static final Integer UPDATED_TRANGTHAITHANHTOAN = 2;
    private static final Integer SMALLER_TRANGTHAITHANHTOAN = 1 - 1;

    private static final LocalDate DEFAULT_NGAYTHANHTOAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYTHANHTOAN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYTHANHTOAN = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_MATHANHTOAN = "AAAAAAAAAA";
    private static final String UPDATED_MATHANHTOAN = "BBBBBBBBBB";

    @Autowired
    private QldtQlHocvienRepository qldtQlHocvienRepository;

    @Autowired
    private QldtQlHocvienMapper qldtQlHocvienMapper;

    @Autowired
    private QldtQlHocvienService qldtQlHocvienService;

    @Autowired
    private QldtQlHocvienQueryService qldtQlHocvienQueryService;

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

    private MockMvc restQldtQlHocvienMockMvc;

    private QldtQlHocvien qldtQlHocvien;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QldtQlHocvienResource qldtQlHocvienResource = new QldtQlHocvienResource(qldtQlHocvienService, qldtQlHocvienQueryService);
        this.restQldtQlHocvienMockMvc = MockMvcBuilders.standaloneSetup(qldtQlHocvienResource)
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
    public static QldtQlHocvien createEntity(EntityManager em) {
        QldtQlHocvien qldtQlHocvien = new QldtQlHocvien()
            .diemdanh(DEFAULT_DIEMDANH)
            .diem(DEFAULT_DIEM)
            .danhgia(DEFAULT_DANHGIA)
            .sudung(DEFAULT_SUDUNG)
            .trangthaithanhtoan(DEFAULT_TRANGTHAITHANHTOAN)
            .ngaythanhtoan(DEFAULT_NGAYTHANHTOAN)
            .mathanhtoan(DEFAULT_MATHANHTOAN);
        return qldtQlHocvien;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtQlHocvien createUpdatedEntity(EntityManager em) {
        QldtQlHocvien qldtQlHocvien = new QldtQlHocvien()
            .diemdanh(UPDATED_DIEMDANH)
            .diem(UPDATED_DIEM)
            .danhgia(UPDATED_DANHGIA)
            .sudung(UPDATED_SUDUNG)
            .trangthaithanhtoan(UPDATED_TRANGTHAITHANHTOAN)
            .ngaythanhtoan(UPDATED_NGAYTHANHTOAN)
            .mathanhtoan(UPDATED_MATHANHTOAN);
        return qldtQlHocvien;
    }

    @BeforeEach
    public void initTest() {
        qldtQlHocvien = createEntity(em);
    }

    @Test
    @Transactional
    public void createQldtQlHocvien() throws Exception {
        int databaseSizeBeforeCreate = qldtQlHocvienRepository.findAll().size();

        // Create the QldtQlHocvien
        QldtQlHocvienDTO qldtQlHocvienDTO = qldtQlHocvienMapper.toDto(qldtQlHocvien);
        restQldtQlHocvienMockMvc.perform(post("/api/qldt-ql-hocviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtQlHocvienDTO)))
            .andExpect(status().isCreated());

        // Validate the QldtQlHocvien in the database
        List<QldtQlHocvien> qldtQlHocvienList = qldtQlHocvienRepository.findAll();
        assertThat(qldtQlHocvienList).hasSize(databaseSizeBeforeCreate + 1);
        QldtQlHocvien testQldtQlHocvien = qldtQlHocvienList.get(qldtQlHocvienList.size() - 1);
        assertThat(testQldtQlHocvien.getDiemdanh()).isEqualTo(DEFAULT_DIEMDANH);
        assertThat(testQldtQlHocvien.getDiem()).isEqualTo(DEFAULT_DIEM);
        assertThat(testQldtQlHocvien.getDanhgia()).isEqualTo(DEFAULT_DANHGIA);
        assertThat(testQldtQlHocvien.getSudung()).isEqualTo(DEFAULT_SUDUNG);
        assertThat(testQldtQlHocvien.getTrangthaithanhtoan()).isEqualTo(DEFAULT_TRANGTHAITHANHTOAN);
        assertThat(testQldtQlHocvien.getNgaythanhtoan()).isEqualTo(DEFAULT_NGAYTHANHTOAN);
        assertThat(testQldtQlHocvien.getMathanhtoan()).isEqualTo(DEFAULT_MATHANHTOAN);
    }

    @Test
    @Transactional
    public void createQldtQlHocvienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qldtQlHocvienRepository.findAll().size();

        // Create the QldtQlHocvien with an existing ID
        qldtQlHocvien.setId(1L);
        QldtQlHocvienDTO qldtQlHocvienDTO = qldtQlHocvienMapper.toDto(qldtQlHocvien);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQldtQlHocvienMockMvc.perform(post("/api/qldt-ql-hocviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtQlHocvienDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtQlHocvien in the database
        List<QldtQlHocvien> qldtQlHocvienList = qldtQlHocvienRepository.findAll();
        assertThat(qldtQlHocvienList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocviens() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList
        restQldtQlHocvienMockMvc.perform(get("/api/qldt-ql-hocviens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtQlHocvien.getId().intValue())))
            .andExpect(jsonPath("$.[*].diemdanh").value(hasItem(DEFAULT_DIEMDANH)))
            .andExpect(jsonPath("$.[*].diem").value(hasItem(DEFAULT_DIEM)))
            .andExpect(jsonPath("$.[*].danhgia").value(hasItem(DEFAULT_DANHGIA)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)))
            .andExpect(jsonPath("$.[*].trangthaithanhtoan").value(hasItem(DEFAULT_TRANGTHAITHANHTOAN)))
            .andExpect(jsonPath("$.[*].ngaythanhtoan").value(hasItem(DEFAULT_NGAYTHANHTOAN.toString())))
            .andExpect(jsonPath("$.[*].mathanhtoan").value(hasItem(DEFAULT_MATHANHTOAN)));
    }
    
    @Test
    @Transactional
    public void getQldtQlHocvien() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get the qldtQlHocvien
        restQldtQlHocvienMockMvc.perform(get("/api/qldt-ql-hocviens/{id}", qldtQlHocvien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qldtQlHocvien.getId().intValue()))
            .andExpect(jsonPath("$.diemdanh").value(DEFAULT_DIEMDANH))
            .andExpect(jsonPath("$.diem").value(DEFAULT_DIEM))
            .andExpect(jsonPath("$.danhgia").value(DEFAULT_DANHGIA))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG))
            .andExpect(jsonPath("$.trangthaithanhtoan").value(DEFAULT_TRANGTHAITHANHTOAN))
            .andExpect(jsonPath("$.ngaythanhtoan").value(DEFAULT_NGAYTHANHTOAN.toString()))
            .andExpect(jsonPath("$.mathanhtoan").value(DEFAULT_MATHANHTOAN));
    }


    @Test
    @Transactional
    public void getQldtQlHocviensByIdFiltering() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        Long id = qldtQlHocvien.getId();

        defaultQldtQlHocvienShouldBeFound("id.equals=" + id);
        defaultQldtQlHocvienShouldNotBeFound("id.notEquals=" + id);

        defaultQldtQlHocvienShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultQldtQlHocvienShouldNotBeFound("id.greaterThan=" + id);

        defaultQldtQlHocvienShouldBeFound("id.lessThanOrEqual=" + id);
        defaultQldtQlHocvienShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemdanhIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diemdanh equals to DEFAULT_DIEMDANH
        defaultQldtQlHocvienShouldBeFound("diemdanh.equals=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienList where diemdanh equals to UPDATED_DIEMDANH
        defaultQldtQlHocvienShouldNotBeFound("diemdanh.equals=" + UPDATED_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemdanhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diemdanh not equals to DEFAULT_DIEMDANH
        defaultQldtQlHocvienShouldNotBeFound("diemdanh.notEquals=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienList where diemdanh not equals to UPDATED_DIEMDANH
        defaultQldtQlHocvienShouldBeFound("diemdanh.notEquals=" + UPDATED_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemdanhIsInShouldWork() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diemdanh in DEFAULT_DIEMDANH or UPDATED_DIEMDANH
        defaultQldtQlHocvienShouldBeFound("diemdanh.in=" + DEFAULT_DIEMDANH + "," + UPDATED_DIEMDANH);

        // Get all the qldtQlHocvienList where diemdanh equals to UPDATED_DIEMDANH
        defaultQldtQlHocvienShouldNotBeFound("diemdanh.in=" + UPDATED_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemdanhIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diemdanh is not null
        defaultQldtQlHocvienShouldBeFound("diemdanh.specified=true");

        // Get all the qldtQlHocvienList where diemdanh is null
        defaultQldtQlHocvienShouldNotBeFound("diemdanh.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemdanhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diemdanh is greater than or equal to DEFAULT_DIEMDANH
        defaultQldtQlHocvienShouldBeFound("diemdanh.greaterThanOrEqual=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienList where diemdanh is greater than or equal to UPDATED_DIEMDANH
        defaultQldtQlHocvienShouldNotBeFound("diemdanh.greaterThanOrEqual=" + UPDATED_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemdanhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diemdanh is less than or equal to DEFAULT_DIEMDANH
        defaultQldtQlHocvienShouldBeFound("diemdanh.lessThanOrEqual=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienList where diemdanh is less than or equal to SMALLER_DIEMDANH
        defaultQldtQlHocvienShouldNotBeFound("diemdanh.lessThanOrEqual=" + SMALLER_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemdanhIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diemdanh is less than DEFAULT_DIEMDANH
        defaultQldtQlHocvienShouldNotBeFound("diemdanh.lessThan=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienList where diemdanh is less than UPDATED_DIEMDANH
        defaultQldtQlHocvienShouldBeFound("diemdanh.lessThan=" + UPDATED_DIEMDANH);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemdanhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diemdanh is greater than DEFAULT_DIEMDANH
        defaultQldtQlHocvienShouldNotBeFound("diemdanh.greaterThan=" + DEFAULT_DIEMDANH);

        // Get all the qldtQlHocvienList where diemdanh is greater than SMALLER_DIEMDANH
        defaultQldtQlHocvienShouldBeFound("diemdanh.greaterThan=" + SMALLER_DIEMDANH);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diem equals to DEFAULT_DIEM
        defaultQldtQlHocvienShouldBeFound("diem.equals=" + DEFAULT_DIEM);

        // Get all the qldtQlHocvienList where diem equals to UPDATED_DIEM
        defaultQldtQlHocvienShouldNotBeFound("diem.equals=" + UPDATED_DIEM);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diem not equals to DEFAULT_DIEM
        defaultQldtQlHocvienShouldNotBeFound("diem.notEquals=" + DEFAULT_DIEM);

        // Get all the qldtQlHocvienList where diem not equals to UPDATED_DIEM
        defaultQldtQlHocvienShouldBeFound("diem.notEquals=" + UPDATED_DIEM);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemIsInShouldWork() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diem in DEFAULT_DIEM or UPDATED_DIEM
        defaultQldtQlHocvienShouldBeFound("diem.in=" + DEFAULT_DIEM + "," + UPDATED_DIEM);

        // Get all the qldtQlHocvienList where diem equals to UPDATED_DIEM
        defaultQldtQlHocvienShouldNotBeFound("diem.in=" + UPDATED_DIEM);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diem is not null
        defaultQldtQlHocvienShouldBeFound("diem.specified=true");

        // Get all the qldtQlHocvienList where diem is null
        defaultQldtQlHocvienShouldNotBeFound("diem.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diem is greater than or equal to DEFAULT_DIEM
        defaultQldtQlHocvienShouldBeFound("diem.greaterThanOrEqual=" + DEFAULT_DIEM);

        // Get all the qldtQlHocvienList where diem is greater than or equal to UPDATED_DIEM
        defaultQldtQlHocvienShouldNotBeFound("diem.greaterThanOrEqual=" + UPDATED_DIEM);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diem is less than or equal to DEFAULT_DIEM
        defaultQldtQlHocvienShouldBeFound("diem.lessThanOrEqual=" + DEFAULT_DIEM);

        // Get all the qldtQlHocvienList where diem is less than or equal to SMALLER_DIEM
        defaultQldtQlHocvienShouldNotBeFound("diem.lessThanOrEqual=" + SMALLER_DIEM);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diem is less than DEFAULT_DIEM
        defaultQldtQlHocvienShouldNotBeFound("diem.lessThan=" + DEFAULT_DIEM);

        // Get all the qldtQlHocvienList where diem is less than UPDATED_DIEM
        defaultQldtQlHocvienShouldBeFound("diem.lessThan=" + UPDATED_DIEM);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDiemIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where diem is greater than DEFAULT_DIEM
        defaultQldtQlHocvienShouldNotBeFound("diem.greaterThan=" + DEFAULT_DIEM);

        // Get all the qldtQlHocvienList where diem is greater than SMALLER_DIEM
        defaultQldtQlHocvienShouldBeFound("diem.greaterThan=" + SMALLER_DIEM);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocviensByDanhgiaIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where danhgia equals to DEFAULT_DANHGIA
        defaultQldtQlHocvienShouldBeFound("danhgia.equals=" + DEFAULT_DANHGIA);

        // Get all the qldtQlHocvienList where danhgia equals to UPDATED_DANHGIA
        defaultQldtQlHocvienShouldNotBeFound("danhgia.equals=" + UPDATED_DANHGIA);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDanhgiaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where danhgia not equals to DEFAULT_DANHGIA
        defaultQldtQlHocvienShouldNotBeFound("danhgia.notEquals=" + DEFAULT_DANHGIA);

        // Get all the qldtQlHocvienList where danhgia not equals to UPDATED_DANHGIA
        defaultQldtQlHocvienShouldBeFound("danhgia.notEquals=" + UPDATED_DANHGIA);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDanhgiaIsInShouldWork() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where danhgia in DEFAULT_DANHGIA or UPDATED_DANHGIA
        defaultQldtQlHocvienShouldBeFound("danhgia.in=" + DEFAULT_DANHGIA + "," + UPDATED_DANHGIA);

        // Get all the qldtQlHocvienList where danhgia equals to UPDATED_DANHGIA
        defaultQldtQlHocvienShouldNotBeFound("danhgia.in=" + UPDATED_DANHGIA);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDanhgiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where danhgia is not null
        defaultQldtQlHocvienShouldBeFound("danhgia.specified=true");

        // Get all the qldtQlHocvienList where danhgia is null
        defaultQldtQlHocvienShouldNotBeFound("danhgia.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtQlHocviensByDanhgiaContainsSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where danhgia contains DEFAULT_DANHGIA
        defaultQldtQlHocvienShouldBeFound("danhgia.contains=" + DEFAULT_DANHGIA);

        // Get all the qldtQlHocvienList where danhgia contains UPDATED_DANHGIA
        defaultQldtQlHocvienShouldNotBeFound("danhgia.contains=" + UPDATED_DANHGIA);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByDanhgiaNotContainsSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where danhgia does not contain DEFAULT_DANHGIA
        defaultQldtQlHocvienShouldNotBeFound("danhgia.doesNotContain=" + DEFAULT_DANHGIA);

        // Get all the qldtQlHocvienList where danhgia does not contain UPDATED_DANHGIA
        defaultQldtQlHocvienShouldBeFound("danhgia.doesNotContain=" + UPDATED_DANHGIA);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocviensBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where sudung equals to DEFAULT_SUDUNG
        defaultQldtQlHocvienShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienList where sudung equals to UPDATED_SUDUNG
        defaultQldtQlHocvienShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where sudung not equals to DEFAULT_SUDUNG
        defaultQldtQlHocvienShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienList where sudung not equals to UPDATED_SUDUNG
        defaultQldtQlHocvienShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultQldtQlHocvienShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the qldtQlHocvienList where sudung equals to UPDATED_SUDUNG
        defaultQldtQlHocvienShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where sudung is not null
        defaultQldtQlHocvienShouldBeFound("sudung.specified=true");

        // Get all the qldtQlHocvienList where sudung is null
        defaultQldtQlHocvienShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultQldtQlHocvienShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultQldtQlHocvienShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultQldtQlHocvienShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienList where sudung is less than or equal to SMALLER_SUDUNG
        defaultQldtQlHocvienShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where sudung is less than DEFAULT_SUDUNG
        defaultQldtQlHocvienShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienList where sudung is less than UPDATED_SUDUNG
        defaultQldtQlHocvienShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where sudung is greater than DEFAULT_SUDUNG
        defaultQldtQlHocvienShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the qldtQlHocvienList where sudung is greater than SMALLER_SUDUNG
        defaultQldtQlHocvienShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocviensByTrangthaithanhtoanIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where trangthaithanhtoan equals to DEFAULT_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldBeFound("trangthaithanhtoan.equals=" + DEFAULT_TRANGTHAITHANHTOAN);

        // Get all the qldtQlHocvienList where trangthaithanhtoan equals to UPDATED_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("trangthaithanhtoan.equals=" + UPDATED_TRANGTHAITHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByTrangthaithanhtoanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where trangthaithanhtoan not equals to DEFAULT_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("trangthaithanhtoan.notEquals=" + DEFAULT_TRANGTHAITHANHTOAN);

        // Get all the qldtQlHocvienList where trangthaithanhtoan not equals to UPDATED_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldBeFound("trangthaithanhtoan.notEquals=" + UPDATED_TRANGTHAITHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByTrangthaithanhtoanIsInShouldWork() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where trangthaithanhtoan in DEFAULT_TRANGTHAITHANHTOAN or UPDATED_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldBeFound("trangthaithanhtoan.in=" + DEFAULT_TRANGTHAITHANHTOAN + "," + UPDATED_TRANGTHAITHANHTOAN);

        // Get all the qldtQlHocvienList where trangthaithanhtoan equals to UPDATED_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("trangthaithanhtoan.in=" + UPDATED_TRANGTHAITHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByTrangthaithanhtoanIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where trangthaithanhtoan is not null
        defaultQldtQlHocvienShouldBeFound("trangthaithanhtoan.specified=true");

        // Get all the qldtQlHocvienList where trangthaithanhtoan is null
        defaultQldtQlHocvienShouldNotBeFound("trangthaithanhtoan.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByTrangthaithanhtoanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where trangthaithanhtoan is greater than or equal to DEFAULT_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldBeFound("trangthaithanhtoan.greaterThanOrEqual=" + DEFAULT_TRANGTHAITHANHTOAN);

        // Get all the qldtQlHocvienList where trangthaithanhtoan is greater than or equal to UPDATED_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("trangthaithanhtoan.greaterThanOrEqual=" + UPDATED_TRANGTHAITHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByTrangthaithanhtoanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where trangthaithanhtoan is less than or equal to DEFAULT_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldBeFound("trangthaithanhtoan.lessThanOrEqual=" + DEFAULT_TRANGTHAITHANHTOAN);

        // Get all the qldtQlHocvienList where trangthaithanhtoan is less than or equal to SMALLER_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("trangthaithanhtoan.lessThanOrEqual=" + SMALLER_TRANGTHAITHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByTrangthaithanhtoanIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where trangthaithanhtoan is less than DEFAULT_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("trangthaithanhtoan.lessThan=" + DEFAULT_TRANGTHAITHANHTOAN);

        // Get all the qldtQlHocvienList where trangthaithanhtoan is less than UPDATED_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldBeFound("trangthaithanhtoan.lessThan=" + UPDATED_TRANGTHAITHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByTrangthaithanhtoanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where trangthaithanhtoan is greater than DEFAULT_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("trangthaithanhtoan.greaterThan=" + DEFAULT_TRANGTHAITHANHTOAN);

        // Get all the qldtQlHocvienList where trangthaithanhtoan is greater than SMALLER_TRANGTHAITHANHTOAN
        defaultQldtQlHocvienShouldBeFound("trangthaithanhtoan.greaterThan=" + SMALLER_TRANGTHAITHANHTOAN);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocviensByNgaythanhtoanIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where ngaythanhtoan equals to DEFAULT_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldBeFound("ngaythanhtoan.equals=" + DEFAULT_NGAYTHANHTOAN);

        // Get all the qldtQlHocvienList where ngaythanhtoan equals to UPDATED_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("ngaythanhtoan.equals=" + UPDATED_NGAYTHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByNgaythanhtoanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where ngaythanhtoan not equals to DEFAULT_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("ngaythanhtoan.notEquals=" + DEFAULT_NGAYTHANHTOAN);

        // Get all the qldtQlHocvienList where ngaythanhtoan not equals to UPDATED_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldBeFound("ngaythanhtoan.notEquals=" + UPDATED_NGAYTHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByNgaythanhtoanIsInShouldWork() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where ngaythanhtoan in DEFAULT_NGAYTHANHTOAN or UPDATED_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldBeFound("ngaythanhtoan.in=" + DEFAULT_NGAYTHANHTOAN + "," + UPDATED_NGAYTHANHTOAN);

        // Get all the qldtQlHocvienList where ngaythanhtoan equals to UPDATED_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("ngaythanhtoan.in=" + UPDATED_NGAYTHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByNgaythanhtoanIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where ngaythanhtoan is not null
        defaultQldtQlHocvienShouldBeFound("ngaythanhtoan.specified=true");

        // Get all the qldtQlHocvienList where ngaythanhtoan is null
        defaultQldtQlHocvienShouldNotBeFound("ngaythanhtoan.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByNgaythanhtoanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where ngaythanhtoan is greater than or equal to DEFAULT_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldBeFound("ngaythanhtoan.greaterThanOrEqual=" + DEFAULT_NGAYTHANHTOAN);

        // Get all the qldtQlHocvienList where ngaythanhtoan is greater than or equal to UPDATED_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("ngaythanhtoan.greaterThanOrEqual=" + UPDATED_NGAYTHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByNgaythanhtoanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where ngaythanhtoan is less than or equal to DEFAULT_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldBeFound("ngaythanhtoan.lessThanOrEqual=" + DEFAULT_NGAYTHANHTOAN);

        // Get all the qldtQlHocvienList where ngaythanhtoan is less than or equal to SMALLER_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("ngaythanhtoan.lessThanOrEqual=" + SMALLER_NGAYTHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByNgaythanhtoanIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where ngaythanhtoan is less than DEFAULT_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("ngaythanhtoan.lessThan=" + DEFAULT_NGAYTHANHTOAN);

        // Get all the qldtQlHocvienList where ngaythanhtoan is less than UPDATED_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldBeFound("ngaythanhtoan.lessThan=" + UPDATED_NGAYTHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByNgaythanhtoanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where ngaythanhtoan is greater than DEFAULT_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("ngaythanhtoan.greaterThan=" + DEFAULT_NGAYTHANHTOAN);

        // Get all the qldtQlHocvienList where ngaythanhtoan is greater than SMALLER_NGAYTHANHTOAN
        defaultQldtQlHocvienShouldBeFound("ngaythanhtoan.greaterThan=" + SMALLER_NGAYTHANHTOAN);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocviensByMathanhtoanIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where mathanhtoan equals to DEFAULT_MATHANHTOAN
        defaultQldtQlHocvienShouldBeFound("mathanhtoan.equals=" + DEFAULT_MATHANHTOAN);

        // Get all the qldtQlHocvienList where mathanhtoan equals to UPDATED_MATHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("mathanhtoan.equals=" + UPDATED_MATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByMathanhtoanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where mathanhtoan not equals to DEFAULT_MATHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("mathanhtoan.notEquals=" + DEFAULT_MATHANHTOAN);

        // Get all the qldtQlHocvienList where mathanhtoan not equals to UPDATED_MATHANHTOAN
        defaultQldtQlHocvienShouldBeFound("mathanhtoan.notEquals=" + UPDATED_MATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByMathanhtoanIsInShouldWork() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where mathanhtoan in DEFAULT_MATHANHTOAN or UPDATED_MATHANHTOAN
        defaultQldtQlHocvienShouldBeFound("mathanhtoan.in=" + DEFAULT_MATHANHTOAN + "," + UPDATED_MATHANHTOAN);

        // Get all the qldtQlHocvienList where mathanhtoan equals to UPDATED_MATHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("mathanhtoan.in=" + UPDATED_MATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByMathanhtoanIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where mathanhtoan is not null
        defaultQldtQlHocvienShouldBeFound("mathanhtoan.specified=true");

        // Get all the qldtQlHocvienList where mathanhtoan is null
        defaultQldtQlHocvienShouldNotBeFound("mathanhtoan.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtQlHocviensByMathanhtoanContainsSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where mathanhtoan contains DEFAULT_MATHANHTOAN
        defaultQldtQlHocvienShouldBeFound("mathanhtoan.contains=" + DEFAULT_MATHANHTOAN);

        // Get all the qldtQlHocvienList where mathanhtoan contains UPDATED_MATHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("mathanhtoan.contains=" + UPDATED_MATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtQlHocviensByMathanhtoanNotContainsSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        // Get all the qldtQlHocvienList where mathanhtoan does not contain DEFAULT_MATHANHTOAN
        defaultQldtQlHocvienShouldNotBeFound("mathanhtoan.doesNotContain=" + DEFAULT_MATHANHTOAN);

        // Get all the qldtQlHocvienList where mathanhtoan does not contain UPDATED_MATHANHTOAN
        defaultQldtQlHocvienShouldBeFound("mathanhtoan.doesNotContain=" + UPDATED_MATHANHTOAN);
    }


    @Test
    @Transactional
    public void getAllQldtQlHocviensByQldtDaotaoIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);
        QldtDaotao qldtDaotao = QldtDaotaoResourceIT.createEntity(em);
        em.persist(qldtDaotao);
        em.flush();
        qldtQlHocvien.setQldtDaotao(qldtDaotao);
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);
        Long qldtDaotaoId = qldtDaotao.getId();

        // Get all the qldtQlHocvienList where qldtDaotao equals to qldtDaotaoId
        defaultQldtQlHocvienShouldBeFound("qldtDaotaoId.equals=" + qldtDaotaoId);

        // Get all the qldtQlHocvienList where qldtDaotao equals to qldtDaotaoId + 1
        defaultQldtQlHocvienShouldNotBeFound("qldtDaotaoId.equals=" + (qldtDaotaoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQldtQlHocvienShouldBeFound(String filter) throws Exception {
        restQldtQlHocvienMockMvc.perform(get("/api/qldt-ql-hocviens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtQlHocvien.getId().intValue())))
            .andExpect(jsonPath("$.[*].diemdanh").value(hasItem(DEFAULT_DIEMDANH)))
            .andExpect(jsonPath("$.[*].diem").value(hasItem(DEFAULT_DIEM)))
            .andExpect(jsonPath("$.[*].danhgia").value(hasItem(DEFAULT_DANHGIA)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)))
            .andExpect(jsonPath("$.[*].trangthaithanhtoan").value(hasItem(DEFAULT_TRANGTHAITHANHTOAN)))
            .andExpect(jsonPath("$.[*].ngaythanhtoan").value(hasItem(DEFAULT_NGAYTHANHTOAN.toString())))
            .andExpect(jsonPath("$.[*].mathanhtoan").value(hasItem(DEFAULT_MATHANHTOAN)));

        // Check, that the count call also returns 1
        restQldtQlHocvienMockMvc.perform(get("/api/qldt-ql-hocviens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQldtQlHocvienShouldNotBeFound(String filter) throws Exception {
        restQldtQlHocvienMockMvc.perform(get("/api/qldt-ql-hocviens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQldtQlHocvienMockMvc.perform(get("/api/qldt-ql-hocviens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingQldtQlHocvien() throws Exception {
        // Get the qldtQlHocvien
        restQldtQlHocvienMockMvc.perform(get("/api/qldt-ql-hocviens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQldtQlHocvien() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        int databaseSizeBeforeUpdate = qldtQlHocvienRepository.findAll().size();

        // Update the qldtQlHocvien
        QldtQlHocvien updatedQldtQlHocvien = qldtQlHocvienRepository.findById(qldtQlHocvien.getId()).get();
        // Disconnect from session so that the updates on updatedQldtQlHocvien are not directly saved in db
        em.detach(updatedQldtQlHocvien);
        updatedQldtQlHocvien
            .diemdanh(UPDATED_DIEMDANH)
            .diem(UPDATED_DIEM)
            .danhgia(UPDATED_DANHGIA)
            .sudung(UPDATED_SUDUNG)
            .trangthaithanhtoan(UPDATED_TRANGTHAITHANHTOAN)
            .ngaythanhtoan(UPDATED_NGAYTHANHTOAN)
            .mathanhtoan(UPDATED_MATHANHTOAN);
        QldtQlHocvienDTO qldtQlHocvienDTO = qldtQlHocvienMapper.toDto(updatedQldtQlHocvien);

        restQldtQlHocvienMockMvc.perform(put("/api/qldt-ql-hocviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtQlHocvienDTO)))
            .andExpect(status().isOk());

        // Validate the QldtQlHocvien in the database
        List<QldtQlHocvien> qldtQlHocvienList = qldtQlHocvienRepository.findAll();
        assertThat(qldtQlHocvienList).hasSize(databaseSizeBeforeUpdate);
        QldtQlHocvien testQldtQlHocvien = qldtQlHocvienList.get(qldtQlHocvienList.size() - 1);
        assertThat(testQldtQlHocvien.getDiemdanh()).isEqualTo(UPDATED_DIEMDANH);
        assertThat(testQldtQlHocvien.getDiem()).isEqualTo(UPDATED_DIEM);
        assertThat(testQldtQlHocvien.getDanhgia()).isEqualTo(UPDATED_DANHGIA);
        assertThat(testQldtQlHocvien.getSudung()).isEqualTo(UPDATED_SUDUNG);
        assertThat(testQldtQlHocvien.getTrangthaithanhtoan()).isEqualTo(UPDATED_TRANGTHAITHANHTOAN);
        assertThat(testQldtQlHocvien.getNgaythanhtoan()).isEqualTo(UPDATED_NGAYTHANHTOAN);
        assertThat(testQldtQlHocvien.getMathanhtoan()).isEqualTo(UPDATED_MATHANHTOAN);
    }

    @Test
    @Transactional
    public void updateNonExistingQldtQlHocvien() throws Exception {
        int databaseSizeBeforeUpdate = qldtQlHocvienRepository.findAll().size();

        // Create the QldtQlHocvien
        QldtQlHocvienDTO qldtQlHocvienDTO = qldtQlHocvienMapper.toDto(qldtQlHocvien);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQldtQlHocvienMockMvc.perform(put("/api/qldt-ql-hocviens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtQlHocvienDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtQlHocvien in the database
        List<QldtQlHocvien> qldtQlHocvienList = qldtQlHocvienRepository.findAll();
        assertThat(qldtQlHocvienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQldtQlHocvien() throws Exception {
        // Initialize the database
        qldtQlHocvienRepository.saveAndFlush(qldtQlHocvien);

        int databaseSizeBeforeDelete = qldtQlHocvienRepository.findAll().size();

        // Delete the qldtQlHocvien
        restQldtQlHocvienMockMvc.perform(delete("/api/qldt-ql-hocviens/{id}", qldtQlHocvien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QldtQlHocvien> qldtQlHocvienList = qldtQlHocvienRepository.findAll();
        assertThat(qldtQlHocvienList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
