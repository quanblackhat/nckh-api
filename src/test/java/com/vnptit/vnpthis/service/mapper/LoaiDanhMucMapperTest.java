package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class LoaiDanhMucMapperTest {

    private LoaiDanhMucMapper loaiDanhMucMapper;

    @BeforeEach
    public void setUp() {
        loaiDanhMucMapper = new LoaiDanhMucMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(loaiDanhMucMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(loaiDanhMucMapper.fromId(null)).isNull();
    }
}
