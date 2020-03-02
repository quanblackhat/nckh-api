package com.vnptit.vnpthis.service.mapper;

import com.vnptit.vnpthis.domain.*;
import com.vnptit.vnpthis.domain.nckh.DuToan;
import com.vnptit.vnpthis.service.dto.DuToanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DuToan} and its DTO {@link DuToanDTO}.
 */
@Mapper(componentModel = "spring", uses = {DeTaiMapper.class})
public interface DuToanMapper extends EntityMapper<DuToanDTO, DuToan> {

    @Mapping(source = "deTai.id", target = "deTaiId")
    DuToanDTO toDto(DuToan duToan);

    @Mapping(source = "deTaiId", target = "deTai")
    DuToan toEntity(DuToanDTO duToanDTO);

    default DuToan fromId(Long id) {
        if (id == null) {
            return null;
        }
        DuToan duToan = new DuToan();
        duToan.setId(id);
        return duToan;
    }
}
