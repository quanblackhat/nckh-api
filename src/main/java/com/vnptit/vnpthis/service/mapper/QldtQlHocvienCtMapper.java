package com.vnptit.vnpthis.service.mapper;


import com.vnptit.vnpthis.domain.qldt.*;
import com.vnptit.vnpthis.service.dto.QldtQlHocvienCtDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QldtQlHocvienCt} and its DTO {@link QldtQlHocvienCtDTO}.
 */
@Mapper(componentModel = "spring", uses = {QldtDaotaoCtMapper.class})
public interface QldtQlHocvienCtMapper extends EntityMapper<QldtQlHocvienCtDTO, QldtQlHocvienCt> {

    @Mapping(source = "qldtDaotaoCt.id", target = "qldtDaotaoCtId")
    QldtQlHocvienCtDTO toDto(QldtQlHocvienCt qldtQlHocvienCt);

    @Mapping(source = "qldtDaotaoCtId", target = "qldtDaotaoCt")
    QldtQlHocvienCt toEntity(QldtQlHocvienCtDTO qldtQlHocvienCtDTO);

    default QldtQlHocvienCt fromId(Long id) {
        if (id == null) {
            return null;
        }
        QldtQlHocvienCt qldtQlHocvienCt = new QldtQlHocvienCt();
        qldtQlHocvienCt.setId(id);
        return qldtQlHocvienCt;
    }
}
