package com.jasper_report.mapper;


import com.jasper_report.dto.git.GitEntityProperties;
import org.mapstruct.Mapper;

@Mapper
public interface GitEntityPropertiesMapper {

    GitEntityProperties map(String columnName, String columnType, String columnDisplayName);
}
