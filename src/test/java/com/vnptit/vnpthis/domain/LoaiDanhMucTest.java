package com.vnptit.vnpthis.domain;

import com.vnptit.vnpthis.domain.nckh.LoaiDanhMuc;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class LoaiDanhMucTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoaiDanhMuc.class);
        LoaiDanhMuc loaiDanhMuc1 = new LoaiDanhMuc();
        loaiDanhMuc1.setId(1L);
        LoaiDanhMuc loaiDanhMuc2 = new LoaiDanhMuc();
        loaiDanhMuc2.setId(loaiDanhMuc1.getId());
        assertThat(loaiDanhMuc1).isEqualTo(loaiDanhMuc2);
        loaiDanhMuc2.setId(2L);
        assertThat(loaiDanhMuc1).isNotEqualTo(loaiDanhMuc2);
        loaiDanhMuc1.setId(null);
        assertThat(loaiDanhMuc1).isNotEqualTo(loaiDanhMuc2);
    }
}
