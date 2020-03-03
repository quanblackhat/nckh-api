package com.vnptit.vnpthis.domain.qldt;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A QldtDaotao.
 */
@Entity
@Table(name = "QLDT_DAOTAO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QldtDaotao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "DAOTAOID")
    private Long id;

    @Column(name = "madaotao")
    private String madaotao;

    @Column(name = "tendaotao")
    private String tendaotao;

    @Column(name = "ngay_bd")
    private LocalDate ngayBd;

    @Column(name = "ngay_kt")
    private LocalDate ngayKt;

    @Column(name = "diachi")
    private String diachi;

    @Column(name = "doituongchitiet")
    private String doituongchitiet;

    @Column(name = "noidungdaotao")
    private String noidungdaotao;

    @Column(name = "thoigiandaotao")
    private Integer thoigiandaotao;

    @Column(name = "sudung")
    private Integer sudung;

    @OneToMany(mappedBy = "qldtDaotao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QldtDaotaoCt> daoTaoCts = new HashSet<>();

    @OneToMany(mappedBy = "qldtDaotao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QldtQlHocvien> hocViens = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMadaotao() {
        return madaotao;
    }

    public QldtDaotao madaotao(String madaotao) {
        this.madaotao = madaotao;
        return this;
    }

    public void setMadaotao(String madaotao) {
        this.madaotao = madaotao;
    }

    public String getTendaotao() {
        return tendaotao;
    }

    public QldtDaotao tendaotao(String tendaotao) {
        this.tendaotao = tendaotao;
        return this;
    }

    public void setTendaotao(String tendaotao) {
        this.tendaotao = tendaotao;
    }

    public LocalDate getNgayBd() {
        return ngayBd;
    }

    public QldtDaotao ngayBd(LocalDate ngayBd) {
        this.ngayBd = ngayBd;
        return this;
    }

    public void setNgayBd(LocalDate ngayBd) {
        this.ngayBd = ngayBd;
    }

    public LocalDate getNgayKt() {
        return ngayKt;
    }

    public QldtDaotao ngayKt(LocalDate ngayKt) {
        this.ngayKt = ngayKt;
        return this;
    }

    public void setNgayKt(LocalDate ngayKt) {
        this.ngayKt = ngayKt;
    }

    public String getDiachi() {
        return diachi;
    }

    public QldtDaotao diachi(String diachi) {
        this.diachi = diachi;
        return this;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getDoituongchitiet() {
        return doituongchitiet;
    }

    public QldtDaotao doituongchitiet(String doituongchitiet) {
        this.doituongchitiet = doituongchitiet;
        return this;
    }

    public void setDoituongchitiet(String doituongchitiet) {
        this.doituongchitiet = doituongchitiet;
    }

    public String getNoidungdaotao() {
        return noidungdaotao;
    }

    public QldtDaotao noidungdaotao(String noidungdaotao) {
        this.noidungdaotao = noidungdaotao;
        return this;
    }

    public void setNoidungdaotao(String noidungdaotao) {
        this.noidungdaotao = noidungdaotao;
    }

    public Integer getThoigiandaotao() {
        return thoigiandaotao;
    }

    public QldtDaotao thoigiandaotao(Integer thoigiandaotao) {
        this.thoigiandaotao = thoigiandaotao;
        return this;
    }

    public void setThoigiandaotao(Integer thoigiandaotao) {
        this.thoigiandaotao = thoigiandaotao;
    }

    public Integer getSudung() {
        return sudung;
    }

    public QldtDaotao sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public Set<QldtDaotaoCt> getDaoTaoCts() {
        return daoTaoCts;
    }

    public QldtDaotao daoTaoCts(Set<QldtDaotaoCt> qldtDaotaoCts) {
        this.daoTaoCts = qldtDaotaoCts;
        return this;
    }

    public QldtDaotao addDaoTaoCt(QldtDaotaoCt qldtDaotaoCt) {
        this.daoTaoCts.add(qldtDaotaoCt);
        qldtDaotaoCt.setQldtDaotao(this);
        return this;
    }

    public QldtDaotao removeDaoTaoCt(QldtDaotaoCt qldtDaotaoCt) {
        this.daoTaoCts.remove(qldtDaotaoCt);
        qldtDaotaoCt.setQldtDaotao(null);
        return this;
    }

    public void setDaoTaoCts(Set<QldtDaotaoCt> qldtDaotaoCts) {
        this.daoTaoCts = qldtDaotaoCts;
    }

    public Set<QldtQlHocvien> getHocViens() {
        return hocViens;
    }

    public QldtDaotao hocViens(Set<QldtQlHocvien> qldtQlHocviens) {
        this.hocViens = qldtQlHocviens;
        return this;
    }

    public QldtDaotao addHocVien(QldtQlHocvien qldtQlHocvien) {
        this.hocViens.add(qldtQlHocvien);
        qldtQlHocvien.setQldtDaotao(this);
        return this;
    }

    public QldtDaotao removeHocVien(QldtQlHocvien qldtQlHocvien) {
        this.hocViens.remove(qldtQlHocvien);
        qldtQlHocvien.setQldtDaotao(null);
        return this;
    }

    public void setHocViens(Set<QldtQlHocvien> qldtQlHocviens) {
        this.hocViens = qldtQlHocviens;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QldtDaotao)) {
            return false;
        }
        return id != null && id.equals(((QldtDaotao) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QldtDaotao{" +
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
