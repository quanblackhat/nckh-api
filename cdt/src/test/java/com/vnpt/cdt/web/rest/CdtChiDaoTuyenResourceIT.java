package com.vnpt.cdt.web.rest;

import com.vnpt.cdt.CdtApp;
import com.vnpt.cdt.domain.CdtChiDaoTuyen;
import com.vnpt.cdt.domain.CdtNhanVien;
import com.vnpt.cdt.domain.CdtNoiDen;
import com.vnpt.cdt.domain.CdtKyThuat;
import com.vnpt.cdt.domain.CdtVatTu;
import com.vnpt.cdt.domain.CdtLyDoCongTac;
import com.vnpt.cdt.domain.CdtKetQuaCongTac;
import com.vnpt.cdt.domain.CdtTaiChinh;
import com.vnpt.cdt.repository.CdtChiDaoTuyenRepository;
import com.vnpt.cdt.service.CdtChiDaoTuyenService;
import com.vnpt.cdt.web.rest.errors.ExceptionTranslator;
import com.vnpt.cdt.service.dto.CdtChiDaoTuyenCriteria;
import com.vnpt.cdt.service.CdtChiDaoTuyenQueryService;

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

import static com.vnpt.cdt.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CdtChiDaoTuyenResource} REST controller.
 */
@SpringBootTest(classes = CdtApp.class)
public class CdtChiDaoTuyenResourceIT {

    private static final String DEFAULT_SO_QUYET_DINH = "AAAAAAAAAA";
    private static final String UPDATED_SO_QUYET_DINH = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_QUYET_DINH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_QUYET_DINH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SO_HOP_DONG = "AAAAAAAAAA";
    private static final String UPDATED_SO_HOP_DONG = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_HOP_DONG = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_HOP_DONG = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_BD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_BD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_NGAY_KT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_KT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_NGAY_TAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_TAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_SO_BENH_NHAN_KHAM = 1L;
    private static final Long UPDATED_SO_BENH_NHAN_KHAM = 2L;
    private static final Long SMALLER_SO_BENH_NHAN_KHAM = 1L - 1L;

    private static final Long DEFAULT_SO_BENH_NHAN_KY_THUAT = 1L;
    private static final Long UPDATED_SO_BENH_NHAN_KY_THUAT = 2L;
    private static final Long SMALLER_SO_BENH_NHAN_KY_THUAT = 1L - 1L;

    private static final Long DEFAULT_SO_CAN_BO_CHUYEN_GIAO = 1L;
    private static final Long UPDATED_SO_CAN_BO_CHUYEN_GIAO = 2L;
    private static final Long SMALLER_SO_CAN_BO_CHUYEN_GIAO = 1L - 1L;

    @Autowired
    private CdtChiDaoTuyenRepository cdtChiDaoTuyenRepository;

    @Autowired
    private CdtChiDaoTuyenService cdtChiDaoTuyenService;

    @Autowired
    private CdtChiDaoTuyenQueryService cdtChiDaoTuyenQueryService;

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

    private MockMvc restCdtChiDaoTuyenMockMvc;

