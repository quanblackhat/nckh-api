package com.vnptit.vnpthis.service.mapper;


import com.vnptit.vnpthis.domain.qldt.*;
import com.vnptit.vnpthis.service.dto.QldtTochucCapDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QldtTochucCap} and its DTO {@link QldtTochucCapDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QldtTochucCapMapper extends EntityMapper<QldtTochucCapDTO, QldtTochucCap> {


    @Mapping(target = "dmChungchis", ignore = true)
    @Mapping(target = "removeDmChungchi", ignore = true)
    QldtTochucCap toEntity(QldtTochucCapDTO qldtTochucCapDTO);

    default QldtTochucCap fromId(Long id) {
        if (id == null) {
            return null;
        }
        QldtTochucCap qldtTochucCap = new QldtTochucCap();
        qldtTochucCap.setId(id);
        return qldtTochucCap;
    }
}
