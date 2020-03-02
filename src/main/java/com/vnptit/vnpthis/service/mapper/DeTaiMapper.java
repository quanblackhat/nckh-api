package com.vnptit.vnpthis.service.mapper;

import com.vnptit.vnpthis.domain.*;
import com.vnptit.vnpthis.service.dto.DeTaiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeTai} and its DTO {@link DeTaiDTO}.
 */
@Mapper(componentModel = "spring", uses = {ChuyenMucMapper.class})
public interface DeTaiMapper extends EntityMapper<DeTaiDTO, DeTai> {

    @Mapping(source = "chuyenMuc.id", target = "chuyenMucId")
    DeTaiDTO toDto(DeTai deTai);

    @Mapping(target = "upFiles", ignore = true)
    @Mapping(target = "removeUpFile", ignore = true)
    @Mapping(target = "tienDos", ignore = true)
    @Mapping(target = "removeTienDo", ignore = true)
    @Mapping(target = "nhanSus", ignore = true)
    @Mapping(target = "removeNhanSu", ignore = true)
    @Mapping(target = "duToans", ignore = true)
    @Mapping(target = "removeDuToan", ignore = true)
    @Mapping(target = "danhGias", ignore = true)
    @Mapping(target = "removeDanhGia", ignore = true)
    @Mapping(source = "chuyenMucId", target = "chuyenMuc")
    DeTai toEntity(DeTaiDTO deTaiDTO);

    default DeTai fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeTai deTai = new DeTai();
        deTai.setId(id);
        return deTai;
    }
}
