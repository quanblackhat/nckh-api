package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class DuToanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DuToanDTO.class);
        DuToanDTO duToanDTO1 = new DuToanDTO();
        duToanDTO1.setId(1L);
        DuToanDTO duToanDTO2 = new DuToanDTO();
        assertThat(duToanDTO1).isNotEqualTo(duToanDTO2);
        duToanDTO2.setId(duToanDTO1.getId());
        assertThat(duToanDTO1).isEqualTo(duToanDTO2);
        duToanDTO2.setId(2L);
        assertThat(duToanDTO1).isNotEqualTo(duToanDTO2);
        duToanDTO1.setId(null);
        assertThat(duToanDTO1).isNotEqualTo(duToanDTO2);
    }
}
