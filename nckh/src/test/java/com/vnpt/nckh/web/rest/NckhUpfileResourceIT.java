package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.NckhApp;
import com.vnpt.nckh.domain.NckhUpfile;
import com.vnpt.nckh.domain.NckhDetai;
import com.vnpt.nckh.repository.NckhUpfileRepository;
import com.vnpt.nckh.service.NckhUpfileService;
import com.vnpt.nckh.web.rest.errors.ExceptionTranslator;
import com.vnpt.nckh.service.dto.NckhUpfileCriteria;
import com.vnpt.nckh.service.NckhUpfileQueryService;

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
import org.springframework.util.Base64Utils;
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
 * Integration tests for the {@link NckhUpfileResource} REST controller.
 */
@SpringBootTest(classes = NckhApp.class)
public class NckhUpfileResourceIT {

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final byte[] DEFAULT_NOI_DUNG = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NOI_DUNG = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_NOI_DUNG_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NOI_DUNG_CONTENT_TYPE = "image/png";

    private static final Long DEFAULT_NGUOI_CN = 1L;
    private static final Long UPDATED_NGUOI_CN = 2L;
    private static final Long SMALLER_NGUOI_CN = 1L - 1L;

    private static final Instant DEFAULT_NGAY_CN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_CN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private NckhUpfileRepository nckhUpfileRepository;

    @Autowired
    private NckhUpfileService nckhUpfileService;

    @Autowired
    private NckhUpfileQueryService nckhUpfileQueryService;

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

    private MockMvc restNckhUpfileMockMvc;

