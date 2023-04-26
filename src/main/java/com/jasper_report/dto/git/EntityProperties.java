package com.jasper_report.dto.git;

import com.jasper_report.dto.Entity;
import com.jasper_report.dto.EntityProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityProperties {

    private String schemaName;

    private String tableName;

    private List<EntityProperty> entityPropertyList;

    private List<EntityProperties> childTables;

    private int order;

}
