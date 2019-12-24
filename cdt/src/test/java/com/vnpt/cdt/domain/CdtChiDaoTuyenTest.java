package com.vnpt.cdt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.cdt.web.rest.TestUtil;

public class CdtChiDaoTuyenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CdtChiDaoTuyen.class);
        CdtChiDaoTuyen cdtChiDaoTuyen1 = new CdtChiDaoTuyen();
        cdtChiDaoTuyen1.setId(1L);
        CdtChiDaoTuyen cdtChiDaoTuyen2 = new CdtChiDaoTuyen();
        cdtChiDaoTuyen2.setId(cdtChiDaoTuyen1.getId());
        assertThat(cdtChiDaoTuyen1).isEqualTo(cdtChiDaoTuyen2);
        cdtChiDaoTuyen2.setId(2L);
        assertThat(cdtChiDaoTuyen1).isNotEqualTo(cdtChiDaoTuyen2);
        cdtChiDaoTuyen1.setId(null);
        assertThat(cdtChiDaoTuyen1).isNotEqualTo(cdtChiDaoTuyen2);
    }
}
