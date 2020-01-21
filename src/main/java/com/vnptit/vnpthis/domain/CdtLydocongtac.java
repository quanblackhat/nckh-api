package com.vnptit.vnpthis.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CDT_LYDOCONGTAC", schema = "PM2_FRESHER", catalog = "")
public class CdtLydocongtac {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private int lydocongtacid;
    private String malydo;
    private String tenlydo;
    private Byte thutusapxep;
    private int csytid;

    @OneToMany(mappedBy = "lydocongtac")
    private Set<CdtChidaotuyen> chidaotuyen = new HashSet<>();

    public Set<CdtChidaotuyen> getChidaotuyen() {
        return chidaotuyen;
    }

    public void setChidaotuyen(Set<CdtChidaotuyen> chidaotuyen) {
        this.chidaotuyen = chidaotuyen;
    }

    @Basic
    @Column(name = "LYDOCONGTACID", nullable = false, precision = 0)
    public int getLydocongtacid() {
        return lydocongtacid;
    }

    public void setLydocongtacid(int lydocongtacid) {
        this.lydocongtacid = lydocongtacid;
    }

    @Basic
    @Column(name = "MALYDO", nullable = true, length = 40)
    public String getMalydo() {
        return malydo;
    }

    public void setMalydo(String malydo) {
        this.malydo = malydo;
    }

    @Basic
    @Column(name = "TENLYDO", nullable = true, length = 1000)
    public String getTenlydo() {
        return tenlydo;
    }

    public void setTenlydo(String tenlydo) {
        this.tenlydo = tenlydo;
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

        CdtLydocongtac that = (CdtLydocongtac) o;

        if (lydocongtacid != that.lydocongtacid) return false;
        if (csytid != that.csytid) return false;
        if (malydo != null ? !malydo.equals(that.malydo) : that.malydo != null) return false;
        if (tenlydo != null ? !tenlydo.equals(that.tenlydo) : that.tenlydo != null) return false;
        if (thutusapxep != null ? !thutusapxep.equals(that.thutusapxep) : that.thutusapxep != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lydocongtacid;
        result = 31 * result + (malydo != null ? malydo.hashCode() : 0);
        result = 31 * result + (tenlydo != null ? tenlydo.hashCode() : 0);
        result = 31 * result + (thutusapxep != null ? thutusapxep.hashCode() : 0);
        result = 31 * result + csytid;
        return result;
    }
}
