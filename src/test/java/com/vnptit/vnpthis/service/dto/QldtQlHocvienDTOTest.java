package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtQlHocvienDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtQlHocvienDTO.class);
        QldtQlHocvienDTO qldtQlHocvienDTO1 = new QldtQlHocvienDTO();
        qldtQlHocvienDTO1.setId(1L);
        QldtQlHocvienDTO qldtQlHocvienDTO2 = new QldtQlHocvienDTO();
        assertThat(qldtQlHocvienDTO1).isNotEqualTo(qldtQlHocvienDTO2);
        qldtQlHocvienDTO2.setId(qldtQlHocvienDTO1.getId());
        assertThat(qldtQlHocvienDTO1).isEqualTo(qldtQlHocvienDTO2);
        qldtQlHocvienDTO2.setId(2L);
        assertThat(qldtQlHocvienDTO1).isNotEqualTo(qldtQlHocvienDTO2);
        qldtQlHocvienDTO1.setId(null);
        assertThat(qldtQlHocvienDTO1).isNotEqualTo(qldtQlHocvienDTO2);
    }
}
