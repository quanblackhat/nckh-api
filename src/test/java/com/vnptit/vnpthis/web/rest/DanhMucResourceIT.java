package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.DanhMuc;
import com.vnptit.vnpthis.domain.LoaiDanhMuc;
import com.vnptit.vnpthis.repository.DanhMucRepository;
import com.vnptit.vnpthis.service.DanhMucService;
import com.vnptit.vnpthis.service.dto.DanhMucDTO;
import com.vnptit.vnpthis.service.mapper.DanhMucMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.service.dto.DanhMucCriteria;
import com.vnptit.vnpthis.service.DanhMucQueryService;

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
 * Integration tests for the {@link DanhMucResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class DanhMucResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAYTAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYTAO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYTAO = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_THUTU = 1;
    private static final Integer UPDATED_THUTU = 2;
    private static final Integer SMALLER_THUTU = 1 - 1;

    @Autowired
    private DanhMucRepository danhMucRepository;

    @Autowired
    private DanhMucMapper danhMucMapper;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private DanhMucQueryService danhMucQueryService;

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

    private MockMvc restDanhMucMockMvc;

    private DanhMuc danhMuc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DanhMucResource danhMucResource = new DanhMucResource(danhMucService, danhMucQueryService);
        this.restDanhMucMockMvc = MockMvcBuilders.standaloneSetup(danhMucResource)
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
    public static DanhMuc createEntity(EntityManager em) {
        DanhMuc danhMuc = new DanhMuc()
            .ma(DEFAULT_MA)
            .ngaytao(DEFAULT_NGAYTAO)
            .sudung(DEFAULT_SUDUNG)
            .ten(DEFAULT_TEN)
            .thutu(DEFAULT_THUTU);
        return danhMuc;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMuc createUpdatedEntity(EntityManager em) {
        DanhMuc danhMuc = new DanhMuc()
            .ma(UPDATED_MA)
            .ngaytao(UPDATED_NGAYTAO)
            .sudung(UPDATED_SUDUNG)
            .ten(UPDATED_TEN)
            .thutu(UPDATED_THUTU);
        return danhMuc;
    }

    @BeforeEach
    public void initTest() {
        danhMuc = createEntity(em);
    }

    @Test
    @Transactional
    public void createDanhMuc() throws Exception {
        int databaseSizeBeforeCreate = danhMucRepository.findAll().size();

        // Create the DanhMuc
        DanhMucDTO danhMucDTO = danhMucMapper.toDto(danhMuc);
        restDanhMucMockMvc.perform(post("/api/danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhMucDTO)))
            .andExpect(status().isCreated());

        // Validate the DanhMuc in the database
        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        assertThat(danhMucList).hasSize(databaseSizeBeforeCreate + 1);
        DanhMuc testDanhMuc = danhMucList.get(danhMucList.size() - 1);
        assertThat(testDanhMuc.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDanhMuc.getNgaytao()).isEqualTo(DEFAULT_NGAYTAO);
        assertThat(testDanhMuc.getSudung()).isEqualTo(DEFAULT_SUDUNG);
        assertThat(testDanhMuc.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testDanhMuc.getThutu()).isEqualTo(DEFAULT_THUTU);
    }

    @Test
    @Transactional
    public void createDanhMucWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = danhMucRepository.findAll().size();

        // Create the DanhMuc with an existing ID
        danhMuc.setId(1L);
        DanhMucDTO danhMucDTO = danhMucMapper.toDto(danhMuc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucMockMvc.perform(post("/api/danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhMucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMuc in the database
        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        assertThat(danhMucList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhMucRepository.findAll().size();
        // set the field null
        danhMuc.setMa(null);

        // Create the DanhMuc, which fails.
        DanhMucDTO danhMucDTO = danhMucMapper.toDto(danhMuc);

        restDanhMucMockMvc.perform(post("/api/danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhMucDTO)))
            .andExpect(status().isBadRequest());

        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        assertThat(danhMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgaytaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhMucRepository.findAll().size();
        // set the field null
        danhMuc.setNgaytao(null);

        // Create the DanhMuc, which fails.
        DanhMucDTO danhMucDTO = danhMucMapper.toDto(danhMuc);

        restDanhMucMockMvc.perform(post("/api/danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhMucDTO)))
            .andExpect(status().isBadRequest());

        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        assertThat(danhMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSudungIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhMucRepository.findAll().size();
        // set the field null
        danhMuc.setSudung(null);

        // Create the DanhMuc, which fails.
        DanhMucDTO danhMucDTO = danhMucMapper.toDto(danhMuc);

        restDanhMucMockMvc.perform(post("/api/danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhMucDTO)))
            .andExpect(status().isBadRequest());

        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        assertThat(danhMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTenIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhMucRepository.findAll().size();
        // set the field null
        danhMuc.setTen(null);

        // Create the DanhMuc, which fails.
        DanhMucDTO danhMucDTO = danhMucMapper.toDto(danhMuc);

        restDanhMucMockMvc.perform(post("/api/danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhMucDTO)))
            .andExpect(status().isBadRequest());

        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        assertThat(danhMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkThutuIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhMucRepository.findAll().size();
        // set the field null
        danhMuc.setThutu(null);

        // Create the DanhMuc, which fails.
        DanhMucDTO danhMucDTO = danhMucMapper.toDto(danhMuc);

        restDanhMucMockMvc.perform(post("/api/danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhMucDTO)))
            .andExpect(status().isBadRequest());

        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        assertThat(danhMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDanhMucs() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList
        restDanhMucMockMvc.perform(get("/api/danh-mucs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ngaytao").value(hasItem(DEFAULT_NGAYTAO.toString())))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].thutu").value(hasItem(DEFAULT_THUTU)));
    }
    
    @Test
    @Transactional
    public void getDanhMuc() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get the danhMuc
        restDanhMucMockMvc.perform(get("/api/danh-mucs/{id}", danhMuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(danhMuc.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ngaytao").value(DEFAULT_NGAYTAO.toString()))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.thutu").value(DEFAULT_THUTU));
    }


    @Test
    @Transactional
    public void getDanhMucsByIdFiltering() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        Long id = danhMuc.getId();

        defaultDanhMucShouldBeFound("id.equals=" + id);
        defaultDanhMucShouldNotBeFound("id.notEquals=" + id);

        defaultDanhMucShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDanhMucShouldNotBeFound("id.greaterThan=" + id);

        defaultDanhMucShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDanhMucShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDanhMucsByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ma equals to DEFAULT_MA
        defaultDanhMucShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the danhMucList where ma equals to UPDATED_MA
        defaultDanhMucShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ma not equals to DEFAULT_MA
        defaultDanhMucShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the danhMucList where ma not equals to UPDATED_MA
        defaultDanhMucShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByMaIsInShouldWork() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ma in DEFAULT_MA or UPDATED_MA
        defaultDanhMucShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the danhMucList where ma equals to UPDATED_MA
        defaultDanhMucShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ma is not null
        defaultDanhMucShouldBeFound("ma.specified=true");

        // Get all the danhMucList where ma is null
        defaultDanhMucShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhMucsByMaContainsSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ma contains DEFAULT_MA
        defaultDanhMucShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the danhMucList where ma contains UPDATED_MA
        defaultDanhMucShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByMaNotContainsSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ma does not contain DEFAULT_MA
        defaultDanhMucShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the danhMucList where ma does not contain UPDATED_MA
        defaultDanhMucShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllDanhMucsByNgaytaoIsEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ngaytao equals to DEFAULT_NGAYTAO
        defaultDanhMucShouldBeFound("ngaytao.equals=" + DEFAULT_NGAYTAO);

        // Get all the danhMucList where ngaytao equals to UPDATED_NGAYTAO
        defaultDanhMucShouldNotBeFound("ngaytao.equals=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByNgaytaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ngaytao not equals to DEFAULT_NGAYTAO
        defaultDanhMucShouldNotBeFound("ngaytao.notEquals=" + DEFAULT_NGAYTAO);

        // Get all the danhMucList where ngaytao not equals to UPDATED_NGAYTAO
        defaultDanhMucShouldBeFound("ngaytao.notEquals=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByNgaytaoIsInShouldWork() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ngaytao in DEFAULT_NGAYTAO or UPDATED_NGAYTAO
        defaultDanhMucShouldBeFound("ngaytao.in=" + DEFAULT_NGAYTAO + "," + UPDATED_NGAYTAO);

        // Get all the danhMucList where ngaytao equals to UPDATED_NGAYTAO
        defaultDanhMucShouldNotBeFound("ngaytao.in=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByNgaytaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ngaytao is not null
        defaultDanhMucShouldBeFound("ngaytao.specified=true");

        // Get all the danhMucList where ngaytao is null
        defaultDanhMucShouldNotBeFound("ngaytao.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhMucsByNgaytaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ngaytao is greater than or equal to DEFAULT_NGAYTAO
        defaultDanhMucShouldBeFound("ngaytao.greaterThanOrEqual=" + DEFAULT_NGAYTAO);

        // Get all the danhMucList where ngaytao is greater than or equal to UPDATED_NGAYTAO
        defaultDanhMucShouldNotBeFound("ngaytao.greaterThanOrEqual=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByNgaytaoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ngaytao is less than or equal to DEFAULT_NGAYTAO
        defaultDanhMucShouldBeFound("ngaytao.lessThanOrEqual=" + DEFAULT_NGAYTAO);

        // Get all the danhMucList where ngaytao is less than or equal to SMALLER_NGAYTAO
        defaultDanhMucShouldNotBeFound("ngaytao.lessThanOrEqual=" + SMALLER_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByNgaytaoIsLessThanSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ngaytao is less than DEFAULT_NGAYTAO
        defaultDanhMucShouldNotBeFound("ngaytao.lessThan=" + DEFAULT_NGAYTAO);

        // Get all the danhMucList where ngaytao is less than UPDATED_NGAYTAO
        defaultDanhMucShouldBeFound("ngaytao.lessThan=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByNgaytaoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ngaytao is greater than DEFAULT_NGAYTAO
        defaultDanhMucShouldNotBeFound("ngaytao.greaterThan=" + DEFAULT_NGAYTAO);

        // Get all the danhMucList where ngaytao is greater than SMALLER_NGAYTAO
        defaultDanhMucShouldBeFound("ngaytao.greaterThan=" + SMALLER_NGAYTAO);
    }


    @Test
    @Transactional
    public void getAllDanhMucsBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where sudung equals to DEFAULT_SUDUNG
        defaultDanhMucShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the danhMucList where sudung equals to UPDATED_SUDUNG
        defaultDanhMucShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhMucsBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where sudung not equals to DEFAULT_SUDUNG
        defaultDanhMucShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the danhMucList where sudung not equals to UPDATED_SUDUNG
        defaultDanhMucShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhMucsBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultDanhMucShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the danhMucList where sudung equals to UPDATED_SUDUNG
        defaultDanhMucShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhMucsBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where sudung is not null
        defaultDanhMucShouldBeFound("sudung.specified=true");

        // Get all the danhMucList where sudung is null
        defaultDanhMucShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhMucsBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultDanhMucShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the danhMucList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultDanhMucShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhMucsBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultDanhMucShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the danhMucList where sudung is less than or equal to SMALLER_SUDUNG
        defaultDanhMucShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhMucsBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where sudung is less than DEFAULT_SUDUNG
        defaultDanhMucShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the danhMucList where sudung is less than UPDATED_SUDUNG
        defaultDanhMucShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllDanhMucsBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where sudung is greater than DEFAULT_SUDUNG
        defaultDanhMucShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the danhMucList where sudung is greater than SMALLER_SUDUNG
        defaultDanhMucShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllDanhMucsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ten equals to DEFAULT_TEN
        defaultDanhMucShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the danhMucList where ten equals to UPDATED_TEN
        defaultDanhMucShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ten not equals to DEFAULT_TEN
        defaultDanhMucShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the danhMucList where ten not equals to UPDATED_TEN
        defaultDanhMucShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultDanhMucShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the danhMucList where ten equals to UPDATED_TEN
        defaultDanhMucShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ten is not null
        defaultDanhMucShouldBeFound("ten.specified=true");

        // Get all the danhMucList where ten is null
        defaultDanhMucShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllDanhMucsByTenContainsSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ten contains DEFAULT_TEN
        defaultDanhMucShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the danhMucList where ten contains UPDATED_TEN
        defaultDanhMucShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where ten does not contain DEFAULT_TEN
        defaultDanhMucShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the danhMucList where ten does not contain UPDATED_TEN
        defaultDanhMucShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllDanhMucsByThutuIsEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where thutu equals to DEFAULT_THUTU
        defaultDanhMucShouldBeFound("thutu.equals=" + DEFAULT_THUTU);

        // Get all the danhMucList where thutu equals to UPDATED_THUTU
        defaultDanhMucShouldNotBeFound("thutu.equals=" + UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByThutuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where thutu not equals to DEFAULT_THUTU
        defaultDanhMucShouldNotBeFound("thutu.notEquals=" + DEFAULT_THUTU);

        // Get all the danhMucList where thutu not equals to UPDATED_THUTU
        defaultDanhMucShouldBeFound("thutu.notEquals=" + UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByThutuIsInShouldWork() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where thutu in DEFAULT_THUTU or UPDATED_THUTU
        defaultDanhMucShouldBeFound("thutu.in=" + DEFAULT_THUTU + "," + UPDATED_THUTU);

        // Get all the danhMucList where thutu equals to UPDATED_THUTU
        defaultDanhMucShouldNotBeFound("thutu.in=" + UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByThutuIsNullOrNotNull() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where thutu is not null
        defaultDanhMucShouldBeFound("thutu.specified=true");

        // Get all the danhMucList where thutu is null
        defaultDanhMucShouldNotBeFound("thutu.specified=false");
    }

    @Test
    @Transactional
    public void getAllDanhMucsByThutuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where thutu is greater than or equal to DEFAULT_THUTU
        defaultDanhMucShouldBeFound("thutu.greaterThanOrEqual=" + DEFAULT_THUTU);

        // Get all the danhMucList where thutu is greater than or equal to UPDATED_THUTU
        defaultDanhMucShouldNotBeFound("thutu.greaterThanOrEqual=" + UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByThutuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where thutu is less than or equal to DEFAULT_THUTU
        defaultDanhMucShouldBeFound("thutu.lessThanOrEqual=" + DEFAULT_THUTU);

        // Get all the danhMucList where thutu is less than or equal to SMALLER_THUTU
        defaultDanhMucShouldNotBeFound("thutu.lessThanOrEqual=" + SMALLER_THUTU);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByThutuIsLessThanSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where thutu is less than DEFAULT_THUTU
        defaultDanhMucShouldNotBeFound("thutu.lessThan=" + DEFAULT_THUTU);

        // Get all the danhMucList where thutu is less than UPDATED_THUTU
        defaultDanhMucShouldBeFound("thutu.lessThan=" + UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void getAllDanhMucsByThutuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        // Get all the danhMucList where thutu is greater than DEFAULT_THUTU
        defaultDanhMucShouldNotBeFound("thutu.greaterThan=" + DEFAULT_THUTU);

        // Get all the danhMucList where thutu is greater than SMALLER_THUTU
        defaultDanhMucShouldBeFound("thutu.greaterThan=" + SMALLER_THUTU);
    }


    @Test
    @Transactional
    public void getAllDanhMucsByLoaiDanhMucIsEqualToSomething() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);
        LoaiDanhMuc loaiDanhMuc = LoaiDanhMucResourceIT.createEntity(em);
        em.persist(loaiDanhMuc);
        em.flush();
        danhMuc.setLoaiDanhMuc(loaiDanhMuc);
        danhMucRepository.saveAndFlush(danhMuc);
        Long loaiDanhMucId = loaiDanhMuc.getId();

        // Get all the danhMucList where loaiDanhMuc equals to loaiDanhMucId
        defaultDanhMucShouldBeFound("loaiDanhMucId.equals=" + loaiDanhMucId);

        // Get all the danhMucList where loaiDanhMuc equals to loaiDanhMucId + 1
        defaultDanhMucShouldNotBeFound("loaiDanhMucId.equals=" + (loaiDanhMucId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhMucShouldBeFound(String filter) throws Exception {
        restDanhMucMockMvc.perform(get("/api/danh-mucs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ngaytao").value(hasItem(DEFAULT_NGAYTAO.toString())))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].thutu").value(hasItem(DEFAULT_THUTU)));

        // Check, that the count call also returns 1
        restDanhMucMockMvc.perform(get("/api/danh-mucs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhMucShouldNotBeFound(String filter) throws Exception {
        restDanhMucMockMvc.perform(get("/api/danh-mucs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhMucMockMvc.perform(get("/api/danh-mucs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDanhMuc() throws Exception {
        // Get the danhMuc
        restDanhMucMockMvc.perform(get("/api/danh-mucs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDanhMuc() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        int databaseSizeBeforeUpdate = danhMucRepository.findAll().size();

        // Update the danhMuc
        DanhMuc updatedDanhMuc = danhMucRepository.findById(danhMuc.getId()).get();
        // Disconnect from session so that the updates on updatedDanhMuc are not directly saved in db
        em.detach(updatedDanhMuc);
        updatedDanhMuc
            .ma(UPDATED_MA)
            .ngaytao(UPDATED_NGAYTAO)
            .sudung(UPDATED_SUDUNG)
            .ten(UPDATED_TEN)
            .thutu(UPDATED_THUTU);
        DanhMucDTO danhMucDTO = danhMucMapper.toDto(updatedDanhMuc);

        restDanhMucMockMvc.perform(put("/api/danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhMucDTO)))
            .andExpect(status().isOk());

        // Validate the DanhMuc in the database
        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        assertThat(danhMucList).hasSize(databaseSizeBeforeUpdate);
        DanhMuc testDanhMuc = danhMucList.get(danhMucList.size() - 1);
        assertThat(testDanhMuc.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDanhMuc.getNgaytao()).isEqualTo(UPDATED_NGAYTAO);
        assertThat(testDanhMuc.getSudung()).isEqualTo(UPDATED_SUDUNG);
        assertThat(testDanhMuc.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testDanhMuc.getThutu()).isEqualTo(UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void updateNonExistingDanhMuc() throws Exception {
        int databaseSizeBeforeUpdate = danhMucRepository.findAll().size();

        // Create the DanhMuc
        DanhMucDTO danhMucDTO = danhMucMapper.toDto(danhMuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucMockMvc.perform(put("/api/danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhMucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMuc in the database
        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        assertThat(danhMucList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDanhMuc() throws Exception {
        // Initialize the database
        danhMucRepository.saveAndFlush(danhMuc);

        int databaseSizeBeforeDelete = danhMucRepository.findAll().size();

        // Delete the danhMuc
        restDanhMucMockMvc.perform(delete("/api/danh-mucs/{id}", danhMuc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        assertThat(danhMucList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
