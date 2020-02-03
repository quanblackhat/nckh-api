package com.vnptit.vnpthis.domain.cdt;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CDT_KYTHUATHOTRO", schema = "PM2_FRESHER", catalog = "")
public class CdtKythuathotro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_GENERATOR")
    @SequenceGenerator(name = "SEQUENCE_GENERATOR")
    private int kythuathotroid;
    private String makythuat;
    private String tenkythuat;
    private Byte thutusapxep;
    private int csytid;

    @ManyToMany(mappedBy = "danhSachKyThuatHoTro")
    private Set<CdtChidaotuyen> danhSachChiDaoTuyen = new HashSet<>();


    @Basic
    @Column(name = "KYTHUATHOTROID", nullable = false, precision = 0)
    public int getKythuathotroid() {
        return kythuathotroid;
    }

    public void setKythuathotroid(int kythuathotroid) {
        this.kythuathotroid = kythuathotroid;
    }

    @Basic
    @Column(name = "MAKYTHUAT", nullable = true, length = 40)
    public String getMakythuat() {
        return makythuat;
    }

    public void setMakythuat(String makythuat) {
        this.makythuat = makythuat;
    }

    @Basic
    @Column(name = "TENKYTHUAT", nullable = true, length = 1000)
    public String getTenkythuat() {
        return tenkythuat;
    }

    public void setTenkythuat(String tenkythuat) {
        this.tenkythuat = tenkythuat;
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

        CdtKythuathotro that = (CdtKythuathotro) o;

        if (kythuathotroid != that.kythuathotroid) return false;
        if (csytid != that.csytid) return false;
        if (makythuat != null ? !makythuat.equals(that.makythuat) : that.makythuat != null) return false;
        if (tenkythuat != null ? !tenkythuat.equals(that.tenkythuat) : that.tenkythuat != null) return false;
        if (thutusapxep != null ? !thutusapxep.equals(that.thutusapxep) : that.thutusapxep != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kythuathotroid;
        result = 31 * result + (makythuat != null ? makythuat.hashCode() : 0);
        result = 31 * result + (tenkythuat != null ? tenkythuat.hashCode() : 0);
        result = 31 * result + (thutusapxep != null ? thutusapxep.hashCode() : 0);
        result = 31 * result + csytid;
        return result;
    }
}
