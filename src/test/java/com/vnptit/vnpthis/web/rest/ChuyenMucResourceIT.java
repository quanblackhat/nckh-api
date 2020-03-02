package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.ChuyenMuc;
import com.vnptit.vnpthis.domain.DeTai;
import com.vnptit.vnpthis.repository.ChuyenMucRepository;
import com.vnptit.vnpthis.service.ChuyenMucService;
import com.vnptit.vnpthis.service.dto.ChuyenMucDTO;
import com.vnptit.vnpthis.service.mapper.ChuyenMucMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.service.dto.ChuyenMucCriteria;
import com.vnptit.vnpthis.service.ChuyenMucQueryService;

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
 * Integration tests for the {@link ChuyenMucResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class ChuyenMucResourceIT {

    private static final LocalDate DEFAULT_NGAYTAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYTAO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYTAO = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_SOTT = 1;
    private static final Integer UPDATED_SOTT = 2;
    private static final Integer SMALLER_SOTT = 1 - 1;

    private static final String DEFAULT_TENCHUYENMUC = "AAAAAAAAAA";
    private static final String UPDATED_TENCHUYENMUC = "BBBBBBBBBB";

    @Autowired
    private ChuyenMucRepository chuyenMucRepository;

    @Autowired
    private ChuyenMucMapper chuyenMucMapper;

    @Autowired
    private ChuyenMucService chuyenMucService;

    @Autowired
    private ChuyenMucQueryService chuyenMucQueryService;

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

    private MockMvc restChuyenMucMockMvc;

    private ChuyenMuc chuyenMuc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChuyenMucResource chuyenMucResource = new ChuyenMucResource(chuyenMucService, chuyenMucQueryService);
        this.restChuyenMucMockMvc = MockMvcBuilders.standaloneSetup(chuyenMucResource)
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
    public static ChuyenMuc createEntity(EntityManager em) {
        ChuyenMuc chuyenMuc = new ChuyenMuc()
            .ngaytao(DEFAULT_NGAYTAO)
            .sott(DEFAULT_SOTT)
            .tenchuyenmuc(DEFAULT_TENCHUYENMUC);
        return chuyenMuc;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChuyenMuc createUpdatedEntity(EntityManager em) {
        ChuyenMuc chuyenMuc = new ChuyenMuc()
            .ngaytao(UPDATED_NGAYTAO)
            .sott(UPDATED_SOTT)
            .tenchuyenmuc(UPDATED_TENCHUYENMUC);
        return chuyenMuc;
    }

    @BeforeEach
    public void initTest() {
        chuyenMuc = createEntity(em);
    }

    @Test
    @Transactional
    public void createChuyenMuc() throws Exception {
        int databaseSizeBeforeCreate = chuyenMucRepository.findAll().size();

        // Create the ChuyenMuc
        ChuyenMucDTO chuyenMucDTO = chuyenMucMapper.toDto(chuyenMuc);
        restChuyenMucMockMvc.perform(post("/api/chuyen-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chuyenMucDTO)))
            .andExpect(status().isCreated());

        // Validate the ChuyenMuc in the database
        List<ChuyenMuc> chuyenMucList = chuyenMucRepository.findAll();
        assertThat(chuyenMucList).hasSize(databaseSizeBeforeCreate + 1);
        ChuyenMuc testChuyenMuc = chuyenMucList.get(chuyenMucList.size() - 1);
        assertThat(testChuyenMuc.getNgaytao()).isEqualTo(DEFAULT_NGAYTAO);
        assertThat(testChuyenMuc.getSott()).isEqualTo(DEFAULT_SOTT);
        assertThat(testChuyenMuc.getTenchuyenmuc()).isEqualTo(DEFAULT_TENCHUYENMUC);
    }

    @Test
    @Transactional
    public void createChuyenMucWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chuyenMucRepository.findAll().size();

        // Create the ChuyenMuc with an existing ID
        chuyenMuc.setId(1L);
        ChuyenMucDTO chuyenMucDTO = chuyenMucMapper.toDto(chuyenMuc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChuyenMucMockMvc.perform(post("/api/chuyen-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chuyenMucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChuyenMuc in the database
        List<ChuyenMuc> chuyenMucList = chuyenMucRepository.findAll();
        assertThat(chuyenMucList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNgaytaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = chuyenMucRepository.findAll().size();
        // set the field null
        chuyenMuc.setNgaytao(null);

        // Create the ChuyenMuc, which fails.
        ChuyenMucDTO chuyenMucDTO = chuyenMucMapper.toDto(chuyenMuc);

        restChuyenMucMockMvc.perform(post("/api/chuyen-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chuyenMucDTO)))
            .andExpect(status().isBadRequest());

        List<ChuyenMuc> chuyenMucList = chuyenMucRepository.findAll();
        assertThat(chuyenMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSottIsRequired() throws Exception {
        int databaseSizeBeforeTest = chuyenMucRepository.findAll().size();
        // set the field null
        chuyenMuc.setSott(null);

        // Create the ChuyenMuc, which fails.
        ChuyenMucDTO chuyenMucDTO = chuyenMucMapper.toDto(chuyenMuc);

        restChuyenMucMockMvc.perform(post("/api/chuyen-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chuyenMucDTO)))
            .andExpect(status().isBadRequest());

        List<ChuyenMuc> chuyenMucList = chuyenMucRepository.findAll();
        assertThat(chuyenMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTenchuyenmucIsRequired() throws Exception {
        int databaseSizeBeforeTest = chuyenMucRepository.findAll().size();
        // set the field null
        chuyenMuc.setTenchuyenmuc(null);

        // Create the ChuyenMuc, which fails.
        ChuyenMucDTO chuyenMucDTO = chuyenMucMapper.toDto(chuyenMuc);

        restChuyenMucMockMvc.perform(post("/api/chuyen-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chuyenMucDTO)))
            .andExpect(status().isBadRequest());

        List<ChuyenMuc> chuyenMucList = chuyenMucRepository.findAll();
        assertThat(chuyenMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChuyenMucs() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList
        restChuyenMucMockMvc.perform(get("/api/chuyen-mucs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chuyenMuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].ngaytao").value(hasItem(DEFAULT_NGAYTAO.toString())))
            .andExpect(jsonPath("$.[*].sott").value(hasItem(DEFAULT_SOTT)))
            .andExpect(jsonPath("$.[*].tenchuyenmuc").value(hasItem(DEFAULT_TENCHUYENMUC)));
    }
    
    @Test
    @Transactional
    public void getChuyenMuc() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get the chuyenMuc
        restChuyenMucMockMvc.perform(get("/api/chuyen-mucs/{id}", chuyenMuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chuyenMuc.getId().intValue()))
            .andExpect(jsonPath("$.ngaytao").value(DEFAULT_NGAYTAO.toString()))
            .andExpect(jsonPath("$.sott").value(DEFAULT_SOTT))
            .andExpect(jsonPath("$.tenchuyenmuc").value(DEFAULT_TENCHUYENMUC));
    }


    @Test
    @Transactional
    public void getChuyenMucsByIdFiltering() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        Long id = chuyenMuc.getId();

        defaultChuyenMucShouldBeFound("id.equals=" + id);
        defaultChuyenMucShouldNotBeFound("id.notEquals=" + id);

        defaultChuyenMucShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultChuyenMucShouldNotBeFound("id.greaterThan=" + id);

        defaultChuyenMucShouldBeFound("id.lessThanOrEqual=" + id);
        defaultChuyenMucShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllChuyenMucsByNgaytaoIsEqualToSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where ngaytao equals to DEFAULT_NGAYTAO
        defaultChuyenMucShouldBeFound("ngaytao.equals=" + DEFAULT_NGAYTAO);

        // Get all the chuyenMucList where ngaytao equals to UPDATED_NGAYTAO
        defaultChuyenMucShouldNotBeFound("ngaytao.equals=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsByNgaytaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where ngaytao not equals to DEFAULT_NGAYTAO
        defaultChuyenMucShouldNotBeFound("ngaytao.notEquals=" + DEFAULT_NGAYTAO);

        // Get all the chuyenMucList where ngaytao not equals to UPDATED_NGAYTAO
        defaultChuyenMucShouldBeFound("ngaytao.notEquals=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsByNgaytaoIsInShouldWork() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where ngaytao in DEFAULT_NGAYTAO or UPDATED_NGAYTAO
        defaultChuyenMucShouldBeFound("ngaytao.in=" + DEFAULT_NGAYTAO + "," + UPDATED_NGAYTAO);

        // Get all the chuyenMucList where ngaytao equals to UPDATED_NGAYTAO
        defaultChuyenMucShouldNotBeFound("ngaytao.in=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsByNgaytaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where ngaytao is not null
        defaultChuyenMucShouldBeFound("ngaytao.specified=true");

        // Get all the chuyenMucList where ngaytao is null
        defaultChuyenMucShouldNotBeFound("ngaytao.specified=false");
    }

    @Test
    @Transactional
    public void getAllChuyenMucsByNgaytaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where ngaytao is greater than or equal to DEFAULT_NGAYTAO
        defaultChuyenMucShouldBeFound("ngaytao.greaterThanOrEqual=" + DEFAULT_NGAYTAO);

        // Get all the chuyenMucList where ngaytao is greater than or equal to UPDATED_NGAYTAO
        defaultChuyenMucShouldNotBeFound("ngaytao.greaterThanOrEqual=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsByNgaytaoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where ngaytao is less than or equal to DEFAULT_NGAYTAO
        defaultChuyenMucShouldBeFound("ngaytao.lessThanOrEqual=" + DEFAULT_NGAYTAO);

        // Get all the chuyenMucList where ngaytao is less than or equal to SMALLER_NGAYTAO
        defaultChuyenMucShouldNotBeFound("ngaytao.lessThanOrEqual=" + SMALLER_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsByNgaytaoIsLessThanSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where ngaytao is less than DEFAULT_NGAYTAO
        defaultChuyenMucShouldNotBeFound("ngaytao.lessThan=" + DEFAULT_NGAYTAO);

        // Get all the chuyenMucList where ngaytao is less than UPDATED_NGAYTAO
        defaultChuyenMucShouldBeFound("ngaytao.lessThan=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsByNgaytaoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where ngaytao is greater than DEFAULT_NGAYTAO
        defaultChuyenMucShouldNotBeFound("ngaytao.greaterThan=" + DEFAULT_NGAYTAO);

        // Get all the chuyenMucList where ngaytao is greater than SMALLER_NGAYTAO
        defaultChuyenMucShouldBeFound("ngaytao.greaterThan=" + SMALLER_NGAYTAO);
    }


    @Test
    @Transactional
    public void getAllChuyenMucsBySottIsEqualToSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where sott equals to DEFAULT_SOTT
        defaultChuyenMucShouldBeFound("sott.equals=" + DEFAULT_SOTT);

        // Get all the chuyenMucList where sott equals to UPDATED_SOTT
        defaultChuyenMucShouldNotBeFound("sott.equals=" + UPDATED_SOTT);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsBySottIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where sott not equals to DEFAULT_SOTT
        defaultChuyenMucShouldNotBeFound("sott.notEquals=" + DEFAULT_SOTT);

        // Get all the chuyenMucList where sott not equals to UPDATED_SOTT
        defaultChuyenMucShouldBeFound("sott.notEquals=" + UPDATED_SOTT);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsBySottIsInShouldWork() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where sott in DEFAULT_SOTT or UPDATED_SOTT
        defaultChuyenMucShouldBeFound("sott.in=" + DEFAULT_SOTT + "," + UPDATED_SOTT);

        // Get all the chuyenMucList where sott equals to UPDATED_SOTT
        defaultChuyenMucShouldNotBeFound("sott.in=" + UPDATED_SOTT);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsBySottIsNullOrNotNull() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where sott is not null
        defaultChuyenMucShouldBeFound("sott.specified=true");

        // Get all the chuyenMucList where sott is null
        defaultChuyenMucShouldNotBeFound("sott.specified=false");
    }

    @Test
    @Transactional
    public void getAllChuyenMucsBySottIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where sott is greater than or equal to DEFAULT_SOTT
        defaultChuyenMucShouldBeFound("sott.greaterThanOrEqual=" + DEFAULT_SOTT);

        // Get all the chuyenMucList where sott is greater than or equal to UPDATED_SOTT
        defaultChuyenMucShouldNotBeFound("sott.greaterThanOrEqual=" + UPDATED_SOTT);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsBySottIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where sott is less than or equal to DEFAULT_SOTT
        defaultChuyenMucShouldBeFound("sott.lessThanOrEqual=" + DEFAULT_SOTT);

        // Get all the chuyenMucList where sott is less than or equal to SMALLER_SOTT
        defaultChuyenMucShouldNotBeFound("sott.lessThanOrEqual=" + SMALLER_SOTT);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsBySottIsLessThanSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where sott is less than DEFAULT_SOTT
        defaultChuyenMucShouldNotBeFound("sott.lessThan=" + DEFAULT_SOTT);

        // Get all the chuyenMucList where sott is less than UPDATED_SOTT
        defaultChuyenMucShouldBeFound("sott.lessThan=" + UPDATED_SOTT);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsBySottIsGreaterThanSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where sott is greater than DEFAULT_SOTT
        defaultChuyenMucShouldNotBeFound("sott.greaterThan=" + DEFAULT_SOTT);

        // Get all the chuyenMucList where sott is greater than SMALLER_SOTT
        defaultChuyenMucShouldBeFound("sott.greaterThan=" + SMALLER_SOTT);
    }


    @Test
    @Transactional
    public void getAllChuyenMucsByTenchuyenmucIsEqualToSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where tenchuyenmuc equals to DEFAULT_TENCHUYENMUC
        defaultChuyenMucShouldBeFound("tenchuyenmuc.equals=" + DEFAULT_TENCHUYENMUC);

        // Get all the chuyenMucList where tenchuyenmuc equals to UPDATED_TENCHUYENMUC
        defaultChuyenMucShouldNotBeFound("tenchuyenmuc.equals=" + UPDATED_TENCHUYENMUC);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsByTenchuyenmucIsNotEqualToSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where tenchuyenmuc not equals to DEFAULT_TENCHUYENMUC
        defaultChuyenMucShouldNotBeFound("tenchuyenmuc.notEquals=" + DEFAULT_TENCHUYENMUC);

        // Get all the chuyenMucList where tenchuyenmuc not equals to UPDATED_TENCHUYENMUC
        defaultChuyenMucShouldBeFound("tenchuyenmuc.notEquals=" + UPDATED_TENCHUYENMUC);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsByTenchuyenmucIsInShouldWork() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where tenchuyenmuc in DEFAULT_TENCHUYENMUC or UPDATED_TENCHUYENMUC
        defaultChuyenMucShouldBeFound("tenchuyenmuc.in=" + DEFAULT_TENCHUYENMUC + "," + UPDATED_TENCHUYENMUC);

        // Get all the chuyenMucList where tenchuyenmuc equals to UPDATED_TENCHUYENMUC
        defaultChuyenMucShouldNotBeFound("tenchuyenmuc.in=" + UPDATED_TENCHUYENMUC);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsByTenchuyenmucIsNullOrNotNull() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where tenchuyenmuc is not null
        defaultChuyenMucShouldBeFound("tenchuyenmuc.specified=true");

        // Get all the chuyenMucList where tenchuyenmuc is null
        defaultChuyenMucShouldNotBeFound("tenchuyenmuc.specified=false");
    }
                @Test
    @Transactional
    public void getAllChuyenMucsByTenchuyenmucContainsSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where tenchuyenmuc contains DEFAULT_TENCHUYENMUC
        defaultChuyenMucShouldBeFound("tenchuyenmuc.contains=" + DEFAULT_TENCHUYENMUC);

        // Get all the chuyenMucList where tenchuyenmuc contains UPDATED_TENCHUYENMUC
        defaultChuyenMucShouldNotBeFound("tenchuyenmuc.contains=" + UPDATED_TENCHUYENMUC);
    }

    @Test
    @Transactional
    public void getAllChuyenMucsByTenchuyenmucNotContainsSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        // Get all the chuyenMucList where tenchuyenmuc does not contain DEFAULT_TENCHUYENMUC
        defaultChuyenMucShouldNotBeFound("tenchuyenmuc.doesNotContain=" + DEFAULT_TENCHUYENMUC);

        // Get all the chuyenMucList where tenchuyenmuc does not contain UPDATED_TENCHUYENMUC
        defaultChuyenMucShouldBeFound("tenchuyenmuc.doesNotContain=" + UPDATED_TENCHUYENMUC);
    }


    @Test
    @Transactional
    public void getAllChuyenMucsByDeTaiIsEqualToSomething() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);
        DeTai deTai = DeTaiResourceIT.createEntity(em);
        em.persist(deTai);
        em.flush();
        chuyenMuc.addDeTai(deTai);
        chuyenMucRepository.saveAndFlush(chuyenMuc);
        Long deTaiId = deTai.getId();

        // Get all the chuyenMucList where deTai equals to deTaiId
        defaultChuyenMucShouldBeFound("deTaiId.equals=" + deTaiId);

        // Get all the chuyenMucList where deTai equals to deTaiId + 1
        defaultChuyenMucShouldNotBeFound("deTaiId.equals=" + (deTaiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultChuyenMucShouldBeFound(String filter) throws Exception {
        restChuyenMucMockMvc.perform(get("/api/chuyen-mucs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chuyenMuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].ngaytao").value(hasItem(DEFAULT_NGAYTAO.toString())))
            .andExpect(jsonPath("$.[*].sott").value(hasItem(DEFAULT_SOTT)))
            .andExpect(jsonPath("$.[*].tenchuyenmuc").value(hasItem(DEFAULT_TENCHUYENMUC)));

        // Check, that the count call also returns 1
        restChuyenMucMockMvc.perform(get("/api/chuyen-mucs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultChuyenMucShouldNotBeFound(String filter) throws Exception {
        restChuyenMucMockMvc.perform(get("/api/chuyen-mucs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restChuyenMucMockMvc.perform(get("/api/chuyen-mucs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingChuyenMuc() throws Exception {
        // Get the chuyenMuc
        restChuyenMucMockMvc.perform(get("/api/chuyen-mucs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChuyenMuc() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        int databaseSizeBeforeUpdate = chuyenMucRepository.findAll().size();

        // Update the chuyenMuc
        ChuyenMuc updatedChuyenMuc = chuyenMucRepository.findById(chuyenMuc.getId()).get();
        // Disconnect from session so that the updates on updatedChuyenMuc are not directly saved in db
        em.detach(updatedChuyenMuc);
        updatedChuyenMuc
            .ngaytao(UPDATED_NGAYTAO)
            .sott(UPDATED_SOTT)
            .tenchuyenmuc(UPDATED_TENCHUYENMUC);
        ChuyenMucDTO chuyenMucDTO = chuyenMucMapper.toDto(updatedChuyenMuc);

        restChuyenMucMockMvc.perform(put("/api/chuyen-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chuyenMucDTO)))
            .andExpect(status().isOk());

        // Validate the ChuyenMuc in the database
        List<ChuyenMuc> chuyenMucList = chuyenMucRepository.findAll();
        assertThat(chuyenMucList).hasSize(databaseSizeBeforeUpdate);
        ChuyenMuc testChuyenMuc = chuyenMucList.get(chuyenMucList.size() - 1);
        assertThat(testChuyenMuc.getNgaytao()).isEqualTo(UPDATED_NGAYTAO);
        assertThat(testChuyenMuc.getSott()).isEqualTo(UPDATED_SOTT);
        assertThat(testChuyenMuc.getTenchuyenmuc()).isEqualTo(UPDATED_TENCHUYENMUC);
    }

    @Test
    @Transactional
    public void updateNonExistingChuyenMuc() throws Exception {
        int databaseSizeBeforeUpdate = chuyenMucRepository.findAll().size();

        // Create the ChuyenMuc
        ChuyenMucDTO chuyenMucDTO = chuyenMucMapper.toDto(chuyenMuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChuyenMucMockMvc.perform(put("/api/chuyen-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chuyenMucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChuyenMuc in the database
        List<ChuyenMuc> chuyenMucList = chuyenMucRepository.findAll();
        assertThat(chuyenMucList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChuyenMuc() throws Exception {
        // Initialize the database
        chuyenMucRepository.saveAndFlush(chuyenMuc);

        int databaseSizeBeforeDelete = chuyenMucRepository.findAll().size();

        // Delete the chuyenMuc
        restChuyenMucMockMvc.perform(delete("/api/chuyen-mucs/{id}", chuyenMuc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChuyenMuc> chuyenMucList = chuyenMucRepository.findAll();
        assertThat(chuyenMucList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
