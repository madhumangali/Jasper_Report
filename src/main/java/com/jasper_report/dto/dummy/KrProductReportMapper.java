package com.jasper_report.dto.dummy;

import com.jasper_report.dto.dummy.KrProductionReportDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface KrProductReportMapper {

    KrProductionReportDto map(Object object);
}
