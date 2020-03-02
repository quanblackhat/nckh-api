package com.vnptit.vnpthis.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnptit.vnpthis.domain.QldtDaotao} entity.
 */
public class QldtDaotaoDTO implements Serializable {

    private Long id;

    private String madaotao;

    private String tendaotao;

    private LocalDate ngayBd;

    private LocalDate ngayKt;

    private String diachi;

    private String doituongchitiet;

    private String noidungdaotao;

    private Integer thoigiandaotao;

    private Integer sudung;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMadaotao() {
        return madaotao;
    }

    public void setMadaotao(String madaotao) {
        this.madaotao = madaotao;
    }

    public String getTendaotao() {
        return tendaotao;
    }

    public void setTendaotao(String tendaotao) {
        this.tendaotao = tendaotao;
    }

    public LocalDate getNgayBd() {
        return ngayBd;
    }

    public void setNgayBd(LocalDate ngayBd) {
        this.ngayBd = ngayBd;
    }

    public LocalDate getNgayKt() {
        return ngayKt;
    }

    public void setNgayKt(LocalDate ngayKt) {
        this.ngayKt = ngayKt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getDoituongchitiet() {
        return doituongchitiet;
    }

    public void setDoituongchitiet(String doituongchitiet) {
        this.doituongchitiet = doituongchitiet;
    }

    public String getNoidungdaotao() {
        return noidungdaotao;
    }

    public void setNoidungdaotao(String noidungdaotao) {
        this.noidungdaotao = noidungdaotao;
    }

    public Integer getThoigiandaotao() {
        return thoigiandaotao;
    }

    public void setThoigiandaotao(Integer thoigiandaotao) {
        this.thoigiandaotao = thoigiandaotao;
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

        QldtDaotaoDTO qldtDaotaoDTO = (QldtDaotaoDTO) o;
        if (qldtDaotaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qldtDaotaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QldtDaotaoDTO{" +
            "id=" + getId() +
            ", madaotao='" + getMadaotao() + "'" +
            ", tendaotao='" + getTendaotao() + "'" +
            ", ngayBd='" + getNgayBd() + "'" +
            ", ngayKt='" + getNgayKt() + "'" +
            ", diachi='" + getDiachi() + "'" +
            ", doituongchitiet='" + getDoituongchitiet() + "'" +
            ", noidungdaotao='" + getNoidungdaotao() + "'" +
            ", thoigiandaotao=" + getThoigiandaotao() +
            ", sudung=" + getSudung() +
            "}";
    }
}
