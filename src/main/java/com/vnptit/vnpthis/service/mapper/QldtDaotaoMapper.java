package com.vnptit.vnpthis.service.mapper;


import com.vnptit.vnpthis.domain.qldt.*;
import com.vnptit.vnpthis.service.dto.QldtDaotaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QldtDaotao} and its DTO {@link QldtDaotaoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QldtDaotaoMapper extends EntityMapper<QldtDaotaoDTO, QldtDaotao> {


    @Mapping(target = "daoTaoCts", ignore = true)
    @Mapping(target = "removeDaoTaoCt", ignore = true)
    @Mapping(target = "hocViens", ignore = true)
    @Mapping(target = "removeHocVien", ignore = true)
    QldtDaotao toEntity(QldtDaotaoDTO qldtDaotaoDTO);

    default QldtDaotao fromId(Long id) {
        if (id == null) {
            return null;
        }
        QldtDaotao qldtDaotao = new QldtDaotao();
        qldtDaotao.setId(id);
        return qldtDaotao;
    }
}
