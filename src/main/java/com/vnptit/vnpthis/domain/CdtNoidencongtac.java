package com.vnptit.vnpthis.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CDT_NOIDENCONGTAC", schema = "PM2_FRESHER", catalog = "")
public class CdtNoidencongtac {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private int noidencongtacid;
    private String manoiden;
    private String tennoiden;
    private Byte thutusapxep;
    private int csytid;

    @ManyToMany(mappedBy = "danhSachNoiDen")
    Set<CdtChidaotuyen> danhSachChiDaoTuyen = new HashSet<>();

    @Basic
    @Column(name = "NOIDENCONGTACID", nullable = false, precision = 0)
    public int getNoidencongtacid() {
        return noidencongtacid;
    }

    public void setNoidencongtacid(int noidencongtacid) {
        this.noidencongtacid = noidencongtacid;
    }

    @Basic
    @Column(name = "MANOIDEN", nullable = true, length = 40)
    public String getManoiden() {
        return manoiden;
    }

    public void setManoiden(String manoiden) {
        this.manoiden = manoiden;
    }

    @Basic
    @Column(name = "TENNOIDEN", nullable = true, length = 1000)
    public String getTennoiden() {
        return tennoiden;
    }

    public void setTennoiden(String tennoiden) {
        this.tennoiden = tennoiden;
    }

    @Basic
    @Column(name = "THUTUSAPXEP", nullable = true, precision = 0)
    public Byte getThutusapxep() {
        return thutusapxep;
    }

    public void setThutusapxep(Byte thutusapxep) {
        this.thutusapxep = thutusapxep;
    }

    @Basic
    @Column(name = "CSYTID", nullable = false, precision = 0)
    public int getCsytid() {
        return csytid;
    }

    public void setCsytid(int csytid) {
        this.csytid = csytid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CdtNoidencongtac that = (CdtNoidencongtac) o;

        if (noidencongtacid != that.noidencongtacid) return false;
        if (csytid != that.csytid) return false;
        if (manoiden != null ? !manoiden.equals(that.manoiden) : that.manoiden != null) return false;
        if (tennoiden != null ? !tennoiden.equals(that.tennoiden) : that.tennoiden != null) return false;
        if (thutusapxep != null ? !thutusapxep.equals(that.thutusapxep) : that.thutusapxep != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noidencongtacid;
        result = 31 * result + (manoiden != null ? manoiden.hashCode() : 0);
        result = 31 * result + (tennoiden != null ? tennoiden.hashCode() : 0);
        result = 31 * result + (thutusapxep != null ? thutusapxep.hashCode() : 0);
        result = 31 * result + csytid;
        return result;
    }
}
