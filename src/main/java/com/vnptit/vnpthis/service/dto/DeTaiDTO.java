package com.vnptit.vnpthis.service.dto;
import com.vnptit.vnpthis.domain.nckh.DeTai;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link DeTai} entity.
 */
public class DeTaiDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer sott;

    @NotNull
    private String tendetai;

    @NotNull
    private LocalDate ngaytao;

    @NotNull
    private LocalDate ngaypheduyet;

    @NotNull
    private LocalDate ngaybd;

    @NotNull
    private LocalDate ngaykt;

    @NotNull
    private Integer hientrang;

    @NotNull
    private Integer xuatban;

    @NotNull
    private Integer capquanly;

    @NotNull
    private LocalDate ngaynghiemthu;

    @NotNull
    private Integer kinhphithuchien;

    @NotNull
    private String nguonkinhphi;

    @NotNull
    private String muctieu;

    @NotNull
    private Integer kinhphiDukien;

    @NotNull
    private Integer chunhiemdetai;

    @NotNull
    private String noidungtongquan;

    @NotNull
    private Integer kinhphiHoanthanh;

    @NotNull
    private Integer tongdiem;

    @NotNull
    private String ghichu;

    @NotNull
    private Integer nguoiCn;

    @NotNull
    private LocalDate ngayCn;


    private Long chuyenMucId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSott() {
        return sott;
    }

    public void setSott(Integer sott) {
        this.sott = sott;
    }

    public String getTendetai() {
        return tendetai;
    }

    public void setTendetai(String tendetai) {
        this.tendetai = tendetai;
    }

    public LocalDate getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
    }

    public LocalDate getNgaypheduyet() {
        return ngaypheduyet;
    }

    public void setNgaypheduyet(LocalDate ngaypheduyet) {
        this.ngaypheduyet = ngaypheduyet;
    }

    public LocalDate getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(LocalDate ngaybd) {
        this.ngaybd = ngaybd;
    }

    public LocalDate getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(LocalDate ngaykt) {
        this.ngaykt = ngaykt;
    }

    public Integer getHientrang() {
        return hientrang;
    }

    public void setHientrang(Integer hientrang) {
        this.hientrang = hientrang;
    }

    public Integer getXuatban() {
        return xuatban;
    }

    public void setXuatban(Integer xuatban) {
        this.xuatban = xuatban;
    }

    public Integer getCapquanly() {
        return capquanly;
    }

    public void setCapquanly(Integer capquanly) {
        this.capquanly = capquanly;
    }

    public LocalDate getNgaynghiemthu() {
        return ngaynghiemthu;
    }

    public void setNgaynghiemthu(LocalDate ngaynghiemthu) {
        this.ngaynghiemthu = ngaynghiemthu;
    }

    public Integer getKinhphithuchien() {
        return kinhphithuchien;
    }

    public void setKinhphithuchien(Integer kinhphithuchien) {
        this.kinhphithuchien = kinhphithuchien;
    }

    public String getNguonkinhphi() {
        return nguonkinhphi;
    }

    public void setNguonkinhphi(String nguonkinhphi) {
        this.nguonkinhphi = nguonkinhphi;
    }

    public String getMuctieu() {
        return muctieu;
    }

    public void setMuctieu(String muctieu) {
        this.muctieu = muctieu;
    }

    public Integer getKinhphiDukien() {
        return kinhphiDukien;
    }

    public void setKinhphiDukien(Integer kinhphiDukien) {
        this.kinhphiDukien = kinhphiDukien;
    }

    public Integer getChunhiemdetai() {
        return chunhiemdetai;
    }

    public void setChunhiemdetai(Integer chunhiemdetai) {
        this.chunhiemdetai = chunhiemdetai;
    }

    public String getNoidungtongquan() {
        return noidungtongquan;
    }

    public void setNoidungtongquan(String noidungtongquan) {
        this.noidungtongquan = noidungtongquan;
    }

    public Integer getKinhphiHoanthanh() {
        return kinhphiHoanthanh;
    }

    public void setKinhphiHoanthanh(Integer kinhphiHoanthanh) {
        this.kinhphiHoanthanh = kinhphiHoanthanh;
    }

    public Integer getTongdiem() {
        return tongdiem;
    }

    public void setTongdiem(Integer tongdiem) {
        this.tongdiem = tongdiem;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Integer getNguoiCn() {
        return nguoiCn;
    }

    public void setNguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    public LocalDate getNgayCn() {
        return ngayCn;
    }

    public void setNgayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
    }

    public Long getChuyenMucId() {
        return chuyenMucId;
    }

    public void setChuyenMucId(Long chuyenMucId) {
        this.chuyenMucId = chuyenMucId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeTaiDTO deTaiDTO = (DeTaiDTO) o;
        if (deTaiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deTaiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeTaiDTO{" +
            "id=" + getId() +
            ", sott=" + getSott() +
            ", tendetai='" + getTendetai() + "'" +
            ", ngaytao='" + getNgaytao() + "'" +
            ", ngaypheduyet='" + getNgaypheduyet() + "'" +
            ", ngaybd='" + getNgaybd() + "'" +
            ", ngaykt='" + getNgaykt() + "'" +
            ", hientrang=" + getHientrang() +
            ", xuatban=" + getXuatban() +
            ", capquanly=" + getCapquanly() +
            ", ngaynghiemthu='" + getNgaynghiemthu() + "'" +
            ", kinhphithuchien=" + getKinhphithuchien() +
            ", nguonkinhphi='" + getNguonkinhphi() + "'" +
            ", muctieu='" + getMuctieu() + "'" +
            ", kinhphiDukien=" + getKinhphiDukien() +
            ", chunhiemdetai=" + getChunhiemdetai() +
            ", noidungtongquan='" + getNoidungtongquan() + "'" +
            ", kinhphiHoanthanh=" + getKinhphiHoanthanh() +
            ", tongdiem=" + getTongdiem() +
            ", ghichu='" + getGhichu() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            ", ngayCn='" + getNgayCn() + "'" +
            ", chuyenMucId=" + getChuyenMucId() +
            "}";
    }
}
