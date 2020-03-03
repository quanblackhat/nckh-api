package com.vnptit.vnpthis.service.mapper;


import com.vnptit.vnpthis.domain.qldt.*;
import com.vnptit.vnpthis.service.dto.QldtDmNguoidungDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QldtDmNguoidung} and its DTO {@link QldtDmNguoidungDTO}.
 */
@Mapper(componentModel = "spring", uses = {QldtDaotaoCtMapper.class, QldtQlHocvienMapper.class})
public interface QldtDmNguoidungMapper extends EntityMapper<QldtDmNguoidungDTO, QldtDmNguoidung> {


    QldtDmNguoidungDTO toDto(QldtDmNguoidung qldtDmNguoidung);

 
    QldtDmNguoidung toEntity(QldtDmNguoidungDTO qldtDmNguoidungDTO);

    default QldtDmNguoidung fromId(Long id) {
        if (id == null) {
            return null;
        }
        QldtDmNguoidung qldtDmNguoidung = new QldtDmNguoidung();
        qldtDmNguoidung.setId(id);
        return qldtDmNguoidung;
    }
}
