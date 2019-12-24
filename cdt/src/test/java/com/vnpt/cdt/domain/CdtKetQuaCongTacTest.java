package com.vnpt.cdt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.cdt.web.rest.TestUtil;

public class CdtKetQuaCongTacTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CdtKetQuaCongTac.class);
        CdtKetQuaCongTac cdtKetQuaCongTac1 = new CdtKetQuaCongTac();
        cdtKetQuaCongTac1.setId(1L);
        CdtKetQuaCongTac cdtKetQuaCongTac2 = new CdtKetQuaCongTac();
        cdtKetQuaCongTac2.setId(cdtKetQuaCongTac1.getId());
        assertThat(cdtKetQuaCongTac1).isEqualTo(cdtKetQuaCongTac2);
        cdtKetQuaCongTac2.setId(2L);
        assertThat(cdtKetQuaCongTac1).isNotEqualTo(cdtKetQuaCongTac2);
        cdtKetQuaCongTac1.setId(null);
        assertThat(cdtKetQuaCongTac1).isNotEqualTo(cdtKetQuaCongTac2);
    }
}
