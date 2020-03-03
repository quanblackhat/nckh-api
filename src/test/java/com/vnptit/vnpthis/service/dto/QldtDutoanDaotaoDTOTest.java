package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDutoanDaotaoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDutoanDaotaoDTO.class);
        QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO1 = new QldtDutoanDaotaoDTO();
        qldtDutoanDaotaoDTO1.setId(1L);
        QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO2 = new QldtDutoanDaotaoDTO();
        assertThat(qldtDutoanDaotaoDTO1).isNotEqualTo(qldtDutoanDaotaoDTO2);
        qldtDutoanDaotaoDTO2.setId(qldtDutoanDaotaoDTO1.getId());
        assertThat(qldtDutoanDaotaoDTO1).isEqualTo(qldtDutoanDaotaoDTO2);
        qldtDutoanDaotaoDTO2.setId(2L);
        assertThat(qldtDutoanDaotaoDTO1).isNotEqualTo(qldtDutoanDaotaoDTO2);
        qldtDutoanDaotaoDTO1.setId(null);
        assertThat(qldtDutoanDaotaoDTO1).isNotEqualTo(qldtDutoanDaotaoDTO2);
    }
}
