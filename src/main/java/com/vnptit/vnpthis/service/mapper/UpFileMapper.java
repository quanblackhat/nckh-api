package com.vnptit.vnpthis.service.mapper;

import com.vnptit.vnpthis.domain.*;
import com.vnptit.vnpthis.domain.nckh.UpFile;
import com.vnptit.vnpthis.service.dto.UpFileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UpFile} and its DTO {@link UpFileDTO}.
 */
@Mapper(componentModel = "spring", uses = {DeTaiMapper.class, TienDoMapper.class})
public interface UpFileMapper extends EntityMapper<UpFileDTO, UpFile> {

    @Mapping(source = "deTai.id", target = "deTaiId")
    @Mapping(source = "tienDo.id", target = "tienDoId")
    UpFileDTO toDto(UpFile upFile);

    @Mapping(source = "deTaiId", target = "deTai")
    @Mapping(source = "tienDoId", target = "tienDo")
    UpFile toEntity(UpFileDTO upFileDTO);

    default UpFile fromId(Long id) {
        if (id == null) {
            return null;
        }
        UpFile upFile = new UpFile();
        upFile.setId(id);
        return upFile;
    }
}
