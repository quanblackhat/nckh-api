package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.vnptit.vnpthis.domain.qldt.QldtQlHocvien;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtQlHocvienTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtQlHocvien.class);
        QldtQlHocvien qldtQlHocvien1 = new QldtQlHocvien();
        qldtQlHocvien1.setId(1L);
        QldtQlHocvien qldtQlHocvien2 = new QldtQlHocvien();
        qldtQlHocvien2.setId(qldtQlHocvien1.getId());
        assertThat(qldtQlHocvien1).isEqualTo(qldtQlHocvien2);
        qldtQlHocvien2.setId(2L);
        assertThat(qldtQlHocvien1).isNotEqualTo(qldtQlHocvien2);
        qldtQlHocvien1.setId(null);
        assertThat(qldtQlHocvien1).isNotEqualTo(qldtQlHocvien2);
    }
}
