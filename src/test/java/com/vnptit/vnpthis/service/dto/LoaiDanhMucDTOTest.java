package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class LoaiDanhMucDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiDanhMucDTO.class);
        LoaiDanhMucDTO loaiDanhMucDTO1 = new LoaiDanhMucDTO();
        loaiDanhMucDTO1.setId(1L);
        LoaiDanhMucDTO loaiDanhMucDTO2 = new LoaiDanhMucDTO();
        assertThat(loaiDanhMucDTO1).isNotEqualTo(loaiDanhMucDTO2);
        loaiDanhMucDTO2.setId(loaiDanhMucDTO1.getId());
        assertThat(loaiDanhMucDTO1).isEqualTo(loaiDanhMucDTO2);
        loaiDanhMucDTO2.setId(2L);
        assertThat(loaiDanhMucDTO1).isNotEqualTo(loaiDanhMucDTO2);
        loaiDanhMucDTO1.setId(null);
        assertThat(loaiDanhMucDTO1).isNotEqualTo(loaiDanhMucDTO2);
    }
}
