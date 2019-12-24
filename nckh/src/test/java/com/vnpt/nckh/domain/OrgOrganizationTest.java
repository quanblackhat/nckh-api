package com.vnpt.nckh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnpt.nckh.web.rest.TestUtil;

public class OrgOrganizationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrgOrganization.class);
        OrgOrganization orgOrganization1 = new OrgOrganization();
        orgOrganization1.setId(1L);
        OrgOrganization orgOrganization2 = new OrgOrganization();
        orgOrganization2.setId(orgOrganization1.getId());
        assertThat(orgOrganization1).isEqualTo(orgOrganization2);
        orgOrganization2.setId(2L);
        assertThat(orgOrganization1).isNotEqualTo(orgOrganization2);
        orgOrganization1.setId(null);
        assertThat(orgOrganization1).isNotEqualTo(orgOrganization2);
    }
}
