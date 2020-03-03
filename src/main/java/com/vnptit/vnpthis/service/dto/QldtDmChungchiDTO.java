package com.vnptit.vnpthis.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.QldtDmChungchi} entity.
 */
public class QldtDmChungchiDTO implements Serializable {

    private Long id;

    private String machungchi;

    private String tenchungchi;

    private String mota;

    private Integer trangthai;

    private Integer sudung;


    private Long qldtTochucCapId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachungchi() {
        return machungchi;
    }

    public void setMachungchi(String machungchi) {
        this.machungchi = machungchi;
    }

    public String getTenchungchi() {
        return tenchungchi;
    }

    public void setTenchungchi(String tenchungchi) {
        this.tenchungchi = tenchungchi;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Long getQldtTochucCapId() {
        return qldtTochucCapId;
    }

    public void setQldtTochucCapId(Long qldtTochucCapId) {
        this.qldtTochucCapId = qldtTochucCapId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QldtDmChungchiDTO qldtDmChungchiDTO = (QldtDmChungchiDTO) o;
        if (qldtDmChungchiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qldtDmChungchiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QldtDmChungchiDTO{" +
            "id=" + getId() +
            ", machungchi='" + getMachungchi() + "'" +
            ", tenchungchi='" + getTenchungchi() + "'" +
            ", mota='" + getMota() + "'" +
            ", trangthai=" + getTrangthai() +
            ", sudung=" + getSudung() +
            ", qldtTochucCapId=" + getQldtTochucCapId() +
            "}";
    }
}
