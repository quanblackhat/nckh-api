package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.vnptit.vnpthis.domain.qldt.QldtDutoanDaotao;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDutoanDaotaoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDutoanDaotao.class);
        QldtDutoanDaotao qldtDutoanDaotao1 = new QldtDutoanDaotao();
        qldtDutoanDaotao1.setId(1L);
        QldtDutoanDaotao qldtDutoanDaotao2 = new QldtDutoanDaotao();
        qldtDutoanDaotao2.setId(qldtDutoanDaotao1.getId());
        assertThat(qldtDutoanDaotao1).isEqualTo(qldtDutoanDaotao2);
        qldtDutoanDaotao2.setId(2L);
        assertThat(qldtDutoanDaotao1).isNotEqualTo(qldtDutoanDaotao2);
        qldtDutoanDaotao1.setId(null);
        assertThat(qldtDutoanDaotao1).isNotEqualTo(qldtDutoanDaotao2);
    }
}
