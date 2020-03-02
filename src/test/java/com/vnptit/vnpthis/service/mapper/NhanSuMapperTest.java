package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NhanSuMapperTest {

    private NhanSuMapper nhanSuMapper;

    @BeforeEach
    public void setUp() {
        nhanSuMapper = new NhanSuMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(nhanSuMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nhanSuMapper.fromId(null)).isNull();
    }
}
