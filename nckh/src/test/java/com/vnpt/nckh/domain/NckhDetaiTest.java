package com.vnpt.nckh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.nckh.web.rest.TestUtil;

public class NckhDetaiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NckhDetai.class);
        NckhDetai nckhDetai1 = new NckhDetai();
        nckhDetai1.setId(1L);
        NckhDetai nckhDetai2 = new NckhDetai();
        nckhDetai2.setId(nckhDetai1.getId());
        assertThat(nckhDetai1).isEqualTo(nckhDetai2);
        nckhDetai2.setId(2L);
        assertThat(nckhDetai1).isNotEqualTo(nckhDetai2);
        nckhDetai1.setId(null);
        assertThat(nckhDetai1).isNotEqualTo(nckhDetai2);
    }
}
