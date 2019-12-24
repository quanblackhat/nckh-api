package com.vnpt.cdt.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.vnpt.cdt.domain.CdtChiDaoTuyen} entity. This class is used
 * in {@link com.vnpt.cdt.web.rest.CdtChiDaoTuyenResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cdt-chi-dao-tuyens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CdtChiDaoTuyenCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter soQuyetDinh;

    private InstantFilter ngayQuyetDinh;

    private StringFilter soHopDong;

    private InstantFilter ngayHopDong;

    private StringFilter ghiChu;

    private StringFilter noiDung;

    private InstantFilter ngayBD;

    private InstantFilter ngayKT;

    private InstantFilter ngayTao;

    private LongFilter soBenhNhanKham;

    private LongFilter soBenhNhanKyThuat;

    private LongFilter soCanBoChuyenGiao;

    private LongFilter nhanvienId;

    private LongFilter noidenId;

    private LongFilter kythuatId;

    private LongFilter vattuId;

    private LongFilter lydocongtacId;

    private LongFilter ketquacongtacId;

    private LongFilter taichinhId;

    public CdtChiDaoTuyenCriteria(){
    }

    public CdtChiDaoTuyenCriteria(CdtChiDaoTuyenCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.soQuyetDinh = other.soQuyetDinh == null ? null : other.soQuyetDinh.copy();
        this.ngayQuyetDinh = other.ngayQuyetDinh == null ? null : other.ngayQuyetDinh.copy();
        this.soHopDong = other.soHopDong == null ? null : other.soHopDong.copy();
        this.ngayHopDong = other.ngayHopDong == null ? null : other.ngayHopDong.copy();
        this.ghiChu = other.ghiChu == null ? null : other.ghiChu.copy();
        this.noiDung = other.noiDung == null ? null : other.noiDung.copy();
        this.ngayBD = other.ngayBD == null ? null : other.ngayBD.copy();
        this.ngayKT = other.ngayKT == null ? null : other.ngayKT.copy();
        this.ngayTao = other.ngayTao == null ? null : other.ngayTao.copy();
        this.soBenhNhanKham = other.soBenhNhanKham == null ? null : other.soBenhNhanKham.copy();
        this.soBenhNhanKyThuat = other.soBenhNhanKyThuat == null ? null : other.soBenhNhanKyThuat.copy();
        this.soCanBoChuyenGiao = other.soCanBoChuyenGiao == null ? null : other.soCanBoChuyenGiao.copy();
        this.nhanvienId = other.nhanvienId == null ? null : other.nhanvienId.copy();
        this.noidenId = other.noidenId == null ? null : other.noidenId.copy();
        this.kythuatId = other.kythuatId == null ? null : other.kythuatId.copy();
        this.vattuId = other.vattuId == null ? null : other.vattuId.copy();
        this.lydocongtacId = other.lydocongtacId == null ? null : other.lydocongtacId.copy();
        this.ketquacongtacId = other.ketquacongtacId == null ? null : other.ketquacongtacId.copy();
        this.taichinhId = other.taichinhId == null ? null : other.taichinhId.copy();
    }

    @Override
    public CdtChiDaoTuyenCriteria copy() {
        return new CdtChiDaoTuyenCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSoQuyetDinh() {
        return soQuyetDinh;
    }

    public void setSoQuyetDinh(StringFilter soQuyetDinh) {
        this.soQuyetDinh = soQuyetDinh;
    }

    public InstantFilter getNgayQuyetDinh() {
        return ngayQuyetDinh;
    }

    public void setNgayQuyetDinh(InstantFilter ngayQuyetDinh) {
        this.ngayQuyetDinh = ngayQuyetDinh;
    }

    public StringFilter getSoHopDong() {
        return soHopDong;
    }

    public void setSoHopDong(StringFilter soHopDong) {
        this.soHopDong = soHopDong;
    }

    public InstantFilter getNgayHopDong() {
        return ngayHopDong;
    }

    public void setNgayHopDong(InstantFilter ngayHopDong) {
        this.ngayHopDong = ngayHopDong;
    }

    public StringFilter getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(StringFilter ghiChu) {
        this.ghiChu = ghiChu;
    }

    public StringFilter getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(StringFilter noiDung) {
        this.noiDung = noiDung;
    }

    public InstantFilter getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(InstantFilter ngayBD) {
        this.ngayBD = ngayBD;
    }

    public InstantFilter getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(InstantFilter ngayKT) {
        this.ngayKT = ngayKT;
    }

    public InstantFilter getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(InstantFilter ngayTao) {
        this.ngayTao = ngayTao;
    }

    public LongFilter getSoBenhNhanKham() {
        return soBenhNhanKham;
    }

    public void setSoBenhNhanKham(LongFilter soBenhNhanKham) {
        this.soBenhNhanKham = soBenhNhanKham;
    }

    public LongFilter getSoBenhNhanKyThuat() {
        return soBenhNhanKyThuat;
    }

    public void setSoBenhNhanKyThuat(LongFilter soBenhNhanKyThuat) {
        this.soBenhNhanKyThuat = soBenhNhanKyThuat;
    }

    public LongFilter getSoCanBoChuyenGiao() {
        return soCanBoChuyenGiao;
    }

    public void setSoCanBoChuyenGiao(LongFilter soCanBoChuyenGiao) {
        this.soCanBoChuyenGiao = soCanBoChuyenGiao;
    }

    public LongFilter getNhanvienId() {
        return nhanvienId;
    }

    public void setNhanvienId(LongFilter nhanvienId) {
        this.nhanvienId = nhanvienId;
    }

    public LongFilter getNoidenId() {
        return noidenId;
    }

    public void setNoidenId(LongFilter noidenId) {
        this.noidenId = noidenId;
    }

    public LongFilter getKythuatId() {
        return kythuatId;
    }

    public void setKythuatId(LongFilter kythuatId) {
        this.kythuatId = kythuatId;
    }

    public LongFilter getVattuId() {
        return vattuId;
    }

    public void setVattuId(LongFilter vattuId) {
        this.vattuId = vattuId;
    }

    public LongFilter getLydocongtacId() {
        return lydocongtacId;
    }

    public void setLydocongtacId(LongFilter lydocongtacId) {
        this.lydocongtacId = lydocongtacId;
    }

    public LongFilter getKetquacongtacId() {
        return ketquacongtacId;
    }

    public void setKetquacongtacId(LongFilter ketquacongtacId) {
        this.ketquacongtacId = ketquacongtacId;
    }

    public LongFilter getTaichinhId() {
        return taichinhId;
    }

    public void setTaichinhId(LongFilter taichinhId) {
        this.taichinhId = taichinhId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CdtChiDaoTuyenCriteria that = (CdtChiDaoTuyenCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(soQuyetDinh, that.soQuyetDinh) &&
            Objects.equals(ngayQuyetDinh, that.ngayQuyetDinh) &&
            Objects.equals(soHopDong, that.soHopDong) &&
            Objects.equals(ngayHopDong, that.ngayHopDong) &&
            Objects.equals(ghiChu, that.ghiChu) &&
            Objects.equals(noiDung, that.noiDung) &&
            Objects.equals(ngayBD, that.ngayBD) &&
            Objects.equals(ngayKT, that.ngayKT) &&
            Objects.equals(ngayTao, that.ngayTao) &&
            Objects.equals(soBenhNhanKham, that.soBenhNhanKham) &&
            Objects.equals(soBenhNhanKyThuat, that.soBenhNhanKyThuat) &&
            Objects.equals(soCanBoChuyenGiao, that.soCanBoChuyenGiao) &&
            Objects.equals(nhanvienId, that.nhanvienId) &&
            Objects.equals(noidenId, that.noidenId) &&
            Objects.equals(kythuatId, that.kythuatId) &&
            Objects.equals(vattuId, that.vattuId) &&
            Objects.equals(lydocongtacId, that.lydocongtacId) &&
            Objects.equals(ketquacongtacId, that.ketquacongtacId) &&
            Objects.equals(taichinhId, that.taichinhId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        soQuyetDinh,
        ngayQuyetDinh,
        soHopDong,
        ngayHopDong,
        ghiChu,
        noiDung,
        ngayBD,
        ngayKT,
        ngayTao,
        soBenhNhanKham,
        soBenhNhanKyThuat,
        soCanBoChuyenGiao,
        nhanvienId,
        noidenId,
        kythuatId,
        vattuId,
        lydocongtacId,
        ketquacongtacId,
        taichinhId
        );
    }

    @Override
    public String toString() {
        return "CdtChiDaoTuyenCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (soQuyetDinh != null ? "soQuyetDinh=" + soQuyetDinh + ", " : "") +
                (ngayQuyetDinh != null ? "ngayQuyetDinh=" + ngayQuyetDinh + ", " : "") +
                (soHopDong != null ? "soHopDong=" + soHopDong + ", " : "") +
                (ngayHopDong != null ? "ngayHopDong=" + ngayHopDong + ", " : "") +
                (ghiChu != null ? "ghiChu=" + ghiChu + ", " : "") +
                (noiDung != null ? "noiDung=" + noiDung + ", " : "") +
                (ngayBD != null ? "ngayBD=" + ngayBD + ", " : "") +
                (ngayKT != null ? "ngayKT=" + ngayKT + ", " : "") +
                (ngayTao != null ? "ngayTao=" + ngayTao + ", " : "") +
                (soBenhNhanKham != null ? "soBenhNhanKham=" + soBenhNhanKham + ", " : "") +
                (soBenhNhanKyThuat != null ? "soBenhNhanKyThuat=" + soBenhNhanKyThuat + ", " : "") +
                (soCanBoChuyenGiao != null ? "soCanBoChuyenGiao=" + soCanBoChuyenGiao + ", " : "") +
                (nhanvienId != null ? "nhanvienId=" + nhanvienId + ", " : "") +
                (noidenId != null ? "noidenId=" + noidenId + ", " : "") +
                (kythuatId != null ? "kythuatId=" + kythuatId + ", " : "") +
                (vattuId != null ? "vattuId=" + vattuId + ", " : "") +
                (lydocongtacId != null ? "lydocongtacId=" + lydocongtacId + ", " : "") +
                (ketquacongtacId != null ? "ketquacongtacId=" + ketquacongtacId + ", " : "") +
                (taichinhId != null ? "taichinhId=" + taichinhId + ", " : "") +
            "}";
    }

}
