package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class ChuyenMucDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChuyenMucDTO.class);
        ChuyenMucDTO chuyenMucDTO1 = new ChuyenMucDTO();
        chuyenMucDTO1.setId(1L);
        ChuyenMucDTO chuyenMucDTO2 = new ChuyenMucDTO();
        assertThat(chuyenMucDTO1).isNotEqualTo(chuyenMucDTO2);
        chuyenMucDTO2.setId(chuyenMucDTO1.getId());
        assertThat(chuyenMucDTO1).isEqualTo(chuyenMucDTO2);
        chuyenMucDTO2.setId(2L);
        assertThat(chuyenMucDTO1).isNotEqualTo(chuyenMucDTO2);
        chuyenMucDTO1.setId(null);
        assertThat(chuyenMucDTO1).isNotEqualTo(chuyenMucDTO2);
    }
}
