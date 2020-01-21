package com.vnptit.vnpthis.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CDT_VATTUHOTRO", schema = "PM2_FRESHER", catalog = "")
public class CdtVattuhotro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private int vattuhotroid;
    private String mavattu;
    private String tenvattu;
    private Byte thutusapxep;
    private int csytid;

    @ManyToMany(mappedBy = "danhSachVatTuHoTro")
    Set<CdtChidaotuyen> danhsachChiDaoTuyen = new HashSet<>();

    @Basic
    @Column(name = "VATTUHOTROID", nullable = false, precision = 0)
    public int getVattuhotroid() {
        return vattuhotroid;
    }

    public void setVattuhotroid(int vattuhotroid) {
        this.vattuhotroid = vattuhotroid;
    }

    @Basic
    @Column(name = "MAVATTU", nullable = true, length = 40)
    public String getMavattu() {
        return mavattu;
    }

    public void setMavattu(String mavattu) {
        this.mavattu = mavattu;
    }

    @Basic
    @Column(name = "TENVATTU", nullable = true, length = 1000)
    public String getTenvattu() {
        return tenvattu;
    }

    public void setTenvattu(String tenvattu) {
        this.tenvattu = tenvattu;
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

        CdtVattuhotro that = (CdtVattuhotro) o;

        if (vattuhotroid != that.vattuhotroid) return false;
        if (csytid != that.csytid) return false;
        if (mavattu != null ? !mavattu.equals(that.mavattu) : that.mavattu != null) return false;
        if (tenvattu != null ? !tenvattu.equals(that.tenvattu) : that.tenvattu != null) return false;
        if (thutusapxep != null ? !thutusapxep.equals(that.thutusapxep) : that.thutusapxep != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vattuhotroid;
        result = 31 * result + (mavattu != null ? mavattu.hashCode() : 0);
        result = 31 * result + (tenvattu != null ? tenvattu.hashCode() : 0);
        result = 31 * result + (thutusapxep != null ? thutusapxep.hashCode() : 0);
        result = 31 * result + csytid;
        return result;
    }
}
