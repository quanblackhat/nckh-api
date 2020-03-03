package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QldtTochucCapMapperTest {

    private QldtTochucCapMapper qldtTochucCapMapper;

    @BeforeEach
    public void setUp() {
        qldtTochucCapMapper = new QldtTochucCapMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qldtTochucCapMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qldtTochucCapMapper.fromId(null)).isNull();
    }
}
