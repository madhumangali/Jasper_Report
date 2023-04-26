package com.jasper_report.dto.git;

import com.jasper_report.dto.EntityProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GitEntity {

    private String schemaName;

    private String tableName;

    private List<EntityProperty> entityPropertyList;
}
