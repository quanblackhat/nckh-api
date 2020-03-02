package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDmNoidungDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDmNoidungDTO.class);
        QldtDmNoidungDTO qldtDmNoidungDTO1 = new QldtDmNoidungDTO();
        qldtDmNoidungDTO1.setId(1L);
        QldtDmNoidungDTO qldtDmNoidungDTO2 = new QldtDmNoidungDTO();
        assertThat(qldtDmNoidungDTO1).isNotEqualTo(qldtDmNoidungDTO2);
        qldtDmNoidungDTO2.setId(qldtDmNoidungDTO1.getId());
        assertThat(qldtDmNoidungDTO1).isEqualTo(qldtDmNoidungDTO2);
        qldtDmNoidungDTO2.setId(2L);
        assertThat(qldtDmNoidungDTO1).isNotEqualTo(qldtDmNoidungDTO2);
        qldtDmNoidungDTO1.setId(null);
        assertThat(qldtDmNoidungDTO1).isNotEqualTo(qldtDmNoidungDTO2);
    }
}
