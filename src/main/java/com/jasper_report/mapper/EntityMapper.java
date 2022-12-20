package com.jasper_report.mapper;

import com.jasper_report.dto.Entity;
import com.jasper_report.dto.EntityProperty;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EntityMapper {

    Entity map(String schemaName,String tableName,List<EntityProperty> entityPropertyList,List<String> childTables);

}
