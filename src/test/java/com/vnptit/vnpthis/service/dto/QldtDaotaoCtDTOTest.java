package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDaotaoCtDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDaotaoCtDTO.class);
        QldtDaotaoCtDTO qldtDaotaoCtDTO1 = new QldtDaotaoCtDTO();
        qldtDaotaoCtDTO1.setId(1L);
        QldtDaotaoCtDTO qldtDaotaoCtDTO2 = new QldtDaotaoCtDTO();
        assertThat(qldtDaotaoCtDTO1).isNotEqualTo(qldtDaotaoCtDTO2);
        qldtDaotaoCtDTO2.setId(qldtDaotaoCtDTO1.getId());
        assertThat(qldtDaotaoCtDTO1).isEqualTo(qldtDaotaoCtDTO2);
        qldtDaotaoCtDTO2.setId(2L);
        assertThat(qldtDaotaoCtDTO1).isNotEqualTo(qldtDaotaoCtDTO2);
        qldtDaotaoCtDTO1.setId(null);
        assertThat(qldtDaotaoCtDTO1).isNotEqualTo(qldtDaotaoCtDTO2);
    }
}
