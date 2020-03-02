package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class DanhGiaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhGiaDTO.class);
        DanhGiaDTO danhGiaDTO1 = new DanhGiaDTO();
        danhGiaDTO1.setId(1L);
        DanhGiaDTO danhGiaDTO2 = new DanhGiaDTO();
        assertThat(danhGiaDTO1).isNotEqualTo(danhGiaDTO2);
        danhGiaDTO2.setId(danhGiaDTO1.getId());
        assertThat(danhGiaDTO1).isEqualTo(danhGiaDTO2);
        danhGiaDTO2.setId(2L);
        assertThat(danhGiaDTO1).isNotEqualTo(danhGiaDTO2);
        danhGiaDTO1.setId(null);
        assertThat(danhGiaDTO1).isNotEqualTo(danhGiaDTO2);
    }
}
