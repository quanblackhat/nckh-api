package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ChuyenMucMapperTest {

    private ChuyenMucMapper chuyenMucMapper;

    @BeforeEach
    public void setUp() {
        chuyenMucMapper = new ChuyenMucMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(chuyenMucMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(chuyenMucMapper.fromId(null)).isNull();
    }
}
