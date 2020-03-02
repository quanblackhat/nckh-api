package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DanhMucMapperTest {

    private DanhMucMapper danhMucMapper;

    @BeforeEach
    public void setUp() {
        danhMucMapper = new DanhMucMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(danhMucMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(danhMucMapper.fromId(null)).isNull();
    }
}
