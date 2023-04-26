package com.jasper_report.mapper;

import com.jasper_report.dto.Entity;
import com.jasper_report.dto.EntityProperty;
import com.jasper_report.dto.git.GitEntity;
import com.jasper_report.dto.git.GitEntityProperties;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GitEntityMapper {

    GitEntity map(String schemaName, String tableName, List<GitEntityProperties> entityPropertyList);

}
