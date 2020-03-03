package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotao;
import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotaoct;
import com.vnptit.vnpthis.repository.qldt.QldtDutoanDaotaoRepository;
import com.vnptit.vnpthis.service.qldt.QldtDutoanDaotaoService;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoDTO;
import com.vnptit.vnpthis.service.mapper.QldtDutoanDaotaoMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.web.rest.qldt.QldtDutoanDaotaoResource;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDutoanDaotaoQueryService;

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
 * Integration tests for the {@link QldtDutoanDaotaoResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class QldtDutoanDaotaoResourceIT {

    private static final Integer DEFAULT_SOLOP = 1;
    private static final Integer UPDATED_SOLOP = 2;
    private static final Integer SMALLER_SOLOP = 1 - 1;

    private static final Integer DEFAULT_SOHOCVIEN = 1;
    private static final Integer UPDATED_SOHOCVIEN = 2;
    private static final Integer SMALLER_SOHOCVIEN = 1 - 1;

    private static final Integer DEFAULT_DATHANHTOAN = 1;
    private static final Integer UPDATED_DATHANHTOAN = 2;
    private static final Integer SMALLER_DATHANHTOAN = 1 - 1;

    private static final Integer DEFAULT_MADUTOAN = 1;
    private static final Integer UPDATED_MADUTOAN = 2;
    private static final Integer SMALLER_MADUTOAN = 1 - 1;

    private static final String DEFAULT_TENDUTOAN = "AAAAAAAAAA";
    private static final String UPDATED_TENDUTOAN = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANGTHAI = 1;
    private static final Integer UPDATED_TRANGTHAI = 2;
    private static final Integer SMALLER_TRANGTHAI = 1 - 1;

    private static final LocalDate DEFAULT_NGAY_BD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_BD = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_BD = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_NGAY_KT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_KT = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private QldtDutoanDaotaoRepository qldtDutoanDaotaoRepository;

    @Autowired
    private QldtDutoanDaotaoMapper qldtDutoanDaotaoMapper;

    @Autowired
    private QldtDutoanDaotaoService qldtDutoanDaotaoService;

    @Autowired
    private QldtDutoanDaotaoQueryService qldtDutoanDaotaoQueryService;

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

    private MockMvc restQldtDutoanDaotaoMockMvc;

    private QldtDutoanDaotao qldtDutoanDaotao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QldtDutoanDaotaoResource qldtDutoanDaotaoResource = new QldtDutoanDaotaoResource(qldtDutoanDaotaoService, qldtDutoanDaotaoQueryService);
        this.restQldtDutoanDaotaoMockMvc = MockMvcBuilders.standaloneSetup(qldtDutoanDaotaoResource)
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
    public static QldtDutoanDaotao createEntity(EntityManager em) {
        QldtDutoanDaotao qldtDutoanDaotao = new QldtDutoanDaotao()
            .solop(DEFAULT_SOLOP)
            .sohocvien(DEFAULT_SOHOCVIEN)
            .dathanhtoan(DEFAULT_DATHANHTOAN)
            .madutoan(DEFAULT_MADUTOAN)
            .tendutoan(DEFAULT_TENDUTOAN)
            .trangthai(DEFAULT_TRANGTHAI)
            .ngayBd(DEFAULT_NGAY_BD)
            .ngayKt(DEFAULT_NGAY_KT)
            .sudung(DEFAULT_SUDUNG);
        return qldtDutoanDaotao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtDutoanDaotao createUpdatedEntity(EntityManager em) {
        QldtDutoanDaotao qldtDutoanDaotao = new QldtDutoanDaotao()
            .solop(UPDATED_SOLOP)
            .sohocvien(UPDATED_SOHOCVIEN)
            .dathanhtoan(UPDATED_DATHANHTOAN)
            .madutoan(UPDATED_MADUTOAN)
            .tendutoan(UPDATED_TENDUTOAN)
            .trangthai(UPDATED_TRANGTHAI)
            .ngayBd(UPDATED_NGAY_BD)
            .ngayKt(UPDATED_NGAY_KT)
            .sudung(UPDATED_SUDUNG);
        return qldtDutoanDaotao;
    }

    @BeforeEach
    public void initTest() {
        qldtDutoanDaotao = createEntity(em);
    }

    @Test
    @Transactional
    public void createQldtDutoanDaotao() throws Exception {
        int databaseSizeBeforeCreate = qldtDutoanDaotaoRepository.findAll().size();

        // Create the QldtDutoanDaotao
        QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO = qldtDutoanDaotaoMapper.toDto(qldtDutoanDaotao);
        restQldtDutoanDaotaoMockMvc.perform(post("/api/qldt-dutoan-daotaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDutoanDaotaoDTO)))
            .andExpect(status().isCreated());

        // Validate the QldtDutoanDaotao in the database
        List<QldtDutoanDaotao> qldtDutoanDaotaoList = qldtDutoanDaotaoRepository.findAll();
        assertThat(qldtDutoanDaotaoList).hasSize(databaseSizeBeforeCreate + 1);
        QldtDutoanDaotao testQldtDutoanDaotao = qldtDutoanDaotaoList.get(qldtDutoanDaotaoList.size() - 1);
        assertThat(testQldtDutoanDaotao.getSolop()).isEqualTo(DEFAULT_SOLOP);
        assertThat(testQldtDutoanDaotao.getSohocvien()).isEqualTo(DEFAULT_SOHOCVIEN);
        assertThat(testQldtDutoanDaotao.getDathanhtoan()).isEqualTo(DEFAULT_DATHANHTOAN);
        assertThat(testQldtDutoanDaotao.getMadutoan()).isEqualTo(DEFAULT_MADUTOAN);
        assertThat(testQldtDutoanDaotao.getTendutoan()).isEqualTo(DEFAULT_TENDUTOAN);
        assertThat(testQldtDutoanDaotao.getTrangthai()).isEqualTo(DEFAULT_TRANGTHAI);
        assertThat(testQldtDutoanDaotao.getNgayBd()).isEqualTo(DEFAULT_NGAY_BD);
        assertThat(testQldtDutoanDaotao.getNgayKt()).isEqualTo(DEFAULT_NGAY_KT);
        assertThat(testQldtDutoanDaotao.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createQldtDutoanDaotaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qldtDutoanDaotaoRepository.findAll().size();

        // Create the QldtDutoanDaotao with an existing ID
        qldtDutoanDaotao.setId(1L);
        QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO = qldtDutoanDaotaoMapper.toDto(qldtDutoanDaotao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQldtDutoanDaotaoMockMvc.perform(post("/api/qldt-dutoan-daotaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDutoanDaotaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDutoanDaotao in the database
        List<QldtDutoanDaotao> qldtDutoanDaotaoList = qldtDutoanDaotaoRepository.findAll();
        assertThat(qldtDutoanDaotaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaos() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList
        restQldtDutoanDaotaoMockMvc.perform(get("/api/qldt-dutoan-daotaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDutoanDaotao.getId().intValue())))
            .andExpect(jsonPath("$.[*].solop").value(hasItem(DEFAULT_SOLOP)))
            .andExpect(jsonPath("$.[*].sohocvien").value(hasItem(DEFAULT_SOHOCVIEN)))
            .andExpect(jsonPath("$.[*].dathanhtoan").value(hasItem(DEFAULT_DATHANHTOAN)))
            .andExpect(jsonPath("$.[*].madutoan").value(hasItem(DEFAULT_MADUTOAN)))
            .andExpect(jsonPath("$.[*].tendutoan").value(hasItem(DEFAULT_TENDUTOAN)))
            .andExpect(jsonPath("$.[*].trangthai").value(hasItem(DEFAULT_TRANGTHAI)))
            .andExpect(jsonPath("$.[*].ngayBd").value(hasItem(DEFAULT_NGAY_BD.toString())))
            .andExpect(jsonPath("$.[*].ngayKt").value(hasItem(DEFAULT_NGAY_KT.toString())))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getQldtDutoanDaotao() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get the qldtDutoanDaotao
        restQldtDutoanDaotaoMockMvc.perform(get("/api/qldt-dutoan-daotaos/{id}", qldtDutoanDaotao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qldtDutoanDaotao.getId().intValue()))
            .andExpect(jsonPath("$.solop").value(DEFAULT_SOLOP))
            .andExpect(jsonPath("$.sohocvien").value(DEFAULT_SOHOCVIEN))
            .andExpect(jsonPath("$.dathanhtoan").value(DEFAULT_DATHANHTOAN))
            .andExpect(jsonPath("$.madutoan").value(DEFAULT_MADUTOAN))
            .andExpect(jsonPath("$.tendutoan").value(DEFAULT_TENDUTOAN))
            .andExpect(jsonPath("$.trangthai").value(DEFAULT_TRANGTHAI))
            .andExpect(jsonPath("$.ngayBd").value(DEFAULT_NGAY_BD.toString()))
            .andExpect(jsonPath("$.ngayKt").value(DEFAULT_NGAY_KT.toString()))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getQldtDutoanDaotaosByIdFiltering() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        Long id = qldtDutoanDaotao.getId();

        defaultQldtDutoanDaotaoShouldBeFound("id.equals=" + id);
        defaultQldtDutoanDaotaoShouldNotBeFound("id.notEquals=" + id);

        defaultQldtDutoanDaotaoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultQldtDutoanDaotaoShouldNotBeFound("id.greaterThan=" + id);

        defaultQldtDutoanDaotaoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultQldtDutoanDaotaoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySolopIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where solop equals to DEFAULT_SOLOP
        defaultQldtDutoanDaotaoShouldBeFound("solop.equals=" + DEFAULT_SOLOP);

        // Get all the qldtDutoanDaotaoList where solop equals to UPDATED_SOLOP
        defaultQldtDutoanDaotaoShouldNotBeFound("solop.equals=" + UPDATED_SOLOP);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySolopIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where solop not equals to DEFAULT_SOLOP
        defaultQldtDutoanDaotaoShouldNotBeFound("solop.notEquals=" + DEFAULT_SOLOP);

        // Get all the qldtDutoanDaotaoList where solop not equals to UPDATED_SOLOP
        defaultQldtDutoanDaotaoShouldBeFound("solop.notEquals=" + UPDATED_SOLOP);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySolopIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where solop in DEFAULT_SOLOP or UPDATED_SOLOP
        defaultQldtDutoanDaotaoShouldBeFound("solop.in=" + DEFAULT_SOLOP + "," + UPDATED_SOLOP);

        // Get all the qldtDutoanDaotaoList where solop equals to UPDATED_SOLOP
        defaultQldtDutoanDaotaoShouldNotBeFound("solop.in=" + UPDATED_SOLOP);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySolopIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where solop is not null
        defaultQldtDutoanDaotaoShouldBeFound("solop.specified=true");

        // Get all the qldtDutoanDaotaoList where solop is null
        defaultQldtDutoanDaotaoShouldNotBeFound("solop.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySolopIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where solop is greater than or equal to DEFAULT_SOLOP
        defaultQldtDutoanDaotaoShouldBeFound("solop.greaterThanOrEqual=" + DEFAULT_SOLOP);

        // Get all the qldtDutoanDaotaoList where solop is greater than or equal to UPDATED_SOLOP
        defaultQldtDutoanDaotaoShouldNotBeFound("solop.greaterThanOrEqual=" + UPDATED_SOLOP);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySolopIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where solop is less than or equal to DEFAULT_SOLOP
        defaultQldtDutoanDaotaoShouldBeFound("solop.lessThanOrEqual=" + DEFAULT_SOLOP);

        // Get all the qldtDutoanDaotaoList where solop is less than or equal to SMALLER_SOLOP
        defaultQldtDutoanDaotaoShouldNotBeFound("solop.lessThanOrEqual=" + SMALLER_SOLOP);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySolopIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where solop is less than DEFAULT_SOLOP
        defaultQldtDutoanDaotaoShouldNotBeFound("solop.lessThan=" + DEFAULT_SOLOP);

        // Get all the qldtDutoanDaotaoList where solop is less than UPDATED_SOLOP
        defaultQldtDutoanDaotaoShouldBeFound("solop.lessThan=" + UPDATED_SOLOP);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySolopIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where solop is greater than DEFAULT_SOLOP
        defaultQldtDutoanDaotaoShouldNotBeFound("solop.greaterThan=" + DEFAULT_SOLOP);

        // Get all the qldtDutoanDaotaoList where solop is greater than SMALLER_SOLOP
        defaultQldtDutoanDaotaoShouldBeFound("solop.greaterThan=" + SMALLER_SOLOP);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySohocvienIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sohocvien equals to DEFAULT_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldBeFound("sohocvien.equals=" + DEFAULT_SOHOCVIEN);

        // Get all the qldtDutoanDaotaoList where sohocvien equals to UPDATED_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldNotBeFound("sohocvien.equals=" + UPDATED_SOHOCVIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySohocvienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sohocvien not equals to DEFAULT_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldNotBeFound("sohocvien.notEquals=" + DEFAULT_SOHOCVIEN);

        // Get all the qldtDutoanDaotaoList where sohocvien not equals to UPDATED_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldBeFound("sohocvien.notEquals=" + UPDATED_SOHOCVIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySohocvienIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sohocvien in DEFAULT_SOHOCVIEN or UPDATED_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldBeFound("sohocvien.in=" + DEFAULT_SOHOCVIEN + "," + UPDATED_SOHOCVIEN);

        // Get all the qldtDutoanDaotaoList where sohocvien equals to UPDATED_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldNotBeFound("sohocvien.in=" + UPDATED_SOHOCVIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySohocvienIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sohocvien is not null
        defaultQldtDutoanDaotaoShouldBeFound("sohocvien.specified=true");

        // Get all the qldtDutoanDaotaoList where sohocvien is null
        defaultQldtDutoanDaotaoShouldNotBeFound("sohocvien.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySohocvienIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sohocvien is greater than or equal to DEFAULT_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldBeFound("sohocvien.greaterThanOrEqual=" + DEFAULT_SOHOCVIEN);

        // Get all the qldtDutoanDaotaoList where sohocvien is greater than or equal to UPDATED_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldNotBeFound("sohocvien.greaterThanOrEqual=" + UPDATED_SOHOCVIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySohocvienIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sohocvien is less than or equal to DEFAULT_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldBeFound("sohocvien.lessThanOrEqual=" + DEFAULT_SOHOCVIEN);

        // Get all the qldtDutoanDaotaoList where sohocvien is less than or equal to SMALLER_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldNotBeFound("sohocvien.lessThanOrEqual=" + SMALLER_SOHOCVIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySohocvienIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sohocvien is less than DEFAULT_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldNotBeFound("sohocvien.lessThan=" + DEFAULT_SOHOCVIEN);

        // Get all the qldtDutoanDaotaoList where sohocvien is less than UPDATED_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldBeFound("sohocvien.lessThan=" + UPDATED_SOHOCVIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySohocvienIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sohocvien is greater than DEFAULT_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldNotBeFound("sohocvien.greaterThan=" + DEFAULT_SOHOCVIEN);

        // Get all the qldtDutoanDaotaoList where sohocvien is greater than SMALLER_SOHOCVIEN
        defaultQldtDutoanDaotaoShouldBeFound("sohocvien.greaterThan=" + SMALLER_SOHOCVIEN);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByDathanhtoanIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where dathanhtoan equals to DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldBeFound("dathanhtoan.equals=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoList where dathanhtoan equals to UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("dathanhtoan.equals=" + UPDATED_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByDathanhtoanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where dathanhtoan not equals to DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("dathanhtoan.notEquals=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoList where dathanhtoan not equals to UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldBeFound("dathanhtoan.notEquals=" + UPDATED_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByDathanhtoanIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where dathanhtoan in DEFAULT_DATHANHTOAN or UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldBeFound("dathanhtoan.in=" + DEFAULT_DATHANHTOAN + "," + UPDATED_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoList where dathanhtoan equals to UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("dathanhtoan.in=" + UPDATED_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByDathanhtoanIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where dathanhtoan is not null
        defaultQldtDutoanDaotaoShouldBeFound("dathanhtoan.specified=true");

        // Get all the qldtDutoanDaotaoList where dathanhtoan is null
        defaultQldtDutoanDaotaoShouldNotBeFound("dathanhtoan.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByDathanhtoanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where dathanhtoan is greater than or equal to DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldBeFound("dathanhtoan.greaterThanOrEqual=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoList where dathanhtoan is greater than or equal to UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("dathanhtoan.greaterThanOrEqual=" + UPDATED_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByDathanhtoanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where dathanhtoan is less than or equal to DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldBeFound("dathanhtoan.lessThanOrEqual=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoList where dathanhtoan is less than or equal to SMALLER_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("dathanhtoan.lessThanOrEqual=" + SMALLER_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByDathanhtoanIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where dathanhtoan is less than DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("dathanhtoan.lessThan=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoList where dathanhtoan is less than UPDATED_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldBeFound("dathanhtoan.lessThan=" + UPDATED_DATHANHTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByDathanhtoanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where dathanhtoan is greater than DEFAULT_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("dathanhtoan.greaterThan=" + DEFAULT_DATHANHTOAN);

        // Get all the qldtDutoanDaotaoList where dathanhtoan is greater than SMALLER_DATHANHTOAN
        defaultQldtDutoanDaotaoShouldBeFound("dathanhtoan.greaterThan=" + SMALLER_DATHANHTOAN);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByMadutoanIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where madutoan equals to DEFAULT_MADUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("madutoan.equals=" + DEFAULT_MADUTOAN);

        // Get all the qldtDutoanDaotaoList where madutoan equals to UPDATED_MADUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("madutoan.equals=" + UPDATED_MADUTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByMadutoanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where madutoan not equals to DEFAULT_MADUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("madutoan.notEquals=" + DEFAULT_MADUTOAN);

        // Get all the qldtDutoanDaotaoList where madutoan not equals to UPDATED_MADUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("madutoan.notEquals=" + UPDATED_MADUTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByMadutoanIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where madutoan in DEFAULT_MADUTOAN or UPDATED_MADUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("madutoan.in=" + DEFAULT_MADUTOAN + "," + UPDATED_MADUTOAN);

        // Get all the qldtDutoanDaotaoList where madutoan equals to UPDATED_MADUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("madutoan.in=" + UPDATED_MADUTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByMadutoanIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where madutoan is not null
        defaultQldtDutoanDaotaoShouldBeFound("madutoan.specified=true");

        // Get all the qldtDutoanDaotaoList where madutoan is null
        defaultQldtDutoanDaotaoShouldNotBeFound("madutoan.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByMadutoanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where madutoan is greater than or equal to DEFAULT_MADUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("madutoan.greaterThanOrEqual=" + DEFAULT_MADUTOAN);

        // Get all the qldtDutoanDaotaoList where madutoan is greater than or equal to UPDATED_MADUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("madutoan.greaterThanOrEqual=" + UPDATED_MADUTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByMadutoanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where madutoan is less than or equal to DEFAULT_MADUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("madutoan.lessThanOrEqual=" + DEFAULT_MADUTOAN);

        // Get all the qldtDutoanDaotaoList where madutoan is less than or equal to SMALLER_MADUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("madutoan.lessThanOrEqual=" + SMALLER_MADUTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByMadutoanIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where madutoan is less than DEFAULT_MADUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("madutoan.lessThan=" + DEFAULT_MADUTOAN);

        // Get all the qldtDutoanDaotaoList where madutoan is less than UPDATED_MADUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("madutoan.lessThan=" + UPDATED_MADUTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByMadutoanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where madutoan is greater than DEFAULT_MADUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("madutoan.greaterThan=" + DEFAULT_MADUTOAN);

        // Get all the qldtDutoanDaotaoList where madutoan is greater than SMALLER_MADUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("madutoan.greaterThan=" + SMALLER_MADUTOAN);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTendutoanIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where tendutoan equals to DEFAULT_TENDUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("tendutoan.equals=" + DEFAULT_TENDUTOAN);

        // Get all the qldtDutoanDaotaoList where tendutoan equals to UPDATED_TENDUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("tendutoan.equals=" + UPDATED_TENDUTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTendutoanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where tendutoan not equals to DEFAULT_TENDUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("tendutoan.notEquals=" + DEFAULT_TENDUTOAN);

        // Get all the qldtDutoanDaotaoList where tendutoan not equals to UPDATED_TENDUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("tendutoan.notEquals=" + UPDATED_TENDUTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTendutoanIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where tendutoan in DEFAULT_TENDUTOAN or UPDATED_TENDUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("tendutoan.in=" + DEFAULT_TENDUTOAN + "," + UPDATED_TENDUTOAN);

        // Get all the qldtDutoanDaotaoList where tendutoan equals to UPDATED_TENDUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("tendutoan.in=" + UPDATED_TENDUTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTendutoanIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where tendutoan is not null
        defaultQldtDutoanDaotaoShouldBeFound("tendutoan.specified=true");

        // Get all the qldtDutoanDaotaoList where tendutoan is null
        defaultQldtDutoanDaotaoShouldNotBeFound("tendutoan.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTendutoanContainsSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where tendutoan contains DEFAULT_TENDUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("tendutoan.contains=" + DEFAULT_TENDUTOAN);

        // Get all the qldtDutoanDaotaoList where tendutoan contains UPDATED_TENDUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("tendutoan.contains=" + UPDATED_TENDUTOAN);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTendutoanNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where tendutoan does not contain DEFAULT_TENDUTOAN
        defaultQldtDutoanDaotaoShouldNotBeFound("tendutoan.doesNotContain=" + DEFAULT_TENDUTOAN);

        // Get all the qldtDutoanDaotaoList where tendutoan does not contain UPDATED_TENDUTOAN
        defaultQldtDutoanDaotaoShouldBeFound("tendutoan.doesNotContain=" + UPDATED_TENDUTOAN);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTrangthaiIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where trangthai equals to DEFAULT_TRANGTHAI
        defaultQldtDutoanDaotaoShouldBeFound("trangthai.equals=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDutoanDaotaoList where trangthai equals to UPDATED_TRANGTHAI
        defaultQldtDutoanDaotaoShouldNotBeFound("trangthai.equals=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTrangthaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where trangthai not equals to DEFAULT_TRANGTHAI
        defaultQldtDutoanDaotaoShouldNotBeFound("trangthai.notEquals=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDutoanDaotaoList where trangthai not equals to UPDATED_TRANGTHAI
        defaultQldtDutoanDaotaoShouldBeFound("trangthai.notEquals=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTrangthaiIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where trangthai in DEFAULT_TRANGTHAI or UPDATED_TRANGTHAI
        defaultQldtDutoanDaotaoShouldBeFound("trangthai.in=" + DEFAULT_TRANGTHAI + "," + UPDATED_TRANGTHAI);

        // Get all the qldtDutoanDaotaoList where trangthai equals to UPDATED_TRANGTHAI
        defaultQldtDutoanDaotaoShouldNotBeFound("trangthai.in=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTrangthaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where trangthai is not null
        defaultQldtDutoanDaotaoShouldBeFound("trangthai.specified=true");

        // Get all the qldtDutoanDaotaoList where trangthai is null
        defaultQldtDutoanDaotaoShouldNotBeFound("trangthai.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTrangthaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where trangthai is greater than or equal to DEFAULT_TRANGTHAI
        defaultQldtDutoanDaotaoShouldBeFound("trangthai.greaterThanOrEqual=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDutoanDaotaoList where trangthai is greater than or equal to UPDATED_TRANGTHAI
        defaultQldtDutoanDaotaoShouldNotBeFound("trangthai.greaterThanOrEqual=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTrangthaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where trangthai is less than or equal to DEFAULT_TRANGTHAI
        defaultQldtDutoanDaotaoShouldBeFound("trangthai.lessThanOrEqual=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDutoanDaotaoList where trangthai is less than or equal to SMALLER_TRANGTHAI
        defaultQldtDutoanDaotaoShouldNotBeFound("trangthai.lessThanOrEqual=" + SMALLER_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTrangthaiIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where trangthai is less than DEFAULT_TRANGTHAI
        defaultQldtDutoanDaotaoShouldNotBeFound("trangthai.lessThan=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDutoanDaotaoList where trangthai is less than UPDATED_TRANGTHAI
        defaultQldtDutoanDaotaoShouldBeFound("trangthai.lessThan=" + UPDATED_TRANGTHAI);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByTrangthaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where trangthai is greater than DEFAULT_TRANGTHAI
        defaultQldtDutoanDaotaoShouldNotBeFound("trangthai.greaterThan=" + DEFAULT_TRANGTHAI);

        // Get all the qldtDutoanDaotaoList where trangthai is greater than SMALLER_TRANGTHAI
        defaultQldtDutoanDaotaoShouldBeFound("trangthai.greaterThan=" + SMALLER_TRANGTHAI);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayBdIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayBd equals to DEFAULT_NGAY_BD
        defaultQldtDutoanDaotaoShouldBeFound("ngayBd.equals=" + DEFAULT_NGAY_BD);

        // Get all the qldtDutoanDaotaoList where ngayBd equals to UPDATED_NGAY_BD
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayBd.equals=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayBdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayBd not equals to DEFAULT_NGAY_BD
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayBd.notEquals=" + DEFAULT_NGAY_BD);

        // Get all the qldtDutoanDaotaoList where ngayBd not equals to UPDATED_NGAY_BD
        defaultQldtDutoanDaotaoShouldBeFound("ngayBd.notEquals=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayBdIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayBd in DEFAULT_NGAY_BD or UPDATED_NGAY_BD
        defaultQldtDutoanDaotaoShouldBeFound("ngayBd.in=" + DEFAULT_NGAY_BD + "," + UPDATED_NGAY_BD);

        // Get all the qldtDutoanDaotaoList where ngayBd equals to UPDATED_NGAY_BD
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayBd.in=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayBdIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayBd is not null
        defaultQldtDutoanDaotaoShouldBeFound("ngayBd.specified=true");

        // Get all the qldtDutoanDaotaoList where ngayBd is null
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayBd.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayBdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayBd is greater than or equal to DEFAULT_NGAY_BD
        defaultQldtDutoanDaotaoShouldBeFound("ngayBd.greaterThanOrEqual=" + DEFAULT_NGAY_BD);

        // Get all the qldtDutoanDaotaoList where ngayBd is greater than or equal to UPDATED_NGAY_BD
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayBd.greaterThanOrEqual=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayBdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayBd is less than or equal to DEFAULT_NGAY_BD
        defaultQldtDutoanDaotaoShouldBeFound("ngayBd.lessThanOrEqual=" + DEFAULT_NGAY_BD);

        // Get all the qldtDutoanDaotaoList where ngayBd is less than or equal to SMALLER_NGAY_BD
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayBd.lessThanOrEqual=" + SMALLER_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayBdIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayBd is less than DEFAULT_NGAY_BD
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayBd.lessThan=" + DEFAULT_NGAY_BD);

        // Get all the qldtDutoanDaotaoList where ngayBd is less than UPDATED_NGAY_BD
        defaultQldtDutoanDaotaoShouldBeFound("ngayBd.lessThan=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayBdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayBd is greater than DEFAULT_NGAY_BD
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayBd.greaterThan=" + DEFAULT_NGAY_BD);

        // Get all the qldtDutoanDaotaoList where ngayBd is greater than SMALLER_NGAY_BD
        defaultQldtDutoanDaotaoShouldBeFound("ngayBd.greaterThan=" + SMALLER_NGAY_BD);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayKtIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayKt equals to DEFAULT_NGAY_KT
        defaultQldtDutoanDaotaoShouldBeFound("ngayKt.equals=" + DEFAULT_NGAY_KT);

        // Get all the qldtDutoanDaotaoList where ngayKt equals to UPDATED_NGAY_KT
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayKt.equals=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayKtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayKt not equals to DEFAULT_NGAY_KT
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayKt.notEquals=" + DEFAULT_NGAY_KT);

        // Get all the qldtDutoanDaotaoList where ngayKt not equals to UPDATED_NGAY_KT
        defaultQldtDutoanDaotaoShouldBeFound("ngayKt.notEquals=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayKtIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayKt in DEFAULT_NGAY_KT or UPDATED_NGAY_KT
        defaultQldtDutoanDaotaoShouldBeFound("ngayKt.in=" + DEFAULT_NGAY_KT + "," + UPDATED_NGAY_KT);

        // Get all the qldtDutoanDaotaoList where ngayKt equals to UPDATED_NGAY_KT
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayKt.in=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayKtIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayKt is not null
        defaultQldtDutoanDaotaoShouldBeFound("ngayKt.specified=true");

        // Get all the qldtDutoanDaotaoList where ngayKt is null
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayKt.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayKtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayKt is greater than or equal to DEFAULT_NGAY_KT
        defaultQldtDutoanDaotaoShouldBeFound("ngayKt.greaterThanOrEqual=" + DEFAULT_NGAY_KT);

        // Get all the qldtDutoanDaotaoList where ngayKt is greater than or equal to UPDATED_NGAY_KT
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayKt.greaterThanOrEqual=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayKtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayKt is less than or equal to DEFAULT_NGAY_KT
        defaultQldtDutoanDaotaoShouldBeFound("ngayKt.lessThanOrEqual=" + DEFAULT_NGAY_KT);

        // Get all the qldtDutoanDaotaoList where ngayKt is less than or equal to SMALLER_NGAY_KT
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayKt.lessThanOrEqual=" + SMALLER_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayKtIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayKt is less than DEFAULT_NGAY_KT
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayKt.lessThan=" + DEFAULT_NGAY_KT);

        // Get all the qldtDutoanDaotaoList where ngayKt is less than UPDATED_NGAY_KT
        defaultQldtDutoanDaotaoShouldBeFound("ngayKt.lessThan=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByNgayKtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where ngayKt is greater than DEFAULT_NGAY_KT
        defaultQldtDutoanDaotaoShouldNotBeFound("ngayKt.greaterThan=" + DEFAULT_NGAY_KT);

        // Get all the qldtDutoanDaotaoList where ngayKt is greater than SMALLER_NGAY_KT
        defaultQldtDutoanDaotaoShouldBeFound("ngayKt.greaterThan=" + SMALLER_NGAY_KT);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sudung equals to DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoList where sudung equals to UPDATED_SUDUNG
        defaultQldtDutoanDaotaoShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sudung not equals to DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoList where sudung not equals to UPDATED_SUDUNG
        defaultQldtDutoanDaotaoShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultQldtDutoanDaotaoShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the qldtDutoanDaotaoList where sudung equals to UPDATED_SUDUNG
        defaultQldtDutoanDaotaoShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sudung is not null
        defaultQldtDutoanDaotaoShouldBeFound("sudung.specified=true");

        // Get all the qldtDutoanDaotaoList where sudung is null
        defaultQldtDutoanDaotaoShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultQldtDutoanDaotaoShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoList where sudung is less than or equal to SMALLER_SUDUNG
        defaultQldtDutoanDaotaoShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sudung is less than DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoList where sudung is less than UPDATED_SUDUNG
        defaultQldtDutoanDaotaoShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        // Get all the qldtDutoanDaotaoList where sudung is greater than DEFAULT_SUDUNG
        defaultQldtDutoanDaotaoShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDutoanDaotaoList where sudung is greater than SMALLER_SUDUNG
        defaultQldtDutoanDaotaoShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllQldtDutoanDaotaosByDuToanDaotaoCtIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);
        QldtDutoanDaotaoct duToanDaotaoCt = QldtDutoanDaotaoctResourceIT.createEntity(em);
        em.persist(duToanDaotaoCt);
        em.flush();
        qldtDutoanDaotao.addDuToanDaotaoCt(duToanDaotaoCt);
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);
        Long duToanDaotaoCtId = duToanDaotaoCt.getId();

        // Get all the qldtDutoanDaotaoList where duToanDaotaoCt equals to duToanDaotaoCtId
        defaultQldtDutoanDaotaoShouldBeFound("duToanDaotaoCtId.equals=" + duToanDaotaoCtId);

        // Get all the qldtDutoanDaotaoList where duToanDaotaoCt equals to duToanDaotaoCtId + 1
        defaultQldtDutoanDaotaoShouldNotBeFound("duToanDaotaoCtId.equals=" + (duToanDaotaoCtId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQldtDutoanDaotaoShouldBeFound(String filter) throws Exception {
        restQldtDutoanDaotaoMockMvc.perform(get("/api/qldt-dutoan-daotaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDutoanDaotao.getId().intValue())))
            .andExpect(jsonPath("$.[*].solop").value(hasItem(DEFAULT_SOLOP)))
            .andExpect(jsonPath("$.[*].sohocvien").value(hasItem(DEFAULT_SOHOCVIEN)))
            .andExpect(jsonPath("$.[*].dathanhtoan").value(hasItem(DEFAULT_DATHANHTOAN)))
            .andExpect(jsonPath("$.[*].madutoan").value(hasItem(DEFAULT_MADUTOAN)))
            .andExpect(jsonPath("$.[*].tendutoan").value(hasItem(DEFAULT_TENDUTOAN)))
            .andExpect(jsonPath("$.[*].trangthai").value(hasItem(DEFAULT_TRANGTHAI)))
            .andExpect(jsonPath("$.[*].ngayBd").value(hasItem(DEFAULT_NGAY_BD.toString())))
            .andExpect(jsonPath("$.[*].ngayKt").value(hasItem(DEFAULT_NGAY_KT.toString())))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restQldtDutoanDaotaoMockMvc.perform(get("/api/qldt-dutoan-daotaos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQldtDutoanDaotaoShouldNotBeFound(String filter) throws Exception {
        restQldtDutoanDaotaoMockMvc.perform(get("/api/qldt-dutoan-daotaos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQldtDutoanDaotaoMockMvc.perform(get("/api/qldt-dutoan-daotaos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingQldtDutoanDaotao() throws Exception {
        // Get the qldtDutoanDaotao
        restQldtDutoanDaotaoMockMvc.perform(get("/api/qldt-dutoan-daotaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQldtDutoanDaotao() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        int databaseSizeBeforeUpdate = qldtDutoanDaotaoRepository.findAll().size();

        // Update the qldtDutoanDaotao
        QldtDutoanDaotao updatedQldtDutoanDaotao = qldtDutoanDaotaoRepository.findById(qldtDutoanDaotao.getId()).get();
        // Disconnect from session so that the updates on updatedQldtDutoanDaotao are not directly saved in db
        em.detach(updatedQldtDutoanDaotao);
        updatedQldtDutoanDaotao
            .solop(UPDATED_SOLOP)
            .sohocvien(UPDATED_SOHOCVIEN)
            .dathanhtoan(UPDATED_DATHANHTOAN)
            .madutoan(UPDATED_MADUTOAN)
            .tendutoan(UPDATED_TENDUTOAN)
            .trangthai(UPDATED_TRANGTHAI)
            .ngayBd(UPDATED_NGAY_BD)
            .ngayKt(UPDATED_NGAY_KT)
            .sudung(UPDATED_SUDUNG);
        QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO = qldtDutoanDaotaoMapper.toDto(updatedQldtDutoanDaotao);

        restQldtDutoanDaotaoMockMvc.perform(put("/api/qldt-dutoan-daotaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDutoanDaotaoDTO)))
            .andExpect(status().isOk());

        // Validate the QldtDutoanDaotao in the database
        List<QldtDutoanDaotao> qldtDutoanDaotaoList = qldtDutoanDaotaoRepository.findAll();
        assertThat(qldtDutoanDaotaoList).hasSize(databaseSizeBeforeUpdate);
        QldtDutoanDaotao testQldtDutoanDaotao = qldtDutoanDaotaoList.get(qldtDutoanDaotaoList.size() - 1);
        assertThat(testQldtDutoanDaotao.getSolop()).isEqualTo(UPDATED_SOLOP);
        assertThat(testQldtDutoanDaotao.getSohocvien()).isEqualTo(UPDATED_SOHOCVIEN);
        assertThat(testQldtDutoanDaotao.getDathanhtoan()).isEqualTo(UPDATED_DATHANHTOAN);
        assertThat(testQldtDutoanDaotao.getMadutoan()).isEqualTo(UPDATED_MADUTOAN);
        assertThat(testQldtDutoanDaotao.getTendutoan()).isEqualTo(UPDATED_TENDUTOAN);
        assertThat(testQldtDutoanDaotao.getTrangthai()).isEqualTo(UPDATED_TRANGTHAI);
        assertThat(testQldtDutoanDaotao.getNgayBd()).isEqualTo(UPDATED_NGAY_BD);
        assertThat(testQldtDutoanDaotao.getNgayKt()).isEqualTo(UPDATED_NGAY_KT);
        assertThat(testQldtDutoanDaotao.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingQldtDutoanDaotao() throws Exception {
        int databaseSizeBeforeUpdate = qldtDutoanDaotaoRepository.findAll().size();

        // Create the QldtDutoanDaotao
        QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO = qldtDutoanDaotaoMapper.toDto(qldtDutoanDaotao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQldtDutoanDaotaoMockMvc.perform(put("/api/qldt-dutoan-daotaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDutoanDaotaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDutoanDaotao in the database
        List<QldtDutoanDaotao> qldtDutoanDaotaoList = qldtDutoanDaotaoRepository.findAll();
        assertThat(qldtDutoanDaotaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQldtDutoanDaotao() throws Exception {
        // Initialize the database
        qldtDutoanDaotaoRepository.saveAndFlush(qldtDutoanDaotao);

        int databaseSizeBeforeDelete = qldtDutoanDaotaoRepository.findAll().size();

        // Delete the qldtDutoanDaotao
        restQldtDutoanDaotaoMockMvc.perform(delete("/api/qldt-dutoan-daotaos/{id}", qldtDutoanDaotao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QldtDutoanDaotao> qldtDutoanDaotaoList = qldtDutoanDaotaoRepository.findAll();
        assertThat(qldtDutoanDaotaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
