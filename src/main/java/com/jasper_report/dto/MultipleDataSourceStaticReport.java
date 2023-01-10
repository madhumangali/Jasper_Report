package com.jasper_report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultipleDataSourceStaticReport {

    private Entity entity ;

    private List<Map> rows;

    private String fileName;

    private String reportHeader;

    private String description;

    private String subject;

    private String titleName;

    private String subtitleName;

    private List<StyleBuilderParamsDto> styleBuilderParamsList;
}
