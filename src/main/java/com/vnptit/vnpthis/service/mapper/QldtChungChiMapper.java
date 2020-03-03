package com.vnptit.vnpthis.service.mapper;


import com.vnptit.vnpthis.domain.qldt.*;
import com.vnptit.vnpthis.service.dto.QldtChungChiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QldtChungChi} and its DTO {@link QldtChungChiDTO}.
 */
@Mapper(componentModel = "spring", uses = {QldtDmChungchiMapper.class})
public interface QldtChungChiMapper extends EntityMapper<QldtChungChiDTO, QldtChungChi> {

    @Mapping(source = "qldtDmChungchi.id", target = "qldtDmChungchiId")
    QldtChungChiDTO toDto(QldtChungChi qldtChungChi);

    @Mapping(source = "qldtDmChungchiId", target = "qldtDmChungchi")
    QldtChungChi toEntity(QldtChungChiDTO qldtChungChiDTO);

    default QldtChungChi fromId(Long id) {
        if (id == null) {
            return null;
        }
        QldtChungChi qldtChungChi = new QldtChungChi();
        qldtChungChi.setId(id);
        return qldtChungChi;
    }
}
