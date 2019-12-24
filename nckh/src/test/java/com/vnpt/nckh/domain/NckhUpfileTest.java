package com.vnpt.nckh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.nckh.web.rest.TestUtil;

public class NckhUpfileTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NckhUpfile.class);
        NckhUpfile nckhUpfile1 = new NckhUpfile();
        nckhUpfile1.setId(1L);
        NckhUpfile nckhUpfile2 = new NckhUpfile();
        nckhUpfile2.setId(nckhUpfile1.getId());
        assertThat(nckhUpfile1).isEqualTo(nckhUpfile2);
        nckhUpfile2.setId(2L);
        assertThat(nckhUpfile1).isNotEqualTo(nckhUpfile2);
        nckhUpfile1.setId(null);
        assertThat(nckhUpfile1).isNotEqualTo(nckhUpfile2);
    }
}
