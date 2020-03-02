package com.vnptit.vnpthis.web.rest;

import com.vnptit.vnpthis.VnptHisBackendApp;
import com.vnptit.vnpthis.domain.LoaiDanhMuc;
import com.vnptit.vnpthis.domain.DanhMuc;
import com.vnptit.vnpthis.repository.LoaiDanhMucRepository;
import com.vnptit.vnpthis.service.LoaiDanhMucService;
import com.vnptit.vnpthis.service.dto.LoaiDanhMucDTO;
import com.vnptit.vnpthis.service.mapper.LoaiDanhMucMapper;
import com.vnptit.vnpthis.web.rest.errors.ExceptionTranslator;
import com.vnptit.vnpthis.service.dto.LoaiDanhMucCriteria;
import com.vnptit.vnpthis.service.LoaiDanhMucQueryService;

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
 * Integration tests for the {@link LoaiDanhMucResource} REST controller.
 */
@SpringBootTest(classes = VnptHisBackendApp.class)
public class LoaiDanhMucResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUDUNG = 1;
    private static final Integer UPDATED_SUDUNG = 2;
    private static final Integer SMALLER_SUDUNG = 1 - 1;

    @Autowired
    private LoaiDanhMucRepository loaiDanhMucRepository;

    @Autowired
    private LoaiDanhMucMapper loaiDanhMucMapper;

    @Autowired
    private LoaiDanhMucService loaiDanhMucService;

    @Autowired
    private LoaiDanhMucQueryService loaiDanhMucQueryService;

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

    private MockMvc restLoaiDanhMucMockMvc;

    private LoaiDanhMuc loaiDanhMuc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoaiDanhMucResource loaiDanhMucResource = new LoaiDanhMucResource(loaiDanhMucService, loaiDanhMucQueryService);
        this.restLoaiDanhMucMockMvc = MockMvcBuilders.standaloneSetup(loaiDanhMucResource)
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
    public static LoaiDanhMuc createEntity(EntityManager em) {
        LoaiDanhMuc loaiDanhMuc = new LoaiDanhMuc()
            .ten(DEFAULT_TEN)
            .sudung(DEFAULT_SUDUNG);
        return loaiDanhMuc;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiDanhMuc createUpdatedEntity(EntityManager em) {
        LoaiDanhMuc loaiDanhMuc = new LoaiDanhMuc()
            .ten(UPDATED_TEN)
            .sudung(UPDATED_SUDUNG);
        return loaiDanhMuc;
    }

    @BeforeEach
    public void initTest() {
        loaiDanhMuc = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoaiDanhMuc() throws Exception {
        int databaseSizeBeforeCreate = loaiDanhMucRepository.findAll().size();

        // Create the LoaiDanhMuc
        LoaiDanhMucDTO loaiDanhMucDTO = loaiDanhMucMapper.toDto(loaiDanhMuc);
        restLoaiDanhMucMockMvc.perform(post("/api/loai-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiDanhMucDTO)))
            .andExpect(status().isCreated());

        // Validate the LoaiDanhMuc in the database
        List<LoaiDanhMuc> loaiDanhMucList = loaiDanhMucRepository.findAll();
        assertThat(loaiDanhMucList).hasSize(databaseSizeBeforeCreate + 1);
        LoaiDanhMuc testLoaiDanhMuc = loaiDanhMucList.get(loaiDanhMucList.size() - 1);
        assertThat(testLoaiDanhMuc.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testLoaiDanhMuc.getSudung()).isEqualTo(DEFAULT_SUDUNG);
    }

    @Test
    @Transactional
    public void createLoaiDanhMucWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loaiDanhMucRepository.findAll().size();

        // Create the LoaiDanhMuc with an existing ID
        loaiDanhMuc.setId(1L);
        LoaiDanhMucDTO loaiDanhMucDTO = loaiDanhMucMapper.toDto(loaiDanhMuc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiDanhMucMockMvc.perform(post("/api/loai-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiDanhMucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiDanhMuc in the database
        List<LoaiDanhMuc> loaiDanhMucList = loaiDanhMucRepository.findAll();
        assertThat(loaiDanhMucList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTenIsRequired() throws Exception {
        int databaseSizeBeforeTest = loaiDanhMucRepository.findAll().size();
        // set the field null
        loaiDanhMuc.setTen(null);

        // Create the LoaiDanhMuc, which fails.
        LoaiDanhMucDTO loaiDanhMucDTO = loaiDanhMucMapper.toDto(loaiDanhMuc);

        restLoaiDanhMucMockMvc.perform(post("/api/loai-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiDanhMucDTO)))
            .andExpect(status().isBadRequest());

        List<LoaiDanhMuc> loaiDanhMucList = loaiDanhMucRepository.findAll();
        assertThat(loaiDanhMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSudungIsRequired() throws Exception {
        int databaseSizeBeforeTest = loaiDanhMucRepository.findAll().size();
        // set the field null
        loaiDanhMuc.setSudung(null);

        // Create the LoaiDanhMuc, which fails.
        LoaiDanhMucDTO loaiDanhMucDTO = loaiDanhMucMapper.toDto(loaiDanhMuc);

        restLoaiDanhMucMockMvc.perform(post("/api/loai-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiDanhMucDTO)))
            .andExpect(status().isBadRequest());

        List<LoaiDanhMuc> loaiDanhMucList = loaiDanhMucRepository.findAll();
        assertThat(loaiDanhMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucs() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList
        restLoaiDanhMucMockMvc.perform(get("/api/loai-danh-mucs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiDanhMuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));
    }
    
    @Test
    @Transactional
    public void getLoaiDanhMuc() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get the loaiDanhMuc
        restLoaiDanhMucMockMvc.perform(get("/api/loai-danh-mucs/{id}", loaiDanhMuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loaiDanhMuc.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.sudung").value(DEFAULT_SUDUNG));
    }


    @Test
    @Transactional
    public void getLoaiDanhMucsByIdFiltering() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        Long id = loaiDanhMuc.getId();

        defaultLoaiDanhMucShouldBeFound("id.equals=" + id);
        defaultLoaiDanhMucShouldNotBeFound("id.notEquals=" + id);

        defaultLoaiDanhMucShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultLoaiDanhMucShouldNotBeFound("id.greaterThan=" + id);

        defaultLoaiDanhMucShouldBeFound("id.lessThanOrEqual=" + id);
        defaultLoaiDanhMucShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllLoaiDanhMucsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where ten equals to DEFAULT_TEN
        defaultLoaiDanhMucShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the loaiDanhMucList where ten equals to UPDATED_TEN
        defaultLoaiDanhMucShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where ten not equals to DEFAULT_TEN
        defaultLoaiDanhMucShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the loaiDanhMucList where ten not equals to UPDATED_TEN
        defaultLoaiDanhMucShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultLoaiDanhMucShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the loaiDanhMucList where ten equals to UPDATED_TEN
        defaultLoaiDanhMucShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where ten is not null
        defaultLoaiDanhMucShouldBeFound("ten.specified=true");

        // Get all the loaiDanhMucList where ten is null
        defaultLoaiDanhMucShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllLoaiDanhMucsByTenContainsSomething() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where ten contains DEFAULT_TEN
        defaultLoaiDanhMucShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the loaiDanhMucList where ten contains UPDATED_TEN
        defaultLoaiDanhMucShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where ten does not contain DEFAULT_TEN
        defaultLoaiDanhMucShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the loaiDanhMucList where ten does not contain UPDATED_TEN
        defaultLoaiDanhMucShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllLoaiDanhMucsBySudungIsEqualToSomething() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where sudung equals to DEFAULT_SUDUNG
        defaultLoaiDanhMucShouldBeFound("sudung.equals=" + DEFAULT_SUDUNG);

        // Get all the loaiDanhMucList where sudung equals to UPDATED_SUDUNG
        defaultLoaiDanhMucShouldNotBeFound("sudung.equals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucsBySudungIsNotEqualToSomething() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where sudung not equals to DEFAULT_SUDUNG
        defaultLoaiDanhMucShouldNotBeFound("sudung.notEquals=" + DEFAULT_SUDUNG);

        // Get all the loaiDanhMucList where sudung not equals to UPDATED_SUDUNG
        defaultLoaiDanhMucShouldBeFound("sudung.notEquals=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucsBySudungIsInShouldWork() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where sudung in DEFAULT_SUDUNG or UPDATED_SUDUNG
        defaultLoaiDanhMucShouldBeFound("sudung.in=" + DEFAULT_SUDUNG + "," + UPDATED_SUDUNG);

        // Get all the loaiDanhMucList where sudung equals to UPDATED_SUDUNG
        defaultLoaiDanhMucShouldNotBeFound("sudung.in=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucsBySudungIsNullOrNotNull() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where sudung is not null
        defaultLoaiDanhMucShouldBeFound("sudung.specified=true");

        // Get all the loaiDanhMucList where sudung is null
        defaultLoaiDanhMucShouldNotBeFound("sudung.specified=false");
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucsBySudungIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where sudung is greater than or equal to DEFAULT_SUDUNG
        defaultLoaiDanhMucShouldBeFound("sudung.greaterThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the loaiDanhMucList where sudung is greater than or equal to UPDATED_SUDUNG
        defaultLoaiDanhMucShouldNotBeFound("sudung.greaterThanOrEqual=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucsBySudungIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where sudung is less than or equal to DEFAULT_SUDUNG
        defaultLoaiDanhMucShouldBeFound("sudung.lessThanOrEqual=" + DEFAULT_SUDUNG);

        // Get all the loaiDanhMucList where sudung is less than or equal to SMALLER_SUDUNG
        defaultLoaiDanhMucShouldNotBeFound("sudung.lessThanOrEqual=" + SMALLER_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucsBySudungIsLessThanSomething() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where sudung is less than DEFAULT_SUDUNG
        defaultLoaiDanhMucShouldNotBeFound("sudung.lessThan=" + DEFAULT_SUDUNG);

        // Get all the loaiDanhMucList where sudung is less than UPDATED_SUDUNG
        defaultLoaiDanhMucShouldBeFound("sudung.lessThan=" + UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void getAllLoaiDanhMucsBySudungIsGreaterThanSomething() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        // Get all the loaiDanhMucList where sudung is greater than DEFAULT_SUDUNG
        defaultLoaiDanhMucShouldNotBeFound("sudung.greaterThan=" + DEFAULT_SUDUNG);

        // Get all the loaiDanhMucList where sudung is greater than SMALLER_SUDUNG
        defaultLoaiDanhMucShouldBeFound("sudung.greaterThan=" + SMALLER_SUDUNG);
    }


    @Test
    @Transactional
    public void getAllLoaiDanhMucsByDanhMucIsEqualToSomething() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);
        DanhMuc danhMuc = DanhMucResourceIT.createEntity(em);
        em.persist(danhMuc);
        em.flush();
        loaiDanhMuc.addDanhMuc(danhMuc);
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);
        Long danhMucId = danhMuc.getId();

        // Get all the loaiDanhMucList where danhMuc equals to danhMucId
        defaultLoaiDanhMucShouldBeFound("danhMucId.equals=" + danhMucId);

        // Get all the loaiDanhMucList where danhMuc equals to danhMucId + 1
        defaultLoaiDanhMucShouldNotBeFound("danhMucId.equals=" + (danhMucId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLoaiDanhMucShouldBeFound(String filter) throws Exception {
        restLoaiDanhMucMockMvc.perform(get("/api/loai-danh-mucs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiDanhMuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].sudung").value(hasItem(DEFAULT_SUDUNG)));

        // Check, that the count call also returns 1
        restLoaiDanhMucMockMvc.perform(get("/api/loai-danh-mucs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLoaiDanhMucShouldNotBeFound(String filter) throws Exception {
        restLoaiDanhMucMockMvc.perform(get("/api/loai-danh-mucs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLoaiDanhMucMockMvc.perform(get("/api/loai-danh-mucs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingLoaiDanhMuc() throws Exception {
        // Get the loaiDanhMuc
        restLoaiDanhMucMockMvc.perform(get("/api/loai-danh-mucs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoaiDanhMuc() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        int databaseSizeBeforeUpdate = loaiDanhMucRepository.findAll().size();

        // Update the loaiDanhMuc
        LoaiDanhMuc updatedLoaiDanhMuc = loaiDanhMucRepository.findById(loaiDanhMuc.getId()).get();
        // Disconnect from session so that the updates on updatedLoaiDanhMuc are not directly saved in db
        em.detach(updatedLoaiDanhMuc);
        updatedLoaiDanhMuc
            .ten(UPDATED_TEN)
            .sudung(UPDATED_SUDUNG);
        LoaiDanhMucDTO loaiDanhMucDTO = loaiDanhMucMapper.toDto(updatedLoaiDanhMuc);

        restLoaiDanhMucMockMvc.perform(put("/api/loai-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiDanhMucDTO)))
            .andExpect(status().isOk());

        // Validate the LoaiDanhMuc in the database
        List<LoaiDanhMuc> loaiDanhMucList = loaiDanhMucRepository.findAll();
        assertThat(loaiDanhMucList).hasSize(databaseSizeBeforeUpdate);
        LoaiDanhMuc testLoaiDanhMuc = loaiDanhMucList.get(loaiDanhMucList.size() - 1);
        assertThat(testLoaiDanhMuc.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testLoaiDanhMuc.getSudung()).isEqualTo(UPDATED_SUDUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingLoaiDanhMuc() throws Exception {
        int databaseSizeBeforeUpdate = loaiDanhMucRepository.findAll().size();

        // Create the LoaiDanhMuc
        LoaiDanhMucDTO loaiDanhMucDTO = loaiDanhMucMapper.toDto(loaiDanhMuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiDanhMucMockMvc.perform(put("/api/loai-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loaiDanhMucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiDanhMuc in the database
        List<LoaiDanhMuc> loaiDanhMucList = loaiDanhMucRepository.findAll();
        assertThat(loaiDanhMucList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLoaiDanhMuc() throws Exception {
        // Initialize the database
        loaiDanhMucRepository.saveAndFlush(loaiDanhMuc);

        int databaseSizeBeforeDelete = loaiDanhMucRepository.findAll().size();

        // Delete the loaiDanhMuc
        restLoaiDanhMucMockMvc.perform(delete("/api/loai-danh-mucs/{id}", loaiDanhMuc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LoaiDanhMuc> loaiDanhMucList = loaiDanhMucRepository.findAll();
        assertThat(loaiDanhMucList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