    private NckhUpfile nckhUpfile;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NckhUpfileResource nckhUpfileResource = new NckhUpfileResource(nckhUpfileService, nckhUpfileQueryService);
        this.restNckhUpfileMockMvc = MockMvcBuilders.standaloneSetup(nckhUpfileResource)
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
    public static NckhUpfile createEntity(EntityManager em) {
        NckhUpfile nckhUpfile = new NckhUpfile()
            .moTa(DEFAULT_MO_TA)
            .noiDung(DEFAULT_NOI_DUNG)
            .noiDungContentType(DEFAULT_NOI_DUNG_CONTENT_TYPE)
            .nguoiCN(DEFAULT_NGUOI_CN)
            .ngayCN(DEFAULT_NGAY_CN);
        return nckhUpfile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NckhUpfile createUpdatedEntity(EntityManager em) {
        NckhUpfile nckhUpfile = new NckhUpfile()
            .moTa(UPDATED_MO_TA)
            .noiDung(UPDATED_NOI_DUNG)
            .noiDungContentType(UPDATED_NOI_DUNG_CONTENT_TYPE)
            .nguoiCN(UPDATED_NGUOI_CN)
            .ngayCN(UPDATED_NGAY_CN);
        return nckhUpfile;
    }

    @BeforeEach
    public void initTest() {
        nckhUpfile = createEntity(em);
    }

    @Test
    @Transactional
    public void createNckhUpfile() throws Exception {
        int databaseSizeBeforeCreate = nckhUpfileRepository.findAll().size();

        // Create the NckhUpfile
        restNckhUpfileMockMvc.perform(post("/api/nckh-upfiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhUpfile)))
            .andExpect(status().isCreated());

        // Validate the NckhUpfile in the database
        List<NckhUpfile> nckhUpfileList = nckhUpfileRepository.findAll();
        assertThat(nckhUpfileList).hasSize(databaseSizeBeforeCreate + 1);
        NckhUpfile testNckhUpfile = nckhUpfileList.get(nckhUpfileList.size() - 1);
        assertThat(testNckhUpfile.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testNckhUpfile.getNoiDung()).isEqualTo(DEFAULT_NOI_DUNG);
        assertThat(testNckhUpfile.getNoiDungContentType()).isEqualTo(DEFAULT_NOI_DUNG_CONTENT_TYPE);
        assertThat(testNckhUpfile.getNguoiCN()).isEqualTo(DEFAULT_NGUOI_CN);
        assertThat(testNckhUpfile.getNgayCN()).isEqualTo(DEFAULT_NGAY_CN);
    }

    @Test
    @Transactional
    public void createNckhUpfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nckhUpfileRepository.findAll().size();

        // Create the NckhUpfile with an existing ID
        nckhUpfile.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNckhUpfileMockMvc.perform(post("/api/nckh-upfiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhUpfile)))
            .andExpect(status().isBadRequest());

        // Validate the NckhUpfile in the database
        List<NckhUpfile> nckhUpfileList = nckhUpfileRepository.findAll();
        assertThat(nckhUpfileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNckhUpfiles() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList
        restNckhUpfileMockMvc.perform(get("/api/nckh-upfiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nckhUpfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].noiDungContentType").value(hasItem(DEFAULT_NOI_DUNG_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOI_DUNG))))
            .andExpect(jsonPath("$.[*].nguoiCN").value(hasItem(DEFAULT_NGUOI_CN.intValue())))
            .andExpect(jsonPath("$.[*].ngayCN").value(hasItem(DEFAULT_NGAY_CN.toString())));
    }
    
    @Test
    @Transactional
    public void getNckhUpfile() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get the nckhUpfile
        restNckhUpfileMockMvc.perform(get("/api/nckh-upfiles/{id}", nckhUpfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nckhUpfile.getId().intValue()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.noiDungContentType").value(DEFAULT_NOI_DUNG_CONTENT_TYPE))
            .andExpect(jsonPath("$.noiDung").value(Base64Utils.encodeToString(DEFAULT_NOI_DUNG)))
            .andExpect(jsonPath("$.nguoiCN").value(DEFAULT_NGUOI_CN.intValue()))
            .andExpect(jsonPath("$.ngayCN").value(DEFAULT_NGAY_CN.toString()));
    }


    @Test
    @Transactional
    public void getNckhUpfilesByIdFiltering() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        Long id = nckhUpfile.getId();

        defaultNckhUpfileShouldBeFound("id.equals=" + id);
        defaultNckhUpfileShouldNotBeFound("id.notEquals=" + id);

        defaultNckhUpfileShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNckhUpfileShouldNotBeFound("id.greaterThan=" + id);

        defaultNckhUpfileShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNckhUpfileShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllNckhUpfilesByMoTaIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where moTa equals to DEFAULT_MO_TA
        defaultNckhUpfileShouldBeFound("moTa.equals=" + DEFAULT_MO_TA);

        // Get all the nckhUpfileList where moTa equals to UPDATED_MO_TA
        defaultNckhUpfileShouldNotBeFound("moTa.equals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByMoTaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where moTa not equals to DEFAULT_MO_TA
        defaultNckhUpfileShouldNotBeFound("moTa.notEquals=" + DEFAULT_MO_TA);

        // Get all the nckhUpfileList where moTa not equals to UPDATED_MO_TA
        defaultNckhUpfileShouldBeFound("moTa.notEquals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByMoTaIsInShouldWork() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where moTa in DEFAULT_MO_TA or UPDATED_MO_TA
        defaultNckhUpfileShouldBeFound("moTa.in=" + DEFAULT_MO_TA + "," + UPDATED_MO_TA);

        // Get all the nckhUpfileList where moTa equals to UPDATED_MO_TA
        defaultNckhUpfileShouldNotBeFound("moTa.in=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByMoTaIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where moTa is not null
        defaultNckhUpfileShouldBeFound("moTa.specified=true");

        // Get all the nckhUpfileList where moTa is null
        defaultNckhUpfileShouldNotBeFound("moTa.specified=false");
    }
                @Test
    @Transactional
    public void getAllNckhUpfilesByMoTaContainsSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where moTa contains DEFAULT_MO_TA
        defaultNckhUpfileShouldBeFound("moTa.contains=" + DEFAULT_MO_TA);

        // Get all the nckhUpfileList where moTa contains UPDATED_MO_TA
        defaultNckhUpfileShouldNotBeFound("moTa.contains=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByMoTaNotContainsSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where moTa does not contain DEFAULT_MO_TA
        defaultNckhUpfileShouldNotBeFound("moTa.doesNotContain=" + DEFAULT_MO_TA);

        // Get all the nckhUpfileList where moTa does not contain UPDATED_MO_TA
        defaultNckhUpfileShouldBeFound("moTa.doesNotContain=" + UPDATED_MO_TA);
    }


    @Test
    @Transactional
    public void getAllNckhUpfilesByNguoiCNIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where nguoiCN equals to DEFAULT_NGUOI_CN
        defaultNckhUpfileShouldBeFound("nguoiCN.equals=" + DEFAULT_NGUOI_CN);

        // Get all the nckhUpfileList where nguoiCN equals to UPDATED_NGUOI_CN
        defaultNckhUpfileShouldNotBeFound("nguoiCN.equals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByNguoiCNIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where nguoiCN not equals to DEFAULT_NGUOI_CN
        defaultNckhUpfileShouldNotBeFound("nguoiCN.notEquals=" + DEFAULT_NGUOI_CN);

        // Get all the nckhUpfileList where nguoiCN not equals to UPDATED_NGUOI_CN
        defaultNckhUpfileShouldBeFound("nguoiCN.notEquals=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByNguoiCNIsInShouldWork() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where nguoiCN in DEFAULT_NGUOI_CN or UPDATED_NGUOI_CN
        defaultNckhUpfileShouldBeFound("nguoiCN.in=" + DEFAULT_NGUOI_CN + "," + UPDATED_NGUOI_CN);

        // Get all the nckhUpfileList where nguoiCN equals to UPDATED_NGUOI_CN
        defaultNckhUpfileShouldNotBeFound("nguoiCN.in=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByNguoiCNIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where nguoiCN is not null
        defaultNckhUpfileShouldBeFound("nguoiCN.specified=true");

        // Get all the nckhUpfileList where nguoiCN is null
        defaultNckhUpfileShouldNotBeFound("nguoiCN.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByNguoiCNIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where nguoiCN is greater than or equal to DEFAULT_NGUOI_CN
        defaultNckhUpfileShouldBeFound("nguoiCN.greaterThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the nckhUpfileList where nguoiCN is greater than or equal to UPDATED_NGUOI_CN
        defaultNckhUpfileShouldNotBeFound("nguoiCN.greaterThanOrEqual=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByNguoiCNIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where nguoiCN is less than or equal to DEFAULT_NGUOI_CN
        defaultNckhUpfileShouldBeFound("nguoiCN.lessThanOrEqual=" + DEFAULT_NGUOI_CN);

        // Get all the nckhUpfileList where nguoiCN is less than or equal to SMALLER_NGUOI_CN
        defaultNckhUpfileShouldNotBeFound("nguoiCN.lessThanOrEqual=" + SMALLER_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByNguoiCNIsLessThanSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where nguoiCN is less than DEFAULT_NGUOI_CN
        defaultNckhUpfileShouldNotBeFound("nguoiCN.lessThan=" + DEFAULT_NGUOI_CN);

        // Get all the nckhUpfileList where nguoiCN is less than UPDATED_NGUOI_CN
        defaultNckhUpfileShouldBeFound("nguoiCN.lessThan=" + UPDATED_NGUOI_CN);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByNguoiCNIsGreaterThanSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where nguoiCN is greater than DEFAULT_NGUOI_CN
        defaultNckhUpfileShouldNotBeFound("nguoiCN.greaterThan=" + DEFAULT_NGUOI_CN);

        // Get all the nckhUpfileList where nguoiCN is greater than SMALLER_NGUOI_CN
        defaultNckhUpfileShouldBeFound("nguoiCN.greaterThan=" + SMALLER_NGUOI_CN);
    }


    @Test
    @Transactional
    public void getAllNckhUpfilesByNgayCNIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where ngayCN equals to DEFAULT_NGAY_CN
        defaultNckhUpfileShouldBeFound("ngayCN.equals=" + DEFAULT_NGAY_CN);

        // Get all the nckhUpfileList where ngayCN equals to UPDATED_NGAY_CN
        defaultNckhUpfileShouldNotBeFound("ngayCN.equals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByNgayCNIsNotEqualToSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where ngayCN not equals to DEFAULT_NGAY_CN
        defaultNckhUpfileShouldNotBeFound("ngayCN.notEquals=" + DEFAULT_NGAY_CN);

        // Get all the nckhUpfileList where ngayCN not equals to UPDATED_NGAY_CN
        defaultNckhUpfileShouldBeFound("ngayCN.notEquals=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByNgayCNIsInShouldWork() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where ngayCN in DEFAULT_NGAY_CN or UPDATED_NGAY_CN
        defaultNckhUpfileShouldBeFound("ngayCN.in=" + DEFAULT_NGAY_CN + "," + UPDATED_NGAY_CN);

        // Get all the nckhUpfileList where ngayCN equals to UPDATED_NGAY_CN
        defaultNckhUpfileShouldNotBeFound("ngayCN.in=" + UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByNgayCNIsNullOrNotNull() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);

        // Get all the nckhUpfileList where ngayCN is not null
        defaultNckhUpfileShouldBeFound("ngayCN.specified=true");

        // Get all the nckhUpfileList where ngayCN is null
        defaultNckhUpfileShouldNotBeFound("ngayCN.specified=false");
    }

    @Test
    @Transactional
    public void getAllNckhUpfilesByNckhDetaiIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhUpfileRepository.saveAndFlush(nckhUpfile);
        NckhDetai nckhDetai = NckhDetaiResourceIT.createEntity(em);
        em.persist(nckhDetai);
        em.flush();
        nckhUpfile.setNckhDetai(nckhDetai);
        nckhUpfileRepository.saveAndFlush(nckhUpfile);
        Long nckhDetaiId = nckhDetai.getId();

        // Get all the nckhUpfileList where nckhDetai equals to nckhDetaiId
        defaultNckhUpfileShouldBeFound("nckhDetaiId.equals=" + nckhDetaiId);

        // Get all the nckhUpfileList where nckhDetai equals to nckhDetaiId + 1
        defaultNckhUpfileShouldNotBeFound("nckhDetaiId.equals=" + (nckhDetaiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNckhUpfileShouldBeFound(String filter) throws Exception {
        restNckhUpfileMockMvc.perform(get("/api/nckh-upfiles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nckhUpfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].noiDungContentType").value(hasItem(DEFAULT_NOI_DUNG_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOI_DUNG))))
            .andExpect(jsonPath("$.[*].nguoiCN").value(hasItem(DEFAULT_NGUOI_CN.intValue())))
            .andExpect(jsonPath("$.[*].ngayCN").value(hasItem(DEFAULT_NGAY_CN.toString())));

        // Check, that the count call also returns 1
        restNckhUpfileMockMvc.perform(get("/api/nckh-upfiles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNckhUpfileShouldNotBeFound(String filter) throws Exception {
        restNckhUpfileMockMvc.perform(get("/api/nckh-upfiles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNckhUpfileMockMvc.perform(get("/api/nckh-upfiles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingNckhUpfile() throws Exception {
        // Get the nckhUpfile
        restNckhUpfileMockMvc.perform(get("/api/nckh-upfiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNckhUpfile() throws Exception {
        // Initialize the database
        nckhUpfileService.save(nckhUpfile);

        int databaseSizeBeforeUpdate = nckhUpfileRepository.findAll().size();

        // Update the nckhUpfile
        NckhUpfile updatedNckhUpfile = nckhUpfileRepository.findById(nckhUpfile.getId()).get();
        // Disconnect from session so that the updates on updatedNckhUpfile are not directly saved in db
        em.detach(updatedNckhUpfile);
        updatedNckhUpfile
            .moTa(UPDATED_MO_TA)
            .noiDung(UPDATED_NOI_DUNG)
            .noiDungContentType(UPDATED_NOI_DUNG_CONTENT_TYPE)
            .nguoiCN(UPDATED_NGUOI_CN)
            .ngayCN(UPDATED_NGAY_CN);

        restNckhUpfileMockMvc.perform(put("/api/nckh-upfiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNckhUpfile)))
            .andExpect(status().isOk());

        // Validate the NckhUpfile in the database
        List<NckhUpfile> nckhUpfileList = nckhUpfileRepository.findAll();
        assertThat(nckhUpfileList).hasSize(databaseSizeBeforeUpdate);
        NckhUpfile testNckhUpfile = nckhUpfileList.get(nckhUpfileList.size() - 1);
        assertThat(testNckhUpfile.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testNckhUpfile.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testNckhUpfile.getNoiDungContentType()).isEqualTo(UPDATED_NOI_DUNG_CONTENT_TYPE);
        assertThat(testNckhUpfile.getNguoiCN()).isEqualTo(UPDATED_NGUOI_CN);
        assertThat(testNckhUpfile.getNgayCN()).isEqualTo(UPDATED_NGAY_CN);
    }

    @Test
    @Transactional
    public void updateNonExistingNckhUpfile() throws Exception {
        int databaseSizeBeforeUpdate = nckhUpfileRepository.findAll().size();

        // Create the NckhUpfile

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNckhUpfileMockMvc.perform(put("/api/nckh-upfiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhUpfile)))
            .andExpect(status().isBadRequest());

        // Validate the NckhUpfile in the database
        List<NckhUpfile> nckhUpfileList = nckhUpfileRepository.findAll();
        assertThat(nckhUpfileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNckhUpfile() throws Exception {
        // Initialize the database
        nckhUpfileService.save(nckhUpfile);

        int databaseSizeBeforeDelete = nckhUpfileRepository.findAll().size();

        // Delete the nckhUpfile
        restNckhUpfileMockMvc.perform(delete("/api/nckh-upfiles/{id}", nckhUpfile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NckhUpfile> nckhUpfileList = nckhUpfileRepository.findAll();
        assertThat(nckhUpfileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
