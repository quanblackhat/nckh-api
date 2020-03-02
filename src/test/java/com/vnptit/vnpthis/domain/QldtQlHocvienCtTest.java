package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.vnptit.vnpthis.domain.qldt.QldtQlHocvienCt;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtQlHocvienCtTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtQlHocvienCt.class);
        QldtQlHocvienCt qldtQlHocvienCt1 = new QldtQlHocvienCt();
        qldtQlHocvienCt1.setId(1L);
        QldtQlHocvienCt qldtQlHocvienCt2 = new QldtQlHocvienCt();
        qldtQlHocvienCt2.setId(qldtQlHocvienCt1.getId());
        assertThat(qldtQlHocvienCt1).isEqualTo(qldtQlHocvienCt2);
        qldtQlHocvienCt2.setId(2L);
        assertThat(qldtQlHocvienCt1).isNotEqualTo(qldtQlHocvienCt2);
        qldtQlHocvienCt1.setId(null);
        assertThat(qldtQlHocvienCt1).isNotEqualTo(qldtQlHocvienCt2);
    }
}
