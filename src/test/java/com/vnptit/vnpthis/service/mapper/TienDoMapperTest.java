package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TienDoMapperTest {

    private TienDoMapper tienDoMapper;

    @BeforeEach
    public void setUp() {
        tienDoMapper = new TienDoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(tienDoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tienDoMapper.fromId(null)).isNull();
    }
}
