package com.jasper_report.mapper;

import com.jasper_report.dto.MultiTableColumnsResult;
import org.mapstruct.Mapper;

@Mapper
public interface MultiTableColumnsResultMapper {

    MultiTableColumnsResult mapToResult(String fileName,String reportHeader,String description,
                                        String subject,String titleName,String subtitleName);

}
