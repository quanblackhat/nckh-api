package com.vnpt.nckh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.nckh.web.rest.TestUtil;

public class NckhDanhmucTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NckhDanhmuc.class);
        NckhDanhmuc nckhDanhmuc1 = new NckhDanhmuc();
        nckhDanhmuc1.setId(1L);
        NckhDanhmuc nckhDanhmuc2 = new NckhDanhmuc();
        nckhDanhmuc2.setId(nckhDanhmuc1.getId());
        assertThat(nckhDanhmuc1).isEqualTo(nckhDanhmuc2);
        nckhDanhmuc2.setId(2L);
        assertThat(nckhDanhmuc1).isNotEqualTo(nckhDanhmuc2);
        nckhDanhmuc1.setId(null);
        assertThat(nckhDanhmuc1).isNotEqualTo(nckhDanhmuc2);
    }
}
