package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class DanhMucDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucDTO.class);
        DanhMucDTO danhMucDTO1 = new DanhMucDTO();
        danhMucDTO1.setId(1L);
        DanhMucDTO danhMucDTO2 = new DanhMucDTO();
        assertThat(danhMucDTO1).isNotEqualTo(danhMucDTO2);
        danhMucDTO2.setId(danhMucDTO1.getId());
        assertThat(danhMucDTO1).isEqualTo(danhMucDTO2);
        danhMucDTO2.setId(2L);
        assertThat(danhMucDTO1).isNotEqualTo(danhMucDTO2);
        danhMucDTO1.setId(null);
        assertThat(danhMucDTO1).isNotEqualTo(danhMucDTO2);
    }
}
