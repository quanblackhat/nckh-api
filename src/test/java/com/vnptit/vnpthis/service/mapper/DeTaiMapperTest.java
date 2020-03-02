package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DeTaiMapperTest {

    private DeTaiMapper deTaiMapper;

    @BeforeEach
    public void setUp() {
        deTaiMapper = new DeTaiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(deTaiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(deTaiMapper.fromId(null)).isNull();
    }
}
