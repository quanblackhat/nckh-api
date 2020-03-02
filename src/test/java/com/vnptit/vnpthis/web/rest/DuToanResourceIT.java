package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.nckh.DuToan;
import com.vnptit.vnpthis.domain.nckh.DeTai;
import com.vnptit.vnpthis.repository.DuToanRepository;
import com.vnptit.vnpthis.service.DuToanService;
import com.vnptit.vnpthis.service.dto.DuToanDTO;
import com.vnptit.vnpthis.service.mapper.DuToanMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.service.dto.DuToanCriteria;
import com.vnptit.vnpthis.service.DuToanQueryService;

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
 * Integration tests for the {@link DuToanResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class DuToanResourceIT {

    private static final Integer DEFAULT_TIEN_DUKIEN = 1;
    private static final Integer UPDATED_TIEN_DUKIEN = 2;
    private static final Integer SMALLER_TIEN_DUKIEN = 1 - 1;

    private static final Integer DEFAULT_TIEN_HOANTHANH = 1;
    private static final Integer UPDATED_TIEN_HOANTHANH = 2;
    private static final Integer SMALLER_TIEN_HOANTHANH = 1 - 1;

    private static final String DEFAULT_GHICHU = "AAAAAAAAAA";
    private static final String UPDATED_GHICHU = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_CN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_CN = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_NGUOI_CN = 1;
    private static final Integer UPDATED_NGUOI_CN = 2;
    private static final Integer SMALLER_NGUOI_CN = 1 - 1;

    @Autowired
    private DuToanRepository duToanRepository;

    @Autowired
    private DuToanMapper duToanMapper;

    @Autowired
    private DuToanService duToanService;

    @Autowired
    private DuToanQueryService duToanQueryService;

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

    private MockMvc restDuToanMockMvc;

    private DuToan duToan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DuToanResource duToanResource = new DuToanResource(duToanService, duToanQueryService);
        this.restDuToanMockMvc = MockMvcBuilders.standaloneSetup(duToanResource)
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
    public static DuToan createEntity(EntityManager em) {
        DuToan duToan = new DuToan()
            .tienDukien(DEFAULT_TIEN_DUKIEN)
            .tienHoanthanh(DEFAULT_TIEN_HOANTHANH)
            .ghichu(DEFAULT_GHICHU)
            .ngayCn(DEFAULT_NGAY_CN)
            .nguoiCn(DEFAULT_NGUOI_CN);
        return duToan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuToan createUpdatedEntity(EntityManager em) {
        DuToan duToan = new DuToan()
            .tienDukien(UPDATED_TIEN_DUKIEN)
            .tienHoanthanh(UPDATED_TIEN_HOANTHANH)
            .ghichu(UPDATED_GHICHU)
            .ngayCn(UPDATED_NGAY_CN)
            .nguoiCn(UPDATED_NGUOI_CN);
        return duToan;
    }

    @BeforeEach
    public void initTest() {
        duToan = createEntity(em);
    }

    @Test
    @Transactional
    public void createDuToan() throws Exception {
        int databaseSizeBeforeCreate = duToanRepository.findAll().size();

        // Create the DuToan
        DuToanDTO duToanDTO = duToanMapper.toDto(duToan);
        restDuToanMockMvc.perform(post("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToanDTO)))
            .andExpect(status().isCreated());

        // Validate the DuToan in the database
        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeCreate + 1);
        DuToan testDuToan = duToanList.get(duToanList.size() - 1);
        assertThat(testDuToan.getTienDukien()).isEqualTo(DEFAULT_TIEN_DUKIEN);
        assertThat(testDuToan.getTienHoanthanh()).isEqualTo(DEFAULT_TIEN_HOANTHANH);
        assertThat(testDuToan.getGhichu()).isEqualTo(DEFAULT_GHICHU);
        assertThat(testDuToan.getNgayCn()).isEqualTo(DEFAULT_NGAY_CN);
        assertThat(testDuToan.getNguoiCn()).isEqualTo(DEFAULT_NGUOI_CN);
    }

    @Test
    @Transactional
    public void createDuToanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = duToanRepository.findAll().size();

        // Create the DuToan with an existing ID
        duToan.setId(1L);
        DuToanDTO duToanDTO = duToanMapper.toDto(duToan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDuToanMockMvc.perform(post("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DuToan in the database
        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTienDukienIsRequired() throws Exception {
        int databaseSizeBeforeTest = duToanRepository.findAll().size();
        // set the field null
        duToan.setTienDukien(null);

        // Create the DuToan, which fails.
        DuToanDTO duToanDTO = duToanMapper.toDto(duToan);

        restDuToanMockMvc.perform(post("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToanDTO)))
            .andExpect(status().isBadRequest());

        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTienHoanthanhIsRequired() throws Exception {
        int databaseSizeBeforeTest = duToanRepository.findAll().size();
        // set the field null
        duToan.setTienHoanthanh(null);

        // Create the DuToan, which fails.
        DuToanDTO duToanDTO = duToanMapper.toDto(duToan);

        restDuToanMockMvc.perform(post("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToanDTO)))
            .andExpect(status().isBadRequest());

        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGhichuIsRequired() throws Exception {
        int databaseSizeBeforeTest = duToanRepository.findAll().size();
        // set the field null
        duToan.setGhichu(null);

        // Create the DuToan, which fails.
        DuToanDTO duToanDTO = duToanMapper.toDto(duToan);

        restDuToanMockMvc.perform(post("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToanDTO)))
            .andExpect(status().isBadRequest());

        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgayCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = duToanRepository.findAll().size();
        // set the field null
        duToan.setNgayCn(null);

        // Create the DuToan, which fails.
        DuToanDTO duToanDTO = duToanMapper.toDto(duToan);

        restDuToanMockMvc.perform(post("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToanDTO)))
            .andExpect(status().isBadRequest());

        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNguoiCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = duToanRepository.findAll().size();
        // set the field null
        duToan.setNguoiCn(null);

        // Create the DuToan, which fails.
        DuToanDTO duToanDTO = duToanMapper.toDto(duToan);

        restDuToanMockMvc.perform(post("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToanDTO)))
            .andExpect(status().isBadRequest());

        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDuToans() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList
        restDuToanMockMvc.perform(get("/api/du-toans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(duToan.getId().intValue())))
            .andExpect(jsonPath("$.[*].tienDukien").value(hasItem(DEFAULT_TIEN_DUKIEN)))
            .andExpect(jsonPath("$.[*].tienHoanthanh").value(hasItem(DEFAULT_TIEN_HOANTHANH)))
            .andExpect(jsonPath("$.[*].ghichu").value(hasItem(DEFAULT_GHICHU)))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)));
    }

    @Test
    @Transactional
    public void getDuToan() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get the duToan
        restDuToanMockMvc.perform(get("/api/du-toans/{id}", duToan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(duToan.getId().intValue()))
            .andExpect(jsonPath("$.tienDukien").value(DEFAULT_TIEN_DUKIEN))
            .andExpect(jsonPath("$.tienHoanthanh").value(DEFAULT_TIEN_HOANTHANH))
            .andExpect(jsonPath("$.ghichu").value(DEFAULT_GHICHU))
            .andExpect(jsonPath("$.ngayCn").value(DEFAULT_NGAY_CN.toString()))
            .andExpect(jsonPath("$.nguoiCn").value(DEFAULT_NGUOI_CN));
    }


    @Test
    @Transactional
    public void getDuToansByIdFiltering() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        Long id = duToan.getId();

        defaultDuToanShouldBeFound("id.equals=" + id);
        defaultDuToanShouldNotBeFound("id.notEquals=" + id);

        defaultDuToanShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDuToanShouldNotBeFound("id.greaterThan=" + id);

        defaultDuToanShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDuToanShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDuToansByTienDukienIsEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienDukien equals to DEFAULT_TIEN_DUKIEN
        defaultDuToanShouldBeFound("tienDukien.equals=" + DEFAULT_TIEN_DUKIEN);

        // Get all the duToanList where tienDukien equals to UPDATED_TIEN_DUKIEN
        defaultDuToanShouldNotBeFound("tienDukien.equals=" + UPDATED_TIEN_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienDukienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienDukien not equals to DEFAULT_TIEN_DUKIEN
        defaultDuToanShouldNotBeFound("tienDukien.notEquals=" + DEFAULT_TIEN_DUKIEN);

        // Get all the duToanList where tienDukien not equals to UPDATED_TIEN_DUKIEN
        defaultDuToanShouldBeFound("tienDukien.notEquals=" + UPDATED_TIEN_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienDukienIsInShouldWork() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienDukien in DEFAULT_TIEN_DUKIEN or UPDATED_TIEN_DUKIEN
        defaultDuToanShouldBeFound("tienDukien.in=" + DEFAULT_TIEN_DUKIEN + "," + UPDATED_TIEN_DUKIEN);

        // Get all the duToanList where tienDukien equals to UPDATED_TIEN_DUKIEN
        defaultDuToanShouldNotBeFound("tienDukien.in=" + UPDATED_TIEN_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienDukienIsNullOrNotNull() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienDukien is not null
        defaultDuToanShouldBeFound("tienDukien.specified=true");

        // Get all the duToanList where tienDukien is null
        defaultDuToanShouldNotBeFound("tienDukien.specified=false");
    }

    @Test
    @Transactional
    public void getAllDuToansByTienDukienIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienDukien is greater than or equal to DEFAULT_TIEN_DUKIEN
        defaultDuToanShouldBeFound("tienDukien.greaterThanOrEqual=" + DEFAULT_TIEN_DUKIEN);

        // Get all the duToanList where tienDukien is greater than or equal to UPDATED_TIEN_DUKIEN
        defaultDuToanShouldNotBeFound("tienDukien.greaterThanOrEqual=" + UPDATED_TIEN_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienDukienIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienDukien is less than or equal to DEFAULT_TIEN_DUKIEN
        defaultDuToanShouldBeFound("tienDukien.lessThanOrEqual=" + DEFAULT_TIEN_DUKIEN);

        // Get all the duToanList where tienDukien is less than or equal to SMALLER_TIEN_DUKIEN
        defaultDuToanShouldNotBeFound("tienDukien.lessThanOrEqual=" + SMALLER_TIEN_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienDukienIsLessThanSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienDukien is less than DEFAULT_TIEN_DUKIEN
        defaultDuToanShouldNotBeFound("tienDukien.lessThan=" + DEFAULT_TIEN_DUKIEN);

        // Get all the duToanList where tienDukien is less than UPDATED_TIEN_DUKIEN
        defaultDuToanShouldBeFound("tienDukien.lessThan=" + UPDATED_TIEN_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienDukienIsGreaterThanSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienDukien is greater than DEFAULT_TIEN_DUKIEN
        defaultDuToanShouldNotBeFound("tienDukien.greaterThan=" + DEFAULT_TIEN_DUKIEN);

        // Get all the duToanList where tienDukien is greater than SMALLER_TIEN_DUKIEN
        defaultDuToanShouldBeFound("tienDukien.greaterThan=" + SMALLER_TIEN_DUKIEN);
    }


    @Test
    @Transactional
    public void getAllDuToansByTienHoanthanhIsEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienHoanthanh equals to DEFAULT_TIEN_HOANTHANH
        defaultDuToanShouldBeFound("tienHoanthanh.equals=" + DEFAULT_TIEN_HOANTHANH);

        // Get all the duToanList where tienHoanthanh equals to UPDATED_TIEN_HOANTHANH
        defaultDuToanShouldNotBeFound("tienHoanthanh.equals=" + UPDATED_TIEN_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienHoanthanhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienHoanthanh not equals to DEFAULT_TIEN_HOANTHANH
        defaultDuToanShouldNotBeFound("tienHoanthanh.notEquals=" + DEFAULT_TIEN_HOANTHANH);

        // Get all the duToanList where tienHoanthanh not equals to UPDATED_TIEN_HOANTHANH
        defaultDuToanShouldBeFound("tienHoanthanh.notEquals=" + UPDATED_TIEN_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienHoanthanhIsInShouldWork() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienHoanthanh in DEFAULT_TIEN_HOANTHANH or UPDATED_TIEN_HOANTHANH
        defaultDuToanShouldBeFound("tienHoanthanh.in=" + DEFAULT_TIEN_HOANTHANH + "," + UPDATED_TIEN_HOANTHANH);

        // Get all the duToanList where tienHoanthanh equals to UPDATED_TIEN_HOANTHANH
        defaultDuToanShouldNotBeFound("tienHoanthanh.in=" + UPDATED_TIEN_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienHoanthanhIsNullOrNotNull() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienHoanthanh is not null
        defaultDuToanShouldBeFound("tienHoanthanh.specified=true");

        // Get all the duToanList where tienHoanthanh is null
        defaultDuToanShouldNotBeFound("tienHoanthanh.specified=false");
    }

    @Test
    @Transactional
    public void getAllDuToansByTienHoanthanhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienHoanthanh is greater than or equal to DEFAULT_TIEN_HOANTHANH
        defaultDuToanShouldBeFound("tienHoanthanh.greaterThanOrEqual=" + DEFAULT_TIEN_HOANTHANH);

        // Get all the duToanList where tienHoanthanh is greater than or equal to UPDATED_TIEN_HOANTHANH
        defaultDuToanShouldNotBeFound("tienHoanthanh.greaterThanOrEqual=" + UPDATED_TIEN_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienHoanthanhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienHoanthanh is less than or equal to DEFAULT_TIEN_HOANTHANH
        defaultDuToanShouldBeFound("tienHoanthanh.lessThanOrEqual=" + DEFAULT_TIEN_HOANTHANH);

        // Get all the duToanList where tienHoanthanh is less than or equal to SMALLER_TIEN_HOANTHANH
        defaultDuToanShouldNotBeFound("tienHoanthanh.lessThanOrEqual=" + SMALLER_TIEN_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienHoanthanhIsLessThanSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienHoanthanh is less than DEFAULT_TIEN_HOANTHANH
        defaultDuToanShouldNotBeFound("tienHoanthanh.lessThan=" + DEFAULT_TIEN_HOANTHANH);

        // Get all the duToanList where tienHoanthanh is less than UPDATED_TIEN_HOANTHANH
        defaultDuToanShouldBeFound("tienHoanthanh.lessThan=" + UPDATED_TIEN_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDuToansByTienHoanthanhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where tienHoanthanh is greater than DEFAULT_TIEN_HOANTHANH
        defaultDuToanShouldNotBeFound("tienHoanthanh.greaterThan=" + DEFAULT_TIEN_HOANTHANH);

        // Get all the duToanList where tienHoanthanh is greater than SMALLER_TIEN_HOANTHANH
        defaultDuToanShouldBeFound("tienHoanthanh.greaterThan=" + SMALLER_TIEN_HOANTHANH);
    }


    @Test
    @Transactional
    public void getAllDuToansByGhichuIsEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ghichu equals to DEFAULT_GHICHU
        defaultDuToanShouldBeFound("ghichu.equals=" + DEFAULT_GHICHU);

        // Get all the duToanList where ghichu equals to UPDATED_GHICHU
        defaultDuToanShouldNotBeFound("ghichu.equals=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDuToansByGhichuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ghichu not equals to DEFAULT_GHICHU
        defaultDuToanShouldNotBeFound("ghichu.notEquals=" + DEFAULT_GHICHU);

        // Get all the duToanList where ghichu not equals to UPDATED_GHICHU
        defaultDuToanShouldBeFound("ghichu.notEquals=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDuToansByGhichuIsInShouldWork() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ghichu in DEFAULT_GHICHU or UPDATED_GHICHU
        defaultDuToanShouldBeFound("ghichu.in=" + DEFAULT_GHICHU + "," + UPDATED_GHICHU);

        // Get all the duToanList where ghichu equals to UPDATED_GHICHU
        defaultDuToanShouldNotBeFound("ghichu.in=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDuToansByGhichuIsNullOrNotNull() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ghichu is not null
        defaultDuToanShouldBeFound("ghichu.specified=true");

        // Get all the duToanList where ghichu is null
        defaultDuToanShouldNotBeFound("ghichu.specified=false");
    }
                @Test
    @Transactional
    public void getAllDuToansByGhichuContainsSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ghichu contains DEFAULT_GHICHU
        defaultDuToanShouldBeFound("ghichu.contains=" + DEFAULT_GHICHU);

        // Get all the duToanList where ghichu contains UPDATED_GHICHU
        defaultDuToanShouldNotBeFound("ghichu.contains=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDuToansByGhichuNotContainsSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ghichu does not contain DEFAULT_GHICHU
        defaultDuToanShouldNotBeFound("ghichu.doesNotContain=" + DEFAULT_GHICHU);

        // Get all the duToanList where ghichu does not contain UPDATED_GHICHU
        defaultDuToanShouldBeFound("ghichu.doesNotContain=" + UPDATED_GHICHU);
    }


    @Test
    @Transactional
    public void getAllDuToansByNgayCnIsEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ngayCn equals to DEFAULT_NGAY_CN
        defaultDuToanShouldBeFound("ngayCn.equals=" + DEFAULT_NGAY_CN);

        // Get all the duToanList where ngayCn equals to UPDATED_NGAY_CN
        defaultDuToanShouldNotBeFound("ngayCn.equals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNgayCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ngayCn not equals to DEFAULT_NGAY_CN
        defaultDuToanShouldNotBeFound("ngayCn.notEquals=" + DEFAULT_NGAY_CN);

        // Get all the duToanList where ngayCn not equals to UPDATED_NGAY_CN
        defaultDuToanShouldBeFound("ngayCn.notEquals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNgayCnIsInShouldWork() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ngayCn in DEFAULT_NGAY_CN or UPDATED_NGAY_CN
        defaultDuToanShouldBeFound("ngayCn.in=" + DEFAULT_NGAY_CN + "," + UPDATED_NGAY_CN);

        // Get all the duToanList where ngayCn equals to UPDATED_NGAY_CN
        defaultDuToanShouldNotBeFound("ngayCn.in=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNgayCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ngayCn is not null
        defaultDuToanShouldBeFound("ngayCn.specified=true");

        // Get all the duToanList where ngayCn is null
        defaultDuToanShouldNotBeFound("ngayCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllDuToansByNgayCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ngayCn is greater than or equal to DEFAULT_NGAY_CN
        defaultDuToanShouldBeFound("ngayCn.greaterThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the duToanList where ngayCn is greater than or equal to UPDATED_NGAY_CN
        defaultDuToanShouldNotBeFound("ngayCn.greaterThanOrEqual=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNgayCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ngayCn is less than or equal to DEFAULT_NGAY_CN
        defaultDuToanShouldBeFound("ngayCn.lessThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the duToanList where ngayCn is less than or equal to SMALLER_NGAY_CN
        defaultDuToanShouldNotBeFound("ngayCn.lessThanOrEqual=" + SMALLER_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNgayCnIsLessThanSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ngayCn is less than DEFAULT_NGAY_CN
        defaultDuToanShouldNotBeFound("ngayCn.lessThan=" + DEFAULT_NGAY_CN);

        // Get all the duToanList where ngayCn is less than UPDATED_NGAY_CN
        defaultDuToanShouldBeFound("ngayCn.lessThan=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNgayCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where ngayCn is greater than DEFAULT_NGAY_CN
        defaultDuToanShouldNotBeFound("ngayCn.greaterThan=" + DEFAULT_NGAY_CN);

        // Get all the duToanList where ngayCn is greater than SMALLER_NGAY_CN
        defaultDuToanShouldBeFound("ngayCn.greaterThan=" + SMALLER_NGAY_CN);
    }


    @Test
    @Transactional
    public void getAllDuToansByNguoiCnIsEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where nguoiCn equals to DEFAULT_NGUOI_CN
        defaultDuToanShouldBeFound("nguoiCn.equals=" + DEFAULT_NGUOI_CN);

        // Get all the duToanList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultDuToanShouldNotBeFound("nguoiCn.equals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNguoiCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where nguoiCn not equals to DEFAULT_NGUOI_CN
        defaultDuToanShouldNotBeFound("nguoiCn.notEquals=" + DEFAULT_NGUOI_CN);

        // Get all the duToanList where nguoiCn not equals to UPDATED_NGUOI_CN
        defaultDuToanShouldBeFound("nguoiCn.notEquals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNguoiCnIsInShouldWork() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where nguoiCn in DEFAULT_NGUOI_CN or UPDATED_NGUOI_CN
        defaultDuToanShouldBeFound("nguoiCn.in=" + DEFAULT_NGUOI_CN + "," + UPDATED_NGUOI_CN);

        // Get all the duToanList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultDuToanShouldNotBeFound("nguoiCn.in=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNguoiCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where nguoiCn is not null
        defaultDuToanShouldBeFound("nguoiCn.specified=true");

        // Get all the duToanList where nguoiCn is null
        defaultDuToanShouldNotBeFound("nguoiCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllDuToansByNguoiCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where nguoiCn is greater than or equal to DEFAULT_NGUOI_CN
        defaultDuToanShouldBeFound("nguoiCn.greaterThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the duToanList where nguoiCn is greater than or equal to UPDATED_NGUOI_CN
        defaultDuToanShouldNotBeFound("nguoiCn.greaterThanOrEqual=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNguoiCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where nguoiCn is less than or equal to DEFAULT_NGUOI_CN
        defaultDuToanShouldBeFound("nguoiCn.lessThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the duToanList where nguoiCn is less than or equal to SMALLER_NGUOI_CN
        defaultDuToanShouldNotBeFound("nguoiCn.lessThanOrEqual=" + SMALLER_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNguoiCnIsLessThanSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where nguoiCn is less than DEFAULT_NGUOI_CN
        defaultDuToanShouldNotBeFound("nguoiCn.lessThan=" + DEFAULT_NGUOI_CN);

        // Get all the duToanList where nguoiCn is less than UPDATED_NGUOI_CN
        defaultDuToanShouldBeFound("nguoiCn.lessThan=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDuToansByNguoiCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList where nguoiCn is greater than DEFAULT_NGUOI_CN
        defaultDuToanShouldNotBeFound("nguoiCn.greaterThan=" + DEFAULT_NGUOI_CN);

        // Get all the duToanList where nguoiCn is greater than SMALLER_NGUOI_CN
        defaultDuToanShouldBeFound("nguoiCn.greaterThan=" + SMALLER_NGUOI_CN);
    }


    @Test
    @Transactional
    public void getAllDuToansByDeTaiIsEqualToSomething() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);
        DeTai deTai = DeTaiResourceIT.createEntity(em);
        em.persist(deTai);
        em.flush();
        duToan.setDeTai(deTai);
        duToanRepository.saveAndFlush(duToan);
        Long deTaiId = deTai.getId();

        // Get all the duToanList where deTai equals to deTaiId
        defaultDuToanShouldBeFound("deTaiId.equals=" + deTaiId);

        // Get all the duToanList where deTai equals to deTaiId + 1
        defaultDuToanShouldNotBeFound("deTaiId.equals=" + (deTaiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDuToanShouldBeFound(String filter) throws Exception {
        restDuToanMockMvc.perform(get("/api/du-toans?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(duToan.getId().intValue())))
            .andExpect(jsonPath("$.[*].tienDukien").value(hasItem(DEFAULT_TIEN_DUKIEN)))
            .andExpect(jsonPath("$.[*].tienHoanthanh").value(hasItem(DEFAULT_TIEN_HOANTHANH)))
            .andExpect(jsonPath("$.[*].ghichu").value(hasItem(DEFAULT_GHICHU)))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)));

        // Check, that the count call also returns 1
        restDuToanMockMvc.perform(get("/api/du-toans/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDuToanShouldNotBeFound(String filter) throws Exception {
        restDuToanMockMvc.perform(get("/api/du-toans?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDuToanMockMvc.perform(get("/api/du-toans/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDuToan() throws Exception {
        // Get the duToan
        restDuToanMockMvc.perform(get("/api/du-toans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDuToan() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        int databaseSizeBeforeUpdate = duToanRepository.findAll().size();

        // Update the duToan
        DuToan updatedDuToan = duToanRepository.findById(duToan.getId()).get();
        // Disconnect from session so that the updates on updatedDuToan are not directly saved in db
        em.detach(updatedDuToan);
        updatedDuToan
            .tienDukien(UPDATED_TIEN_DUKIEN)
            .tienHoanthanh(UPDATED_TIEN_HOANTHANH)
            .ghichu(UPDATED_GHICHU)
            .ngayCn(UPDATED_NGAY_CN)
            .nguoiCn(UPDATED_NGUOI_CN);
        DuToanDTO duToanDTO = duToanMapper.toDto(updatedDuToan);

        restDuToanMockMvc.perform(put("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToanDTO)))
            .andExpect(status().isOk());

        // Validate the DuToan in the database
        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeUpdate);
        DuToan testDuToan = duToanList.get(duToanList.size() - 1);
        assertThat(testDuToan.getTienDukien()).isEqualTo(UPDATED_TIEN_DUKIEN);
        assertThat(testDuToan.getTienHoanthanh()).isEqualTo(UPDATED_TIEN_HOANTHANH);
        assertThat(testDuToan.getGhichu()).isEqualTo(UPDATED_GHICHU);
        assertThat(testDuToan.getNgayCn()).isEqualTo(UPDATED_NGAY_CN);
        assertThat(testDuToan.getNguoiCn()).isEqualTo(UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void updateNonExistingDuToan() throws Exception {
        int databaseSizeBeforeUpdate = duToanRepository.findAll().size();

        // Create the DuToan
        DuToanDTO duToanDTO = duToanMapper.toDto(duToan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuToanMockMvc.perform(put("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DuToan in the database
        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDuToan() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        int databaseSizeBeforeDelete = duToanRepository.findAll().size();

        // Delete the duToan
        restDuToanMockMvc.perform(delete("/api/du-toans/{id}", duToan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
