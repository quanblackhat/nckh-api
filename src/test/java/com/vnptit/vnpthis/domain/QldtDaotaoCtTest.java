package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.vnptit.vnpthis.domain.qldt.QldtDaotaoCt;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDaotaoCtTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDaotaoCt.class);
        QldtDaotaoCt qldtDaotaoCt1 = new QldtDaotaoCt();
        qldtDaotaoCt1.setId(1L);
        QldtDaotaoCt qldtDaotaoCt2 = new QldtDaotaoCt();
        qldtDaotaoCt2.setId(qldtDaotaoCt1.getId());
        assertThat(qldtDaotaoCt1).isEqualTo(qldtDaotaoCt2);
        qldtDaotaoCt2.setId(2L);
        assertThat(qldtDaotaoCt1).isNotEqualTo(qldtDaotaoCt2);
        qldtDaotaoCt1.setId(null);
        assertThat(qldtDaotaoCt1).isNotEqualTo(qldtDaotaoCt2);
    }
}
