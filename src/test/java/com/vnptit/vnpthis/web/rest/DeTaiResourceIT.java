package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.nckh.DeTai;
import com.vnptit.vnpthis.domain.nckh.UpFile;
import com.vnptit.vnpthis.domain.nckh.TienDo;
import com.vnptit.vnpthis.domain.nckh.NhanSu;
import com.vnptit.vnpthis.domain.nckh.DuToan;
import com.vnptit.vnpthis.domain.nckh.DanhGia;
import com.vnptit.vnpthis.domain.nckh.ChuyenMuc;
import com.vnptit.vnpthis.repository.DeTaiRepository;
import com.vnptit.vnpthis.service.DeTaiService;
import com.vnptit.vnpthis.service.dto.DeTaiDTO;
import com.vnptit.vnpthis.service.mapper.DeTaiMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.service.dto.DeTaiCriteria;
import com.vnptit.vnpthis.service.DeTaiQueryService;

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
 * Integration tests for the {@link DeTaiResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class DeTaiResourceIT {

    private static final Integer DEFAULT_SOTT = 1;
    private static final Integer UPDATED_SOTT = 2;
    private static final Integer SMALLER_SOTT = 1 - 1;

    private static final String DEFAULT_TENDETAI = "AAAAAAAAAA";
    private static final String UPDATED_TENDETAI = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAYTAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYTAO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYTAO = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_NGAYPHEDUYET = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYPHEDUYET = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYPHEDUYET = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_NGAYBD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYBD = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYBD = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_NGAYKT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYKT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYKT = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_HIENTRANG = 1;
    private static final Integer UPDATED_HIENTRANG = 2;
    private static final Integer SMALLER_HIENTRANG = 1 - 1;

    private static final Integer DEFAULT_XUATBAN = 1;
    private static final Integer UPDATED_XUATBAN = 2;
    private static final Integer SMALLER_XUATBAN = 1 - 1;

    private static final Integer DEFAULT_CAPQUANLY = 1;
    private static final Integer UPDATED_CAPQUANLY = 2;
    private static final Integer SMALLER_CAPQUANLY = 1 - 1;

    private static final LocalDate DEFAULT_NGAYNGHIEMTHU = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAYNGHIEMTHU = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAYNGHIEMTHU = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_KINHPHITHUCHIEN = 1;
    private static final Integer UPDATED_KINHPHITHUCHIEN = 2;
    private static final Integer SMALLER_KINHPHITHUCHIEN = 1 - 1;

    private static final String DEFAULT_NGUONKINHPHI = "AAAAAAAAAA";
    private static final String UPDATED_NGUONKINHPHI = "BBBBBBBBBB";

    private static final String DEFAULT_MUCTIEU = "AAAAAAAAAA";
    private static final String UPDATED_MUCTIEU = "BBBBBBBBBB";

    private static final Integer DEFAULT_KINHPHI_DUKIEN = 1;
    private static final Integer UPDATED_KINHPHI_DUKIEN = 2;
    private static final Integer SMALLER_KINHPHI_DUKIEN = 1 - 1;

    private static final Integer DEFAULT_CHUNHIEMDETAI = 1;
    private static final Integer UPDATED_CHUNHIEMDETAI = 2;
    private static final Integer SMALLER_CHUNHIEMDETAI = 1 - 1;

    private static final String DEFAULT_NOIDUNGTONGQUAN = "AAAAAAAAAA";
    private static final String UPDATED_NOIDUNGTONGQUAN = "BBBBBBBBBB";

    private static final Integer DEFAULT_KINHPHI_HOANTHANH = 1;
    private static final Integer UPDATED_KINHPHI_HOANTHANH = 2;
    private static final Integer SMALLER_KINHPHI_HOANTHANH = 1 - 1;

    private static final Integer DEFAULT_TONGDIEM = 1;
    private static final Integer UPDATED_TONGDIEM = 2;
    private static final Integer SMALLER_TONGDIEM = 1 - 1;

    private static final String DEFAULT_GHICHU = "AAAAAAAAAA";
    private static final String UPDATED_GHICHU = "BBBBBBBBBB";

    private static final Integer DEFAULT_NGUOI_CN = 1;
    private static final Integer UPDATED_NGUOI_CN = 2;
    private static final Integer SMALLER_NGUOI_CN = 1 - 1;

    private static final LocalDate DEFAULT_NGAY_CN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_CN = LocalDate.ofEpochDay(-1L);

    @Autowired
    private DeTaiRepository deTaiRepository;

    @Autowired
    private DeTaiMapper deTaiMapper;

    @Autowired
    private DeTaiService deTaiService;

    @Autowired
    private DeTaiQueryService deTaiQueryService;

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

    private MockMvc restDeTaiMockMvc;

    private DeTai deTai;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeTaiResource deTaiResource = new DeTaiResource(deTaiService, deTaiQueryService);
        this.restDeTaiMockMvc = MockMvcBuilders.standaloneSetup(deTaiResource)
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
    public static DeTai createEntity(EntityManager em) {
        DeTai deTai = new DeTai()
            .sott(DEFAULT_SOTT)
            .tendetai(DEFAULT_TENDETAI)
            .ngaytao(DEFAULT_NGAYTAO)
            .ngaypheduyet(DEFAULT_NGAYPHEDUYET)
            .ngaybd(DEFAULT_NGAYBD)
            .ngaykt(DEFAULT_NGAYKT)
            .hientrang(DEFAULT_HIENTRANG)
            .xuatban(DEFAULT_XUATBAN)
            .capquanly(DEFAULT_CAPQUANLY)
            .ngaynghiemthu(DEFAULT_NGAYNGHIEMTHU)
            .kinhphithuchien(DEFAULT_KINHPHITHUCHIEN)
            .nguonkinhphi(DEFAULT_NGUONKINHPHI)
            .muctieu(DEFAULT_MUCTIEU)
            .kinhphiDukien(DEFAULT_KINHPHI_DUKIEN)
            .chunhiemdetai(DEFAULT_CHUNHIEMDETAI)
            .noidungtongquan(DEFAULT_NOIDUNGTONGQUAN)
            .kinhphiHoanthanh(DEFAULT_KINHPHI_HOANTHANH)
            .tongdiem(DEFAULT_TONGDIEM)
            .ghichu(DEFAULT_GHICHU)
            .nguoiCn(DEFAULT_NGUOI_CN)
            .ngayCn(DEFAULT_NGAY_CN);
        return deTai;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeTai createUpdatedEntity(EntityManager em) {
        DeTai deTai = new DeTai()
            .sott(UPDATED_SOTT)
            .tendetai(UPDATED_TENDETAI)
            .ngaytao(UPDATED_NGAYTAO)
            .ngaypheduyet(UPDATED_NGAYPHEDUYET)
            .ngaybd(UPDATED_NGAYBD)
            .ngaykt(UPDATED_NGAYKT)
            .hientrang(UPDATED_HIENTRANG)
            .xuatban(UPDATED_XUATBAN)
            .capquanly(UPDATED_CAPQUANLY)
            .ngaynghiemthu(UPDATED_NGAYNGHIEMTHU)
            .kinhphithuchien(UPDATED_KINHPHITHUCHIEN)
            .nguonkinhphi(UPDATED_NGUONKINHPHI)
            .muctieu(UPDATED_MUCTIEU)
            .kinhphiDukien(UPDATED_KINHPHI_DUKIEN)
            .chunhiemdetai(UPDATED_CHUNHIEMDETAI)
            .noidungtongquan(UPDATED_NOIDUNGTONGQUAN)
            .kinhphiHoanthanh(UPDATED_KINHPHI_HOANTHANH)
            .tongdiem(UPDATED_TONGDIEM)
            .ghichu(UPDATED_GHICHU)
            .nguoiCn(UPDATED_NGUOI_CN)
            .ngayCn(UPDATED_NGAY_CN);
        return deTai;
    }

    @BeforeEach
    public void initTest() {
        deTai = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeTai() throws Exception {
        int databaseSizeBeforeCreate = deTaiRepository.findAll().size();

        // Create the DeTai
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);
        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isCreated());

        // Validate the DeTai in the database
        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeCreate + 1);
        DeTai testDeTai = deTaiList.get(deTaiList.size() - 1);
        assertThat(testDeTai.getSott()).isEqualTo(DEFAULT_SOTT);
        assertThat(testDeTai.getTendetai()).isEqualTo(DEFAULT_TENDETAI);
        assertThat(testDeTai.getNgaytao()).isEqualTo(DEFAULT_NGAYTAO);
        assertThat(testDeTai.getNgaypheduyet()).isEqualTo(DEFAULT_NGAYPHEDUYET);
        assertThat(testDeTai.getNgaybd()).isEqualTo(DEFAULT_NGAYBD);
        assertThat(testDeTai.getNgaykt()).isEqualTo(DEFAULT_NGAYKT);
        assertThat(testDeTai.getHientrang()).isEqualTo(DEFAULT_HIENTRANG);
        assertThat(testDeTai.getXuatban()).isEqualTo(DEFAULT_XUATBAN);
        assertThat(testDeTai.getCapquanly()).isEqualTo(DEFAULT_CAPQUANLY);
        assertThat(testDeTai.getNgaynghiemthu()).isEqualTo(DEFAULT_NGAYNGHIEMTHU);
        assertThat(testDeTai.getKinhphithuchien()).isEqualTo(DEFAULT_KINHPHITHUCHIEN);
        assertThat(testDeTai.getNguonkinhphi()).isEqualTo(DEFAULT_NGUONKINHPHI);
        assertThat(testDeTai.getMuctieu()).isEqualTo(DEFAULT_MUCTIEU);
        assertThat(testDeTai.getKinhphiDukien()).isEqualTo(DEFAULT_KINHPHI_DUKIEN);
        assertThat(testDeTai.getChunhiemdetai()).isEqualTo(DEFAULT_CHUNHIEMDETAI);
        assertThat(testDeTai.getNoidungtongquan()).isEqualTo(DEFAULT_NOIDUNGTONGQUAN);
        assertThat(testDeTai.getKinhphiHoanthanh()).isEqualTo(DEFAULT_KINHPHI_HOANTHANH);
        assertThat(testDeTai.getTongdiem()).isEqualTo(DEFAULT_TONGDIEM);
        assertThat(testDeTai.getGhichu()).isEqualTo(DEFAULT_GHICHU);
        assertThat(testDeTai.getNguoiCn()).isEqualTo(DEFAULT_NGUOI_CN);
        assertThat(testDeTai.getNgayCn()).isEqualTo(DEFAULT_NGAY_CN);
    }

    @Test
    @Transactional
    public void createDeTaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deTaiRepository.findAll().size();

        // Create the DeTai with an existing ID
        deTai.setId(1L);
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeTai in the database
        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSottIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setSott(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTendetaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setTendetai(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgaytaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNgaytao(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgaypheduyetIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNgaypheduyet(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgaybdIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNgaybd(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgayktIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNgaykt(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHientrangIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setHientrang(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkXuatbanIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setXuatban(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCapquanlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setCapquanly(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgaynghiemthuIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNgaynghiemthu(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKinhphithuchienIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setKinhphithuchien(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNguonkinhphiIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNguonkinhphi(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMuctieuIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setMuctieu(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKinhphiDukienIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setKinhphiDukien(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChunhiemdetaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setChunhiemdetai(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNoidungtongquanIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNoidungtongquan(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKinhphiHoanthanhIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setKinhphiHoanthanh(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTongdiemIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setTongdiem(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGhichuIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setGhichu(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNguoiCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNguoiCn(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgayCnIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNgayCn(null);

        // Create the DeTai, which fails.
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeTais() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList
        restDeTaiMockMvc.perform(get("/api/de-tais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deTai.getId().intValue())))
            .andExpect(jsonPath("$.[*].sott").value(hasItem(DEFAULT_SOTT)))
            .andExpect(jsonPath("$.[*].tendetai").value(hasItem(DEFAULT_TENDETAI)))
            .andExpect(jsonPath("$.[*].ngaytao").value(hasItem(DEFAULT_NGAYTAO.toString())))
            .andExpect(jsonPath("$.[*].ngaypheduyet").value(hasItem(DEFAULT_NGAYPHEDUYET.toString())))
            .andExpect(jsonPath("$.[*].ngaybd").value(hasItem(DEFAULT_NGAYBD.toString())))
            .andExpect(jsonPath("$.[*].ngaykt").value(hasItem(DEFAULT_NGAYKT.toString())))
            .andExpect(jsonPath("$.[*].hientrang").value(hasItem(DEFAULT_HIENTRANG)))
            .andExpect(jsonPath("$.[*].xuatban").value(hasItem(DEFAULT_XUATBAN)))
            .andExpect(jsonPath("$.[*].capquanly").value(hasItem(DEFAULT_CAPQUANLY)))
            .andExpect(jsonPath("$.[*].ngaynghiemthu").value(hasItem(DEFAULT_NGAYNGHIEMTHU.toString())))
            .andExpect(jsonPath("$.[*].kinhphithuchien").value(hasItem(DEFAULT_KINHPHITHUCHIEN)))
            .andExpect(jsonPath("$.[*].nguonkinhphi").value(hasItem(DEFAULT_NGUONKINHPHI)))
            .andExpect(jsonPath("$.[*].muctieu").value(hasItem(DEFAULT_MUCTIEU)))
            .andExpect(jsonPath("$.[*].kinhphiDukien").value(hasItem(DEFAULT_KINHPHI_DUKIEN)))
            .andExpect(jsonPath("$.[*].chunhiemdetai").value(hasItem(DEFAULT_CHUNHIEMDETAI)))
            .andExpect(jsonPath("$.[*].noidungtongquan").value(hasItem(DEFAULT_NOIDUNGTONGQUAN)))
            .andExpect(jsonPath("$.[*].kinhphiHoanthanh").value(hasItem(DEFAULT_KINHPHI_HOANTHANH)))
            .andExpect(jsonPath("$.[*].tongdiem").value(hasItem(DEFAULT_TONGDIEM)))
            .andExpect(jsonPath("$.[*].ghichu").value(hasItem(DEFAULT_GHICHU)))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())));
    }

    @Test
    @Transactional
    public void getDeTai() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get the deTai
        restDeTaiMockMvc.perform(get("/api/de-tais/{id}", deTai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(deTai.getId().intValue()))
            .andExpect(jsonPath("$.sott").value(DEFAULT_SOTT))
            .andExpect(jsonPath("$.tendetai").value(DEFAULT_TENDETAI))
            .andExpect(jsonPath("$.ngaytao").value(DEFAULT_NGAYTAO.toString()))
            .andExpect(jsonPath("$.ngaypheduyet").value(DEFAULT_NGAYPHEDUYET.toString()))
            .andExpect(jsonPath("$.ngaybd").value(DEFAULT_NGAYBD.toString()))
            .andExpect(jsonPath("$.ngaykt").value(DEFAULT_NGAYKT.toString()))
            .andExpect(jsonPath("$.hientrang").value(DEFAULT_HIENTRANG))
            .andExpect(jsonPath("$.xuatban").value(DEFAULT_XUATBAN))
            .andExpect(jsonPath("$.capquanly").value(DEFAULT_CAPQUANLY))
            .andExpect(jsonPath("$.ngaynghiemthu").value(DEFAULT_NGAYNGHIEMTHU.toString()))
            .andExpect(jsonPath("$.kinhphithuchien").value(DEFAULT_KINHPHITHUCHIEN))
            .andExpect(jsonPath("$.nguonkinhphi").value(DEFAULT_NGUONKINHPHI))
            .andExpect(jsonPath("$.muctieu").value(DEFAULT_MUCTIEU))
            .andExpect(jsonPath("$.kinhphiDukien").value(DEFAULT_KINHPHI_DUKIEN))
            .andExpect(jsonPath("$.chunhiemdetai").value(DEFAULT_CHUNHIEMDETAI))
            .andExpect(jsonPath("$.noidungtongquan").value(DEFAULT_NOIDUNGTONGQUAN))
            .andExpect(jsonPath("$.kinhphiHoanthanh").value(DEFAULT_KINHPHI_HOANTHANH))
            .andExpect(jsonPath("$.tongdiem").value(DEFAULT_TONGDIEM))
            .andExpect(jsonPath("$.ghichu").value(DEFAULT_GHICHU))
            .andExpect(jsonPath("$.nguoiCn").value(DEFAULT_NGUOI_CN))
            .andExpect(jsonPath("$.ngayCn").value(DEFAULT_NGAY_CN.toString()));
    }


    @Test
    @Transactional
    public void getDeTaisByIdFiltering() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        Long id = deTai.getId();

        defaultDeTaiShouldBeFound("id.equals=" + id);
        defaultDeTaiShouldNotBeFound("id.notEquals=" + id);

        defaultDeTaiShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDeTaiShouldNotBeFound("id.greaterThan=" + id);

        defaultDeTaiShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDeTaiShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDeTaisBySottIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where sott equals to DEFAULT_SOTT
        defaultDeTaiShouldBeFound("sott.equals=" + DEFAULT_SOTT);

        // Get all the deTaiList where sott equals to UPDATED_SOTT
        defaultDeTaiShouldNotBeFound("sott.equals=" + UPDATED_SOTT);
    }

    @Test
    @Transactional
    public void getAllDeTaisBySottIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where sott not equals to DEFAULT_SOTT
        defaultDeTaiShouldNotBeFound("sott.notEquals=" + DEFAULT_SOTT);

        // Get all the deTaiList where sott not equals to UPDATED_SOTT
        defaultDeTaiShouldBeFound("sott.notEquals=" + UPDATED_SOTT);
    }

    @Test
    @Transactional
    public void getAllDeTaisBySottIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where sott in DEFAULT_SOTT or UPDATED_SOTT
        defaultDeTaiShouldBeFound("sott.in=" + DEFAULT_SOTT + "," + UPDATED_SOTT);

        // Get all the deTaiList where sott equals to UPDATED_SOTT
        defaultDeTaiShouldNotBeFound("sott.in=" + UPDATED_SOTT);
    }

    @Test
    @Transactional
    public void getAllDeTaisBySottIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where sott is not null
        defaultDeTaiShouldBeFound("sott.specified=true");

        // Get all the deTaiList where sott is null
        defaultDeTaiShouldNotBeFound("sott.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisBySottIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where sott is greater than or equal to DEFAULT_SOTT
        defaultDeTaiShouldBeFound("sott.greaterThanOrEqual=" + DEFAULT_SOTT);

        // Get all the deTaiList where sott is greater than or equal to UPDATED_SOTT
        defaultDeTaiShouldNotBeFound("sott.greaterThanOrEqual=" + UPDATED_SOTT);
    }

    @Test
    @Transactional
    public void getAllDeTaisBySottIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where sott is less than or equal to DEFAULT_SOTT
        defaultDeTaiShouldBeFound("sott.lessThanOrEqual=" + DEFAULT_SOTT);

        // Get all the deTaiList where sott is less than or equal to SMALLER_SOTT
        defaultDeTaiShouldNotBeFound("sott.lessThanOrEqual=" + SMALLER_SOTT);
    }

    @Test
    @Transactional
    public void getAllDeTaisBySottIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where sott is less than DEFAULT_SOTT
        defaultDeTaiShouldNotBeFound("sott.lessThan=" + DEFAULT_SOTT);

        // Get all the deTaiList where sott is less than UPDATED_SOTT
        defaultDeTaiShouldBeFound("sott.lessThan=" + UPDATED_SOTT);
    }

    @Test
    @Transactional
    public void getAllDeTaisBySottIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where sott is greater than DEFAULT_SOTT
        defaultDeTaiShouldNotBeFound("sott.greaterThan=" + DEFAULT_SOTT);

        // Get all the deTaiList where sott is greater than SMALLER_SOTT
        defaultDeTaiShouldBeFound("sott.greaterThan=" + SMALLER_SOTT);
    }


    @Test
    @Transactional
    public void getAllDeTaisByTendetaiIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tendetai equals to DEFAULT_TENDETAI
        defaultDeTaiShouldBeFound("tendetai.equals=" + DEFAULT_TENDETAI);

        // Get all the deTaiList where tendetai equals to UPDATED_TENDETAI
        defaultDeTaiShouldNotBeFound("tendetai.equals=" + UPDATED_TENDETAI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByTendetaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tendetai not equals to DEFAULT_TENDETAI
        defaultDeTaiShouldNotBeFound("tendetai.notEquals=" + DEFAULT_TENDETAI);

        // Get all the deTaiList where tendetai not equals to UPDATED_TENDETAI
        defaultDeTaiShouldBeFound("tendetai.notEquals=" + UPDATED_TENDETAI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByTendetaiIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tendetai in DEFAULT_TENDETAI or UPDATED_TENDETAI
        defaultDeTaiShouldBeFound("tendetai.in=" + DEFAULT_TENDETAI + "," + UPDATED_TENDETAI);

        // Get all the deTaiList where tendetai equals to UPDATED_TENDETAI
        defaultDeTaiShouldNotBeFound("tendetai.in=" + UPDATED_TENDETAI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByTendetaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tendetai is not null
        defaultDeTaiShouldBeFound("tendetai.specified=true");

        // Get all the deTaiList where tendetai is null
        defaultDeTaiShouldNotBeFound("tendetai.specified=false");
    }
                @Test
    @Transactional
    public void getAllDeTaisByTendetaiContainsSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tendetai contains DEFAULT_TENDETAI
        defaultDeTaiShouldBeFound("tendetai.contains=" + DEFAULT_TENDETAI);

        // Get all the deTaiList where tendetai contains UPDATED_TENDETAI
        defaultDeTaiShouldNotBeFound("tendetai.contains=" + UPDATED_TENDETAI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByTendetaiNotContainsSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tendetai does not contain DEFAULT_TENDETAI
        defaultDeTaiShouldNotBeFound("tendetai.doesNotContain=" + DEFAULT_TENDETAI);

        // Get all the deTaiList where tendetai does not contain UPDATED_TENDETAI
        defaultDeTaiShouldBeFound("tendetai.doesNotContain=" + UPDATED_TENDETAI);
    }


    @Test
    @Transactional
    public void getAllDeTaisByNgaytaoIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaytao equals to DEFAULT_NGAYTAO
        defaultDeTaiShouldBeFound("ngaytao.equals=" + DEFAULT_NGAYTAO);

        // Get all the deTaiList where ngaytao equals to UPDATED_NGAYTAO
        defaultDeTaiShouldNotBeFound("ngaytao.equals=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaytaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaytao not equals to DEFAULT_NGAYTAO
        defaultDeTaiShouldNotBeFound("ngaytao.notEquals=" + DEFAULT_NGAYTAO);

        // Get all the deTaiList where ngaytao not equals to UPDATED_NGAYTAO
        defaultDeTaiShouldBeFound("ngaytao.notEquals=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaytaoIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaytao in DEFAULT_NGAYTAO or UPDATED_NGAYTAO
        defaultDeTaiShouldBeFound("ngaytao.in=" + DEFAULT_NGAYTAO + "," + UPDATED_NGAYTAO);

        // Get all the deTaiList where ngaytao equals to UPDATED_NGAYTAO
        defaultDeTaiShouldNotBeFound("ngaytao.in=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaytaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaytao is not null
        defaultDeTaiShouldBeFound("ngaytao.specified=true");

        // Get all the deTaiList where ngaytao is null
        defaultDeTaiShouldNotBeFound("ngaytao.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaytaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaytao is greater than or equal to DEFAULT_NGAYTAO
        defaultDeTaiShouldBeFound("ngaytao.greaterThanOrEqual=" + DEFAULT_NGAYTAO);

        // Get all the deTaiList where ngaytao is greater than or equal to UPDATED_NGAYTAO
        defaultDeTaiShouldNotBeFound("ngaytao.greaterThanOrEqual=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaytaoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaytao is less than or equal to DEFAULT_NGAYTAO
        defaultDeTaiShouldBeFound("ngaytao.lessThanOrEqual=" + DEFAULT_NGAYTAO);

        // Get all the deTaiList where ngaytao is less than or equal to SMALLER_NGAYTAO
        defaultDeTaiShouldNotBeFound("ngaytao.lessThanOrEqual=" + SMALLER_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaytaoIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaytao is less than DEFAULT_NGAYTAO
        defaultDeTaiShouldNotBeFound("ngaytao.lessThan=" + DEFAULT_NGAYTAO);

        // Get all the deTaiList where ngaytao is less than UPDATED_NGAYTAO
        defaultDeTaiShouldBeFound("ngaytao.lessThan=" + UPDATED_NGAYTAO);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaytaoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaytao is greater than DEFAULT_NGAYTAO
        defaultDeTaiShouldNotBeFound("ngaytao.greaterThan=" + DEFAULT_NGAYTAO);

        // Get all the deTaiList where ngaytao is greater than SMALLER_NGAYTAO
        defaultDeTaiShouldBeFound("ngaytao.greaterThan=" + SMALLER_NGAYTAO);
    }


    @Test
    @Transactional
    public void getAllDeTaisByNgaypheduyetIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaypheduyet equals to DEFAULT_NGAYPHEDUYET
        defaultDeTaiShouldBeFound("ngaypheduyet.equals=" + DEFAULT_NGAYPHEDUYET);

        // Get all the deTaiList where ngaypheduyet equals to UPDATED_NGAYPHEDUYET
        defaultDeTaiShouldNotBeFound("ngaypheduyet.equals=" + UPDATED_NGAYPHEDUYET);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaypheduyetIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaypheduyet not equals to DEFAULT_NGAYPHEDUYET
        defaultDeTaiShouldNotBeFound("ngaypheduyet.notEquals=" + DEFAULT_NGAYPHEDUYET);

        // Get all the deTaiList where ngaypheduyet not equals to UPDATED_NGAYPHEDUYET
        defaultDeTaiShouldBeFound("ngaypheduyet.notEquals=" + UPDATED_NGAYPHEDUYET);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaypheduyetIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaypheduyet in DEFAULT_NGAYPHEDUYET or UPDATED_NGAYPHEDUYET
        defaultDeTaiShouldBeFound("ngaypheduyet.in=" + DEFAULT_NGAYPHEDUYET + "," + UPDATED_NGAYPHEDUYET);

        // Get all the deTaiList where ngaypheduyet equals to UPDATED_NGAYPHEDUYET
        defaultDeTaiShouldNotBeFound("ngaypheduyet.in=" + UPDATED_NGAYPHEDUYET);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaypheduyetIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaypheduyet is not null
        defaultDeTaiShouldBeFound("ngaypheduyet.specified=true");

        // Get all the deTaiList where ngaypheduyet is null
        defaultDeTaiShouldNotBeFound("ngaypheduyet.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaypheduyetIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaypheduyet is greater than or equal to DEFAULT_NGAYPHEDUYET
        defaultDeTaiShouldBeFound("ngaypheduyet.greaterThanOrEqual=" + DEFAULT_NGAYPHEDUYET);

        // Get all the deTaiList where ngaypheduyet is greater than or equal to UPDATED_NGAYPHEDUYET
        defaultDeTaiShouldNotBeFound("ngaypheduyet.greaterThanOrEqual=" + UPDATED_NGAYPHEDUYET);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaypheduyetIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaypheduyet is less than or equal to DEFAULT_NGAYPHEDUYET
        defaultDeTaiShouldBeFound("ngaypheduyet.lessThanOrEqual=" + DEFAULT_NGAYPHEDUYET);

        // Get all the deTaiList where ngaypheduyet is less than or equal to SMALLER_NGAYPHEDUYET
        defaultDeTaiShouldNotBeFound("ngaypheduyet.lessThanOrEqual=" + SMALLER_NGAYPHEDUYET);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaypheduyetIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaypheduyet is less than DEFAULT_NGAYPHEDUYET
        defaultDeTaiShouldNotBeFound("ngaypheduyet.lessThan=" + DEFAULT_NGAYPHEDUYET);

        // Get all the deTaiList where ngaypheduyet is less than UPDATED_NGAYPHEDUYET
        defaultDeTaiShouldBeFound("ngaypheduyet.lessThan=" + UPDATED_NGAYPHEDUYET);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaypheduyetIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaypheduyet is greater than DEFAULT_NGAYPHEDUYET
        defaultDeTaiShouldNotBeFound("ngaypheduyet.greaterThan=" + DEFAULT_NGAYPHEDUYET);

        // Get all the deTaiList where ngaypheduyet is greater than SMALLER_NGAYPHEDUYET
        defaultDeTaiShouldBeFound("ngaypheduyet.greaterThan=" + SMALLER_NGAYPHEDUYET);
    }


    @Test
    @Transactional
    public void getAllDeTaisByNgaybdIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaybd equals to DEFAULT_NGAYBD
        defaultDeTaiShouldBeFound("ngaybd.equals=" + DEFAULT_NGAYBD);

        // Get all the deTaiList where ngaybd equals to UPDATED_NGAYBD
        defaultDeTaiShouldNotBeFound("ngaybd.equals=" + UPDATED_NGAYBD);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaybdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaybd not equals to DEFAULT_NGAYBD
        defaultDeTaiShouldNotBeFound("ngaybd.notEquals=" + DEFAULT_NGAYBD);

        // Get all the deTaiList where ngaybd not equals to UPDATED_NGAYBD
        defaultDeTaiShouldBeFound("ngaybd.notEquals=" + UPDATED_NGAYBD);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaybdIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaybd in DEFAULT_NGAYBD or UPDATED_NGAYBD
        defaultDeTaiShouldBeFound("ngaybd.in=" + DEFAULT_NGAYBD + "," + UPDATED_NGAYBD);

        // Get all the deTaiList where ngaybd equals to UPDATED_NGAYBD
        defaultDeTaiShouldNotBeFound("ngaybd.in=" + UPDATED_NGAYBD);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaybdIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaybd is not null
        defaultDeTaiShouldBeFound("ngaybd.specified=true");

        // Get all the deTaiList where ngaybd is null
        defaultDeTaiShouldNotBeFound("ngaybd.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaybdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaybd is greater than or equal to DEFAULT_NGAYBD
        defaultDeTaiShouldBeFound("ngaybd.greaterThanOrEqual=" + DEFAULT_NGAYBD);

        // Get all the deTaiList where ngaybd is greater than or equal to UPDATED_NGAYBD
        defaultDeTaiShouldNotBeFound("ngaybd.greaterThanOrEqual=" + UPDATED_NGAYBD);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaybdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaybd is less than or equal to DEFAULT_NGAYBD
        defaultDeTaiShouldBeFound("ngaybd.lessThanOrEqual=" + DEFAULT_NGAYBD);

        // Get all the deTaiList where ngaybd is less than or equal to SMALLER_NGAYBD
        defaultDeTaiShouldNotBeFound("ngaybd.lessThanOrEqual=" + SMALLER_NGAYBD);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaybdIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaybd is less than DEFAULT_NGAYBD
        defaultDeTaiShouldNotBeFound("ngaybd.lessThan=" + DEFAULT_NGAYBD);

        // Get all the deTaiList where ngaybd is less than UPDATED_NGAYBD
        defaultDeTaiShouldBeFound("ngaybd.lessThan=" + UPDATED_NGAYBD);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaybdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaybd is greater than DEFAULT_NGAYBD
        defaultDeTaiShouldNotBeFound("ngaybd.greaterThan=" + DEFAULT_NGAYBD);

        // Get all the deTaiList where ngaybd is greater than SMALLER_NGAYBD
        defaultDeTaiShouldBeFound("ngaybd.greaterThan=" + SMALLER_NGAYBD);
    }


    @Test
    @Transactional
    public void getAllDeTaisByNgayktIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaykt equals to DEFAULT_NGAYKT
        defaultDeTaiShouldBeFound("ngaykt.equals=" + DEFAULT_NGAYKT);

        // Get all the deTaiList where ngaykt equals to UPDATED_NGAYKT
        defaultDeTaiShouldNotBeFound("ngaykt.equals=" + UPDATED_NGAYKT);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayktIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaykt not equals to DEFAULT_NGAYKT
        defaultDeTaiShouldNotBeFound("ngaykt.notEquals=" + DEFAULT_NGAYKT);

        // Get all the deTaiList where ngaykt not equals to UPDATED_NGAYKT
        defaultDeTaiShouldBeFound("ngaykt.notEquals=" + UPDATED_NGAYKT);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayktIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaykt in DEFAULT_NGAYKT or UPDATED_NGAYKT
        defaultDeTaiShouldBeFound("ngaykt.in=" + DEFAULT_NGAYKT + "," + UPDATED_NGAYKT);

        // Get all the deTaiList where ngaykt equals to UPDATED_NGAYKT
        defaultDeTaiShouldNotBeFound("ngaykt.in=" + UPDATED_NGAYKT);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayktIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaykt is not null
        defaultDeTaiShouldBeFound("ngaykt.specified=true");

        // Get all the deTaiList where ngaykt is null
        defaultDeTaiShouldNotBeFound("ngaykt.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayktIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaykt is greater than or equal to DEFAULT_NGAYKT
        defaultDeTaiShouldBeFound("ngaykt.greaterThanOrEqual=" + DEFAULT_NGAYKT);

        // Get all the deTaiList where ngaykt is greater than or equal to UPDATED_NGAYKT
        defaultDeTaiShouldNotBeFound("ngaykt.greaterThanOrEqual=" + UPDATED_NGAYKT);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayktIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaykt is less than or equal to DEFAULT_NGAYKT
        defaultDeTaiShouldBeFound("ngaykt.lessThanOrEqual=" + DEFAULT_NGAYKT);

        // Get all the deTaiList where ngaykt is less than or equal to SMALLER_NGAYKT
        defaultDeTaiShouldNotBeFound("ngaykt.lessThanOrEqual=" + SMALLER_NGAYKT);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayktIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaykt is less than DEFAULT_NGAYKT
        defaultDeTaiShouldNotBeFound("ngaykt.lessThan=" + DEFAULT_NGAYKT);

        // Get all the deTaiList where ngaykt is less than UPDATED_NGAYKT
        defaultDeTaiShouldBeFound("ngaykt.lessThan=" + UPDATED_NGAYKT);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayktIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaykt is greater than DEFAULT_NGAYKT
        defaultDeTaiShouldNotBeFound("ngaykt.greaterThan=" + DEFAULT_NGAYKT);

        // Get all the deTaiList where ngaykt is greater than SMALLER_NGAYKT
        defaultDeTaiShouldBeFound("ngaykt.greaterThan=" + SMALLER_NGAYKT);
    }


    @Test
    @Transactional
    public void getAllDeTaisByHientrangIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where hientrang equals to DEFAULT_HIENTRANG
        defaultDeTaiShouldBeFound("hientrang.equals=" + DEFAULT_HIENTRANG);

        // Get all the deTaiList where hientrang equals to UPDATED_HIENTRANG
        defaultDeTaiShouldNotBeFound("hientrang.equals=" + UPDATED_HIENTRANG);
    }

    @Test
    @Transactional
    public void getAllDeTaisByHientrangIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where hientrang not equals to DEFAULT_HIENTRANG
        defaultDeTaiShouldNotBeFound("hientrang.notEquals=" + DEFAULT_HIENTRANG);

        // Get all the deTaiList where hientrang not equals to UPDATED_HIENTRANG
        defaultDeTaiShouldBeFound("hientrang.notEquals=" + UPDATED_HIENTRANG);
    }

    @Test
    @Transactional
    public void getAllDeTaisByHientrangIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where hientrang in DEFAULT_HIENTRANG or UPDATED_HIENTRANG
        defaultDeTaiShouldBeFound("hientrang.in=" + DEFAULT_HIENTRANG + "," + UPDATED_HIENTRANG);

        // Get all the deTaiList where hientrang equals to UPDATED_HIENTRANG
        defaultDeTaiShouldNotBeFound("hientrang.in=" + UPDATED_HIENTRANG);
    }

    @Test
    @Transactional
    public void getAllDeTaisByHientrangIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where hientrang is not null
        defaultDeTaiShouldBeFound("hientrang.specified=true");

        // Get all the deTaiList where hientrang is null
        defaultDeTaiShouldNotBeFound("hientrang.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByHientrangIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where hientrang is greater than or equal to DEFAULT_HIENTRANG
        defaultDeTaiShouldBeFound("hientrang.greaterThanOrEqual=" + DEFAULT_HIENTRANG);

        // Get all the deTaiList where hientrang is greater than or equal to UPDATED_HIENTRANG
        defaultDeTaiShouldNotBeFound("hientrang.greaterThanOrEqual=" + UPDATED_HIENTRANG);
    }

    @Test
    @Transactional
    public void getAllDeTaisByHientrangIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where hientrang is less than or equal to DEFAULT_HIENTRANG
        defaultDeTaiShouldBeFound("hientrang.lessThanOrEqual=" + DEFAULT_HIENTRANG);

        // Get all the deTaiList where hientrang is less than or equal to SMALLER_HIENTRANG
        defaultDeTaiShouldNotBeFound("hientrang.lessThanOrEqual=" + SMALLER_HIENTRANG);
    }

    @Test
    @Transactional
    public void getAllDeTaisByHientrangIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where hientrang is less than DEFAULT_HIENTRANG
        defaultDeTaiShouldNotBeFound("hientrang.lessThan=" + DEFAULT_HIENTRANG);

        // Get all the deTaiList where hientrang is less than UPDATED_HIENTRANG
        defaultDeTaiShouldBeFound("hientrang.lessThan=" + UPDATED_HIENTRANG);
    }

    @Test
    @Transactional
    public void getAllDeTaisByHientrangIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where hientrang is greater than DEFAULT_HIENTRANG
        defaultDeTaiShouldNotBeFound("hientrang.greaterThan=" + DEFAULT_HIENTRANG);

        // Get all the deTaiList where hientrang is greater than SMALLER_HIENTRANG
        defaultDeTaiShouldBeFound("hientrang.greaterThan=" + SMALLER_HIENTRANG);
    }


    @Test
    @Transactional
    public void getAllDeTaisByXuatbanIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where xuatban equals to DEFAULT_XUATBAN
        defaultDeTaiShouldBeFound("xuatban.equals=" + DEFAULT_XUATBAN);

        // Get all the deTaiList where xuatban equals to UPDATED_XUATBAN
        defaultDeTaiShouldNotBeFound("xuatban.equals=" + UPDATED_XUATBAN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByXuatbanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where xuatban not equals to DEFAULT_XUATBAN
        defaultDeTaiShouldNotBeFound("xuatban.notEquals=" + DEFAULT_XUATBAN);

        // Get all the deTaiList where xuatban not equals to UPDATED_XUATBAN
        defaultDeTaiShouldBeFound("xuatban.notEquals=" + UPDATED_XUATBAN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByXuatbanIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where xuatban in DEFAULT_XUATBAN or UPDATED_XUATBAN
        defaultDeTaiShouldBeFound("xuatban.in=" + DEFAULT_XUATBAN + "," + UPDATED_XUATBAN);

        // Get all the deTaiList where xuatban equals to UPDATED_XUATBAN
        defaultDeTaiShouldNotBeFound("xuatban.in=" + UPDATED_XUATBAN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByXuatbanIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where xuatban is not null
        defaultDeTaiShouldBeFound("xuatban.specified=true");

        // Get all the deTaiList where xuatban is null
        defaultDeTaiShouldNotBeFound("xuatban.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByXuatbanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where xuatban is greater than or equal to DEFAULT_XUATBAN
        defaultDeTaiShouldBeFound("xuatban.greaterThanOrEqual=" + DEFAULT_XUATBAN);

        // Get all the deTaiList where xuatban is greater than or equal to UPDATED_XUATBAN
        defaultDeTaiShouldNotBeFound("xuatban.greaterThanOrEqual=" + UPDATED_XUATBAN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByXuatbanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where xuatban is less than or equal to DEFAULT_XUATBAN
        defaultDeTaiShouldBeFound("xuatban.lessThanOrEqual=" + DEFAULT_XUATBAN);

        // Get all the deTaiList where xuatban is less than or equal to SMALLER_XUATBAN
        defaultDeTaiShouldNotBeFound("xuatban.lessThanOrEqual=" + SMALLER_XUATBAN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByXuatbanIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where xuatban is less than DEFAULT_XUATBAN
        defaultDeTaiShouldNotBeFound("xuatban.lessThan=" + DEFAULT_XUATBAN);

        // Get all the deTaiList where xuatban is less than UPDATED_XUATBAN
        defaultDeTaiShouldBeFound("xuatban.lessThan=" + UPDATED_XUATBAN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByXuatbanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where xuatban is greater than DEFAULT_XUATBAN
        defaultDeTaiShouldNotBeFound("xuatban.greaterThan=" + DEFAULT_XUATBAN);

        // Get all the deTaiList where xuatban is greater than SMALLER_XUATBAN
        defaultDeTaiShouldBeFound("xuatban.greaterThan=" + SMALLER_XUATBAN);
    }


    @Test
    @Transactional
    public void getAllDeTaisByCapquanlyIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where capquanly equals to DEFAULT_CAPQUANLY
        defaultDeTaiShouldBeFound("capquanly.equals=" + DEFAULT_CAPQUANLY);

        // Get all the deTaiList where capquanly equals to UPDATED_CAPQUANLY
        defaultDeTaiShouldNotBeFound("capquanly.equals=" + UPDATED_CAPQUANLY);
    }

    @Test
    @Transactional
    public void getAllDeTaisByCapquanlyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where capquanly not equals to DEFAULT_CAPQUANLY
        defaultDeTaiShouldNotBeFound("capquanly.notEquals=" + DEFAULT_CAPQUANLY);

        // Get all the deTaiList where capquanly not equals to UPDATED_CAPQUANLY
        defaultDeTaiShouldBeFound("capquanly.notEquals=" + UPDATED_CAPQUANLY);
    }

    @Test
    @Transactional
    public void getAllDeTaisByCapquanlyIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where capquanly in DEFAULT_CAPQUANLY or UPDATED_CAPQUANLY
        defaultDeTaiShouldBeFound("capquanly.in=" + DEFAULT_CAPQUANLY + "," + UPDATED_CAPQUANLY);

        // Get all the deTaiList where capquanly equals to UPDATED_CAPQUANLY
        defaultDeTaiShouldNotBeFound("capquanly.in=" + UPDATED_CAPQUANLY);
    }

    @Test
    @Transactional
    public void getAllDeTaisByCapquanlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where capquanly is not null
        defaultDeTaiShouldBeFound("capquanly.specified=true");

        // Get all the deTaiList where capquanly is null
        defaultDeTaiShouldNotBeFound("capquanly.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByCapquanlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where capquanly is greater than or equal to DEFAULT_CAPQUANLY
        defaultDeTaiShouldBeFound("capquanly.greaterThanOrEqual=" + DEFAULT_CAPQUANLY);

        // Get all the deTaiList where capquanly is greater than or equal to UPDATED_CAPQUANLY
        defaultDeTaiShouldNotBeFound("capquanly.greaterThanOrEqual=" + UPDATED_CAPQUANLY);
    }

    @Test
    @Transactional
    public void getAllDeTaisByCapquanlyIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where capquanly is less than or equal to DEFAULT_CAPQUANLY
        defaultDeTaiShouldBeFound("capquanly.lessThanOrEqual=" + DEFAULT_CAPQUANLY);

        // Get all the deTaiList where capquanly is less than or equal to SMALLER_CAPQUANLY
        defaultDeTaiShouldNotBeFound("capquanly.lessThanOrEqual=" + SMALLER_CAPQUANLY);
    }

    @Test
    @Transactional
    public void getAllDeTaisByCapquanlyIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where capquanly is less than DEFAULT_CAPQUANLY
        defaultDeTaiShouldNotBeFound("capquanly.lessThan=" + DEFAULT_CAPQUANLY);

        // Get all the deTaiList where capquanly is less than UPDATED_CAPQUANLY
        defaultDeTaiShouldBeFound("capquanly.lessThan=" + UPDATED_CAPQUANLY);
    }

    @Test
    @Transactional
    public void getAllDeTaisByCapquanlyIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where capquanly is greater than DEFAULT_CAPQUANLY
        defaultDeTaiShouldNotBeFound("capquanly.greaterThan=" + DEFAULT_CAPQUANLY);

        // Get all the deTaiList where capquanly is greater than SMALLER_CAPQUANLY
        defaultDeTaiShouldBeFound("capquanly.greaterThan=" + SMALLER_CAPQUANLY);
    }


    @Test
    @Transactional
    public void getAllDeTaisByNgaynghiemthuIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaynghiemthu equals to DEFAULT_NGAYNGHIEMTHU
        defaultDeTaiShouldBeFound("ngaynghiemthu.equals=" + DEFAULT_NGAYNGHIEMTHU);

        // Get all the deTaiList where ngaynghiemthu equals to UPDATED_NGAYNGHIEMTHU
        defaultDeTaiShouldNotBeFound("ngaynghiemthu.equals=" + UPDATED_NGAYNGHIEMTHU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaynghiemthuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaynghiemthu not equals to DEFAULT_NGAYNGHIEMTHU
        defaultDeTaiShouldNotBeFound("ngaynghiemthu.notEquals=" + DEFAULT_NGAYNGHIEMTHU);

        // Get all the deTaiList where ngaynghiemthu not equals to UPDATED_NGAYNGHIEMTHU
        defaultDeTaiShouldBeFound("ngaynghiemthu.notEquals=" + UPDATED_NGAYNGHIEMTHU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaynghiemthuIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaynghiemthu in DEFAULT_NGAYNGHIEMTHU or UPDATED_NGAYNGHIEMTHU
        defaultDeTaiShouldBeFound("ngaynghiemthu.in=" + DEFAULT_NGAYNGHIEMTHU + "," + UPDATED_NGAYNGHIEMTHU);

        // Get all the deTaiList where ngaynghiemthu equals to UPDATED_NGAYNGHIEMTHU
        defaultDeTaiShouldNotBeFound("ngaynghiemthu.in=" + UPDATED_NGAYNGHIEMTHU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaynghiemthuIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaynghiemthu is not null
        defaultDeTaiShouldBeFound("ngaynghiemthu.specified=true");

        // Get all the deTaiList where ngaynghiemthu is null
        defaultDeTaiShouldNotBeFound("ngaynghiemthu.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaynghiemthuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaynghiemthu is greater than or equal to DEFAULT_NGAYNGHIEMTHU
        defaultDeTaiShouldBeFound("ngaynghiemthu.greaterThanOrEqual=" + DEFAULT_NGAYNGHIEMTHU);

        // Get all the deTaiList where ngaynghiemthu is greater than or equal to UPDATED_NGAYNGHIEMTHU
        defaultDeTaiShouldNotBeFound("ngaynghiemthu.greaterThanOrEqual=" + UPDATED_NGAYNGHIEMTHU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaynghiemthuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaynghiemthu is less than or equal to DEFAULT_NGAYNGHIEMTHU
        defaultDeTaiShouldBeFound("ngaynghiemthu.lessThanOrEqual=" + DEFAULT_NGAYNGHIEMTHU);

        // Get all the deTaiList where ngaynghiemthu is less than or equal to SMALLER_NGAYNGHIEMTHU
        defaultDeTaiShouldNotBeFound("ngaynghiemthu.lessThanOrEqual=" + SMALLER_NGAYNGHIEMTHU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaynghiemthuIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaynghiemthu is less than DEFAULT_NGAYNGHIEMTHU
        defaultDeTaiShouldNotBeFound("ngaynghiemthu.lessThan=" + DEFAULT_NGAYNGHIEMTHU);

        // Get all the deTaiList where ngaynghiemthu is less than UPDATED_NGAYNGHIEMTHU
        defaultDeTaiShouldBeFound("ngaynghiemthu.lessThan=" + UPDATED_NGAYNGHIEMTHU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgaynghiemthuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngaynghiemthu is greater than DEFAULT_NGAYNGHIEMTHU
        defaultDeTaiShouldNotBeFound("ngaynghiemthu.greaterThan=" + DEFAULT_NGAYNGHIEMTHU);

        // Get all the deTaiList where ngaynghiemthu is greater than SMALLER_NGAYNGHIEMTHU
        defaultDeTaiShouldBeFound("ngaynghiemthu.greaterThan=" + SMALLER_NGAYNGHIEMTHU);
    }


    @Test
    @Transactional
    public void getAllDeTaisByKinhphithuchienIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphithuchien equals to DEFAULT_KINHPHITHUCHIEN
        defaultDeTaiShouldBeFound("kinhphithuchien.equals=" + DEFAULT_KINHPHITHUCHIEN);

        // Get all the deTaiList where kinhphithuchien equals to UPDATED_KINHPHITHUCHIEN
        defaultDeTaiShouldNotBeFound("kinhphithuchien.equals=" + UPDATED_KINHPHITHUCHIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphithuchienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphithuchien not equals to DEFAULT_KINHPHITHUCHIEN
        defaultDeTaiShouldNotBeFound("kinhphithuchien.notEquals=" + DEFAULT_KINHPHITHUCHIEN);

        // Get all the deTaiList where kinhphithuchien not equals to UPDATED_KINHPHITHUCHIEN
        defaultDeTaiShouldBeFound("kinhphithuchien.notEquals=" + UPDATED_KINHPHITHUCHIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphithuchienIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphithuchien in DEFAULT_KINHPHITHUCHIEN or UPDATED_KINHPHITHUCHIEN
        defaultDeTaiShouldBeFound("kinhphithuchien.in=" + DEFAULT_KINHPHITHUCHIEN + "," + UPDATED_KINHPHITHUCHIEN);

        // Get all the deTaiList where kinhphithuchien equals to UPDATED_KINHPHITHUCHIEN
        defaultDeTaiShouldNotBeFound("kinhphithuchien.in=" + UPDATED_KINHPHITHUCHIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphithuchienIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphithuchien is not null
        defaultDeTaiShouldBeFound("kinhphithuchien.specified=true");

        // Get all the deTaiList where kinhphithuchien is null
        defaultDeTaiShouldNotBeFound("kinhphithuchien.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphithuchienIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphithuchien is greater than or equal to DEFAULT_KINHPHITHUCHIEN
        defaultDeTaiShouldBeFound("kinhphithuchien.greaterThanOrEqual=" + DEFAULT_KINHPHITHUCHIEN);

        // Get all the deTaiList where kinhphithuchien is greater than or equal to UPDATED_KINHPHITHUCHIEN
        defaultDeTaiShouldNotBeFound("kinhphithuchien.greaterThanOrEqual=" + UPDATED_KINHPHITHUCHIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphithuchienIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphithuchien is less than or equal to DEFAULT_KINHPHITHUCHIEN
        defaultDeTaiShouldBeFound("kinhphithuchien.lessThanOrEqual=" + DEFAULT_KINHPHITHUCHIEN);

        // Get all the deTaiList where kinhphithuchien is less than or equal to SMALLER_KINHPHITHUCHIEN
        defaultDeTaiShouldNotBeFound("kinhphithuchien.lessThanOrEqual=" + SMALLER_KINHPHITHUCHIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphithuchienIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphithuchien is less than DEFAULT_KINHPHITHUCHIEN
        defaultDeTaiShouldNotBeFound("kinhphithuchien.lessThan=" + DEFAULT_KINHPHITHUCHIEN);

        // Get all the deTaiList where kinhphithuchien is less than UPDATED_KINHPHITHUCHIEN
        defaultDeTaiShouldBeFound("kinhphithuchien.lessThan=" + UPDATED_KINHPHITHUCHIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphithuchienIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphithuchien is greater than DEFAULT_KINHPHITHUCHIEN
        defaultDeTaiShouldNotBeFound("kinhphithuchien.greaterThan=" + DEFAULT_KINHPHITHUCHIEN);

        // Get all the deTaiList where kinhphithuchien is greater than SMALLER_KINHPHITHUCHIEN
        defaultDeTaiShouldBeFound("kinhphithuchien.greaterThan=" + SMALLER_KINHPHITHUCHIEN);
    }


    @Test
    @Transactional
    public void getAllDeTaisByNguonkinhphiIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguonkinhphi equals to DEFAULT_NGUONKINHPHI
        defaultDeTaiShouldBeFound("nguonkinhphi.equals=" + DEFAULT_NGUONKINHPHI);

        // Get all the deTaiList where nguonkinhphi equals to UPDATED_NGUONKINHPHI
        defaultDeTaiShouldNotBeFound("nguonkinhphi.equals=" + UPDATED_NGUONKINHPHI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNguonkinhphiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguonkinhphi not equals to DEFAULT_NGUONKINHPHI
        defaultDeTaiShouldNotBeFound("nguonkinhphi.notEquals=" + DEFAULT_NGUONKINHPHI);

        // Get all the deTaiList where nguonkinhphi not equals to UPDATED_NGUONKINHPHI
        defaultDeTaiShouldBeFound("nguonkinhphi.notEquals=" + UPDATED_NGUONKINHPHI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNguonkinhphiIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguonkinhphi in DEFAULT_NGUONKINHPHI or UPDATED_NGUONKINHPHI
        defaultDeTaiShouldBeFound("nguonkinhphi.in=" + DEFAULT_NGUONKINHPHI + "," + UPDATED_NGUONKINHPHI);

        // Get all the deTaiList where nguonkinhphi equals to UPDATED_NGUONKINHPHI
        defaultDeTaiShouldNotBeFound("nguonkinhphi.in=" + UPDATED_NGUONKINHPHI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNguonkinhphiIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguonkinhphi is not null
        defaultDeTaiShouldBeFound("nguonkinhphi.specified=true");

        // Get all the deTaiList where nguonkinhphi is null
        defaultDeTaiShouldNotBeFound("nguonkinhphi.specified=false");
    }
                @Test
    @Transactional
    public void getAllDeTaisByNguonkinhphiContainsSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguonkinhphi contains DEFAULT_NGUONKINHPHI
        defaultDeTaiShouldBeFound("nguonkinhphi.contains=" + DEFAULT_NGUONKINHPHI);

        // Get all the deTaiList where nguonkinhphi contains UPDATED_NGUONKINHPHI
        defaultDeTaiShouldNotBeFound("nguonkinhphi.contains=" + UPDATED_NGUONKINHPHI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNguonkinhphiNotContainsSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguonkinhphi does not contain DEFAULT_NGUONKINHPHI
        defaultDeTaiShouldNotBeFound("nguonkinhphi.doesNotContain=" + DEFAULT_NGUONKINHPHI);

        // Get all the deTaiList where nguonkinhphi does not contain UPDATED_NGUONKINHPHI
        defaultDeTaiShouldBeFound("nguonkinhphi.doesNotContain=" + UPDATED_NGUONKINHPHI);
    }


    @Test
    @Transactional
    public void getAllDeTaisByMuctieuIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where muctieu equals to DEFAULT_MUCTIEU
        defaultDeTaiShouldBeFound("muctieu.equals=" + DEFAULT_MUCTIEU);

        // Get all the deTaiList where muctieu equals to UPDATED_MUCTIEU
        defaultDeTaiShouldNotBeFound("muctieu.equals=" + UPDATED_MUCTIEU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByMuctieuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where muctieu not equals to DEFAULT_MUCTIEU
        defaultDeTaiShouldNotBeFound("muctieu.notEquals=" + DEFAULT_MUCTIEU);

        // Get all the deTaiList where muctieu not equals to UPDATED_MUCTIEU
        defaultDeTaiShouldBeFound("muctieu.notEquals=" + UPDATED_MUCTIEU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByMuctieuIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where muctieu in DEFAULT_MUCTIEU or UPDATED_MUCTIEU
        defaultDeTaiShouldBeFound("muctieu.in=" + DEFAULT_MUCTIEU + "," + UPDATED_MUCTIEU);

        // Get all the deTaiList where muctieu equals to UPDATED_MUCTIEU
        defaultDeTaiShouldNotBeFound("muctieu.in=" + UPDATED_MUCTIEU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByMuctieuIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where muctieu is not null
        defaultDeTaiShouldBeFound("muctieu.specified=true");

        // Get all the deTaiList where muctieu is null
        defaultDeTaiShouldNotBeFound("muctieu.specified=false");
    }
                @Test
    @Transactional
    public void getAllDeTaisByMuctieuContainsSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where muctieu contains DEFAULT_MUCTIEU
        defaultDeTaiShouldBeFound("muctieu.contains=" + DEFAULT_MUCTIEU);

        // Get all the deTaiList where muctieu contains UPDATED_MUCTIEU
        defaultDeTaiShouldNotBeFound("muctieu.contains=" + UPDATED_MUCTIEU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByMuctieuNotContainsSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where muctieu does not contain DEFAULT_MUCTIEU
        defaultDeTaiShouldNotBeFound("muctieu.doesNotContain=" + DEFAULT_MUCTIEU);

        // Get all the deTaiList where muctieu does not contain UPDATED_MUCTIEU
        defaultDeTaiShouldBeFound("muctieu.doesNotContain=" + UPDATED_MUCTIEU);
    }


    @Test
    @Transactional
    public void getAllDeTaisByKinhphiDukienIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiDukien equals to DEFAULT_KINHPHI_DUKIEN
        defaultDeTaiShouldBeFound("kinhphiDukien.equals=" + DEFAULT_KINHPHI_DUKIEN);

        // Get all the deTaiList where kinhphiDukien equals to UPDATED_KINHPHI_DUKIEN
        defaultDeTaiShouldNotBeFound("kinhphiDukien.equals=" + UPDATED_KINHPHI_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiDukienIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiDukien not equals to DEFAULT_KINHPHI_DUKIEN
        defaultDeTaiShouldNotBeFound("kinhphiDukien.notEquals=" + DEFAULT_KINHPHI_DUKIEN);

        // Get all the deTaiList where kinhphiDukien not equals to UPDATED_KINHPHI_DUKIEN
        defaultDeTaiShouldBeFound("kinhphiDukien.notEquals=" + UPDATED_KINHPHI_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiDukienIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiDukien in DEFAULT_KINHPHI_DUKIEN or UPDATED_KINHPHI_DUKIEN
        defaultDeTaiShouldBeFound("kinhphiDukien.in=" + DEFAULT_KINHPHI_DUKIEN + "," + UPDATED_KINHPHI_DUKIEN);

        // Get all the deTaiList where kinhphiDukien equals to UPDATED_KINHPHI_DUKIEN
        defaultDeTaiShouldNotBeFound("kinhphiDukien.in=" + UPDATED_KINHPHI_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiDukienIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiDukien is not null
        defaultDeTaiShouldBeFound("kinhphiDukien.specified=true");

        // Get all the deTaiList where kinhphiDukien is null
        defaultDeTaiShouldNotBeFound("kinhphiDukien.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiDukienIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiDukien is greater than or equal to DEFAULT_KINHPHI_DUKIEN
        defaultDeTaiShouldBeFound("kinhphiDukien.greaterThanOrEqual=" + DEFAULT_KINHPHI_DUKIEN);

        // Get all the deTaiList where kinhphiDukien is greater than or equal to UPDATED_KINHPHI_DUKIEN
        defaultDeTaiShouldNotBeFound("kinhphiDukien.greaterThanOrEqual=" + UPDATED_KINHPHI_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiDukienIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiDukien is less than or equal to DEFAULT_KINHPHI_DUKIEN
        defaultDeTaiShouldBeFound("kinhphiDukien.lessThanOrEqual=" + DEFAULT_KINHPHI_DUKIEN);

        // Get all the deTaiList where kinhphiDukien is less than or equal to SMALLER_KINHPHI_DUKIEN
        defaultDeTaiShouldNotBeFound("kinhphiDukien.lessThanOrEqual=" + SMALLER_KINHPHI_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiDukienIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiDukien is less than DEFAULT_KINHPHI_DUKIEN
        defaultDeTaiShouldNotBeFound("kinhphiDukien.lessThan=" + DEFAULT_KINHPHI_DUKIEN);

        // Get all the deTaiList where kinhphiDukien is less than UPDATED_KINHPHI_DUKIEN
        defaultDeTaiShouldBeFound("kinhphiDukien.lessThan=" + UPDATED_KINHPHI_DUKIEN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiDukienIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiDukien is greater than DEFAULT_KINHPHI_DUKIEN
        defaultDeTaiShouldNotBeFound("kinhphiDukien.greaterThan=" + DEFAULT_KINHPHI_DUKIEN);

        // Get all the deTaiList where kinhphiDukien is greater than SMALLER_KINHPHI_DUKIEN
        defaultDeTaiShouldBeFound("kinhphiDukien.greaterThan=" + SMALLER_KINHPHI_DUKIEN);
    }


    @Test
    @Transactional
    public void getAllDeTaisByChunhiemdetaiIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where chunhiemdetai equals to DEFAULT_CHUNHIEMDETAI
        defaultDeTaiShouldBeFound("chunhiemdetai.equals=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the deTaiList where chunhiemdetai equals to UPDATED_CHUNHIEMDETAI
        defaultDeTaiShouldNotBeFound("chunhiemdetai.equals=" + UPDATED_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByChunhiemdetaiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where chunhiemdetai not equals to DEFAULT_CHUNHIEMDETAI
        defaultDeTaiShouldNotBeFound("chunhiemdetai.notEquals=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the deTaiList where chunhiemdetai not equals to UPDATED_CHUNHIEMDETAI
        defaultDeTaiShouldBeFound("chunhiemdetai.notEquals=" + UPDATED_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByChunhiemdetaiIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where chunhiemdetai in DEFAULT_CHUNHIEMDETAI or UPDATED_CHUNHIEMDETAI
        defaultDeTaiShouldBeFound("chunhiemdetai.in=" + DEFAULT_CHUNHIEMDETAI + "," + UPDATED_CHUNHIEMDETAI);

        // Get all the deTaiList where chunhiemdetai equals to UPDATED_CHUNHIEMDETAI
        defaultDeTaiShouldNotBeFound("chunhiemdetai.in=" + UPDATED_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByChunhiemdetaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where chunhiemdetai is not null
        defaultDeTaiShouldBeFound("chunhiemdetai.specified=true");

        // Get all the deTaiList where chunhiemdetai is null
        defaultDeTaiShouldNotBeFound("chunhiemdetai.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByChunhiemdetaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where chunhiemdetai is greater than or equal to DEFAULT_CHUNHIEMDETAI
        defaultDeTaiShouldBeFound("chunhiemdetai.greaterThanOrEqual=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the deTaiList where chunhiemdetai is greater than or equal to UPDATED_CHUNHIEMDETAI
        defaultDeTaiShouldNotBeFound("chunhiemdetai.greaterThanOrEqual=" + UPDATED_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByChunhiemdetaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where chunhiemdetai is less than or equal to DEFAULT_CHUNHIEMDETAI
        defaultDeTaiShouldBeFound("chunhiemdetai.lessThanOrEqual=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the deTaiList where chunhiemdetai is less than or equal to SMALLER_CHUNHIEMDETAI
        defaultDeTaiShouldNotBeFound("chunhiemdetai.lessThanOrEqual=" + SMALLER_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByChunhiemdetaiIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where chunhiemdetai is less than DEFAULT_CHUNHIEMDETAI
        defaultDeTaiShouldNotBeFound("chunhiemdetai.lessThan=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the deTaiList where chunhiemdetai is less than UPDATED_CHUNHIEMDETAI
        defaultDeTaiShouldBeFound("chunhiemdetai.lessThan=" + UPDATED_CHUNHIEMDETAI);
    }

    @Test
    @Transactional
    public void getAllDeTaisByChunhiemdetaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where chunhiemdetai is greater than DEFAULT_CHUNHIEMDETAI
        defaultDeTaiShouldNotBeFound("chunhiemdetai.greaterThan=" + DEFAULT_CHUNHIEMDETAI);

        // Get all the deTaiList where chunhiemdetai is greater than SMALLER_CHUNHIEMDETAI
        defaultDeTaiShouldBeFound("chunhiemdetai.greaterThan=" + SMALLER_CHUNHIEMDETAI);
    }


    @Test
    @Transactional
    public void getAllDeTaisByNoidungtongquanIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where noidungtongquan equals to DEFAULT_NOIDUNGTONGQUAN
        defaultDeTaiShouldBeFound("noidungtongquan.equals=" + DEFAULT_NOIDUNGTONGQUAN);

        // Get all the deTaiList where noidungtongquan equals to UPDATED_NOIDUNGTONGQUAN
        defaultDeTaiShouldNotBeFound("noidungtongquan.equals=" + UPDATED_NOIDUNGTONGQUAN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNoidungtongquanIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where noidungtongquan not equals to DEFAULT_NOIDUNGTONGQUAN
        defaultDeTaiShouldNotBeFound("noidungtongquan.notEquals=" + DEFAULT_NOIDUNGTONGQUAN);

        // Get all the deTaiList where noidungtongquan not equals to UPDATED_NOIDUNGTONGQUAN
        defaultDeTaiShouldBeFound("noidungtongquan.notEquals=" + UPDATED_NOIDUNGTONGQUAN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNoidungtongquanIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where noidungtongquan in DEFAULT_NOIDUNGTONGQUAN or UPDATED_NOIDUNGTONGQUAN
        defaultDeTaiShouldBeFound("noidungtongquan.in=" + DEFAULT_NOIDUNGTONGQUAN + "," + UPDATED_NOIDUNGTONGQUAN);

        // Get all the deTaiList where noidungtongquan equals to UPDATED_NOIDUNGTONGQUAN
        defaultDeTaiShouldNotBeFound("noidungtongquan.in=" + UPDATED_NOIDUNGTONGQUAN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNoidungtongquanIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where noidungtongquan is not null
        defaultDeTaiShouldBeFound("noidungtongquan.specified=true");

        // Get all the deTaiList where noidungtongquan is null
        defaultDeTaiShouldNotBeFound("noidungtongquan.specified=false");
    }
                @Test
    @Transactional
    public void getAllDeTaisByNoidungtongquanContainsSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where noidungtongquan contains DEFAULT_NOIDUNGTONGQUAN
        defaultDeTaiShouldBeFound("noidungtongquan.contains=" + DEFAULT_NOIDUNGTONGQUAN);

        // Get all the deTaiList where noidungtongquan contains UPDATED_NOIDUNGTONGQUAN
        defaultDeTaiShouldNotBeFound("noidungtongquan.contains=" + UPDATED_NOIDUNGTONGQUAN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNoidungtongquanNotContainsSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where noidungtongquan does not contain DEFAULT_NOIDUNGTONGQUAN
        defaultDeTaiShouldNotBeFound("noidungtongquan.doesNotContain=" + DEFAULT_NOIDUNGTONGQUAN);

        // Get all the deTaiList where noidungtongquan does not contain UPDATED_NOIDUNGTONGQUAN
        defaultDeTaiShouldBeFound("noidungtongquan.doesNotContain=" + UPDATED_NOIDUNGTONGQUAN);
    }


    @Test
    @Transactional
    public void getAllDeTaisByKinhphiHoanthanhIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiHoanthanh equals to DEFAULT_KINHPHI_HOANTHANH
        defaultDeTaiShouldBeFound("kinhphiHoanthanh.equals=" + DEFAULT_KINHPHI_HOANTHANH);

        // Get all the deTaiList where kinhphiHoanthanh equals to UPDATED_KINHPHI_HOANTHANH
        defaultDeTaiShouldNotBeFound("kinhphiHoanthanh.equals=" + UPDATED_KINHPHI_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiHoanthanhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiHoanthanh not equals to DEFAULT_KINHPHI_HOANTHANH
        defaultDeTaiShouldNotBeFound("kinhphiHoanthanh.notEquals=" + DEFAULT_KINHPHI_HOANTHANH);

        // Get all the deTaiList where kinhphiHoanthanh not equals to UPDATED_KINHPHI_HOANTHANH
        defaultDeTaiShouldBeFound("kinhphiHoanthanh.notEquals=" + UPDATED_KINHPHI_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiHoanthanhIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiHoanthanh in DEFAULT_KINHPHI_HOANTHANH or UPDATED_KINHPHI_HOANTHANH
        defaultDeTaiShouldBeFound("kinhphiHoanthanh.in=" + DEFAULT_KINHPHI_HOANTHANH + "," + UPDATED_KINHPHI_HOANTHANH);

        // Get all the deTaiList where kinhphiHoanthanh equals to UPDATED_KINHPHI_HOANTHANH
        defaultDeTaiShouldNotBeFound("kinhphiHoanthanh.in=" + UPDATED_KINHPHI_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiHoanthanhIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiHoanthanh is not null
        defaultDeTaiShouldBeFound("kinhphiHoanthanh.specified=true");

        // Get all the deTaiList where kinhphiHoanthanh is null
        defaultDeTaiShouldNotBeFound("kinhphiHoanthanh.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiHoanthanhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiHoanthanh is greater than or equal to DEFAULT_KINHPHI_HOANTHANH
        defaultDeTaiShouldBeFound("kinhphiHoanthanh.greaterThanOrEqual=" + DEFAULT_KINHPHI_HOANTHANH);

        // Get all the deTaiList where kinhphiHoanthanh is greater than or equal to UPDATED_KINHPHI_HOANTHANH
        defaultDeTaiShouldNotBeFound("kinhphiHoanthanh.greaterThanOrEqual=" + UPDATED_KINHPHI_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiHoanthanhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiHoanthanh is less than or equal to DEFAULT_KINHPHI_HOANTHANH
        defaultDeTaiShouldBeFound("kinhphiHoanthanh.lessThanOrEqual=" + DEFAULT_KINHPHI_HOANTHANH);

        // Get all the deTaiList where kinhphiHoanthanh is less than or equal to SMALLER_KINHPHI_HOANTHANH
        defaultDeTaiShouldNotBeFound("kinhphiHoanthanh.lessThanOrEqual=" + SMALLER_KINHPHI_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiHoanthanhIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiHoanthanh is less than DEFAULT_KINHPHI_HOANTHANH
        defaultDeTaiShouldNotBeFound("kinhphiHoanthanh.lessThan=" + DEFAULT_KINHPHI_HOANTHANH);

        // Get all the deTaiList where kinhphiHoanthanh is less than UPDATED_KINHPHI_HOANTHANH
        defaultDeTaiShouldBeFound("kinhphiHoanthanh.lessThan=" + UPDATED_KINHPHI_HOANTHANH);
    }

    @Test
    @Transactional
    public void getAllDeTaisByKinhphiHoanthanhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where kinhphiHoanthanh is greater than DEFAULT_KINHPHI_HOANTHANH
        defaultDeTaiShouldNotBeFound("kinhphiHoanthanh.greaterThan=" + DEFAULT_KINHPHI_HOANTHANH);

        // Get all the deTaiList where kinhphiHoanthanh is greater than SMALLER_KINHPHI_HOANTHANH
        defaultDeTaiShouldBeFound("kinhphiHoanthanh.greaterThan=" + SMALLER_KINHPHI_HOANTHANH);
    }


    @Test
    @Transactional
    public void getAllDeTaisByTongdiemIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tongdiem equals to DEFAULT_TONGDIEM
        defaultDeTaiShouldBeFound("tongdiem.equals=" + DEFAULT_TONGDIEM);

        // Get all the deTaiList where tongdiem equals to UPDATED_TONGDIEM
        defaultDeTaiShouldNotBeFound("tongdiem.equals=" + UPDATED_TONGDIEM);
    }

    @Test
    @Transactional
    public void getAllDeTaisByTongdiemIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tongdiem not equals to DEFAULT_TONGDIEM
        defaultDeTaiShouldNotBeFound("tongdiem.notEquals=" + DEFAULT_TONGDIEM);

        // Get all the deTaiList where tongdiem not equals to UPDATED_TONGDIEM
        defaultDeTaiShouldBeFound("tongdiem.notEquals=" + UPDATED_TONGDIEM);
    }

    @Test
    @Transactional
    public void getAllDeTaisByTongdiemIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tongdiem in DEFAULT_TONGDIEM or UPDATED_TONGDIEM
        defaultDeTaiShouldBeFound("tongdiem.in=" + DEFAULT_TONGDIEM + "," + UPDATED_TONGDIEM);

        // Get all the deTaiList where tongdiem equals to UPDATED_TONGDIEM
        defaultDeTaiShouldNotBeFound("tongdiem.in=" + UPDATED_TONGDIEM);
    }

    @Test
    @Transactional
    public void getAllDeTaisByTongdiemIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tongdiem is not null
        defaultDeTaiShouldBeFound("tongdiem.specified=true");

        // Get all the deTaiList where tongdiem is null
        defaultDeTaiShouldNotBeFound("tongdiem.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByTongdiemIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tongdiem is greater than or equal to DEFAULT_TONGDIEM
        defaultDeTaiShouldBeFound("tongdiem.greaterThanOrEqual=" + DEFAULT_TONGDIEM);

        // Get all the deTaiList where tongdiem is greater than or equal to UPDATED_TONGDIEM
        defaultDeTaiShouldNotBeFound("tongdiem.greaterThanOrEqual=" + UPDATED_TONGDIEM);
    }

    @Test
    @Transactional
    public void getAllDeTaisByTongdiemIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tongdiem is less than or equal to DEFAULT_TONGDIEM
        defaultDeTaiShouldBeFound("tongdiem.lessThanOrEqual=" + DEFAULT_TONGDIEM);

        // Get all the deTaiList where tongdiem is less than or equal to SMALLER_TONGDIEM
        defaultDeTaiShouldNotBeFound("tongdiem.lessThanOrEqual=" + SMALLER_TONGDIEM);
    }

    @Test
    @Transactional
    public void getAllDeTaisByTongdiemIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tongdiem is less than DEFAULT_TONGDIEM
        defaultDeTaiShouldNotBeFound("tongdiem.lessThan=" + DEFAULT_TONGDIEM);

        // Get all the deTaiList where tongdiem is less than UPDATED_TONGDIEM
        defaultDeTaiShouldBeFound("tongdiem.lessThan=" + UPDATED_TONGDIEM);
    }

    @Test
    @Transactional
    public void getAllDeTaisByTongdiemIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where tongdiem is greater than DEFAULT_TONGDIEM
        defaultDeTaiShouldNotBeFound("tongdiem.greaterThan=" + DEFAULT_TONGDIEM);

        // Get all the deTaiList where tongdiem is greater than SMALLER_TONGDIEM
        defaultDeTaiShouldBeFound("tongdiem.greaterThan=" + SMALLER_TONGDIEM);
    }


    @Test
    @Transactional
    public void getAllDeTaisByGhichuIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ghichu equals to DEFAULT_GHICHU
        defaultDeTaiShouldBeFound("ghichu.equals=" + DEFAULT_GHICHU);

        // Get all the deTaiList where ghichu equals to UPDATED_GHICHU
        defaultDeTaiShouldNotBeFound("ghichu.equals=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByGhichuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ghichu not equals to DEFAULT_GHICHU
        defaultDeTaiShouldNotBeFound("ghichu.notEquals=" + DEFAULT_GHICHU);

        // Get all the deTaiList where ghichu not equals to UPDATED_GHICHU
        defaultDeTaiShouldBeFound("ghichu.notEquals=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByGhichuIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ghichu in DEFAULT_GHICHU or UPDATED_GHICHU
        defaultDeTaiShouldBeFound("ghichu.in=" + DEFAULT_GHICHU + "," + UPDATED_GHICHU);

        // Get all the deTaiList where ghichu equals to UPDATED_GHICHU
        defaultDeTaiShouldNotBeFound("ghichu.in=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByGhichuIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ghichu is not null
        defaultDeTaiShouldBeFound("ghichu.specified=true");

        // Get all the deTaiList where ghichu is null
        defaultDeTaiShouldNotBeFound("ghichu.specified=false");
    }
                @Test
    @Transactional
    public void getAllDeTaisByGhichuContainsSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ghichu contains DEFAULT_GHICHU
        defaultDeTaiShouldBeFound("ghichu.contains=" + DEFAULT_GHICHU);

        // Get all the deTaiList where ghichu contains UPDATED_GHICHU
        defaultDeTaiShouldNotBeFound("ghichu.contains=" + UPDATED_GHICHU);
    }

    @Test
    @Transactional
    public void getAllDeTaisByGhichuNotContainsSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ghichu does not contain DEFAULT_GHICHU
        defaultDeTaiShouldNotBeFound("ghichu.doesNotContain=" + DEFAULT_GHICHU);

        // Get all the deTaiList where ghichu does not contain UPDATED_GHICHU
        defaultDeTaiShouldBeFound("ghichu.doesNotContain=" + UPDATED_GHICHU);
    }


    @Test
    @Transactional
    public void getAllDeTaisByNguoiCnIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguoiCn equals to DEFAULT_NGUOI_CN
        defaultDeTaiShouldBeFound("nguoiCn.equals=" + DEFAULT_NGUOI_CN);

        // Get all the deTaiList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultDeTaiShouldNotBeFound("nguoiCn.equals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNguoiCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguoiCn not equals to DEFAULT_NGUOI_CN
        defaultDeTaiShouldNotBeFound("nguoiCn.notEquals=" + DEFAULT_NGUOI_CN);

        // Get all the deTaiList where nguoiCn not equals to UPDATED_NGUOI_CN
        defaultDeTaiShouldBeFound("nguoiCn.notEquals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNguoiCnIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguoiCn in DEFAULT_NGUOI_CN or UPDATED_NGUOI_CN
        defaultDeTaiShouldBeFound("nguoiCn.in=" + DEFAULT_NGUOI_CN + "," + UPDATED_NGUOI_CN);

        // Get all the deTaiList where nguoiCn equals to UPDATED_NGUOI_CN
        defaultDeTaiShouldNotBeFound("nguoiCn.in=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNguoiCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguoiCn is not null
        defaultDeTaiShouldBeFound("nguoiCn.specified=true");

        // Get all the deTaiList where nguoiCn is null
        defaultDeTaiShouldNotBeFound("nguoiCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByNguoiCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguoiCn is greater than or equal to DEFAULT_NGUOI_CN
        defaultDeTaiShouldBeFound("nguoiCn.greaterThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the deTaiList where nguoiCn is greater than or equal to UPDATED_NGUOI_CN
        defaultDeTaiShouldNotBeFound("nguoiCn.greaterThanOrEqual=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNguoiCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguoiCn is less than or equal to DEFAULT_NGUOI_CN
        defaultDeTaiShouldBeFound("nguoiCn.lessThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the deTaiList where nguoiCn is less than or equal to SMALLER_NGUOI_CN
        defaultDeTaiShouldNotBeFound("nguoiCn.lessThanOrEqual=" + SMALLER_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNguoiCnIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguoiCn is less than DEFAULT_NGUOI_CN
        defaultDeTaiShouldNotBeFound("nguoiCn.lessThan=" + DEFAULT_NGUOI_CN);

        // Get all the deTaiList where nguoiCn is less than UPDATED_NGUOI_CN
        defaultDeTaiShouldBeFound("nguoiCn.lessThan=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNguoiCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where nguoiCn is greater than DEFAULT_NGUOI_CN
        defaultDeTaiShouldNotBeFound("nguoiCn.greaterThan=" + DEFAULT_NGUOI_CN);

        // Get all the deTaiList where nguoiCn is greater than SMALLER_NGUOI_CN
        defaultDeTaiShouldBeFound("nguoiCn.greaterThan=" + SMALLER_NGUOI_CN);
    }


    @Test
    @Transactional
    public void getAllDeTaisByNgayCnIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngayCn equals to DEFAULT_NGAY_CN
        defaultDeTaiShouldBeFound("ngayCn.equals=" + DEFAULT_NGAY_CN);

        // Get all the deTaiList where ngayCn equals to UPDATED_NGAY_CN
        defaultDeTaiShouldNotBeFound("ngayCn.equals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayCnIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngayCn not equals to DEFAULT_NGAY_CN
        defaultDeTaiShouldNotBeFound("ngayCn.notEquals=" + DEFAULT_NGAY_CN);

        // Get all the deTaiList where ngayCn not equals to UPDATED_NGAY_CN
        defaultDeTaiShouldBeFound("ngayCn.notEquals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayCnIsInShouldWork() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngayCn in DEFAULT_NGAY_CN or UPDATED_NGAY_CN
        defaultDeTaiShouldBeFound("ngayCn.in=" + DEFAULT_NGAY_CN + "," + UPDATED_NGAY_CN);

        // Get all the deTaiList where ngayCn equals to UPDATED_NGAY_CN
        defaultDeTaiShouldNotBeFound("ngayCn.in=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayCnIsNullOrNotNull() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngayCn is not null
        defaultDeTaiShouldBeFound("ngayCn.specified=true");

        // Get all the deTaiList where ngayCn is null
        defaultDeTaiShouldNotBeFound("ngayCn.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayCnIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngayCn is greater than or equal to DEFAULT_NGAY_CN
        defaultDeTaiShouldBeFound("ngayCn.greaterThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the deTaiList where ngayCn is greater than or equal to UPDATED_NGAY_CN
        defaultDeTaiShouldNotBeFound("ngayCn.greaterThanOrEqual=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayCnIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngayCn is less than or equal to DEFAULT_NGAY_CN
        defaultDeTaiShouldBeFound("ngayCn.lessThanOrEqual=" + DEFAULT_NGAY_CN);

        // Get all the deTaiList where ngayCn is less than or equal to SMALLER_NGAY_CN
        defaultDeTaiShouldNotBeFound("ngayCn.lessThanOrEqual=" + SMALLER_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayCnIsLessThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngayCn is less than DEFAULT_NGAY_CN
        defaultDeTaiShouldNotBeFound("ngayCn.lessThan=" + DEFAULT_NGAY_CN);

        // Get all the deTaiList where ngayCn is less than UPDATED_NGAY_CN
        defaultDeTaiShouldBeFound("ngayCn.lessThan=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllDeTaisByNgayCnIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList where ngayCn is greater than DEFAULT_NGAY_CN
        defaultDeTaiShouldNotBeFound("ngayCn.greaterThan=" + DEFAULT_NGAY_CN);

        // Get all the deTaiList where ngayCn is greater than SMALLER_NGAY_CN
        defaultDeTaiShouldBeFound("ngayCn.greaterThan=" + SMALLER_NGAY_CN);
    }


    @Test
    @Transactional
    public void getAllDeTaisByUpFileIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);
        UpFile upFile = UpFileResourceIT.createEntity(em);
        em.persist(upFile);
        em.flush();
        deTai.addUpFile(upFile);
        deTaiRepository.saveAndFlush(deTai);
        Long upFileId = upFile.getId();

        // Get all the deTaiList where upFile equals to upFileId
        defaultDeTaiShouldBeFound("upFileId.equals=" + upFileId);

        // Get all the deTaiList where upFile equals to upFileId + 1
        defaultDeTaiShouldNotBeFound("upFileId.equals=" + (upFileId + 1));
    }


    @Test
    @Transactional
    public void getAllDeTaisByTienDoIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);
        TienDo tienDo = TienDoResourceIT.createEntity(em);
        em.persist(tienDo);
        em.flush();
        deTai.addTienDo(tienDo);
        deTaiRepository.saveAndFlush(deTai);
        Long tienDoId = tienDo.getId();

        // Get all the deTaiList where tienDo equals to tienDoId
        defaultDeTaiShouldBeFound("tienDoId.equals=" + tienDoId);

        // Get all the deTaiList where tienDo equals to tienDoId + 1
        defaultDeTaiShouldNotBeFound("tienDoId.equals=" + (tienDoId + 1));
    }


    @Test
    @Transactional
    public void getAllDeTaisByNhanSuIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);
        NhanSu nhanSu = NhanSuResourceIT.createEntity(em);
        em.persist(nhanSu);
        em.flush();
        deTai.addNhanSu(nhanSu);
        deTaiRepository.saveAndFlush(deTai);
        Long nhanSuId = nhanSu.getId();

        // Get all the deTaiList where nhanSu equals to nhanSuId
        defaultDeTaiShouldBeFound("nhanSuId.equals=" + nhanSuId);

        // Get all the deTaiList where nhanSu equals to nhanSuId + 1
        defaultDeTaiShouldNotBeFound("nhanSuId.equals=" + (nhanSuId + 1));
    }


    @Test
    @Transactional
    public void getAllDeTaisByDuToanIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);
        DuToan duToan = DuToanResourceIT.createEntity(em);
        em.persist(duToan);
        em.flush();
        deTai.addDuToan(duToan);
        deTaiRepository.saveAndFlush(deTai);
        Long duToanId = duToan.getId();

        // Get all the deTaiList where duToan equals to duToanId
        defaultDeTaiShouldBeFound("duToanId.equals=" + duToanId);

        // Get all the deTaiList where duToan equals to duToanId + 1
        defaultDeTaiShouldNotBeFound("duToanId.equals=" + (duToanId + 1));
    }


    @Test
    @Transactional
    public void getAllDeTaisByDanhGiaIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);
        DanhGia danhGia = DanhGiaResourceIT.createEntity(em);
        em.persist(danhGia);
        em.flush();
        deTai.addDanhGia(danhGia);
        deTaiRepository.saveAndFlush(deTai);
        Long danhGiaId = danhGia.getId();

        // Get all the deTaiList where danhGia equals to danhGiaId
        defaultDeTaiShouldBeFound("danhGiaId.equals=" + danhGiaId);

        // Get all the deTaiList where danhGia equals to danhGiaId + 1
        defaultDeTaiShouldNotBeFound("danhGiaId.equals=" + (danhGiaId + 1));
    }


    @Test
    @Transactional
    public void getAllDeTaisByChuyenMucIsEqualToSomething() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);
        ChuyenMuc chuyenMuc = ChuyenMucResourceIT.createEntity(em);
        em.persist(chuyenMuc);
        em.flush();
        deTai.setChuyenMuc(chuyenMuc);
        deTaiRepository.saveAndFlush(deTai);
        Long chuyenMucId = chuyenMuc.getId();

        // Get all the deTaiList where chuyenMuc equals to chuyenMucId
        defaultDeTaiShouldBeFound("chuyenMucId.equals=" + chuyenMucId);

        // Get all the deTaiList where chuyenMuc equals to chuyenMucId + 1
        defaultDeTaiShouldNotBeFound("chuyenMucId.equals=" + (chuyenMucId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDeTaiShouldBeFound(String filter) throws Exception {
        restDeTaiMockMvc.perform(get("/api/de-tais?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deTai.getId().intValue())))
            .andExpect(jsonPath("$.[*].sott").value(hasItem(DEFAULT_SOTT)))
            .andExpect(jsonPath("$.[*].tendetai").value(hasItem(DEFAULT_TENDETAI)))
            .andExpect(jsonPath("$.[*].ngaytao").value(hasItem(DEFAULT_NGAYTAO.toString())))
            .andExpect(jsonPath("$.[*].ngaypheduyet").value(hasItem(DEFAULT_NGAYPHEDUYET.toString())))
            .andExpect(jsonPath("$.[*].ngaybd").value(hasItem(DEFAULT_NGAYBD.toString())))
            .andExpect(jsonPath("$.[*].ngaykt").value(hasItem(DEFAULT_NGAYKT.toString())))
            .andExpect(jsonPath("$.[*].hientrang").value(hasItem(DEFAULT_HIENTRANG)))
            .andExpect(jsonPath("$.[*].xuatban").value(hasItem(DEFAULT_XUATBAN)))
            .andExpect(jsonPath("$.[*].capquanly").value(hasItem(DEFAULT_CAPQUANLY)))
            .andExpect(jsonPath("$.[*].ngaynghiemthu").value(hasItem(DEFAULT_NGAYNGHIEMTHU.toString())))
            .andExpect(jsonPath("$.[*].kinhphithuchien").value(hasItem(DEFAULT_KINHPHITHUCHIEN)))
            .andExpect(jsonPath("$.[*].nguonkinhphi").value(hasItem(DEFAULT_NGUONKINHPHI)))
            .andExpect(jsonPath("$.[*].muctieu").value(hasItem(DEFAULT_MUCTIEU)))
            .andExpect(jsonPath("$.[*].kinhphiDukien").value(hasItem(DEFAULT_KINHPHI_DUKIEN)))
            .andExpect(jsonPath("$.[*].chunhiemdetai").value(hasItem(DEFAULT_CHUNHIEMDETAI)))
            .andExpect(jsonPath("$.[*].noidungtongquan").value(hasItem(DEFAULT_NOIDUNGTONGQUAN)))
            .andExpect(jsonPath("$.[*].kinhphiHoanthanh").value(hasItem(DEFAULT_KINHPHI_HOANTHANH)))
            .andExpect(jsonPath("$.[*].tongdiem").value(hasItem(DEFAULT_TONGDIEM)))
            .andExpect(jsonPath("$.[*].ghichu").value(hasItem(DEFAULT_GHICHU)))
            .andExpect(jsonPath("$.[*].nguoiCn").value(hasItem(DEFAULT_NGUOI_CN)))
            .andExpect(jsonPath("$.[*].ngayCn").value(hasItem(DEFAULT_NGAY_CN.toString())));

        // Check, that the count call also returns 1
        restDeTaiMockMvc.perform(get("/api/de-tais/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDeTaiShouldNotBeFound(String filter) throws Exception {
        restDeTaiMockMvc.perform(get("/api/de-tais?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDeTaiMockMvc.perform(get("/api/de-tais/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDeTai() throws Exception {
        // Get the deTai
        restDeTaiMockMvc.perform(get("/api/de-tais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeTai() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        int databaseSizeBeforeUpdate = deTaiRepository.findAll().size();

        // Update the deTai
        DeTai updatedDeTai = deTaiRepository.findById(deTai.getId()).get();
        // Disconnect from session so that the updates on updatedDeTai are not directly saved in db
        em.detach(updatedDeTai);
        updatedDeTai
            .sott(UPDATED_SOTT)
            .tendetai(UPDATED_TENDETAI)
            .ngaytao(UPDATED_NGAYTAO)
            .ngaypheduyet(UPDATED_NGAYPHEDUYET)
            .ngaybd(UPDATED_NGAYBD)
            .ngaykt(UPDATED_NGAYKT)
            .hientrang(UPDATED_HIENTRANG)
            .xuatban(UPDATED_XUATBAN)
            .capquanly(UPDATED_CAPQUANLY)
            .ngaynghiemthu(UPDATED_NGAYNGHIEMTHU)
            .kinhphithuchien(UPDATED_KINHPHITHUCHIEN)
            .nguonkinhphi(UPDATED_NGUONKINHPHI)
            .muctieu(UPDATED_MUCTIEU)
            .kinhphiDukien(UPDATED_KINHPHI_DUKIEN)
            .chunhiemdetai(UPDATED_CHUNHIEMDETAI)
            .noidungtongquan(UPDATED_NOIDUNGTONGQUAN)
            .kinhphiHoanthanh(UPDATED_KINHPHI_HOANTHANH)
            .tongdiem(UPDATED_TONGDIEM)
            .ghichu(UPDATED_GHICHU)
            .nguoiCn(UPDATED_NGUOI_CN)
            .ngayCn(UPDATED_NGAY_CN);
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(updatedDeTai);

        restDeTaiMockMvc.perform(put("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isOk());

        // Validate the DeTai in the database
        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeUpdate);
        DeTai testDeTai = deTaiList.get(deTaiList.size() - 1);
        assertThat(testDeTai.getSott()).isEqualTo(UPDATED_SOTT);
        assertThat(testDeTai.getTendetai()).isEqualTo(UPDATED_TENDETAI);
        assertThat(testDeTai.getNgaytao()).isEqualTo(UPDATED_NGAYTAO);
        assertThat(testDeTai.getNgaypheduyet()).isEqualTo(UPDATED_NGAYPHEDUYET);
        assertThat(testDeTai.getNgaybd()).isEqualTo(UPDATED_NGAYBD);
        assertThat(testDeTai.getNgaykt()).isEqualTo(UPDATED_NGAYKT);
        assertThat(testDeTai.getHientrang()).isEqualTo(UPDATED_HIENTRANG);
        assertThat(testDeTai.getXuatban()).isEqualTo(UPDATED_XUATBAN);
        assertThat(testDeTai.getCapquanly()).isEqualTo(UPDATED_CAPQUANLY);
        assertThat(testDeTai.getNgaynghiemthu()).isEqualTo(UPDATED_NGAYNGHIEMTHU);
        assertThat(testDeTai.getKinhphithuchien()).isEqualTo(UPDATED_KINHPHITHUCHIEN);
        assertThat(testDeTai.getNguonkinhphi()).isEqualTo(UPDATED_NGUONKINHPHI);
        assertThat(testDeTai.getMuctieu()).isEqualTo(UPDATED_MUCTIEU);
        assertThat(testDeTai.getKinhphiDukien()).isEqualTo(UPDATED_KINHPHI_DUKIEN);
        assertThat(testDeTai.getChunhiemdetai()).isEqualTo(UPDATED_CHUNHIEMDETAI);
        assertThat(testDeTai.getNoidungtongquan()).isEqualTo(UPDATED_NOIDUNGTONGQUAN);
        assertThat(testDeTai.getKinhphiHoanthanh()).isEqualTo(UPDATED_KINHPHI_HOANTHANH);
        assertThat(testDeTai.getTongdiem()).isEqualTo(UPDATED_TONGDIEM);
        assertThat(testDeTai.getGhichu()).isEqualTo(UPDATED_GHICHU);
        assertThat(testDeTai.getNguoiCn()).isEqualTo(UPDATED_NGUOI_CN);
        assertThat(testDeTai.getNgayCn()).isEqualTo(UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void updateNonExistingDeTai() throws Exception {
        int databaseSizeBeforeUpdate = deTaiRepository.findAll().size();

        // Create the DeTai
        DeTaiDTO deTaiDTO = deTaiMapper.toDto(deTai);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeTaiMockMvc.perform(put("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTaiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeTai in the database
        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeTai() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        int databaseSizeBeforeDelete = deTaiRepository.findAll().size();

        // Delete the deTai
        restDeTaiMockMvc.perform(delete("/api/de-tais/{id}", deTai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
