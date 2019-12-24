package com.vnpt.cdt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.cdt.web.rest.TestUtil;

public class CdtTaiChinhTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CdtTaiChinh.class);
        CdtTaiChinh cdtTaiChinh1 = new CdtTaiChinh();
        cdtTaiChinh1.setId(1L);
        CdtTaiChinh cdtTaiChinh2 = new CdtTaiChinh();
        cdtTaiChinh2.setId(cdtTaiChinh1.getId());
        assertThat(cdtTaiChinh1).isEqualTo(cdtTaiChinh2);
        cdtTaiChinh2.setId(2L);
        assertThat(cdtTaiChinh1).isNotEqualTo(cdtTaiChinh2);
        cdtTaiChinh1.setId(null);
        assertThat(cdtTaiChinh1).isNotEqualTo(cdtTaiChinh2);
    }
}
