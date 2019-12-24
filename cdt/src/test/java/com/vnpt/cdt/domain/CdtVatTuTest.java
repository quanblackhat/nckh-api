package com.vnpt.cdt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.cdt.web.rest.TestUtil;

public class CdtVatTuTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CdtVatTu.class);
        CdtVatTu cdtVatTu1 = new CdtVatTu();
        cdtVatTu1.setId(1L);
        CdtVatTu cdtVatTu2 = new CdtVatTu();
        cdtVatTu2.setId(cdtVatTu1.getId());
        assertThat(cdtVatTu1).isEqualTo(cdtVatTu2);
        cdtVatTu2.setId(2L);
        assertThat(cdtVatTu1).isNotEqualTo(cdtVatTu2);
        cdtVatTu1.setId(null);
        assertThat(cdtVatTu1).isNotEqualTo(cdtVatTu2);
    }
}
