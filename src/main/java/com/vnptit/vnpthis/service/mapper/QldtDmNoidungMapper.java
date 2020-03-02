package com.vnptit.vnpthis.service.mapper;


import com.vnptit.vnpthis.domain.qldt.*;
import com.vnptit.vnpthis.service.dto.QldtDmNoidungDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QldtDmNoidung} and its DTO {@link QldtDmNoidungDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QldtDmNoidungMapper extends EntityMapper<QldtDmNoidungDTO, QldtDmNoidung> {


    @Mapping(target = "duToandaotaoCts", ignore = true)
    @Mapping(target = "removeDuToandaotaoCt", ignore = true)
    QldtDmNoidung toEntity(QldtDmNoidungDTO qldtDmNoidungDTO);

    default QldtDmNoidung fromId(Long id) {
        if (id == null) {
            return null;
        }
        QldtDmNoidung qldtDmNoidung = new QldtDmNoidung();
        qldtDmNoidung.setId(id);
        return qldtDmNoidung;
    }
}
