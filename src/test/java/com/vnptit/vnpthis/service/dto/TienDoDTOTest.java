package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class TienDoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TienDoDTO.class);
        TienDoDTO tienDoDTO1 = new TienDoDTO();
        tienDoDTO1.setId(1L);
        TienDoDTO tienDoDTO2 = new TienDoDTO();
        assertThat(tienDoDTO1).isNotEqualTo(tienDoDTO2);
        tienDoDTO2.setId(tienDoDTO1.getId());
        assertThat(tienDoDTO1).isEqualTo(tienDoDTO2);
        tienDoDTO2.setId(2L);
        assertThat(tienDoDTO1).isNotEqualTo(tienDoDTO2);
        tienDoDTO1.setId(null);
        assertThat(tienDoDTO1).isNotEqualTo(tienDoDTO2);
    }
}
