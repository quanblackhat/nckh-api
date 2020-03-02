package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DuToanMapperTest {

    private DuToanMapper duToanMapper;

    @BeforeEach
    public void setUp() {
        duToanMapper = new DuToanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(duToanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(duToanMapper.fromId(null)).isNull();
    }
}
