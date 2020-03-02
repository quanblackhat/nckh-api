package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QldtDaotaoMapperTest {

    private QldtDaotaoMapper qldtDaotaoMapper;

    @BeforeEach
    public void setUp() {
        qldtDaotaoMapper = new QldtDaotaoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qldtDaotaoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qldtDaotaoMapper.fromId(null)).isNull();
    }
}
