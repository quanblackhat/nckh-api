package com.vnptit.vnpthis.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DataSourceConfig.
 */
@Entity
@Table(name = "data_source_config")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DataSourceConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "db_name", nullable = false)
    private String dbName;

    @NotNull
    @Column(name = "driver_class_name", nullable = false)
    private String driverClassName;

    @NotNull
    @Column(name = "db_url", nullable = false)
    private String dbUrl;

    @NotNull
    @Column(name = "db_username", nullable = false)
    private String dbUsername;

    @NotNull
    @Column(name = "db_password", nullable = false)
    private String dbPassword;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDbName() {
        return dbName;
    }

    public DataSourceConfig dbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public DataSourceConfig driverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public DataSourceConfig dbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
        return this;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public DataSourceConfig dbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
        return this;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public DataSourceConfig dbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
        return this;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataSourceConfig)) {
            return false;
        }
        return id != null && id.equals(((DataSourceConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DataSourceConfig{" +
            "id=" + getId() +
            ", dbName='" + getDbName() + "'" +
            ", driverClassName='" + getDriverClassName() + "'" +
            ", dbUrl='" + getDbUrl() + "'" +
            ", dbUsername='" + getDbUsername() + "'" +
            ", dbPassword='" + getDbPassword() + "'" +
            "}";
    }
}
