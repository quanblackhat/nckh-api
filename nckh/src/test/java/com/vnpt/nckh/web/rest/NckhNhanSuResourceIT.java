package com.vnpt.nckh.web.rest;

import com.vnpt.nckh.NckhApp;
import com.vnpt.nckh.domain.NckhNhanSu;
import com.vnpt.nckh.domain.OrgOfficer;
import com.vnpt.nckh.domain.NckhDetai;
import com.vnpt.nckh.repository.NckhNhanSuRepository;
import com.vnpt.nckh.service.NckhNhanSuService;
import com.vnpt.nckh.web.rest.errors.ExceptionTranslator;
import com.vnpt.nckh.service.dto.NckhNhanSuCriteria;
import com.vnpt.nckh.service.NckhNhanSuQueryService;

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

import static com.vnpt.nckh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NckhNhanSuResource} REST controller.
 */
@SpringBootTest(classes = NckhApp.class)
public class NckhNhanSuResourceIT {

    @Autowired
    private NckhNhanSuRepository nckhNhanSuRepository;

    @Autowired
    private NckhNhanSuService nckhNhanSuService;

    @Autowired
    private NckhNhanSuQueryService nckhNhanSuQueryService;

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

    private MockMvc restNckhNhanSuMockMvc;

    private NckhNhanSu nckhNhanSu;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NckhNhanSuResource nckhNhanSuResource = new NckhNhanSuResource(nckhNhanSuService, nckhNhanSuQueryService);
        this.restNckhNhanSuMockMvc = MockMvcBuilders.standaloneSetup(nckhNhanSuResource)
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
    public static NckhNhanSu createEntity(EntityManager em) {
        NckhNhanSu nckhNhanSu = new NckhNhanSu();
        return nckhNhanSu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NckhNhanSu createUpdatedEntity(EntityManager em) {
        NckhNhanSu nckhNhanSu = new NckhNhanSu();
        return nckhNhanSu;
    }

    @BeforeEach
    public void initTest() {
        nckhNhanSu = createEntity(em);
    }

    @Test
    @Transactional
    public void createNckhNhanSu() throws Exception {
        int databaseSizeBeforeCreate = nckhNhanSuRepository.findAll().size();

        // Create the NckhNhanSu
        restNckhNhanSuMockMvc.perform(post("/api/nckh-nhan-sus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhNhanSu)))
            .andExpect(status().isCreated());

        // Validate the NckhNhanSu in the database
        List<NckhNhanSu> nckhNhanSuList = nckhNhanSuRepository.findAll();
        assertThat(nckhNhanSuList).hasSize(databaseSizeBeforeCreate + 1);
        NckhNhanSu testNckhNhanSu = nckhNhanSuList.get(nckhNhanSuList.size() - 1);
    }

    @Test
    @Transactional
    public void createNckhNhanSuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nckhNhanSuRepository.findAll().size();

        // Create the NckhNhanSu with an existing ID
        nckhNhanSu.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNckhNhanSuMockMvc.perform(post("/api/nckh-nhan-sus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhNhanSu)))
            .andExpect(status().isBadRequest());

        // Validate the NckhNhanSu in the database
        List<NckhNhanSu> nckhNhanSuList = nckhNhanSuRepository.findAll();
        assertThat(nckhNhanSuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNckhNhanSus() throws Exception {
        // Initialize the database
        nckhNhanSuRepository.saveAndFlush(nckhNhanSu);

        // Get all the nckhNhanSuList
        restNckhNhanSuMockMvc.perform(get("/api/nckh-nhan-sus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nckhNhanSu.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getNckhNhanSu() throws Exception {
        // Initialize the database
        nckhNhanSuRepository.saveAndFlush(nckhNhanSu);

        // Get the nckhNhanSu
        restNckhNhanSuMockMvc.perform(get("/api/nckh-nhan-sus/{id}", nckhNhanSu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nckhNhanSu.getId().intValue()));
    }


    @Test
    @Transactional
    public void getNckhNhanSusByIdFiltering() throws Exception {
        // Initialize the database
        nckhNhanSuRepository.saveAndFlush(nckhNhanSu);

        Long id = nckhNhanSu.getId();

        defaultNckhNhanSuShouldBeFound("id.equals=" + id);
        defaultNckhNhanSuShouldNotBeFound("id.notEquals=" + id);

        defaultNckhNhanSuShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNckhNhanSuShouldNotBeFound("id.greaterThan=" + id);

        defaultNckhNhanSuShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNckhNhanSuShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllNckhNhanSusByOrgOfficerIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhNhanSuRepository.saveAndFlush(nckhNhanSu);
        OrgOfficer orgOfficer = OrgOfficerResourceIT.createEntity(em);
        em.persist(orgOfficer);
        em.flush();
        nckhNhanSu.setOrgOfficer(orgOfficer);
        nckhNhanSuRepository.saveAndFlush(nckhNhanSu);
        Long orgOfficerId = orgOfficer.getId();

        // Get all the nckhNhanSuList where orgOfficer equals to orgOfficerId
        defaultNckhNhanSuShouldBeFound("orgOfficerId.equals=" + orgOfficerId);

        // Get all the nckhNhanSuList where orgOfficer equals to orgOfficerId + 1
        defaultNckhNhanSuShouldNotBeFound("orgOfficerId.equals=" + (orgOfficerId + 1));
    }


    @Test
    @Transactional
    public void getAllNckhNhanSusByNckhDetaiIsEqualToSomething() throws Exception {
        // Initialize the database
        nckhNhanSuRepository.saveAndFlush(nckhNhanSu);
        NckhDetai nckhDetai = NckhDetaiResourceIT.createEntity(em);
        em.persist(nckhDetai);
        em.flush();
        nckhNhanSu.setNckhDetai(nckhDetai);
        nckhNhanSuRepository.saveAndFlush(nckhNhanSu);
        Long nckhDetaiId = nckhDetai.getId();

        // Get all the nckhNhanSuList where nckhDetai equals to nckhDetaiId
        defaultNckhNhanSuShouldBeFound("nckhDetaiId.equals=" + nckhDetaiId);

        // Get all the nckhNhanSuList where nckhDetai equals to nckhDetaiId + 1
        defaultNckhNhanSuShouldNotBeFound("nckhDetaiId.equals=" + (nckhDetaiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNckhNhanSuShouldBeFound(String filter) throws Exception {
        restNckhNhanSuMockMvc.perform(get("/api/nckh-nhan-sus?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nckhNhanSu.getId().intValue())));

        // Check, that the count call also returns 1
        restNckhNhanSuMockMvc.perform(get("/api/nckh-nhan-sus/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNckhNhanSuShouldNotBeFound(String filter) throws Exception {
        restNckhNhanSuMockMvc.perform(get("/api/nckh-nhan-sus?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNckhNhanSuMockMvc.perform(get("/api/nckh-nhan-sus/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingNckhNhanSu() throws Exception {
        // Get the nckhNhanSu
        restNckhNhanSuMockMvc.perform(get("/api/nckh-nhan-sus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNckhNhanSu() throws Exception {
        // Initialize the database
        nckhNhanSuService.save(nckhNhanSu);

        int databaseSizeBeforeUpdate = nckhNhanSuRepository.findAll().size();

        // Update the nckhNhanSu
        NckhNhanSu updatedNckhNhanSu = nckhNhanSuRepository.findById(nckhNhanSu.getId()).get();
        // Disconnect from session so that the updates on updatedNckhNhanSu are not directly saved in db
        em.detach(updatedNckhNhanSu);

        restNckhNhanSuMockMvc.perform(put("/api/nckh-nhan-sus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNckhNhanSu)))
            .andExpect(status().isOk());

        // Validate the NckhNhanSu in the database
        List<NckhNhanSu> nckhNhanSuList = nckhNhanSuRepository.findAll();
        assertThat(nckhNhanSuList).hasSize(databaseSizeBeforeUpdate);
        NckhNhanSu testNckhNhanSu = nckhNhanSuList.get(nckhNhanSuList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingNckhNhanSu() throws Exception {
        int databaseSizeBeforeUpdate = nckhNhanSuRepository.findAll().size();

        // Create the NckhNhanSu

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNckhNhanSuMockMvc.perform(put("/api/nckh-nhan-sus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nckhNhanSu)))
            .andExpect(status().isBadRequest());

        // Validate the NckhNhanSu in the database
        List<NckhNhanSu> nckhNhanSuList = nckhNhanSuRepository.findAll();
        assertThat(nckhNhanSuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNckhNhanSu() throws Exception {
        // Initialize the database
        nckhNhanSuService.save(nckhNhanSu);

        int databaseSizeBeforeDelete = nckhNhanSuRepository.findAll().size();

        // Delete the nckhNhanSu
        restNckhNhanSuMockMvc.perform(delete("/api/nckh-nhan-sus/{id}", nckhNhanSu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NckhNhanSu> nckhNhanSuList = nckhNhanSuRepository.findAll();
        assertThat(nckhNhanSuList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
