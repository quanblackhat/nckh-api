package com.vnptit.vnpthis.service.mapper;


import com.vnptit.vnpthis.domain.qldt.*;
import com.vnptit.vnpthis.service.dto.QldtDmChungchiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QldtDmChungchi} and its DTO {@link QldtDmChungchiDTO}.
 */
@Mapper(componentModel = "spring", uses = {QldtTochucCapMapper.class})
public interface QldtDmChungchiMapper extends EntityMapper<QldtDmChungchiDTO, QldtDmChungchi> {

    @Mapping(source = "qldtTochucCap.id", target = "qldtTochucCapId")
    QldtDmChungchiDTO toDto(QldtDmChungchi qldtDmChungchi);

    @Mapping(target = "chungChis", ignore = true)
    @Mapping(target = "removeChungChi", ignore = true)
    @Mapping(source = "qldtTochucCapId", target = "qldtTochucCap")
    QldtDmChungchi toEntity(QldtDmChungchiDTO qldtDmChungchiDTO);

    default QldtDmChungchi fromId(Long id) {
        if (id == null) {
            return null;
        }
        QldtDmChungchi qldtDmChungchi = new QldtDmChungchi();
        qldtDmChungchi.setId(id);
        return qldtDmChungchi;
    }
}
