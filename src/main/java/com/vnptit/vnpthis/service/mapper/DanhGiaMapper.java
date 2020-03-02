package com.vnptit.vnpthis.service.mapper;

import com.vnptit.vnpthis.domain.*;
import com.vnptit.vnpthis.domain.nckh.DanhGia;
import com.vnptit.vnpthis.service.dto.DanhGiaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DanhGia} and its DTO {@link DanhGiaDTO}.
 */
@Mapper(componentModel = "spring", uses = {DeTaiMapper.class})
public interface DanhGiaMapper extends EntityMapper<DanhGiaDTO, DanhGia> {

    @Mapping(source = "deTai.id", target = "deTaiId")
    DanhGiaDTO toDto(DanhGia danhGia);

    @Mapping(source = "deTaiId", target = "deTai")
    DanhGia toEntity(DanhGiaDTO danhGiaDTO);

    default DanhGia fromId(Long id) {
        if (id == null) {
            return null;
        }
        DanhGia danhGia = new DanhGia();
        danhGia.setId(id);
        return danhGia;
    }
}
