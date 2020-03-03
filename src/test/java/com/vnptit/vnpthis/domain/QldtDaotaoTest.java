package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.vnptit.vnpthis.domain.qldt.QldtDaotao;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDaotaoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDaotao.class);
        QldtDaotao qldtDaotao1 = new QldtDaotao();
        qldtDaotao1.setId(1L);
        QldtDaotao qldtDaotao2 = new QldtDaotao();
        qldtDaotao2.setId(qldtDaotao1.getId());
        assertThat(qldtDaotao1).isEqualTo(qldtDaotao2);
        qldtDaotao2.setId(2L);
        assertThat(qldtDaotao1).isNotEqualTo(qldtDaotao2);
        qldtDaotao1.setId(null);
        assertThat(qldtDaotao1).isNotEqualTo(qldtDaotao2);
    }
}
