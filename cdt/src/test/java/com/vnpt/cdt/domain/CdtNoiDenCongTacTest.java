package com.vnpt.cdt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.cdt.web.rest.TestUtil;

public class CdtNoiDenCongTacTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CdtNoiDenCongTac.class);
        CdtNoiDenCongTac cdtNoiDenCongTac1 = new CdtNoiDenCongTac();
        cdtNoiDenCongTac1.setId(1L);
        CdtNoiDenCongTac cdtNoiDenCongTac2 = new CdtNoiDenCongTac();
        cdtNoiDenCongTac2.setId(cdtNoiDenCongTac1.getId());
        assertThat(cdtNoiDenCongTac1).isEqualTo(cdtNoiDenCongTac2);
        cdtNoiDenCongTac2.setId(2L);
        assertThat(cdtNoiDenCongTac1).isNotEqualTo(cdtNoiDenCongTac2);
        cdtNoiDenCongTac1.setId(null);
        assertThat(cdtNoiDenCongTac1).isNotEqualTo(cdtNoiDenCongTac2);
    }
}
