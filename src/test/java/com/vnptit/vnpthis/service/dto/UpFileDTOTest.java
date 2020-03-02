package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class UpFileDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UpFileDTO.class);
        UpFileDTO upFileDTO1 = new UpFileDTO();
        upFileDTO1.setId(1L);
        UpFileDTO upFileDTO2 = new UpFileDTO();
        assertThat(upFileDTO1).isNotEqualTo(upFileDTO2);
        upFileDTO2.setId(upFileDTO1.getId());
        assertThat(upFileDTO1).isEqualTo(upFileDTO2);
        upFileDTO2.setId(2L);
        assertThat(upFileDTO1).isNotEqualTo(upFileDTO2);
        upFileDTO1.setId(null);
        assertThat(upFileDTO1).isNotEqualTo(upFileDTO2);
    }
}
