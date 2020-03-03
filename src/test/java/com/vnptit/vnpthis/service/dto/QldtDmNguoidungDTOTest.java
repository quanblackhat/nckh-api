package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtDmNguoidungDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtDmNguoidungDTO.class);
        QldtDmNguoidungDTO qldtDmNguoidungDTO1 = new QldtDmNguoidungDTO();
        qldtDmNguoidungDTO1.setId(1L);
        QldtDmNguoidungDTO qldtDmNguoidungDTO2 = new QldtDmNguoidungDTO();
        assertThat(qldtDmNguoidungDTO1).isNotEqualTo(qldtDmNguoidungDTO2);
        qldtDmNguoidungDTO2.setId(qldtDmNguoidungDTO1.getId());
        assertThat(qldtDmNguoidungDTO1).isEqualTo(qldtDmNguoidungDTO2);
        qldtDmNguoidungDTO2.setId(2L);
        assertThat(qldtDmNguoidungDTO1).isNotEqualTo(qldtDmNguoidungDTO2);
        qldtDmNguoidungDTO1.setId(null);
        assertThat(qldtDmNguoidungDTO1).isNotEqualTo(qldtDmNguoidungDTO2);
    }
}
