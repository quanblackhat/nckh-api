package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtTochucCapDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtTochucCapDTO.class);
        QldtTochucCapDTO qldtTochucCapDTO1 = new QldtTochucCapDTO();
        qldtTochucCapDTO1.setId(1L);
        QldtTochucCapDTO qldtTochucCapDTO2 = new QldtTochucCapDTO();
        assertThat(qldtTochucCapDTO1).isNotEqualTo(qldtTochucCapDTO2);
        qldtTochucCapDTO2.setId(qldtTochucCapDTO1.getId());
        assertThat(qldtTochucCapDTO1).isEqualTo(qldtTochucCapDTO2);
        qldtTochucCapDTO2.setId(2L);
        assertThat(qldtTochucCapDTO1).isNotEqualTo(qldtTochucCapDTO2);
        qldtTochucCapDTO1.setId(null);
        assertThat(qldtTochucCapDTO1).isNotEqualTo(qldtTochucCapDTO2);
    }
}
