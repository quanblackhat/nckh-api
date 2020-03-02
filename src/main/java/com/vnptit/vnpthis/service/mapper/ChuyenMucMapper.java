package com.vnptit.vnpthis.service.mapper;

import com.vnptit.vnpthis.domain.*;
import com.vnptit.vnpthis.service.dto.ChuyenMucDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChuyenMuc} and its DTO {@link ChuyenMucDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChuyenMucMapper extends EntityMapper<ChuyenMucDTO, ChuyenMuc> {


    @Mapping(target = "deTais", ignore = true)
    @Mapping(target = "removeDeTai", ignore = true)
    ChuyenMuc toEntity(ChuyenMucDTO chuyenMucDTO);

    default ChuyenMuc fromId(Long id) {
        if (id == null) {
            return null;
        }
        ChuyenMuc chuyenMuc = new ChuyenMuc();
        chuyenMuc.setId(id);
        return chuyenMuc;
    }
}
