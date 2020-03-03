package com.vnptit.vnpthis.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.QldtTochucCap} entity.
 */
public class QldtTochucCapDTO implements Serializable {

    private Long id;

    private String matochuc;

    private String tentochuc;

    private String noidung;

    private Integer sudung;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatochuc() {
        return matochuc;
    }

    public void setMatochuc(String matochuc) {
        this.matochuc = matochuc;
    }

    public String getTentochuc() {
        return tentochuc;
    }

    public void setTentochuc(String tentochuc) {
        this.tentochuc = tentochuc;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QldtTochucCapDTO qldtTochucCapDTO = (QldtTochucCapDTO) o;
        if (qldtTochucCapDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qldtTochucCapDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QldtTochucCapDTO{" +
            "id=" + getId() +
            ", matochuc='" + getMatochuc() + "'" +
            ", tentochuc='" + getTentochuc() + "'" +
            ", noidung='" + getNoidung() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
