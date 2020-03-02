package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.DanhGia;
import com.vnptit.vnpthis.domain.DeTai;
import com.vnptit.vnpthis.repository.DanhGiaRepository;
import com.vnptit.vnpthis.service.DanhGiaService;
import com.vnptit.vnpthis.service.dto.DanhGiaDTO;
import com.vnptit.vnpthis.service.mapper.DanhGiaMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.service.dto.DanhGiaCriteria;
import com.vnptit.vnpthis.service.DanhGiaQueryService;

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
 * Integration tests for the {@link DanhGiaResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class DanhGiaResourceIT {

    private static final Integer DEFAULT_DIEMDANHGIA = 1;
    private static final Integer UPDATED_DIEMDANHGIA = 2;
    private static final Integer SMALLER_DIEMDANHGIA = 1 - 1;

    private static final String DEFAULT_GHICHU = "AAAAAAAAAA";
    private static final String UPDATED_GHICHU = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_CN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_CN = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_NGUOI_CN = 1;
    private static final Integer UPDATED_NGUOI_CN = 2;
    private static final Integer SMALLER_NGUOI_CN = 1 - 1;

    @Autowired
    private DanhGiaRepository danhGiaRepository;

    @Autowired
    private DanhGiaMapper danhGiaMapper;

    @Autowired
    private DanhGiaService danhGiaService;

    @Autowired
    private DanhGiaQueryService danhGiaQueryService;

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

    private MockMvc restDanhGiaMockMvc;

    private DanhGia danhGia;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DanhGiaResource danhGiaResource = new DanhGiaResource(danhGiaService, danhGiaQueryService);
        this.restDanhGiaMockMvc = MockMvcBuilders.standaloneSetup(danhGiaResource)
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
    public static DanhGia createEntity(EntityManager em) {
        DanhGia danhGia = new DanhGia()
            .diemdanhgia(DEFAULT_DIEMDANHGIA)
            .ghichu(DEFAULT_GHICHU)
            .ngayCn(DEFAULT_NGAY_CN)
            .nguoiCn(DEFAULT_NGUOI_CN);
        return danhGia;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhGia createUpdatedEntity(EntityManager em) {
        DanhGia danhGia = new DanhGia()
            .diemdanhgia(UPDATED_DIEMDANHGIA)
            .ghichu(UPDATED_GHICHU)
            .ngayCn(UPDATED_NGAY_CN)
            .nguoiCn(UPDATED_NGUOI_CN);
        return danhGia;
    }

    @BeforeEach
    public void initTest() {
        danhGia = createEntity(em);
    }

    @Test
    @Transactional
    public void createDanhGia() throws Exception {
        int databaseSizeBeforeCreate = danhGiaRepository.findAll().size();

        // Create the DanhGia
        DanhGiaDTO danhGiaDTO = danhGiaMapper.toDto(danhGia);
        restDanhGiaMockMvc.perform(post("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGiaDTO)))
            .andExpect(status().isCreated());

        // Validate the DanhGia in the database
        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeCreate + 1);
        DanhGia testDanhGia = danhGiaList.get(danhGiaList.size() - 1);
        assertThat(testDanhGia.getDiemdanhgia()).isEqualTo(DEFAULT_DIEMDANHGIA);
        assertThat(testDanhGia.getGhichu()).isEqualTo(DEFAULT_GHICHU);
        assertThat(testDanhGia.getNgayCn()).isEqualTo(DEFAULT_NGAY_CN);
        assertThat(testDanhGia.getNguoiCn()).isEqualTo(DEFAULT_NGUOI_CN);
    }

    @Test
    @Transactional
    public void createDanhGiaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = danhGiaRepository.findAll().size();

        // Create the DanhGia with an existing ID
        danhGia.setId(1L);
        DanhGiaDTO danhGiaDTO = danhGiaMapper.toDto(danhGia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhGiaMockMvc.perform(post("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGiaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhGia in the database
        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDiemdanhgiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhGiaRepository.findAll().size();
        // set the field null
        danhGia.setDiemdanhgia(null);

        // Create the DanhGia, which fails.
        DanhGiaDTO danhGiaDTO = danhGiaMapper.toDto(danhGia);

        restDanhGiaMockMvc.perform(post("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGiaDTO)))
            .andExpect(status().isBadRequest());

        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGhichuIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhGiaRepository.findAll().size();
        // set the field null
        danhGia.setGhichu(null);

        // Create the DanhGia, which fails.
        DanhGiaDTO danhGiaDTO = danhGiaMapper.toDto(danhGia);

        restDanhGiaMockMvc.perform(post("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGiaDTO)))
            .andExpect(status().isBadRequest());

        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgayCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhGiaRepository.findAll().size();
        // set the field null
        danhGia.setNgayCn(null);

        // Create the DanhGia, which fails.
        DanhGiaDTO danhGiaDTO = danhGiaMapper.toDto(danhGia);

        restDanhGiaMockMvc.perform(post("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGiaDTO)))
            .andExpect(status().isBadRequest());

        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNguoiCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhGiaRepository.findAll().size();
        // set the field null
        danhGia.setNguoiCn(null);

        // Create the DanhGia, which fails.
        DanhGiaDTO danhGiaDTO = danhGiaMapper.toDto(danhGia);

        restDanhGiaMockMvc.perform(post("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGiaDTO)))
            .andExpect(status().isBadRequest());

        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDanhGias() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList
        restDanhGiaMockMvc.perform(get("/api/danh-gias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhGia.getId().intValue())))
            .andExpect(jsonPath("$.[*].diemdanhgia").value(hasItem(DEFAULT_DIEMDANHGIA)))
            .andExpect(jsonPath("$.[*].ghichu").value(hasItem(DEFAULT_GHICHU)))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)));
    }
    
    @Test
    @Transactional
    public void getDanhGia() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get the danhGia
        restDanhGiaMockMvc.perform(get("/api/danh-gias/{id}", danhGia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(danhGia.getId().intValue()))
            .andExpect(jsonPath("$.diemdanhgia").value(DEFAULT_DIEMDANHGIA))
            .andExpect(jsonPath("$.ghichu").value(DEFAULT_GHICHU))
            .andExpect(jsonPath("$.ngayCn").value(DEFAULT_NGAY_CN.toString()))
            .andExpect(jsonPath("$.nguoiCn").value(DEFAULT_NGUOI_CN));
    }


    @Test
    @Transactional
    public void getDanhGiasByIdFiltering() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        Long id = danhGia.getId();

        defaultDanhGiaShouldBeFound("id.equals=" + id);
        defaultDanhGiaShouldNotBeFound("id.notEquals=" + id);

        defaultDanhGiaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDanhGiaShouldNotBeFound("id.greaterThan=" + id);

        defaultDanhGiaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDanhGiaShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDanhGiasByDiemdanhgiaIsEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where diemdanhgia equals to DEFAULT_DIEMDANHGIA
        defaultDanhGiaShouldBeFound("diemdanhgia.equals=" + DEFAULT_DIEMDANHGIA);

        // Get all the danhGiaList where diemdanhgia equals to UPDATED_DIEMDANHGIA
        defaultDanhGiaShouldNotBeFound("diemdanhgia.equals=" + UPDATED_DIEMDANHGIA);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByDiemdanhgiaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where diemdanhgia not equals to DEFAULT_DIEMDANHGIA
        defaultDanhGiaShouldNotBeFound("diemdanhgia.notEquals=" + DEFAULT_DIEMDANHGIA);

        // Get all the danhGiaList where diemdanhgia not equals to UPDATED_DIEMDANHGIA
        defaultDanhGiaShouldBeFound("diemdanhgia.notEquals=" + UPDATED_DIEMDANHGIA);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByDiemdanhgiaIsInShouldWork() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where diemdanhgia in DEFAULT_DIEMDANHGIA or UPDATED_DIEMDANHGIA
        defaultDanhGiaShouldBeFound("diemdanhgia.in=" + DEFAULT_DIEMDANHGIA + "," + UPDATED_DIEMDANHGIA);

        // Get all the danhGiaList where diemdanhgia equals to UPDATED_DIEMDANHGIA
        defaultDanhGiaShouldNotBeFound("diemdanhgia.in=" + UPDATED_DIEMDANHGIA);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByDiemdanhgiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where diemdanhgia is not null
        defaultDanhGiaShouldBeFound("diemdanhgia.specified=true");

        // Get all the danhGiaList where diemdanhgia is null
        defaultDanhGiaShouldNotBeFound("diemdanhgia.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhGiasByDiemdanhgiaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where diemdanhgia is greater than or equal to DEFAULT_DIEMDANHGIA
        defaultDanhGiaShouldBeFound("diemdanhgia.greaterThanOrEqual=" + DEFAULT_DIEMDANHGIA);

        // Get all the danhGiaList where diemdanhgia is greater than or equal to UPDATED_DIEMDANHGIA
        defaultDanhGiaShouldNotBeFound("diemdanhgia.greaterThanOrEqual=" + UPDATED_DIEMDANHGIA);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByDiemdanhgiaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where diemdanhgia is less than or equal to DEFAULT_DIEMDANHGIA
        defaultDanhGiaShouldBeFound("diemdanhgia.lessThanOrEqual=" + DEFAULT_DIEMDANHGIA);

        // Get all the danhGiaList where diemdanhgia is less than or equal to SMALLER_DIEMDANHGIA
        defaultDanhGiaShouldNotBeFound("diemdanhgia.lessThanOrEqual=" + SMALLER_DIEMDANHGIA);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByDiemdanhgiaIsLessThanSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where diemdanhgia is less than DEFAULT_DIEMDANHGIA
        defaultDanhGiaShouldNotBeFound("diemdanhgia.lessThan=" + DEFAULT_DIEMDANHGIA);

        // Get all the danhGiaList where diemdanhgia is less than UPDATED_DIEMDANHGIA
        defaultDanhGiaShouldBeFound("diemdanhgia.lessThan=" + UPDATED_DIEMDANHGIA);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByDiemdanhgiaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where diemdanhgia is greater than DEFAULT_DIEMDANHGIA
        defaultDanhGiaShouldNotBeFound("diemdanhgia.greaterThan=" + DEFAULT_DIEMDANHGIA);

        // Get all the danhGiaList where diemdanhgia is greater than SMALLER_DIEMDANHGIA
        defaultDanhGiaShouldBeFound("diemdanhgia.greaterThan=" + SMALLER_DIEMDANHGIA);
    }


    @Test
    @Transactional
    public void getAllDanhGiasByGhichuIsEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ghichu equals to DEFAULT_GHICHU
        defaultDanhGiaShouldBeFound("ghichu.equals=" + DEFAULT_GHICHU);

        // Get all the danhGiaList where ghichu equals to UPDATED_GHICHU
        defaultDanhGiaShouldNotBeFound("ghichu.equals=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByGhichuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ghichu not equals to DEFAULT_GHICHU
        defaultDanhGiaShouldNotBeFound("ghichu.notEquals=" + DEFAULT_GHICHU);

        // Get all the danhGiaList where ghichu not equals to UPDATED_GHICHU
        defaultDanhGiaShouldBeFound("ghichu.notEquals=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByGhichuIsInShouldWork() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ghichu in DEFAULT_GHICHU or UPDATED_GHICHU
        defaultDanhGiaShouldBeFound("ghichu.in=" + DEFAULT_GHICHU + "," + UPDATED_GHICHU);

        // Get all the danhGiaList where ghichu equals to UPDATED_GHICHU
        defaultDanhGiaShouldNotBeFound("ghichu.in=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByGhichuIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ghichu is not null
        defaultDanhGiaShouldBeFound("ghichu.specified=true");

        // Get all the danhGiaList where ghichu is null
        defaultDanhGiaShouldNotBeFound("ghichu.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhGiasByGhichuContainsSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ghichu contains DEFAULT_GHICHU
        defaultDanhGiaShouldBeFound("ghichu.contains=" + DEFAULT_GHICHU);

        // Get all the danhGiaList where ghichu contains UPDATED_GHICHU
        defaultDanhGiaShouldNotBeFound("ghichu.contains=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByGhichuNotContainsSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ghichu does not contain DEFAULT_GHICHU
        defaultDanhGiaShouldNotBeFound("ghichu.doesNotContain=" + DEFAULT_GHICHU);

        // Get all the danhGiaList where ghichu does not contain UPDATED_GHICHU
        defaultDanhGiaShouldBeFound("ghichu.doesNotContain=" + UPDATED_GHICHU);
    }


    @Test
    @Transactional
    public void getAllDanhGiasByNgayCnIsEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ngayCn equals to DEFAULT_NGAY_CN
        defaultDanhGiaShouldBeFound("ngayCn.equals=" + DEFAULT_NGAY_CN);

        // Get all the danhGiaList where ngayCn equals to UPDATED_NGAY_CN
        defaultDanhGiaShouldNotBeFound("ngayCn.equals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNgayCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ngayCn not equals to DEFAULT_NGAY_CN
        defaultDanhGiaShouldNotBeFound("ngayCn.notEquals=" + DEFAULT_NGAY_CN);

        // Get all the danhGiaList where ngayCn not equals to UPDATED_NGAY_CN
        defaultDanhGiaShouldBeFound("ngayCn.notEquals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNgayCnIsInShouldWork() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ngayCn in DEFAULT_NGAY_CN or UPDATED_NGAY_CN
        defaultDanhGiaShouldBeFound("ngayCn.in=" + DEFAULT_NGAY_CN + "," + UPDATED_NGAY_CN);

        // Get all the danhGiaList where ngayCn equals to UPDATED_NGAY_CN
        defaultDanhGiaShouldNotBeFound("ngayCn.in=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNgayCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ngayCn is not null
        defaultDanhGiaShouldBeFound("ngayCn.specified=true");

        // Get all the danhGiaList where ngayCn is null
        defaultDanhGiaShouldNotBeFound("ngayCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNgayCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ngayCn is greater than or equal to DEFAULT_NGAY_CN
        defaultDanhGiaShouldBeFound("ngayCn.greaterThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the danhGiaList where ngayCn is greater than or equal to UPDATED_NGAY_CN
        defaultDanhGiaShouldNotBeFound("ngayCn.greaterThanOrEqual=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNgayCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ngayCn is less than or equal to DEFAULT_NGAY_CN
        defaultDanhGiaShouldBeFound("ngayCn.lessThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the danhGiaList where ngayCn is less than or equal to SMALLER_NGAY_CN
        defaultDanhGiaShouldNotBeFound("ngayCn.lessThanOrEqual=" + SMALLER_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNgayCnIsLessThanSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ngayCn is less than DEFAULT_NGAY_CN
        defaultDanhGiaShouldNotBeFound("ngayCn.lessThan=" + DEFAULT_NGAY_CN);

        // Get all the danhGiaList where ngayCn is less than UPDATED_NGAY_CN
        defaultDanhGiaShouldBeFound("ngayCn.lessThan=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNgayCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where ngayCn is greater than DEFAULT_NGAY_CN
        defaultDanhGiaShouldNotBeFound("ngayCn.greaterThan=" + DEFAULT_NGAY_CN);

        // Get all the danhGiaList where ngayCn is greater than SMALLER_NGAY_CN
        defaultDanhGiaShouldBeFound("ngayCn.greaterThan=" + SMALLER_NGAY_CN);
    }


    @Test
    @Transactional
    public void getAllDanhGiasByNguoiCnIsEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where nguoiCn equals to DEFAULT_NGUOI_CN
        defaultDanhGiaShouldBeFound("nguoiCn.equals=" + DEFAULT_NGUOI_CN);

        // Get all the danhGiaList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultDanhGiaShouldNotBeFound("nguoiCn.equals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNguoiCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where nguoiCn not equals to DEFAULT_NGUOI_CN
        defaultDanhGiaShouldNotBeFound("nguoiCn.notEquals=" + DEFAULT_NGUOI_CN);

        // Get all the danhGiaList where nguoiCn not equals to UPDATED_NGUOI_CN
        defaultDanhGiaShouldBeFound("nguoiCn.notEquals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNguoiCnIsInShouldWork() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where nguoiCn in DEFAULT_NGUOI_CN or UPDATED_NGUOI_CN
        defaultDanhGiaShouldBeFound("nguoiCn.in=" + DEFAULT_NGUOI_CN + "," + UPDATED_NGUOI_CN);

        // Get all the danhGiaList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultDanhGiaShouldNotBeFound("nguoiCn.in=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNguoiCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where nguoiCn is not null
        defaultDanhGiaShouldBeFound("nguoiCn.specified=true");

        // Get all the danhGiaList where nguoiCn is null
        defaultDanhGiaShouldNotBeFound("nguoiCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNguoiCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where nguoiCn is greater than or equal to DEFAULT_NGUOI_CN
        defaultDanhGiaShouldBeFound("nguoiCn.greaterThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the danhGiaList where nguoiCn is greater than or equal to UPDATED_NGUOI_CN
        defaultDanhGiaShouldNotBeFound("nguoiCn.greaterThanOrEqual=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNguoiCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where nguoiCn is less than or equal to DEFAULT_NGUOI_CN
        defaultDanhGiaShouldBeFound("nguoiCn.lessThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the danhGiaList where nguoiCn is less than or equal to SMALLER_NGUOI_CN
        defaultDanhGiaShouldNotBeFound("nguoiCn.lessThanOrEqual=" + SMALLER_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNguoiCnIsLessThanSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where nguoiCn is less than DEFAULT_NGUOI_CN
        defaultDanhGiaShouldNotBeFound("nguoiCn.lessThan=" + DEFAULT_NGUOI_CN);

        // Get all the danhGiaList where nguoiCn is less than UPDATED_NGUOI_CN
        defaultDanhGiaShouldBeFound("nguoiCn.lessThan=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDanhGiasByNguoiCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList where nguoiCn is greater than DEFAULT_NGUOI_CN
        defaultDanhGiaShouldNotBeFound("nguoiCn.greaterThan=" + DEFAULT_NGUOI_CN);

        // Get all the danhGiaList where nguoiCn is greater than SMALLER_NGUOI_CN
        defaultDanhGiaShouldBeFound("nguoiCn.greaterThan=" + SMALLER_NGUOI_CN);
    }


    @Test
    @Transactional
    public void getAllDanhGiasByDeTaiIsEqualToSomething() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);
        DeTai deTai = DeTaiResourceIT.createEntity(em);
        em.persist(deTai);
        em.flush();
        danhGia.setDeTai(deTai);
        danhGiaRepository.saveAndFlush(danhGia);
        Long deTaiId = deTai.getId();

        // Get all the danhGiaList where deTai equals to deTaiId
        defaultDanhGiaShouldBeFound("deTaiId.equals=" + deTaiId);

        // Get all the danhGiaList where deTai equals to deTaiId + 1
        defaultDanhGiaShouldNotBeFound("deTaiId.equals=" + (deTaiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhGiaShouldBeFound(String filter) throws Exception {
        restDanhGiaMockMvc.perform(get("/api/danh-gias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhGia.getId().intValue())))
            .andExpect(jsonPath("$.[*].diemdanhgia").value(hasItem(DEFAULT_DIEMDANHGIA)))
            .andExpect(jsonPath("$.[*].ghichu").value(hasItem(DEFAULT_GHICHU)))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)));

        // Check, that the count call also returns 1
        restDanhGiaMockMvc.perform(get("/api/danh-gias/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhGiaShouldNotBeFound(String filter) throws Exception {
        restDanhGiaMockMvc.perform(get("/api/danh-gias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhGiaMockMvc.perform(get("/api/danh-gias/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDanhGia() throws Exception {
        // Get the danhGia
        restDanhGiaMockMvc.perform(get("/api/danh-gias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDanhGia() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        int databaseSizeBeforeUpdate = danhGiaRepository.findAll().size();

        // Update the danhGia
        DanhGia updatedDanhGia = danhGiaRepository.findById(danhGia.getId()).get();
        // Disconnect from session so that the updates on updatedDanhGia are not directly saved in db
        em.detach(updatedDanhGia);
        updatedDanhGia
            .diemdanhgia(UPDATED_DIEMDANHGIA)
            .ghichu(UPDATED_GHICHU)
            .ngayCn(UPDATED_NGAY_CN)
            .nguoiCn(UPDATED_NGUOI_CN);
        DanhGiaDTO danhGiaDTO = danhGiaMapper.toDto(updatedDanhGia);

        restDanhGiaMockMvc.perform(put("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGiaDTO)))
            .andExpect(status().isOk());

        // Validate the DanhGia in the database
        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeUpdate);
        DanhGia testDanhGia = danhGiaList.get(danhGiaList.size() - 1);
        assertThat(testDanhGia.getDiemdanhgia()).isEqualTo(UPDATED_DIEMDANHGIA);
        assertThat(testDanhGia.getGhichu()).isEqualTo(UPDATED_GHICHU);
        assertThat(testDanhGia.getNgayCn()).isEqualTo(UPDATED_NGAY_CN);
        assertThat(testDanhGia.getNguoiCn()).isEqualTo(UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void updateNonExistingDanhGia() throws Exception {
        int databaseSizeBeforeUpdate = danhGiaRepository.findAll().size();

        // Create the DanhGia
        DanhGiaDTO danhGiaDTO = danhGiaMapper.toDto(danhGia);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhGiaMockMvc.perform(put("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGiaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhGia in the database
        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDanhGia() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        int databaseSizeBeforeDelete = danhGiaRepository.findAll().size();

        // Delete the danhGia
        restDanhGiaMockMvc.perform(delete("/api/danh-gias/{id}", danhGia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
