package com.vnpt.cdt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.cdt.web.rest.TestUtil;

public class CdtNoiDenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CdtNoiDen.class);
        CdtNoiDen cdtNoiDen1 = new CdtNoiDen();
        cdtNoiDen1.setId(1L);
        CdtNoiDen cdtNoiDen2 = new CdtNoiDen();
        cdtNoiDen2.setId(cdtNoiDen1.getId());
        assertThat(cdtNoiDen1).isEqualTo(cdtNoiDen2);
        cdtNoiDen2.setId(2L);
        assertThat(cdtNoiDen1).isNotEqualTo(cdtNoiDen2);
        cdtNoiDen1.setId(null);
        assertThat(cdtNoiDen1).isNotEqualTo(cdtNoiDen2);
    }
}
