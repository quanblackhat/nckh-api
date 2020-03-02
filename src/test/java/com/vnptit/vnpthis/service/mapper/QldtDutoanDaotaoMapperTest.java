package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QldtDutoanDaotaoMapperTest {

    private QldtDutoanDaotaoMapper qldtDutoanDaotaoMapper;

    @BeforeEach
    public void setUp() {
        qldtDutoanDaotaoMapper = new QldtDutoanDaotaoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qldtDutoanDaotaoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qldtDutoanDaotaoMapper.fromId(null)).isNull();
    }
}
