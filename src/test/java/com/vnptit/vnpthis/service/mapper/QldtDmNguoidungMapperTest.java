package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QldtDmNguoidungMapperTest {

    private QldtDmNguoidungMapper qldtDmNguoidungMapper;

    @BeforeEach
    public void setUp() {
        qldtDmNguoidungMapper = new QldtDmNguoidungMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qldtDmNguoidungMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qldtDmNguoidungMapper.fromId(null)).isNull();
    }
}
