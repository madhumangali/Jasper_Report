package com.jasper_report.mapper;

import com.jasper_report.dto.StyleBuilderParamsDto;
import com.jasper_report.model.StyleBuilderDuplicate;
import com.jasper_report.model.StyleBuilderParams;
import org.mapstruct.Mapper;

@Mapper
public interface StyleBuilderMapper {

    StyleBuilderParamsDto mapToDto(StyleBuilderDuplicate styleBuilderDuplicate);


    StyleBuilderDuplicate mapTODuplicate( StyleBuilderParamsDto styleBuilderParams,String titleName,String subtitleName,
                                          String fileName,String reportHeader,String description,String subject);

    StyleBuilderParams mapToBuilderParams(StyleBuilderParamsDto styleBuilderParams,String titleName,String subtitleName,
                                          String fileName,String reportHeader,String description,String subject);

}
