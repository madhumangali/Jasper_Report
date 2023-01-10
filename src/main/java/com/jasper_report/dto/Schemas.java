package com.jasper_report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schemas {

    private String catalogName;

    private List<String> schemaName;

}
