package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.NckhApp;
import com.vnpt.nckh.domain.NckhDanhmuc;
import com.vnpt.nckh.domain.NckhDetai;
import com.vnpt.nckh.repository.NckhDanhmucRepository;
import com.vnpt.nckh.service.NckhDanhmucService;
import com.vnpt.nckh.web.rest.errors.ExceptionTranslator;
import com.vnpt.nckh.service.dto.NckhDanhmucCriteria;
import com.vnpt.nckh.service.NckhDanhmucQueryService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.vnpt.nckh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NckhDanhmucResource} REST controller.
 */
@SpringBootTest(classes = NckhApp.class)
public class NckhDanhmucResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAYTAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAYTAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CSYTID = 1L;
    private static final Long UPDATED_CSYTID = 2L;
    private static final Long SMALLER_CSYTID = 1L - 1L;

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    private static final Long DEFAULT_THUTU = 1L;
    private static final Long UPDATED_THUTU = 2L;
    private static final Long SMALLER_THUTU = 1L - 1L;

    @Autowired
    private NckhDanhmucRepository nckhDanhmucRepository;

    @Autowired
    private NckhDanhmucService nckhDanhmucService;

    @Autowired
    private NckhDanhmucQueryService nckhDanhmucQueryService;

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

    private MockMvc restNckhDanhmucMockMvc;

    private NckhDanhmuc nckhDanhmuc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NckhDanhmucResource nckhDanhmucResource = new NckhDanhmucResource(nckhDanhmucService, nckhDanhmucQueryService);
        this.restNckhDanhmucMockMvc = MockMvcBuilders.standaloneSetup(nckhDanhmucResource)
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
    public static NckhDanhmuc createEntity(EntityManager em) {
        NckhDanhmuc nckhDanhmuc = new NckhDanhmuc()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .ngaytao(DEFAULT_NGAYTAO)
            .csytid(DEFAULT_CSYTID)
            .sudung(DEFAULT_SUDUNG)
            .thutu(DEFAULT_THUTU);
        return nckhDanhmuc;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NckhDanhmuc createUpdatedEntity(EntityManager em) {
        NckhDanhmuc nckhDanhmuc = new NckhDanhmuc()
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .ngaytao(UPDATED_NGAYTAO)
            .csytid(UPDATED_CSYTID)
            .sudung(UPDATED_SUDUNG)
            .thutu(UPDATED_THUTU);
        return nckhDanhmuc;
    }

    @BeforeEach
    public void initTest() {
        nckhDanhmuc = createEntity(em);
    }

    @Test
    @Transactional
    public void createNckhDanhmuc() throws Exception {
        int databaseSizeBeforeCreate = nckhDanhmucRepository.findAll().size();

        // Create the NckhDanhmuc
        restNckhDanhmucMockMvc.perform(post("/api/nckh-danhmucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhDanhmuc)))
            .andExpect(status().isCreated());

        // Validate the NckhDanhmuc in the database
        List<NckhDanhmuc> nckhDanhmucList = nckhDanhmucRepository.findAll();
        assertThat(nckhDanhmucList).hasSize(databaseSizeBeforeCreate + 1);
        NckhDanhmuc testNckhDanhmuc = nckhDanhmucList.get(nckhDanhmucList.size() - 1);
        assertThat(testNckhDanhmuc.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testNckhDanhmuc.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testNckhDanhmuc.getNgaytao()).isEqualTo(DEFAULT_NGAYTAO);
        assertThat(testNckhDanhmuc.getCsytid()).isEqualTo(DEFAULT_CSYTID);
        assertThat(testNckhDanhmuc.getSudung()).isEqualTo(DEFAULT_SUDUNG);
        assertThat(testNckhDanhmuc.getThutu()).isEqualTo(DEFAULT_THUTU);
    }

    @Test
    @Transactional
    public void createNckhDanhmucWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nckhDanhmucRepository.findAll().size();

        // Create the NckhDanhmuc with an existing ID
        nckhDanhmuc.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNckhDanhmucMockMvc.perform(post("/api/nckh-danhmucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhDanhmuc)))
            .andExpect(status().isBadRequest());

        // Validate the NckhDanhmuc in the database
        List<NckhDanhmuc> nckhDanhmucList = nckhDanhmucRepository.findAll();
        assertThat(nckhDanhmucList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTenIsRequired() throws Exception {
        int databaseSizeBeforeTest = nckhDanhmucRepository.findAll().size();
        // set the field null
        nckhDanhmuc.setTen(null);

        // Create the NckhDanhmuc, which fails.

        restNckhDanhmucMockMvc.perform(post("/api/nckh-danhmucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhDanhmuc)))
            .andExpect(status().isBadRequest());

        List<NckhDanhmuc> nckhDanhmucList = nckhDanhmucRepository.findAll();
        assertThat(nckhDanhmucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucs() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList
        restNckhDanhmucMockMvc.perform(get("/api/nckh-danhmucs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nckhDanhmuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].ngaytao").value(hasItem(DEFAULT_NGAYTAO.toString())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)))
            .andExpect(jsonPath("$.[*].thutu").value(hasItem(DEFAULT_THUTU.intValue())));
    }
    
    @Test
    @Transactional
    public void getNckhDanhmuc() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get the nckhDanhmuc
        restNckhDanhmucMockMvc.perform(get("/api/nckh-danhmucs/{id}", nckhDanhmuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nckhDanhmuc.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.ngaytao").value(DEFAULT_NGAYTAO.toString()))
            .andExpect(jsonPath("$.csytid").value(DEFAULT_CSYTID.intValue()))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG))
            .andExpect(jsonPath("$.thutu").value(DEFAULT_THUTU.intValue()));
    }


    @Test
    @Transactional
    public void getNckhDanhmucsByIdFiltering() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        Long id = nckhDanhmuc.getId();

        defaultNckhDanhmucShouldBeFound("id.equals=" + id);
        defaultNckhDanhmucShouldNotBeFound("id.notEquals=" + id);

        defaultNckhDanhmucShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNckhDanhmucShouldNotBeFound("id.greaterThan=" + id);

        defaultNckhDanhmucShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNckhDanhmucShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllNckhDanhmucsByMaIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ma equals to DEFAULT_MA
        defaultNckhDanhmucShouldBeFound("ma.equals=" + DEFAULT_MA);

        // Get all the nckhDanhmucList where ma equals to UPDATED_MA
        defaultNckhDanhmucShouldNotBeFound("ma.equals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByMaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ma not equals to DEFAULT_MA
        defaultNckhDanhmucShouldNotBeFound("ma.notEquals=" + DEFAULT_MA);

        // Get all the nckhDanhmucList where ma not equals to UPDATED_MA
        defaultNckhDanhmucShouldBeFound("ma.notEquals=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByMaIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ma in DEFAULT_MA or UPDATED_MA
        defaultNckhDanhmucShouldBeFound("ma.in=" + DEFAULT_MA + "," + UPDATED_MA);

        // Get all the nckhDanhmucList where ma equals to UPDATED_MA
        defaultNckhDanhmucShouldNotBeFound("ma.in=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByMaIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ma is not null
        defaultNckhDanhmucShouldBeFound("ma.specified=true");

        // Get all the nckhDanhmucList where ma is null
        defaultNckhDanhmucShouldNotBeFound("ma.specified=false");
    }
                @Test
    @Transactional
    public void getAllNckhDanhmucsByMaContainsSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ma contains DEFAULT_MA
        defaultNckhDanhmucShouldBeFound("ma.contains=" + DEFAULT_MA);

        // Get all the nckhDanhmucList where ma contains UPDATED_MA
        defaultNckhDanhmucShouldNotBeFound("ma.contains=" + UPDATED_MA);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByMaNotContainsSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ma does not contain DEFAULT_MA
        defaultNckhDanhmucShouldNotBeFound("ma.doesNotContain=" + DEFAULT_MA);

        // Get all the nckhDanhmucList where ma does not contain UPDATED_MA
        defaultNckhDanhmucShouldBeFound("ma.doesNotContain=" + UPDATED_MA);
    }


    @Test
    @Transactional
    public void getAllNckhDanhmucsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ten equals to DEFAULT_TEN
        defaultNckhDanhmucShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the nckhDanhmucList where ten equals to UPDATED_TEN
        defaultNckhDanhmucShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ten not equals to DEFAULT_TEN
        defaultNckhDanhmucShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the nckhDanhmucList where ten not equals to UPDATED_TEN
        defaultNckhDanhmucShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultNckhDanhmucShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the nckhDanhmucList where ten equals to UPDATED_TEN
        defaultNckhDanhmucShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ten is not null
        defaultNckhDanhmucShouldBeFound("ten.specified=true");

        // Get all the nckhDanhmucList where ten is null
        defaultNckhDanhmucShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllNckhDanhmucsByTenContainsSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ten contains DEFAULT_TEN
        defaultNckhDanhmucShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the nckhDanhmucList where ten contains UPDATED_TEN
        defaultNckhDanhmucShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ten does not contain DEFAULT_TEN
        defaultNckhDanhmucShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the nckhDanhmucList where ten does not contain UPDATED_TEN
        defaultNckhDanhmucShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllNckhDanhmucsByNgaytaoIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ngaytao equals to DEFAULT_NGAYTAO
        defaultNckhDanhmucShouldBeFound("ngaytao.equals=" + DEFAULT_NGAYTAO);

        // Get all the nckhDanhmucList where ngaytao equals to UPDATED_NGAYTAO
        defaultNckhDanhmucShouldNotBeFound("ngaytao.equals=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByNgaytaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ngaytao not equals to DEFAULT_NGAYTAO
        defaultNckhDanhmucShouldNotBeFound("ngaytao.notEquals=" + DEFAULT_NGAYTAO);

        // Get all the nckhDanhmucList where ngaytao not equals to UPDATED_NGAYTAO
        defaultNckhDanhmucShouldBeFound("ngaytao.notEquals=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByNgaytaoIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ngaytao in DEFAULT_NGAYTAO or UPDATED_NGAYTAO
        defaultNckhDanhmucShouldBeFound("ngaytao.in=" + DEFAULT_NGAYTAO + "," + UPDATED_NGAYTAO);

        // Get all the nckhDanhmucList where ngaytao equals to UPDATED_NGAYTAO
        defaultNckhDanhmucShouldNotBeFound("ngaytao.in=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByNgaytaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where ngaytao is not null
        defaultNckhDanhmucShouldBeFound("ngaytao.specified=true");

        // Get all the nckhDanhmucList where ngaytao is null
        defaultNckhDanhmucShouldNotBeFound("ngaytao.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByCsytidIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where csytid equals to DEFAULT_CSYTID
        defaultNckhDanhmucShouldBeFound("csytid.equals=" + DEFAULT_CSYTID);

        // Get all the nckhDanhmucList where csytid equals to UPDATED_CSYTID
        defaultNckhDanhmucShouldNotBeFound("csytid.equals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByCsytidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where csytid not equals to DEFAULT_CSYTID
        defaultNckhDanhmucShouldNotBeFound("csytid.notEquals=" + DEFAULT_CSYTID);

        // Get all the nckhDanhmucList where csytid not equals to UPDATED_CSYTID
        defaultNckhDanhmucShouldBeFound("csytid.notEquals=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByCsytidIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where csytid in DEFAULT_CSYTID or UPDATED_CSYTID
        defaultNckhDanhmucShouldBeFound("csytid.in=" + DEFAULT_CSYTID + "," + UPDATED_CSYTID);

        // Get all the nckhDanhmucList where csytid equals to UPDATED_CSYTID
        defaultNckhDanhmucShouldNotBeFound("csytid.in=" + UPDATED_CSYTID);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByCsytidIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where csytid is not null
        defaultNckhDanhmucShouldBeFound("csytid.specified=true");

        // Get all the nckhDanhmucList where csytid is null
        defaultNckhDanhmucShouldNotBeFound("csytid.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByCsytidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where csytid is greater than or equal to DEFAULT_CSYTID
        defaultNckhDanhmucShouldBeFound("csytid.greaterThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the nckhDanhmucList where csytid is greater than or equal to (DEFAULT_CSYTID + 1)
        defaultNckhDanhmucShouldNotBeFound("csytid.greaterThanOrEqual=" + (DEFAULT_CSYTID + 1));
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByCsytidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where csytid is less than or equal to DEFAULT_CSYTID
        defaultNckhDanhmucShouldBeFound("csytid.lessThanOrEqual=" + DEFAULT_CSYTID);

        // Get all the nckhDanhmucList where csytid is less than or equal to SMALLER_CSYTID
        defaultNckhDanhmucShouldNotBeFound("csytid.lessThanOrEqual=" + SMALLER_CSYTID);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByCsytidIsLessThanSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where csytid is less than DEFAULT_CSYTID
        defaultNckhDanhmucShouldNotBeFound("csytid.lessThan=" + DEFAULT_CSYTID);

        // Get all the nckhDanhmucList where csytid is less than (DEFAULT_CSYTID + 1)
        defaultNckhDanhmucShouldBeFound("csytid.lessThan=" + (DEFAULT_CSYTID + 1));
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByCsytidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where csytid is greater than DEFAULT_CSYTID
        defaultNckhDanhmucShouldNotBeFound("csytid.greaterThan=" + DEFAULT_CSYTID);

        // Get all the nckhDanhmucList where csytid is greater than SMALLER_CSYTID
        defaultNckhDanhmucShouldBeFound("csytid.greaterThan=" + SMALLER_CSYTID);
    }


    @Test
    @Transactional
    public void getAllNckhDanhmucsBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where sudung equals to DEFAULT_SUDUNG
        defaultNckhDanhmucShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the nckhDanhmucList where sudung equals to UPDATED_SUDUNG
        defaultNckhDanhmucShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where sudung not equals to DEFAULT_SUDUNG
        defaultNckhDanhmucShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the nckhDanhmucList where sudung not equals to UPDATED_SUDUNG
        defaultNckhDanhmucShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultNckhDanhmucShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the nckhDanhmucList where sudung equals to UPDATED_SUDUNG
        defaultNckhDanhmucShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where sudung is not null
        defaultNckhDanhmucShouldBeFound("sudung.specified=true");

        // Get all the nckhDanhmucList where sudung is null
        defaultNckhDanhmucShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultNckhDanhmucShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the nckhDanhmucList where sudung is greater than or equal to (DEFAULT_SUDUNG + 1)
        defaultNckhDanhmucShouldNotBeFound("sudung.greaterThanOrEqual=" + (DEFAULT_SUDUNG + 1));
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultNckhDanhmucShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the nckhDanhmucList where sudung is less than or equal to SMALLER_SUDUNG
        defaultNckhDanhmucShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where sudung is less than DEFAULT_SUDUNG
        defaultNckhDanhmucShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the nckhDanhmucList where sudung is less than (DEFAULT_SUDUNG + 1)
        defaultNckhDanhmucShouldBeFound("sudung.lessThan=" + (DEFAULT_SUDUNG + 1));
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where sudung is greater than DEFAULT_SUDUNG
        defaultNckhDanhmucShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the nckhDanhmucList where sudung is greater than SMALLER_SUDUNG
        defaultNckhDanhmucShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllNckhDanhmucsByThutuIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where thutu equals to DEFAULT_THUTU
        defaultNckhDanhmucShouldBeFound("thutu.equals=" + DEFAULT_THUTU);

        // Get all the nckhDanhmucList where thutu equals to UPDATED_THUTU
        defaultNckhDanhmucShouldNotBeFound("thutu.equals=" + UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByThutuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where thutu not equals to DEFAULT_THUTU
        defaultNckhDanhmucShouldNotBeFound("thutu.notEquals=" + DEFAULT_THUTU);

        // Get all the nckhDanhmucList where thutu not equals to UPDATED_THUTU
        defaultNckhDanhmucShouldBeFound("thutu.notEquals=" + UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByThutuIsInShouldWork() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where thutu in DEFAULT_THUTU or UPDATED_THUTU
        defaultNckhDanhmucShouldBeFound("thutu.in=" + DEFAULT_THUTU + "," + UPDATED_THUTU);

        // Get all the nckhDanhmucList where thutu equals to UPDATED_THUTU
        defaultNckhDanhmucShouldNotBeFound("thutu.in=" + UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByThutuIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where thutu is not null
        defaultNckhDanhmucShouldBeFound("thutu.specified=true");

        // Get all the nckhDanhmucList where thutu is null
        defaultNckhDanhmucShouldNotBeFound("thutu.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByThutuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where thutu is greater than or equal to DEFAULT_THUTU
        defaultNckhDanhmucShouldBeFound("thutu.greaterThanOrEqual=" + DEFAULT_THUTU);

        // Get all the nckhDanhmucList where thutu is greater than or equal to UPDATED_THUTU
        defaultNckhDanhmucShouldNotBeFound("thutu.greaterThanOrEqual=" + UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByThutuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where thutu is less than or equal to DEFAULT_THUTU
        defaultNckhDanhmucShouldBeFound("thutu.lessThanOrEqual=" + DEFAULT_THUTU);

        // Get all the nckhDanhmucList where thutu is less than or equal to SMALLER_THUTU
        defaultNckhDanhmucShouldNotBeFound("thutu.lessThanOrEqual=" + SMALLER_THUTU);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByThutuIsLessThanSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where thutu is less than DEFAULT_THUTU
        defaultNckhDanhmucShouldNotBeFound("thutu.lessThan=" + DEFAULT_THUTU);

        // Get all the nckhDanhmucList where thutu is less than UPDATED_THUTU
        defaultNckhDanhmucShouldBeFound("thutu.lessThan=" + UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void getAllNckhDanhmucsByThutuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);

        // Get all the nckhDanhmucList where thutu is greater than DEFAULT_THUTU
        defaultNckhDanhmucShouldNotBeFound("thutu.greaterThan=" + DEFAULT_THUTU);

        // Get all the nckhDanhmucList where thutu is greater than SMALLER_THUTU
        defaultNckhDanhmucShouldBeFound("thutu.greaterThan=" + SMALLER_THUTU);
    }


    @Test
    @Transactional
    public void getAllNckhDanhmucsByNckhDetaiIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);
        NckhDetai nckhDetai = NckhDetaiResourceIT.createEntity(em);
        em.persist(nckhDetai);
        em.flush();
        nckhDanhmuc.addNckhDetai(nckhDetai);
        nckhDanhmucRepository.saveAndFlush(nckhDanhmuc);
        Long nckhDetaiId = nckhDetai.getId();

        // Get all the nckhDanhmucList where nckhDetai equals to nckhDetaiId
        defaultNckhDanhmucShouldBeFound("nckhDetaiId.equals=" + nckhDetaiId);

        // Get all the nckhDanhmucList where nckhDetai equals to nckhDetaiId + 1
        defaultNckhDanhmucShouldNotBeFound("nckhDetaiId.equals=" + (nckhDetaiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNckhDanhmucShouldBeFound(String filter) throws Exception {
        restNckhDanhmucMockMvc.perform(get("/api/nckh-danhmucs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nckhDanhmuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].ngaytao").value(hasItem(DEFAULT_NGAYTAO.toString())))
            .andExpect(jsonPath("$.[*].csytid").value(hasItem(DEFAULT_CSYTID.intValue())))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)))
            .andExpect(jsonPath("$.[*].thutu").value(hasItem(DEFAULT_THUTU.intValue())));

        // Check, that the count call also returns 1
        restNckhDanhmucMockMvc.perform(get("/api/nckh-danhmucs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNckhDanhmucShouldNotBeFound(String filter) throws Exception {
        restNckhDanhmucMockMvc.perform(get("/api/nckh-danhmucs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNckhDanhmucMockMvc.perform(get("/api/nckh-danhmucs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingNckhDanhmuc() throws Exception {
        // Get the nckhDanhmuc
        restNckhDanhmucMockMvc.perform(get("/api/nckh-danhmucs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNckhDanhmuc() throws Exception {
        // Initialize the database
        nckhDanhmucService.save(nckhDanhmuc);

        int databaseSizeBeforeUpdate = nckhDanhmucRepository.findAll().size();

        // Update the nckhDanhmuc
        NckhDanhmuc updatedNckhDanhmuc = nckhDanhmucRepository.findById(nckhDanhmuc.getId()).get();
        // Disconnect from session so that the updates on updatedNckhDanhmuc are not directly saved in db
        em.detach(updatedNckhDanhmuc);
        updatedNckhDanhmuc
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .ngaytao(UPDATED_NGAYTAO)
            .csytid(UPDATED_CSYTID)
            .sudung(UPDATED_SUDUNG)
            .thutu(UPDATED_THUTU);

        restNckhDanhmucMockMvc.perform(put("/api/nckh-danhmucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNckhDanhmuc)))
            .andExpect(status().isOk());

        // Validate the NckhDanhmuc in the database
        List<NckhDanhmuc> nckhDanhmucList = nckhDanhmucRepository.findAll();
        assertThat(nckhDanhmucList).hasSize(databaseSizeBeforeUpdate);
        NckhDanhmuc testNckhDanhmuc = nckhDanhmucList.get(nckhDanhmucList.size() - 1);
        assertThat(testNckhDanhmuc.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testNckhDanhmuc.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testNckhDanhmuc.getNgaytao()).isEqualTo(UPDATED_NGAYTAO);
        assertThat(testNckhDanhmuc.getCsytid()).isEqualTo(UPDATED_CSYTID);
        assertThat(testNckhDanhmuc.getSudung()).isEqualTo(UPDATED_SUDUNG);
        assertThat(testNckhDanhmuc.getThutu()).isEqualTo(UPDATED_THUTU);
    }

    @Test
    @Transactional
    public void updateNonExistingNckhDanhmuc() throws Exception {
        int databaseSizeBeforeUpdate = nckhDanhmucRepository.findAll().size();

        // Create the NckhDanhmuc

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNckhDanhmucMockMvc.perform(put("/api/nckh-danhmucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhDanhmuc)))
            .andExpect(status().isBadRequest());

        // Validate the NckhDanhmuc in the database
        List<NckhDanhmuc> nckhDanhmucList = nckhDanhmucRepository.findAll();
        assertThat(nckhDanhmucList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNckhDanhmuc() throws Exception {
        // Initialize the database
        nckhDanhmucService.save(nckhDanhmuc);

        int databaseSizeBeforeDelete = nckhDanhmucRepository.findAll().size();

        // Delete the nckhDanhmuc
        restNckhDanhmucMockMvc.perform(delete("/api/nckh-danhmucs/{id}", nckhDanhmuc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NckhDanhmuc> nckhDanhmucList = nckhDanhmucRepository.findAll();
        assertThat(nckhDanhmucList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
