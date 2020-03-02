package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.vnptit.vnpthis.domain.qldt.QldtDmNguoidung;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDmNguoidungTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDmNguoidung.class);
        QldtDmNguoidung qldtDmNguoidung1 = new QldtDmNguoidung();
        qldtDmNguoidung1.setId(1L);
        QldtDmNguoidung qldtDmNguoidung2 = new QldtDmNguoidung();
        qldtDmNguoidung2.setId(qldtDmNguoidung1.getId());
        assertThat(qldtDmNguoidung1).isEqualTo(qldtDmNguoidung2);
        qldtDmNguoidung2.setId(2L);
        assertThat(qldtDmNguoidung1).isNotEqualTo(qldtDmNguoidung2);
        qldtDmNguoidung1.setId(null);
        assertThat(qldtDmNguoidung1).isNotEqualTo(qldtDmNguoidung2);
    }
}
