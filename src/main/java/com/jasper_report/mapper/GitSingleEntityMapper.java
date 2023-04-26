package com.jasper_report.mapper;

import com.jasper_report.dto.Entity;
import com.jasper_report.dto.git.GitEntity;
import com.jasper_report.dto.git.GitEntityProperties;
import com.jasper_report.dto.git.GitSingleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GitSingleEntityMapper {

    GitSingleEntity map( GitEntity parentEntity, List<GitEntity> childEntities);

}
