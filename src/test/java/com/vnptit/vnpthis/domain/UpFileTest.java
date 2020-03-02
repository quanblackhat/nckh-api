package com.vnptit.vnpthis.domain;

import com.vnptit.vnpthis.domain.nckh.UpFile;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class UpFileTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UpFile.class);
        UpFile upFile1 = new UpFile();
        upFile1.setId(1L);
        UpFile upFile2 = new UpFile();
        upFile2.setId(upFile1.getId());
        assertThat(upFile1).isEqualTo(upFile2);
        upFile2.setId(2L);
        assertThat(upFile1).isNotEqualTo(upFile2);
        upFile1.setId(null);
        assertThat(upFile1).isNotEqualTo(upFile2);
    }
}
