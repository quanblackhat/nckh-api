package com.vnpt.nckh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.nckh.web.rest.TestUtil;

public class NckhNhanSuTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NckhNhanSu.class);
        NckhNhanSu nckhNhanSu1 = new NckhNhanSu();
        nckhNhanSu1.setId(1L);
        NckhNhanSu nckhNhanSu2 = new NckhNhanSu();
        nckhNhanSu2.setId(nckhNhanSu1.getId());
        assertThat(nckhNhanSu1).isEqualTo(nckhNhanSu2);
        nckhNhanSu2.setId(2L);
        assertThat(nckhNhanSu1).isNotEqualTo(nckhNhanSu2);
        nckhNhanSu1.setId(null);
        assertThat(nckhNhanSu1).isNotEqualTo(nckhNhanSu2);
    }
}
