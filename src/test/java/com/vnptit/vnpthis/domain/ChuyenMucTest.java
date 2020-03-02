package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class ChuyenMucTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChuyenMuc.class);
        ChuyenMuc chuyenMuc1 = new ChuyenMuc();
        chuyenMuc1.setId(1L);
        ChuyenMuc chuyenMuc2 = new ChuyenMuc();
        chuyenMuc2.setId(chuyenMuc1.getId());
        assertThat(chuyenMuc1).isEqualTo(chuyenMuc2);
        chuyenMuc2.setId(2L);
        assertThat(chuyenMuc1).isNotEqualTo(chuyenMuc2);
        chuyenMuc1.setId(null);
        assertThat(chuyenMuc1).isNotEqualTo(chuyenMuc2);
    }
}
