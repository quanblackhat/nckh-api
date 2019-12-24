package com.vnpt.cdt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.cdt.web.rest.TestUtil;

public class CdtKyThuatTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CdtKyThuat.class);
        CdtKyThuat cdtKyThuat1 = new CdtKyThuat();
        cdtKyThuat1.setId(1L);
        CdtKyThuat cdtKyThuat2 = new CdtKyThuat();
        cdtKyThuat2.setId(cdtKyThuat1.getId());
        assertThat(cdtKyThuat1).isEqualTo(cdtKyThuat2);
        cdtKyThuat2.setId(2L);
        assertThat(cdtKyThuat1).isNotEqualTo(cdtKyThuat2);
        cdtKyThuat1.setId(null);
        assertThat(cdtKyThuat1).isNotEqualTo(cdtKyThuat2);
    }
}
