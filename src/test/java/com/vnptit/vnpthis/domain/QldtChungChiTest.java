package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.vnptit.vnpthis.domain.qldt.QldtChungChi;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtChungChiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtChungChi.class);
        QldtChungChi qldtChungChi1 = new QldtChungChi();
        qldtChungChi1.setId(1L);
        QldtChungChi qldtChungChi2 = new QldtChungChi();
        qldtChungChi2.setId(qldtChungChi1.getId());
        assertThat(qldtChungChi1).isEqualTo(qldtChungChi2);
        qldtChungChi2.setId(2L);
        assertThat(qldtChungChi1).isNotEqualTo(qldtChungChi2);
        qldtChungChi1.setId(null);
        assertThat(qldtChungChi1).isNotEqualTo(qldtChungChi2);
    }
}
