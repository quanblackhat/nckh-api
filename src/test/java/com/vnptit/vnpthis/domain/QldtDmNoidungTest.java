package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.vnptit.vnpthis.domain.qldt.QldtDmNoidung;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDmNoidungTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDmNoidung.class);
        QldtDmNoidung qldtDmNoidung1 = new QldtDmNoidung();
        qldtDmNoidung1.setId(1L);
        QldtDmNoidung qldtDmNoidung2 = new QldtDmNoidung();
        qldtDmNoidung2.setId(qldtDmNoidung1.getId());
        assertThat(qldtDmNoidung1).isEqualTo(qldtDmNoidung2);
        qldtDmNoidung2.setId(2L);
        assertThat(qldtDmNoidung1).isNotEqualTo(qldtDmNoidung2);
        qldtDmNoidung1.setId(null);
        assertThat(qldtDmNoidung1).isNotEqualTo(qldtDmNoidung2);
    }
}
