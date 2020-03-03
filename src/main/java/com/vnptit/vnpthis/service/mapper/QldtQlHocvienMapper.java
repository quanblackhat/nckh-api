package com.vnptit.vnpthis.service.mapper;


import com.vnptit.vnpthis.domain.qldt.*;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QldtQlHocvien} and its DTO {@link QldtQlHocvienDTO}.
 */
@Mapper(componentModel = "spring", uses = {QldtDaotaoMapper.class})
public interface QldtQlHocvienMapper extends EntityMapper<QldtQlHocvienDTO, QldtQlHocvien> {

    @Mapping(source = "qldtDaotao.id", target = "qldtDaotaoId")
    QldtQlHocvienDTO toDto(QldtQlHocvien qldtQlHocvien);

    @Mapping(source = "qldtDaotaoId", target = "qldtDaotao")
    QldtQlHocvien toEntity(QldtQlHocvienDTO qldtQlHocvienDTO);

    default QldtQlHocvien fromId(Long id) {
        if (id == null) {
            return null;
        }
        QldtQlHocvien qldtQlHocvien = new QldtQlHocvien();
        qldtQlHocvien.setId(id);
        return qldtQlHocvien;
    }
}
