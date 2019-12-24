package com.vnpt.nckh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.nckh.web.rest.TestUtil;

public class NckhTiendoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NckhTiendo.class);
        NckhTiendo nckhTiendo1 = new NckhTiendo();
        nckhTiendo1.setId(1L);
        NckhTiendo nckhTiendo2 = new NckhTiendo();
        nckhTiendo2.setId(nckhTiendo1.getId());
        assertThat(nckhTiendo1).isEqualTo(nckhTiendo2);
        nckhTiendo2.setId(2L);
        assertThat(nckhTiendo1).isNotEqualTo(nckhTiendo2);
        nckhTiendo1.setId(null);
        assertThat(nckhTiendo1).isNotEqualTo(nckhTiendo2);
    }
}
