package com.vnptit.vnpthis.domain.qldt;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A QldtDutoanDaotao.
 */
@Entity
@Table(name = "QLDT_DUTOAN_DAOTAO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QldtDutoanDaotao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "solop")
    private Integer solop;

    @Column(name = "sohocvien")
    private Integer sohocvien;

    @Column(name = "dathanhtoan")
    private Integer dathanhtoan;

    @Column(name = "madutoan")
    private Integer madutoan;

    @Column(name = "tendutoan")
    private String tendutoan;

    @Column(name = "trangthai")
    private Integer trangthai;

    @Column(name = "ngay_bd")
    private LocalDate ngayBd;

    @Column(name = "ngay_kt")
    private LocalDate ngayKt;

    @Column(name = "sudung")
    private Integer sudung;

    @OneToMany(mappedBy = "qldtDutoanDaotao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QldtDutoanDaotaoct> duToanDaotaoCts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSolop() {
        return solop;
    }

    public QldtDutoanDaotao solop(Integer solop) {
        this.solop = solop;
        return this;
    }

    public void setSolop(Integer solop) {
        this.solop = solop;
    }

    public Integer getSohocvien() {
        return sohocvien;
    }

    public QldtDutoanDaotao sohocvien(Integer sohocvien) {
        this.sohocvien = sohocvien;
        return this;
    }

    public void setSohocvien(Integer sohocvien) {
        this.sohocvien = sohocvien;
    }

    public Integer getDathanhtoan() {
        return dathanhtoan;
    }

    public QldtDutoanDaotao dathanhtoan(Integer dathanhtoan) {
        this.dathanhtoan = dathanhtoan;
        return this;
    }

    public void setDathanhtoan(Integer dathanhtoan) {
        this.dathanhtoan = dathanhtoan;
    }

    public Integer getMadutoan() {
        return madutoan;
    }

    public QldtDutoanDaotao madutoan(Integer madutoan) {
        this.madutoan = madutoan;
        return this;
    }

    public void setMadutoan(Integer madutoan) {
        this.madutoan = madutoan;
    }

    public String getTendutoan() {
        return tendutoan;
    }

    public QldtDutoanDaotao tendutoan(String tendutoan) {
        this.tendutoan = tendutoan;
        return this;
    }

    public void setTendutoan(String tendutoan) {
        this.tendutoan = tendutoan;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public QldtDutoanDaotao trangthai(Integer trangthai) {
        this.trangthai = trangthai;
        return this;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public LocalDate getNgayBd() {
        return ngayBd;
    }

    public QldtDutoanDaotao ngayBd(LocalDate ngayBd) {
        this.ngayBd = ngayBd;
        return this;
    }

    public void setNgayBd(LocalDate ngayBd) {
        this.ngayBd = ngayBd;
    }

    public LocalDate getNgayKt() {
        return ngayKt;
    }

    public QldtDutoanDaotao ngayKt(LocalDate ngayKt) {
        this.ngayKt = ngayKt;
        return this;
    }

    public void setNgayKt(LocalDate ngayKt) {
        this.ngayKt = ngayKt;
    }

    public Integer getSudung() {
        return sudung;
    }

    public QldtDutoanDaotao sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Set<QldtDutoanDaotaoct> getDuToanDaotaoCts() {
        return duToanDaotaoCts;
    }

    public QldtDutoanDaotao duToanDaotaoCts(Set<QldtDutoanDaotaoct> qldtDutoanDaotaocts) {
        this.duToanDaotaoCts = qldtDutoanDaotaocts;
        return this;
    }

    public QldtDutoanDaotao addDuToanDaotaoCt(QldtDutoanDaotaoct qldtDutoanDaotaoct) {
        this.duToanDaotaoCts.add(qldtDutoanDaotaoct);
        qldtDutoanDaotaoct.setQldtDutoanDaotao(this);
        return this;
    }

    public QldtDutoanDaotao removeDuToanDaotaoCt(QldtDutoanDaotaoct qldtDutoanDaotaoct) {
        this.duToanDaotaoCts.remove(qldtDutoanDaotaoct);
        qldtDutoanDaotaoct.setQldtDutoanDaotao(null);
        return this;
    }

    public void setDuToanDaotaoCts(Set<QldtDutoanDaotaoct> qldtDutoanDaotaocts) {
        this.duToanDaotaoCts = qldtDutoanDaotaocts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QldtDutoanDaotao)) {
            return false;
        }
        return id != null && id.equals(((QldtDutoanDaotao) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QldtDutoanDaotao{" +
            "id=" + getId() +
            ", solop=" + getSolop() +
            ", sohocvien=" + getSohocvien() +
            ", dathanhtoan=" + getDathanhtoan() +
            ", madutoan=" + getMadutoan() +
            ", tendutoan='" + getTendutoan() + "'" +
            ", trangthai=" + getTrangthai() +
            ", ngayBd='" + getNgayBd() + "'" +
            ", ngayKt='" + getNgayKt() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
