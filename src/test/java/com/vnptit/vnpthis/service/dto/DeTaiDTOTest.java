package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class DeTaiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeTaiDTO.class);
        DeTaiDTO deTaiDTO1 = new DeTaiDTO();
        deTaiDTO1.setId(1L);
        DeTaiDTO deTaiDTO2 = new DeTaiDTO();
        assertThat(deTaiDTO1).isNotEqualTo(deTaiDTO2);
        deTaiDTO2.setId(deTaiDTO1.getId());
        assertThat(deTaiDTO1).isEqualTo(deTaiDTO2);
        deTaiDTO2.setId(2L);
        assertThat(deTaiDTO1).isNotEqualTo(deTaiDTO2);
        deTaiDTO1.setId(null);
        assertThat(deTaiDTO1).isNotEqualTo(deTaiDTO2);
    }
}
