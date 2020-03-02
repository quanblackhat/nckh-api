package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QldtChungChiMapperTest {

    private QldtChungChiMapper qldtChungChiMapper;

    @BeforeEach
    public void setUp() {
        qldtChungChiMapper = new QldtChungChiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qldtChungChiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qldtChungChiMapper.fromId(null)).isNull();
    }
}
