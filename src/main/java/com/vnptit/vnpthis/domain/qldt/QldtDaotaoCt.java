package com.vnptit.vnpthis.domain.qldt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A QldtDaotaoCt.
 */
@Entity
@Table(name = "QLDT_DAOTAO_CT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QldtDaotaoCt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "daotaoctid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "madaotaochitiet")
    private String madaotaochitiet;

    @Column(name = "tendaotaochitiet")
    private String tendaotaochitiet;

    @Column(name = "ngay_bd")
    private LocalDate ngayBd;

    @Column(name = "ngay_kt")
    private LocalDate ngayKt;

    @Column(name = "thoigiandaotaoct")
    private Integer thoigiandaotaoct;

    @Column(name = "noidungdaotaoct")
    private String noidungdaotaoct;

    @Column(name = "trangthaidaotaoct")
    private Integer trangthaidaotaoct;

    @Column(name = "sudung")
    private Integer sudung;

    @OneToMany(mappedBy = "qldtDaotaoCt")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QldtQlHocvienCt> hocVienCts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "daotaoid", insertable = false, updatable = false)
    @JsonIgnoreProperties("daoTaoCts")
    private QldtDaotao qldtDaotao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMadaotaochitiet() {
        return madaotaochitiet;
    }

    public QldtDaotaoCt madaotaochitiet(String madaotaochitiet) {
        this.madaotaochitiet = madaotaochitiet;
        return this;
    }

    public void setMadaotaochitiet(String madaotaochitiet) {
        this.madaotaochitiet = madaotaochitiet;
    }

    public String getTendaotaochitiet() {
        return tendaotaochitiet;
    }

    public QldtDaotaoCt tendaotaochitiet(String tendaotaochitiet) {
        this.tendaotaochitiet = tendaotaochitiet;
        return this;
    }

    public void setTendaotaochitiet(String tendaotaochitiet) {
        this.tendaotaochitiet = tendaotaochitiet;
    }

    public LocalDate getNgayBd() {
        return ngayBd;
    }

    public QldtDaotaoCt ngayBd(LocalDate ngayBd) {
        this.ngayBd = ngayBd;
        return this;
    }

    public void setNgayBd(LocalDate ngayBd) {
        this.ngayBd = ngayBd;
    }

    public LocalDate getNgayKt() {
        return ngayKt;
    }

    public QldtDaotaoCt ngayKt(LocalDate ngayKt) {
        this.ngayKt = ngayKt;
        return this;
    }

    public void setNgayKt(LocalDate ngayKt) {
        this.ngayKt = ngayKt;
    }

    public Integer getThoigiandaotaoct() {
        return thoigiandaotaoct;
    }

    public QldtDaotaoCt thoigiandaotaoct(Integer thoigiandaotaoct) {
        this.thoigiandaotaoct = thoigiandaotaoct;
        return this;
    }

    public void setThoigiandaotaoct(Integer thoigiandaotaoct) {
        this.thoigiandaotaoct = thoigiandaotaoct;
    }

    public String getNoidungdaotaoct() {
        return noidungdaotaoct;
    }

    public QldtDaotaoCt noidungdaotaoct(String noidungdaotaoct) {
        this.noidungdaotaoct = noidungdaotaoct;
        return this;
    }

    public void setNoidungdaotaoct(String noidungdaotaoct) {
        this.noidungdaotaoct = noidungdaotaoct;
    }

    public Integer getTrangthaidaotaoct() {
        return trangthaidaotaoct;
    }

    public QldtDaotaoCt trangthaidaotaoct(Integer trangthaidaotaoct) {
        this.trangthaidaotaoct = trangthaidaotaoct;
        return this;
    }

    public void setTrangthaidaotaoct(Integer trangthaidaotaoct) {
        this.trangthaidaotaoct = trangthaidaotaoct;
    }

    public Integer getSudung() {
        return sudung;
    }

    public QldtDaotaoCt sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Set<QldtQlHocvienCt> getHocVienCts() {
        return hocVienCts;
    }

    public QldtDaotaoCt hocVienCts(Set<QldtQlHocvienCt> qldtQlHocvienCts) {
        this.hocVienCts = qldtQlHocvienCts;
        return this;
    }

    public QldtDaotaoCt addHocVienCt(QldtQlHocvienCt qldtQlHocvienCt) {
        this.hocVienCts.add(qldtQlHocvienCt);
        qldtQlHocvienCt.setQldtDaotaoCt(this);
        return this;
    }

    public QldtDaotaoCt removeHocVienCt(QldtQlHocvienCt qldtQlHocvienCt) {
        this.hocVienCts.remove(qldtQlHocvienCt);
        qldtQlHocvienCt.setQldtDaotaoCt(null);
        return this;
    }

    public void setHocVienCts(Set<QldtQlHocvienCt> qldtQlHocvienCts) {
        this.hocVienCts = qldtQlHocvienCts;
    }

    public QldtDaotao getQldtDaotao() {
        return qldtDaotao;
    }

    public QldtDaotaoCt qldtDaotao(QldtDaotao qldtDaotao) {
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
        if (!(o instanceof QldtDaotaoCt)) {
            return false;
        }
        return id != null && id.equals(((QldtDaotaoCt) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QldtDaotaoCt{" +
            "id=" + getId() +
            ", madaotaochitiet='" + getMadaotaochitiet() + "'" +
            ", tendaotaochitiet='" + getTendaotaochitiet() + "'" +
            ", ngayBd='" + getNgayBd() + "'" +
            ", ngayKt='" + getNgayKt() + "'" +
            ", thoigiandaotaoct=" + getThoigiandaotaoct() +
            ", noidungdaotaoct='" + getNoidungdaotaoct() + "'" +
            ", trangthaidaotaoct=" + getTrangthaidaotaoct() +
            ", sudung=" + getSudung() +
            "}";
    }
}
