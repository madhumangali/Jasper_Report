package com.jasper_report.mapper;

import com.jasper_report.dto.EntityProperty;
import org.mapstruct.Mapper;

@Mapper
public interface EntityPropertyMapper {

    EntityProperty map(String columnName,String columnType,String columnDisplayName);

}
