package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotaoct;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDutoanDaotaoctTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDutoanDaotaoct.class);
        QldtDutoanDaotaoct qldtDutoanDaotaoct1 = new QldtDutoanDaotaoct();
        qldtDutoanDaotaoct1.setId(1L);
        QldtDutoanDaotaoct qldtDutoanDaotaoct2 = new QldtDutoanDaotaoct();
        qldtDutoanDaotaoct2.setId(qldtDutoanDaotaoct1.getId());
        assertThat(qldtDutoanDaotaoct1).isEqualTo(qldtDutoanDaotaoct2);
        qldtDutoanDaotaoct2.setId(2L);
        assertThat(qldtDutoanDaotaoct1).isNotEqualTo(qldtDutoanDaotaoct2);
        qldtDutoanDaotaoct1.setId(null);
        assertThat(qldtDutoanDaotaoct1).isNotEqualTo(qldtDutoanDaotaoct2);
    }
}
