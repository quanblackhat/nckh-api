package com.vnptit.vnpthis.domain.qldt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A QldtQlHocvien.
 */
@Entity
@Table(name = "QLDT_QL_HOCVIEN")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QldtQlHocvien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "diemdanh")
    private Integer diemdanh;

    @Column(name = "diem")
    private Integer diem;

    @Column(name = "danhgia")
    private String danhgia;

    @Column(name = "sudung")
    private Integer sudung;

    @Column(name = "trangthaithanhtoan")
    private Integer trangthaithanhtoan;

    @Column(name = "ngaythanhtoan")
    private LocalDate ngaythanhtoan;

    @Column(name = "mathanhtoan")
    private String mathanhtoan;

    @ManyToOne
    @JsonIgnoreProperties("hocViens")
    private QldtDaotao qldtDaotao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDiemdanh() {
        return diemdanh;
    }

    public QldtQlHocvien diemdanh(Integer diemdanh) {
        this.diemdanh = diemdanh;
        return this;
    }

    public void setDiemdanh(Integer diemdanh) {
        this.diemdanh = diemdanh;
    }

    public Integer getDiem() {
        return diem;
    }

    public QldtQlHocvien diem(Integer diem) {
        this.diem = diem;
        return this;
    }

    public void setDiem(Integer diem) {
        this.diem = diem;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public QldtQlHocvien danhgia(String danhgia) {
        this.danhgia = danhgia;
        return this;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }

    public Integer getSudung() {
        return sudung;
    }

    public QldtQlHocvien sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Integer getTrangthaithanhtoan() {
        return trangthaithanhtoan;
    }

    public QldtQlHocvien trangthaithanhtoan(Integer trangthaithanhtoan) {
        this.trangthaithanhtoan = trangthaithanhtoan;
        return this;
    }

    public void setTrangthaithanhtoan(Integer trangthaithanhtoan) {
        this.trangthaithanhtoan = trangthaithanhtoan;
    }

    public LocalDate getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public QldtQlHocvien ngaythanhtoan(LocalDate ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
        return this;
    }

    public void setNgaythanhtoan(LocalDate ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public String getMathanhtoan() {
        return mathanhtoan;
    }

    public QldtQlHocvien mathanhtoan(String mathanhtoan) {
        this.mathanhtoan = mathanhtoan;
        return this;
    }

    public void setMathanhtoan(String mathanhtoan) {
        this.mathanhtoan = mathanhtoan;
    }

    public QldtDaotao getQldtDaotao() {
        return qldtDaotao;
    }

    public QldtQlHocvien qldtDaotao(QldtDaotao qldtDaotao) {
        this.qldtDaotao = qldtDaotao;
        return this;
    }

    public void setQldtDaotao(QldtDaotao qldtDaotao) {
        this.qldtDaotao = qldtDaotao;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QldtQlHocvien)) {
            return false;
        }
        return id != null && id.equals(((QldtQlHocvien) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QldtQlHocvien{" +
            "id=" + getId() +
            ", diemdanh=" + getDiemdanh() +
            ", diem=" + getDiem() +
            ", danhgia='" + getDanhgia() + "'" +
            ", sudung=" + getSudung() +
            ", trangthaithanhtoan=" + getTrangthaithanhtoan() +
            ", ngaythanhtoan='" + getNgaythanhtoan() + "'" +
            ", mathanhtoan='" + getMathanhtoan() + "'" +
            "}";
    }
}
