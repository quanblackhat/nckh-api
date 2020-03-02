package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDmChungchiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDmChungchiDTO.class);
        QldtDmChungchiDTO qldtDmChungchiDTO1 = new QldtDmChungchiDTO();
        qldtDmChungchiDTO1.setId(1L);
        QldtDmChungchiDTO qldtDmChungchiDTO2 = new QldtDmChungchiDTO();
        assertThat(qldtDmChungchiDTO1).isNotEqualTo(qldtDmChungchiDTO2);
        qldtDmChungchiDTO2.setId(qldtDmChungchiDTO1.getId());
        assertThat(qldtDmChungchiDTO1).isEqualTo(qldtDmChungchiDTO2);
        qldtDmChungchiDTO2.setId(2L);
        assertThat(qldtDmChungchiDTO1).isNotEqualTo(qldtDmChungchiDTO2);
        qldtDmChungchiDTO1.setId(null);
        assertThat(qldtDmChungchiDTO1).isNotEqualTo(qldtDmChungchiDTO2);
    }
}
