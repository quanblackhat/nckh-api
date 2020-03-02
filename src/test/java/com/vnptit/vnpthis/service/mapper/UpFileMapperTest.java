package com.vnptit.vnpthis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class UpFileMapperTest {

    private UpFileMapper upFileMapper;

    @BeforeEach
    public void setUp() {
        upFileMapper = new UpFileMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(upFileMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(upFileMapper.fromId(null)).isNull();
    }
}
