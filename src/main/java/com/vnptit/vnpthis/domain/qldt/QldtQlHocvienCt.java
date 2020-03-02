package com.vnptit.vnpthis.domain.qldt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A QldtQlHocvienCt.
 */
@Entity
@Table(name = "QLDT_QL_HOCVIEN_CT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QldtQlHocvienCt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "diemdanh")
    private Integer diemdanh;

    @Column(name = "diemthi")
    private Integer diemthi;

    @Column(name = "danhgia")
    private String danhgia;

    @Column(name = "sudung")
    private Integer sudung;

    @ManyToOne
    @JsonIgnoreProperties("hocVienCts")
    private QldtDaotaoCt qldtDaotaoCt;

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

    public QldtQlHocvienCt diemdanh(Integer diemdanh) {
        this.diemdanh = diemdanh;
        return this;
    }

    public void setDiemdanh(Integer diemdanh) {
        this.diemdanh = diemdanh;
    }

    public Integer getDiemthi() {
        return diemthi;
    }

    public QldtQlHocvienCt diemthi(Integer diemthi) {
        this.diemthi = diemthi;
        return this;
    }

    public void setDiemthi(Integer diemthi) {
        this.diemthi = diemthi;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public QldtQlHocvienCt danhgia(String danhgia) {
        this.danhgia = danhgia;
        return this;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }

    public Integer getSudung() {
        return sudung;
    }

    public QldtQlHocvienCt sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public QldtDaotaoCt getQldtDaotaoCt() {
        return qldtDaotaoCt;
    }

    public QldtQlHocvienCt qldtDaotaoCt(QldtDaotaoCt qldtDaotaoCt) {
        this.qldtDaotaoCt = qldtDaotaoCt;
        return this;
    }

    public void setQldtDaotaoCt(QldtDaotaoCt qldtDaotaoCt) {
        this.qldtDaotaoCt = qldtDaotaoCt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QldtQlHocvienCt)) {
            return false;
        }
        return id != null && id.equals(((QldtQlHocvienCt) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QldtQlHocvienCt{" +
            "id=" + getId() +
            ", diemdanh=" + getDiemdanh() +
            ", diemthi=" + getDiemthi() +
            ", danhgia='" + getDanhgia() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
