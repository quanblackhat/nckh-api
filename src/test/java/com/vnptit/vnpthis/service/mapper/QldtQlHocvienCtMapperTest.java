package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QldtQlHocvienCtMapperTest {

    private QldtQlHocvienCtMapper qldtQlHocvienCtMapper;

    @BeforeEach
    public void setUp() {
        qldtQlHocvienCtMapper = new QldtQlHocvienCtMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qldtQlHocvienCtMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qldtQlHocvienCtMapper.fromId(null)).isNull();
    }
}
