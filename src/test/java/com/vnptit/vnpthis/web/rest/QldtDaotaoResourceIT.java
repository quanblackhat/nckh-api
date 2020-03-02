package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.qldt.QldtDaotao;
import com.vnptit.vnpthis.domain.qldt.QldtDaotaoCt;
import com.vnptit.vnpthis.domain.qldt.QldtQlHocvien;
import com.vnptit.vnpthis.repository.qldt.QldtDaotaoRepository;
import com.vnptit.vnpthis.service.qldt.QldtDaotaoService;
import com.vnptit.vnpthis.service.dto.QldtDaotaoDTO;
import com.vnptit.vnpthis.service.mapper.QldtDaotaoMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.web.rest.qldt.QldtDaotaoResource;
import com.vnptit.vnpthis.service.dto.QldtDaotaoCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDaotaoQueryService;

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
 * Integration tests for the {@link QldtDaotaoResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class QldtDaotaoResourceIT {

    private static final String DEFAULT_MADAOTAO = "AAAAAAAAAA";
    private static final String UPDATED_MADAOTAO = "BBBBBBBBBB";

    private static final String DEFAULT_TENDAOTAO = "AAAAAAAAAA";
    private static final String UPDATED_TENDAOTAO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_BD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_BD = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_BD = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_NGAY_KT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_KT = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_DIACHI = "AAAAAAAAAA";
    private static final String UPDATED_DIACHI = "BBBBBBBBBB";

    private static final String DEFAULT_DOITUONGCHITIET = "AAAAAAAAAA";
    private static final String UPDATED_DOITUONGCHITIET = "BBBBBBBBBB";

    private static final String DEFAULT_NOIDUNGDAOTAO = "AAAAAAAAAA";
    private static final String UPDATED_NOIDUNGDAOTAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_THOIGIANDAOTAO = 1;
    private static final Integer UPDATED_THOIGIANDAOTAO = 2;
    private static final Integer SMALLER_THOIGIANDAOTAO = 1 - 1;

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private QldtDaotaoRepository qldtDaotaoRepository;

    @Autowired
    private QldtDaotaoMapper qldtDaotaoMapper;

    @Autowired
    private QldtDaotaoService qldtDaotaoService;

    @Autowired
    private QldtDaotaoQueryService qldtDaotaoQueryService;

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

    private MockMvc restQldtDaotaoMockMvc;

    private QldtDaotao qldtDaotao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QldtDaotaoResource qldtDaotaoResource = new QldtDaotaoResource(qldtDaotaoService, qldtDaotaoQueryService);
        this.restQldtDaotaoMockMvc = MockMvcBuilders.standaloneSetup(qldtDaotaoResource)
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
    public static QldtDaotao createEntity(EntityManager em) {
        QldtDaotao qldtDaotao = new QldtDaotao()
            .madaotao(DEFAULT_MADAOTAO)
            .tendaotao(DEFAULT_TENDAOTAO)
            .ngayBd(DEFAULT_NGAY_BD)
            .ngayKt(DEFAULT_NGAY_KT)
            .diachi(DEFAULT_DIACHI)
            .doituongchitiet(DEFAULT_DOITUONGCHITIET)
            .noidungdaotao(DEFAULT_NOIDUNGDAOTAO)
            .thoigiandaotao(DEFAULT_THOIGIANDAOTAO)
            .sudung(DEFAULT_SUDUNG);
        return qldtDaotao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtDaotao createUpdatedEntity(EntityManager em) {
        QldtDaotao qldtDaotao = new QldtDaotao()
            .madaotao(UPDATED_MADAOTAO)
            .tendaotao(UPDATED_TENDAOTAO)
            .ngayBd(UPDATED_NGAY_BD)
            .ngayKt(UPDATED_NGAY_KT)
            .diachi(UPDATED_DIACHI)
            .doituongchitiet(UPDATED_DOITUONGCHITIET)
            .noidungdaotao(UPDATED_NOIDUNGDAOTAO)
            .thoigiandaotao(UPDATED_THOIGIANDAOTAO)
            .sudung(UPDATED_SUDUNG);
        return qldtDaotao;
    }

    @BeforeEach
    public void initTest() {
        qldtDaotao = createEntity(em);
    }

    @Test
    @Transactional
    public void createQldtDaotao() throws Exception {
        int databaseSizeBeforeCreate = qldtDaotaoRepository.findAll().size();

        // Create the QldtDaotao
        QldtDaotaoDTO qldtDaotaoDTO = qldtDaotaoMapper.toDto(qldtDaotao);
        restQldtDaotaoMockMvc.perform(post("/api/qldt-daotaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDaotaoDTO)))
            .andExpect(status().isCreated());

        // Validate the QldtDaotao in the database
        List<QldtDaotao> qldtDaotaoList = qldtDaotaoRepository.findAll();
        assertThat(qldtDaotaoList).hasSize(databaseSizeBeforeCreate + 1);
        QldtDaotao testQldtDaotao = qldtDaotaoList.get(qldtDaotaoList.size() - 1);
        assertThat(testQldtDaotao.getMadaotao()).isEqualTo(DEFAULT_MADAOTAO);
        assertThat(testQldtDaotao.getTendaotao()).isEqualTo(DEFAULT_TENDAOTAO);
        assertThat(testQldtDaotao.getNgayBd()).isEqualTo(DEFAULT_NGAY_BD);
        assertThat(testQldtDaotao.getNgayKt()).isEqualTo(DEFAULT_NGAY_KT);
        assertThat(testQldtDaotao.getDiachi()).isEqualTo(DEFAULT_DIACHI);
        assertThat(testQldtDaotao.getDoituongchitiet()).isEqualTo(DEFAULT_DOITUONGCHITIET);
        assertThat(testQldtDaotao.getNoidungdaotao()).isEqualTo(DEFAULT_NOIDUNGDAOTAO);
        assertThat(testQldtDaotao.getThoigiandaotao()).isEqualTo(DEFAULT_THOIGIANDAOTAO);
        assertThat(testQldtDaotao.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createQldtDaotaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qldtDaotaoRepository.findAll().size();

        // Create the QldtDaotao with an existing ID
        qldtDaotao.setId(1L);
        QldtDaotaoDTO qldtDaotaoDTO = qldtDaotaoMapper.toDto(qldtDaotao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQldtDaotaoMockMvc.perform(post("/api/qldt-daotaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDaotaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDaotao in the database
        List<QldtDaotao> qldtDaotaoList = qldtDaotaoRepository.findAll();
        assertThat(qldtDaotaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaos() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList
        restQldtDaotaoMockMvc.perform(get("/api/qldt-daotaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDaotao.getId().intValue())))
            .andExpect(jsonPath("$.[*].madaotao").value(hasItem(DEFAULT_MADAOTAO)))
            .andExpect(jsonPath("$.[*].tendaotao").value(hasItem(DEFAULT_TENDAOTAO)))
            .andExpect(jsonPath("$.[*].ngayBd").value(hasItem(DEFAULT_NGAY_BD.toString())))
            .andExpect(jsonPath("$.[*].ngayKt").value(hasItem(DEFAULT_NGAY_KT.toString())))
            .andExpect(jsonPath("$.[*].diachi").value(hasItem(DEFAULT_DIACHI)))
            .andExpect(jsonPath("$.[*].doituongchitiet").value(hasItem(DEFAULT_DOITUONGCHITIET)))
            .andExpect(jsonPath("$.[*].noidungdaotao").value(hasItem(DEFAULT_NOIDUNGDAOTAO)))
            .andExpect(jsonPath("$.[*].thoigiandaotao").value(hasItem(DEFAULT_THOIGIANDAOTAO)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getQldtDaotao() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get the qldtDaotao
        restQldtDaotaoMockMvc.perform(get("/api/qldt-daotaos/{id}", qldtDaotao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qldtDaotao.getId().intValue()))
            .andExpect(jsonPath("$.madaotao").value(DEFAULT_MADAOTAO))
            .andExpect(jsonPath("$.tendaotao").value(DEFAULT_TENDAOTAO))
            .andExpect(jsonPath("$.ngayBd").value(DEFAULT_NGAY_BD.toString()))
            .andExpect(jsonPath("$.ngayKt").value(DEFAULT_NGAY_KT.toString()))
            .andExpect(jsonPath("$.diachi").value(DEFAULT_DIACHI))
            .andExpect(jsonPath("$.doituongchitiet").value(DEFAULT_DOITUONGCHITIET))
            .andExpect(jsonPath("$.noidungdaotao").value(DEFAULT_NOIDUNGDAOTAO))
            .andExpect(jsonPath("$.thoigiandaotao").value(DEFAULT_THOIGIANDAOTAO))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getQldtDaotaosByIdFiltering() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        Long id = qldtDaotao.getId();

        defaultQldtDaotaoShouldBeFound("id.equals=" + id);
        defaultQldtDaotaoShouldNotBeFound("id.notEquals=" + id);

        defaultQldtDaotaoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultQldtDaotaoShouldNotBeFound("id.greaterThan=" + id);

        defaultQldtDaotaoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultQldtDaotaoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaosByMadaotaoIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where madaotao equals to DEFAULT_MADAOTAO
        defaultQldtDaotaoShouldBeFound("madaotao.equals=" + DEFAULT_MADAOTAO);

        // Get all the qldtDaotaoList where madaotao equals to UPDATED_MADAOTAO
        defaultQldtDaotaoShouldNotBeFound("madaotao.equals=" + UPDATED_MADAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByMadaotaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where madaotao not equals to DEFAULT_MADAOTAO
        defaultQldtDaotaoShouldNotBeFound("madaotao.notEquals=" + DEFAULT_MADAOTAO);

        // Get all the qldtDaotaoList where madaotao not equals to UPDATED_MADAOTAO
        defaultQldtDaotaoShouldBeFound("madaotao.notEquals=" + UPDATED_MADAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByMadaotaoIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where madaotao in DEFAULT_MADAOTAO or UPDATED_MADAOTAO
        defaultQldtDaotaoShouldBeFound("madaotao.in=" + DEFAULT_MADAOTAO + "," + UPDATED_MADAOTAO);

        // Get all the qldtDaotaoList where madaotao equals to UPDATED_MADAOTAO
        defaultQldtDaotaoShouldNotBeFound("madaotao.in=" + UPDATED_MADAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByMadaotaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where madaotao is not null
        defaultQldtDaotaoShouldBeFound("madaotao.specified=true");

        // Get all the qldtDaotaoList where madaotao is null
        defaultQldtDaotaoShouldNotBeFound("madaotao.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDaotaosByMadaotaoContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where madaotao contains DEFAULT_MADAOTAO
        defaultQldtDaotaoShouldBeFound("madaotao.contains=" + DEFAULT_MADAOTAO);

        // Get all the qldtDaotaoList where madaotao contains UPDATED_MADAOTAO
        defaultQldtDaotaoShouldNotBeFound("madaotao.contains=" + UPDATED_MADAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByMadaotaoNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where madaotao does not contain DEFAULT_MADAOTAO
        defaultQldtDaotaoShouldNotBeFound("madaotao.doesNotContain=" + DEFAULT_MADAOTAO);

        // Get all the qldtDaotaoList where madaotao does not contain UPDATED_MADAOTAO
        defaultQldtDaotaoShouldBeFound("madaotao.doesNotContain=" + UPDATED_MADAOTAO);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaosByTendaotaoIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where tendaotao equals to DEFAULT_TENDAOTAO
        defaultQldtDaotaoShouldBeFound("tendaotao.equals=" + DEFAULT_TENDAOTAO);

        // Get all the qldtDaotaoList where tendaotao equals to UPDATED_TENDAOTAO
        defaultQldtDaotaoShouldNotBeFound("tendaotao.equals=" + UPDATED_TENDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByTendaotaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where tendaotao not equals to DEFAULT_TENDAOTAO
        defaultQldtDaotaoShouldNotBeFound("tendaotao.notEquals=" + DEFAULT_TENDAOTAO);

        // Get all the qldtDaotaoList where tendaotao not equals to UPDATED_TENDAOTAO
        defaultQldtDaotaoShouldBeFound("tendaotao.notEquals=" + UPDATED_TENDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByTendaotaoIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where tendaotao in DEFAULT_TENDAOTAO or UPDATED_TENDAOTAO
        defaultQldtDaotaoShouldBeFound("tendaotao.in=" + DEFAULT_TENDAOTAO + "," + UPDATED_TENDAOTAO);

        // Get all the qldtDaotaoList where tendaotao equals to UPDATED_TENDAOTAO
        defaultQldtDaotaoShouldNotBeFound("tendaotao.in=" + UPDATED_TENDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByTendaotaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where tendaotao is not null
        defaultQldtDaotaoShouldBeFound("tendaotao.specified=true");

        // Get all the qldtDaotaoList where tendaotao is null
        defaultQldtDaotaoShouldNotBeFound("tendaotao.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDaotaosByTendaotaoContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where tendaotao contains DEFAULT_TENDAOTAO
        defaultQldtDaotaoShouldBeFound("tendaotao.contains=" + DEFAULT_TENDAOTAO);

        // Get all the qldtDaotaoList where tendaotao contains UPDATED_TENDAOTAO
        defaultQldtDaotaoShouldNotBeFound("tendaotao.contains=" + UPDATED_TENDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByTendaotaoNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where tendaotao does not contain DEFAULT_TENDAOTAO
        defaultQldtDaotaoShouldNotBeFound("tendaotao.doesNotContain=" + DEFAULT_TENDAOTAO);

        // Get all the qldtDaotaoList where tendaotao does not contain UPDATED_TENDAOTAO
        defaultQldtDaotaoShouldBeFound("tendaotao.doesNotContain=" + UPDATED_TENDAOTAO);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayBdIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayBd equals to DEFAULT_NGAY_BD
        defaultQldtDaotaoShouldBeFound("ngayBd.equals=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoList where ngayBd equals to UPDATED_NGAY_BD
        defaultQldtDaotaoShouldNotBeFound("ngayBd.equals=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayBdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayBd not equals to DEFAULT_NGAY_BD
        defaultQldtDaotaoShouldNotBeFound("ngayBd.notEquals=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoList where ngayBd not equals to UPDATED_NGAY_BD
        defaultQldtDaotaoShouldBeFound("ngayBd.notEquals=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayBdIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayBd in DEFAULT_NGAY_BD or UPDATED_NGAY_BD
        defaultQldtDaotaoShouldBeFound("ngayBd.in=" + DEFAULT_NGAY_BD + "," + UPDATED_NGAY_BD);

        // Get all the qldtDaotaoList where ngayBd equals to UPDATED_NGAY_BD
        defaultQldtDaotaoShouldNotBeFound("ngayBd.in=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayBdIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayBd is not null
        defaultQldtDaotaoShouldBeFound("ngayBd.specified=true");

        // Get all the qldtDaotaoList where ngayBd is null
        defaultQldtDaotaoShouldNotBeFound("ngayBd.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayBdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayBd is greater than or equal to DEFAULT_NGAY_BD
        defaultQldtDaotaoShouldBeFound("ngayBd.greaterThanOrEqual=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoList where ngayBd is greater than or equal to UPDATED_NGAY_BD
        defaultQldtDaotaoShouldNotBeFound("ngayBd.greaterThanOrEqual=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayBdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayBd is less than or equal to DEFAULT_NGAY_BD
        defaultQldtDaotaoShouldBeFound("ngayBd.lessThanOrEqual=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoList where ngayBd is less than or equal to SMALLER_NGAY_BD
        defaultQldtDaotaoShouldNotBeFound("ngayBd.lessThanOrEqual=" + SMALLER_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayBdIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayBd is less than DEFAULT_NGAY_BD
        defaultQldtDaotaoShouldNotBeFound("ngayBd.lessThan=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoList where ngayBd is less than UPDATED_NGAY_BD
        defaultQldtDaotaoShouldBeFound("ngayBd.lessThan=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayBdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayBd is greater than DEFAULT_NGAY_BD
        defaultQldtDaotaoShouldNotBeFound("ngayBd.greaterThan=" + DEFAULT_NGAY_BD);

        // Get all the qldtDaotaoList where ngayBd is greater than SMALLER_NGAY_BD
        defaultQldtDaotaoShouldBeFound("ngayBd.greaterThan=" + SMALLER_NGAY_BD);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayKtIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayKt equals to DEFAULT_NGAY_KT
        defaultQldtDaotaoShouldBeFound("ngayKt.equals=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoList where ngayKt equals to UPDATED_NGAY_KT
        defaultQldtDaotaoShouldNotBeFound("ngayKt.equals=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayKtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayKt not equals to DEFAULT_NGAY_KT
        defaultQldtDaotaoShouldNotBeFound("ngayKt.notEquals=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoList where ngayKt not equals to UPDATED_NGAY_KT
        defaultQldtDaotaoShouldBeFound("ngayKt.notEquals=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayKtIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayKt in DEFAULT_NGAY_KT or UPDATED_NGAY_KT
        defaultQldtDaotaoShouldBeFound("ngayKt.in=" + DEFAULT_NGAY_KT + "," + UPDATED_NGAY_KT);

        // Get all the qldtDaotaoList where ngayKt equals to UPDATED_NGAY_KT
        defaultQldtDaotaoShouldNotBeFound("ngayKt.in=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayKtIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayKt is not null
        defaultQldtDaotaoShouldBeFound("ngayKt.specified=true");

        // Get all the qldtDaotaoList where ngayKt is null
        defaultQldtDaotaoShouldNotBeFound("ngayKt.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayKtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayKt is greater than or equal to DEFAULT_NGAY_KT
        defaultQldtDaotaoShouldBeFound("ngayKt.greaterThanOrEqual=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoList where ngayKt is greater than or equal to UPDATED_NGAY_KT
        defaultQldtDaotaoShouldNotBeFound("ngayKt.greaterThanOrEqual=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayKtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayKt is less than or equal to DEFAULT_NGAY_KT
        defaultQldtDaotaoShouldBeFound("ngayKt.lessThanOrEqual=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoList where ngayKt is less than or equal to SMALLER_NGAY_KT
        defaultQldtDaotaoShouldNotBeFound("ngayKt.lessThanOrEqual=" + SMALLER_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayKtIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayKt is less than DEFAULT_NGAY_KT
        defaultQldtDaotaoShouldNotBeFound("ngayKt.lessThan=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoList where ngayKt is less than UPDATED_NGAY_KT
        defaultQldtDaotaoShouldBeFound("ngayKt.lessThan=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNgayKtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where ngayKt is greater than DEFAULT_NGAY_KT
        defaultQldtDaotaoShouldNotBeFound("ngayKt.greaterThan=" + DEFAULT_NGAY_KT);

        // Get all the qldtDaotaoList where ngayKt is greater than SMALLER_NGAY_KT
        defaultQldtDaotaoShouldBeFound("ngayKt.greaterThan=" + SMALLER_NGAY_KT);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaosByDiachiIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where diachi equals to DEFAULT_DIACHI
        defaultQldtDaotaoShouldBeFound("diachi.equals=" + DEFAULT_DIACHI);

        // Get all the qldtDaotaoList where diachi equals to UPDATED_DIACHI
        defaultQldtDaotaoShouldNotBeFound("diachi.equals=" + UPDATED_DIACHI);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByDiachiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where diachi not equals to DEFAULT_DIACHI
        defaultQldtDaotaoShouldNotBeFound("diachi.notEquals=" + DEFAULT_DIACHI);

        // Get all the qldtDaotaoList where diachi not equals to UPDATED_DIACHI
        defaultQldtDaotaoShouldBeFound("diachi.notEquals=" + UPDATED_DIACHI);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByDiachiIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where diachi in DEFAULT_DIACHI or UPDATED_DIACHI
        defaultQldtDaotaoShouldBeFound("diachi.in=" + DEFAULT_DIACHI + "," + UPDATED_DIACHI);

        // Get all the qldtDaotaoList where diachi equals to UPDATED_DIACHI
        defaultQldtDaotaoShouldNotBeFound("diachi.in=" + UPDATED_DIACHI);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByDiachiIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where diachi is not null
        defaultQldtDaotaoShouldBeFound("diachi.specified=true");

        // Get all the qldtDaotaoList where diachi is null
        defaultQldtDaotaoShouldNotBeFound("diachi.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDaotaosByDiachiContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where diachi contains DEFAULT_DIACHI
        defaultQldtDaotaoShouldBeFound("diachi.contains=" + DEFAULT_DIACHI);

        // Get all the qldtDaotaoList where diachi contains UPDATED_DIACHI
        defaultQldtDaotaoShouldNotBeFound("diachi.contains=" + UPDATED_DIACHI);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByDiachiNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where diachi does not contain DEFAULT_DIACHI
        defaultQldtDaotaoShouldNotBeFound("diachi.doesNotContain=" + DEFAULT_DIACHI);

        // Get all the qldtDaotaoList where diachi does not contain UPDATED_DIACHI
        defaultQldtDaotaoShouldBeFound("diachi.doesNotContain=" + UPDATED_DIACHI);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaosByDoituongchitietIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where doituongchitiet equals to DEFAULT_DOITUONGCHITIET
        defaultQldtDaotaoShouldBeFound("doituongchitiet.equals=" + DEFAULT_DOITUONGCHITIET);

        // Get all the qldtDaotaoList where doituongchitiet equals to UPDATED_DOITUONGCHITIET
        defaultQldtDaotaoShouldNotBeFound("doituongchitiet.equals=" + UPDATED_DOITUONGCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByDoituongchitietIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where doituongchitiet not equals to DEFAULT_DOITUONGCHITIET
        defaultQldtDaotaoShouldNotBeFound("doituongchitiet.notEquals=" + DEFAULT_DOITUONGCHITIET);

        // Get all the qldtDaotaoList where doituongchitiet not equals to UPDATED_DOITUONGCHITIET
        defaultQldtDaotaoShouldBeFound("doituongchitiet.notEquals=" + UPDATED_DOITUONGCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByDoituongchitietIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where doituongchitiet in DEFAULT_DOITUONGCHITIET or UPDATED_DOITUONGCHITIET
        defaultQldtDaotaoShouldBeFound("doituongchitiet.in=" + DEFAULT_DOITUONGCHITIET + "," + UPDATED_DOITUONGCHITIET);

        // Get all the qldtDaotaoList where doituongchitiet equals to UPDATED_DOITUONGCHITIET
        defaultQldtDaotaoShouldNotBeFound("doituongchitiet.in=" + UPDATED_DOITUONGCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByDoituongchitietIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where doituongchitiet is not null
        defaultQldtDaotaoShouldBeFound("doituongchitiet.specified=true");

        // Get all the qldtDaotaoList where doituongchitiet is null
        defaultQldtDaotaoShouldNotBeFound("doituongchitiet.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDaotaosByDoituongchitietContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where doituongchitiet contains DEFAULT_DOITUONGCHITIET
        defaultQldtDaotaoShouldBeFound("doituongchitiet.contains=" + DEFAULT_DOITUONGCHITIET);

        // Get all the qldtDaotaoList where doituongchitiet contains UPDATED_DOITUONGCHITIET
        defaultQldtDaotaoShouldNotBeFound("doituongchitiet.contains=" + UPDATED_DOITUONGCHITIET);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByDoituongchitietNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where doituongchitiet does not contain DEFAULT_DOITUONGCHITIET
        defaultQldtDaotaoShouldNotBeFound("doituongchitiet.doesNotContain=" + DEFAULT_DOITUONGCHITIET);

        // Get all the qldtDaotaoList where doituongchitiet does not contain UPDATED_DOITUONGCHITIET
        defaultQldtDaotaoShouldBeFound("doituongchitiet.doesNotContain=" + UPDATED_DOITUONGCHITIET);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaosByNoidungdaotaoIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where noidungdaotao equals to DEFAULT_NOIDUNGDAOTAO
        defaultQldtDaotaoShouldBeFound("noidungdaotao.equals=" + DEFAULT_NOIDUNGDAOTAO);

        // Get all the qldtDaotaoList where noidungdaotao equals to UPDATED_NOIDUNGDAOTAO
        defaultQldtDaotaoShouldNotBeFound("noidungdaotao.equals=" + UPDATED_NOIDUNGDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNoidungdaotaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where noidungdaotao not equals to DEFAULT_NOIDUNGDAOTAO
        defaultQldtDaotaoShouldNotBeFound("noidungdaotao.notEquals=" + DEFAULT_NOIDUNGDAOTAO);

        // Get all the qldtDaotaoList where noidungdaotao not equals to UPDATED_NOIDUNGDAOTAO
        defaultQldtDaotaoShouldBeFound("noidungdaotao.notEquals=" + UPDATED_NOIDUNGDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNoidungdaotaoIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where noidungdaotao in DEFAULT_NOIDUNGDAOTAO or UPDATED_NOIDUNGDAOTAO
        defaultQldtDaotaoShouldBeFound("noidungdaotao.in=" + DEFAULT_NOIDUNGDAOTAO + "," + UPDATED_NOIDUNGDAOTAO);

        // Get all the qldtDaotaoList where noidungdaotao equals to UPDATED_NOIDUNGDAOTAO
        defaultQldtDaotaoShouldNotBeFound("noidungdaotao.in=" + UPDATED_NOIDUNGDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNoidungdaotaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where noidungdaotao is not null
        defaultQldtDaotaoShouldBeFound("noidungdaotao.specified=true");

        // Get all the qldtDaotaoList where noidungdaotao is null
        defaultQldtDaotaoShouldNotBeFound("noidungdaotao.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDaotaosByNoidungdaotaoContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where noidungdaotao contains DEFAULT_NOIDUNGDAOTAO
        defaultQldtDaotaoShouldBeFound("noidungdaotao.contains=" + DEFAULT_NOIDUNGDAOTAO);

        // Get all the qldtDaotaoList where noidungdaotao contains UPDATED_NOIDUNGDAOTAO
        defaultQldtDaotaoShouldNotBeFound("noidungdaotao.contains=" + UPDATED_NOIDUNGDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByNoidungdaotaoNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where noidungdaotao does not contain DEFAULT_NOIDUNGDAOTAO
        defaultQldtDaotaoShouldNotBeFound("noidungdaotao.doesNotContain=" + DEFAULT_NOIDUNGDAOTAO);

        // Get all the qldtDaotaoList where noidungdaotao does not contain UPDATED_NOIDUNGDAOTAO
        defaultQldtDaotaoShouldBeFound("noidungdaotao.doesNotContain=" + UPDATED_NOIDUNGDAOTAO);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaosByThoigiandaotaoIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where thoigiandaotao equals to DEFAULT_THOIGIANDAOTAO
        defaultQldtDaotaoShouldBeFound("thoigiandaotao.equals=" + DEFAULT_THOIGIANDAOTAO);

        // Get all the qldtDaotaoList where thoigiandaotao equals to UPDATED_THOIGIANDAOTAO
        defaultQldtDaotaoShouldNotBeFound("thoigiandaotao.equals=" + UPDATED_THOIGIANDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByThoigiandaotaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where thoigiandaotao not equals to DEFAULT_THOIGIANDAOTAO
        defaultQldtDaotaoShouldNotBeFound("thoigiandaotao.notEquals=" + DEFAULT_THOIGIANDAOTAO);

        // Get all the qldtDaotaoList where thoigiandaotao not equals to UPDATED_THOIGIANDAOTAO
        defaultQldtDaotaoShouldBeFound("thoigiandaotao.notEquals=" + UPDATED_THOIGIANDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByThoigiandaotaoIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where thoigiandaotao in DEFAULT_THOIGIANDAOTAO or UPDATED_THOIGIANDAOTAO
        defaultQldtDaotaoShouldBeFound("thoigiandaotao.in=" + DEFAULT_THOIGIANDAOTAO + "," + UPDATED_THOIGIANDAOTAO);

        // Get all the qldtDaotaoList where thoigiandaotao equals to UPDATED_THOIGIANDAOTAO
        defaultQldtDaotaoShouldNotBeFound("thoigiandaotao.in=" + UPDATED_THOIGIANDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByThoigiandaotaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where thoigiandaotao is not null
        defaultQldtDaotaoShouldBeFound("thoigiandaotao.specified=true");

        // Get all the qldtDaotaoList where thoigiandaotao is null
        defaultQldtDaotaoShouldNotBeFound("thoigiandaotao.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByThoigiandaotaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where thoigiandaotao is greater than or equal to DEFAULT_THOIGIANDAOTAO
        defaultQldtDaotaoShouldBeFound("thoigiandaotao.greaterThanOrEqual=" + DEFAULT_THOIGIANDAOTAO);

        // Get all the qldtDaotaoList where thoigiandaotao is greater than or equal to UPDATED_THOIGIANDAOTAO
        defaultQldtDaotaoShouldNotBeFound("thoigiandaotao.greaterThanOrEqual=" + UPDATED_THOIGIANDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByThoigiandaotaoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where thoigiandaotao is less than or equal to DEFAULT_THOIGIANDAOTAO
        defaultQldtDaotaoShouldBeFound("thoigiandaotao.lessThanOrEqual=" + DEFAULT_THOIGIANDAOTAO);

        // Get all the qldtDaotaoList where thoigiandaotao is less than or equal to SMALLER_THOIGIANDAOTAO
        defaultQldtDaotaoShouldNotBeFound("thoigiandaotao.lessThanOrEqual=" + SMALLER_THOIGIANDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByThoigiandaotaoIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where thoigiandaotao is less than DEFAULT_THOIGIANDAOTAO
        defaultQldtDaotaoShouldNotBeFound("thoigiandaotao.lessThan=" + DEFAULT_THOIGIANDAOTAO);

        // Get all the qldtDaotaoList where thoigiandaotao is less than UPDATED_THOIGIANDAOTAO
        defaultQldtDaotaoShouldBeFound("thoigiandaotao.lessThan=" + UPDATED_THOIGIANDAOTAO);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosByThoigiandaotaoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where thoigiandaotao is greater than DEFAULT_THOIGIANDAOTAO
        defaultQldtDaotaoShouldNotBeFound("thoigiandaotao.greaterThan=" + DEFAULT_THOIGIANDAOTAO);

        // Get all the qldtDaotaoList where thoigiandaotao is greater than SMALLER_THOIGIANDAOTAO
        defaultQldtDaotaoShouldBeFound("thoigiandaotao.greaterThan=" + SMALLER_THOIGIANDAOTAO);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaosBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where sudung equals to DEFAULT_SUDUNG
        defaultQldtDaotaoShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoList where sudung equals to UPDATED_SUDUNG
        defaultQldtDaotaoShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where sudung not equals to DEFAULT_SUDUNG
        defaultQldtDaotaoShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoList where sudung not equals to UPDATED_SUDUNG
        defaultQldtDaotaoShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultQldtDaotaoShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the qldtDaotaoList where sudung equals to UPDATED_SUDUNG
        defaultQldtDaotaoShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where sudung is not null
        defaultQldtDaotaoShouldBeFound("sudung.specified=true");

        // Get all the qldtDaotaoList where sudung is null
        defaultQldtDaotaoShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultQldtDaotaoShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultQldtDaotaoShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultQldtDaotaoShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoList where sudung is less than or equal to SMALLER_SUDUNG
        defaultQldtDaotaoShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where sudung is less than DEFAULT_SUDUNG
        defaultQldtDaotaoShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoList where sudung is less than UPDATED_SUDUNG
        defaultQldtDaotaoShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDaotaosBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        // Get all the qldtDaotaoList where sudung is greater than DEFAULT_SUDUNG
        defaultQldtDaotaoShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDaotaoList where sudung is greater than SMALLER_SUDUNG
        defaultQldtDaotaoShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtDaotaosByDaoTaoCtIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);
        QldtDaotaoCt daoTaoCt = QldtDaotaoCtResourceIT.createEntity(em);
        em.persist(daoTaoCt);
        em.flush();
        qldtDaotao.addDaoTaoCt(daoTaoCt);
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);
        Long daoTaoCtId = daoTaoCt.getId();

        // Get all the qldtDaotaoList where daoTaoCt equals to daoTaoCtId
        defaultQldtDaotaoShouldBeFound("daoTaoCtId.equals=" + daoTaoCtId);

        // Get all the qldtDaotaoList where daoTaoCt equals to daoTaoCtId + 1
        defaultQldtDaotaoShouldNotBeFound("daoTaoCtId.equals=" + (daoTaoCtId + 1));
    }


    @Test
    @Transactional
    public void getAllQldtDaotaosByHocVienIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);
        QldtQlHocvien hocVien = QldtQlHocvienResourceIT.createEntity(em);
        em.persist(hocVien);
        em.flush();
        qldtDaotao.addHocVien(hocVien);
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);
        Long hocVienId = hocVien.getId();

        // Get all the qldtDaotaoList where hocVien equals to hocVienId
        defaultQldtDaotaoShouldBeFound("hocVienId.equals=" + hocVienId);

        // Get all the qldtDaotaoList where hocVien equals to hocVienId + 1
        defaultQldtDaotaoShouldNotBeFound("hocVienId.equals=" + (hocVienId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQldtDaotaoShouldBeFound(String filter) throws Exception {
        restQldtDaotaoMockMvc.perform(get("/api/qldt-daotaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDaotao.getId().intValue())))
            .andExpect(jsonPath("$.[*].madaotao").value(hasItem(DEFAULT_MADAOTAO)))
            .andExpect(jsonPath("$.[*].tendaotao").value(hasItem(DEFAULT_TENDAOTAO)))
            .andExpect(jsonPath("$.[*].ngayBd").value(hasItem(DEFAULT_NGAY_BD.toString())))
            .andExpect(jsonPath("$.[*].ngayKt").value(hasItem(DEFAULT_NGAY_KT.toString())))
            .andExpect(jsonPath("$.[*].diachi").value(hasItem(DEFAULT_DIACHI)))
            .andExpect(jsonPath("$.[*].doituongchitiet").value(hasItem(DEFAULT_DOITUONGCHITIET)))
            .andExpect(jsonPath("$.[*].noidungdaotao").value(hasItem(DEFAULT_NOIDUNGDAOTAO)))
            .andExpect(jsonPath("$.[*].thoigiandaotao").value(hasItem(DEFAULT_THOIGIANDAOTAO)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restQldtDaotaoMockMvc.perform(get("/api/qldt-daotaos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQldtDaotaoShouldNotBeFound(String filter) throws Exception {
        restQldtDaotaoMockMvc.perform(get("/api/qldt-daotaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQldtDaotaoMockMvc.perform(get("/api/qldt-daotaos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingQldtDaotao() throws Exception {
        // Get the qldtDaotao
        restQldtDaotaoMockMvc.perform(get("/api/qldt-daotaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQldtDaotao() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        int databaseSizeBeforeUpdate = qldtDaotaoRepository.findAll().size();

        // Update the qldtDaotao
        QldtDaotao updatedQldtDaotao = qldtDaotaoRepository.findById(qldtDaotao.getId()).get();
        // Disconnect from session so that the updates on updatedQldtDaotao are not directly saved in db
        em.detach(updatedQldtDaotao);
        updatedQldtDaotao
            .madaotao(UPDATED_MADAOTAO)
            .tendaotao(UPDATED_TENDAOTAO)
            .ngayBd(UPDATED_NGAY_BD)
            .ngayKt(UPDATED_NGAY_KT)
            .diachi(UPDATED_DIACHI)
            .doituongchitiet(UPDATED_DOITUONGCHITIET)
            .noidungdaotao(UPDATED_NOIDUNGDAOTAO)
            .thoigiandaotao(UPDATED_THOIGIANDAOTAO)
            .sudung(UPDATED_SUDUNG);
        QldtDaotaoDTO qldtDaotaoDTO = qldtDaotaoMapper.toDto(updatedQldtDaotao);

        restQldtDaotaoMockMvc.perform(put("/api/qldt-daotaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDaotaoDTO)))
            .andExpect(status().isOk());

        // Validate the QldtDaotao in the database
        List<QldtDaotao> qldtDaotaoList = qldtDaotaoRepository.findAll();
        assertThat(qldtDaotaoList).hasSize(databaseSizeBeforeUpdate);
        QldtDaotao testQldtDaotao = qldtDaotaoList.get(qldtDaotaoList.size() - 1);
        assertThat(testQldtDaotao.getMadaotao()).isEqualTo(UPDATED_MADAOTAO);
        assertThat(testQldtDaotao.getTendaotao()).isEqualTo(UPDATED_TENDAOTAO);
        assertThat(testQldtDaotao.getNgayBd()).isEqualTo(UPDATED_NGAY_BD);
        assertThat(testQldtDaotao.getNgayKt()).isEqualTo(UPDATED_NGAY_KT);
        assertThat(testQldtDaotao.getDiachi()).isEqualTo(UPDATED_DIACHI);
        assertThat(testQldtDaotao.getDoituongchitiet()).isEqualTo(UPDATED_DOITUONGCHITIET);
        assertThat(testQldtDaotao.getNoidungdaotao()).isEqualTo(UPDATED_NOIDUNGDAOTAO);
        assertThat(testQldtDaotao.getThoigiandaotao()).isEqualTo(UPDATED_THOIGIANDAOTAO);
        assertThat(testQldtDaotao.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingQldtDaotao() throws Exception {
        int databaseSizeBeforeUpdate = qldtDaotaoRepository.findAll().size();

        // Create the QldtDaotao
        QldtDaotaoDTO qldtDaotaoDTO = qldtDaotaoMapper.toDto(qldtDaotao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQldtDaotaoMockMvc.perform(put("/api/qldt-daotaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDaotaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDaotao in the database
        List<QldtDaotao> qldtDaotaoList = qldtDaotaoRepository.findAll();
        assertThat(qldtDaotaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQldtDaotao() throws Exception {
        // Initialize the database
        qldtDaotaoRepository.saveAndFlush(qldtDaotao);

        int databaseSizeBeforeDelete = qldtDaotaoRepository.findAll().size();

        // Delete the qldtDaotao
        restQldtDaotaoMockMvc.perform(delete("/api/qldt-daotaos/{id}", qldtDaotao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QldtDaotao> qldtDaotaoList = qldtDaotaoRepository.findAll();
        assertThat(qldtDaotaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
