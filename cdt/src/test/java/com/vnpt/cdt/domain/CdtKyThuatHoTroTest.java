package com.vnpt.cdt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.cdt.web.rest.TestUtil;

public class CdtKyThuatHoTroTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CdtKyThuatHoTro.class);
        CdtKyThuatHoTro cdtKyThuatHoTro1 = new CdtKyThuatHoTro();
        cdtKyThuatHoTro1.setId(1L);
        CdtKyThuatHoTro cdtKyThuatHoTro2 = new CdtKyThuatHoTro();
        cdtKyThuatHoTro2.setId(cdtKyThuatHoTro1.getId());
        assertThat(cdtKyThuatHoTro1).isEqualTo(cdtKyThuatHoTro2);
        cdtKyThuatHoTro2.setId(2L);
        assertThat(cdtKyThuatHoTro1).isNotEqualTo(cdtKyThuatHoTro2);
        cdtKyThuatHoTro1.setId(null);
        assertThat(cdtKyThuatHoTro1).isNotEqualTo(cdtKyThuatHoTro2);
    }
}
