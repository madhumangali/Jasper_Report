package com.jasper_report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityProperty {

    private String columnName;

    private String columnType;

    private String columnDisplayName;

}
