package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QldtDmNoidungMapperTest {

    private QldtDmNoidungMapper qldtDmNoidungMapper;

    @BeforeEach
    public void setUp() {
        qldtDmNoidungMapper = new QldtDmNoidungMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qldtDmNoidungMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qldtDmNoidungMapper.fromId(null)).isNull();
    }
}
