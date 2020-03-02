package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.nckh.NhanSu;
import com.vnptit.vnpthis.domain.nckh.DeTai;
import com.vnptit.vnpthis.repository.NhanSuRepository;
import com.vnptit.vnpthis.service.NhanSuService;
import com.vnptit.vnpthis.service.dto.NhanSuDTO;
import com.vnptit.vnpthis.service.mapper.NhanSuMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.service.dto.NhanSuCriteria;
import com.vnptit.vnpthis.service.NhanSuQueryService;

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
 * Integration tests for the {@link NhanSuResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class NhanSuResourceIT {

    private static final Integer DEFAULT_CHUNHIEMDETAI = 1;
    private static final Integer UPDATED_CHUNHIEMDETAI = 2;
    private static final Integer SMALLER_CHUNHIEMDETAI = 1 - 1;

    private static final LocalDate DEFAULT_NGAY_CN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_CN = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_NGUOI_CN = 1;
    private static final Integer UPDATED_NGUOI_CN = 2;
    private static final Integer SMALLER_NGUOI_CN = 1 - 1;

    @Autowired
    private NhanSuRepository nhanSuRepository;

    @Autowired
    private NhanSuMapper nhanSuMapper;

    @Autowired
    private NhanSuService nhanSuService;

    @Autowired
    private NhanSuQueryService nhanSuQueryService;

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

    private MockMvc restNhanSuMockMvc;

    private NhanSu nhanSu;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NhanSuResource nhanSuResource = new NhanSuResource(nhanSuService, nhanSuQueryService);
        this.restNhanSuMockMvc = MockMvcBuilders.standaloneSetup(nhanSuResource)
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
    public static NhanSu createEntity(EntityManager em) {
        NhanSu nhanSu = new NhanSu()
            .chunhiemdetai(DEFAULT_CHUNHIEMDETAI)
            .ngayCn(DEFAULT_NGAY_CN)
            .nguoiCn(DEFAULT_NGUOI_CN);
        return nhanSu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhanSu createUpdatedEntity(EntityManager em) {
        NhanSu nhanSu = new NhanSu()
            .chunhiemdetai(UPDATED_CHUNHIEMDETAI)
            .ngayCn(UPDATED_NGAY_CN)
            .nguoiCn(UPDATED_NGUOI_CN);
        return nhanSu;
    }

    @BeforeEach
    public void initTest() {
        nhanSu = createEntity(em);
    }

    @Test
    @Transactional
    public void createNhanSu() throws Exception {
        int databaseSizeBeforeCreate = nhanSuRepository.findAll().size();

        // Create the NhanSu
        NhanSuDTO nhanSuDTO = nhanSuMapper.toDto(nhanSu);
        restNhanSuMockMvc.perform(post("/api/nhan-sus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanSuDTO)))
            .andExpect(status().isCreated());

        // Validate the NhanSu in the database
        List<NhanSu> nhanSuList = nhanSuRepository.findAll();
        assertThat(nhanSuList).hasSize(databaseSizeBeforeCreate + 1);
        NhanSu testNhanSu = nhanSuList.get(nhanSuList.size() - 1);
        assertThat(testNhanSu.getChunhiemdetai()).isEqualTo(DEFAULT_CHUNHIEMDETAI);
        assertThat(testNhanSu.getNgayCn()).isEqualTo(DEFAULT_NGAY_CN);
        assertThat(testNhanSu.getNguoiCn()).isEqualTo(DEFAULT_NGUOI_CN);
    }

    @Test
    @Transactional
    public void createNhanSuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nhanSuRepository.findAll().size();

        // Create the NhanSu with an existing ID
        nhanSu.setId(1L);
        NhanSuDTO nhanSuDTO = nhanSuMapper.toDto(nhanSu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhanSuMockMvc.perform(post("/api/nhan-sus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhanSu in the database
        List<NhanSu> nhanSuList = nhanSuRepository.findAll();
        assertThat(nhanSuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkChunhiemdetaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanSuRepository.findAll().size();
        // set the field null
        nhanSu.setChunhiemdetai(null);

        // Create the NhanSu, which fails.
        NhanSuDTO nhanSuDTO = nhanSuMapper.toDto(nhanSu);

        restNhanSuMockMvc.perform(post("/api/nhan-sus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanSuDTO)))
            .andExpect(status().isBadRequest());

        List<NhanSu> nhanSuList = nhanSuRepository.findAll();
        assertThat(nhanSuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgayCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanSuRepository.findAll().size();
        // set the field null
        nhanSu.setNgayCn(null);

        // Create the NhanSu, which fails.
        NhanSuDTO nhanSuDTO = nhanSuMapper.toDto(nhanSu);

        restNhanSuMockMvc.perform(post("/api/nhan-sus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanSuDTO)))
            .andExpect(status().isBadRequest());

        List<NhanSu> nhanSuList = nhanSuRepository.findAll();
        assertThat(nhanSuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNguoiCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanSuRepository.findAll().size();
        // set the field null
        nhanSu.setNguoiCn(null);

        // Create the NhanSu, which fails.
        NhanSuDTO nhanSuDTO = nhanSuMapper.toDto(nhanSu);

        restNhanSuMockMvc.perform(post("/api/nhan-sus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanSuDTO)))
            .andExpect(status().isBadRequest());

        List<NhanSu> nhanSuList = nhanSuRepository.findAll();
        assertThat(nhanSuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNhanSus() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList
        restNhanSuMockMvc.perform(get("/api/nhan-sus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanSu.getId().intValue())))
            .andExpect(jsonPath("$.[*].chunhiemdetai").value(hasItem(DEFAULT_CHUNHIEMDETAI)))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)));
    }

    @Test
    @Transactional
    public void getNhanSu() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get the nhanSu
        restNhanSuMockMvc.perform(get("/api/nhan-sus/{id}", nhanSu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nhanSu.getId().intValue()))
            .andExpect(jsonPath("$.chunhiemdetai").value(DEFAULT_CHUNHIEMDETAI))
            .andExpect(jsonPath("$.ngayCn").value(DEFAULT_NGAY_CN.toString()))
            .andExpect(jsonPath("$.nguoiCn").value(DEFAULT_NGUOI_CN));
    }


    @Test
    @Transactional
    public void getNhanSusByIdFiltering() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        Long id = nhanSu.getId();

        defaultNhanSuShouldBeFound("id.equals=" + id);
        defaultNhanSuShouldNotBeFound("id.notEquals=" + id);

        defaultNhanSuShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNhanSuShouldNotBeFound("id.greaterThan=" + id);

        defaultNhanSuShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNhanSuShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllNhanSusByChunhiemdetaiIsEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where chunhiemdetai equals to DEFAULT_CHUNHIEMDETAI
        defaultNhanSuShouldBeFound("chunhiemdetai.equals=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the nhanSuList where chunhiemdetai equals to UPDATED_CHUNHIEMDETAI
        defaultNhanSuShouldNotBeFound("chunhiemdetai.equals=" + UPDATED_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllNhanSusByChunhiemdetaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where chunhiemdetai not equals to DEFAULT_CHUNHIEMDETAI
        defaultNhanSuShouldNotBeFound("chunhiemdetai.notEquals=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the nhanSuList where chunhiemdetai not equals to UPDATED_CHUNHIEMDETAI
        defaultNhanSuShouldBeFound("chunhiemdetai.notEquals=" + UPDATED_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllNhanSusByChunhiemdetaiIsInShouldWork() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where chunhiemdetai in DEFAULT_CHUNHIEMDETAI or UPDATED_CHUNHIEMDETAI
        defaultNhanSuShouldBeFound("chunhiemdetai.in=" + DEFAULT_CHUNHIEMDETAI + "," + UPDATED_CHUNHIEMDETAI);

        // Get all the nhanSuList where chunhiemdetai equals to UPDATED_CHUNHIEMDETAI
        defaultNhanSuShouldNotBeFound("chunhiemdetai.in=" + UPDATED_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllNhanSusByChunhiemdetaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where chunhiemdetai is not null
        defaultNhanSuShouldBeFound("chunhiemdetai.specified=true");

        // Get all the nhanSuList where chunhiemdetai is null
        defaultNhanSuShouldNotBeFound("chunhiemdetai.specified=false");
    }

    @Test
    @Transactional
    public void getAllNhanSusByChunhiemdetaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where chunhiemdetai is greater than or equal to DEFAULT_CHUNHIEMDETAI
        defaultNhanSuShouldBeFound("chunhiemdetai.greaterThanOrEqual=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the nhanSuList where chunhiemdetai is greater than or equal to UPDATED_CHUNHIEMDETAI
        defaultNhanSuShouldNotBeFound("chunhiemdetai.greaterThanOrEqual=" + UPDATED_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllNhanSusByChunhiemdetaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where chunhiemdetai is less than or equal to DEFAULT_CHUNHIEMDETAI
        defaultNhanSuShouldBeFound("chunhiemdetai.lessThanOrEqual=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the nhanSuList where chunhiemdetai is less than or equal to SMALLER_CHUNHIEMDETAI
        defaultNhanSuShouldNotBeFound("chunhiemdetai.lessThanOrEqual=" + SMALLER_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllNhanSusByChunhiemdetaiIsLessThanSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where chunhiemdetai is less than DEFAULT_CHUNHIEMDETAI
        defaultNhanSuShouldNotBeFound("chunhiemdetai.lessThan=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the nhanSuList where chunhiemdetai is less than UPDATED_CHUNHIEMDETAI
        defaultNhanSuShouldBeFound("chunhiemdetai.lessThan=" + UPDATED_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllNhanSusByChunhiemdetaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where chunhiemdetai is greater than DEFAULT_CHUNHIEMDETAI
        defaultNhanSuShouldNotBeFound("chunhiemdetai.greaterThan=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the nhanSuList where chunhiemdetai is greater than SMALLER_CHUNHIEMDETAI
        defaultNhanSuShouldBeFound("chunhiemdetai.greaterThan=" + SMALLER_CHUNHIEMDETAI);
    }


    @Test
    @Transactional
    public void getAllNhanSusByNgayCnIsEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where ngayCn equals to DEFAULT_NGAY_CN
        defaultNhanSuShouldBeFound("ngayCn.equals=" + DEFAULT_NGAY_CN);

        // Get all the nhanSuList where ngayCn equals to UPDATED_NGAY_CN
        defaultNhanSuShouldNotBeFound("ngayCn.equals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNgayCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where ngayCn not equals to DEFAULT_NGAY_CN
        defaultNhanSuShouldNotBeFound("ngayCn.notEquals=" + DEFAULT_NGAY_CN);

        // Get all the nhanSuList where ngayCn not equals to UPDATED_NGAY_CN
        defaultNhanSuShouldBeFound("ngayCn.notEquals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNgayCnIsInShouldWork() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where ngayCn in DEFAULT_NGAY_CN or UPDATED_NGAY_CN
        defaultNhanSuShouldBeFound("ngayCn.in=" + DEFAULT_NGAY_CN + "," + UPDATED_NGAY_CN);

        // Get all the nhanSuList where ngayCn equals to UPDATED_NGAY_CN
        defaultNhanSuShouldNotBeFound("ngayCn.in=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNgayCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where ngayCn is not null
        defaultNhanSuShouldBeFound("ngayCn.specified=true");

        // Get all the nhanSuList where ngayCn is null
        defaultNhanSuShouldNotBeFound("ngayCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllNhanSusByNgayCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where ngayCn is greater than or equal to DEFAULT_NGAY_CN
        defaultNhanSuShouldBeFound("ngayCn.greaterThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the nhanSuList where ngayCn is greater than or equal to UPDATED_NGAY_CN
        defaultNhanSuShouldNotBeFound("ngayCn.greaterThanOrEqual=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNgayCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where ngayCn is less than or equal to DEFAULT_NGAY_CN
        defaultNhanSuShouldBeFound("ngayCn.lessThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the nhanSuList where ngayCn is less than or equal to SMALLER_NGAY_CN
        defaultNhanSuShouldNotBeFound("ngayCn.lessThanOrEqual=" + SMALLER_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNgayCnIsLessThanSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where ngayCn is less than DEFAULT_NGAY_CN
        defaultNhanSuShouldNotBeFound("ngayCn.lessThan=" + DEFAULT_NGAY_CN);

        // Get all the nhanSuList where ngayCn is less than UPDATED_NGAY_CN
        defaultNhanSuShouldBeFound("ngayCn.lessThan=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNgayCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where ngayCn is greater than DEFAULT_NGAY_CN
        defaultNhanSuShouldNotBeFound("ngayCn.greaterThan=" + DEFAULT_NGAY_CN);

        // Get all the nhanSuList where ngayCn is greater than SMALLER_NGAY_CN
        defaultNhanSuShouldBeFound("ngayCn.greaterThan=" + SMALLER_NGAY_CN);
    }


    @Test
    @Transactional
    public void getAllNhanSusByNguoiCnIsEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where nguoiCn equals to DEFAULT_NGUOI_CN
        defaultNhanSuShouldBeFound("nguoiCn.equals=" + DEFAULT_NGUOI_CN);

        // Get all the nhanSuList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultNhanSuShouldNotBeFound("nguoiCn.equals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNguoiCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where nguoiCn not equals to DEFAULT_NGUOI_CN
        defaultNhanSuShouldNotBeFound("nguoiCn.notEquals=" + DEFAULT_NGUOI_CN);

        // Get all the nhanSuList where nguoiCn not equals to UPDATED_NGUOI_CN
        defaultNhanSuShouldBeFound("nguoiCn.notEquals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNguoiCnIsInShouldWork() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where nguoiCn in DEFAULT_NGUOI_CN or UPDATED_NGUOI_CN
        defaultNhanSuShouldBeFound("nguoiCn.in=" + DEFAULT_NGUOI_CN + "," + UPDATED_NGUOI_CN);

        // Get all the nhanSuList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultNhanSuShouldNotBeFound("nguoiCn.in=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNguoiCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where nguoiCn is not null
        defaultNhanSuShouldBeFound("nguoiCn.specified=true");

        // Get all the nhanSuList where nguoiCn is null
        defaultNhanSuShouldNotBeFound("nguoiCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllNhanSusByNguoiCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where nguoiCn is greater than or equal to DEFAULT_NGUOI_CN
        defaultNhanSuShouldBeFound("nguoiCn.greaterThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the nhanSuList where nguoiCn is greater than or equal to UPDATED_NGUOI_CN
        defaultNhanSuShouldNotBeFound("nguoiCn.greaterThanOrEqual=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNguoiCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where nguoiCn is less than or equal to DEFAULT_NGUOI_CN
        defaultNhanSuShouldBeFound("nguoiCn.lessThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the nhanSuList where nguoiCn is less than or equal to SMALLER_NGUOI_CN
        defaultNhanSuShouldNotBeFound("nguoiCn.lessThanOrEqual=" + SMALLER_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNguoiCnIsLessThanSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where nguoiCn is less than DEFAULT_NGUOI_CN
        defaultNhanSuShouldNotBeFound("nguoiCn.lessThan=" + DEFAULT_NGUOI_CN);

        // Get all the nhanSuList where nguoiCn is less than UPDATED_NGUOI_CN
        defaultNhanSuShouldBeFound("nguoiCn.lessThan=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNhanSusByNguoiCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        // Get all the nhanSuList where nguoiCn is greater than DEFAULT_NGUOI_CN
        defaultNhanSuShouldNotBeFound("nguoiCn.greaterThan=" + DEFAULT_NGUOI_CN);

        // Get all the nhanSuList where nguoiCn is greater than SMALLER_NGUOI_CN
        defaultNhanSuShouldBeFound("nguoiCn.greaterThan=" + SMALLER_NGUOI_CN);
    }


    @Test
    @Transactional
    public void getAllNhanSusByDeTaiIsEqualToSomething() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);
        DeTai deTai = DeTaiResourceIT.createEntity(em);
        em.persist(deTai);
        em.flush();
        nhanSu.setDeTai(deTai);
        nhanSuRepository.saveAndFlush(nhanSu);
        Long deTaiId = deTai.getId();

        // Get all the nhanSuList where deTai equals to deTaiId
        defaultNhanSuShouldBeFound("deTaiId.equals=" + deTaiId);

        // Get all the nhanSuList where deTai equals to deTaiId + 1
        defaultNhanSuShouldNotBeFound("deTaiId.equals=" + (deTaiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNhanSuShouldBeFound(String filter) throws Exception {
        restNhanSuMockMvc.perform(get("/api/nhan-sus?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanSu.getId().intValue())))
            .andExpect(jsonPath("$.[*].chunhiemdetai").value(hasItem(DEFAULT_CHUNHIEMDETAI)))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)));

        // Check, that the count call also returns 1
        restNhanSuMockMvc.perform(get("/api/nhan-sus/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNhanSuShouldNotBeFound(String filter) throws Exception {
        restNhanSuMockMvc.perform(get("/api/nhan-sus?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNhanSuMockMvc.perform(get("/api/nhan-sus/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingNhanSu() throws Exception {
        // Get the nhanSu
        restNhanSuMockMvc.perform(get("/api/nhan-sus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNhanSu() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        int databaseSizeBeforeUpdate = nhanSuRepository.findAll().size();

        // Update the nhanSu
        NhanSu updatedNhanSu = nhanSuRepository.findById(nhanSu.getId()).get();
        // Disconnect from session so that the updates on updatedNhanSu are not directly saved in db
        em.detach(updatedNhanSu);
        updatedNhanSu
            .chunhiemdetai(UPDATED_CHUNHIEMDETAI)
            .ngayCn(UPDATED_NGAY_CN)
            .nguoiCn(UPDATED_NGUOI_CN);
        NhanSuDTO nhanSuDTO = nhanSuMapper.toDto(updatedNhanSu);

        restNhanSuMockMvc.perform(put("/api/nhan-sus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanSuDTO)))
            .andExpect(status().isOk());

        // Validate the NhanSu in the database
        List<NhanSu> nhanSuList = nhanSuRepository.findAll();
        assertThat(nhanSuList).hasSize(databaseSizeBeforeUpdate);
        NhanSu testNhanSu = nhanSuList.get(nhanSuList.size() - 1);
        assertThat(testNhanSu.getChunhiemdetai()).isEqualTo(UPDATED_CHUNHIEMDETAI);
        assertThat(testNhanSu.getNgayCn()).isEqualTo(UPDATED_NGAY_CN);
        assertThat(testNhanSu.getNguoiCn()).isEqualTo(UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void updateNonExistingNhanSu() throws Exception {
        int databaseSizeBeforeUpdate = nhanSuRepository.findAll().size();

        // Create the NhanSu
        NhanSuDTO nhanSuDTO = nhanSuMapper.toDto(nhanSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhanSuMockMvc.perform(put("/api/nhan-sus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhanSu in the database
        List<NhanSu> nhanSuList = nhanSuRepository.findAll();
        assertThat(nhanSuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNhanSu() throws Exception {
        // Initialize the database
        nhanSuRepository.saveAndFlush(nhanSu);

        int databaseSizeBeforeDelete = nhanSuRepository.findAll().size();

        // Delete the nhanSu
        restNhanSuMockMvc.perform(delete("/api/nhan-sus/{id}", nhanSu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NhanSu> nhanSuList = nhanSuRepository.findAll();
        assertThat(nhanSuList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
