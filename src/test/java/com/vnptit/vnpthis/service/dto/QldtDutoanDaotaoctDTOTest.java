package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDutoanDaotaoctDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDutoanDaotaoctDTO.class);
        QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO1 = new QldtDutoanDaotaoctDTO();
        qldtDutoanDaotaoctDTO1.setId(1L);
        QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO2 = new QldtDutoanDaotaoctDTO();
        assertThat(qldtDutoanDaotaoctDTO1).isNotEqualTo(qldtDutoanDaotaoctDTO2);
        qldtDutoanDaotaoctDTO2.setId(qldtDutoanDaotaoctDTO1.getId());
        assertThat(qldtDutoanDaotaoctDTO1).isEqualTo(qldtDutoanDaotaoctDTO2);
        qldtDutoanDaotaoctDTO2.setId(2L);
        assertThat(qldtDutoanDaotaoctDTO1).isNotEqualTo(qldtDutoanDaotaoctDTO2);
        qldtDutoanDaotaoctDTO1.setId(null);
        assertThat(qldtDutoanDaotaoctDTO1).isNotEqualTo(qldtDutoanDaotaoctDTO2);
    }
}
