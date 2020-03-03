package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.qldt.QldtDmChungchi;
import com.vnptit.vnpthis.domain.qldt.QldtChungChi;
import com.vnptit.vnpthis.domain.qldt.QldtTochucCap;
import com.vnptit.vnpthis.repository.qldt.QldtDmChungchiRepository;
import com.vnptit.vnpthis.service.qldt.QldtDmChungchiService;
import com.vnptit.vnpthis.service.dto.QldtDmChungchiDTO;
import com.vnptit.vnpthis.service.mapper.QldtDmChungchiMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.web.rest.qldt.QldtDmChungchiResource;
import com.vnptit.vnpthis.service.dto.QldtDmChungchiCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDmChungchiQueryService;

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
 * Integration tests for the {@link QldtDmChungchiResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class QldtDmChungchiResourceIT {

    private static final String DEFAULT_MACHUNGCHI = "AAAAAAAAAA";
    private static final String UPDATED_MACHUNGCHI = "BBBBBBBBBB";

    private static final String DEFAULT_TENCHUNGCHI = "AAAAAAAAAA";
    private static final String UPDATED_TENCHUNGCHI = "BBBBBBBBBB";

    private static final String DEFAULT_MOTA = "AAAAAAAAAA";
    private static final String UPDATED_MOTA = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANGTHAI = 1;
    private static final Integer UPDATED_TRANGTHAI = 2;
    private static final Integer SMALLER_TRANGTHAI = 1 - 1;

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private QldtDmChungchiRepository qldtDmChungchiRepository;

    @Autowired
    private QldtDmChungchiMapper qldtDmChungchiMapper;

    @Autowired
    private QldtDmChungchiService qldtDmChungchiService;

    @Autowired
    private QldtDmChungchiQueryService qldtDmChungchiQueryService;

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

    private MockMvc restQldtDmChungchiMockMvc;

    private QldtDmChungchi qldtDmChungchi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QldtDmChungchiResource qldtDmChungchiResource = new QldtDmChungchiResource(qldtDmChungchiService, qldtDmChungchiQueryService);
        this.restQldtDmChungchiMockMvc = MockMvcBuilders.standaloneSetup(qldtDmChungchiResource)
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
    public static QldtDmChungchi createEntity(EntityManager em) {
        QldtDmChungchi qldtDmChungchi = new QldtDmChungchi()
            .machungchi(DEFAULT_MACHUNGCHI)
            .tenchungchi(DEFAULT_TENCHUNGCHI)
            .mota(DEFAULT_MOTA)
            .trangthai(DEFAULT_TRANGTHAI)
            .sudung(DEFAULT_SUDUNG);
        return qldtDmChungchi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtDmChungchi createUpdatedEntity(EntityManager em) {
        QldtDmChungchi qldtDmChungchi = new QldtDmChungchi()
            .machungchi(UPDATED_MACHUNGCHI)
            .tenchungchi(UPDATED_TENCHUNGCHI)
            .mota(UPDATED_MOTA)
            .trangthai(UPDATED_TRANGTHAI)
            .sudung(UPDATED_SUDUNG);
        return qldtDmChungchi;
    }

    @BeforeEach
    public void initTest() {
        qldtDmChungchi = createEntity(em);
    }

    @Test
    @Transactional
    public void createQldtDmChungchi() throws Exception {
        int databaseSizeBeforeCreate = qldtDmChungchiRepository.findAll().size();

        // Create the QldtDmChungchi
        QldtDmChungchiDTO qldtDmChungchiDTO = qldtDmChungchiMapper.toDto(qldtDmChungchi);
        restQldtDmChungchiMockMvc.perform(post("/api/qldt-dm-chungchis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmChungchiDTO)))
            .andExpect(status().isCreated());

        // Validate the QldtDmChungchi in the database
        List<QldtDmChungchi> qldtDmChungchiList = qldtDmChungchiRepository.findAll();
        assertThat(qldtDmChungchiList).hasSize(databaseSizeBeforeCreate + 1);
        QldtDmChungchi testQldtDmChungchi = qldtDmChungchiList.get(qldtDmChungchiList.size() - 1);
        assertThat(testQldtDmChungchi.getMachungchi()).isEqualTo(DEFAULT_MACHUNGCHI);
        assertThat(testQldtDmChungchi.getTenchungchi()).isEqualTo(DEFAULT_TENCHUNGCHI);
        assertThat(testQldtDmChungchi.getMota()).isEqualTo(DEFAULT_MOTA);
        assertThat(testQldtDmChungchi.getTrangthai()).isEqualTo(DEFAULT_TRANGTHAI);
        assertThat(testQldtDmChungchi.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createQldtDmChungchiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qldtDmChungchiRepository.findAll().size();

        // Create the QldtDmChungchi with an existing ID
        qldtDmChungchi.setId(1L);
        QldtDmChungchiDTO qldtDmChungchiDTO = qldtDmChungchiMapper.toDto(qldtDmChungchi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQldtDmChungchiMockMvc.perform(post("/api/qldt-dm-chungchis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmChungchiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDmChungchi in the database
        List<QldtDmChungchi> qldtDmChungchiList = qldtDmChungchiRepository.findAll();
        assertThat(qldtDmChungchiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQldtDmChungchis() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList
        restQldtDmChungchiMockMvc.perform(get("/api/qldt-dm-chungchis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDmChungchi.getId().intValue())))
            .andExpect(jsonPath("$.[*].machungchi").value(hasItem(DEFAULT_MACHUNGCHI)))
            .andExpect(jsonPath("$.[*].tenchungchi").value(hasItem(DEFAULT_TENCHUNGCHI)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].trangthai").value(hasItem(DEFAULT_TRANGTHAI)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getQldtDmChungchi() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get the qldtDmChungchi
        restQldtDmChungchiMockMvc.perform(get("/api/qldt-dm-chungchis/{id}", qldtDmChungchi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qldtDmChungchi.getId().intValue()))
            .andExpect(jsonPath("$.machungchi").value(DEFAULT_MACHUNGCHI))
            .andExpect(jsonPath("$.tenchungchi").value(DEFAULT_TENCHUNGCHI))
            .andExpect(jsonPath("$.mota").value(DEFAULT_MOTA))
            .andExpect(jsonPath("$.trangthai").value(DEFAULT_TRANGTHAI))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getQldtDmChungchisByIdFiltering() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        Long id = qldtDmChungchi.getId();

        defaultQldtDmChungchiShouldBeFound("id.equals=" + id);
        defaultQldtDmChungchiShouldNotBeFound("id.notEquals=" + id);

        defaultQldtDmChungchiShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultQldtDmChungchiShouldNotBeFound("id.greaterThan=" + id);

        defaultQldtDmChungchiShouldBeFound("id.lessThanOrEqual=" + id);
        defaultQldtDmChungchiShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllQldtDmChungchisByMachungchiIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where machungchi equals to DEFAULT_MACHUNGCHI
        defaultQldtDmChungchiShouldBeFound("machungchi.equals=" + DEFAULT_MACHUNGCHI);

        // Get all the qldtDmChungchiList where machungchi equals to UPDATED_MACHUNGCHI
        defaultQldtDmChungchiShouldNotBeFound("machungchi.equals=" + UPDATED_MACHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByMachungchiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where machungchi not equals to DEFAULT_MACHUNGCHI
        defaultQldtDmChungchiShouldNotBeFound("machungchi.notEquals=" + DEFAULT_MACHUNGCHI);

        // Get all the qldtDmChungchiList where machungchi not equals to UPDATED_MACHUNGCHI
        defaultQldtDmChungchiShouldBeFound("machungchi.notEquals=" + UPDATED_MACHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByMachungchiIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where machungchi in DEFAULT_MACHUNGCHI or UPDATED_MACHUNGCHI
        defaultQldtDmChungchiShouldBeFound("machungchi.in=" + DEFAULT_MACHUNGCHI + "," + UPDATED_MACHUNGCHI);

        // Get all the qldtDmChungchiList where machungchi equals to UPDATED_MACHUNGCHI
        defaultQldtDmChungchiShouldNotBeFound("machungchi.in=" + UPDATED_MACHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByMachungchiIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where machungchi is not null
        defaultQldtDmChungchiShouldBeFound("machungchi.specified=true");

        // Get all the qldtDmChungchiList where machungchi is null
        defaultQldtDmChungchiShouldNotBeFound("machungchi.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDmChungchisByMachungchiContainsSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where machungchi contains DEFAULT_MACHUNGCHI
        defaultQldtDmChungchiShouldBeFound("machungchi.contains=" + DEFAULT_MACHUNGCHI);

        // Get all the qldtDmChungchiList where machungchi contains UPDATED_MACHUNGCHI
        defaultQldtDmChungchiShouldNotBeFound("machungchi.contains=" + UPDATED_MACHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByMachungchiNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where machungchi does not contain DEFAULT_MACHUNGCHI
        defaultQldtDmChungchiShouldNotBeFound("machungchi.doesNotContain=" + DEFAULT_MACHUNGCHI);

        // Get all the qldtDmChungchiList where machungchi does not contain UPDATED_MACHUNGCHI
        defaultQldtDmChungchiShouldBeFound("machungchi.doesNotContain=" + UPDATED_MACHUNGCHI);
    }


    @Test
    @Transactional
    public void getAllQldtDmChungchisByTenchungchiIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where tenchungchi equals to DEFAULT_TENCHUNGCHI
        defaultQldtDmChungchiShouldBeFound("tenchungchi.equals=" + DEFAULT_TENCHUNGCHI);

        // Get all the qldtDmChungchiList where tenchungchi equals to UPDATED_TENCHUNGCHI
        defaultQldtDmChungchiShouldNotBeFound("tenchungchi.equals=" + UPDATED_TENCHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByTenchungchiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where tenchungchi not equals to DEFAULT_TENCHUNGCHI
        defaultQldtDmChungchiShouldNotBeFound("tenchungchi.notEquals=" + DEFAULT_TENCHUNGCHI);

        // Get all the qldtDmChungchiList where tenchungchi not equals to UPDATED_TENCHUNGCHI
        defaultQldtDmChungchiShouldBeFound("tenchungchi.notEquals=" + UPDATED_TENCHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByTenchungchiIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where tenchungchi in DEFAULT_TENCHUNGCHI or UPDATED_TENCHUNGCHI
        defaultQldtDmChungchiShouldBeFound("tenchungchi.in=" + DEFAULT_TENCHUNGCHI + "," + UPDATED_TENCHUNGCHI);

        // Get all the qldtDmChungchiList where tenchungchi equals to UPDATED_TENCHUNGCHI
        defaultQldtDmChungchiShouldNotBeFound("tenchungchi.in=" + UPDATED_TENCHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByTenchungchiIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where tenchungchi is not null
        defaultQldtDmChungchiShouldBeFound("tenchungchi.specified=true");

        // Get all the qldtDmChungchiList where tenchungchi is null
        defaultQldtDmChungchiShouldNotBeFound("tenchungchi.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDmChungchisByTenchungchiContainsSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where tenchungchi contains DEFAULT_TENCHUNGCHI
        defaultQldtDmChungchiShouldBeFound("tenchungchi.contains=" + DEFAULT_TENCHUNGCHI);

        // Get all the qldtDmChungchiList where tenchungchi contains UPDATED_TENCHUNGCHI
        defaultQldtDmChungchiShouldNotBeFound("tenchungchi.contains=" + UPDATED_TENCHUNGCHI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByTenchungchiNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where tenchungchi does not contain DEFAULT_TENCHUNGCHI
        defaultQldtDmChungchiShouldNotBeFound("tenchungchi.doesNotContain=" + DEFAULT_TENCHUNGCHI);

        // Get all the qldtDmChungchiList where tenchungchi does not contain UPDATED_TENCHUNGCHI
        defaultQldtDmChungchiShouldBeFound("tenchungchi.doesNotContain=" + UPDATED_TENCHUNGCHI);
    }


    @Test
    @Transactional
    public void getAllQldtDmChungchisByMotaIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where mota equals to DEFAULT_MOTA
        defaultQldtDmChungchiShouldBeFound("mota.equals=" + DEFAULT_MOTA);

        // Get all the qldtDmChungchiList where mota equals to UPDATED_MOTA
        defaultQldtDmChungchiShouldNotBeFound("mota.equals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByMotaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where mota not equals to DEFAULT_MOTA
        defaultQldtDmChungchiShouldNotBeFound("mota.notEquals=" + DEFAULT_MOTA);

        // Get all the qldtDmChungchiList where mota not equals to UPDATED_MOTA
        defaultQldtDmChungchiShouldBeFound("mota.notEquals=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByMotaIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where mota in DEFAULT_MOTA or UPDATED_MOTA
        defaultQldtDmChungchiShouldBeFound("mota.in=" + DEFAULT_MOTA + "," + UPDATED_MOTA);

        // Get all the qldtDmChungchiList where mota equals to UPDATED_MOTA
        defaultQldtDmChungchiShouldNotBeFound("mota.in=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByMotaIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where mota is not null
        defaultQldtDmChungchiShouldBeFound("mota.specified=true");

        // Get all the qldtDmChungchiList where mota is null
        defaultQldtDmChungchiShouldNotBeFound("mota.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDmChungchisByMotaContainsSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where mota contains DEFAULT_MOTA
        defaultQldtDmChungchiShouldBeFound("mota.contains=" + DEFAULT_MOTA);

        // Get all the qldtDmChungchiList where mota contains UPDATED_MOTA
        defaultQldtDmChungchiShouldNotBeFound("mota.contains=" + UPDATED_MOTA);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByMotaNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where mota does not contain DEFAULT_MOTA
        defaultQldtDmChungchiShouldNotBeFound("mota.doesNotContain=" + DEFAULT_MOTA);

        // Get all the qldtDmChungchiList where mota does not contain UPDATED_MOTA
        defaultQldtDmChungchiShouldBeFound("mota.doesNotContain=" + UPDATED_MOTA);
    }


    @Test
    @Transactional
    public void getAllQldtDmChungchisByTrangthaiIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where trangthai equals to DEFAULT_TRANGTHAI
        defaultQldtDmChungchiShouldBeFound("trangthai.equals=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDmChungchiList where trangthai equals to UPDATED_TRANGTHAI
        defaultQldtDmChungchiShouldNotBeFound("trangthai.equals=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByTrangthaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where trangthai not equals to DEFAULT_TRANGTHAI
        defaultQldtDmChungchiShouldNotBeFound("trangthai.notEquals=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDmChungchiList where trangthai not equals to UPDATED_TRANGTHAI
        defaultQldtDmChungchiShouldBeFound("trangthai.notEquals=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByTrangthaiIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where trangthai in DEFAULT_TRANGTHAI or UPDATED_TRANGTHAI
        defaultQldtDmChungchiShouldBeFound("trangthai.in=" + DEFAULT_TRANGTHAI + "," + UPDATED_TRANGTHAI);

        // Get all the qldtDmChungchiList where trangthai equals to UPDATED_TRANGTHAI
        defaultQldtDmChungchiShouldNotBeFound("trangthai.in=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByTrangthaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where trangthai is not null
        defaultQldtDmChungchiShouldBeFound("trangthai.specified=true");

        // Get all the qldtDmChungchiList where trangthai is null
        defaultQldtDmChungchiShouldNotBeFound("trangthai.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByTrangthaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where trangthai is greater than or equal to DEFAULT_TRANGTHAI
        defaultQldtDmChungchiShouldBeFound("trangthai.greaterThanOrEqual=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDmChungchiList where trangthai is greater than or equal to UPDATED_TRANGTHAI
        defaultQldtDmChungchiShouldNotBeFound("trangthai.greaterThanOrEqual=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByTrangthaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where trangthai is less than or equal to DEFAULT_TRANGTHAI
        defaultQldtDmChungchiShouldBeFound("trangthai.lessThanOrEqual=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDmChungchiList where trangthai is less than or equal to SMALLER_TRANGTHAI
        defaultQldtDmChungchiShouldNotBeFound("trangthai.lessThanOrEqual=" + SMALLER_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByTrangthaiIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where trangthai is less than DEFAULT_TRANGTHAI
        defaultQldtDmChungchiShouldNotBeFound("trangthai.lessThan=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDmChungchiList where trangthai is less than UPDATED_TRANGTHAI
        defaultQldtDmChungchiShouldBeFound("trangthai.lessThan=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisByTrangthaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where trangthai is greater than DEFAULT_TRANGTHAI
        defaultQldtDmChungchiShouldNotBeFound("trangthai.greaterThan=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDmChungchiList where trangthai is greater than SMALLER_TRANGTHAI
        defaultQldtDmChungchiShouldBeFound("trangthai.greaterThan=" + SMALLER_TRANGTHAI);
    }


    @Test
    @Transactional
    public void getAllQldtDmChungchisBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where sudung equals to DEFAULT_SUDUNG
        defaultQldtDmChungchiShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the qldtDmChungchiList where sudung equals to UPDATED_SUDUNG
        defaultQldtDmChungchiShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where sudung not equals to DEFAULT_SUDUNG
        defaultQldtDmChungchiShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the qldtDmChungchiList where sudung not equals to UPDATED_SUDUNG
        defaultQldtDmChungchiShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultQldtDmChungchiShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the qldtDmChungchiList where sudung equals to UPDATED_SUDUNG
        defaultQldtDmChungchiShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where sudung is not null
        defaultQldtDmChungchiShouldBeFound("sudung.specified=true");

        // Get all the qldtDmChungchiList where sudung is null
        defaultQldtDmChungchiShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultQldtDmChungchiShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDmChungchiList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultQldtDmChungchiShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultQldtDmChungchiShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDmChungchiList where sudung is less than or equal to SMALLER_SUDUNG
        defaultQldtDmChungchiShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where sudung is less than DEFAULT_SUDUNG
        defaultQldtDmChungchiShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDmChungchiList where sudung is less than UPDATED_SUDUNG
        defaultQldtDmChungchiShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmChungchisBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        // Get all the qldtDmChungchiList where sudung is greater than DEFAULT_SUDUNG
        defaultQldtDmChungchiShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDmChungchiList where sudung is greater than SMALLER_SUDUNG
        defaultQldtDmChungchiShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtDmChungchisByChungChiIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);
        QldtChungChi chungChi = QldtChungChiResourceIT.createEntity(em);
        em.persist(chungChi);
        em.flush();
        qldtDmChungchi.addChungChi(chungChi);
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);
        Long chungChiId = chungChi.getId();

        // Get all the qldtDmChungchiList where chungChi equals to chungChiId
        defaultQldtDmChungchiShouldBeFound("chungChiId.equals=" + chungChiId);

        // Get all the qldtDmChungchiList where chungChi equals to chungChiId + 1
        defaultQldtDmChungchiShouldNotBeFound("chungChiId.equals=" + (chungChiId + 1));
    }


    @Test
    @Transactional
    public void getAllQldtDmChungchisByQldtTochucCapIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);
        QldtTochucCap qldtTochucCap = QldtTochucCapResourceIT.createEntity(em);
        em.persist(qldtTochucCap);
        em.flush();
        qldtDmChungchi.setQldtTochucCap(qldtTochucCap);
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);
        Long qldtTochucCapId = qldtTochucCap.getId();

        // Get all the qldtDmChungchiList where qldtTochucCap equals to qldtTochucCapId
        defaultQldtDmChungchiShouldBeFound("qldtTochucCapId.equals=" + qldtTochucCapId);

        // Get all the qldtDmChungchiList where qldtTochucCap equals to qldtTochucCapId + 1
        defaultQldtDmChungchiShouldNotBeFound("qldtTochucCapId.equals=" + (qldtTochucCapId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQldtDmChungchiShouldBeFound(String filter) throws Exception {
        restQldtDmChungchiMockMvc.perform(get("/api/qldt-dm-chungchis?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDmChungchi.getId().intValue())))
            .andExpect(jsonPath("$.[*].machungchi").value(hasItem(DEFAULT_MACHUNGCHI)))
            .andExpect(jsonPath("$.[*].tenchungchi").value(hasItem(DEFAULT_TENCHUNGCHI)))
            .andExpect(jsonPath("$.[*].mota").value(hasItem(DEFAULT_MOTA)))
            .andExpect(jsonPath("$.[*].trangthai").value(hasItem(DEFAULT_TRANGTHAI)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restQldtDmChungchiMockMvc.perform(get("/api/qldt-dm-chungchis/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQldtDmChungchiShouldNotBeFound(String filter) throws Exception {
        restQldtDmChungchiMockMvc.perform(get("/api/qldt-dm-chungchis?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQldtDmChungchiMockMvc.perform(get("/api/qldt-dm-chungchis/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingQldtDmChungchi() throws Exception {
        // Get the qldtDmChungchi
        restQldtDmChungchiMockMvc.perform(get("/api/qldt-dm-chungchis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQldtDmChungchi() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        int databaseSizeBeforeUpdate = qldtDmChungchiRepository.findAll().size();

        // Update the qldtDmChungchi
        QldtDmChungchi updatedQldtDmChungchi = qldtDmChungchiRepository.findById(qldtDmChungchi.getId()).get();
        // Disconnect from session so that the updates on updatedQldtDmChungchi are not directly saved in db
        em.detach(updatedQldtDmChungchi);
        updatedQldtDmChungchi
            .machungchi(UPDATED_MACHUNGCHI)
            .tenchungchi(UPDATED_TENCHUNGCHI)
            .mota(UPDATED_MOTA)
            .trangthai(UPDATED_TRANGTHAI)
            .sudung(UPDATED_SUDUNG);
        QldtDmChungchiDTO qldtDmChungchiDTO = qldtDmChungchiMapper.toDto(updatedQldtDmChungchi);

        restQldtDmChungchiMockMvc.perform(put("/api/qldt-dm-chungchis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmChungchiDTO)))
            .andExpect(status().isOk());

        // Validate the QldtDmChungchi in the database
        List<QldtDmChungchi> qldtDmChungchiList = qldtDmChungchiRepository.findAll();
        assertThat(qldtDmChungchiList).hasSize(databaseSizeBeforeUpdate);
        QldtDmChungchi testQldtDmChungchi = qldtDmChungchiList.get(qldtDmChungchiList.size() - 1);
        assertThat(testQldtDmChungchi.getMachungchi()).isEqualTo(UPDATED_MACHUNGCHI);
        assertThat(testQldtDmChungchi.getTenchungchi()).isEqualTo(UPDATED_TENCHUNGCHI);
        assertThat(testQldtDmChungchi.getMota()).isEqualTo(UPDATED_MOTA);
        assertThat(testQldtDmChungchi.getTrangthai()).isEqualTo(UPDATED_TRANGTHAI);
        assertThat(testQldtDmChungchi.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingQldtDmChungchi() throws Exception {
        int databaseSizeBeforeUpdate = qldtDmChungchiRepository.findAll().size();

        // Create the QldtDmChungchi
        QldtDmChungchiDTO qldtDmChungchiDTO = qldtDmChungchiMapper.toDto(qldtDmChungchi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQldtDmChungchiMockMvc.perform(put("/api/qldt-dm-chungchis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmChungchiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDmChungchi in the database
        List<QldtDmChungchi> qldtDmChungchiList = qldtDmChungchiRepository.findAll();
        assertThat(qldtDmChungchiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQldtDmChungchi() throws Exception {
        // Initialize the database
        qldtDmChungchiRepository.saveAndFlush(qldtDmChungchi);

        int databaseSizeBeforeDelete = qldtDmChungchiRepository.findAll().size();

        // Delete the qldtDmChungchi
        restQldtDmChungchiMockMvc.perform(delete("/api/qldt-dm-chungchis/{id}", qldtDmChungchi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QldtDmChungchi> qldtDmChungchiList = qldtDmChungchiRepository.findAll();
        assertThat(qldtDmChungchiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
