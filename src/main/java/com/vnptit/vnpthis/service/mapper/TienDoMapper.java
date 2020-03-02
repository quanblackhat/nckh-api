package com.vnptit.vnpthis.service.mapper;

import com.vnptit.vnpthis.domain.*;
import com.vnptit.vnpthis.domain.nckh.TienDo;
import com.vnptit.vnpthis.service.dto.TienDoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TienDo} and its DTO {@link TienDoDTO}.
 */
@Mapper(componentModel = "spring", uses = {DeTaiMapper.class})
public interface TienDoMapper extends EntityMapper<TienDoDTO, TienDo> {

    @Mapping(source = "deTai.id", target = "deTaiId")
    TienDoDTO toDto(TienDo tienDo);

    @Mapping(target = "upFiles", ignore = true)
    @Mapping(target = "removeUpFile", ignore = true)
    @Mapping(source = "deTaiId", target = "deTai")
    TienDo toEntity(TienDoDTO tienDoDTO);

    default TienDo fromId(Long id) {
        if (id == null) {
            return null;
        }
        TienDo tienDo = new TienDo();
        tienDo.setId(id);
        return tienDo;
    }
}
