package com.jasper_report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entity {

    private String schemaName;

    private String tableName;

    private List<EntityProperty> entityPropertyList;

    private List<String> childTables;

    private int order;

}
