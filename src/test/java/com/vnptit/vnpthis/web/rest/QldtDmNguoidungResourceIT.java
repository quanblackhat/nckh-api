package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.qldt.QldtDmNguoidung;
import com.vnptit.vnpthis.domain.qldt.QldtDaotaoCt;
import com.vnptit.vnpthis.domain.qldt.QldtQlHocvien;
import com.vnptit.vnpthis.repository.qldt.QldtDmNguoidungRepository;
import com.vnptit.vnpthis.service.qldt.QldtDmNguoidungService;
import com.vnptit.vnpthis.service.dto.QldtDmNguoidungDTO;
import com.vnptit.vnpthis.service.mapper.QldtDmNguoidungMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.web.rest.qldt.QldtDmNguoidungResource;
import com.vnptit.vnpthis.service.dto.QldtDmNguoidungCriteria;
import com.vnptit.vnpthis.service.qldt.QldtDmNguoidungQueryService;

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
 * Integration tests for the {@link QldtDmNguoidungResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class QldtDmNguoidungResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAYSINH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYSINH = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYSINH = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_MABENHVIEN = "AAAAAAAAAA";
    private static final String UPDATED_MABENHVIEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private QldtDmNguoidungRepository qldtDmNguoidungRepository;

    @Autowired
    private QldtDmNguoidungMapper qldtDmNguoidungMapper;

    @Autowired
    private QldtDmNguoidungService qldtDmNguoidungService;

    @Autowired
    private QldtDmNguoidungQueryService qldtDmNguoidungQueryService;

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

    private MockMvc restQldtDmNguoidungMockMvc;

    private QldtDmNguoidung qldtDmNguoidung;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QldtDmNguoidungResource qldtDmNguoidungResource = new QldtDmNguoidungResource(qldtDmNguoidungService, qldtDmNguoidungQueryService);
        this.restQldtDmNguoidungMockMvc = MockMvcBuilders.standaloneSetup(qldtDmNguoidungResource)
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
    public static QldtDmNguoidung createEntity(EntityManager em) {
        QldtDmNguoidung qldtDmNguoidung = new QldtDmNguoidung()
            .ten(DEFAULT_TEN)
            .ngaysinh(DEFAULT_NGAYSINH)
            .mabenhvien(DEFAULT_MABENHVIEN)
            .sudung(DEFAULT_SUDUNG);
        return qldtDmNguoidung;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QldtDmNguoidung createUpdatedEntity(EntityManager em) {
        QldtDmNguoidung qldtDmNguoidung = new QldtDmNguoidung()
            .ten(UPDATED_TEN)
            .ngaysinh(UPDATED_NGAYSINH)
            .mabenhvien(UPDATED_MABENHVIEN)
            .sudung(UPDATED_SUDUNG);
        return qldtDmNguoidung;
    }

    @BeforeEach
    public void initTest() {
        qldtDmNguoidung = createEntity(em);
    }

    @Test
    @Transactional
    public void createQldtDmNguoidung() throws Exception {
        int databaseSizeBeforeCreate = qldtDmNguoidungRepository.findAll().size();

        // Create the QldtDmNguoidung
        QldtDmNguoidungDTO qldtDmNguoidungDTO = qldtDmNguoidungMapper.toDto(qldtDmNguoidung);
        restQldtDmNguoidungMockMvc.perform(post("/api/qldt-dm-nguoidungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmNguoidungDTO)))
            .andExpect(status().isCreated());

        // Validate the QldtDmNguoidung in the database
        List<QldtDmNguoidung> qldtDmNguoidungList = qldtDmNguoidungRepository.findAll();
        assertThat(qldtDmNguoidungList).hasSize(databaseSizeBeforeCreate + 1);
        QldtDmNguoidung testQldtDmNguoidung = qldtDmNguoidungList.get(qldtDmNguoidungList.size() - 1);
        assertThat(testQldtDmNguoidung.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testQldtDmNguoidung.getNgaysinh()).isEqualTo(DEFAULT_NGAYSINH);
        assertThat(testQldtDmNguoidung.getMabenhvien()).isEqualTo(DEFAULT_MABENHVIEN);
        assertThat(testQldtDmNguoidung.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createQldtDmNguoidungWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qldtDmNguoidungRepository.findAll().size();

        // Create the QldtDmNguoidung with an existing ID
        qldtDmNguoidung.setId(1L);
        QldtDmNguoidungDTO qldtDmNguoidungDTO = qldtDmNguoidungMapper.toDto(qldtDmNguoidung);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQldtDmNguoidungMockMvc.perform(post("/api/qldt-dm-nguoidungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmNguoidungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDmNguoidung in the database
        List<QldtDmNguoidung> qldtDmNguoidungList = qldtDmNguoidungRepository.findAll();
        assertThat(qldtDmNguoidungList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQldtDmNguoidungs() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList
        restQldtDmNguoidungMockMvc.perform(get("/api/qldt-dm-nguoidungs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDmNguoidung.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].ngaysinh").value(hasItem(DEFAULT_NGAYSINH.toString())))
            .andExpect(jsonPath("$.[*].mabenhvien").value(hasItem(DEFAULT_MABENHVIEN)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getQldtDmNguoidung() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get the qldtDmNguoidung
        restQldtDmNguoidungMockMvc.perform(get("/api/qldt-dm-nguoidungs/{id}", qldtDmNguoidung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qldtDmNguoidung.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.ngaysinh").value(DEFAULT_NGAYSINH.toString()))
            .andExpect(jsonPath("$.mabenhvien").value(DEFAULT_MABENHVIEN))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getQldtDmNguoidungsByIdFiltering() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        Long id = qldtDmNguoidung.getId();

        defaultQldtDmNguoidungShouldBeFound("id.equals=" + id);
        defaultQldtDmNguoidungShouldNotBeFound("id.notEquals=" + id);

        defaultQldtDmNguoidungShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultQldtDmNguoidungShouldNotBeFound("id.greaterThan=" + id);

        defaultQldtDmNguoidungShouldBeFound("id.lessThanOrEqual=" + id);
        defaultQldtDmNguoidungShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ten equals to DEFAULT_TEN
        defaultQldtDmNguoidungShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the qldtDmNguoidungList where ten equals to UPDATED_TEN
        defaultQldtDmNguoidungShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ten not equals to DEFAULT_TEN
        defaultQldtDmNguoidungShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the qldtDmNguoidungList where ten not equals to UPDATED_TEN
        defaultQldtDmNguoidungShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultQldtDmNguoidungShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the qldtDmNguoidungList where ten equals to UPDATED_TEN
        defaultQldtDmNguoidungShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ten is not null
        defaultQldtDmNguoidungShouldBeFound("ten.specified=true");

        // Get all the qldtDmNguoidungList where ten is null
        defaultQldtDmNguoidungShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDmNguoidungsByTenContainsSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ten contains DEFAULT_TEN
        defaultQldtDmNguoidungShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the qldtDmNguoidungList where ten contains UPDATED_TEN
        defaultQldtDmNguoidungShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ten does not contain DEFAULT_TEN
        defaultQldtDmNguoidungShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the qldtDmNguoidungList where ten does not contain UPDATED_TEN
        defaultQldtDmNguoidungShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByNgaysinhIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ngaysinh equals to DEFAULT_NGAYSINH
        defaultQldtDmNguoidungShouldBeFound("ngaysinh.equals=" + DEFAULT_NGAYSINH);

        // Get all the qldtDmNguoidungList where ngaysinh equals to UPDATED_NGAYSINH
        defaultQldtDmNguoidungShouldNotBeFound("ngaysinh.equals=" + UPDATED_NGAYSINH);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByNgaysinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ngaysinh not equals to DEFAULT_NGAYSINH
        defaultQldtDmNguoidungShouldNotBeFound("ngaysinh.notEquals=" + DEFAULT_NGAYSINH);

        // Get all the qldtDmNguoidungList where ngaysinh not equals to UPDATED_NGAYSINH
        defaultQldtDmNguoidungShouldBeFound("ngaysinh.notEquals=" + UPDATED_NGAYSINH);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByNgaysinhIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ngaysinh in DEFAULT_NGAYSINH or UPDATED_NGAYSINH
        defaultQldtDmNguoidungShouldBeFound("ngaysinh.in=" + DEFAULT_NGAYSINH + "," + UPDATED_NGAYSINH);

        // Get all the qldtDmNguoidungList where ngaysinh equals to UPDATED_NGAYSINH
        defaultQldtDmNguoidungShouldNotBeFound("ngaysinh.in=" + UPDATED_NGAYSINH);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByNgaysinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ngaysinh is not null
        defaultQldtDmNguoidungShouldBeFound("ngaysinh.specified=true");

        // Get all the qldtDmNguoidungList where ngaysinh is null
        defaultQldtDmNguoidungShouldNotBeFound("ngaysinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByNgaysinhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ngaysinh is greater than or equal to DEFAULT_NGAYSINH
        defaultQldtDmNguoidungShouldBeFound("ngaysinh.greaterThanOrEqual=" + DEFAULT_NGAYSINH);

        // Get all the qldtDmNguoidungList where ngaysinh is greater than or equal to UPDATED_NGAYSINH
        defaultQldtDmNguoidungShouldNotBeFound("ngaysinh.greaterThanOrEqual=" + UPDATED_NGAYSINH);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByNgaysinhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ngaysinh is less than or equal to DEFAULT_NGAYSINH
        defaultQldtDmNguoidungShouldBeFound("ngaysinh.lessThanOrEqual=" + DEFAULT_NGAYSINH);

        // Get all the qldtDmNguoidungList where ngaysinh is less than or equal to SMALLER_NGAYSINH
        defaultQldtDmNguoidungShouldNotBeFound("ngaysinh.lessThanOrEqual=" + SMALLER_NGAYSINH);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByNgaysinhIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ngaysinh is less than DEFAULT_NGAYSINH
        defaultQldtDmNguoidungShouldNotBeFound("ngaysinh.lessThan=" + DEFAULT_NGAYSINH);

        // Get all the qldtDmNguoidungList where ngaysinh is less than UPDATED_NGAYSINH
        defaultQldtDmNguoidungShouldBeFound("ngaysinh.lessThan=" + UPDATED_NGAYSINH);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByNgaysinhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where ngaysinh is greater than DEFAULT_NGAYSINH
        defaultQldtDmNguoidungShouldNotBeFound("ngaysinh.greaterThan=" + DEFAULT_NGAYSINH);

        // Get all the qldtDmNguoidungList where ngaysinh is greater than SMALLER_NGAYSINH
        defaultQldtDmNguoidungShouldBeFound("ngaysinh.greaterThan=" + SMALLER_NGAYSINH);
    }


    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByMabenhvienIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where mabenhvien equals to DEFAULT_MABENHVIEN
        defaultQldtDmNguoidungShouldBeFound("mabenhvien.equals=" + DEFAULT_MABENHVIEN);

        // Get all the qldtDmNguoidungList where mabenhvien equals to UPDATED_MABENHVIEN
        defaultQldtDmNguoidungShouldNotBeFound("mabenhvien.equals=" + UPDATED_MABENHVIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByMabenhvienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where mabenhvien not equals to DEFAULT_MABENHVIEN
        defaultQldtDmNguoidungShouldNotBeFound("mabenhvien.notEquals=" + DEFAULT_MABENHVIEN);

        // Get all the qldtDmNguoidungList where mabenhvien not equals to UPDATED_MABENHVIEN
        defaultQldtDmNguoidungShouldBeFound("mabenhvien.notEquals=" + UPDATED_MABENHVIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByMabenhvienIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where mabenhvien in DEFAULT_MABENHVIEN or UPDATED_MABENHVIEN
        defaultQldtDmNguoidungShouldBeFound("mabenhvien.in=" + DEFAULT_MABENHVIEN + "," + UPDATED_MABENHVIEN);

        // Get all the qldtDmNguoidungList where mabenhvien equals to UPDATED_MABENHVIEN
        defaultQldtDmNguoidungShouldNotBeFound("mabenhvien.in=" + UPDATED_MABENHVIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByMabenhvienIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where mabenhvien is not null
        defaultQldtDmNguoidungShouldBeFound("mabenhvien.specified=true");

        // Get all the qldtDmNguoidungList where mabenhvien is null
        defaultQldtDmNguoidungShouldNotBeFound("mabenhvien.specified=false");
    }
                @Test
    @Transactional
    public void getAllQldtDmNguoidungsByMabenhvienContainsSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where mabenhvien contains DEFAULT_MABENHVIEN
        defaultQldtDmNguoidungShouldBeFound("mabenhvien.contains=" + DEFAULT_MABENHVIEN);

        // Get all the qldtDmNguoidungList where mabenhvien contains UPDATED_MABENHVIEN
        defaultQldtDmNguoidungShouldNotBeFound("mabenhvien.contains=" + UPDATED_MABENHVIEN);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsByMabenhvienNotContainsSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where mabenhvien does not contain DEFAULT_MABENHVIEN
        defaultQldtDmNguoidungShouldNotBeFound("mabenhvien.doesNotContain=" + DEFAULT_MABENHVIEN);

        // Get all the qldtDmNguoidungList where mabenhvien does not contain UPDATED_MABENHVIEN
        defaultQldtDmNguoidungShouldBeFound("mabenhvien.doesNotContain=" + UPDATED_MABENHVIEN);
    }


    @Test
    @Transactional
    public void getAllQldtDmNguoidungsBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where sudung equals to DEFAULT_SUDUNG
        defaultQldtDmNguoidungShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNguoidungList where sudung equals to UPDATED_SUDUNG
        defaultQldtDmNguoidungShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where sudung not equals to DEFAULT_SUDUNG
        defaultQldtDmNguoidungShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNguoidungList where sudung not equals to UPDATED_SUDUNG
        defaultQldtDmNguoidungShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultQldtDmNguoidungShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the qldtDmNguoidungList where sudung equals to UPDATED_SUDUNG
        defaultQldtDmNguoidungShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where sudung is not null
        defaultQldtDmNguoidungShouldBeFound("sudung.specified=true");

        // Get all the qldtDmNguoidungList where sudung is null
        defaultQldtDmNguoidungShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultQldtDmNguoidungShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNguoidungList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultQldtDmNguoidungShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultQldtDmNguoidungShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNguoidungList where sudung is less than or equal to SMALLER_SUDUNG
        defaultQldtDmNguoidungShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where sudung is less than DEFAULT_SUDUNG
        defaultQldtDmNguoidungShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNguoidungList where sudung is less than UPDATED_SUDUNG
        defaultQldtDmNguoidungShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllQldtDmNguoidungsBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        // Get all the qldtDmNguoidungList where sudung is greater than DEFAULT_SUDUNG
        defaultQldtDmNguoidungShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the qldtDmNguoidungList where sudung is greater than SMALLER_SUDUNG
        defaultQldtDmNguoidungShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


  

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQldtDmNguoidungShouldBeFound(String filter) throws Exception {
        restQldtDmNguoidungMockMvc.perform(get("/api/qldt-dm-nguoidungs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qldtDmNguoidung.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].ngaysinh").value(hasItem(DEFAULT_NGAYSINH.toString())))
            .andExpect(jsonPath("$.[*].mabenhvien").value(hasItem(DEFAULT_MABENHVIEN)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restQldtDmNguoidungMockMvc.perform(get("/api/qldt-dm-nguoidungs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQldtDmNguoidungShouldNotBeFound(String filter) throws Exception {
        restQldtDmNguoidungMockMvc.perform(get("/api/qldt-dm-nguoidungs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQldtDmNguoidungMockMvc.perform(get("/api/qldt-dm-nguoidungs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingQldtDmNguoidung() throws Exception {
        // Get the qldtDmNguoidung
        restQldtDmNguoidungMockMvc.perform(get("/api/qldt-dm-nguoidungs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQldtDmNguoidung() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        int databaseSizeBeforeUpdate = qldtDmNguoidungRepository.findAll().size();

        // Update the qldtDmNguoidung
        QldtDmNguoidung updatedQldtDmNguoidung = qldtDmNguoidungRepository.findById(qldtDmNguoidung.getId()).get();
        // Disconnect from session so that the updates on updatedQldtDmNguoidung are not directly saved in db
        em.detach(updatedQldtDmNguoidung);
        updatedQldtDmNguoidung
            .ten(UPDATED_TEN)
            .ngaysinh(UPDATED_NGAYSINH)
            .mabenhvien(UPDATED_MABENHVIEN)
            .sudung(UPDATED_SUDUNG);
        QldtDmNguoidungDTO qldtDmNguoidungDTO = qldtDmNguoidungMapper.toDto(updatedQldtDmNguoidung);

        restQldtDmNguoidungMockMvc.perform(put("/api/qldt-dm-nguoidungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmNguoidungDTO)))
            .andExpect(status().isOk());

        // Validate the QldtDmNguoidung in the database
        List<QldtDmNguoidung> qldtDmNguoidungList = qldtDmNguoidungRepository.findAll();
        assertThat(qldtDmNguoidungList).hasSize(databaseSizeBeforeUpdate);
        QldtDmNguoidung testQldtDmNguoidung = qldtDmNguoidungList.get(qldtDmNguoidungList.size() - 1);
        assertThat(testQldtDmNguoidung.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testQldtDmNguoidung.getNgaysinh()).isEqualTo(UPDATED_NGAYSINH);
        assertThat(testQldtDmNguoidung.getMabenhvien()).isEqualTo(UPDATED_MABENHVIEN);
        assertThat(testQldtDmNguoidung.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingQldtDmNguoidung() throws Exception {
        int databaseSizeBeforeUpdate = qldtDmNguoidungRepository.findAll().size();

        // Create the QldtDmNguoidung
        QldtDmNguoidungDTO qldtDmNguoidungDTO = qldtDmNguoidungMapper.toDto(qldtDmNguoidung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQldtDmNguoidungMockMvc.perform(put("/api/qldt-dm-nguoidungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qldtDmNguoidungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QldtDmNguoidung in the database
        List<QldtDmNguoidung> qldtDmNguoidungList = qldtDmNguoidungRepository.findAll();
        assertThat(qldtDmNguoidungList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQldtDmNguoidung() throws Exception {
        // Initialize the database
        qldtDmNguoidungRepository.saveAndFlush(qldtDmNguoidung);

        int databaseSizeBeforeDelete = qldtDmNguoidungRepository.findAll().size();

        // Delete the qldtDmNguoidung
        restQldtDmNguoidungMockMvc.perform(delete("/api/qldt-dm-nguoidungs/{id}", qldtDmNguoidung.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QldtDmNguoidung> qldtDmNguoidungList = qldtDmNguoidungRepository.findAll();
        assertThat(qldtDmNguoidungList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
