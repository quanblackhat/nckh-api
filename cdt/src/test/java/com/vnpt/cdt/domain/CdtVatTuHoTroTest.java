package com.vnpt.cdt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.cdt.web.rest.TestUtil;

public class CdtVatTuHoTroTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CdtVatTuHoTro.class);
        CdtVatTuHoTro cdtVatTuHoTro1 = new CdtVatTuHoTro();
        cdtVatTuHoTro1.setId(1L);
        CdtVatTuHoTro cdtVatTuHoTro2 = new CdtVatTuHoTro();
        cdtVatTuHoTro2.setId(cdtVatTuHoTro1.getId());
        assertThat(cdtVatTuHoTro1).isEqualTo(cdtVatTuHoTro2);
        cdtVatTuHoTro2.setId(2L);
        assertThat(cdtVatTuHoTro1).isNotEqualTo(cdtVatTuHoTro2);
        cdtVatTuHoTro1.setId(null);
        assertThat(cdtVatTuHoTro1).isNotEqualTo(cdtVatTuHoTro2);
    }
}
