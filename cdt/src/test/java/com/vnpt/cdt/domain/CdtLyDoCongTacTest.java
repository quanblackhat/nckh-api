package com.vnpt.cdt.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.cdt.web.rest.TestUtil;

public class CdtLyDoCongTacTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CdtLyDoCongTac.class);
        CdtLyDoCongTac cdtLyDoCongTac1 = new CdtLyDoCongTac();
        cdtLyDoCongTac1.setId(1L);
        CdtLyDoCongTac cdtLyDoCongTac2 = new CdtLyDoCongTac();
        cdtLyDoCongTac2.setId(cdtLyDoCongTac1.getId());
        assertThat(cdtLyDoCongTac1).isEqualTo(cdtLyDoCongTac2);
        cdtLyDoCongTac2.setId(2L);
        assertThat(cdtLyDoCongTac1).isNotEqualTo(cdtLyDoCongTac2);
        cdtLyDoCongTac1.setId(null);
        assertThat(cdtLyDoCongTac1).isNotEqualTo(cdtLyDoCongTac2);
    }
}
