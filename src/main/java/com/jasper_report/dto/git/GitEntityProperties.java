package com.jasper_report.dto.git;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GitEntityProperties {

    private String columnName;

    private String columnType;

    private String columnDisplayName;

}
