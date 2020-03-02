package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DanhGiaMapperTest {

    private DanhGiaMapper danhGiaMapper;

    @BeforeEach
    public void setUp() {
        danhGiaMapper = new DanhGiaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(danhGiaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(danhGiaMapper.fromId(null)).isNull();
    }
}
