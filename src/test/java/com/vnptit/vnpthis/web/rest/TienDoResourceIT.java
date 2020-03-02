package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.TienDo;
import com.vnptit.vnpthis.domain.UpFile;
import com.vnptit.vnpthis.domain.DeTai;
import com.vnptit.vnpthis.repository.TienDoRepository;
import com.vnptit.vnpthis.service.TienDoService;
import com.vnptit.vnpthis.service.dto.TienDoDTO;
import com.vnptit.vnpthis.service.mapper.TienDoMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.service.dto.TienDoCriteria;
import com.vnptit.vnpthis.service.TienDoQueryService;

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
 * Integration tests for the {@link TienDoResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class TienDoResourceIT {

    private static final Integer DEFAULT_TYLE_HOANTHANH = 1;
    private static final Integer UPDATED_TYLE_HOANTHANH = 2;
    private static final Integer SMALLER_TYLE_HOANTHANH = 1 - 1;

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_CN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_CN = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_NGUOI_CN = 1;
    private static final Integer UPDATED_NGUOI_CN = 2;
    private static final Integer SMALLER_NGUOI_CN = 1 - 1;

    @Autowired
    private TienDoRepository tienDoRepository;

    @Autowired
    private TienDoMapper tienDoMapper;

    @Autowired
    private TienDoService tienDoService;

    @Autowired
    private TienDoQueryService tienDoQueryService;

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

    private MockMvc restTienDoMockMvc;

    private TienDo tienDo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TienDoResource tienDoResource = new TienDoResource(tienDoService, tienDoQueryService);
        this.restTienDoMockMvc = MockMvcBuilders.standaloneSetup(tienDoResource)
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
    public static TienDo createEntity(EntityManager em) {
        TienDo tienDo = new TienDo()
            .tyleHoanthanh(DEFAULT_TYLE_HOANTHANH)
            .mota(DEFAULT_MOTA)
            .ngayCn(DEFAULT_NGAY_CN)
            .nguoiCn(DEFAULT_NGUOI_CN);
        return tienDo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TienDo createUpdatedEntity(EntityManager em) {
        TienDo tienDo = new TienDo()
            .tyleHoanthanh(UPDATED_TYLE_HOANTHANH)
            .mota(UPDATED_MOTA)
            .ngayCn(UPDATED_NGAY_CN)
            .nguoiCn(UPDATED_NGUOI_CN);
        return tienDo;
    }

    @BeforeEach
    public void initTest() {
        tienDo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTienDo() throws Exception {
        int databaseSizeBeforeCreate = tienDoRepository.findAll().size();

        // Create the TienDo
        TienDoDTO tienDoDTO = tienDoMapper.toDto(tienDo);
        restTienDoMockMvc.perform(post("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDoDTO)))
            .andExpect(status().isCreated());

        // Validate the TienDo in the database
        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeCreate + 1);
        TienDo testTienDo = tienDoList.get(tienDoList.size() - 1);
        assertThat(testTienDo.getTyleHoanthanh()).isEqualTo(DEFAULT_TYLE_HOANTHANH);
        assertThat(testTienDo.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testTienDo.getNgayCn()).isEqualTo(DEFAULT_NGAY_CN);
        assertThat(testTienDo.getNguoiCn()).isEqualTo(DEFAULT_NGUOI_CN);
    }

    @Test
    @Transactional
    public void createTienDoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tienDoRepository.findAll().size();

        // Create the TienDo with an existing ID
        tienDo.setId(1L);
        TienDoDTO tienDoDTO = tienDoMapper.toDto(tienDo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTienDoMockMvc.perform(post("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TienDo in the database
        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTyleHoanthanhIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienDoRepository.findAll().size();
        // set the field null
        tienDo.setTyleHoanthanh(null);

        // Create the TienDo, which fails.
        TienDoDTO tienDoDTO = tienDoMapper.toDto(tienDo);

        restTienDoMockMvc.perform(post("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDoDTO)))
            .andExpect(status().isBadRequest());

        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienDoRepository.findAll().size();
        // set the field null
        tienDo.setMota(null);

        // Create the TienDo, which fails.
        TienDoDTO tienDoDTO = tienDoMapper.toDto(tienDo);

        restTienDoMockMvc.perform(post("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDoDTO)))
            .andExpect(status().isBadRequest());

        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgayCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienDoRepository.findAll().size();
        // set the field null
        tienDo.setNgayCn(null);

        // Create the TienDo, which fails.
        TienDoDTO tienDoDTO = tienDoMapper.toDto(tienDo);

        restTienDoMockMvc.perform(post("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDoDTO)))
            .andExpect(status().isBadRequest());

        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNguoiCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienDoRepository.findAll().size();
        // set the field null
        tienDo.setNguoiCn(null);

        // Create the TienDo, which fails.
        TienDoDTO tienDoDTO = tienDoMapper.toDto(tienDo);

        restTienDoMockMvc.perform(post("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDoDTO)))
            .andExpect(status().isBadRequest());

        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTienDos() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList
        restTienDoMockMvc.perform(get("/api/tien-dos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tienDo.getId().intValue())))
            .andExpect(jsonPath("$.[*].tyleHoanthanh").value(hasItem(DEFAULT_TYLE_HOANTHANH)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)));
    }
    
    @Test
    @Transactional
    public void getTienDo() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get the tienDo
        restTienDoMockMvc.perform(get("/api/tien-dos/{id}", tienDo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tienDo.getId().intValue()))
            .andExpect(jsonPath("$.tyleHoanthanh").value(DEFAULT_TYLE_HOANTHANH))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.ngayCn").value(DEFAULT_NGAY_CN.toString()))
            .andExpect(jsonPath("$.nguoiCn").value(DEFAULT_NGUOI_CN));
    }


    @Test
    @Transactional
    public void getTienDosByIdFiltering() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        Long id = tienDo.getId();

        defaultTienDoShouldBeFound("id.equals=" + id);
        defaultTienDoShouldNotBeFound("id.notEquals=" + id);

        defaultTienDoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTienDoShouldNotBeFound("id.greaterThan=" + id);

        defaultTienDoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTienDoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTienDosByTyleHoanthanhIsEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where tyleHoanthanh equals to DEFAULT_TYLE_HOANTHANH
        defaultTienDoShouldBeFound("tyleHoanthanh.equals=" + DEFAULT_TYLE_HOANTHANH);

        // Get all the tienDoList where tyleHoanthanh equals to UPDATED_TYLE_HOANTHANH
        defaultTienDoShouldNotBeFound("tyleHoanthanh.equals=" + UPDATED_TYLE_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllTienDosByTyleHoanthanhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where tyleHoanthanh not equals to DEFAULT_TYLE_HOANTHANH
        defaultTienDoShouldNotBeFound("tyleHoanthanh.notEquals=" + DEFAULT_TYLE_HOANTHANH);

        // Get all the tienDoList where tyleHoanthanh not equals to UPDATED_TYLE_HOANTHANH
        defaultTienDoShouldBeFound("tyleHoanthanh.notEquals=" + UPDATED_TYLE_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllTienDosByTyleHoanthanhIsInShouldWork() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where tyleHoanthanh in DEFAULT_TYLE_HOANTHANH or UPDATED_TYLE_HOANTHANH
        defaultTienDoShouldBeFound("tyleHoanthanh.in=" + DEFAULT_TYLE_HOANTHANH + "," + UPDATED_TYLE_HOANTHANH);

        // Get all the tienDoList where tyleHoanthanh equals to UPDATED_TYLE_HOANTHANH
        defaultTienDoShouldNotBeFound("tyleHoanthanh.in=" + UPDATED_TYLE_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllTienDosByTyleHoanthanhIsNullOrNotNull() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where tyleHoanthanh is not null
        defaultTienDoShouldBeFound("tyleHoanthanh.specified=true");

        // Get all the tienDoList where tyleHoanthanh is null
        defaultTienDoShouldNotBeFound("tyleHoanthanh.specified=false");
    }

    @Test
    @Transactional
    public void getAllTienDosByTyleHoanthanhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where tyleHoanthanh is greater than or equal to DEFAULT_TYLE_HOANTHANH
        defaultTienDoShouldBeFound("tyleHoanthanh.greaterThanOrEqual=" + DEFAULT_TYLE_HOANTHANH);

        // Get all the tienDoList where tyleHoanthanh is greater than or equal to UPDATED_TYLE_HOANTHANH
        defaultTienDoShouldNotBeFound("tyleHoanthanh.greaterThanOrEqual=" + UPDATED_TYLE_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllTienDosByTyleHoanthanhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where tyleHoanthanh is less than or equal to DEFAULT_TYLE_HOANTHANH
        defaultTienDoShouldBeFound("tyleHoanthanh.lessThanOrEqual=" + DEFAULT_TYLE_HOANTHANH);

        // Get all the tienDoList where tyleHoanthanh is less than or equal to SMALLER_TYLE_HOANTHANH
        defaultTienDoShouldNotBeFound("tyleHoanthanh.lessThanOrEqual=" + SMALLER_TYLE_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllTienDosByTyleHoanthanhIsLessThanSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where tyleHoanthanh is less than DEFAULT_TYLE_HOANTHANH
        defaultTienDoShouldNotBeFound("tyleHoanthanh.lessThan=" + DEFAULT_TYLE_HOANTHANH);

        // Get all the tienDoList where tyleHoanthanh is less than UPDATED_TYLE_HOANTHANH
        defaultTienDoShouldBeFound("tyleHoanthanh.lessThan=" + UPDATED_TYLE_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllTienDosByTyleHoanthanhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where tyleHoanthanh is greater than DEFAULT_TYLE_HOANTHANH
        defaultTienDoShouldNotBeFound("tyleHoanthanh.greaterThan=" + DEFAULT_TYLE_HOANTHANH);

        // Get all the tienDoList where tyleHoanthanh is greater than SMALLER_TYLE_HOANTHANH
        defaultTienDoShouldBeFound("tyleHoanthanh.greaterThan=" + SMALLER_TYLE_HOANTHANH);
    }


    @Test
    @Transactional
    public void getAllTienDosByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where mota equals to DEFAULT_MOTA
        defaultTienDoShouldBeFound("mota.equals=" + DEFAULT_MOTA);

        // Get all the tienDoList where mota equals to UPDATED_MOTA
        defaultTienDoShouldNotBeFound("mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllTienDosByMotaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where mota not equals to DEFAULT_MOTA
        defaultTienDoShouldNotBeFound("mota.notEquals=" + DEFAULT_MOTA);

        // Get all the tienDoList where mota not equals to UPDATED_MOTA
        defaultTienDoShouldBeFound("mota.notEquals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllTienDosByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where mota in DEFAULT_MOTA or UPDATED_MOTA
        defaultTienDoShouldBeFound("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA);

        // Get all the tienDoList where mota equals to UPDATED_MOTA
        defaultTienDoShouldNotBeFound("mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllTienDosByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where mota is not null
        defaultTienDoShouldBeFound("mota.specified=true");

        // Get all the tienDoList where mota is null
        defaultTienDoShouldNotBeFound("mota.specified=false");
    }
                @Test
    @Transactional
    public void getAllTienDosByMotaContainsSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where mota contains DEFAULT_MOTA
        defaultTienDoShouldBeFound("mota.contains=" + DEFAULT_MOTA);

        // Get all the tienDoList where mota contains UPDATED_MOTA
        defaultTienDoShouldNotBeFound("mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllTienDosByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where mota does not contain DEFAULT_MOTA
        defaultTienDoShouldNotBeFound("mota.doesNotContain=" + DEFAULT_MOTA);

        // Get all the tienDoList where mota does not contain UPDATED_MOTA
        defaultTienDoShouldBeFound("mota.doesNotContain=" + UPDATED_MOTA);
    }


    @Test
    @Transactional
    public void getAllTienDosByNgayCnIsEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where ngayCn equals to DEFAULT_NGAY_CN
        defaultTienDoShouldBeFound("ngayCn.equals=" + DEFAULT_NGAY_CN);

        // Get all the tienDoList where ngayCn equals to UPDATED_NGAY_CN
        defaultTienDoShouldNotBeFound("ngayCn.equals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNgayCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where ngayCn not equals to DEFAULT_NGAY_CN
        defaultTienDoShouldNotBeFound("ngayCn.notEquals=" + DEFAULT_NGAY_CN);

        // Get all the tienDoList where ngayCn not equals to UPDATED_NGAY_CN
        defaultTienDoShouldBeFound("ngayCn.notEquals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNgayCnIsInShouldWork() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where ngayCn in DEFAULT_NGAY_CN or UPDATED_NGAY_CN
        defaultTienDoShouldBeFound("ngayCn.in=" + DEFAULT_NGAY_CN + "," + UPDATED_NGAY_CN);

        // Get all the tienDoList where ngayCn equals to UPDATED_NGAY_CN
        defaultTienDoShouldNotBeFound("ngayCn.in=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNgayCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where ngayCn is not null
        defaultTienDoShouldBeFound("ngayCn.specified=true");

        // Get all the tienDoList where ngayCn is null
        defaultTienDoShouldNotBeFound("ngayCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllTienDosByNgayCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where ngayCn is greater than or equal to DEFAULT_NGAY_CN
        defaultTienDoShouldBeFound("ngayCn.greaterThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the tienDoList where ngayCn is greater than or equal to UPDATED_NGAY_CN
        defaultTienDoShouldNotBeFound("ngayCn.greaterThanOrEqual=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNgayCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where ngayCn is less than or equal to DEFAULT_NGAY_CN
        defaultTienDoShouldBeFound("ngayCn.lessThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the tienDoList where ngayCn is less than or equal to SMALLER_NGAY_CN
        defaultTienDoShouldNotBeFound("ngayCn.lessThanOrEqual=" + SMALLER_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNgayCnIsLessThanSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where ngayCn is less than DEFAULT_NGAY_CN
        defaultTienDoShouldNotBeFound("ngayCn.lessThan=" + DEFAULT_NGAY_CN);

        // Get all the tienDoList where ngayCn is less than UPDATED_NGAY_CN
        defaultTienDoShouldBeFound("ngayCn.lessThan=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNgayCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where ngayCn is greater than DEFAULT_NGAY_CN
        defaultTienDoShouldNotBeFound("ngayCn.greaterThan=" + DEFAULT_NGAY_CN);

        // Get all the tienDoList where ngayCn is greater than SMALLER_NGAY_CN
        defaultTienDoShouldBeFound("ngayCn.greaterThan=" + SMALLER_NGAY_CN);
    }


    @Test
    @Transactional
    public void getAllTienDosByNguoiCnIsEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where nguoiCn equals to DEFAULT_NGUOI_CN
        defaultTienDoShouldBeFound("nguoiCn.equals=" + DEFAULT_NGUOI_CN);

        // Get all the tienDoList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultTienDoShouldNotBeFound("nguoiCn.equals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNguoiCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where nguoiCn not equals to DEFAULT_NGUOI_CN
        defaultTienDoShouldNotBeFound("nguoiCn.notEquals=" + DEFAULT_NGUOI_CN);

        // Get all the tienDoList where nguoiCn not equals to UPDATED_NGUOI_CN
        defaultTienDoShouldBeFound("nguoiCn.notEquals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNguoiCnIsInShouldWork() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where nguoiCn in DEFAULT_NGUOI_CN or UPDATED_NGUOI_CN
        defaultTienDoShouldBeFound("nguoiCn.in=" + DEFAULT_NGUOI_CN + "," + UPDATED_NGUOI_CN);

        // Get all the tienDoList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultTienDoShouldNotBeFound("nguoiCn.in=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNguoiCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where nguoiCn is not null
        defaultTienDoShouldBeFound("nguoiCn.specified=true");

        // Get all the tienDoList where nguoiCn is null
        defaultTienDoShouldNotBeFound("nguoiCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllTienDosByNguoiCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where nguoiCn is greater than or equal to DEFAULT_NGUOI_CN
        defaultTienDoShouldBeFound("nguoiCn.greaterThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the tienDoList where nguoiCn is greater than or equal to UPDATED_NGUOI_CN
        defaultTienDoShouldNotBeFound("nguoiCn.greaterThanOrEqual=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNguoiCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where nguoiCn is less than or equal to DEFAULT_NGUOI_CN
        defaultTienDoShouldBeFound("nguoiCn.lessThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the tienDoList where nguoiCn is less than or equal to SMALLER_NGUOI_CN
        defaultTienDoShouldNotBeFound("nguoiCn.lessThanOrEqual=" + SMALLER_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNguoiCnIsLessThanSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where nguoiCn is less than DEFAULT_NGUOI_CN
        defaultTienDoShouldNotBeFound("nguoiCn.lessThan=" + DEFAULT_NGUOI_CN);

        // Get all the tienDoList where nguoiCn is less than UPDATED_NGUOI_CN
        defaultTienDoShouldBeFound("nguoiCn.lessThan=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllTienDosByNguoiCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList where nguoiCn is greater than DEFAULT_NGUOI_CN
        defaultTienDoShouldNotBeFound("nguoiCn.greaterThan=" + DEFAULT_NGUOI_CN);

        // Get all the tienDoList where nguoiCn is greater than SMALLER_NGUOI_CN
        defaultTienDoShouldBeFound("nguoiCn.greaterThan=" + SMALLER_NGUOI_CN);
    }


    @Test
    @Transactional
    public void getAllTienDosByUpFileIsEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);
        UpFile upFile = UpFileResourceIT.createEntity(em);
        em.persist(upFile);
        em.flush();
        tienDo.addUpFile(upFile);
        tienDoRepository.saveAndFlush(tienDo);
        Long upFileId = upFile.getId();

        // Get all the tienDoList where upFile equals to upFileId
        defaultTienDoShouldBeFound("upFileId.equals=" + upFileId);

        // Get all the tienDoList where upFile equals to upFileId + 1
        defaultTienDoShouldNotBeFound("upFileId.equals=" + (upFileId + 1));
    }


    @Test
    @Transactional
    public void getAllTienDosByDeTaiIsEqualToSomething() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);
        DeTai deTai = DeTaiResourceIT.createEntity(em);
        em.persist(deTai);
        em.flush();
        tienDo.setDeTai(deTai);
        tienDoRepository.saveAndFlush(tienDo);
        Long deTaiId = deTai.getId();

        // Get all the tienDoList where deTai equals to deTaiId
        defaultTienDoShouldBeFound("deTaiId.equals=" + deTaiId);

        // Get all the tienDoList where deTai equals to deTaiId + 1
        defaultTienDoShouldNotBeFound("deTaiId.equals=" + (deTaiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTienDoShouldBeFound(String filter) throws Exception {
        restTienDoMockMvc.perform(get("/api/tien-dos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tienDo.getId().intValue())))
            .andExpect(jsonPath("$.[*].tyleHoanthanh").value(hasItem(DEFAULT_TYLE_HOANTHANH)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)));

        // Check, that the count call also returns 1
        restTienDoMockMvc.perform(get("/api/tien-dos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTienDoShouldNotBeFound(String filter) throws Exception {
        restTienDoMockMvc.perform(get("/api/tien-dos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTienDoMockMvc.perform(get("/api/tien-dos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTienDo() throws Exception {
        // Get the tienDo
        restTienDoMockMvc.perform(get("/api/tien-dos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTienDo() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        int databaseSizeBeforeUpdate = tienDoRepository.findAll().size();

        // Update the tienDo
        TienDo updatedTienDo = tienDoRepository.findById(tienDo.getId()).get();
        // Disconnect from session so that the updates on updatedTienDo are not directly saved in db
        em.detach(updatedTienDo);
        updatedTienDo
            .tyleHoanthanh(UPDATED_TYLE_HOANTHANH)
            .mota(UPDATED_MOTA)
            .ngayCn(UPDATED_NGAY_CN)
            .nguoiCn(UPDATED_NGUOI_CN);
        TienDoDTO tienDoDTO = tienDoMapper.toDto(updatedTienDo);

        restTienDoMockMvc.perform(put("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDoDTO)))
            .andExpect(status().isOk());

        // Validate the TienDo in the database
        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeUpdate);
        TienDo testTienDo = tienDoList.get(tienDoList.size() - 1);
        assertThat(testTienDo.getTyleHoanthanh()).isEqualTo(UPDATED_TYLE_HOANTHANH);
        assertThat(testTienDo.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testTienDo.getNgayCn()).isEqualTo(UPDATED_NGAY_CN);
        assertThat(testTienDo.getNguoiCn()).isEqualTo(UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void updateNonExistingTienDo() throws Exception {
        int databaseSizeBeforeUpdate = tienDoRepository.findAll().size();

        // Create the TienDo
        TienDoDTO tienDoDTO = tienDoMapper.toDto(tienDo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTienDoMockMvc.perform(put("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TienDo in the database
        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTienDo() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        int databaseSizeBeforeDelete = tienDoRepository.findAll().size();

        // Delete the tienDo
        restTienDoMockMvc.perform(delete("/api/tien-dos/{id}", tienDo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
