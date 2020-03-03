package com.vnptit.vnpthis.domain.qldt;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A QldtDmNguoidung.
 */
@Entity
@Table(name = "QLDT_DM_NGUOIDUNG")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QldtDmNguoidung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "nguoidungid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngaysinh")
    private LocalDate ngaysinh;

    @Column(name = "mabenhvien")
    private String mabenhvien;

    @Column(name = "sudung")
    private Integer sudung;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public QldtDmNguoidung ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public LocalDate getNgaysinh() {
        return ngaysinh;
    }

    public QldtDmNguoidung ngaysinh(LocalDate ngaysinh) {
        this.ngaysinh = ngaysinh;
        return this;
    }

    public void setNgaysinh(LocalDate ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getMabenhvien() {
        return mabenhvien;
    }

    public QldtDmNguoidung mabenhvien(String mabenhvien) {
        this.mabenhvien = mabenhvien;
        return this;
    }

    public void setMabenhvien(String mabenhvien) {
        this.mabenhvien = mabenhvien;
    }

    public Integer getSudung() {
        return sudung;
    }

    public QldtDmNguoidung sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QldtDmNguoidung)) {
            return false;
        }
        return id != null && id.equals(((QldtDmNguoidung) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QldtDmNguoidung{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", ngaysinh='" + getNgaysinh() + "'" +
            ", mabenhvien='" + getMabenhvien() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
