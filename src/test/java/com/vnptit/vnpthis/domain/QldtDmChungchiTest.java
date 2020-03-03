package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.vnptit.vnpthis.domain.qldt.QldtDmChungchi;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDmChungchiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDmChungchi.class);
        QldtDmChungchi qldtDmChungchi1 = new QldtDmChungchi();
        qldtDmChungchi1.setId(1L);
        QldtDmChungchi qldtDmChungchi2 = new QldtDmChungchi();
        qldtDmChungchi2.setId(qldtDmChungchi1.getId());
        assertThat(qldtDmChungchi1).isEqualTo(qldtDmChungchi2);
        qldtDmChungchi2.setId(2L);
        assertThat(qldtDmChungchi1).isNotEqualTo(qldtDmChungchi2);
        qldtDmChungchi1.setId(null);
        assertThat(qldtDmChungchi1).isNotEqualTo(qldtDmChungchi2);
    }
}
