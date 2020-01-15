package com.vnptit.vnpthis.domain;

import javax.persistence.*;

@Entity
@Table(name = "CDT_KETQUACONGTAC", schema = "PM2_FRESHER", catalog = "")
public class CdtKetquacongtac {
    @Id
    private int ketquacongtacid;
    private String maketqua;
    private String tenketqua;
    private Byte thutusapxep;
    private int csytid;

    @Basic
    @Column(name = "KETQUACONGTACID", nullable = false, precision = 0)
    public int getKetquacongtacid() {
        return ketquacongtacid;
    }

    public void setKetquacongtacid(int ketquacongtacid) {
        this.ketquacongtacid = ketquacongtacid;
    }

    @Basic
    @Column(name = "MAKETQUA", nullable = true, length = 40)
    public String getMaketqua() {
        return maketqua;
    }

    public void setMaketqua(String maketqua) {
        this.maketqua = maketqua;
    }

    @Basic
    @Column(name = "TENKETQUA", nullable = true, length = 1000)
    public String getTenketqua() {
        return tenketqua;
    }

    public void setTenketqua(String tenketqua) {
        this.tenketqua = tenketqua;
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

        CdtKetquacongtac that = (CdtKetquacongtac) o;

        if (ketquacongtacid != that.ketquacongtacid) return false;
        if (csytid != that.csytid) return false;
        if (maketqua != null ? !maketqua.equals(that.maketqua) : that.maketqua != null) return false;
        if (tenketqua != null ? !tenketqua.equals(that.tenketqua) : that.tenketqua != null) return false;
        if (thutusapxep != null ? !thutusapxep.equals(that.thutusapxep) : that.thutusapxep != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ketquacongtacid;
        result = 31 * result + (maketqua != null ? maketqua.hashCode() : 0);
        result = 31 * result + (tenketqua != null ? tenketqua.hashCode() : 0);
        result = 31 * result + (thutusapxep != null ? thutusapxep.hashCode() : 0);
        result = 31 * result + csytid;
        return result;
    }
}