    private CdtChiDaoTuyen cdtChiDaoTuyen;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CdtChiDaoTuyenResource cdtChiDaoTuyenResource = new CdtChiDaoTuyenResource(cdtChiDaoTuyenService, cdtChiDaoTuyenQueryService);
        this.restCdtChiDaoTuyenMockMvc = MockMvcBuilders.standaloneSetup(cdtChiDaoTuyenResource)
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
    public static CdtChiDaoTuyen createEntity(EntityManager em) {
        CdtChiDaoTuyen cdtChiDaoTuyen = new CdtChiDaoTuyen()
            .soQuyetDinh(DEFAULT_SO_QUYET_DINH)
            .ngayQuyetDinh(DEFAULT_NGAY_QUYET_DINH)
            .soHopDong(DEFAULT_SO_HOP_DONG)
            .ngayHopDong(DEFAULT_NGAY_HOP_DONG)
            .ghiChu(DEFAULT_GHI_CHU)
            .noiDung(DEFAULT_NOI_DUNG)
            .ngayBD(DEFAULT_NGAY_BD)
            .ngayKT(DEFAULT_NGAY_KT)
            .ngayTao(DEFAULT_NGAY_TAO)
            .soBenhNhanKham(DEFAULT_SO_BENH_NHAN_KHAM)
            .soBenhNhanKyThuat(DEFAULT_SO_BENH_NHAN_KY_THUAT)
            .soCanBoChuyenGiao(DEFAULT_SO_CAN_BO_CHUYEN_GIAO);
        return cdtChiDaoTuyen;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CdtChiDaoTuyen createUpdatedEntity(EntityManager em) {
        CdtChiDaoTuyen cdtChiDaoTuyen = new CdtChiDaoTuyen()
            .soQuyetDinh(UPDATED_SO_QUYET_DINH)
            .ngayQuyetDinh(UPDATED_NGAY_QUYET_DINH)
            .soHopDong(UPDATED_SO_HOP_DONG)
            .ngayHopDong(UPDATED_NGAY_HOP_DONG)
            .ghiChu(UPDATED_GHI_CHU)
            .noiDung(UPDATED_NOI_DUNG)
            .ngayBD(UPDATED_NGAY_BD)
            .ngayKT(UPDATED_NGAY_KT)
            .ngayTao(UPDATED_NGAY_TAO)
            .soBenhNhanKham(UPDATED_SO_BENH_NHAN_KHAM)
            .soBenhNhanKyThuat(UPDATED_SO_BENH_NHAN_KY_THUAT)
            .soCanBoChuyenGiao(UPDATED_SO_CAN_BO_CHUYEN_GIAO);
        return cdtChiDaoTuyen;
    }

    @BeforeEach
    public void initTest() {
        cdtChiDaoTuyen = createEntity(em);
    }

    @Test
    @Transactional
    public void createCdtChiDaoTuyen() throws Exception {
        int databaseSizeBeforeCreate = cdtChiDaoTuyenRepository.findAll().size();

        // Create the CdtChiDaoTuyen
        restCdtChiDaoTuyenMockMvc.perform(post("/api/cdt-chi-dao-tuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtChiDaoTuyen)))
            .andExpect(status().isCreated());

        // Validate the CdtChiDaoTuyen in the database
        List<CdtChiDaoTuyen> cdtChiDaoTuyenList = cdtChiDaoTuyenRepository.findAll();
        assertThat(cdtChiDaoTuyenList).hasSize(databaseSizeBeforeCreate + 1);
        CdtChiDaoTuyen testCdtChiDaoTuyen = cdtChiDaoTuyenList.get(cdtChiDaoTuyenList.size() - 1);
        assertThat(testCdtChiDaoTuyen.getSoQuyetDinh()).isEqualTo(DEFAULT_SO_QUYET_DINH);
        assertThat(testCdtChiDaoTuyen.getNgayQuyetDinh()).isEqualTo(DEFAULT_NGAY_QUYET_DINH);
        assertThat(testCdtChiDaoTuyen.getSoHopDong()).isEqualTo(DEFAULT_SO_HOP_DONG);
        assertThat(testCdtChiDaoTuyen.getNgayHopDong()).isEqualTo(DEFAULT_NGAY_HOP_DONG);
        assertThat(testCdtChiDaoTuyen.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testCdtChiDaoTuyen.getNoiDung()).isEqualTo(DEFAULT_NOI_DUNG);
        assertThat(testCdtChiDaoTuyen.getNgayBD()).isEqualTo(DEFAULT_NGAY_BD);
        assertThat(testCdtChiDaoTuyen.getNgayKT()).isEqualTo(DEFAULT_NGAY_KT);
        assertThat(testCdtChiDaoTuyen.getNgayTao()).isEqualTo(DEFAULT_NGAY_TAO);
        assertThat(testCdtChiDaoTuyen.getSoBenhNhanKham()).isEqualTo(DEFAULT_SO_BENH_NHAN_KHAM);
        assertThat(testCdtChiDaoTuyen.getSoBenhNhanKyThuat()).isEqualTo(DEFAULT_SO_BENH_NHAN_KY_THUAT);
        assertThat(testCdtChiDaoTuyen.getSoCanBoChuyenGiao()).isEqualTo(DEFAULT_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    public void createCdtChiDaoTuyenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cdtChiDaoTuyenRepository.findAll().size();

        // Create the CdtChiDaoTuyen with an existing ID
        cdtChiDaoTuyen.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCdtChiDaoTuyenMockMvc.perform(post("/api/cdt-chi-dao-tuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtChiDaoTuyen)))
            .andExpect(status().isBadRequest());

        // Validate the CdtChiDaoTuyen in the database
        List<CdtChiDaoTuyen> cdtChiDaoTuyenList = cdtChiDaoTuyenRepository.findAll();
        assertThat(cdtChiDaoTuyenList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyens() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList
        restCdtChiDaoTuyenMockMvc.perform(get("/api/cdt-chi-dao-tuyens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtChiDaoTuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].soQuyetDinh").value(hasItem(DEFAULT_SO_QUYET_DINH)))
            .andExpect(jsonPath("$.[*].ngayQuyetDinh").value(hasItem(DEFAULT_NGAY_QUYET_DINH.toString())))
            .andExpect(jsonPath("$.[*].soHopDong").value(hasItem(DEFAULT_SO_HOP_DONG)))
            .andExpect(jsonPath("$.[*].ngayHopDong").value(hasItem(DEFAULT_NGAY_HOP_DONG.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(DEFAULT_NOI_DUNG)))
            .andExpect(jsonPath("$.[*].ngayBD").value(hasItem(DEFAULT_NGAY_BD.toString())))
            .andExpect(jsonPath("$.[*].ngayKT").value(hasItem(DEFAULT_NGAY_KT.toString())))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(DEFAULT_NGAY_TAO.toString())))
            .andExpect(jsonPath("$.[*].soBenhNhanKham").value(hasItem(DEFAULT_SO_BENH_NHAN_KHAM.intValue())))
            .andExpect(jsonPath("$.[*].soBenhNhanKyThuat").value(hasItem(DEFAULT_SO_BENH_NHAN_KY_THUAT.intValue())))
            .andExpect(jsonPath("$.[*].soCanBoChuyenGiao").value(hasItem(DEFAULT_SO_CAN_BO_CHUYEN_GIAO.intValue())));
    }
    
    @Test
    @Transactional
    public void getCdtChiDaoTuyen() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get the cdtChiDaoTuyen
        restCdtChiDaoTuyenMockMvc.perform(get("/api/cdt-chi-dao-tuyens/{id}", cdtChiDaoTuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cdtChiDaoTuyen.getId().intValue()))
            .andExpect(jsonPath("$.soQuyetDinh").value(DEFAULT_SO_QUYET_DINH))
            .andExpect(jsonPath("$.ngayQuyetDinh").value(DEFAULT_NGAY_QUYET_DINH.toString()))
            .andExpect(jsonPath("$.soHopDong").value(DEFAULT_SO_HOP_DONG))
            .andExpect(jsonPath("$.ngayHopDong").value(DEFAULT_NGAY_HOP_DONG.toString()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU))
            .andExpect(jsonPath("$.noiDung").value(DEFAULT_NOI_DUNG))
            .andExpect(jsonPath("$.ngayBD").value(DEFAULT_NGAY_BD.toString()))
            .andExpect(jsonPath("$.ngayKT").value(DEFAULT_NGAY_KT.toString()))
            .andExpect(jsonPath("$.ngayTao").value(DEFAULT_NGAY_TAO.toString()))
            .andExpect(jsonPath("$.soBenhNhanKham").value(DEFAULT_SO_BENH_NHAN_KHAM.intValue()))
            .andExpect(jsonPath("$.soBenhNhanKyThuat").value(DEFAULT_SO_BENH_NHAN_KY_THUAT.intValue()))
            .andExpect(jsonPath("$.soCanBoChuyenGiao").value(DEFAULT_SO_CAN_BO_CHUYEN_GIAO.intValue()));
    }


    @Test
    @Transactional
    public void getCdtChiDaoTuyensByIdFiltering() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        Long id = cdtChiDaoTuyen.getId();

        defaultCdtChiDaoTuyenShouldBeFound("id.equals=" + id);
        defaultCdtChiDaoTuyenShouldNotBeFound("id.notEquals=" + id);

        defaultCdtChiDaoTuyenShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCdtChiDaoTuyenShouldNotBeFound("id.greaterThan=" + id);

        defaultCdtChiDaoTuyenShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCdtChiDaoTuyenShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoQuyetDinhIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soQuyetDinh equals to DEFAULT_SO_QUYET_DINH
        defaultCdtChiDaoTuyenShouldBeFound("soQuyetDinh.equals=" + DEFAULT_SO_QUYET_DINH);

        // Get all the cdtChiDaoTuyenList where soQuyetDinh equals to UPDATED_SO_QUYET_DINH
        defaultCdtChiDaoTuyenShouldNotBeFound("soQuyetDinh.equals=" + UPDATED_SO_QUYET_DINH);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoQuyetDinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soQuyetDinh not equals to DEFAULT_SO_QUYET_DINH
        defaultCdtChiDaoTuyenShouldNotBeFound("soQuyetDinh.notEquals=" + DEFAULT_SO_QUYET_DINH);

        // Get all the cdtChiDaoTuyenList where soQuyetDinh not equals to UPDATED_SO_QUYET_DINH
        defaultCdtChiDaoTuyenShouldBeFound("soQuyetDinh.notEquals=" + UPDATED_SO_QUYET_DINH);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoQuyetDinhIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soQuyetDinh in DEFAULT_SO_QUYET_DINH or UPDATED_SO_QUYET_DINH
        defaultCdtChiDaoTuyenShouldBeFound("soQuyetDinh.in=" + DEFAULT_SO_QUYET_DINH + "," + UPDATED_SO_QUYET_DINH);

        // Get all the cdtChiDaoTuyenList where soQuyetDinh equals to UPDATED_SO_QUYET_DINH
        defaultCdtChiDaoTuyenShouldNotBeFound("soQuyetDinh.in=" + UPDATED_SO_QUYET_DINH);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoQuyetDinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soQuyetDinh is not null
        defaultCdtChiDaoTuyenShouldBeFound("soQuyetDinh.specified=true");

        // Get all the cdtChiDaoTuyenList where soQuyetDinh is null
        defaultCdtChiDaoTuyenShouldNotBeFound("soQuyetDinh.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoQuyetDinhContainsSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soQuyetDinh contains DEFAULT_SO_QUYET_DINH
        defaultCdtChiDaoTuyenShouldBeFound("soQuyetDinh.contains=" + DEFAULT_SO_QUYET_DINH);

        // Get all the cdtChiDaoTuyenList where soQuyetDinh contains UPDATED_SO_QUYET_DINH
        defaultCdtChiDaoTuyenShouldNotBeFound("soQuyetDinh.contains=" + UPDATED_SO_QUYET_DINH);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoQuyetDinhNotContainsSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soQuyetDinh does not contain DEFAULT_SO_QUYET_DINH
        defaultCdtChiDaoTuyenShouldNotBeFound("soQuyetDinh.doesNotContain=" + DEFAULT_SO_QUYET_DINH);

        // Get all the cdtChiDaoTuyenList where soQuyetDinh does not contain UPDATED_SO_QUYET_DINH
        defaultCdtChiDaoTuyenShouldBeFound("soQuyetDinh.doesNotContain=" + UPDATED_SO_QUYET_DINH);
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayQuyetDinhIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayQuyetDinh equals to DEFAULT_NGAY_QUYET_DINH
        defaultCdtChiDaoTuyenShouldBeFound("ngayQuyetDinh.equals=" + DEFAULT_NGAY_QUYET_DINH);

        // Get all the cdtChiDaoTuyenList where ngayQuyetDinh equals to UPDATED_NGAY_QUYET_DINH
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.equals=" + UPDATED_NGAY_QUYET_DINH);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayQuyetDinhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayQuyetDinh not equals to DEFAULT_NGAY_QUYET_DINH
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.notEquals=" + DEFAULT_NGAY_QUYET_DINH);

        // Get all the cdtChiDaoTuyenList where ngayQuyetDinh not equals to UPDATED_NGAY_QUYET_DINH
        defaultCdtChiDaoTuyenShouldBeFound("ngayQuyetDinh.notEquals=" + UPDATED_NGAY_QUYET_DINH);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayQuyetDinhIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayQuyetDinh in DEFAULT_NGAY_QUYET_DINH or UPDATED_NGAY_QUYET_DINH
        defaultCdtChiDaoTuyenShouldBeFound("ngayQuyetDinh.in=" + DEFAULT_NGAY_QUYET_DINH + "," + UPDATED_NGAY_QUYET_DINH);

        // Get all the cdtChiDaoTuyenList where ngayQuyetDinh equals to UPDATED_NGAY_QUYET_DINH
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.in=" + UPDATED_NGAY_QUYET_DINH);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayQuyetDinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayQuyetDinh is not null
        defaultCdtChiDaoTuyenShouldBeFound("ngayQuyetDinh.specified=true");

        // Get all the cdtChiDaoTuyenList where ngayQuyetDinh is null
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayQuyetDinh.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoHopDongIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soHopDong equals to DEFAULT_SO_HOP_DONG
        defaultCdtChiDaoTuyenShouldBeFound("soHopDong.equals=" + DEFAULT_SO_HOP_DONG);

        // Get all the cdtChiDaoTuyenList where soHopDong equals to UPDATED_SO_HOP_DONG
        defaultCdtChiDaoTuyenShouldNotBeFound("soHopDong.equals=" + UPDATED_SO_HOP_DONG);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoHopDongIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soHopDong not equals to DEFAULT_SO_HOP_DONG
        defaultCdtChiDaoTuyenShouldNotBeFound("soHopDong.notEquals=" + DEFAULT_SO_HOP_DONG);

        // Get all the cdtChiDaoTuyenList where soHopDong not equals to UPDATED_SO_HOP_DONG
        defaultCdtChiDaoTuyenShouldBeFound("soHopDong.notEquals=" + UPDATED_SO_HOP_DONG);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoHopDongIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soHopDong in DEFAULT_SO_HOP_DONG or UPDATED_SO_HOP_DONG
        defaultCdtChiDaoTuyenShouldBeFound("soHopDong.in=" + DEFAULT_SO_HOP_DONG + "," + UPDATED_SO_HOP_DONG);

        // Get all the cdtChiDaoTuyenList where soHopDong equals to UPDATED_SO_HOP_DONG
        defaultCdtChiDaoTuyenShouldNotBeFound("soHopDong.in=" + UPDATED_SO_HOP_DONG);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoHopDongIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soHopDong is not null
        defaultCdtChiDaoTuyenShouldBeFound("soHopDong.specified=true");

        // Get all the cdtChiDaoTuyenList where soHopDong is null
        defaultCdtChiDaoTuyenShouldNotBeFound("soHopDong.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoHopDongContainsSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soHopDong contains DEFAULT_SO_HOP_DONG
        defaultCdtChiDaoTuyenShouldBeFound("soHopDong.contains=" + DEFAULT_SO_HOP_DONG);

        // Get all the cdtChiDaoTuyenList where soHopDong contains UPDATED_SO_HOP_DONG
        defaultCdtChiDaoTuyenShouldNotBeFound("soHopDong.contains=" + UPDATED_SO_HOP_DONG);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoHopDongNotContainsSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soHopDong does not contain DEFAULT_SO_HOP_DONG
        defaultCdtChiDaoTuyenShouldNotBeFound("soHopDong.doesNotContain=" + DEFAULT_SO_HOP_DONG);

        // Get all the cdtChiDaoTuyenList where soHopDong does not contain UPDATED_SO_HOP_DONG
        defaultCdtChiDaoTuyenShouldBeFound("soHopDong.doesNotContain=" + UPDATED_SO_HOP_DONG);
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayHopDongIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayHopDong equals to DEFAULT_NGAY_HOP_DONG
        defaultCdtChiDaoTuyenShouldBeFound("ngayHopDong.equals=" + DEFAULT_NGAY_HOP_DONG);

        // Get all the cdtChiDaoTuyenList where ngayHopDong equals to UPDATED_NGAY_HOP_DONG
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayHopDong.equals=" + UPDATED_NGAY_HOP_DONG);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayHopDongIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayHopDong not equals to DEFAULT_NGAY_HOP_DONG
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayHopDong.notEquals=" + DEFAULT_NGAY_HOP_DONG);

        // Get all the cdtChiDaoTuyenList where ngayHopDong not equals to UPDATED_NGAY_HOP_DONG
        defaultCdtChiDaoTuyenShouldBeFound("ngayHopDong.notEquals=" + UPDATED_NGAY_HOP_DONG);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayHopDongIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayHopDong in DEFAULT_NGAY_HOP_DONG or UPDATED_NGAY_HOP_DONG
        defaultCdtChiDaoTuyenShouldBeFound("ngayHopDong.in=" + DEFAULT_NGAY_HOP_DONG + "," + UPDATED_NGAY_HOP_DONG);

        // Get all the cdtChiDaoTuyenList where ngayHopDong equals to UPDATED_NGAY_HOP_DONG
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayHopDong.in=" + UPDATED_NGAY_HOP_DONG);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayHopDongIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayHopDong is not null
        defaultCdtChiDaoTuyenShouldBeFound("ngayHopDong.specified=true");

        // Get all the cdtChiDaoTuyenList where ngayHopDong is null
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayHopDong.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ghiChu equals to DEFAULT_GHI_CHU
        defaultCdtChiDaoTuyenShouldBeFound("ghiChu.equals=" + DEFAULT_GHI_CHU);

        // Get all the cdtChiDaoTuyenList where ghiChu equals to UPDATED_GHI_CHU
        defaultCdtChiDaoTuyenShouldNotBeFound("ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByGhiChuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ghiChu not equals to DEFAULT_GHI_CHU
        defaultCdtChiDaoTuyenShouldNotBeFound("ghiChu.notEquals=" + DEFAULT_GHI_CHU);

        // Get all the cdtChiDaoTuyenList where ghiChu not equals to UPDATED_GHI_CHU
        defaultCdtChiDaoTuyenShouldBeFound("ghiChu.notEquals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ghiChu in DEFAULT_GHI_CHU or UPDATED_GHI_CHU
        defaultCdtChiDaoTuyenShouldBeFound("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU);

        // Get all the cdtChiDaoTuyenList where ghiChu equals to UPDATED_GHI_CHU
        defaultCdtChiDaoTuyenShouldNotBeFound("ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ghiChu is not null
        defaultCdtChiDaoTuyenShouldBeFound("ghiChu.specified=true");

        // Get all the cdtChiDaoTuyenList where ghiChu is null
        defaultCdtChiDaoTuyenShouldNotBeFound("ghiChu.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByGhiChuContainsSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ghiChu contains DEFAULT_GHI_CHU
        defaultCdtChiDaoTuyenShouldBeFound("ghiChu.contains=" + DEFAULT_GHI_CHU);

        // Get all the cdtChiDaoTuyenList where ghiChu contains UPDATED_GHI_CHU
        defaultCdtChiDaoTuyenShouldNotBeFound("ghiChu.contains=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByGhiChuNotContainsSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ghiChu does not contain DEFAULT_GHI_CHU
        defaultCdtChiDaoTuyenShouldNotBeFound("ghiChu.doesNotContain=" + DEFAULT_GHI_CHU);

        // Get all the cdtChiDaoTuyenList where ghiChu does not contain UPDATED_GHI_CHU
        defaultCdtChiDaoTuyenShouldBeFound("ghiChu.doesNotContain=" + UPDATED_GHI_CHU);
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNoiDungIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where noiDung equals to DEFAULT_NOI_DUNG
        defaultCdtChiDaoTuyenShouldBeFound("noiDung.equals=" + DEFAULT_NOI_DUNG);

        // Get all the cdtChiDaoTuyenList where noiDung equals to UPDATED_NOI_DUNG
        defaultCdtChiDaoTuyenShouldNotBeFound("noiDung.equals=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNoiDungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where noiDung not equals to DEFAULT_NOI_DUNG
        defaultCdtChiDaoTuyenShouldNotBeFound("noiDung.notEquals=" + DEFAULT_NOI_DUNG);

        // Get all the cdtChiDaoTuyenList where noiDung not equals to UPDATED_NOI_DUNG
        defaultCdtChiDaoTuyenShouldBeFound("noiDung.notEquals=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNoiDungIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where noiDung in DEFAULT_NOI_DUNG or UPDATED_NOI_DUNG
        defaultCdtChiDaoTuyenShouldBeFound("noiDung.in=" + DEFAULT_NOI_DUNG + "," + UPDATED_NOI_DUNG);

        // Get all the cdtChiDaoTuyenList where noiDung equals to UPDATED_NOI_DUNG
        defaultCdtChiDaoTuyenShouldNotBeFound("noiDung.in=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNoiDungIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where noiDung is not null
        defaultCdtChiDaoTuyenShouldBeFound("noiDung.specified=true");

        // Get all the cdtChiDaoTuyenList where noiDung is null
        defaultCdtChiDaoTuyenShouldNotBeFound("noiDung.specified=false");
    }
                @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNoiDungContainsSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where noiDung contains DEFAULT_NOI_DUNG
        defaultCdtChiDaoTuyenShouldBeFound("noiDung.contains=" + DEFAULT_NOI_DUNG);

        // Get all the cdtChiDaoTuyenList where noiDung contains UPDATED_NOI_DUNG
        defaultCdtChiDaoTuyenShouldNotBeFound("noiDung.contains=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNoiDungNotContainsSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where noiDung does not contain DEFAULT_NOI_DUNG
        defaultCdtChiDaoTuyenShouldNotBeFound("noiDung.doesNotContain=" + DEFAULT_NOI_DUNG);

        // Get all the cdtChiDaoTuyenList where noiDung does not contain UPDATED_NOI_DUNG
        defaultCdtChiDaoTuyenShouldBeFound("noiDung.doesNotContain=" + UPDATED_NOI_DUNG);
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayBDIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayBD equals to DEFAULT_NGAY_BD
        defaultCdtChiDaoTuyenShouldBeFound("ngayBD.equals=" + DEFAULT_NGAY_BD);

        // Get all the cdtChiDaoTuyenList where ngayBD equals to UPDATED_NGAY_BD
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayBD.equals=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayBDIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayBD not equals to DEFAULT_NGAY_BD
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayBD.notEquals=" + DEFAULT_NGAY_BD);

        // Get all the cdtChiDaoTuyenList where ngayBD not equals to UPDATED_NGAY_BD
        defaultCdtChiDaoTuyenShouldBeFound("ngayBD.notEquals=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayBDIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayBD in DEFAULT_NGAY_BD or UPDATED_NGAY_BD
        defaultCdtChiDaoTuyenShouldBeFound("ngayBD.in=" + DEFAULT_NGAY_BD + "," + UPDATED_NGAY_BD);

        // Get all the cdtChiDaoTuyenList where ngayBD equals to UPDATED_NGAY_BD
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayBD.in=" + UPDATED_NGAY_BD);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayBDIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayBD is not null
        defaultCdtChiDaoTuyenShouldBeFound("ngayBD.specified=true");

        // Get all the cdtChiDaoTuyenList where ngayBD is null
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayBD.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayKTIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayKT equals to DEFAULT_NGAY_KT
        defaultCdtChiDaoTuyenShouldBeFound("ngayKT.equals=" + DEFAULT_NGAY_KT);

        // Get all the cdtChiDaoTuyenList where ngayKT equals to UPDATED_NGAY_KT
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayKT.equals=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayKTIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayKT not equals to DEFAULT_NGAY_KT
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayKT.notEquals=" + DEFAULT_NGAY_KT);

        // Get all the cdtChiDaoTuyenList where ngayKT not equals to UPDATED_NGAY_KT
        defaultCdtChiDaoTuyenShouldBeFound("ngayKT.notEquals=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayKTIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayKT in DEFAULT_NGAY_KT or UPDATED_NGAY_KT
        defaultCdtChiDaoTuyenShouldBeFound("ngayKT.in=" + DEFAULT_NGAY_KT + "," + UPDATED_NGAY_KT);

        // Get all the cdtChiDaoTuyenList where ngayKT equals to UPDATED_NGAY_KT
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayKT.in=" + UPDATED_NGAY_KT);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayKTIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayKT is not null
        defaultCdtChiDaoTuyenShouldBeFound("ngayKT.specified=true");

        // Get all the cdtChiDaoTuyenList where ngayKT is null
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayKT.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayTaoIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayTao equals to DEFAULT_NGAY_TAO
        defaultCdtChiDaoTuyenShouldBeFound("ngayTao.equals=" + DEFAULT_NGAY_TAO);

        // Get all the cdtChiDaoTuyenList where ngayTao equals to UPDATED_NGAY_TAO
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayTao.equals=" + UPDATED_NGAY_TAO);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayTaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayTao not equals to DEFAULT_NGAY_TAO
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayTao.notEquals=" + DEFAULT_NGAY_TAO);

        // Get all the cdtChiDaoTuyenList where ngayTao not equals to UPDATED_NGAY_TAO
        defaultCdtChiDaoTuyenShouldBeFound("ngayTao.notEquals=" + UPDATED_NGAY_TAO);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayTaoIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayTao in DEFAULT_NGAY_TAO or UPDATED_NGAY_TAO
        defaultCdtChiDaoTuyenShouldBeFound("ngayTao.in=" + DEFAULT_NGAY_TAO + "," + UPDATED_NGAY_TAO);

        // Get all the cdtChiDaoTuyenList where ngayTao equals to UPDATED_NGAY_TAO
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayTao.in=" + UPDATED_NGAY_TAO);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNgayTaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where ngayTao is not null
        defaultCdtChiDaoTuyenShouldBeFound("ngayTao.specified=true");

        // Get all the cdtChiDaoTuyenList where ngayTao is null
        defaultCdtChiDaoTuyenShouldNotBeFound("ngayTao.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKhamIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham equals to DEFAULT_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKham.equals=" + DEFAULT_SO_BENH_NHAN_KHAM);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham equals to UPDATED_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKham.equals=" + UPDATED_SO_BENH_NHAN_KHAM);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKhamIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham not equals to DEFAULT_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKham.notEquals=" + DEFAULT_SO_BENH_NHAN_KHAM);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham not equals to UPDATED_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKham.notEquals=" + UPDATED_SO_BENH_NHAN_KHAM);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKhamIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham in DEFAULT_SO_BENH_NHAN_KHAM or UPDATED_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKham.in=" + DEFAULT_SO_BENH_NHAN_KHAM + "," + UPDATED_SO_BENH_NHAN_KHAM);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham equals to UPDATED_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKham.in=" + UPDATED_SO_BENH_NHAN_KHAM);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKhamIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham is not null
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKham.specified=true");

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham is null
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKham.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKhamIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham is greater than or equal to DEFAULT_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKham.greaterThanOrEqual=" + DEFAULT_SO_BENH_NHAN_KHAM);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham is greater than or equal to UPDATED_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKham.greaterThanOrEqual=" + UPDATED_SO_BENH_NHAN_KHAM);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKhamIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham is less than or equal to DEFAULT_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKham.lessThanOrEqual=" + DEFAULT_SO_BENH_NHAN_KHAM);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham is less than or equal to SMALLER_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKham.lessThanOrEqual=" + SMALLER_SO_BENH_NHAN_KHAM);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKhamIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham is less than DEFAULT_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKham.lessThan=" + DEFAULT_SO_BENH_NHAN_KHAM);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham is less than UPDATED_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKham.lessThan=" + UPDATED_SO_BENH_NHAN_KHAM);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKhamIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham is greater than DEFAULT_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKham.greaterThan=" + DEFAULT_SO_BENH_NHAN_KHAM);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKham is greater than SMALLER_SO_BENH_NHAN_KHAM
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKham.greaterThan=" + SMALLER_SO_BENH_NHAN_KHAM);
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKyThuatIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat equals to DEFAULT_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKyThuat.equals=" + DEFAULT_SO_BENH_NHAN_KY_THUAT);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat equals to UPDATED_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKyThuat.equals=" + UPDATED_SO_BENH_NHAN_KY_THUAT);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKyThuatIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat not equals to DEFAULT_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKyThuat.notEquals=" + DEFAULT_SO_BENH_NHAN_KY_THUAT);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat not equals to UPDATED_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKyThuat.notEquals=" + UPDATED_SO_BENH_NHAN_KY_THUAT);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKyThuatIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat in DEFAULT_SO_BENH_NHAN_KY_THUAT or UPDATED_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKyThuat.in=" + DEFAULT_SO_BENH_NHAN_KY_THUAT + "," + UPDATED_SO_BENH_NHAN_KY_THUAT);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat equals to UPDATED_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKyThuat.in=" + UPDATED_SO_BENH_NHAN_KY_THUAT);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKyThuatIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat is not null
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKyThuat.specified=true");

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat is null
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKyThuat.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKyThuatIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat is greater than or equal to DEFAULT_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKyThuat.greaterThanOrEqual=" + DEFAULT_SO_BENH_NHAN_KY_THUAT);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat is greater than or equal to UPDATED_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKyThuat.greaterThanOrEqual=" + UPDATED_SO_BENH_NHAN_KY_THUAT);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKyThuatIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat is less than or equal to DEFAULT_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKyThuat.lessThanOrEqual=" + DEFAULT_SO_BENH_NHAN_KY_THUAT);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat is less than or equal to SMALLER_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKyThuat.lessThanOrEqual=" + SMALLER_SO_BENH_NHAN_KY_THUAT);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKyThuatIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat is less than DEFAULT_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKyThuat.lessThan=" + DEFAULT_SO_BENH_NHAN_KY_THUAT);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat is less than UPDATED_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKyThuat.lessThan=" + UPDATED_SO_BENH_NHAN_KY_THUAT);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoBenhNhanKyThuatIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat is greater than DEFAULT_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldNotBeFound("soBenhNhanKyThuat.greaterThan=" + DEFAULT_SO_BENH_NHAN_KY_THUAT);

        // Get all the cdtChiDaoTuyenList where soBenhNhanKyThuat is greater than SMALLER_SO_BENH_NHAN_KY_THUAT
        defaultCdtChiDaoTuyenShouldBeFound("soBenhNhanKyThuat.greaterThan=" + SMALLER_SO_BENH_NHAN_KY_THUAT);
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoCanBoChuyenGiaoIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao equals to DEFAULT_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.equals=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao equals to UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.equals=" + UPDATED_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoCanBoChuyenGiaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao not equals to DEFAULT_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.notEquals=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao not equals to UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.notEquals=" + UPDATED_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoCanBoChuyenGiaoIsInShouldWork() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao in DEFAULT_SO_CAN_BO_CHUYEN_GIAO or UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.in=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO + "," + UPDATED_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao equals to UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.in=" + UPDATED_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoCanBoChuyenGiaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao is not null
        defaultCdtChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.specified=true");

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao is null
        defaultCdtChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.specified=false");
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoCanBoChuyenGiaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao is greater than or equal to DEFAULT_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.greaterThanOrEqual=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao is greater than or equal to UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.greaterThanOrEqual=" + UPDATED_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoCanBoChuyenGiaoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao is less than or equal to DEFAULT_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.lessThanOrEqual=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao is less than or equal to SMALLER_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.lessThanOrEqual=" + SMALLER_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoCanBoChuyenGiaoIsLessThanSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao is less than DEFAULT_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.lessThan=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao is less than UPDATED_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.lessThan=" + UPDATED_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensBySoCanBoChuyenGiaoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao is greater than DEFAULT_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldNotBeFound("soCanBoChuyenGiao.greaterThan=" + DEFAULT_SO_CAN_BO_CHUYEN_GIAO);

        // Get all the cdtChiDaoTuyenList where soCanBoChuyenGiao is greater than SMALLER_SO_CAN_BO_CHUYEN_GIAO
        defaultCdtChiDaoTuyenShouldBeFound("soCanBoChuyenGiao.greaterThan=" + SMALLER_SO_CAN_BO_CHUYEN_GIAO);
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNhanvienIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        CdtNhanVien nhanvien = CdtNhanVienResourceIT.createEntity(em);
        em.persist(nhanvien);
        em.flush();
        cdtChiDaoTuyen.addNhanvien(nhanvien);
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        Long nhanvienId = nhanvien.getId();

        // Get all the cdtChiDaoTuyenList where nhanvien equals to nhanvienId
        defaultCdtChiDaoTuyenShouldBeFound("nhanvienId.equals=" + nhanvienId);

        // Get all the cdtChiDaoTuyenList where nhanvien equals to nhanvienId + 1
        defaultCdtChiDaoTuyenShouldNotBeFound("nhanvienId.equals=" + (nhanvienId + 1));
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByNoidenIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        CdtNoiDen noiden = CdtNoiDenResourceIT.createEntity(em);
        em.persist(noiden);
        em.flush();
        cdtChiDaoTuyen.addNoiden(noiden);
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        Long noidenId = noiden.getId();

        // Get all the cdtChiDaoTuyenList where noiden equals to noidenId
        defaultCdtChiDaoTuyenShouldBeFound("noidenId.equals=" + noidenId);

        // Get all the cdtChiDaoTuyenList where noiden equals to noidenId + 1
        defaultCdtChiDaoTuyenShouldNotBeFound("noidenId.equals=" + (noidenId + 1));
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByKythuatIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        CdtKyThuat kythuat = CdtKyThuatResourceIT.createEntity(em);
        em.persist(kythuat);
        em.flush();
        cdtChiDaoTuyen.addKythuat(kythuat);
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        Long kythuatId = kythuat.getId();

        // Get all the cdtChiDaoTuyenList where kythuat equals to kythuatId
        defaultCdtChiDaoTuyenShouldBeFound("kythuatId.equals=" + kythuatId);

        // Get all the cdtChiDaoTuyenList where kythuat equals to kythuatId + 1
        defaultCdtChiDaoTuyenShouldNotBeFound("kythuatId.equals=" + (kythuatId + 1));
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByVattuIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        CdtVatTu vattu = CdtVatTuResourceIT.createEntity(em);
        em.persist(vattu);
        em.flush();
        cdtChiDaoTuyen.addVattu(vattu);
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        Long vattuId = vattu.getId();

        // Get all the cdtChiDaoTuyenList where vattu equals to vattuId
        defaultCdtChiDaoTuyenShouldBeFound("vattuId.equals=" + vattuId);

        // Get all the cdtChiDaoTuyenList where vattu equals to vattuId + 1
        defaultCdtChiDaoTuyenShouldNotBeFound("vattuId.equals=" + (vattuId + 1));
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByLydocongtacIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        CdtLyDoCongTac lydocongtac = CdtLyDoCongTacResourceIT.createEntity(em);
        em.persist(lydocongtac);
        em.flush();
        cdtChiDaoTuyen.addLydocongtac(lydocongtac);
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        Long lydocongtacId = lydocongtac.getId();

        // Get all the cdtChiDaoTuyenList where lydocongtac equals to lydocongtacId
        defaultCdtChiDaoTuyenShouldBeFound("lydocongtacId.equals=" + lydocongtacId);

        // Get all the cdtChiDaoTuyenList where lydocongtac equals to lydocongtacId + 1
        defaultCdtChiDaoTuyenShouldNotBeFound("lydocongtacId.equals=" + (lydocongtacId + 1));
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByKetquacongtacIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        CdtKetQuaCongTac ketquacongtac = CdtKetQuaCongTacResourceIT.createEntity(em);
        em.persist(ketquacongtac);
        em.flush();
        cdtChiDaoTuyen.addKetquacongtac(ketquacongtac);
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        Long ketquacongtacId = ketquacongtac.getId();

        // Get all the cdtChiDaoTuyenList where ketquacongtac equals to ketquacongtacId
        defaultCdtChiDaoTuyenShouldBeFound("ketquacongtacId.equals=" + ketquacongtacId);

        // Get all the cdtChiDaoTuyenList where ketquacongtac equals to ketquacongtacId + 1
        defaultCdtChiDaoTuyenShouldNotBeFound("ketquacongtacId.equals=" + (ketquacongtacId + 1));
    }


    @Test
    @Transactional
    public void getAllCdtChiDaoTuyensByTaichinhIsEqualToSomething() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        CdtTaiChinh taichinh = CdtTaiChinhResourceIT.createEntity(em);
        em.persist(taichinh);
        em.flush();
        cdtChiDaoTuyen.addTaichinh(taichinh);
        cdtChiDaoTuyenRepository.saveAndFlush(cdtChiDaoTuyen);
        Long taichinhId = taichinh.getId();

        // Get all the cdtChiDaoTuyenList where taichinh equals to taichinhId
        defaultCdtChiDaoTuyenShouldBeFound("taichinhId.equals=" + taichinhId);

        // Get all the cdtChiDaoTuyenList where taichinh equals to taichinhId + 1
        defaultCdtChiDaoTuyenShouldNotBeFound("taichinhId.equals=" + (taichinhId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCdtChiDaoTuyenShouldBeFound(String filter) throws Exception {
        restCdtChiDaoTuyenMockMvc.perform(get("/api/cdt-chi-dao-tuyens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cdtChiDaoTuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].soQuyetDinh").value(hasItem(DEFAULT_SO_QUYET_DINH)))
            .andExpect(jsonPath("$.[*].ngayQuyetDinh").value(hasItem(DEFAULT_NGAY_QUYET_DINH.toString())))
            .andExpect(jsonPath("$.[*].soHopDong").value(hasItem(DEFAULT_SO_HOP_DONG)))
            .andExpect(jsonPath("$.[*].ngayHopDong").value(hasItem(DEFAULT_NGAY_HOP_DONG.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(DEFAULT_NOI_DUNG)))
            .andExpect(jsonPath("$.[*].ngayBD").value(hasItem(DEFAULT_NGAY_BD.toString())))
            .andExpect(jsonPath("$.[*].ngayKT").value(hasItem(DEFAULT_NGAY_KT.toString())))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(DEFAULT_NGAY_TAO.toString())))
            .andExpect(jsonPath("$.[*].soBenhNhanKham").value(hasItem(DEFAULT_SO_BENH_NHAN_KHAM.intValue())))
            .andExpect(jsonPath("$.[*].soBenhNhanKyThuat").value(hasItem(DEFAULT_SO_BENH_NHAN_KY_THUAT.intValue())))
            .andExpect(jsonPath("$.[*].soCanBoChuyenGiao").value(hasItem(DEFAULT_SO_CAN_BO_CHUYEN_GIAO.intValue())));

        // Check, that the count call also returns 1
        restCdtChiDaoTuyenMockMvc.perform(get("/api/cdt-chi-dao-tuyens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCdtChiDaoTuyenShouldNotBeFound(String filter) throws Exception {
        restCdtChiDaoTuyenMockMvc.perform(get("/api/cdt-chi-dao-tuyens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCdtChiDaoTuyenMockMvc.perform(get("/api/cdt-chi-dao-tuyens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCdtChiDaoTuyen() throws Exception {
        // Get the cdtChiDaoTuyen
        restCdtChiDaoTuyenMockMvc.perform(get("/api/cdt-chi-dao-tuyens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCdtChiDaoTuyen() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenService.save(cdtChiDaoTuyen);

        int databaseSizeBeforeUpdate = cdtChiDaoTuyenRepository.findAll().size();

        // Update the cdtChiDaoTuyen
        CdtChiDaoTuyen updatedCdtChiDaoTuyen = cdtChiDaoTuyenRepository.findById(cdtChiDaoTuyen.getId()).get();
        // Disconnect from session so that the updates on updatedCdtChiDaoTuyen are not directly saved in db
        em.detach(updatedCdtChiDaoTuyen);
        updatedCdtChiDaoTuyen
            .soQuyetDinh(UPDATED_SO_QUYET_DINH)
            .ngayQuyetDinh(UPDATED_NGAY_QUYET_DINH)
            .soHopDong(UPDATED_SO_HOP_DONG)
            .ngayHopDong(UPDATED_NGAY_HOP_DONG)
            .ghiChu(UPDATED_GHI_CHU)
            .noiDung(UPDATED_NOI_DUNG)
            .ngayBD(UPDATED_NGAY_BD)
            .ngayKT(UPDATED_NGAY_KT)
            .ngayTao(UPDATED_NGAY_TAO)
            .soBenhNhanKham(UPDATED_SO_BENH_NHAN_KHAM)
            .soBenhNhanKyThuat(UPDATED_SO_BENH_NHAN_KY_THUAT)
            .soCanBoChuyenGiao(UPDATED_SO_CAN_BO_CHUYEN_GIAO);

        restCdtChiDaoTuyenMockMvc.perform(put("/api/cdt-chi-dao-tuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCdtChiDaoTuyen)))
            .andExpect(status().isOk());

        // Validate the CdtChiDaoTuyen in the database
        List<CdtChiDaoTuyen> cdtChiDaoTuyenList = cdtChiDaoTuyenRepository.findAll();
        assertThat(cdtChiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
        CdtChiDaoTuyen testCdtChiDaoTuyen = cdtChiDaoTuyenList.get(cdtChiDaoTuyenList.size() - 1);
        assertThat(testCdtChiDaoTuyen.getSoQuyetDinh()).isEqualTo(UPDATED_SO_QUYET_DINH);
        assertThat(testCdtChiDaoTuyen.getNgayQuyetDinh()).isEqualTo(UPDATED_NGAY_QUYET_DINH);
        assertThat(testCdtChiDaoTuyen.getSoHopDong()).isEqualTo(UPDATED_SO_HOP_DONG);
        assertThat(testCdtChiDaoTuyen.getNgayHopDong()).isEqualTo(UPDATED_NGAY_HOP_DONG);
        assertThat(testCdtChiDaoTuyen.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testCdtChiDaoTuyen.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testCdtChiDaoTuyen.getNgayBD()).isEqualTo(UPDATED_NGAY_BD);
        assertThat(testCdtChiDaoTuyen.getNgayKT()).isEqualTo(UPDATED_NGAY_KT);
        assertThat(testCdtChiDaoTuyen.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testCdtChiDaoTuyen.getSoBenhNhanKham()).isEqualTo(UPDATED_SO_BENH_NHAN_KHAM);
        assertThat(testCdtChiDaoTuyen.getSoBenhNhanKyThuat()).isEqualTo(UPDATED_SO_BENH_NHAN_KY_THUAT);
        assertThat(testCdtChiDaoTuyen.getSoCanBoChuyenGiao()).isEqualTo(UPDATED_SO_CAN_BO_CHUYEN_GIAO);
    }

    @Test
    @Transactional
    public void updateNonExistingCdtChiDaoTuyen() throws Exception {
        int databaseSizeBeforeUpdate = cdtChiDaoTuyenRepository.findAll().size();

        // Create the CdtChiDaoTuyen

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCdtChiDaoTuyenMockMvc.perform(put("/api/cdt-chi-dao-tuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cdtChiDaoTuyen)))
            .andExpect(status().isBadRequest());

        // Validate the CdtChiDaoTuyen in the database
        List<CdtChiDaoTuyen> cdtChiDaoTuyenList = cdtChiDaoTuyenRepository.findAll();
        assertThat(cdtChiDaoTuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCdtChiDaoTuyen() throws Exception {
        // Initialize the database
        cdtChiDaoTuyenService.save(cdtChiDaoTuyen);

        int databaseSizeBeforeDelete = cdtChiDaoTuyenRepository.findAll().size();

        // Delete the cdtChiDaoTuyen
        restCdtChiDaoTuyenMockMvc.perform(delete("/api/cdt-chi-dao-tuyens/{id}", cdtChiDaoTuyen.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CdtChiDaoTuyen> cdtChiDaoTuyenList = cdtChiDaoTuyenRepository.findAll();
        assertThat(cdtChiDaoTuyenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
