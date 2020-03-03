package com.vnptit.vnpthis.service.mapper;


import com.vnptit.vnpthis.domain.qldt.*;
import com.vnptit.vnpthis.service.dto.QldtDutoanDaotaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QldtDutoanDaotao} and its DTO {@link QldtDutoanDaotaoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QldtDutoanDaotaoMapper extends EntityMapper<QldtDutoanDaotaoDTO, QldtDutoanDaotao> {


    @Mapping(target = "duToanDaotaoCts", ignore = true)
    @Mapping(target = "removeDuToanDaotaoCt", ignore = true)
    QldtDutoanDaotao toEntity(QldtDutoanDaotaoDTO qldtDutoanDaotaoDTO);

    default QldtDutoanDaotao fromId(Long id) {
        if (id == null) {
            return null;
        }
        QldtDutoanDaotao qldtDutoanDaotao = new QldtDutoanDaotao();
        qldtDutoanDaotao.setId(id);
        return qldtDutoanDaotao;
    }
}
