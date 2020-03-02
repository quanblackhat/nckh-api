package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QldtDmChungchiMapperTest {

    private QldtDmChungchiMapper qldtDmChungchiMapper;

    @BeforeEach
    public void setUp() {
        qldtDmChungchiMapper = new QldtDmChungchiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qldtDmChungchiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qldtDmChungchiMapper.fromId(null)).isNull();
    }
}
