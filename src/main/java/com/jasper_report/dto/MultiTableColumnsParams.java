package com.jasper_report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiTableColumnsParams {

    private List<Entity> entityList;

    private String fileName;

    private String reportHeader;

    private String description;

    private String subject;

    private String titleName;

    private String subtitleName;

    private List<StyleBuilderParamsDto> styleBuilderParamsList;

}
