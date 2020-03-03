package com.vnptit.vnpthis.service.mapper;


import com.vnptit.vnpthis.domain.qldt.*;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoctDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QldtDutoanDaotaoct} and its DTO {@link QldtDutoanDaotaoctDTO}.
 */
@Mapper(componentModel = "spring", uses = {QldtDutoanDaotaoMapper.class, QldtDmNoidungMapper.class})
public interface QldtDutoanDaotaoctMapper extends EntityMapper<QldtDutoanDaotaoctDTO, QldtDutoanDaotaoct> {

    @Mapping(source = "qldtDutoanDaotao.id", target = "qldtDutoanDaotaoId")
    @Mapping(source = "qldtDmNoidung.id", target = "qldtDmNoidungId")
    QldtDutoanDaotaoctDTO toDto(QldtDutoanDaotaoct qldtDutoanDaotaoct);

    @Mapping(source = "qldtDutoanDaotaoId", target = "qldtDutoanDaotao")
    @Mapping(source = "qldtDmNoidungId", target = "qldtDmNoidung")
    QldtDutoanDaotaoct toEntity(QldtDutoanDaotaoctDTO qldtDutoanDaotaoctDTO);

    default QldtDutoanDaotaoct fromId(Long id) {
        if (id == null) {
            return null;
        }
        QldtDutoanDaotaoct qldtDutoanDaotaoct = new QldtDutoanDaotaoct();
        qldtDutoanDaotaoct.setId(id);
        return qldtDutoanDaotaoct;
    }
}
