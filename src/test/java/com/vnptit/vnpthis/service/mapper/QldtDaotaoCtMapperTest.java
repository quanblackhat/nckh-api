package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QldtDaotaoCtMapperTest {

    private QldtDaotaoCtMapper qldtDaotaoCtMapper;

    @BeforeEach
    public void setUp() {
        qldtDaotaoCtMapper = new QldtDaotaoCtMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qldtDaotaoCtMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qldtDaotaoCtMapper.fromId(null)).isNull();
    }
}
