package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QldtDutoanDaotaoctMapperTest {

    private QldtDutoanDaotaoctMapper qldtDutoanDaotaoctMapper;

    @BeforeEach
    public void setUp() {
        qldtDutoanDaotaoctMapper = new QldtDutoanDaotaoctMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qldtDutoanDaotaoctMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qldtDutoanDaotaoctMapper.fromId(null)).isNull();
    }
}
