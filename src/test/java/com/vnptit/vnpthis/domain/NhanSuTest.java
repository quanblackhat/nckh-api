package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class NhanSuTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhanSu.class);
        NhanSu nhanSu1 = new NhanSu();
        nhanSu1.setId(1L);
        NhanSu nhanSu2 = new NhanSu();
        nhanSu2.setId(nhanSu1.getId());
        assertThat(nhanSu1).isEqualTo(nhanSu2);
        nhanSu2.setId(2L);
        assertThat(nhanSu1).isNotEqualTo(nhanSu2);
        nhanSu1.setId(null);
        assertThat(nhanSu1).isNotEqualTo(nhanSu2);
    }
}
