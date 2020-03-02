package com.vnptit.vnpthis.service.mapper;

import com.vnptit.vnpthis.domain.*;
import com.vnptit.vnpthis.domain.nckh.NhanSu;
import com.vnptit.vnpthis.service.dto.NhanSuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NhanSu} and its DTO {@link NhanSuDTO}.
 */
@Mapper(componentModel = "spring", uses = {DeTaiMapper.class})
public interface NhanSuMapper extends EntityMapper<NhanSuDTO, NhanSu> {

    @Mapping(source = "deTai.id", target = "deTaiId")
    NhanSuDTO toDto(NhanSu nhanSu);

    @Mapping(source = "deTaiId", target = "deTai")
    NhanSu toEntity(NhanSuDTO nhanSuDTO);

    default NhanSu fromId(Long id) {
        if (id == null) {
            return null;
        }
        NhanSu nhanSu = new NhanSu();
        nhanSu.setId(id);
        return nhanSu;
    }
}
