package com.vnptit.vnpthis.service.mapper;


import com.vnptit.vnpthis.domain.qldt.*;
import com.vnptit.vnpthis.service.dto.QldtDaotaoCtDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QldtDaotaoCt} and its DTO {@link QldtDaotaoCtDTO}.
 */
@Mapper(componentModel = "spring", uses = {QldtDaotaoMapper.class})
public interface QldtDaotaoCtMapper extends EntityMapper<QldtDaotaoCtDTO, QldtDaotaoCt> {

    @Mapping(source = "qldtDaotao.id", target = "qldtDaotaoId")
    QldtDaotaoCtDTO toDto(QldtDaotaoCt qldtDaotaoCt);

    @Mapping(target = "hocVienCts", ignore = true)
    @Mapping(target = "removeHocVienCt", ignore = true)
    @Mapping(source = "qldtDaotaoId", target = "qldtDaotao")
    QldtDaotaoCt toEntity(QldtDaotaoCtDTO qldtDaotaoCtDTO);

    default QldtDaotaoCt fromId(Long id) {
        if (id == null) {
            return null;
        }
        QldtDaotaoCt qldtDaotaoCt = new QldtDaotaoCt();
        qldtDaotaoCt.setId(id);
        return qldtDaotaoCt;
    }
}
