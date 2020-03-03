package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.vnptit.vnpthis.domain.qldt.QldtTochucCap;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtTochucCapTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtTochucCap.class);
        QldtTochucCap qldtTochucCap1 = new QldtTochucCap();
        qldtTochucCap1.setId(1L);
        QldtTochucCap qldtTochucCap2 = new QldtTochucCap();
        qldtTochucCap2.setId(qldtTochucCap1.getId());
        assertThat(qldtTochucCap1).isEqualTo(qldtTochucCap2);
        qldtTochucCap2.setId(2L);
        assertThat(qldtTochucCap1).isNotEqualTo(qldtTochucCap2);
        qldtTochucCap1.setId(null);
        assertThat(qldtTochucCap1).isNotEqualTo(qldtTochucCap2);
    }
}
