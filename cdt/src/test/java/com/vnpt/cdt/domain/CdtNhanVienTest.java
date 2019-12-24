package com.vnpt.cdt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.cdt.web.rest.TestUtil;

public class CdtNhanVienTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CdtNhanVien.class);
        CdtNhanVien cdtNhanVien1 = new CdtNhanVien();
        cdtNhanVien1.setId(1L);
        CdtNhanVien cdtNhanVien2 = new CdtNhanVien();
        cdtNhanVien2.setId(cdtNhanVien1.getId());
        assertThat(cdtNhanVien1).isEqualTo(cdtNhanVien2);
        cdtNhanVien2.setId(2L);
        assertThat(cdtNhanVien1).isNotEqualTo(cdtNhanVien2);
        cdtNhanVien1.setId(null);
        assertThat(cdtNhanVien1).isNotEqualTo(cdtNhanVien2);
    }
}
