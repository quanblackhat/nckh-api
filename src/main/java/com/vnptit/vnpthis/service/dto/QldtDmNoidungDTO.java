package com.vnptit.vnpthis.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.QldtDmNoidung} entity.
 */
public class QldtDmNoidungDTO implements Serializable {

    private Long id;

    private String noidung;

    private Integer sudung;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        QldtDmNoidungDTO qldtDmNoidungDTO = (QldtDmNoidungDTO) o;
        if (qldtDmNoidungDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qldtDmNoidungDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QldtDmNoidungDTO{" +
            "id=" + getId() +
            ", noidung='" + getNoidung() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
