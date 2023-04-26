package com.jasper_report.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntityProperty {

    private String columnName;

    private String columnType;

    private String columnDisplayName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnDisplayName() {
        return columnDisplayName;
    }

    public void setColumnDisplayName(String columnDisplayName) {
        this.columnDisplayName = columnDisplayName;
    }
}
