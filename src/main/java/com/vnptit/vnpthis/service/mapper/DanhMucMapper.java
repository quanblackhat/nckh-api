package com.vnptit.vnpthis.service.mapper;

import com.vnptit.vnpthis.domain.*;
import com.vnptit.vnpthis.domain.nckh.DanhMuc;
import com.vnptit.vnpthis.service.dto.DanhMucDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DanhMuc} and its DTO {@link DanhMucDTO}.
 */
@Mapper(componentModel = "spring", uses = {LoaiDanhMucMapper.class})
public interface DanhMucMapper extends EntityMapper<DanhMucDTO, DanhMuc> {

    @Mapping(source = "loaiDanhMuc.id", target = "loaiDanhMucId")
    DanhMucDTO toDto(DanhMuc danhMuc);

    @Mapping(source = "loaiDanhMucId", target = "loaiDanhMuc")
    DanhMuc toEntity(DanhMucDTO danhMucDTO);

    default DanhMuc fromId(Long id) {
        if (id == null) {
            return null;
        }
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setId(id);
        return danhMuc;
    }
}
