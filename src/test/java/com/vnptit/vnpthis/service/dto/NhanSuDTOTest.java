package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class NhanSuDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhanSuDTO.class);
        NhanSuDTO nhanSuDTO1 = new NhanSuDTO();
        nhanSuDTO1.setId(1L);
        NhanSuDTO nhanSuDTO2 = new NhanSuDTO();
        assertThat(nhanSuDTO1).isNotEqualTo(nhanSuDTO2);
        nhanSuDTO2.setId(nhanSuDTO1.getId());
        assertThat(nhanSuDTO1).isEqualTo(nhanSuDTO2);
        nhanSuDTO2.setId(2L);
        assertThat(nhanSuDTO1).isNotEqualTo(nhanSuDTO2);
        nhanSuDTO1.setId(null);
        assertThat(nhanSuDTO1).isNotEqualTo(nhanSuDTO2);
    }
}
