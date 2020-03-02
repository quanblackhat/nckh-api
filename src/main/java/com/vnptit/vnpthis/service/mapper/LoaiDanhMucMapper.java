package com.vnptit.vnpthis.service.mapper;

import com.vnptit.vnpthis.domain.*;
import com.vnptit.vnpthis.domain.nckh.LoaiDanhMuc;
import com.vnptit.vnpthis.service.dto.LoaiDanhMucDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LoaiDanhMuc} and its DTO {@link LoaiDanhMucDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LoaiDanhMucMapper extends EntityMapper<LoaiDanhMucDTO, LoaiDanhMuc> {


    @Mapping(target = "danhMucs", ignore = true)
    @Mapping(target = "removeDanhMuc", ignore = true)
    LoaiDanhMuc toEntity(LoaiDanhMucDTO loaiDanhMucDTO);

    default LoaiDanhMuc fromId(Long id) {
        if (id == null) {
            return null;
        }
        LoaiDanhMuc loaiDanhMuc = new LoaiDanhMuc();
        loaiDanhMuc.setId(id);
        return loaiDanhMuc;
    }
}
