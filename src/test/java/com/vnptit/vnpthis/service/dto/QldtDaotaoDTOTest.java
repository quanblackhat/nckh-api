package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDaotaoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDaotaoDTO.class);
        QldtDaotaoDTO qldtDaotaoDTO1 = new QldtDaotaoDTO();
        qldtDaotaoDTO1.setId(1L);
        QldtDaotaoDTO qldtDaotaoDTO2 = new QldtDaotaoDTO();
        assertThat(qldtDaotaoDTO1).isNotEqualTo(qldtDaotaoDTO2);
        qldtDaotaoDTO2.setId(qldtDaotaoDTO1.getId());
        assertThat(qldtDaotaoDTO1).isEqualTo(qldtDaotaoDTO2);
        qldtDaotaoDTO2.setId(2L);
        assertThat(qldtDaotaoDTO1).isNotEqualTo(qldtDaotaoDTO2);
        qldtDaotaoDTO1.setId(null);
        assertThat(qldtDaotaoDTO1).isNotEqualTo(qldtDaotaoDTO2);
    }
}
