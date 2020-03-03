package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QldtQlHocvienMapperTest {

    private QldtQlHocvienMapper qldtQlHocvienMapper;

    @BeforeEach
    public void setUp() {
        qldtQlHocvienMapper = new QldtQlHocvienMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qldtQlHocvienMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qldtQlHocvienMapper.fromId(null)).isNull();
    }
}
