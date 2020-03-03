package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtQlHocvienCtDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtQlHocvienCtDTO.class);
        QldtQlHocvienCtDTO qldtQlHocvienCtDTO1 = new QldtQlHocvienCtDTO();
        qldtQlHocvienCtDTO1.setId(1L);
        QldtQlHocvienCtDTO qldtQlHocvienCtDTO2 = new QldtQlHocvienCtDTO();
        assertThat(qldtQlHocvienCtDTO1).isNotEqualTo(qldtQlHocvienCtDTO2);
        qldtQlHocvienCtDTO2.setId(qldtQlHocvienCtDTO1.getId());
        assertThat(qldtQlHocvienCtDTO1).isEqualTo(qldtQlHocvienCtDTO2);
        qldtQlHocvienCtDTO2.setId(2L);
        assertThat(qldtQlHocvienCtDTO1).isNotEqualTo(qldtQlHocvienCtDTO2);
        qldtQlHocvienCtDTO1.setId(null);
        assertThat(qldtQlHocvienCtDTO1).isNotEqualTo(qldtQlHocvienCtDTO2);
    }
}
