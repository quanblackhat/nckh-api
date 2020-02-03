package com.vnptit.vnpthis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vnptit.vnpthis.web.rest.TestUtil;

public class DataSourceConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataSourceConfig.class);
        DataSourceConfig dataSourceConfig1 = new DataSourceConfig();
        dataSourceConfig1.setId(1L);
        DataSourceConfig dataSourceConfig2 = new DataSourceConfig();
        dataSourceConfig2.setId(dataSourceConfig1.getId());
        assertThat(dataSourceConfig1).isEqualTo(dataSourceConfig2);
        dataSourceConfig2.setId(2L);
        assertThat(dataSourceConfig1).isNotEqualTo(dataSourceConfig2);
        dataSourceConfig1.setId(null);
        assertThat(dataSourceConfig1).isNotEqualTo(dataSourceConfig2);
    }
}
