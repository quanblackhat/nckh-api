package com.vnpt.nckh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.nckh.web.rest.TestUtil;

public class OrgOfficerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrgOfficer.class);
        OrgOfficer orgOfficer1 = new OrgOfficer();
        orgOfficer1.setId(1L);
        OrgOfficer orgOfficer2 = new OrgOfficer();
        orgOfficer2.setId(orgOfficer1.getId());
        assertThat(orgOfficer1).isEqualTo(orgOfficer2);
        orgOfficer2.setId(2L);
        assertThat(orgOfficer1).isNotEqualTo(orgOfficer2);
        orgOfficer1.setId(null);
        assertThat(orgOfficer1).isNotEqualTo(orgOfficer2);
    }
}
