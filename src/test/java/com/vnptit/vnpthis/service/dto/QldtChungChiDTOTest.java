package com.vnptit.vnpthis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class QldtChungChiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QldtChungChiDTO.class);
        QldtChungChiDTO qldtChungChiDTO1 = new QldtChungChiDTO();
        qldtChungChiDTO1.setId(1L);
        QldtChungChiDTO qldtChungChiDTO2 = new QldtChungChiDTO();
        assertThat(qldtChungChiDTO1).isNotEqualTo(qldtChungChiDTO2);
        qldtChungChiDTO2.setId(qldtChungChiDTO1.getId());
        assertThat(qldtChungChiDTO1).isEqualTo(qldtChungChiDTO2);
        qldtChungChiDTO2.setId(2L);
        assertThat(qldtChungChiDTO1).isNotEqualTo(qldtChungChiDTO2);
        qldtChungChiDTO1.setId(null);
        assertThat(qldtChungChiDTO1).isNotEqualTo(qldtChungChiDTO2);
    }
}
